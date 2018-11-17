package com.multibrand.dao.jdbc.sp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import oracle.jdbc.internal.OracleTypes;

/**
 * Annotation represents procedures IN parameter.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProcedureInParameter {

	/**
	 * @return Name of the IN parameter.
	 */
	String name();

	/**
	 * @return Type of the IN parameter. Defaults to VARCHAR if, not specified.
	 */
	int type() default OracleTypes.VARCHAR;

	/**
	 * @return Index of this IN parameter in the procedure call.
	 */
	int parameterIndex();
}
