package com.multibrand.vo.response.billingResponse;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PaymentMethodB {

	private String isAllowed;
	private String isRegisteredWithAutopay;
	private String nameOnAccount;
	private String routingNumber;
	private String paymentMethodType;
	private String paymentMethodToken;
	private String paymentMethodNickName;
	private String activationDate;
	private String verifyCard;
	private String onlinePayAccountId;
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
	public String getRoutingNumber() {
		return routingNumber;
	}
	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}
	public String getActivationDate() {
		return activationDate;
	}
	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}
	public String getVerifyCard() {
		return verifyCard;
	}
	public void setVerifyCard(String verifyCard) {
		this.verifyCard = verifyCard;
	}
	/**
	 * @return the onlinePayAccountId
	 */
	public String getOnlinePayAccountId() {
		return onlinePayAccountId;
	}
	/**
	 * @param onlinePayAccountId the onlinePayAccountId to set
	 */
	public void setOnlinePayAccountId(String onlinePayAccountId) {
		this.onlinePayAccountId = onlinePayAccountId;
	}
}
