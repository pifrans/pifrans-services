package com.pifrans.project.report.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String getDateCurrentReportName() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	public static String formatDateSQL(Date date) {
		StringBuffer buffer = new StringBuffer();
		DateFormat format = new SimpleDateFormat("yyy-MM-dd");
		buffer.append("'");
		buffer.append(format.format(date));
		buffer.append("'");
		return buffer.toString();
	}

	public static String formatDateSQLSimple(Date date) {
		StringBuffer buffer = new StringBuffer();
		DateFormat format = new SimpleDateFormat("yyy-MM-dd");
		buffer.append(format.format(date));
		return buffer.toString();
	}
}
