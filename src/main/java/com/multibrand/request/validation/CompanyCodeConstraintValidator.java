package com.multibrand.request.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;

public class CompanyCodeConstraintValidator implements 
ConstraintValidator<ValidateCompanyCode, String>, Constants{

	private List<String> companyCodeList = null;
	
	@Override
	public void initialize(ValidateCompanyCode constraintAnnotation) {
		this.companyCodeList = Arrays.asList(COMPANY_CODE_ARRAY);
		
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return CommonUtil.isValidCompanyCode(value);
	}

}
