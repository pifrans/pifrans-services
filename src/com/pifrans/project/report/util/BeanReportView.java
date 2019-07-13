package com.pifrans.project.report.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

import com.pifrans.project.util.all.BeanViewAbstract;

@Component
public abstract class BeanReportView extends BeanViewAbstract {
	private static final long serialVersionUID = 1L;

	protected StreamedContent fileReport;
	protected int typeReport;
	protected List<?> listDataBeanCollectionReport;
	protected HashMap<Object, Object> parametersReport;
	protected String nameReportIn = "default";
	protected String nameReportOut = "default";
	@Resource
	private ReportUtil reportUtil;

	@SuppressWarnings("rawtypes")
	public BeanReportView() {
		parametersReport = new HashMap<Object, Object>();
		listDataBeanCollectionReport = new ArrayList();
	}

	public ReportUtil getReportUtil() {
		return reportUtil;
	}

	public void setReportUtil(ReportUtil reportUtil) {
		this.reportUtil = reportUtil;
	}

	public int getTypeReport() {
		return typeReport;
	}

	public void setTypeReport(int typeReport) {
		this.typeReport = typeReport;
	}

	public List<?> getListDataBeanCollectionReport() {
		return listDataBeanCollectionReport;
	}

	public void setListDataBeanCollectionReport(List<?> listDataBeanCollectionReport) {
		this.listDataBeanCollectionReport = listDataBeanCollectionReport;
	}

	public HashMap<Object, Object> getParametersReport() {
		return parametersReport;
	}

	public void setParametersReport(HashMap<Object, Object> parametersReport) {
		this.parametersReport = parametersReport;
	}

	public String getNameReportIn() {
		return nameReportIn;
	}

	public void setNameReportIn(String nameReportIn) {
		if (nameReportIn == null || nameReportIn.isEmpty()) {
			nameReportIn = "default";
		}
		this.nameReportIn = nameReportIn;
	}

	public String getNameReportOut() {
		return nameReportOut;
	}

	public void setNameReportOut(String nameReportOut) {
		if (nameReportOut == null || nameReportOut.isEmpty()) {
			nameReportOut = "default";
		}
		this.nameReportOut = nameReportOut;
	}

	public StreamedContent getFileReport() throws Exception {
		return getReportUtil().generateReport(getListDataBeanCollectionReport(), getParametersReport(),
				getNameReportIn(), getNameReportOut(), getTypeReport());
	}

}
