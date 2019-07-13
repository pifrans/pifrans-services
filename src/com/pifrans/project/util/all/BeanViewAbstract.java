package com.pifrans.project.util.all;

import org.springframework.stereotype.Component;

@Component
public abstract class BeanViewAbstract implements ActionViewStandard {
	private static final long serialVersionUID = 1L;

	@Override
	public void clearList() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNotReturn() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveEdit() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String enable() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String create() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String edit() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNullVariables() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void queryEntity() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void statusOperation(StatusPersistence statusPersistence) throws Exception {
		Message.responseOperation(statusPersistence);
	}

	@Override
	public String redirectNewEntity() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String redirectFindEntity() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMessage(String message) throws Exception {
		Message.message(message);
	}

	protected void success() throws Exception {
		statusOperation(StatusPersistence.SUCCESS);
	}

	protected void error() throws Exception {
		statusOperation(StatusPersistence.ERROR);
	}

}
