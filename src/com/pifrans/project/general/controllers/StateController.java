package com.pifrans.project.general.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;

import com.pifrans.framework.implementations.crud.CrudImplementation;
import com.pifrans.framework.interfaces.crud.CrudInterface;
import com.pifrans.project.model.classes.State;

@Controller
public class StateController extends CrudImplementation<State> implements CrudInterface<State> {
	private static final long serialVersionUID = 1L;

	public List<SelectItem> getListState() throws Exception {
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		List<State> states = super.findListByQueryDynamic("FROM state");

		for (State state : states) {
			selectItems.add(new SelectItem(state, state.getSta_name()));
		}
		return selectItems;
	}
}
