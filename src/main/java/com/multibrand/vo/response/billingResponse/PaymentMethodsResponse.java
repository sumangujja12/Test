package com.multibrand.vo.response.billingResponse;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name = "PaymentMethodsResponse")
@Component
public class PaymentMethodsResponse extends GenericResponse {

	private List<Object> paymentMethods;

	public List<Object> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethodsList(List<Object> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}
	
}
