/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import datos.DetalleServicioFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.DetalleServicio;

/**
 *
 * @author Jesus
 */
@Stateless
@LocalBean
public class MDDetalleServicio {

    @EJB
    private DetalleServicioFacade detalleServicioF;

        public void insertarDetalleServicio(DetalleServicio m) {
        try {
            detalleServicioF.create(m);
        } catch (Exception e) {
            e.printStackTrace(); // O usa un logger para registrar el error.
            throw new RuntimeException("Error al insertar el cliente", e);
        }
    }

    public DetalleServicio obtenerDetalleServicioPorId(int id) {
        return detalleServicioF.find(id);
    }

    public List<DetalleServicio> obtenerDetalleServicio() {
        return detalleServicioF.findAll();
    }

    public void actualizarDetalleServicio(DetalleServicio m) {
        detalleServicioF.edit(m);
    }

    public void eliminarDetalleServicio(int id) {
        DetalleServicio detallehoja = detalleServicioF.find(id);
        if (detallehoja != null) {
            detalleServicioF.remove(detallehoja);

        }
    }

}
