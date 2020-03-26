package com.multibrand.request.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import com.multibrand.util.Constants;


public class ActionCodeValidator implements 
			ConstraintValidator<ActionCode, String>, Constants{
	
    private List<String> actionCodeList = null;
    
    protected static final String[] actionCodeArray = {"CE","AE","PE","SE" };
    
    @Override
	public boolean isValid(String actionCode, ConstraintValidatorContext arg1) {
    	
    		return actionCodeList.contains(actionCode);
    	
	}


	@Override
	public void initialize(ActionCode arg0) {
		this.actionCodeList=Arrays.asList(actionCodeArray);
	}
	
	
}