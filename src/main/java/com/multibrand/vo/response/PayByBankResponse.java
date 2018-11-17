package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement(name="PayByBankResponse")
public class PayByBankResponse extends GenericResponse {

	
	private String errorCode = "";
	private String errorMessage ="";
    private java.lang.String strXCODE="";
    private java.lang.String strOTBDId="";
    
    

    @XmlElement(name="confNumber")
    public java.lang.String getStrOTBDId() {
		return strOTBDId;
	}

	public void setStrOTBDId(java.lang.String strOTBDId) {
		this.strOTBDId = strOTBDId;
	}

	@XmlElement(name = "successCode")
	public java.lang.String getStrXCODE() {
		return strXCODE;
	}

	public void setStrXCODE(java.lang.String strXCODE) {
		this.strXCODE = strXCODE;
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
