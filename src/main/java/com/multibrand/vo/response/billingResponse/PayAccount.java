package com.multibrand.vo.response.billingResponse;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PayAccount {
	
	private String userAccountNumber;
	private String onlinePayAccountType;
	private String lastFourDigit;
	private String nameOnAccount;
	private String payAccountNickName;
	private String payAccountToken;
	private String zipCode;
	private String activeFlag;
	private Date lastPaymentDate;
	private Date activationDate;
	private Date cpdbCreationDate;
	private Date cpdbUpdateDate;
	private String verifyCard;
	private String routingNumber;
	private String ccExpMonth;
	private String ccExpYear;
	private String ccType;
	private String onlinePayAccountId;
	private String autoPay;
	private String paymentInstitutionName;
	
	
	public String getPaymentInstitutionName() {
		return paymentInstitutionName;
	}
	public void setPaymentInstitutionName(String paymentInstitutionName) {
		this.paymentInstitutionName = paymentInstitutionName;
	}
	public String getUserAccountNumber() {
		return userAccountNumber;
	}
	public void setUserAccountNumber(String userAccountNumber) {
		this.userAccountNumber = userAccountNumber;
	}
	public String getOnlinePayAccountType() {
		return onlinePayAccountType;
	}
	public void setOnlinePayAccountType(String onlinePayAccountType) {
		this.onlinePayAccountType = onlinePayAccountType;
	}
	public String getLastFourDigit() {
		return lastFourDigit;
	}
	public void setLastFourDigit(String lastFourDigit) {
		this.lastFourDigit = lastFourDigit;
	}
	public String getNameOnAccount() {
		return nameOnAccount;
	}
	public void setNameOnAccount(String nameOnAccount) {
		this.nameOnAccount = nameOnAccount;
	}
	public String getPayAccountNickName() {
		return payAccountNickName;
	}
	public void setPayAccountNickName(String payAccountNickName) {
		this.payAccountNickName = payAccountNickName;
	}
	public String getPayAccountToken() {
		return payAccountToken;
	}
	public void setPayAccountToken(String payAccountToken) {
		this.payAccountToken = payAccountToken;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}
	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}
	public Date getActivationDate() {
		return activationDate;
	}
	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}
	public Date getCpdbCreationDate() {
		return cpdbCreationDate;
	}
	public void setCpdbCreationDate(Date cpdbCreationDate) {
		this.cpdbCreationDate = cpdbCreationDate;
	}
	public Date getCpdbUpdateDate() {
		return cpdbUpdateDate;
	}
	public void setCpdbUpdateDate(Date cpdbUpdateDate) {
		this.cpdbUpdateDate = cpdbUpdateDate;
	}
	public String getVerifyCard() {
		return verifyCard;
	}
	public void setVerifyCard(String verifyCard) {
		this.verifyCard = verifyCard;
	}
	public String getRoutingNumber() {
		return routingNumber;
	}
	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}
	public String getCcExpMonth() {
		return ccExpMonth;
	}
	public void setCcExpMonth(String ccExpMonth) {
		this.ccExpMonth = ccExpMonth;
	}
	public String getCcExpYear() {
		return ccExpYear;
	}
	public void setCcExpYear(String ccExpYear) {
		this.ccExpYear = ccExpYear;
	}
	public String getOnlinePayAccountId() {
		return onlinePayAccountId;
	}
	public void setOnlinePayAccountId(String onlinePayAccountId) {
		this.onlinePayAccountId = onlinePayAccountId;
	}
	
	public String getAutoPay() {
		return autoPay;
	}
	public void setAutoPay(String autoPay) {
		this.autoPay = autoPay;
	}
	public String getCcType() {
		return ccType;
	}
	public void setCcType(String ccType) {
		this.ccType = ccType;
	}
	
	
}
