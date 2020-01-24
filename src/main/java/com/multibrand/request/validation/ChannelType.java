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

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ChannelTypeValidator.class)
@Documented
public @interface ChannelType {
	
	String format() default "";

	String message() default "Channel Type is not Valid";
	
	String messageCode() default StringUtils.EMPTY;

	String messageCodeText() default StringUtils.EMPTY;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String value() default "";

    
}
