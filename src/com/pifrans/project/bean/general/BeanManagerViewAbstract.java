package com.pifrans.project.bean.general;

import org.springframework.stereotype.Component;

import com.pifrans.framework.interfaces.crud.CrudInterface;
import com.pifrans.project.report.util.BeanReportView;

@Component
public abstract class BeanManagerViewAbstract extends BeanReportView{
	private static final long serialVersionUID = 1L;
	
	protected abstract Class<?> getClassImplementation();
	
	protected abstract CrudInterface<?> getController();
	
}
