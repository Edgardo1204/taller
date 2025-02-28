/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import manipuladatos.MDEmpleado;
import modelo.Clientes;
import modelo.Empleado;

/**
 *
 * @author Jesus
 */
@Named(value = "aDEmpleado")
@SessionScoped
public class ADEmpleado implements Serializable {

    @EJB
    private MDEmpleado mDEmpleado;

    private Empleado empleado = new Empleado();
    private List<Empleado> empleados = null;

    /**
     * Creates a new instance of ADEmpleado
     */
    public ADEmpleado() {
        empleado = new Empleado();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getEmpleados() {
        if (empleados == null) {
            empleados = mDEmpleado.obtenerEmpleado();
        }
        return empleados;
    }
    
        public String agregarEmpleado() {
        try {
            mDEmpleado.insertarEmpleado(empleado);
            empleados = mDEmpleado.obtenerEmpleado();
            empleado = new Empleado();
            return "cliente?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPage";
        }
    }
}
