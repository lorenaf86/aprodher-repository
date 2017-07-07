/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprodher.bean;

import com.aprodher.entity.Academia;
import com.aprodher.entity.Concurso;
import com.aprodher.entity.ConcursoAcademia;
import com.aprodher.entity.ConcursoAcademiaCoreo;
import com.aprodher.entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lorena Franco
 */
@Stateless
public class ConcursoAcademiaFacade extends AbstractFacade<ConcursoAcademia> {

    @PersistenceContext(unitName = "com.aprodher_aprodherWeb_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConcursoAcademiaFacade() {
        super(ConcursoAcademia.class);
    }
    
    public Academia findAcademia(Long idUsuario){
       
       Integer id = ((Usuario)em.find(Usuario.class, idUsuario)).getIdAcademia();
        
       return em.find(Academia.class, id);
    }
    
    public List<ConcursoAcademia> findAllInscripciones(Integer idAcademia) {
        return getEntityManager().createQuery("Select c from ConcursoAcademiaCoreo c "
                + " Where c.id = " + idAcademia).getResultList();
    }
    
    public Concurso findConcursoVigente(){
        List list = getEntityManager().createQuery("Select c from Concurso c "
                + " Where c.vigente = true Order by c.fecha desc ").getResultList();
        
        if (list.size() > 0){
                return (Concurso) list.get(0);
        }else
            return null;
        
    }
}
