package com.pifrans.project.bean.general;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.stereotype.Component;

import com.pifrans.framework.interfaces.crud.CrudInterface;
import com.pifrans.project.annotations.IdentifyFieldSearch;
import com.pifrans.project.constants.ConditionSearch;
import com.pifrans.project.report.util.BeanReportView;
import com.pifrans.project.util.all.UtilityRegex;

@Component
public abstract class BeanManagerViewAbstract extends BeanReportView {
	private static final long serialVersionUID = 1L;
	public ObjectQueryField objectQueryFieldSelected;
	public List<SelectItem> listFieldSearch;
	public List<SelectItem> listConditionSearch;
	public ConditionSearch conditionSearchSelected;
	public String valueSearch;

	/* Métodos */
	protected abstract Class<?> getClassImplementation();

	protected abstract CrudInterface<?> getController();

	public String getValueSearch() {
		return valueSearch != null ? new UtilityRegex().removeAccents(valueSearch) : "";
	}

	public void setValueSearch(String valueSearch) {
		this.valueSearch = valueSearch;
	}

	public ObjectQueryField getObjectQueryFieldSelected() {
		return objectQueryFieldSelected;
	}
	
	public ConditionSearch getConditionSearchSelected() {
		return conditionSearchSelected;
	}

	public void setConditionSearchSelected(ConditionSearch conditionSearchSelected) {
		this.conditionSearchSelected = conditionSearchSelected;
	}

	public List<SelectItem> getListConditionSearch() {
		listConditionSearch = new ArrayList<SelectItem>();
		for (ConditionSearch conditionSearch: ConditionSearch.values()) {
			listConditionSearch.add(new SelectItem(conditionSearch, conditionSearch.toString()));
		}
		return listConditionSearch;
	}

	public void setObjectQueryFieldSelected(ObjectQueryField objectQueryFieldSelected) {
		if (objectQueryFieldSelected != null) {
			for (Field field: getClassImplementation().getDeclaredFields()) {
				if (field.isAnnotationPresent(IdentifyFieldSearch.class)) {
					if (objectQueryFieldSelected.getDatabaseField().equalsIgnoreCase(field.getName())) {
						String fieldDescription = field.getAnnotation(IdentifyFieldSearch.class).fieldDescription();
						objectQueryFieldSelected.setDescription(fieldDescription);
						objectQueryFieldSelected.setTypeClass(field.getType().getCanonicalName());
						objectQueryFieldSelected.setMain(field.getAnnotation(IdentifyFieldSearch.class).fieldMain());
						break;
					}
				}
			}
		}
		this.objectQueryFieldSelected = objectQueryFieldSelected;
	}

	public List<SelectItem> getListFieldSearch() {
		listFieldSearch = new ArrayList<SelectItem>();
		List<ObjectQueryField> listTemporary = new ArrayList<ObjectQueryField>();

		for (Field field : getClassImplementation().getDeclaredFields()) {
			if (field.isAnnotationPresent(IdentifyFieldSearch.class)) {
				String fieldDescription = field.getAnnotation(IdentifyFieldSearch.class).fieldDescription();
				String fieldSearch = field.getAnnotation(IdentifyFieldSearch.class).fieldSearch();
				int fieldMain = field.getAnnotation(IdentifyFieldSearch.class).fieldMain();

				ObjectQueryField objectQueryField = new ObjectQueryField();
				objectQueryField.setDescription(fieldDescription);
				objectQueryField.setDatabaseField(fieldSearch);
				objectQueryField.setTypeClass(field.getType().getCanonicalName());
				objectQueryField.setMain(fieldMain);
				
				listTemporary.add(objectQueryField);
			}
		}

		reverseOrder(listTemporary);

		for (ObjectQueryField objectQueryField : listTemporary) {
			listFieldSearch.add(new SelectItem(objectQueryField));
		}

		return listFieldSearch;
	}

	private void reverseOrder(List<ObjectQueryField> listTemporary) {
		Collections.sort(listTemporary, new Comparator<ObjectQueryField>() {

			@Override
			public int compare(ObjectQueryField firstObject, ObjectQueryField secondObject) {
				return firstObject.getMain().compareTo(secondObject.getMain());
			}
		});
	}
}
