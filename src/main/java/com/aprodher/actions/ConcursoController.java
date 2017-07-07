package com.aprodher.actions;

import com.aprodher.actions.util.AbstractController;
import com.aprodher.actions.util.CalendarHelper;
import com.aprodher.actions.util.Credentials;
import com.aprodher.bean.ConcursoFacade;
import com.aprodher.entity.Concurso;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;


@ViewScoped
@ManagedBean
public class ConcursoController extends AbstractController<Concurso> implements Serializable {

    @EJB
    private ConcursoFacade service;
	    
    private List<Concurso>  list;
    
    @Inject
    Credentials credentials;

    public ConcursoController() {
        super(Concurso.class);
    }

    @PostConstruct
    public void init() {
        super.setService(service);
    }
    
    public void confirm(String accion){
        if(accion.equals("new")){
        	this.getSelected().setEstado("AC");
                this.getSelected().setUsuAlta(this.getCredentials().getUsername());
                this.getSelected().setFechaAlta(CalendarHelper.getCurrentTimestamp());
                this.getSelected().setUsuMod(this.getCredentials().getUsername());
                this.getSelected().setFechaMod(CalendarHelper.getCurrentTimestamp());
                this.saveNew(null);
        }else{
                if(accion.equals("edit")){

                        this.getSelected().setUsuMod(this.getCredentials().getUsername());
                        this.getSelected().setFechaMod(CalendarHelper.getCurrentTimestamp());
                        this.save(null);
                }else{
                        this.getSelected().setEstado("IN");
                        this.delete(null);
                }
        }
    }

    public Credentials getCredentials() {
            return credentials;
    }

    public List<Concurso> getList() {
            return list;
    }

    public void setList(List<Concurso> list) {
            this.list = list;
    }

    public ConcursoFacade getService() {
            return service;
    }

    public void setService(ConcursoFacade service) {
            this.service = service;
    }

    @Override
    public List<Concurso> getItems()
    {
        return super.getItems();
    }

}
