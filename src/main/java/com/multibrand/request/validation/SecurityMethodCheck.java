package com.multibrand.request.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = SecurityMethodCheckValidator.class)
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface SecurityMethodCheck {


	String message() default "Security methods allowed values are DEPOSIT, ACTIVATION_FEE or blank.";
	

	Class<?>[] groups() default {}; 
	
	Class<? extends Payload>[] payload() default {};
	
	String[] fields() default { };
}
