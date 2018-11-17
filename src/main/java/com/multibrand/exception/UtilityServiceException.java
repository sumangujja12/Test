package com.multibrand.exception;
/**
 * @author bbachin1
 * The UtilityServiceException class used to handle REQUEST BASED EXCEPTIONS.
 * 
 */
public class UtilityServiceException extends Exception{
	
private static final long serialVersionUID = 1L;
	
	public UtilityServiceException(String msg){super(msg);}
	
	public UtilityServiceException(){super();}
   
	public UtilityServiceException(Throwable throwable) {super(throwable);}
	
	public UtilityServiceException(String msg, Throwable throwable) {super(msg, throwable);}
}
