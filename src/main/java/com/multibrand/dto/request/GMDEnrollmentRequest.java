package com.multibrand.dto.request;

import java.io.Serializable;

import com.multibrand.util.CommonUtil;

public class GMDEnrollmentRequest implements FormEntityRequest, Serializable {

	private static final long serialVersionUID = 1L;
	private String transactionId = "";
	private String companyCode = "";
	private String brandName = "";
	private String trackingId = "";
	private String preferredLanguageCode = "";
	private String tdspCode = "";
	private String transactionType = "";
	private String esiId = "";
	private String switchHoldFlag = "";
	private String moveInDate = "";
	private String firstName = "";
	private String lastName = "";
	private String middleName = "";
	private String maidenName ="";
	private String dateOfBirth = "";
	private String phoneNumber = "";
	private String emailAddress = "";
	private String paymentType = "";
	private String isAutoPay = "";
	private String ccInstituteCode = "";
	private String ccNumber = "";
	private String paymentAccountName = "";
	private String paymentAmount = "";
	private String bankAccountNumber = "";
	private String bankRoutingNumber = "";
	private String expirationDate = "";
	private String serviceAddressStreetNumber = "";
	private String serviceAddressStreetName = "";
	private String serviceAddressAptNumber = "";
	private String serviceAddressCity = "";
	private String serviceAddressState = "";
	private String serviceAddressZipCode = "";
	private String billingAddressStreetNumber = "";
	private String billingAddressStreetName = "";
	private String billingAddressAptNumber = "";
	private String billingAddressCity = "";
	private String billingAddressState = "";
	private String billingAddressZipCode = "";
	private String billingAddressPoBox = "";
	private String offerCode = "";
	private String promoCode = "";
	private String campaignCode = "";
	private String offerDate = "";
	private String offerTime = "";
	private String recentCallMade ="";
	private String cvvNumber ="";
	private String mobileNumber ="";
	private String serviceStartDate  ="";
	
	
	
	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}
	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * @return the trackingId
	 */
	public String getTrackingId() {
		return trackingId;
	}
	/**
	 * @param trackingId the trackingId to set
	 */
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	/**
	 * @return the preferredLanguageCode
	 */
	public String getPreferredLanguageCode() {
		return preferredLanguageCode;
	}
	/**
	 * @param preferredLanguageCode the preferredLanguageCode to set
	 */
	public void setPreferredLanguageCode(String preferredLanguageCode) {
		this.preferredLanguageCode = preferredLanguageCode;
	}
	/**
	 * @return the tdspCode
	 */
	public String getTdspCode() {
		return tdspCode;
	}
	/**
	 * @param tdspCode the tdspCode to set
	 */
	public void setTdspCode(String tdspCode) {
		this.tdspCode = tdspCode;
	}
	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	/**
	 * @return the esiId
	 */
	public String getEsiId() {
		return esiId;
	}
	/**
	 * @param esiId the esiId to set
	 */
	public void setEsiId(String esiId) {
		this.esiId = esiId;
	}
	/**
	 * @return the switchHoldFlag
	 */
	public String getSwitchHoldFlag() {
		return switchHoldFlag;
	}
	/**
	 * @param switchHoldFlag the switchHoldFlag to set
	 */
	public void setSwitchHoldFlag(String switchHoldFlag) {
		this.switchHoldFlag = switchHoldFlag;
	}
	/**
	 * @return the moveInDate
	 */
	public String getMoveInDate() {
		return moveInDate;
	}
	/**
	 * @param moveInDate the moveInDate to set
	 */
	public void setMoveInDate(String moveInDate) {
		this.moveInDate = moveInDate;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * @return the isAutoPay
	 */
	public String getIsAutoPay() {
		return isAutoPay;
	}
	/**
	 * @param isAutoPay the isAutoPay to set
	 */
	public void setIsAutoPay(String isAutoPay) {
		this.isAutoPay = isAutoPay;
	}
	/**
	 * @return the ccInstituteCode
	 */
	public String getCcInstituteCode() {
		return ccInstituteCode;
	}
	/**
	 * @param ccInstituteCode the ccInstituteCode to set
	 */
	public void setCcInstituteCode(String ccInstituteCode) {
		this.ccInstituteCode = ccInstituteCode;
	}
	/**
	 * @return the ccNumber
	 */
	public String getCcNumber() {
		return ccNumber;
	}
	/**
	 * @param ccNumber the ccNumber to set
	 */
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	/**
	 * @return the paymentAccountName
	 */
	public String getPaymentAccountName() {
		return paymentAccountName;
	}
	/**
	 * @param paymentAccountName the paymentAccountName to set
	 */
	public void setPaymentAccountName(String paymentAccountName) {
		this.paymentAccountName = paymentAccountName;
	}
	/**
	 * @return the paymentAmount
	 */
	public String getPaymentAmount() {
		return paymentAmount;
	}
	/**
	 * @param paymentAmount the paymentAmount to set
	 */
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	/**
	 * @return the bankAccountNumber
	 */
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	/**
	 * @param bankAccountNumber the bankAccountNumber to set
	 */
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	/**
	 * @return the bankRoutingNumber
	 */
	public String getBankRoutingNumber() {
		return bankRoutingNumber;
	}
	/**
	 * @param bankRoutingNumber the bankRoutingNumber to set
	 */
	public void setBankRoutingNumber(String bankRoutingNumber) {
		this.bankRoutingNumber = bankRoutingNumber;
	}
	/**
	 * @return the expirationDate
	 */
	public String getExpirationDate() {
		return expirationDate;
	}
	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	/**
	 * @return the serviceAddressStreetNumber
	 */
	public String getServiceAddressStreetNumber() {
		return serviceAddressStreetNumber;
	}
	/**
	 * @param serviceAddressStreetNumber the serviceAddressStreetNumber to set
	 */
	public void setServiceAddressStreetNumber(String serviceAddressStreetNumber) {
		this.serviceAddressStreetNumber = serviceAddressStreetNumber;
	}
	/**
	 * @return the serviceAddressStreetName
	 */
	public String getServiceAddressStreetName() {
		return serviceAddressStreetName;
	}
	/**
	 * @param serviceAddressStreetName the serviceAddressStreetName to set
	 */
	public void setServiceAddressStreetName(String serviceAddressStreetName) {
		this.serviceAddressStreetName = serviceAddressStreetName;
	}
	/**
	 * @return the serviceAddressAptNumber
	 */
	public String getServiceAddressAptNumber() {
		return serviceAddressAptNumber;
	}
	/**
	 * @param serviceAddressAptNumber the serviceAddressAptNumber to set
	 */
	public void setServiceAddressAptNumber(String serviceAddressAptNumber) {
		this.serviceAddressAptNumber = serviceAddressAptNumber;
	}

	/**
	 * @return the serviceAddressCity
	 */
	public String getServiceAddressCity() {
		return serviceAddressCity;
	}
	/**
	 * @param serviceAddressCity the serviceAddressCity to set
	 */
	public void setServiceAddressCity(String serviceAddressCity) {
		this.serviceAddressCity = serviceAddressCity;
	}
	/**
	 * @return the serviceAddressState
	 */
	public String getServiceAddressState() {
		return serviceAddressState;
	}
	/**
	 * @param serviceAddressState the serviceAddressState to set
	 */
	public void setServiceAddressState(String serviceAddressState) {
		this.serviceAddressState = serviceAddressState;
	}
	/**
	 * @return the serviceAddressZipCode
	 */
	public String getServiceAddressZipCode() {
		return serviceAddressZipCode;
	}
	/**
	 * @param serviceAddressZipCode the serviceAddressZipCode to set
	 */
	public void setServiceAddressZipCode(String serviceAddressZipCode) {
		this.serviceAddressZipCode = serviceAddressZipCode;
	}
	/**
	 * @return the billingAddressStreetNumber
	 */
	public String getBillingAddressStreetNumber() {
		return billingAddressStreetNumber;
	}
	/**
	 * @param billingAddressStreetNumber the billingAddressStreetNumber to set
	 */
	public void setBillingAddressStreetNumber(String billingAddressStreetNumber) {
		this.billingAddressStreetNumber = billingAddressStreetNumber;
	}
	/**
	 * @return the billingAddressStreetName
	 */
	public String getBillingAddressStreetName() {
		return billingAddressStreetName;
	}
	/**
	 * @param billingAddressStreetName the billingAddressStreetName to set
	 */
	public void setBillingAddressStreetName(String billingAddressStreetName) {
		this.billingAddressStreetName = billingAddressStreetName;
	}
	/**
	 * @return the billingAddressAptNumber
	 */
	public String getBillingAddressAptNumber() {
		return billingAddressAptNumber;
	}
	/**
	 * @param billingAddressAptNumber the billingAddressAptNumber to set
	 */
	public void setBillingAddressAptNumber(String billingAddressAptNumber) {
		this.billingAddressAptNumber = billingAddressAptNumber;
	}
	/**
	 * @return the billingAddressCity
	 */
	public String getBillingAddressCity() {
		return billingAddressCity;
	}
	/**
	 * @param billingAddressCity the billingAddressCity to set
	 */
	public void setBillingAddressCity(String billingAddressCity) {
		this.billingAddressCity = billingAddressCity;
	}	
	/**
	 * @return the billingAddressState
	 */
	public String getBillingAddressState() {
		return billingAddressState;
	}
	/**
	 * @param billingAddressState the billingAddressState to set
	 */
	public void setBillingAddressState(String billingAddressState) {
		this.billingAddressState = billingAddressState;
	}
	/**
	 * @return the billingAddressZipCode
	 */
	public String getBillingAddressZipCode() {
		return billingAddressZipCode;
	}
	/**
	 * @param billingAddressZipCode the billingAddressZipCode to set
	 */
	public void setBillingAddressZipCode(String billingAddressZipCode) {
		this.billingAddressZipCode = billingAddressZipCode;
	}
	/**
	 * @return the billingAddressPoBox
	 */
	public String getBillingAddressPoBox() {
		return billingAddressPoBox;
	}
	/**
	 * @param billingAddressPoBox the billingAddressPoBox to set
	 */
	public void setBillingAddressPoBox(String billingAddressPoBox) {
		this.billingAddressPoBox = billingAddressPoBox;
	}
	/**
	 * @return the offerCode
	 */
	public String getOfferCode() {
		return offerCode;
	}
	/**
	 * @param offerCode the offerCode to set
	 */
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	/**
	 * @return the promoCode
	 */
	public String getPromoCode() {
		return promoCode;
	}
	/**
	 * @param promoCode the promoCode to set
	 */
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	/**
	 * @return the campaignCode
	 */
	public String getCampaignCode() {
		return campaignCode;
	}
	/**
	 * @param campaignCode the campaignCode to set
	 */
	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}
	/**
	 * @return the offerDate
	 */
	public String getOfferDate() {
		return offerDate;
	}
	/**
	 * @param offerDate the offerDate to set
	 */
	public void setOfferDate(String offerDate) {
		this.offerDate = offerDate;
	}
	/**
	 * @return the offerTime
	 */
	public String getOfferTime() {
		return offerTime;
	}
	/**
	 * @param offerTime the offerTime to set
	 */
	public void setOfferTime(String offerTime) {
		this.offerTime = offerTime;
	}
	/**
	 * @return the recentCallMade
	 */
	public String getRecentCallMade() {
		return recentCallMade;
	}
	/**
	 * @param recentCallMade the recentCallMade to set
	 */
	public void setRecentCallMade(String recentCallMade) {
		this.recentCallMade = recentCallMade;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the maidenName
	 */
	public String getMaidenName() {
		return maidenName;
	}
	/**
	 * @param maidenName the maidenName to set
	 */
	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}
	/**
	 * @return the cvvNumber
	 */
	public String getCvvNumber() {
		return cvvNumber;
	}
	/**
	 * @param cvvNumber the cvvNumber to set
	 */
	public void setCvvNumber(String cvvNumber) {
		this.cvvNumber = cvvNumber;
	}
	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}	
	
	
	/**
	 * @return the serviceStartDate
	 */
	public String getServiceStartDate() {
		return serviceStartDate;
	}
	/**
	 * @param serviceStartDate the serviceStartDate to set
	 */
	public void setServiceStartDate(String serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}
	
	
	@Override
	public String toString() {
		return super.toString() + CommonUtil.doRender(this);
	}	
}
