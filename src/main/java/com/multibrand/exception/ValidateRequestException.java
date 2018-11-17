package com.multibrand.exception;
/**
 * @author bbachin1
 * The ValidateRequestException class used to handle REQUEST BASED EXCEPTIONS.
 * 
 */
public class ValidateRequestException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public ValidateRequestException(String msg){super(msg);}
	
	public ValidateRequestException(){super();}
   
	public ValidateRequestException(Throwable throwable) {super(throwable);}
	
	public ValidateRequestException(String msg, Throwable throwable) {super(msg, throwable);}
	
}
