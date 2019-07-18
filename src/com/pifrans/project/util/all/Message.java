package com.pifrans.project.util.all;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class Message extends FacesContext implements Serializable {
	private static final long serialVersionUID = 1L;

	public Message() {
	}

	public static void responseOperation(StatusPersistence statusPersistence) {
		if (statusPersistence != null && statusPersistence.equals(StatusPersistence.SUCCESS)) {
			success();
		} else if (statusPersistence != null && statusPersistence.equals(StatusPersistence.SUCCESSFUL_OPERATION)) {
			successfulOperation();
		} else if (statusPersistence != null && statusPersistence.equals(StatusPersistence.REFERENCED_OBJECT)) {
			messageSeverityFatal(StatusPersistence.REFERENCED_OBJECT.toString());
		} else {
			errorInOperation();
		}
	}

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	private static boolean isFacesContext() {
		return getFacesContext() != null;
	}

	public static void message(String message) {
		if (isFacesContext()) {
			getFacesContext().addMessage("message", new FacesMessage(message));
		}
	}

	public static void success() {
		if (isFacesContext()) {
			messageSeverityInfo(Constant.SUCCESS);
		}
	}

	public static void successfulOperation() {
		if (isFacesContext()) {
			messageSeverityInfo(Constant.SUCCESSFUL_OPERATION);
		}
	}

	public static void errorInOperation() {
		if (isFacesContext()) {
			messageSeverityFatal(Constant.ERROR_IN_OPERATION);
		}
	}

	public static void messageSeverityWarn(String message) {
		if (isFacesContext()) {
			getFacesContext().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
		}
	}

	public static void messageSeverityFatal(String message) {
		if (isFacesContext()) {
			getFacesContext().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_FATAL, message, message));
		}
	}

	public static void messageSeverityError(String message) {
		if (isFacesContext()) {
			getFacesContext().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
		}
	}

	public static void messageSeverityInfo(String message) {
		if (isFacesContext()) {
			getFacesContext().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
		}
	}
}
