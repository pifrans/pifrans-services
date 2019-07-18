package com.pifrans.project.util.all;

public enum StatusPersistence {
	ERROR("Erro"), SUCCESS("Sucesso"), SUCCESSFUL_OPERATION("Operação realizada com sucesso!"), REFERENCED_OBJECT("Esse objeto não pode ser excluído pois possui referência!");

	private String name;

	private StatusPersistence(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
