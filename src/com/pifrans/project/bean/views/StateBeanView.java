package com.pifrans.project.bean.views;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pifrans.framework.interfaces.crud.CrudInterface;
import com.pifrans.project.bean.general.BeanManagerViewAbstract;
import com.pifrans.project.general.controllers.StateController;
import com.pifrans.project.model.classes.State;

@Controller
@Scope(value = "session")
@ManagedBean(name = "stateBeanView")
public class StateBeanView extends BeanManagerViewAbstract {
	private static final long serialVersionUID = 1L;

	@Autowired
	private StateController stateController;

	public List<SelectItem> getStates() throws Exception {
		return stateController.getListState();
	}

	@Override
	protected Class<State> getClassImplementation() {
		return State.class;
	}

	@Override
	protected CrudInterface<State> getController() {
		return stateController;
	}
}
