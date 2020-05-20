package com.multibrand.request.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.multibrand.util.Constants;
import com.multibrand.util.LoggerUtil;

public class CurrentOrFutureDateConstraintValidator implements 
ConstraintValidator<CurrentOrFutureDate, String>, Constants{

	LoggerUtil logger = LoggerUtil.getInstance("NRGREST_LOGGER");
	
	DateTimeFormatter dateFormat = null;
	@Override
	public void initialize(CurrentOrFutureDate constraintAnnotation) {
		dateFormat = DateTimeFormatter.ofPattern("MMddyyyy");
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			if(StringUtils.isNotBlank(value)){
		        LocalDate startDate = LocalDate.parse(value, dateFormat);
		        LocalDate current = LocalDate.now();
		        return (startDate.isEqual(current) || startDate.isAfter(current));
			}
	    }catch(DateTimeParseException ex) {
	    	logger.error("Exception in CurrentOrFutureDateConstraintValidator.isValid "+ex.getMessage());
	    }
	    return false;
	}

}
