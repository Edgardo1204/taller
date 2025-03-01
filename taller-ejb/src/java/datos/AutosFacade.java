/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Autos;
import modelo.Clientes;

/**
 *
 * @author Jesus
 */
@Stateless
public class AutosFacade extends AbstractFacade<Autos> {

    @PersistenceContext(unitName = "taller-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutosFacade() {
        super(Autos.class);
    }

    public List<Autos> getAutosByIdCliente(Clientes c) {

        try {
            Query consultaup = em.createNamedQuery("Autos.findByIdCliente");
            consultaup.setParameter("idCliente", c);
            List<Autos> autos = consultaup.getResultList();

            return autos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
