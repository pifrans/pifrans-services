package com.pifrans.project.bean.views;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pifrans.framework.interfaces.crud.CrudInterface;
import com.pifrans.project.bean.general.BeanManagerViewAbstract;
import com.pifrans.project.general.controllers.SessionController;
import com.pifrans.services.interfaces.ServiceLogin;

@Controller
@Scope(value = "request")
@ManagedBean(name = "loginBeanView")
public class LoginBeanView extends BeanManagerViewAbstract {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	@Resource
	private SessionController sessionController;
	@Resource
	private ServiceLogin serviceLogin;

	@RequestMapping(value = "**/invalidate_session", method = RequestMethod.POST)
	public void invalidateSession(HttpServletRequest httpServletRequest) throws Exception {
		String userLoggedSession = null;
		if (httpServletRequest.getUserPrincipal() != null) {
			userLoggedSession = httpServletRequest.getUserPrincipal().getName();
		}

		if (userLoggedSession == null || (userLoggedSession != null && userLoggedSession.trim().isEmpty())) {
			userLoggedSession = httpServletRequest.getRemoteUser();
		}

		if (userLoggedSession != null && userLoggedSession.isEmpty()) {
			sessionController.invalidateSession(userLoggedSession);
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void invalidateSession(ActionEvent actionEvent) throws Exception {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		boolean loggedIn = false;

		if (serviceLogin.isAuthentic(getUsername(), getPassword())) {
			sessionController.invalidateSession(getUsername());
			loggedIn = true;
		} else {
			loggedIn = false;
			facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Acesso negado",
					"Usuário ou senha incorretos!");
		}

		if (facesMessage != null) {
			FacesContext.getCurrentInstance().addMessage("grlMessage", facesMessage);
		}

		requestContext.addCallbackParam("loggedIn", loggedIn);
	}

	@Override
	protected Class<?> getClassImplementation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CrudInterface<?> getController() {
		// TODO Auto-generated method stub
		return null;
	}

}
