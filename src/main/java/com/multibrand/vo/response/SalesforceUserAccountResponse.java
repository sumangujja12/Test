package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="USERACCOUNT")
public class SalesforceUserAccountResponse  {
	

	@SerializedName("UtilityAccountNumber")
	private String utilityAccountNumber;
	
	
	@SerializedName("SiteName")
	private String siteName;
	
	@SerializedName("SiteID")
	private String siteID;	
	
	@SerializedName("ContractAccountNumber")
	private String contractAccountNumber;
	
	@SerializedName("BusinessPartnerNumber")
	private String businessPartnerNumber;
	
	@SerializedName("AccountPhone")
	private String phoneNumber;
	
	@SerializedName("AccountEmail")
	private String email;
	
	@SerializedName("AccountMeterZip")
	private String meterZip;
	
	@SerializedName("AccountMeterStreetName")
	private String meterStreetName;
	
	@SerializedName("AccountMeterState")
	private String meterState;
	
	@SerializedName("AccountMeterCity")
	private String meterCity;
	
	@SerializedName("AccountLastName")
	private String lastName;
	
	@SerializedName("AccountFirstName")
	private String firstName;

	@SerializedName("ProductionEmailOptIn")
	private String productionEmailOptIn;
	
	
	/**
	 * @return the utilityAccountNumber
	 */
	public String getUtilityAccountNumber() {
		return utilityAccountNumber;
	}

	/**
	 * @param utilityAccountNumber the utilityAccountNumber to set
	 */
	public void setUtilityAccountNumber(String utilityAccountNumber) {
		this.utilityAccountNumber = utilityAccountNumber;
	}

	/**
	 * @return the siteName
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * @param siteName the siteName to set
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	/**
	 * @return the siteID
	 */
	public String getSiteID() {
		return siteID;
	}

	/**
	 * @param siteID the siteID to set
	 */
	public void setSiteID(String siteID) {
		this.siteID = siteID;
	}

	/**
	 * @return the contractAccountNumber
	 */
	public String getContractAccountNumber() {
		return contractAccountNumber;
	}

	/**
	 * @param contractAccountNumber the contractAccountNumber to set
	 */
	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}

	/**
	 * @return the businessPartnerNumber
	 */
	public String getBusinessPartnerNumber() {
		return businessPartnerNumber;
	}

	/**
	 * @param businessPartnerNumber the businessPartnerNumber to set
	 */
	public void setBusinessPartnerNumber(String businessPartnerNumber) {
		this.businessPartnerNumber = businessPartnerNumber;
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
	 * @return the meterZip
	 */
	public String getMeterZip() {
		return meterZip;
	}

	/**
	 * @param meterZip the meterZip to set
	 */
	public void setMeterZip(String meterZip) {
		this.meterZip = meterZip;
	}

	/**
	 * @return the meterStreetName
	 */
	public String getMeterStreetName() {
		return meterStreetName;
	}

	/**
	 * @param meterStreetName the meterStreetName to set
	 */
	public void setMeterStreetName(String meterStreetName) {
		this.meterStreetName = meterStreetName;
	}

	/**
	 * @return the meterState
	 */
	public String getMeterState() {
		return meterState;
	}

	/**
	 * @param meterState the meterState to set
	 */
	public void setMeterState(String meterState) {
		this.meterState = meterState;
	}

	/**
	 * @return the meterCity
	 */
	public String getMeterCity() {
		return meterCity;
	}

	/**
	 * @param meterCity the meterCity to set
	 */
	public void setMeterCity(String meterCity) {
		this.meterCity = meterCity;
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
	 * @return the productionEmailOptIn
	 */
	public String getProductionEmailOptIn() {
		return productionEmailOptIn;
	}

	/**
	 * @param productionEmailOptIn the productionEmailOptIn to set
	 */
	public void setProductionEmailOptIn(String productionEmailOptIn) {
		this.productionEmailOptIn = productionEmailOptIn;
	}	
	
}
