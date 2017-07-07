/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprodher.actions;

import com.aprodher.actions.util.AppHelper;
import com.aprodher.actions.util.Credentials;
import com.aprodher.actions.util.FailedException;
import com.aprodher.actions.util.GlobalParameters;
import com.aprodher.actions.util.LoginCheckerHelper;
import com.aprodher.entity.Usuario;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author Lorena Franco
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController extends LoginCheckerHelper implements Serializable {
    
    Usuario usuario;

    @Inject
    private Credentials credentials;

    private boolean localLoggedIn = true;

    private String errorMessage = null;
    
    @Inject
    MenuController menuController;
    
    @EJB
    private com.aprodher.bean.UsuarioFacade ejbFacade;

    /**
     * Creates a new instance of loginController
     */
    public LoginController() {
    }
    
    public String register() {
	return "usuario/Create?faces-redirect=true";
    }
    
    public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String email = (String) value;
		if(ejbFacade.verificaEmail(email)){
			FacesContext.getCurrentInstance().addMessage("validaEmail", new FacesMessage("Email já cadastrado"));
			throw new ValidatorException(new FacesMessage("Email já cadastrado"));
		}
    }
    
    public String login()
    {
        errorMessage = null;
        localLoggedIn = true;
        
        credentials.setSessionHash(AppHelper.createNewSession());
        credentials.setIpAddr(AppHelper.getServletRequest().getRemoteAddr());

        if (credentials.getUsername() == null ||
            credentials.getUsername().isEmpty() ||
            credentials.getPassword() == null ||
            credentials.getPassword().isEmpty()
        ) 
        {
            FacesContext.getCurrentInstance().addMessage("Info", new FacesMessage("Error!"));
            credentials.init();
            return null;
        }
        
        try
        {
            usuario = ejbFacade.login(credentials.getUsername(), credentials.getPassword());
            
            if(usuario != null)
            {
                credentials.setPassword(null);
                //credentials.setParameters(service.getCredentials().getParameters());
                //credentials.setRols(service.getCredentials().getRols());
                credentials.setIdEmpleado(usuario.getId());
                                
                ejbFacade.increment(usuario.getId());
                
                MessageUtil.addFacesMessageInfo("logging.welcome", AppHelper.getBundleMessage("logging.welcome") + " " + credentials.getUsername());
                menuController.armarMenu();

                return "loggedIn";
            }else{
                FacesContext.getCurrentInstance().addMessage("Info", new FacesMessage("Error!"));
                credentials.init();
                return null;
            }
        }
        catch (Exception e) //FileNotFoundException
        {
            MessageUtil.addFacesMessageError("Error al iniciar Sesión, verifique sus datos!");
        }        
        credentials.init();
        
        return null;
    }

    public void check(String modulo)
    {
        prindId();
        
        if((!isChecked()) || hashIsDifferent())
        {
            doLoginWithHash(modulo);
        }
        
        System.out.println("LCC-SessionID for IP ["+AppHelper.getClientIpAddr()+"] is " + hash);
        
    }
    
    private void prindId()
    {
        System.out.println(this.getClass().getSimpleName() + "::" + System.identityHashCode(this));
    }

        private void doLoginWithHash(String modulo)
    {
       // logger.info("checking hash " + hash + "...");
        /*
        try
        {
            login.loginWithSessionId(hash, modulo);
            checked = true;
        }
        catch (FileNotFoundException e)
        {
            logger.log(Level.SEVERE, e.getMessage());
            MessageUtil.addFacesMessageError("auth.module.error.FileNotFoundException");
        }
        catch (IOException e)
        {
            logger.log(Level.SEVERE, e.getMessage());
            MessageUtil.addFacesMessageError("auth.module.error.IOException");
        }
        catch (SessionHashNotFoundException e)
        {
            logger.log(Level.SEVERE, SessionHashNotFoundException.class.getSimpleName());
            MessageUtil.addFacesMessageError("auth.module.error.NoSessionHashFound");
        }
        catch (ConfigException e)
        {
            logger.log(Level.SEVERE, e.getMessage());
            MessageUtil.addFacesMessageError("auth.module.error.ConfigException");
        }
        
        */
    }
    
    private String fullInfo()
    {
        return credentials.getUsername() + "::" + credentials.getIpAddr();
    }

    public String logout()
    {
        errorMessage = null;
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(AppHelper.getBundleMessage("logging.info.logout") + " " + credentials.getUsername()));
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.getExternalContext().invalidateSession();
        
        credentials.init();
        invalidate();

        return GlobalParameters.ROOT+GlobalParameters.FACES_REDIRECT;
    }

    @Produces
    @Named
    public boolean isLoggedIn()
    {
        return credentials != null && credentials.getUsername() != null;
    }
    
    @Produces
    @Named
    public boolean isLocalLoggedIn()
    {
        return localLoggedIn;
    }

    public String loginWithSessionId(String sessionId, String modulo) throws FileNotFoundException, IOException, FailedException
    {
        errorMessage = null;
        localLoggedIn = false;
        
/*        LoginService service = new LoginService(sessionId, loginUri, modulo);
        
        if (service.checkhash())
        {
            credentials.setUsername(service.getCredentials().getUsername());
            credentials.setIdEmpleado(service.getCredentials().getIdEmpleado());
            credentials.setParameters(service.getCredentials().getParameters());
            credentials.setRols(service.getCredentials().getRols());
            credentials.setFuncionalidades(service.getCredentials().getFuncionalidades());
            MessageUtil.addFacesMessageInfo("Welcome");
            
            return "loggedIn";
        }
*/
        return null;
    }
    public Usuario getUsuario(){
        return usuario;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

}
