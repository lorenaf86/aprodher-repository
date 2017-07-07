/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprodher.bean;

import com.aprodher.entity.TipoParticipacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lorena Franco
 */
@Stateless
public class TipoParticipacionFacade extends AbstractFacade<TipoParticipacion> {

    @PersistenceContext(unitName = "com.aprodher_aprodherWeb_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoParticipacionFacade() {
        super(TipoParticipacion.class);
    }
    
    public List<TipoParticipacion> findAllActives() {
        return em.createQuery("Select c from  TipoParticipacion c Where c.estado = 'AC'").getResultList();
    }

    
}
