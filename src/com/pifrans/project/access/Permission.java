package com.pifrans.project.access;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum Permission {
	ADMIN("ADMIN", "Administrador"), USER("USER", "Usuário padrão"),

	REGISTER_ACCESS("REGISTER_ACCESS", "Acessar cadastro"),

	FINANCIAL_ACCESS("FINANCIAL_ACCESS", "Acessar financeiro"),

	MESSAGE_ACCESS("MESSAGE_ACCESS", "Acessar mensagem"),

	NEIGHBORHOOD_ACCESS("NEIGHBORHOOD_ACCESS", "Acessar bairro"), 
	NEIGHBORHOOD_NEW("NEIGHBORHOOD_NEW", "Novo bairro"),
	NEIGHBORHOOD_EDIT("NEIGHBORHOOD_EDIT", "Editar bairro"),
	NEIGHBORHOOD_DELETE("NEIGHBORHOOD_DELETE", "Excluir bairro");

	private String value = "";
	private String description = "";

	private Permission() {
	}

	private Permission(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return getValue();
	}

	public static List<Permission> getListPermissions() {
		List<Permission> permissions = new ArrayList<Permission>();
		for (Permission permission : Permission.values()) {
			permissions.add(permission);
		}
		Collections.sort(permissions, new Comparator<Permission>() {
			@Override
			public int compare(Permission o1, Permission o2) {
				return new Integer(o1.ordinal()).compareTo(new Integer(o2.ordinal()));
			}
		});

		return permissions;
	}
}
