package com.multibrand.dto.request;

import java.io.Serializable;


public class GetKBAQuestionsRequest extends BaseAffiliateRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String drivingLicenseState;
	private String trackingId;
	private String dateOfBirth;
	private String emailAddress;
	private String esiId;
	private String transactionType;
	private String firstName;
	private String middleName;
	private String lastName;
	private String middleNameInitials;
	private String phoneNumber;
	private String ipAddress;
	private String tokenizedDrivingLc;
	private String tokenizedSsn;
	private String serviceAddressCity;
	private String serviceAddressCountry;
	private String serviceAddressPoBox;
	private String serviceAddressState;
	private String serviceAddressStreetName;
	private String serviceAddressStreetNumber;
	private String serviceAddressAptNumber;
	private String serviceAddressZipCode;
	private String preferredLanguage;
	private String esidNumber;
	private String posidHoldFlag;
	private String  PosidUniqueKey;
	
	
	
	public String getPosidUniqueKey() {
		return PosidUniqueKey;
	}
	public void setPosidUniqueKey(String posidUniqueKey) {
		PosidUniqueKey = posidUniqueKey;
	}
	public String getEsidNumber() {
		return esidNumber;
	}
	public void setEsidNumber(String esidNumber) {
		this.esidNumber = esidNumber;
	}
	public String getPosidHoldFlag() {
		return posidHoldFlag;
	}
	public void setPosidHoldFlag(String posidHoldFlag) {
		this.posidHoldFlag = posidHoldFlag;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	public String getDrivingLicenseState() {
		return drivingLicenseState;
	}
	public void setDrivingLicenseState(String drivingLicenseState) {
		this.drivingLicenseState = drivingLicenseState;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getEsiId() {
		return esiId;
	}
	public void setEsiId(String esiId) {
		this.esiId = esiId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleNameInitials() {
		return middleNameInitials;
	}
	public void setMiddleNameInitials(String middleNameInitials) {
		this.middleNameInitials = middleNameInitials;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getTokenizedDrivingLc() {
		return tokenizedDrivingLc;
	}
	public void setTokenizedDrivingLc(String tokenizedDrivingLc) {
		this.tokenizedDrivingLc = tokenizedDrivingLc;
	}
	public String getTokenizedSsn() {
		return tokenizedSsn;
	}
	public void setTokenizedSsn(String tokenizedSsn) {
		this.tokenizedSsn = tokenizedSsn;
	}
	public String getServiceAddressCity() {
		return serviceAddressCity;
	}
	public void setServiceAddressCity(String serviceAddressCity) {
		this.serviceAddressCity = serviceAddressCity;
	}
	public String getServiceAddressCountry() {
		return serviceAddressCountry;
	}
	public void setServiceAddressCountry(String serviceAddressCountry) {
		this.serviceAddressCountry = serviceAddressCountry;
	}
	public String getServiceAddressPoBox() {
		return serviceAddressPoBox;
	}
	public void setServiceAddressPoBox(String serviceAddressPoBox) {
		this.serviceAddressPoBox = serviceAddressPoBox;
	}
	public String getServiceAddressState() {
		return serviceAddressState;
	}
	public void setServiceAddressState(String serviceAddressState) {
		this.serviceAddressState = serviceAddressState;
	}
	public String getServiceAddressStreetName() {
		return serviceAddressStreetName;
	}
	public void setServiceAddressStreetName(String serviceAddressStreetName) {
		this.serviceAddressStreetName = serviceAddressStreetName;
	}
	public String getServiceAddressStreetNumber() {
		return serviceAddressStreetNumber;
	}
	public void setServiceAddressStreetNumber(String serviceAddressStreetNumber) {
		this.serviceAddressStreetNumber = serviceAddressStreetNumber;
	}
	public String getServiceAddressAptNumber() {
		return serviceAddressAptNumber;
	}
	public void setServiceAddressAptNumber(String serviceAddressAptNumber) {
		this.serviceAddressAptNumber = serviceAddressAptNumber;
	}
	public String getServiceAddressZipCode() {
		return serviceAddressZipCode;
	}
	public void setServiceAddressZipCode(String serviceAddressZipCode) {
		this.serviceAddressZipCode = serviceAddressZipCode;
	}
	public String getPreferredLanguage() {
		return preferredLanguage;
	}
	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}
	
	
}
