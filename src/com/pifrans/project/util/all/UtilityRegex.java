package com.pifrans.project.util.all;

public class UtilityRegex {

	public String removeAccents(String string) {
		String aux = new String(string);
		aux = aux.replaceAll("[èéêëÈÉÊË]", "e");
		aux = aux.replaceAll("[ûùüúÛÚÙÜ]", "u");
		aux = aux.replaceAll("[ïîíìÏÎÍÌ]", "i");
		aux = aux.replaceAll("[àâáäãÁÀÂÄ]", "a");
		aux = aux.replaceAll("[óòôöõÓÒÔÖ]", "o");
		return aux;
	}
}
