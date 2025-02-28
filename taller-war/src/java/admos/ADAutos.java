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
    private List<Autos> autos = null;
    private int clienteFK = 0;
    private String autoC;
    private Integer autoId; // ID del auto seleccionado

    /**
     * Creates a new instance of ADClientes
     */
    public ADAutos() {
        auto = new Autos();
    }

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

    public String agregarAuto(ADClientes aDClientes) {
        System.out.println("No entra ni al perro try catch alv ptm");
        try {
            auto.setIdCliente(aDClientes.getClienteId(clienteFK));
            mDAutos.insertarAuto(auto);
            autos = mDAutos.obtenerAutos();
            auto = new Autos();
            clienteFK = 0;
            System.out.println("No vale verga");
            return "auto?faces-redirect=true";
        } catch (Exception e) {
            System.out.println("No se que verga pero nomas entra aqui alv ptm madre");
            e.printStackTrace();
            return "errorPage";
        }
    }

    public String eliminarAuto(Autos cl) {
        mDAutos.eliminarAuto(cl.getId());
        autos = mDAutos.obtenerAutos();
        return "auto?faces-redirect=true";
    }

    public void registroAuto() {
        mDAutos.insertarAuto(auto);

    }

    public MDAutos getmDAutos() {
        return mDAutos;
    }

    public void setmDAutos(MDAutos mDAutos) {
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

    public List<Autos> obtenerAutosPorClienteId(int clienteId) {
        System.out.println("Buscando autos para cliente ID: " + clienteId);
        List<Autos> todosLosAutos = mDAutos.obtenerAutos();
        List<Autos> filtrados = filtrarAutosPorClienteId(todosLosAutos, clienteId);
        System.out.println("Autos encontrados: " + filtrados.size());
        return filtrados;
    }

    private List<Autos> filtrarAutosPorClienteId(List<Autos> autos, int clienteId) {
        return autos.stream()
                .filter(auto -> auto.getIdCliente() != null && auto.getIdCliente().getId() == clienteId)
                .collect(Collectors.toList());
    }

    public void onAutoSelect() {
        if (autoId != null) {
            // Busca el auto por su ID
            auto = mDAutos.obtenerAutoPorId(autoId);
            System.out.println("Auto seleccionado: " + auto.getModelo());
        } else {
            // Si no hay ID seleccionado, limpia los campos
            auto = new Autos();
        }
    }

}
