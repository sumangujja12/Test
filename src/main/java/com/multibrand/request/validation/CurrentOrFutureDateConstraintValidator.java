package com.multibrand.request.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.multibrand.util.Constants;

public class CurrentOrFutureDateConstraintValidator implements 
ConstraintValidator<CurrentOrFutureDate, String>, Constants{

	DateTimeFormatter dateFormat = null;
	@Override
	public void initialize(CurrentOrFutureDate constraintAnnotation) {
		dateFormat = DateTimeFormatter.ofPattern("MMddyyyy");
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
	        LocalDate startDate = LocalDate.parse(value, dateFormat);
	        LocalDate current = LocalDate.now();
	        return (startDate.isEqual(current) || startDate.isAfter(current));
	    }catch(DateTimeParseException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}

}
