package com.multibrand.request.validation;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
 
import com.multibrand.util.Constants;


public class RepetitiveDigitValidator implements 
			ConstraintValidator<RepetitiveDigitCheck, String>, Constants{
	
	Pattern pattern=null;
	Matcher matcher=null;
	String regex ="^(?:(?!\0+|\1+|\2+|\3+|\4+))\\d{9}$";
	    
    @Override
	public boolean isValid(String number, ConstraintValidatorContext arg1) {
    	
    	pattern = Pattern.compile(regex);
		matcher = pattern.matcher(number);
		//System.out.println(number+"  : "+matcher.matches());
		return !(matcher.matches());
    }    	

	@Override
	public void initialize(RepetitiveDigitCheck arg0) {
		
	}
	
	
}