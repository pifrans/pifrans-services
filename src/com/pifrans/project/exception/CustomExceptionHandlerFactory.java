package com.pifrans.project.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {
	private ExceptionHandlerFactory exceptionHandlerFactory;

	public CustomExceptionHandlerFactory(ExceptionHandlerFactory exceptionHandlerFactory) {
		this.exceptionHandlerFactory = exceptionHandlerFactory;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler exceptionHandler = new CustomExceptionHandler(exceptionHandlerFactory.getExceptionHandler());
		return exceptionHandler;
	}

}
