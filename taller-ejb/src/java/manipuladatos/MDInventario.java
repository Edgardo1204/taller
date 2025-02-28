/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import datos.InventarioFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Inventario;

/**
 *
 * @author Jesus
 */
@Stateless
@LocalBean
public class MDInventario {

    @EJB
    private InventarioFacade inventarioF;


    public void insertarInventario(Inventario m) {
        inventarioF.create(m);
    }
 
    public Inventario obtenerInventarioPorId(int id) {
        return inventarioF.find(id);
    }
 
    public List<Inventario> obtenerInventario() {
        return inventarioF.findAll();
    }
 
    public void actualizarInventario(Inventario m) {
        inventarioF.edit(m);
    }

    public void eliminarInventario(int id) {
        Inventario inv = inventarioF.find(id);
        if (inv != null) {
            inventarioF.remove(inv);
            
        }
    }    
}
