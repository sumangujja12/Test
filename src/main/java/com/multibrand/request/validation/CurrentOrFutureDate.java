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

@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CurrentOrFutureDateConstraintValidator.class)
@Documented
@ValidDateTime(format = "MMddyyyy", groups = FormatConstraint.class)
public @interface CurrentOrFutureDate{

	String message() default  "must be a current or future date";
	
	String value() default StringUtils.EMPTY;
	
	Class<?>[] groups() default {}; 
	
	Class<? extends Payload>[] payload() default {};
	
	@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
	@Retention(RUNTIME)
	@Documented
	@interface List{
		CurrentOrFutureDate[] list();
	}
}
