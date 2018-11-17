package com.multibrand.dto.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="BankDepositPaymentResponse")
public class BankDepositPaymentResponse extends GenericResponse {
	
	private String confirmationNumber;
	
	public BankDepositPaymentResponse() {
		super();		
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}
	
	
}
