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
@Constraint(validatedBy = RepetitiveDigitValidator.class)
@Documented
public @interface RepetitiveDigitCheck {
	
	String message() default "All same repetitive digits are not allowed";
	
	String messageText() default  StringUtils.EMPTY;
	
	String messageDescription() default  StringUtils.EMPTY;
	
	String value() default StringUtils.EMPTY;

	Class<?>[] groups() default {}; 
	
	Class<? extends Payload>[] payload() default {};

	@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
	@Retention(RUNTIME)
	@Documented
	@interface List{
		RepetitiveDigitCheck[] list();
	}

    
}
