package com.pifrans.project.bean.general;

import java.io.Serializable;
import java.util.Comparator;

public class ObjectQueryField implements Serializable, Comparator<ObjectQueryField> {
	private static final long serialVersionUID = 1L;
	private String description;
	private String databaseField;
	private Object typeClass;
	private Integer main;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDatabaseField() {
		return databaseField;
	}

	public void setDatabaseField(String databaseField) {
		this.databaseField = databaseField;
	}

	public Object getTypeClass() {
		return typeClass;
	}

	public void setTypeClass(Object typeClass) {
		this.typeClass = typeClass;
	}

	public Integer getMain() {
		return main;
	}

	public void setMain(Integer main) {
		this.main = main;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((databaseField == null) ? 0 : databaseField.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjectQueryField other = (ObjectQueryField) obj;
		if (databaseField == null) {
			if (other.databaseField != null)
				return false;
		} else if (!databaseField.equals(other.databaseField))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getDescription();
	}

	@Override
	public int compare(ObjectQueryField o1, ObjectQueryField o2) {
		return ((ObjectQueryField) o1).getMain().compareTo(((ObjectQueryField) o2).getMain());
	}

}
