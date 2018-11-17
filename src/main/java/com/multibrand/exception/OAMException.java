package com.multibrand.exception;

public class OAMException extends RuntimeException {

	private int errorCode;
	private String errorMessage;
	private Object response;
	
	public OAMException(int errCode, String errMsg, Object resp){
		
		this.errorCode = errCode;
		this.errorMessage = errMsg;
		this.response = resp;
	}
	
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	
	
}
