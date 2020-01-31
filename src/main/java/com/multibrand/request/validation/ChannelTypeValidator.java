package com.multibrand.request.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.multibrand.util.Constants;


public class ChannelTypeValidator implements 
			ConstraintValidator<ChannelType, String>, Constants{
	
    private List<String> channelTypeList = null;
    
    @Override
	public boolean isValid(String channelType, ConstraintValidatorContext arg1) {
    	if (channelType == null || StringUtils.isBlank(channelType)){
    		return true;
    	}else{
    		return channelTypeList.contains(channelType);
    	}
	}

    @Override
	public void initialize(ChannelType constraintAnnotation) {
		 this.channelTypeList=Arrays.asList(channelTypeArray);
	}
	
	
}