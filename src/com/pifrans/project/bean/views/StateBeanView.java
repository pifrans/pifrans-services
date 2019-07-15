package com.pifrans.project.bean.views;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pifrans.project.general.controllers.StateController;
import com.pifrans.project.util.all.BeanViewAbstract;

@Controller
@Scope(value = "session")
@ManagedBean(name = "stateBeanView")
public class StateBeanView extends BeanViewAbstract {
	private static final long serialVersionUID = 1L;

	@Autowired
	private StateController stateController;

	public List<SelectItem> getStates() throws Exception {
		return stateController.getListState();
	}
}
