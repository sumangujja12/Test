package com.multibrand.request.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
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

@Documented
@Constraint(validatedBy = {NotEmptyValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface NotEmpty {
	
	String message() default " is required";
	
	String value() default StringUtils.EMPTY;

	Class<?>[] groups() default {}; 
	
	Class<? extends Payload>[] payload() default {};

	@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
	@Retention(RUNTIME)
	@Documented
	@interface List{
		NotEmpty[] list();
	}

    
}
