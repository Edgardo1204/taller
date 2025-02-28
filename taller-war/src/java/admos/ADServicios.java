package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import manipuladatos.MDDetalleServicio;
import manipuladatos.MDInventario;
import modelo.DetalleServicio;
import modelo.HojaServicio;
import modelo.Inventario;
import modelo.Servicio;

@Named(value = "aDServicios")
@SessionScoped
public class ADServicios implements Serializable {

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

    private List<Inventario> serviciosDisponibles;
    private List<Inventario> selectedServicios;
    private DetalleServicio detalleServ;

    @PostConstruct
    public void init() {
        serviciosDisponibles = getServiciosDisponibles();
        selectedServicios = new ArrayList<>();
    }

    public List<Inventario> getServiciosDisponibles() {
        if (serviciosDisponibles == null) {
            serviciosDisponibles = mDInventario.obtenerInventario();
        }
        return serviciosDisponibles.stream()
                .filter(i -> "Servicio".equalsIgnoreCase(i.getCategoria()))
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

        // Insertar la hoja de servicio
        HojaServicio hojaServicio = new HojaServicio();
        hojaServicio.setFolio(aDHojaServicio.getUltimoFolio());
        hojaServicio.setIdCliente(aDClientes.getCliente());
        hojaServicio.setIdEmpleado(aDEmpleado.getEmpleado());
        hojaServicio.setFecha(aDHojaServicio.getFechaActual());
        hojaServicio.setObservaciones("Observaciones del servicio");

        aDHojaServicio.agregarHojaServicio();

        // Obtener el id de la hoja de servicio recién insertada
        //int idHojaServicio = aDHojaServicio.getUltimaHojaServicio() - 1;

        // Insertar los detalles del servicio
        for (Inventario servicio : selectedServicios) {
            DetalleServicio detalleServicio = new DetalleServicio();
            detalleServicio.setIdHoja(aDHojaServicio.getUltimaHojaRegistrada());
            detalleServicio.setIdProdServ(servicio);
            detalleServicio.setCantidad(servicio.getCantidad());
            detalleServicio.setPrecio(servicio.getPrecioVenta());

            mDDetalleServicio.insertarDetalleServicio(detalleServicio);
        }

        showMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Servicios guardados correctamente");

        return null; // Permanece en la misma página o puedes redirigir a otra
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

}
