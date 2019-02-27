package com.multibrand.vo.response.billingResponse;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PaymentMethodCC {

	private String isAllowed;
	private String isRegisteredWithAutopay;
	private String nameOnAccount;
	private String creditCardExpYear;
	private String creditCardExpMonth;
	private String creditCardType;
	private String paymentMethodType;
	private String paymentMethodToken;
	private String paymentMethodNickName;
	private String zipCode;
	
	public String getIsAllowed() {
		return isAllowed;
	}
	public void setIsAllowed(String isAllowed) {
		this.isAllowed = isAllowed;
	}
	public String getIsRegisteredWithAutopay() {
		return isRegisteredWithAutopay;
	}
	public void setIsRegisteredWithAutopay(String isRegisteredWithAutopay) {
		this.isRegisteredWithAutopay = isRegisteredWithAutopay;
	}
	public String getNameOnAccount() {
		return nameOnAccount;
	}
	public void setNameOnAccount(String nameOnAccount) {
		this.nameOnAccount = nameOnAccount;
	}
	public String getCreditCardExpYear() {
		return creditCardExpYear;
	}
	public void setCreditCardExpYear(String creditCardExpYear) {
		this.creditCardExpYear = creditCardExpYear;
	}
	public String getCreditCardExpMonth() {
		return creditCardExpMonth;
	}
	public void setCreditCardExpMonth(String creditCardExpMonth) {
		this.creditCardExpMonth = creditCardExpMonth;
	}
	public String getCreditCardType() {
		return creditCardType;
	}
	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}
	public String getPaymentMethodType() {
		return paymentMethodType;
	}
	public void setPaymentMethodType(String paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}
	public String getPaymentMethodToken() {
		return paymentMethodToken;
	}
	public void setPaymentMethodToken(String paymentMethodToken) {
		this.paymentMethodToken = paymentMethodToken;
	}
	public String getPaymentMethodNickName() {
		return paymentMethodNickName;
	}
	public void setPaymentMethodNickName(String paymentMethodNickName) {
		this.paymentMethodNickName = paymentMethodNickName;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
