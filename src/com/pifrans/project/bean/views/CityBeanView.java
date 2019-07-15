package com.pifrans.project.bean.views;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pifrans.project.bean.general.BeanManagerViewAbstract;
import com.pifrans.project.general.controllers.CityController;
import com.pifrans.project.model.classes.City;

@Controller
@Scope(value = "session")
@ManagedBean(name = "cityBeanView")
public class CityBeanView extends BeanManagerViewAbstract {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CityController cityController;

	private City selectedObject = new City();
	
	@Override
	public String save() throws Exception {
		System.out.println(selectedObject.getCid_description());
		return "";
	}

	public City getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(City selectedObject) {
		this.selectedObject = selectedObject;
	}
}
