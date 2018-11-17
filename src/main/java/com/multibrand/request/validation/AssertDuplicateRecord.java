package com.multibrand.request.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.apache.commons.lang.StringUtils;

/**
 * Checks duplicate record constraint.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.x, Hibernate-validator 4.x and Jersey 1.17
 */
@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = DuplicateRecordConstraintValidator.class)
@Documented
public @interface AssertDuplicateRecord {

	/**
	 * @return The field value.
	 */
	String value() default StringUtils.EMPTY;

	/**
	 * @return Duplicate record validator class of instance type
	 *         <code>DuplicateRecordValidator</code>.
	 */
	Class<? extends DuplicateRecordValidator> duplicateRecordValidatorClass();

	/**
	 * @return Constraint violation error message from the underlying message
	 *         source.
	 */
	String message() default StringUtils.EMPTY;

	/**
	 * @return Constraint violation error message code.
	 */
	String messageCode() default StringUtils.EMPTY;

	/**
	 * @return Constraint violation error message text related to a provided
	 *         <code>messageCode</code>.
	 */
	String messageCodeText() default StringUtils.EMPTY;

	/**
	 * @return Defines constraint violation group.
	 */
	Class<?>[] groups() default {};

	/**
	 * @return Defines message pay load.
	 */
	Class<? extends Payload>[] payload() default {};

}
