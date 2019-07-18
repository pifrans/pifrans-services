package com.pifrans.project.bean.views;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pifrans.framework.interfaces.crud.CrudInterface;
import com.pifrans.project.bean.general.BeanManagerViewAbstract;

@Controller
@Scope(value = "session")
@ManagedBean(name = "messageBeanView")
public class MessageBeanView extends BeanManagerViewAbstract {
	private static final long serialVersionUID = 1L;

	@Override
	public String create() throws Exception {
		System.out.println("Criar");
		return "";
	}

	@Override
	protected Class<?> getClassImplementation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CrudInterface<?> getController() {
		// TODO Auto-generated method stub
		return null;
	}
}
