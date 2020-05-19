package com.multibrand.request.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import com.multibrand.util.Constants;


public class NotEmptyValidator implements 
			ConstraintValidator<NotEmpty, String>, Constants{

	@Override
	public void initialize(NotEmpty constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !StringUtils.isBlank(value);
	}
	
    
	
}