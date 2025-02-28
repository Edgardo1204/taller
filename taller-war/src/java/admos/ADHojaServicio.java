/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import manipuladatos.MDHojaServicio;
import modelo.HojaServicio;

/**
 *
 * @author Jesus
 */
@Named(value = "aDHojaServicio")
@SessionScoped
public class ADHojaServicio implements Serializable {

    @EJB
    private MDHojaServicio mDHojaServicio;

    private HojaServicio hojaServicio;
    private List<HojaServicio> hojasServicio;

    public ADHojaServicio() {
        hojaServicio = new HojaServicio();
    }

    public HojaServicio getHojaServicio() {
        return hojaServicio;
    }

    public void setHojaServicio(HojaServicio hojaServicio) {
        this.hojaServicio = hojaServicio;
    }

    public void setHojasServicio(List<HojaServicio> hojasServicio) {
        this.hojasServicio = hojasServicio;
    }

    public List<HojaServicio> getHojaServicios() {
        if (hojasServicio == null) {
            hojasServicio = mDHojaServicio.obtenerHojaServicio();
            System.out.println("Clientes cargados: " + hojasServicio.size());  // Verifica en la consola que se estén cargando los clientes
        }
        return hojasServicio;
    }

    public String agregarHojaServicio() {
        try {
            mDHojaServicio.insertarHojaServicio(hojaServicio);
            hojasServicio = mDHojaServicio.obtenerHojaServicio();
            hojaServicio = new HojaServicio();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPage";
        }
    }

    public int getUltimaHojaServicio() {
        List<HojaServicio> hojas = getHojaServicios();
        if (hojas != null && !hojas.isEmpty()) {
            return hojas.get(hojas.size() - 1).getFolio() + 1;
        }
        return 1;
    }

    public int getUltimoFolio() {
        List<HojaServicio> hojas = getHojaServicios();
        if (hojas == null || hojas.isEmpty()) {
            return 1;
        } else {
            return hojas.get(hojas.size() - 1).getFolio() + 1;
        }
    }

    public String eliminarHojaServicio(HojaServicio hj) {
        mDHojaServicio.eliminarHojaServicio(hj.getId());
        hojasServicio = mDHojaServicio.obtenerHojaServicio();
        return "index?faces-redirect=true";
    }

    public HojaServicio getHojaServicioId(int idHoja) {
        return mDHojaServicio.obtenerHojaServicioPorId(idHoja);
    }

    public static Date getFechaActual() {
        // Obtener la fecha actual en LocalDate
        LocalDate fechaActual = LocalDate.now();

        // Convertir LocalDate a Date
        Date fecha = Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return fecha;
    }

    public HojaServicio getUltimaHojaRegistrada() {
        List<HojaServicio> hojas = getHojaServicios();
        if (hojas != null && !hojas.isEmpty()) {
            return hojas.get(hojas.size() - 1);
        }
        return null; // Retorna null si no hay hojas de servicio registradas
    }

}
