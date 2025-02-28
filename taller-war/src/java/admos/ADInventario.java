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

/**
 *
 * @author Jesus
 */
@Named(value = "aDInventaro")
@SessionScoped
public class ADInventario implements Serializable {

    @EJB
    private MDInventario mDInventario;

    private Inventario inventario = new Inventario();
    private List<Inventario> inv = null;
    private String mensajeRegistro;

    /**
     * Creates a new instance of ADInventaro
     */
    public ADInventario() {
        inventario = new Inventario();
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public List<Inventario> getInventarios() {
        if (inv == null) {
            inv = mDInventario.obtenerInventario();
        }
        return inv;
    }

    public String agregarInventario() {
        try {
            mensajeRegistro = "Producto o servicio registrado con éxito.";
            mDInventario.insertarInventario(inventario);
            inv = mDInventario.obtenerInventario();
            inventario = new Inventario();
            return "inventarios?faces-redirect=true";
        } catch (Exception e) {
            mensajeRegistro = "Error al registrar el producto o servicio.";
            return "inventarios?faces-redirect=true";
        }
    }

    public String eliminarInventario(Inventario inve) {
        mDInventario.eliminarInventario(inve.getId());
        inv = mDInventario.obtenerInventario();
        return "inventarios?faces-redirect=true";
    }

    public void actualizarCampos() {
        // Lógica adicional si es necesaria
        System.out.println("Categoría seleccionada: " + inventario.getCategoria());
    }

    public String getMensajeRegistro() {
        return mensajeRegistro;
    }

    public void setMensajeRegistro(String mensajeRegistro) {
        this.mensajeRegistro = mensajeRegistro;
    }

    public List<Inventario> getServicios() {
        if (inv == null) {
            inv = mDInventario.obtenerInventario();
        }
        return inv.stream()
                .filter(i -> "Servicio".equalsIgnoreCase(i.getCategoria()))
                .toList();
    }

}
