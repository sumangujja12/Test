package com.multibrand.request.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.multibrand.dto.request.IdentityRequest;
import com.multibrand.util.Constants;


public class NoidCheckValidator implements 
ConstraintValidator<NoidCheck, IdentityRequest>, Constants {

	@Override
	public void initialize(NoidCheck arg0) {
		//
	}

	@Override
	public boolean isValid(IdentityRequest identityRequest, ConstraintValidatorContext arg1) {
     boolean status = false;
		if(StringUtils.equalsIgnoreCase(FLAG_TRUE, identityRequest.getNoid()) || StringUtils.isEmpty(identityRequest.getNoid())){
			status= true;
	}
		
		return status;
	}

}
