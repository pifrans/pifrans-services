package com.pifrans.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.pifrans.framework.hibernate.session.HibernateUtil;
import com.pifrans.project.model.classes.State;

@FacesConverter(forClass = State.class)
public class ConverterState implements Converter, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String code) {
		if (code != null && !code.isEmpty()) {
			return (State) HibernateUtil.getCurrentSession().get(State.class, new Long(code));
		}
		return code;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
		if (object != null) {
			State state = (State) object;
			return state.getSta_id() != null && state.getSta_id() > 0 ? state.getSta_id().toString() : null;
		}
		return null;
	}
}
