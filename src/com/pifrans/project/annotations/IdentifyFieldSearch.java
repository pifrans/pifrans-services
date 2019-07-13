package com.pifrans.project.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public abstract @interface IdentifyFieldSearch {
	String fieldDescription(); // Descrição do campo para a tela

	String fieldSearch(); // Campo do banco

	int fieldMain() default 10000; // Posição que aparece primeiro
}
