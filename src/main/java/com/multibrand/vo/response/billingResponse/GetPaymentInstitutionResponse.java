package com.multibrand.vo.response.billingResponse;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="GetPaymentInstitutionResponse")
public class GetPaymentInstitutionResponse extends GenericResponse {

	private String paymentInstitutionName;

	public String getPaymentInstitutionName() {
		return paymentInstitutionName;
	}

	public void setPaymentInstitutionName(String paymentInstitutionName) {
		this.paymentInstitutionName = paymentInstitutionName;
	}
	
	
}
