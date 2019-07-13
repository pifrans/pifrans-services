package com.pifrans.services.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@Service
public interface ServiceLogin extends Serializable{
	boolean isAuthentic(String user, String password) throws Exception;
}
