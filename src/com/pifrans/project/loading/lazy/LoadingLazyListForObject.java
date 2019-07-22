package com.pifrans.project.loading.lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import com.pifrans.framework.controller.crud.Controller;
import com.pifrans.project.listener.ContextLoadListenerServicesUtil;

public class LoadingLazyListForObject<T> extends LazyDataModel<T> {
	private static final long serialVersionUID = 1L;

	private List<T> list = new ArrayList<T>();
	private int totalRegisterQuery = 0;
	private String query = null;
	private Controller controller = (Controller) ContextLoadListenerServicesUtil.getBean(Controller.class);

	@Override
	public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
		try {
			if (query != null && !query.isEmpty()) {
				list = (List<T>) controller.findListByQueryDynamic(query, first, pageSize);
				if (totalRegisterQuery == 0) {
					setRowCount(0);
				} else {
					setRowCount(totalRegisterQuery);
				}
			}
			setPageSize(pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (List<T>) list;
	}

	public void setTotalRegisterQuery(int totalRegisterQuery, String query) {
		this.totalRegisterQuery = totalRegisterQuery;
	}
	
	
}
