package com.multibrand.util;

import org.apache.commons.lang.exception.ExceptionUtils;
/**
 * @author bbachin1
 * 
 */
public class NRGRestUtil {
	
	public static String getMessageFromException(Exception ex){
		if(null != ex){return ExceptionUtils.getRootCauseMessage(ex);}
		return "NULL EXCEPTION";
	}

}
