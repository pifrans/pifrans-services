package com.pifrans.dao.implementations;

import org.springframework.stereotype.Repository;

import com.pifrans.framework.implementations.crud.CrudImplementation;
import com.pifrans.project.model.classes.City;
import com.pifrans.repository.interfaces.RepositoryCity;

@Repository
public class DaoCity extends CrudImplementation<City> implements RepositoryCity{
	private static final long serialVersionUID = 1L;

}
