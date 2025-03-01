/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import datos.HojaServicioFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Clientes;
import modelo.HojaServicio;

/**
 *
 * @author Jesus
 */
@Stateless
@LocalBean
public class MDHojaServicio {

    @EJB
    private HojaServicioFacade hojaServicioF;

    public void insertarHojaServicio(HojaServicio m) {
        try {
            hojaServicioF.create(m);
        } catch (Exception e) {
            e.printStackTrace(); // O usa un logger para registrar el error.
            throw new RuntimeException("Error al insertar el cliente", e);
        }
    }

    public HojaServicio obtenerHojaServicioPorId(int id) {
        return hojaServicioF.find(id);
    }

    public List<HojaServicio> obtenerHojaServicio() {
        return hojaServicioF.findAll();
    }

    public void actualizarHojaServicio(HojaServicio m) {
        hojaServicioF.edit(m);
    }

    public void eliminarHojaServicio(int id) {
        HojaServicio hoja = hojaServicioF.find(id);
        if (hoja != null) {
            hojaServicioF.remove(hoja);

        }
    }

    public List<HojaServicio> getHojasByIdCliente(Clientes x) {
        return hojaServicioF.getHojaServicioByIdCliente(x);
    }
}
