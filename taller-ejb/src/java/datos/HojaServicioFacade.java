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
import modelo.Clientes;
import modelo.HojaServicio;

/**
 *
 * @author Jesus
 */
@Stateless
public class HojaServicioFacade extends AbstractFacade<HojaServicio> {

    @PersistenceContext(unitName = "taller-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HojaServicioFacade() {
        super(HojaServicio.class);
    }

    public List<HojaServicio> getHojaServicioByIdCliente(Clientes p) {

        try {
            Query consultaup = em.createNamedQuery("HojaServicio.findByIdCliente");
            consultaup.setParameter("idCliente", p);
            List<HojaServicio> hojas = consultaup.getResultList();

            return hojas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
