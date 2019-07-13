package com.pifrans.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pifrans.repository.interfaces.RepositoryLogin;
import com.pifrans.services.interfaces.ServiceLogin;

@Service
public class ServiceLoginImplementation implements ServiceLogin {
	private static final long serialVersionUID = 1L;
	@Autowired
	private RepositoryLogin repositoryLogin;

	@Override
	public boolean isAuthentic(String user, String password) throws Exception {
		return repositoryLogin.isAuthentic(user, password);
	}
}
