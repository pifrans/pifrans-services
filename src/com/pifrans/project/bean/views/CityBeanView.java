package com.pifrans.project.bean.views;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pifrans.framework.interfaces.crud.CrudInterface;
import com.pifrans.project.bean.general.BeanManagerViewAbstract;
import com.pifrans.project.general.controllers.CityController;
import com.pifrans.project.model.classes.City;

@Controller
@Scope(value = "session")
@ManagedBean(name = "cityBeanView")
public class CityBeanView extends BeanManagerViewAbstract {
	private static final long serialVersionUID = 1L;
	private String urlCadastreCity = "/cadastre/cadastre-city.jsf?faces-redirect=true";
	private String urlSearchCity = "/cadastre/search-city.jsf?faces-redirect=true";
	private List<City> cities = new ArrayList<City>();

	@Autowired
	private CityController cityController;

	private City selectedObject = new City();

	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	public String save() throws Exception {
		selectedObject = cityController.merge(selectedObject);
		return "";
	}

	@Override
	public void saveNotReturn() throws Exception {
		cities.clear();
		selectedObject = cityController.merge(selectedObject);
		cities.add(selectedObject);
		selectedObject = new City();
		successfulOperation();
	}

	@Override
	public String create() throws Exception {
		setNullVariables();
		return urlCadastreCity;
	}

	@Override
	public void setNullVariables() throws Exception {
		cities.clear();
		selectedObject = new City();
	}

	@Override
	public String edit() throws Exception {
		cities.clear();
		return urlCadastreCity;
	}

	@Override
	public void delete() throws Exception {
		selectedObject = (City) cityController.getSession().get(getClassImplementation(), selectedObject.getCit_id());
		cityController.delete(selectedObject);
		cities.remove(selectedObject);
		selectedObject = new City();
		successfulOperation();
	}

	public City getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(City selectedObject) {
		this.selectedObject = selectedObject;
	}

	public List<City> getCities() throws Exception {
		cities = cityController.findList(getClassImplementation());
		return cities;
	}

	@Override
	protected Class<City> getClassImplementation() {
		return City.class;
	}

	@Override
	public String redirectFindEntity() throws Exception {
		setNullVariables();
		return urlSearchCity;
	}

	@Override
	protected CrudInterface<City> getController() {
		return cityController;
	}

}
