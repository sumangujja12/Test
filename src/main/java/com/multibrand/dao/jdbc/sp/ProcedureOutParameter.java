package com.multibrand.dao.jdbc.sp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import oracle.jdbc.internal.OracleTypes;

import org.springframework.jdbc.core.RowMapper;

/**
 * Annotation represents procedures OUT parameter.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProcedureOutParameter {

	/**
	 * @return Name of the OUT parameter.
	 */
	String name();

	/**
	 * @return Type of the OUT parameter. Defaults to VARCHAR if, not specified.
	 */
	int type() default OracleTypes.VARCHAR;

	/**
	 * @return Index of this OUT parameter in the procedure call.
	 */
	int parameterIndex();

	/**
	 * @return <code>RowMapper</code> type of instance for required CURSOR OUT
	 *         parameters. Default is set to <code>NullRowMapper</code>.
	 */
	Class<? extends RowMapper<?>> rowMapper() default NullRowMapper.class;

}
