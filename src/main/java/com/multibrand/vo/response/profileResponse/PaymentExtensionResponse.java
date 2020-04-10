package com.multibrand.vo.response.profileResponse;

import com.multibrand.vo.response.GenericResponse;

public class PaymentExtensionResponse extends GenericResponse {
	
	private boolean isPaymentExtension = false;
	

	/**
	 * @return the isPaymentExtension
	 */
	public boolean isPaymentExtension() {
		return isPaymentExtension;
	}

	/**
	 * @param isPaymentExtension the isPaymentExtension to set
	 */
	public void setPaymentExtension(boolean isPaymentExtension) {
		this.isPaymentExtension = isPaymentExtension;
	}

}
