package com.multibrand.vo.request;

import java.io.Serializable;

import com.multibrand.dto.request.FormEntityRequest;

public class PrivacyPreferencesRequest implements Serializable, FormEntityRequest {

	private String smailPreferenceFlag;
	private String phoneCallPreferenceFlag;
	private String emailPreferenceFlag;
	private String customerType;
	private String businessName;
	private String firstName;
	private String lastName;
	private String addressLine1;
	private String addressLine2;
	private String cityValue;
	private String stateValue;
	private String zipCode;
	private String phoneNumber;
	private String emailAddress;
	private String reEmailAddress;
	private String languageCode;
	private String companyCode;
	
	
	public String getSmailPreferenceFlag() {
		return smailPreferenceFlag;
	}
	public void setSmailPreferenceFlag(String smailPreferenceFlag) {
		this.smailPreferenceFlag = smailPreferenceFlag;
	}
	public String getPhoneCallPreferenceFlag() {
		return phoneCallPreferenceFlag;
	}
	public void setPhoneCallPreferenceFlag(String phoneCallPreferenceFlag) {
		this.phoneCallPreferenceFlag = phoneCallPreferenceFlag;
	}
	public String getEmailPreferenceFlag() {
		return emailPreferenceFlag;
	}
	public void setEmailPreferenceFlag(String emailPreferenceFlag) {
		this.emailPreferenceFlag = emailPreferenceFlag;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCityValue() {
		return cityValue;
	}
	public void setCityValue(String cityValue) {
		this.cityValue = cityValue;
	}
	public String getStateValue() {
		return stateValue;
	}
	public void setStateValue(String stateValue) {
		this.stateValue = stateValue;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getReEmailAddress() {
		return reEmailAddress;
	}
	public void setReEmailAddress(String reEmailAddress) {
		this.reEmailAddress = reEmailAddress;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	
	
	
}
