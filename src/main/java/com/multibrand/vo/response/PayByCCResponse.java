package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement(name="PayByCCResponse")
public class PayByCCResponse extends GenericResponse {

	private String errorCode = "";
	private String errorMessage = "";
	private java.lang.String strXCODE = "";
	private java.lang.String strXValidNum = "";

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

	@XmlElement(name = "successCode")
	public java.lang.String getStrXCODE() {
		return strXCODE;
	}

	public void setStrXCODE(java.lang.String strXCODE) {
		this.strXCODE = strXCODE;
	}

	@XmlElement(name = "confNumber")
	public java.lang.String getStrXValidNum() {
		return strXValidNum;
	}
	
	public void setStrXValidNum(java.lang.String strXValidNum) {
		this.strXValidNum = strXValidNum;
	}

}
