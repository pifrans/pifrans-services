package com.pifrans.project.bean.views;

import java.util.ArrayList;
import java.util.List;

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
	private String url = "/cadastre/cadastre-city.jsf?faces-redirect=true";
	private List<City> cities = new ArrayList<City>();

	@Autowired
	private CityController cityController;

	private City selectedObject = new City();

	@Override
	public String save() throws Exception {
		selectedObject = cityController.merge(selectedObject);
		return "";
	}

	@Override
	public String create() throws Exception {
		selectedObject = new City();
		return url;
	}
	
	@Override
	public String edit() throws Exception {
		return url;
	}
	
	@Override
	public void delete() throws Exception {
		cityController.delete(selectedObject);
		create();
	}

	public City getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(City selectedObject) {
		this.selectedObject = selectedObject;
	}

	public List<City> getCities() throws Exception {
		cities = cityController.findList(City.class);
		return cities;
	}

}
