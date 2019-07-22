package com.pifrans.project.constants;

public enum ConditionSearch {
	CONTEM("Contém"), 
	INICIA_COM("Inicia com"), 
	TERMINA_COM("Termina com"),
	IGUAL("Igual");
	
	private String condition;
	
	private ConditionSearch(String condition) {
		this.condition = condition;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	@Override
	public String toString() {
		return this.condition;
	}
}
