package com.multibrand.request.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.multibrand.util.Constants;

public class CompanyCodeConstraintValidator implements 
ConstraintValidator<ValidateCompanyCode, String>, Constants{

	private List<String> companyCodeList = null;
	protected static final String[] companyCodeArray = {COMPANY_CODE_RELIANT, COMPANY_CODE_GME, COMPANY_CODE_PENNYWISE, COMPANY_CODE_EE};
	
	@Override
	public void initialize(ValidateCompanyCode constraintAnnotation) {
		this.companyCodeList = Arrays.asList(companyCodeArray);
		
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value==null) {
			return false;
		}else {
			return companyCodeList.contains(value);
		}
	}

}
