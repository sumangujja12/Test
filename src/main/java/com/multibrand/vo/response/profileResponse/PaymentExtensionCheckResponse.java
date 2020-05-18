package com.multibrand.vo.response.profileResponse;

import com.multibrand.vo.response.GenericResponse;

public class PaymentExtensionCheckResponse extends GenericResponse {
	
	private boolean isPaymentExtension = false;
	private String paymentExtensionDate ="";
	private String payExtDueAmt ="";
	private String payExtPend ="";
	private String payExtnActive ="";

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

	/**
	 * @return the paymentExtensionDate
	 */
	public String getPaymentExtensionDate() {
		return paymentExtensionDate;
	}

	/**
	 * @param paymentExtensionDate the paymentExtensionDate to set
	 */
	public void setPaymentExtensionDate(String paymentExtensionDate) {
		this.paymentExtensionDate = paymentExtensionDate;
	}

	/**
	 * @return the payExtDueAmt
	 */
	public String getPayExtDueAmt() {
		return payExtDueAmt;
	}

	/**
	 * @param payExtDueAmt the payExtDueAmt to set
	 */
	public void setPayExtDueAmt(String payExtDueAmt) {
		this.payExtDueAmt = payExtDueAmt;
	}

	/**
	 * @return the payExtPend
	 */
	public String getPayExtPend() {
		return payExtPend;
	}

	/**
	 * @param payExtPend the payExtPend to set
	 */
	public void setPayExtPend(String payExtPend) {
		this.payExtPend = payExtPend;
	}

	/**
	 * @return the payExtnActive
	 */
	public String getPayExtnActive() {
		return payExtnActive;
	}

	/**
	 * @param payExtnActive the payExtnActive to set
	 */
	public void setPayExtnActive(String payExtnActive) {
		this.payExtnActive = payExtnActive;
	}
	

}
