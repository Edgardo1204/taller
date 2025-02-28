/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.DetalleServicio;

/**
 *
 * @author Jesus
 */
@Stateless
public class DetalleServicioFacade extends AbstractFacade<DetalleServicio> {

    @PersistenceContext(unitName = "taller-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleServicioFacade() {
        super(DetalleServicio.class);
    }
    
}
