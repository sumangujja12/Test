package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;


@Component
@XmlRootElement(name="AutoPayBankResponse")
public class AutoPayBankResponse extends GenericResponse {

	
	private String errorCode = "";
	private String errorMessage ="";
    private java.lang.String strStatus = "";

    @XmlElement(name = "successCode")
	public java.lang.String getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(java.lang.String strStatus) {
		this.strStatus = strStatus;
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
