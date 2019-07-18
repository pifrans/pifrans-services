package com.pifrans.project.bean.views;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pifrans.framework.interfaces.crud.CrudInterface;
import com.pifrans.project.bean.general.BeanManagerViewAbstract;
import com.pifrans.project.general.controllers.EntityController;
import com.pifrans.project.model.classes.Entity;

@Controller
@Scope(value = "session")
@ManagedBean(name = "entityBeanView")
public class EntityBeanView extends BeanManagerViewAbstract {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ContextBean contextBean;
	@Autowired
	private EntityController entityController;

	public String getUserLoggedIn() {
		return contextBean.getAuthentication().getName();
	}

	public Date getLastAccess() throws Exception {
		return contextBean.geEntityLogged().getEnt_lastAccess();
	}

	@Override
	protected Class<Entity> getClassImplementation() {
		return Entity.class;
	}

	@Override
	protected CrudInterface<Entity> getController() {
		return entityController;
	}
}
