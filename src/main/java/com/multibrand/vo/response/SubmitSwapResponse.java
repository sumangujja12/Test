package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement(name="SubmitSwap")
@Component
public class SubmitSwapResponse extends GenericResponse {

	private String IDOCNumber;
	private String errorMessage;
	private String errorCode;
	
	@XmlElement(name="idocNumber")
	public String getIDOCNumber() {
		return IDOCNumber;
	}
	public void setIDOCNumber(String iDOCNumber) {
		IDOCNumber = iDOCNumber;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}

