package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;


@Component
@XmlRootElement(name="CancelPaymentResponse")
public class CancelPaymentResponse extends GenericResponse {

	
	private String errorCode = "";
	private String errorMessage ="";
    private java.lang.String successCode = "";

    

	public java.lang.String getSuccessCode() {
		return successCode;
	}

	public void setSuccessCode(java.lang.String successCode) {
		this.successCode = successCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
    
	
	
    
    
    
}
