package com.pifrans.repository.interfaces;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RepositoryEntity extends Serializable{

	Date getLastAccessEntityLogged(String name);

	void updateLastAccessUser(String name);

	boolean userExists(String username);
}
