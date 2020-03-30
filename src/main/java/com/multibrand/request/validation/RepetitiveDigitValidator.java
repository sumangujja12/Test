package com.multibrand.request.validation;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import com.multibrand.util.Constants;


public class RepetitiveDigitValidator implements 
			ConstraintValidator<RepetitiveDigitCheck, String>, Constants{
	
	Pattern pattern=null;
	Matcher matcher=null; 
	String regex = "^([0]){9}$|^([1]){9}$|^([2]){9}$|^([3]){9}$|^([4]){9}$|^([5]){9}$|^([6]){9}$|^([7]){9}$|^([8]){9}$|^([9]){9}$";
	    
    @Override
	public boolean isValid(String number, ConstraintValidatorContext arg1) {
    	if(number == null){
    		number = StringUtils.EMPTY;
    	}
    	pattern = Pattern.compile(regex);
		matcher = pattern.matcher(number);
		//System.out.println(number+"  : "+matcher.matches());
		return !(matcher.matches());
    }    	

	@Override
	public void initialize(RepetitiveDigitCheck arg0) {
		
	}
	
	
}