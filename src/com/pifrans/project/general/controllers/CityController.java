package com.pifrans.project.general.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.pifrans.framework.implementations.crud.CrudImplementation;
import com.pifrans.framework.interfaces.crud.CrudInterface;
import com.pifrans.project.model.classes.City;
import com.pifrans.repository.interfaces.RepositoryCity;
import com.pifrans.services.interfaces.ServiceCity;

@Controller
public class CityController extends CrudImplementation<City> implements CrudInterface<City> {
	private static final long serialVersionUID = 1L;

	@Resource
	private ServiceCity serviceCity;
	@Resource
	private RepositoryCity repositoryCity;
}
