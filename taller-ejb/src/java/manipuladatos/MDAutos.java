/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import datos.AutosFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Autos;

/**
 *
 * @author Jesus
 */
@Stateless
@LocalBean
public class MDAutos {

    @EJB
    private AutosFacade AutosF;

    public void insertarAuto(Autos m) {
        try {
            AutosF.create(m);
        } catch (Exception e) {
            e.printStackTrace(); // O usa un logger para registrar el error.
            throw new RuntimeException("Error al insertar el auto", e);
        }
    }

    public Autos obtenerAutoPorId(int id) {
        return AutosF.find(id);
    }

    public List<Autos> obtenerAutos() {
        return AutosF.findAll();
    }

    public void actualizarAuto(Autos m) {
        AutosF.edit(m);
    }

    public void eliminarAuto(int id) {
        Autos inv = AutosF.find(id);
        if (inv != null) {
            AutosF.remove(inv);

        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
