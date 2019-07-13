package com.pifrans.project.general.controllers;

import java.util.HashMap;

import javax.faces.bean.ApplicationScoped;
import javax.servlet.http.HttpSession;

@ApplicationScoped
public class SessionControllerImplementation implements SessionController {
	private static final long serialVersionUID = 1L;
	private HashMap<String, HttpSession> hashMap = new HashMap<String, HttpSession>();

	@Override
	public void addSession(String keyLoginUser, HttpSession httpSession) {
		hashMap.put(keyLoginUser, httpSession);

	}

	@Override
	public void invalidateSession(String keyLoginUser) {
		HttpSession httpSession = hashMap.get(keyLoginUser);

		if (httpSession != null) { // Remove a sessão do usuário passado por parâmetros
			try {
				httpSession.invalidate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Não tem usuário!");
		}
		hashMap.remove(keyLoginUser);
	}

}
