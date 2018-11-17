package com.multibrand.vo.response.billingResponse;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="CancelOTCCPaymentResp")
public class EditCancelOTCCPaymentResponse extends GenericResponse {

	
	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
	
}
