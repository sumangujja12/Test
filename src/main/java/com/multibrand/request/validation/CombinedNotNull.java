package com.multibrand.request.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.apache.commons.lang.StringUtils;

@Documented
@Constraint(validatedBy = CombinedNotNullValidator.class)
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface CombinedNotNull {
	
	String message() default "";
	
	String value() default StringUtils.EMPTY;

	Class<?>[] groups() default {}; 
	
	Class<? extends Payload>[] payload() default {};
	
	String[] fields() default { };
}
