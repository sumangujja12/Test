package com.multibrand.request.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import com.multibrand.util.Constants;


public class ChannelTypeValidator implements 
			ConstraintValidator<ChannelType, String>, Constants{
	
    private List<String> channelTypeList = null;
    
    protected static final String[] channelTypeArray = {CHANNEL_WEB,CHANNEL_AA,CHANNEL_AFF };
    
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