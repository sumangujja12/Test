package com.multibrand.dao.jdbc.sp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.commons.lang.StringUtils;

/**
 * Annotation used for defining a database procedure.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Procedure {

	/**
	 * @return Name of the procedure.
	 */
	String value();

	/**
	 * @return An Spring bean configured message source ID from where the
	 *         procedure configurations will be fetched.
	 */
	String procedureMessageSourceId() default StringUtils.EMPTY;
}
