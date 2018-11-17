package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement(name = "ValidateCCResponse")
public class ValidateCCResponse extends GenericResponse {

	private java.lang.String errorCode="";

	private java.lang.String errorMessage="";
	
	private java.lang.String strXCODE= "";

	@XmlElement(name = "successCode")
	public java.lang.String getStrXCODE() {
		return strXCODE;
	}

	public void setStrXCODE(java.lang.String strXCODE) {
		this.strXCODE = strXCODE;
	}

	public java.lang.String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(java.lang.String errorCode) {
		this.errorCode = errorCode;
	}

	public java.lang.String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(java.lang.String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	

}
