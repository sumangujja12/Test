package com.multibrand.dto;

import com.multibrand.dao.jdbc.sp.ProcedureInParameter;

public class GMDPersonDetailsDTO {
	
	private Integer personId;
	private String nameFirst ="";
	private String nameLast ="";
	private String dateOfBirth="";
	private String phoneNumber="";
	private String emailAddress="";
	private String languagePref="";	
	private String contactByEmailFlag="";
	private String contactByPhoneFlag="";
	private String personStatus="";
	private String keepMeInformedFlag="";
	private String idocNumber ="";
	private String businessPartnerNumber ="";
	private String middleName="";
	private String maidenName="";
	private String routingNumber="";
	private String bankInstitutionName="";
	private String ccType ="";
	private String ccExpiryMonth ="";
	private String ccExpiryYear ="";
	private String ccBillzip ="";
	private String tokenizedCCNumber ="";
	private String ccAccountName ="";
	private String autoPayFlag ="";
	private String tokenizedBankAccountNumber = "";
	
	
	/**
	 * @return the personId
	 */
	public Integer getPersonId() {
		return personId;
	}
	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	/**
	 * @return the nameFirst
	 */
	public String getNameFirst() {
		return nameFirst;
	}
	/**
	 * @param nameFirst the nameFirst to set
	 */
	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}
	/**
	 * @return the nameLast
	 */
	public String getNameLast() {
		return nameLast;
	}
	/**
	 * @param nameLast the nameLast to set
	 */
	public void setNameLast(String nameLast) {
		this.nameLast = nameLast;
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
	 * @return the languagePref
	 */
	public String getLanguagePref() {
		return languagePref;
	}
	/**
	 * @param languagePref the languagePref to set
	 */
	public void setLanguagePref(String languagePref) {
		this.languagePref = languagePref;
	}
	/**
	 * @return the contactByEmailFlag
	 */
	public String getContactByEmailFlag() {
		return contactByEmailFlag;
	}
	/**
	 * @param contactByEmailFlag the contactByEmailFlag to set
	 */
	public void setContactByEmailFlag(String contactByEmailFlag) {
		this.contactByEmailFlag = contactByEmailFlag;
	}
	/**
	 * @return the contactByPhoneFlag
	 */
	public String getContactByPhoneFlag() {
		return contactByPhoneFlag;
	}
	/**
	 * @param contactByPhoneFlag the contactByPhoneFlag to set
	 */
	public void setContactByPhoneFlag(String contactByPhoneFlag) {
		this.contactByPhoneFlag = contactByPhoneFlag;
	}
	/**
	 * @return the keepMeInformedFlag
	 */
	public String getKeepMeInformedFlag() {
		return keepMeInformedFlag;
	}
	/**
	 * @param keepMeInformedFlag the keepMeInformedFlag to set
	 */
	public void setKeepMeInformedFlag(String keepMeInformedFlag) {
		this.keepMeInformedFlag = keepMeInformedFlag;
	}
	/**
	 * @return the personStatus
	 */
	public String getPersonStatus() {
		return personStatus;
	}
	/**
	 * @param personStatus the personStatus to set
	 */
	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}
	/**
	 * @return the idocNumber
	 */
	public String getIdocNumber() {
		return idocNumber;
	}
	/**
	 * @param idocNumber the idocNumber to set
	 */
	public void setIdocNumber(String idocNumber) {
		this.idocNumber = idocNumber;
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
	 * @return the routingNumber
	 */
	public String getRoutingNumber() {
		return routingNumber;
	}
	/**
	 * @param routingNumber the routingNumber to set
	 */
	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}
	/**
	 * @return the bankInstitutionName
	 */
	public String getBankInstitutionName() {
		return bankInstitutionName;
	}
	/**
	 * @param bankInstitutionName the bankInstitutionName to set
	 */
	public void setBankInstitutionName(String bankInstitutionName) {
		this.bankInstitutionName = bankInstitutionName;
	}
	/**
	 * @return the ccType
	 */
	public String getCcType() {
		return ccType;
	}
	/**
	 * @param ccType the ccType to set
	 */
	public void setCcType(String ccType) {
		this.ccType = ccType;
	}
	/**
	 * @return the ccExpiryMonth
	 */
	public String getCcExpiryMonth() {
		return ccExpiryMonth;
	}
	/**
	 * @param ccExpiryMonth the ccExpiryMonth to set
	 */
	public void setCcExpiryMonth(String ccExpiryMonth) {
		this.ccExpiryMonth = ccExpiryMonth;
	}
	/**
	 * @return the ccExpiryYear
	 */
	public String getCcExpiryYear() {
		return ccExpiryYear;
	}
	/**
	 * @param ccExpiryYear the ccExpiryYear to set
	 */
	public void setCcExpiryYear(String ccExpiryYear) {
		this.ccExpiryYear = ccExpiryYear;
	}
	/**
	 * @return the ccBillzip
	 */
	public String getCcBillzip() {
		return ccBillzip;
	}
	/**
	 * @param ccBillzip the ccBillzip to set
	 */
	public void setCcBillzip(String ccBillzip) {
		this.ccBillzip = ccBillzip;
	}
	/**
	 * @return the tokenizedCCNumber
	 */
	public String getTokenizedCCNumber() {
		return tokenizedCCNumber;
	}
	/**
	 * @param tokenizedCCNumber the tokenizedCCNumber to set
	 */
	public void setTokenizedCCNumber(String tokenizedCCNumber) {
		this.tokenizedCCNumber = tokenizedCCNumber;
	}
	/**
	 * @return the ccAccountName
	 */
	public String getCcAccountName() {
		return ccAccountName;
	}
	/**
	 * @param ccAccountName the ccAccountName to set
	 */
	public void setCcAccountName(String ccAccountName) {
		this.ccAccountName = ccAccountName;
	}
	/**
	 * @return the autoPayFlag
	 */
	public String getAutoPayFlag() {
		return autoPayFlag;
	}
	/**
	 * @param autoPayFlag the autoPayFlag to set
	 */
	public void setAutoPayFlag(String autoPayFlag) {
		this.autoPayFlag = autoPayFlag;
	}
	/**
	 * @return the tokenizedBankAccountNumber
	 */
	public String getTokenizedBankAccountNumber() {
		return tokenizedBankAccountNumber;
	}
	/**
	 * @param tokenizedBankAccountNumber the tokenizedBankAccountNumber to set
	 */
	public void setTokenizedBankAccountNumber(String tokenizedBankAccountNumber) {
		this.tokenizedBankAccountNumber = tokenizedBankAccountNumber;
	}
	
	
	

}
