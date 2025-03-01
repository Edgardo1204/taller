/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import datos.HojaServicioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;
import manipuladatos.MDHojaServicio;
import modelo.HojaServicio;
import modelo.Inventario;

/**
 *
 * @author betor
 */
@Named(value = "aDHoja_Servicio")
@SessionScoped
public class ADHoja_Servicio implements Serializable {

    @EJB
    private MDHojaServicio mDHojaServicio;

    private HojaServicio hoja = new HojaServicio();
    private List<HojaServicio> hojas = null;
    private String mensajeHojas;
    private int folio_a_generar = 0;
    private HojaServicio hojaServicioSeleccionado;

    /**
     * Creates a new instance of ADHoja_Servicio
     */
    public ADHoja_Servicio() {

    }

    public HojaServicio getHojaServicio() {
        return hoja;
    }

    public void setHojaServicio(HojaServicio hoja) {
        this.hoja = hoja;
    }

    public List<HojaServicio> getHojaServicioList() {
        if (hojas == null) {
            hojas = mDHojaServicio.obtenerHojaServicio();
        }
        return hojas;
    }

    public String agregarHojaServicio() {
        try {
            mensajeHojas = "Hoja de servicio agregada con éxito.";
            mDHojaServicio.insertarHojaServicio(hoja);
            hojas = mDHojaServicio.obtenerHojaServicio();
            hoja = new HojaServicio();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            mensajeHojas = "Error al agregar la Hoja de Servicio.";
            return "index?faces-redirect=true";
        }
    }

    public String eliminarHojaServicio(HojaServicio hoja) {
        mDHojaServicio.eliminarHojaServicio(hoja.getId());
        hojas = mDHojaServicio.obtenerHojaServicio();
        return "index?faces-redirect=true";
    }

    public HojaServicio obtenerHojaPorId() {
        return mDHojaServicio.obtenerHojaServicioPorId(folio_a_generar);
    }

    public void actualizarCampos() {
    }

    public String getMensajeHojas() {
        return mensajeHojas;
    }

    public void setMensajeHojas(String mensajeHojas) {
        this.mensajeHojas = mensajeHojas;
    }

    public List<HojaServicio> getHojasActivas(ADClientes ac) {
        return mDHojaServicio.getHojasByIdCliente(ac.getCliente());
    }

    public MDHojaServicio getmDHojaServicio() {
        return mDHojaServicio;
    }

    public void setmDHojaServicio(MDHojaServicio mDHojaServicio) {
        this.mDHojaServicio = mDHojaServicio;
    }

    public HojaServicio getHoja() {
        return hoja;
    }

    public void setHoja(HojaServicio hoja) {
        this.hoja = hoja;
    }

    public List<HojaServicio> getHojas() {
        return hojas;
    }

    public void setHojas(List<HojaServicio> hojas) {
        this.hojas = hojas;
    }

    public int getFolio_a_generar() {
        return folio_a_generar;
    }

    public void setFolio_a_generar(int folio_a_generar) {
        this.folio_a_generar = folio_a_generar;
    }

}
