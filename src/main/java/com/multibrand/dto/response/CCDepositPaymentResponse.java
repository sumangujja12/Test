package com.multibrand.dto.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="CCDepositPaymentResponse")
public class CCDepositPaymentResponse extends GenericResponse {
	
	private String confirmationNumber;
	
	public CCDepositPaymentResponse() {
		super();		
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}
	
	
}
