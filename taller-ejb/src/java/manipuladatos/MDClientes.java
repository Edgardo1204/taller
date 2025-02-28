/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import datos.ClientesFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Clientes;

/**
 *
 * @author Jesus
 */
@Stateless
@LocalBean
public class MDClientes {

    @EJB
    private ClientesFacade clientesF;

    public void insertarCliente(Clientes m) {
        try {
            clientesF.create(m);
        } catch (Exception e) {
            e.printStackTrace(); // O usa un logger para registrar el error.
            throw new RuntimeException("Error al insertar el cliente", e);
        }
    }

    public Clientes obtenerClientePorId(int id) {
        return clientesF.find(id);
    }

    public List<Clientes> obtenerCliente() {
        return clientesF.findAll();
    }

    public void actualizarCliente(Clientes m) {
        clientesF.edit(m);
    }

    public void eliminarCliente(int id) {
        Clientes inv = clientesF.find(id);
        if (inv != null) {
            clientesF.remove(inv);

        }
    }
}
