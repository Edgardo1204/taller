/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import manipuladatos.MDClientes;
import modelo.Clientes;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Jesus
 */
@Named(value = "aDClientes")
@SessionScoped
public class ADClientes implements Serializable {

    @EJB
    private MDClientes mDClientes;

    private Clientes cliente = new Clientes();
    private List<Clientes> clientes = null;
    private String NombreC;
    private List<Clientes> clientesFiltrados; // Lista temporal para almacenar clientes filtrados
    private boolean skip;

    /**
     * Creates a new instance of ADClientes
     */
    public ADClientes() {
        cliente = new Clientes();
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setClientes(Clientes cliente) {
        this.cliente = cliente;
    }

    public List<Clientes> getClientes() {
        if (clientes == null) {
            clientes = mDClientes.obtenerCliente();
            System.out.println("Clientes cargados: " + clientes.size());  // Verifica en la consola que se estén cargando los clientes
        }
        return clientes;
    }

    public MDClientes getmDClientes() {
        return mDClientes;
    }

    public void setmDClientes(MDClientes mDClientes) {
        this.mDClientes = mDClientes;
    }

    public String getNombreC() {
        return NombreC;
    }

    public void setNombreC(String NombreC) {
        this.NombreC = NombreC;
    }

    public String agregarCliente() {
        try {
            mDClientes.insertarCliente(cliente);
            clientes = mDClientes.obtenerCliente();
            cliente = new Clientes();
            return "cliente?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPage";
        }
    }

    public String eliminarCliente(Clientes cl) {
        mDClientes.eliminarCliente(cl.getId());
        clientes = mDClientes.obtenerCliente();
        return "cliente?faces-redirect=true";
    }

    public List<String> completeText(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Clientes> clients = mDClientes.obtenerCliente();
        clientesFiltrados = clients.stream()
                .filter(cliente -> cliente.getNombre().toLowerCase().startsWith(queryLowerCase))
                .collect(Collectors.toList());
        return clientesFiltrados.stream()
                .map(Clientes::getNombre)
                .collect(Collectors.toList());
    }

    public void onClienteSelect() {
        if (NombreC != null && !NombreC.isEmpty() && clientesFiltrados != null) {
            cliente = clientesFiltrados.stream()
                    .filter(c -> c.getNombre().equalsIgnoreCase(NombreC))
                    .findFirst()
                    .orElse(null);
        } else {
            cliente = new Clientes();
        }
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public Clientes getClienteId(int idCliente) {
        return mDClientes.obtenerClientePorId(idCliente);
    }
}
