package com.multibrand.request.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.multibrand.util.CommonUtil;

/**
 * Defines a constraint validator for the constraint ValidDateTime.
 * 
 * @author Jenith. Y
 * @version Since JDK 1.6, Spring 3.2, Hibernate-validator 4.3.2 and Jersey 1.17 (1.x)
 */

public class DateTimeValidator implements
		ConstraintValidator<ValidDateTime, String> {

	private String dateFormat;

	public void initialize(ValidDateTime constraintAnnotation) {
		this.dateFormat = constraintAnnotation.format();
	}

	public boolean isValid(String value,
			ConstraintValidatorContext constraintContext) {

		if (value == null)
			return true;

		return CommonUtil.isValidDate(value, dateFormat);
	}

}