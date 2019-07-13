package com.pifrans.services.implementations;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pifrans.repository.interfaces.RepositoryEntity;
import com.pifrans.services.interfaces.ServiceEntity;

@Service
public class ServiceEntityImplementation implements ServiceEntity {
	private static final long serialVersionUID = 1L;
	@Autowired
	private RepositoryEntity repositoryEntity;

	@Override
	public Date getLastAccessEntityLogged(String name) {
		return repositoryEntity.getLastAccessEntityLogged(name);
	}

	@Override
	public void updateLastAccessUser(String name) {
		repositoryEntity.updateLastAccessUser(name);
	}

	@Override
	public boolean userExists(String username) {
		return repositoryEntity.userExists(username);
	}

}
