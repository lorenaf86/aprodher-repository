/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprodher.bean;

import com.aprodher.entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lorena Franco
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "com.aprodher_aprodherWeb_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Boolean verificaEmail(String mail){
        
        String sql = "Select count(u) from Usuario u where trim(u.mail) = trim('" + mail + "')";
        
        int cant = (int) em.createQuery(sql).getSingleResult();
        
        if (cant == 0) 
            return true; 
        else 
            return false;
    }
    
    public Usuario login(String user, String pass){
        try{
            String sql = "Select u from Usuario u where trim(u.username) = trim('" + user + "')"
                + " and trim(u.contrasena) = trim('"+pass+"')";
              //  + " and (u.id = 1 or u.activo)";
        
            return (Usuario) em.createQuery(sql).getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    
    }
    
    public void increment(Long id){
        Usuario u = em.find(Usuario.class, id);
        u.setContador((u.getContador()== null?1:u.getContador()+1));
        
        em.merge(u);
    }
}
