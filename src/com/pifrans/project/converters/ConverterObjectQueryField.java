package com.pifrans.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.pifrans.project.bean.general.ObjectQueryField;

@FacesConverter(forClass = ObjectQueryField.class)
public class ConverterObjectQueryField implements Converter, Serializable{
	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		if (value != null && !value.isEmpty()) {
			String[] values = value.split("\\*");
			ObjectQueryField objectQueryField = new ObjectQueryField();
			objectQueryField.setDatabaseField(values[0]);
			objectQueryField.setTypeClass(values[1]);
			return objectQueryField;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
		if (object != null) {
			ObjectQueryField objectQueryField = (ObjectQueryField) object;
			return objectQueryField.getDatabaseField() + "*" + objectQueryField.getTypeClass();
		}
		return null;
	}

}
