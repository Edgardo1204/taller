/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import datos.EmpleadoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Empleado;

/**
 *
 * @author Jesus
 */
@Stateless
@LocalBean
public class MDEmpleado {

    @EJB
    private EmpleadoFacade empleadoF;

        public void insertarEmpleado(Empleado m) {
        try {
            empleadoF.create(m);
        } catch (Exception e) {
            e.printStackTrace(); // O usa un logger para registrar el error.
            throw new RuntimeException("Error al insertar el cliente", e);
        }
    }

    public Empleado obtenerEmpleadoPorId(int id) {
        return empleadoF.find(id);
    }

    public List<Empleado> obtenerEmpleado() {
        return empleadoF.findAll();
    }

    public void actualizarEmpleado(Empleado m) {
        empleadoF.edit(m);
    }

    public void eliminarEmpleado(int id) {
        Empleado empleado = empleadoF.find(id);
        if (empleado != null) {
            empleadoF.remove(empleado);

        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
