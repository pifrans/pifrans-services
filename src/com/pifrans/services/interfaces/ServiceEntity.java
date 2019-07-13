package com.pifrans.services.interfaces;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public interface ServiceEntity extends Serializable {

	Date getLastAccessEntityLogged(String name);

	void updateLastAccessUser(String name);

	boolean userExists(String username);
}
