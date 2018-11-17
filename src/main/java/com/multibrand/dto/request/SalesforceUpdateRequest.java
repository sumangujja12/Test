package com.multibrand.dto.request;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import com.multibrand.util.Constants;

public class SalesforceUpdateRequest implements Constants, Serializable {
	

	
	@SerializedName("LeaseId")
	private String leaseId;
	
	@SerializedName("AccountEmail")
	private String email;	

	@SerializedName("Phone")
	private String phone;
	
	@SerializedName("RegistrationDate")
	private String registrationDate;	

	@SerializedName("LoginDate")
	private String loginDate;
	
	@SerializedName("BillingStreet")
	private String street;
	
	@SerializedName("BillingStreet2")
	private String street2;
	
	@SerializedName("BillingCity")
	private String city;
	
	@SerializedName("BillingState")
	private String state;
	
	@SerializedName("BillingZip")
	private String zip;

	@SerializedName("productionEmailOptIn")
	private Boolean productionEmailOptIn;
	/**
	 * @return the leaseId
	 */
	public String getLeaseId() {
		return leaseId;
	}

	/**
	 * @param leaseId the leaseId to set
	 */
	public void setLeaseId(String leaseId) {
		this.leaseId = leaseId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the registrationDate
	 */
	public String getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the loginDate
	 */
	public String getLoginDate() {
		return loginDate;
	}

	/**
	 * @param loginDate the loginDate to set
	 */
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the street2
	 */
	public String getStreet2() {
		return street2;
	}

	/**
	 * @param street2 the street2 to set
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	/**
	 * @return the productionEmailOptIn
	 */
	public Boolean getProductionEmailOptIn() {
		return productionEmailOptIn;
	}

	/**
	 * @param productionEmailOptIn the productionEmailOptIn to set
	 */
	public void setProductionEmailOptIn(Boolean productionEmailOptIn) {
		this.productionEmailOptIn = productionEmailOptIn;
	}	
	
}
