package com.pifrans.project.listener;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@ApplicationScoped
public class ContextLoadListenerServicesUtil extends ContextLoaderListener implements Serializable {
	private static final long serialVersionUID = 1L;

	private static WebApplicationContext getWebApplicationContext() {
		return WebApplicationContextUtils
				.getWebApplicationContext(getCurrentWebApplicationContext().getServletContext());
	}

	public static Object getBean(String idNameBean) {
		return getWebApplicationContext().getBean(idNameBean);
	}

	public static Object getBean(Class<?> classReceived) {
		return getWebApplicationContext().getBean(classReceived);
	}
}
