package com.pifrans.project.general.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pifrans.framework.implementations.crud.CrudImplementation;
import com.pifrans.framework.interfaces.crud.CrudInterface;
import com.pifrans.project.model.classes.Entity;
import com.pifrans.services.interfaces.ServiceEntity;

@Controller
public class EntityController extends CrudImplementation<Entity> implements CrudInterface<Entity> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ServiceEntity serviceEntity;

	public Entity findUserLogged(String userLogged) throws Exception {
		return super.findUniqueByProperty(Entity.class, userLogged, "ent_user", " AND entity.ent_inactive is false");
	}

	public Date getLastAccessEntityLogged(String userLogged) {
		return serviceEntity.getLastAccessEntityLogged(userLogged);
	}

	public void updateLastAccessUser(String name) {
		serviceEntity.updateLastAccessUser(name);
	}
}
