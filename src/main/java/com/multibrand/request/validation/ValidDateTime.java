package com.multibrand.request.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.apache.commons.lang.StringUtils;

/**
 * Defines the ValidDateTime custom constraint annotation for Date/Time
 * validations.
 * 
 * @author Jenith. Y
 * @version Since JDK 1.6, Spring 3.2, Hibernate-validator 4.3.2 and Jersey 1.17 (1.x)
 */

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = DateTimeValidator.class)
@Documented
public @interface ValidDateTime {
	String format() default "MMddyyyy";

	String message() default "Date must be in MMddyyyy format or Time must be in HH:mm:ss format";
	
	/**
	 * @return Constraint violation error message code.
	 */
	String messageCode() default StringUtils.EMPTY;

	/**
	 * @return Constraint violation error message text related to a provided
	 *         <code>messageCode</code>.
	 */
	String messageCodeText() default StringUtils.EMPTY;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String value() default "";

}