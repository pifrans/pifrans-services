package com.pifrans.services.implementations;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pifrans.repository.interfaces.RepositoryCity;
import com.pifrans.services.interfaces.ServiceCity;

@Service
public class ServiceCityImplementation implements ServiceCity {
	private static final long serialVersionUID = 1L;

	@Resource
	private RepositoryCity repositoryCity;
}
