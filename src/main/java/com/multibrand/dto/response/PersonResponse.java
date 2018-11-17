package com.multibrand.dto.response;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class PersonResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String personId;
	private String firstName;
	private String lastName;
	private String dob;
	private String ssn;
	private String idType;
	private String idNumber;
	private String idStateOfIssue;
	private String contactTimeCode;
	private String phoneNum;
	private String email;
	private String additionalNameFirst;
	private String additionalNameLast;
	private String keepMeInformedFlag;
	private String contactByEmailFlag;
	private String contactByPhoneFlag;
	private String personStatus;
	private String titleName;
	private String languageCode;
	private String faxNumber;
	private String credLevelNum;
	private String credScoreNum;
	private String credSourceNum;
	private String middleName;
	private String businessPartnerId;
	private String enrollmentNumber;
	private String credStatusCode;
	private String credStatusDate;
	private String idocNumber;
	private String emailOptionRps;
	private String emailOptionSo;
	private String emailOptionEe;
	private String routingNumber;
	private String bankInstitutionName;
	private String ccType;
	private String ccExpiryMonth;
	private String ccExpiryYear;
	private String ccBillzip;
	private String accountNumber;
	private String accountName;
	private String bankAccountLastThree;
	private String drlFour;
	private String ssnFour;
	private String maidenName;
	private String posIdStatus;
	private String posIdDate;
	private String advActionData;
	private String retryCount;

	public PersonResponse() {

	}

	/**
	 * @return the personId
	 */
	public String getPersonId() {
		return personId;
	}

	/**
	 * @param personId
	 *            the personId to set
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
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
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}

	/**
	 * @param ssn
	 *            the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * @return the idType
	 */
	public String getIdType() {
		return idType;
	}

	/**
	 * @param idType
	 *            the idType to set
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * @param idNumber
	 *            the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @return the idStateOfIssue
	 */
	public String getIdStateOfIssue() {
		return idStateOfIssue;
	}

	/**
	 * @param idStateOfIssue
	 *            the idStateOfIssue to set
	 */
	public void setIdStateOfIssue(String idStateOfIssue) {
		this.idStateOfIssue = idStateOfIssue;
	}

	/**
	 * @return the contactTimeCode
	 */
	public String getContactTimeCode() {
		return contactTimeCode;
	}

	/**
	 * @param contactTimeCode
	 *            the contactTimeCode to set
	 */
	public void setContactTimeCode(String contactTimeCode) {
		this.contactTimeCode = contactTimeCode;
	}

	/**
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * @param phoneNum
	 *            the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the additionalNameFirst
	 */
	public String getAdditionalNameFirst() {
		return additionalNameFirst;
	}

	/**
	 * @param additionalNameFirst
	 *            the additionalNameFirst to set
	 */
	public void setAdditionalNameFirst(String additionalNameFirst) {
		this.additionalNameFirst = additionalNameFirst;
	}

	/**
	 * @return the additionalNameLast
	 */
	public String getAdditionalNameLast() {
		return additionalNameLast;
	}

	/**
	 * @param additionalNameLast
	 *            the additionalNameLast to set
	 */
	public void setAdditionalNameLast(String additionalNameLast) {
		this.additionalNameLast = additionalNameLast;
	}

	/**
	 * @return the keepMeInformedFlag
	 */
	public String getKeepMeInformedFlag() {
		return keepMeInformedFlag;
	}

	/**
	 * @param keepMeInformedFlag
	 *            the keepMeInformedFlag to set
	 */
	public void setKeepMeInformedFlag(String keepMeInformedFlag) {
		this.keepMeInformedFlag = keepMeInformedFlag;
	}

	/**
	 * @return the contactByEmailFlag
	 */
	public String getContactByEmailFlag() {
		return contactByEmailFlag;
	}

	/**
	 * @param contactByEmailFlag
	 *            the contactByEmailFlag to set
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
	 * @param contactByPhoneFlag
	 *            the contactByPhoneFlag to set
	 */
	public void setContactByPhoneFlag(String contactByPhoneFlag) {
		this.contactByPhoneFlag = contactByPhoneFlag;
	}

	/**
	 * @return the personStatus
	 */
	public String getPersonStatus() {
		return personStatus;
	}

	/**
	 * @param personStatus
	 *            the personStatus to set
	 */
	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}

	/**
	 * @return the titleName
	 */
	public String getTitleName() {
		return titleName;
	}

	/**
	 * @param titleName
	 *            the titleName to set
	 */
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	/**
	 * @return the languageCode
	 */
	public String getLanguageCode() {
		return languageCode;
	}

	/**
	 * @param languageCode
	 *            the languageCode to set
	 */
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	/**
	 * @return the faxNumber
	 */
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * @param faxNumber
	 *            the faxNumber to set
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	/**
	 * @return the credLevelNum
	 */
	public String getCredLevelNum() {
		return credLevelNum;
	}

	/**
	 * @param credLevelNum
	 *            the credLevelNum to set
	 */
	public void setCredLevelNum(String credLevelNum) {
		this.credLevelNum = credLevelNum;
	}

	/**
	 * @return the credScoreNum
	 */
	public String getCredScoreNum() {
		return credScoreNum;
	}

	/**
	 * @param credScoreNum
	 *            the credScoreNum to set
	 */
	public void setCredScoreNum(String credScoreNum) {
		this.credScoreNum = credScoreNum;
	}

	/**
	 * @return the credSourceNum
	 */
	public String getCredSourceNum() {
		return credSourceNum;
	}

	/**
	 * @param credSourceNum
	 *            the credSourceNum to set
	 */
	public void setCredSourceNum(String credSourceNum) {
		this.credSourceNum = credSourceNum;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the businessPartnerId
	 */
	public String getBusinessPartnerId() {
		return businessPartnerId;
	}

	/**
	 * @param businessPartnerId
	 *            the businessPartnerId to set
	 */
	public void setBusinessPartnerId(String businessPartnerId) {
		this.businessPartnerId = businessPartnerId;
	}

	/**
	 * @return the enrollmentNumber
	 */
	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}

	/**
	 * @param enrollmentNumber
	 *            the enrollmentNumber to set
	 */
	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}

	/**
	 * @return the credStatusCode
	 */
	public String getCredStatusCode() {
		return credStatusCode;
	}

	/**
	 * @param credStatusCode
	 *            the credStatusCode to set
	 */
	public void setCredStatusCode(String credStatusCode) {
		this.credStatusCode = credStatusCode;
	}

	/**
	 * @return the credStatusDate
	 */
	public String getCredStatusDate() {
		return credStatusDate;
	}

	/**
	 * @param credStatusDate
	 *            the credStatusDate to set
	 */
	public void setCredStatusDate(String credStatusDate) {
		this.credStatusDate = credStatusDate;
	}

	/**
	 * @return the idocNumber
	 */
	public String getIdocNumber() {
		return idocNumber;
	}

	/**
	 * @param idocNumber
	 *            the idocNumber to set
	 */
	public void setIdocNumber(String idocNumber) {
		this.idocNumber = idocNumber;
	}

	/**
	 * @return the emailOptionRps
	 */
	public String getEmailOptionRps() {
		return emailOptionRps;
	}

	/**
	 * @param emailOptionRps
	 *            the emailOptionRps to set
	 */
	public void setEmailOptionRps(String emailOptionRps) {
		this.emailOptionRps = emailOptionRps;
	}

	/**
	 * @return the emailOptionSo
	 */
	public String getEmailOptionSo() {
		return emailOptionSo;
	}

	/**
	 * @param emailOptionSo
	 *            the emailOptionSo to set
	 */
	public void setEmailOptionSo(String emailOptionSo) {
		this.emailOptionSo = emailOptionSo;
	}

	/**
	 * @return the emailOptionEe
	 */
	public String getEmailOptionEe() {
		return emailOptionEe;
	}

	/**
	 * @param emailOptionEe
	 *            the emailOptionEe to set
	 */
	public void setEmailOptionEe(String emailOptionEe) {
		this.emailOptionEe = emailOptionEe;
	}

	/**
	 * @return the routingNumber
	 */
	public String getRoutingNumber() {
		return routingNumber;
	}

	/**
	 * @param routingNumber
	 *            the routingNumber to set
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
	 * @param bankInstitutionName
	 *            the bankInstitutionName to set
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
	 * @param ccType
	 *            the ccType to set
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
	 * @param ccExpiryMonth
	 *            the ccExpiryMonth to set
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
	 * @param ccExpiryYear
	 *            the ccExpiryYear to set
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
	 * @param ccBillzip
	 *            the ccBillzip to set
	 */
	public void setCcBillzip(String ccBillzip) {
		this.ccBillzip = ccBillzip;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber
	 *            the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName
	 *            the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the bankAccountLastThree
	 */
	public String getBankAccountLastThree() {
		return bankAccountLastThree;
	}

	/**
	 * @param bankAccountLastThree
	 *            the bankAccountLastThree to set
	 */
	public void setBankAccountLastThree(String bankAccountLastThree) {
		this.bankAccountLastThree = bankAccountLastThree;
	}

	/**
	 * @return the drlFour
	 */
	public String getDrlFour() {
		return drlFour;
	}

	/**
	 * @param drlFour
	 *            the drlFour to set
	 */
	public void setDrlFour(String drlFour) {
		this.drlFour = drlFour;
	}

	/**
	 * @return the ssnFour
	 */
	public String getSsnFour() {
		return ssnFour;
	}

	/**
	 * @param ssnFour
	 *            the ssnFour to set
	 */
	public void setSsnFour(String ssnFour) {
		this.ssnFour = ssnFour;
	}

	/**
	 * @return the maidenName
	 */
	public String getMaidenName() {
		return maidenName;
	}

	/**
	 * @param maidenName
	 *            the maidenName to set
	 */
	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	/**
	 * @return the posIdStatus
	 */
	public String getPosIdStatus() {
		return posIdStatus;
	}

	/**
	 * @param posIdStatus
	 *            the posIdStatus to set
	 */
	public void setPosIdStatus(String posIdStatus) {
		this.posIdStatus = posIdStatus;
	}

	/**
	 * @return the posIdDate
	 */
	public String getPosIdDate() {
		return posIdDate;
	}

	/**
	 * @param posIdDate
	 *            the posIdDate to set
	 */
	public void setPosIdDate(String posIdDate) {
		this.posIdDate = posIdDate;
	}

	/**
	 * @return the advActionData
	 */
	public String getAdvActionData() {
		return advActionData;
	}

	/**
	 * @param advActionData
	 *            the advActionData to set
	 */
	public void setAdvActionData(String advActionData) {
		this.advActionData = advActionData;
	}

	/**
	 * @return the retryCount
	 */
	public String getRetryCount() {
		return retryCount;
	}

	/**
	 * @param retryCount
	 *            the retryCount to set
	 */
	public void setRetryCount(String retryCount) {
		this.retryCount = retryCount;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
