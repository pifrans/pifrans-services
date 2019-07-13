package com.pifrans.repository.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryLogin extends Serializable {
	boolean isAuthentic(String user, String password) throws Exception;
}
