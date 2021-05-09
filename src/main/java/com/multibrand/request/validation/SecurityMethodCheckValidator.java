package com.multibrand.request.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.multibrand.dto.request.SalesEnrollmentRequest;
import com.multibrand.util.Constants;

public class SecurityMethodCheckValidator  implements 
ConstraintValidator<SecurityMethodCheck, SalesEnrollmentRequest>, Constants {

	@Override
	public void initialize(SecurityMethodCheck arg0) {
		 // Do nothing.
	}

	@Override
	public boolean isValid(SalesEnrollmentRequest salesEnrollmentRequest, ConstraintValidatorContext arg1) {
		boolean status=false;
		if(StringUtils.isEmpty(salesEnrollmentRequest.getSecurityMethod()) || DEPOSIT.equals(salesEnrollmentRequest.getSecurityMethod()) ||SURETY_BOND.equals(salesEnrollmentRequest.getSecurityMethod())) { 
			status=true;
		}
		return status;
		
	}


	
}
