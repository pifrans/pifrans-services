package com.pifrans.project.util.all;

import java.io.Serializable;

import javax.annotation.PostConstruct;

public interface ActionViewStandard extends Serializable {

	abstract void clearList() throws Exception;

	abstract String save() throws Exception;

	abstract void saveNotReturn() throws Exception;

	abstract void saveEdit() throws Exception;

	abstract void delete() throws Exception;

	abstract String enable() throws Exception;

	/**
	 * @PostConstruct realiza inicialização de métodos, valores ou variáveis.
	 * @return
	 * @throws Exception
	 */
	@PostConstruct
	abstract String create() throws Exception;

	abstract String edit() throws Exception;

	abstract void setNullVariables() throws Exception;

	abstract void queryEntity() throws Exception;

	abstract void statusOperation(StatusPersistence statusPersistence) throws Exception;

	abstract String redirectNewEntity() throws Exception;

	abstract String redirectFindEntity() throws Exception;

	abstract void addMessage(String message) throws Exception;

}
