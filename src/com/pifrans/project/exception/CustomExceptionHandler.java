package com.pifrans.project.exception;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.hibernate.SessionFactory;
import org.primefaces.context.RequestContext;

import com.pifrans.framework.hibernate.session.HibernateUtil;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {
	private ExceptionHandler wrappered;
	final FacesContext facesContext = FacesContext.getCurrentInstance();
	final Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();
	final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();

	public CustomExceptionHandler(ExceptionHandler exceptionHandler) {
		this.wrappered = exceptionHandler;
	}

	/**
	 * Sobrescreve o método getWrapped {@link ExceptionHandlerWrapper} que retorna a
	 * pilha de exeções
	 * 
	 * @return {@link ExceptionHandler}
	 */
	@Override
	public ExceptionHandler getWrapped() {
		return wrappered;
	}

	/**
	 * Sobrescreve o método handle de {@link ExceptionHandlerWrapper} que é
	 * responsável por manipular as exeções do JSF
	 */
	@Override
	public void handle() throws FacesException {
		final Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();
		while (iterator.hasNext()) {
			ExceptionQueuedEvent exceptionQueuedEvent = iterator.next();
			ExceptionQueuedEventContext exceptionQueuedEventContext = (ExceptionQueuedEventContext) exceptionQueuedEvent
					.getSource();

			// Recupera a execeção do contexto
			Throwable throwable = exceptionQueuedEventContext.getException();

			// Aqui trabalhamos a execeção
			try {
				requestMap.put("exceptionMessage", throwable.getMessage());
				if (throwable != null && throwable.getMessage() != null
						&& throwable.getMessage().indexOf("ConstraintViolationException") != -1) {
					FacesContext.getCurrentInstance().addMessage("messsage", new FacesMessage(
							FacesMessage.SEVERITY_WARN, "O registro não pode ser removido pois está associado!", ""));
				} else if (throwable != null && throwable.getMessage() != null
						&& throwable.getMessage().indexOf("org.hibernate.StaleObjectStateException") != -1) {
					FacesContext.getCurrentInstance().addMessage("messsage",
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"O registro foi atualizado ou excluído por outro usuário!" + " Consulte novamente!",
									""));
				} else {
					// Avisa o usuário do erro
					FacesContext.getCurrentInstance().addMessage("messsage", new FacesMessage(
							FacesMessage.SEVERITY_FATAL, "O sistema se recuperou de um erro inesperado!", ""));

					// Avisa o usuário que ele pode cutinuar usando o sistema normalmente
					FacesContext.getCurrentInstance().addMessage("messsage",
							new FacesMessage(FacesMessage.SEVERITY_WARN, "O registro não !", ""));

					// Avisa o usuário detalhes do erro
					FacesContext.getCurrentInstance().addMessage("messsage", new FacesMessage(
							FacesMessage.SEVERITY_FATAL, "O erro foi causado por: \n" + throwable.getMessage(), ""));

					// O Primefaces exibe um alert apenas se a página não redirecionar
					RequestContext.getCurrentInstance()
							.execute("alert('O sistema se recuperou de um erro inesperado!')");

					// O Primefaces exibe um dialog apenas se a página não redirecionar
					RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Erro", "O sistema se recuperou de um erro inesperado!"));

					// Redireciona para a página de erro
					navigationHandler.handleNavigation(facesContext, null,
							"/error/error.jsf?faces-redirect=true&expired=true");
				}
				// Renderiza a página de erro e exibe as mensagens
				facesContext.renderResponse();
			} finally {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
					sessionFactory.getCurrentSession().getTransaction().rollback();
				}
				// Imprime o erro no console
				throwable.printStackTrace();
				iterator.remove();
			}
		}
		getWrapped().handle();
	}

}
