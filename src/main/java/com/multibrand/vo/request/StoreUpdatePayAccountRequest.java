package com.multibrand.vo.request;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.dto.request.FormEntityRequest;
import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.util.Constants;

public class StoreUpdatePayAccountRequest implements FormEntityRequest, Constants,Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5313732309176983924L;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 12, groups = SizeConstraint.class)
	private String contractAccountNumber;
	
	@NotBlank(groups = BasicConstraint.class)
	private String onlinePayAccountType;
	private String lastFourDigit;
	private String nameOnAccount;
	private String payAccountNickName;
	private String payAccountToken;
	private String zipCode;
	private String activeFlag;
	private String activationDate;
	private String verifyCard;
	private String routingNumber;
	private String ccExpMonth;
	private String ccExpYear;
	private long onlinePayAccountId;
	private String ccType;
	private String autoPay;
	private String paymentInstitutionName;
	private String companyCode;
	private String brandName;
	
	
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getCcType() {
		return ccType;
	}
	public void setCcType(String ccType) {
		this.ccType = ccType;
	}
	
	public String getAutoPay() {
		return autoPay;
	}
	public void setAutoPay(String autoPay) {
		this.autoPay = autoPay;
	}
	public String getContractAccountNumber() {
		return contractAccountNumber;
	}
	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
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
	public long getOnlinePayAccountId() {
		return onlinePayAccountId;
	}
	public void setOnlinePayAccountId(long onlinePayAccountId) {
		this.onlinePayAccountId = onlinePayAccountId;
	}
	public String getPaymentInstitutionName() {
		return paymentInstitutionName;
	}
	public void setPaymentInstitutionName(String paymentInstitutionName) {
		this.paymentInstitutionName = paymentInstitutionName;
	}
	
	/**
	 * @return String representation of this request.
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	} 
	
}
