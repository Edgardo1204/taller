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
import manipuladatos.MDInventario;
import modelo.Inventario;

@Named(value = "aDInventario")
@SessionScoped
public class ADInventario implements Serializable {

    @EJB
    private MDInventario mDInventario;

    private Inventario inventario = new Inventario();
    private List<Inventario> inventarios;
    private String mensajeRegistro;

    /**
     * Constructor por defecto.
     */
    public ADInventario() {
        inventario = new Inventario();
    }

    // Getters y Setters
    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public List<Inventario> getInventarios() {
        if (inventarios == null) {
            inventarios = mDInventario.obtenerInventario();
        }
        return inventarios;
    }

    public String getMensajeRegistro() {
        return mensajeRegistro;
    }

    public void setMensajeRegistro(String mensajeRegistro) {
        this.mensajeRegistro = mensajeRegistro;
    }

    // Métodos de negocio
    public String agregarInventario() {
        try {
            mDInventario.insertarInventario(inventario);
            inventarios = mDInventario.obtenerInventario();
            inventario = new Inventario();
            mensajeRegistro = "Producto o servicio registrado con éxito.";
            return "inventarios?faces-redirect=true";
        } catch (Exception e) {
            mensajeRegistro = "Error al registrar el producto o servicio.";
            return "inventarios?faces-redirect=true";
        }
    }

    public String eliminarInventario(Inventario inventario) {
        mDInventario.eliminarInventario(inventario.getId());
        inventarios = mDInventario.obtenerInventario();
        return "inventarios?faces-redirect=true";
    }

    public void actualizarCampos() {
        // Lógica adicional si es necesaria
        System.out.println("Categoría seleccionada: " + inventario.getCategoria());
    }

    public List<Inventario> getServicios() {
        if (inventarios == null) {
            inventarios = mDInventario.obtenerInventario();
        }
        return inventarios.stream()
                .filter(i -> "Servicio".equalsIgnoreCase(i.getTipo()))
                .toList();
    }
}
