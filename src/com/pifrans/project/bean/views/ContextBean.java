package com.pifrans.project.bean.views;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.pifrans.project.general.controllers.EntityController;
import com.pifrans.project.general.controllers.SessionController;
import com.pifrans.project.model.classes.Entity;

@Scope(value = "session")
@Component(value = "contextBean")
public class ContextBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String USER_LOGGED_IN = "userLoggedIn";
	@Autowired
	private EntityController entityController;
	@Autowired
	private SessionController sessionController;

	/**
	 * Retorna todos as informações do usuário logado
	 * 
	 * @return {@link Authentication}
	 */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public Entity geEntityLogged() throws Exception {
		Entity entity = (Entity) geExternalContext().getSessionMap().get(USER_LOGGED_IN);

		if (entity == null || (entity != null && !entity.getEnt_user().equals(getUserMain()))) {
			if (getAuthentication().isAuthenticated()) {
				entityController.updateLastAccessUser(getAuthentication().getName());
				entity = entityController.findUserLogged(getAuthentication().getName());
				geExternalContext().getSessionMap().put(USER_LOGGED_IN, entity);
				sessionController.addSession(entity.getEnt_user(), (HttpSession) geExternalContext().getSession(true));
			}
		}
		return entity;
	}

	public String getUserMain() {
		return geExternalContext().getUserPrincipal().getName();
	}

	public ExternalContext geExternalContext() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext;
	}

}
