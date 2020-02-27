package com.multibrand.request.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.apache.commons.lang.StringUtils;

/**
 * Checks Company Code.
 * 
 * @author atiwari
 */
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CompanyCodeConstraintValidator.class)
@Documented
public @interface ValidateCompanyCode {

	String message() default  "Please provide Valid Company Code";
	
	String messageText() default  StringUtils.EMPTY;
	
	String messageDescription() default  StringUtils.EMPTY;
	
	String value() default StringUtils.EMPTY;

	Class<?>[] groups() default {}; 
	
	Class<? extends Payload>[] payload() default {};

	@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
	@Retention(RUNTIME)
	@Documented
	@interface List{
		ValidateCompanyCode[] list();
	}
}
