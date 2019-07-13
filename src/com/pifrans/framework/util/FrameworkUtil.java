package com.pifrans.framework.util;

import java.io.Serializable;

public class FrameworkUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

	public synchronized static ThreadLocal<Long> geThreadLocal() {
		return threadLocal;
	}

}
