package com.multibrand.request.validation;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.multibrand.util.Constants;


public class AgeValidator implements 
			ConstraintValidator<ValidAge, String>, Constants{
	
   
	@Override
    public boolean isValid(String dob, ConstraintValidatorContext arg1) {
        try{
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(MMddyyyy);        
            LocalDate birthday = LocalDate.parse(dob, dateTimeFormatter);
            LocalDate today = LocalDate.now();
            Period period = Period.between(birthday, today);
            int age=period.getYears();
            if(age>=18 && age<=100)
                return true;
            else
                return false;
        }catch(Exception e){
            return false;
        }
    }
    	

	@Override
	public void initialize(ValidAge arg0) {
		
	}
	
	
}