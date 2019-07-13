package com.pifrans.project.bean.views;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pifrans.project.bean.general.BeanManagerViewAbstract;

@Controller
@Scope(value = "session")
@ManagedBean(name = "entityBeanView")
public class EntityBeanView extends BeanManagerViewAbstract {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ContextBean contextBean;

	public String getUserLoggedIn() {
		return contextBean.getAuthentication().getName();
	}

	public Date getLastAccess() throws Exception {
		return contextBean.geEntityLogged().getEnt_lastAccess();
	}
}
