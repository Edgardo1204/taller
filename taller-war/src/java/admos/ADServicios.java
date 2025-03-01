package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import manipuladatos.MDDetalleServicio;
import manipuladatos.MDHojaServicio;
import manipuladatos.MDInventario;
import modelo.DetalleServicio;
import modelo.Empleado;
import modelo.HojaServicio;
import modelo.Inventario;
import modelo.Servicio;

@Named(value = "aDServicios")
@SessionScoped
public class ADServicios implements Serializable {

    @EJB
    private MDHojaServicio mDHojaServicio;

    @EJB
    private MDDetalleServicio mDDetalleServicio;

    @EJB
    private MDInventario mDInventario;

    @Inject
    private ADHojaServicio aDHojaServicio;

    @Inject
    private ADClientes aDClientes;

    @Inject
    private ADEmpleado aDEmpleado;
    
    @Inject
    private ADAutos aDAutos;

    private List<Inventario> serviciosDisponibles;
    private List<Inventario> selectedServicios;
    private DetalleServicio detalleServ;
    private HojaServicio hojaServicioGuardada;
    private List<DetalleServicio> detallesGuardados;

    @PostConstruct
    public void init() {
        serviciosDisponibles = getServiciosDisponibles();
        selectedServicios = new ArrayList<>();
        detallesGuardados = new ArrayList<>();

    }

    public List<Inventario> getServiciosDisponibles() {
        if (serviciosDisponibles == null) {
            serviciosDisponibles = mDInventario.obtenerInventario();
        }

        return serviciosDisponibles.stream()
                .sorted(Comparator.comparing(
                        i -> !"Servicio".equalsIgnoreCase(i.getTipo())
                ))
                .toList();
    }

    public List<Inventario> getSelectedServicios() {
        return selectedServicios;
    }

    public void setSelectedServicios(List<Inventario> selectedServicios) {
        this.selectedServicios = selectedServicios;
    }

    public DetalleServicio getDetalleServ() {
        return detalleServ;
    }

    public void setDetalleServ(DetalleServicio detalleServ) {
        this.detalleServ = detalleServ;
    }

    public HojaServicio getHojaServicioGuardada() {
        return hojaServicioGuardada;
    }

    public List<DetalleServicio> getDetallesGuardados() {
        return detallesGuardados;
    }

    public void guardarSelecciones() {
        if (selectedServicios == null || selectedServicios.isEmpty()) {
            showMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "No ha seleccionado ningún servicio");
            return;
        }

        int serviciosSinCantidad = 0;
        int serviciosSinPrecio = 0;

        for (Inventario servicio : selectedServicios) {
            if (servicio.getCantidad() == null || servicio.getCantidad() <= 0) {
                servicio.setCantidad(1); // Establecer un valor predeterminado
                serviciosSinCantidad++;
            }
            if (servicio.getPrecioVenta() == null || servicio.getPrecioVenta() <= 0) {
                servicio.setPrecioVenta(0); // Establecer un valor predeterminado
                serviciosSinPrecio++;
            }
        }

        if (serviciosSinCantidad > 0 || serviciosSinPrecio > 0) {
            showMessage(FacesMessage.SEVERITY_INFO, "Información",
                    "Se ha establecido una cantidad mínima de 1 para " + serviciosSinCantidad
                    + " servicio(s) sin cantidad especificada y un precio de 0 para "
                    + serviciosSinPrecio + " servicio(s) sin precio especificado");
        } else {
            showMessage(FacesMessage.SEVERITY_INFO, "Éxito",
                    "Se han guardado " + selectedServicios.size() + " servicios");
        }
    }

    public String guardarServicio() {
        if (selectedServicios == null || selectedServicios.isEmpty()) {
            showMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Debe seleccionar al menos un servicio");
            return null;
        }

        if (!cantidadesValidas()) {
            showMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Todos los servicios deben tener una cantidad válida");
            return null;
        }

        Empleado empleadoSeleccionado = aDEmpleado.buscarEmpleadoPorId(aDEmpleado.getIdEmpleadoSeleccionado());
        if (empleadoSeleccionado == null) {
            showMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Debe seleccionar un empleado válido");
            return null;
        }

        // Crear y guardar la hoja de servicio
        HojaServicio hojaServicio = new HojaServicio();
        hojaServicio.setFolio(aDHojaServicio.getUltimoFolio());
        hojaServicio.setIdCliente(aDClientes.getCliente());
        hojaServicio.setIdEmpleado(empleadoSeleccionado);
        hojaServicio.setFecha(aDHojaServicio.getFechaActual());
        hojaServicio.setObservaciones(aDHojaServicio.getHojaServicio().getObservaciones());
        

        mDHojaServicio.insertarHojaServicio(hojaServicio);

        // Guardar los detalles del servicio
        List<DetalleServicio> detalles = new ArrayList<>();
        for (Inventario servicio : selectedServicios) {
            DetalleServicio detalleServicio = new DetalleServicio();
            detalleServicio.setIdHoja(aDHojaServicio.getUltimaHojaRegistrada());
            detalleServicio.setIdProdServ(servicio);
            detalleServicio.setCantidad(servicio.getCantidad());
            detalleServicio.setPrecio(servicio.getPrecioVenta());

            mDDetalleServicio.insertarDetalleServicio(detalleServicio);
            detalles.add(detalleServicio);
        }

        // Almacenar datos para impresión
        this.hojaServicioGuardada = hojaServicio;
        this.detallesGuardados = detalles;
        aDAutos.actualizarEstadoAuto(aDAutos.getAutoId(), false);
        showMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Servicios guardados correctamente");

        return "HojaServicioGen.xhtml?faces-redirect=true";  // Redirigir a la página de impresión
    }

    private boolean cantidadesValidas() {
        for (Inventario servicio : selectedServicios) {
            if (servicio.getCantidad() == null || servicio.getCantidad() <= 0) {
                return false;
            }
        }
        return true;
    }

    private void guardarServiciosEnBaseDeDatos() {
        System.out.println("Servicios guardados: " + selectedServicios.size());
        for (Inventario servicio : selectedServicios) {
            System.out.println("Servicio: " + servicio.getNombre() + ", Cantidad: " + servicio.getCantidad());
        }
    }

    private void showMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    private void almacenarDatosParaImpresion(HojaServicio hojaServicio, List<DetalleServicio> detalles) {
        this.hojaServicioGuardada = hojaServicio;
        this.detallesGuardados = new ArrayList<>(detalles);
    }

}
