package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement(name="CheckIfPrepayOfferResponse")
@Component
public class CheckIfPrepayOfferResponse extends GenericResponse {
	
	private String errorCode = "";
	private String errorMessage ="";
    private String prepayOfferFlag = "";
    
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
	public String getPrepayOfferFlag() {
		return prepayOfferFlag;
	}
	public void setPrepayOfferFlag(String prepayOfferFlag) {
		this.prepayOfferFlag = prepayOfferFlag;
	}
	
}
