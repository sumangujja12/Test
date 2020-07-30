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
	private String[] companyCodeArr = {COMPANY_CODE_RELIANT, COMPANY_CODE_GME, COMPANY_CODE_PENNYWISE, COMPANY_CODE_EE, XOOM_COMPANY_CODE};
	
	@Override
	public void initialize(ValidateCompanyCode constraintAnnotation) {
		this.companyCodeList = Arrays.asList(companyCodeArr);
		
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return CommonUtil.isValidCompanyCode(value);
	}

}
