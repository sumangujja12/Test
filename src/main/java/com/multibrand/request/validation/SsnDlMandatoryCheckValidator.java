package com.multibrand.request.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.multibrand.dto.request.IdentityRequest;
import com.multibrand.dto.request.PerformPosIdAndBpMatchRequest;
import com.multibrand.util.Constants;

public class SsnDlMandatoryCheckValidator   implements 
			ConstraintValidator<SsnDlMandatoryCheck, Object>, Constants{
	
	@Override
	public void initialize(SsnDlMandatoryCheck combinedNotNull) {
	}
			
	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
			
		if(object instanceof IdentityRequest){
						
			IdentityRequest value = (IdentityRequest) object;
						
			if(StringUtils.isNotBlank(value.getTokenizedSSN()) && StringUtils.isBlank(value.getTokenizedTDL()))
				return true;
			else if(StringUtils.isBlank(value.getTokenizedSSN()) && StringUtils.isNotBlank(value.getTokenizedTDL()))
				return true;
			else if(StringUtils.isNotBlank(value.getTokenizedSSN()) && StringUtils.isNotBlank(value.getTokenizedTDL()))
				return true;			
			}
		else if(object instanceof PerformPosIdAndBpMatchRequest ){
						
			PerformPosIdAndBpMatchRequest value = (PerformPosIdAndBpMatchRequest) object;
						
			if(StringUtils.isNotBlank(value.getTokenizedSSN()) && StringUtils.isBlank(value.getTokenizedTDL()))
				return true;
			else if(StringUtils.isBlank(value.getTokenizedSSN()) && StringUtils.isNotBlank(value.getTokenizedTDL()))
				return true;
			else if(StringUtils.isNotBlank(value.getTokenizedSSN()) && StringUtils.isNotBlank(value.getTokenizedTDL()))
				return true;
		}
		return false;
		}
}
