package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="AccountResponse")
public class SalesforceAccountResponse extends GenericResponse {
	

	@SerializedName("UtilityAccountNumber")
	private String utilityAccountNumber;
	
	@SerializedName("SiteName")
	private String siteName;

	@SerializedName("SiteID")
	private String siteId;
	
	@SerializedName("Message")
	private String message;
	
	@SerializedName("MessageDescription")
	private String statusDescription;
	
	@SerializedName("ContractAccountNumber")
	private String contractAccountNumber;

	@SerializedName("BusinessPartnerNumber")
	private String businessPartnerNumber;
	
	@SerializedName("AgreementIDStatus")
	private String agreementIDMatchStatus;
	
	@SerializedName("AccountMeterZip")
	private String meterZip;
	
	@SerializedName("AccountMeterState")
	private String meterState;
	
	@SerializedName("AccountMeterCity")
	private String meterCity;
	
	@SerializedName("AccountLastName")
	private String lLastName;
	
	@SerializedName("AccountFirstName")
	private String firstName;
	
	@SerializedName("AccountEmail")
	private String accountEmail;

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
	 * @return the siteId
	 */
	public String getSiteId() {
		return siteId;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the statusDescription
	 */
	public String getStatusDescription() {
		return statusDescription;
	}

	/**
	 * @param statusDescription the statusDescription to set
	 */
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
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
	 * @return the agreementIDMatchStatus
	 */
	public String getAgreementIDMatchStatus() {
		return agreementIDMatchStatus;
	}

	/**
	 * @param agreementIDMatchStatus the agreementIDMatchStatus to set
	 */
	public void setAgreementIDMatchStatus(String agreementIDMatchStatus) {
		this.agreementIDMatchStatus = agreementIDMatchStatus;
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
	 * @return the lLastName
	 */
	public String getlLastName() {
		return lLastName;
	}

	/**
	 * @param lLastName the lLastName to set
	 */
	public void setlLastName(String lLastName) {
		this.lLastName = lLastName;
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
	 * @return the accountEmail
	 */
	public String getAccountEmail() {
		return accountEmail;
	}

	/**
	 * @param accountEmail the accountEmail to set
	 */
	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}
}
