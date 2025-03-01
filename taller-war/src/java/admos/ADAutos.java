/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import manipuladatos.MDAutos;
import modelo.Autos;
import modelo.Clientes;

@Named(value = "aDAutos")
@SessionScoped
public class ADAutos implements Serializable {

    @EJB
    private MDAutos mDAutos;

    private Autos auto = new Autos();
    private List<Autos> autos;
    private int clienteFK;
    private Integer autoId; // ID del auto seleccionado

    /**
     * Constructor por defecto.
     */
    public ADAutos() {
        auto = new Autos();
    }

    // Getters y Setters
    public Autos getAuto() {
        return auto;
    }

    public void setAuto(Autos auto) {
        this.auto = auto;
        if (auto == null) {
            this.auto = new Autos(); // Limpiar los campos
        }
    }

    public List<Autos> getAutos() {
        if (autos == null) {
            autos = mDAutos.obtenerAutos();
        }
        return autos;
    }

    public MDAutos getMDAutos() {
        return mDAutos;
    }

    public void setMDAutos(MDAutos mDAutos) {
        this.mDAutos = mDAutos;
    }

    public int getClienteFK() {
        return clienteFK;
    }

    public void setClienteFK(int clienteFK) {
        this.clienteFK = clienteFK;
    }

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    // Métodos de negocio
    public String agregarAuto(ADClientes aDClientes) {
        try {
            auto.setIdCliente(aDClientes.getClienteId(clienteFK));
            auto.setEstado(true);
            mDAutos.insertarAuto(auto);
            autos = mDAutos.obtenerAutos();
            auto = new Autos();
            clienteFK = 0;
            return "auto?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPage";
        }
    }

    public String agregarAutoDialog(ADClientes aDClientes) {
        try {
            auto.setIdCliente(aDClientes.getClienteId(clienteFK));
            auto.setEstado(true);
            mDAutos.insertarAuto(auto);
            autos = mDAutos.obtenerAutos();
            auto = new Autos();
            clienteFK = 0;
            return "hoja_serv?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPage";
        }
    }

    public String eliminarAuto(Autos auto) {
        mDAutos.eliminarAuto(auto.getId());
        autos = mDAutos.obtenerAutos();
        return "auto?faces-redirect=true";
    }

    public void registroAuto() {
        auto.setEstado(Boolean.TRUE);
        mDAutos.insertarAuto(auto);
    }

    public List<Autos> obtenerAutosPorClienteId(int clienteId) {
        System.out.println("Buscando autos para cliente ID: " + clienteId);
        List<Autos> todosLosAutos = mDAutos.obtenerAutos();
        List<Autos> filtrados = filtrarAutosPorClienteId(todosLosAutos, clienteId);
        System.out.println("Autos encontrados: " + filtrados.size());
        return filtrados;
    }

    private List<Autos> filtrarAutosPorClienteId(List<Autos> autos, int clienteId) {
        return autos.stream()
                .filter(auto -> auto.getIdCliente() != null
                && auto.getIdCliente().getId() == clienteId
                && auto.getEstado() == true) // Filtra solo los autos con estado true
                .collect(Collectors.toList());
    }

    public void onAutoSelect() {
        if (autoId != null) {
            auto = mDAutos.obtenerAutoPorId(autoId);
            System.out.println("Auto seleccionado: " + auto.getModelo());
        } else {
            auto = new Autos();
        }
    }

    public String actualizarEstadoAuto(int autoId, boolean nuevoEstado) {
        try {
            Autos autoActualizar = mDAutos.obtenerAutoPorId(autoId);
            if (autoActualizar != null) {
                autoActualizar.setEstado(nuevoEstado);
                mDAutos.actualizarAuto(autoActualizar);
                autos = mDAutos.obtenerAutos(); // Refrescar la lista de autos
                return "auto?faces-redirect=true"; // Redirigir tras la actualización
            } else {
                System.out.println("No se encontró el auto con ID: " + autoId);
                return "errorPage";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPage";
        }
    }

    public List<Autos> buscarAutosPorCliente(Clientes clienteId) {
        try {
            List<Autos> autosCliente = mDAutos.getAutosByIdClientes(clienteId);
            if (autosCliente.isEmpty()) {
                System.out.println("No se encontraron autos para el cliente con ID: " + clienteId);
            } else {
                System.out.println("Autos encontrados para el cliente ID " + clienteId + ": " + autosCliente.size());
            }
            return autosCliente;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
