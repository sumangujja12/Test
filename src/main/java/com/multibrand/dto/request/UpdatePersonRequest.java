package com.multibrand.dto.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.multibrand.dao.jdbc.sp.Procedure;
import com.multibrand.dao.jdbc.sp.ProcedureInParameter;
import com.multibrand.dao.jdbc.sp.ProcedureOutParameter;

@Procedure(procedureMessageSourceId = "dbProcedureMappingMsgSourceId", value = "sp.update.person.affiliate")
public class UpdatePersonRequest implements FormEntityRequest, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_person_id", parameterIndex = 1)
	private String personId;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_name_first", parameterIndex = 2)
	private String firstName;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_name_last", parameterIndex = 3)
	private String lastName;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_date_of_birth", parameterIndex = 4)
	private String dob;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_ssn", parameterIndex = 5)
	private String ssn;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_id_cd", parameterIndex = 6)
	private String idType;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_id_number", parameterIndex = 7)
	private String idNumber;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_id_state_of_issue", parameterIndex = 8)
	private String idStateOfIssue;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_contact_time_cd", parameterIndex = 9)
	private String contactTimeCode;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_phone_number", parameterIndex = 10)
	private String phoneNum;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_address_email", parameterIndex = 11)
	private String email;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_additional_name_first", parameterIndex = 12)
	private String additionalNameFirst;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_additional_name_last", parameterIndex = 13)
	private String additionalNameLast;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_keep_me_informed_flag", parameterIndex = 14)
	private String keepMeInformedFlag;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_contact_by_email_flag", parameterIndex = 15)
	private String contactByEmailFlag;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_contact_by_phone_flag", parameterIndex = 16)
	private String contactByPhoneFlag;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_person_status", parameterIndex = 17)
	private String personStatus;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_name_title", parameterIndex = 18)
	private String titleName;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_language_cd", parameterIndex = 19)
	private String languageCode;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_fax_number", parameterIndex = 20)
	private String faxNumber;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_cred_level_num", parameterIndex = 21)
	private String credLevelNum;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_cred_score_num", parameterIndex = 22)
	private String credScoreNum;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_cred_source_num", parameterIndex = 23)
	private String credSourceNum;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_name_middle", parameterIndex = 24)
	private String middleName;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_business_partner_id", parameterIndex = 25)
	private String businessPartnerId;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_enrollment_number", parameterIndex = 26)
	private String enrollmentNumber;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_cred_status_cd", parameterIndex = 27)
	private String credStatusCode;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_cred_status_date", parameterIndex = 28)
	private String credStatusDate;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_idoc_number", parameterIndex = 29)
	private String idocNumber;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_email_option_rps", parameterIndex = 30)
	private String emailOptionRps;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_email_option_so", parameterIndex = 31)
	private String emailOptionSo;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_email_option_ee", parameterIndex = 32)
	private String emailOptionEe;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_routing_number", parameterIndex = 33)
	private String routingNumber;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_banking_institution_name", parameterIndex = 34)
	private String bankInstitutionName;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_cc_type", parameterIndex = 35)
	private String ccType;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_cc_expiry_month", parameterIndex = 36)
	private String ccExpiryMonth;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_cc_expiry_year", parameterIndex = 37)
	private String ccExpiryYear;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_cc_billzip", parameterIndex = 38)
	private String ccBillzip;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_account_number", parameterIndex = 39)
	private String accountNumber;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_account_name", parameterIndex = 40)
	private String accountName;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_bankaccount_last_three", parameterIndex = 41)
	private String bankAccountLastThree;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_drl_four", parameterIndex = 42)
	private String drlFour;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_ssn_four", parameterIndex = 43)
	private String ssnFour;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_name_maiden", parameterIndex = 44)
	private String maidenName;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_posid_status", parameterIndex = 45)
	private String posIdStatus;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_posid_date", parameterIndex = 46)
	private String posIdDate;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_adv_action_data", parameterIndex = 47)
	private String advActionData;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_retry_count", parameterIndex = 48)
	private String retryCount;
	
	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_noid", parameterIndex = 49)
	private String noid;
	
	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_cred_score_date", parameterIndex = 50)
	private String creditScoreDate;

	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_dep_waive_flag", parameterIndex = 51)
	private String depWaiveFlag;
	
	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_dep_amt_waived_proc", parameterIndex = 52)
	private String depAmtWaivedProc;
	
	@ProcedureInParameter(name = "sp.update.person.affiliate.in.param.in_dep_amt_waived", parameterIndex = 53)
	private String depAmtWaived;
	
	@ProcedureOutParameter(name = "sp.update.person.affiliate.out.param.out_error_code", parameterIndex = 54)
	private String errorCode;
	
	private String esuiteFlag;
	private String offerCategory;
	private String paymentMethod;
	private String trackingId;

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

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the esuiteFlag
	 */
	public String getEsuiteFlag() {
		return esuiteFlag;
	}

	/**
	 * @param esuiteFlag
	 *            the esuiteFlag to set
	 */
	public void setEsuiteFlag(String esuiteFlag) {
		this.esuiteFlag = esuiteFlag;
	}

	/**
	 * @return the offerCategory
	 */
	public String getOfferCategory() {
		return offerCategory;
	}

	/**
	 * @param offerCategory
	 *            the offerCategory to set
	 */
	public void setOfferCategory(String offerCategory) {
		this.offerCategory = offerCategory;
	}

	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod
	 *            the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return the trackingId
	 */
	public String getTrackingId() {
		return trackingId;
	}

	/**
	 * @param trackingId
	 *            the trackingId to set
	 */
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	
	public String getNoid() {
		return noid;
	}

	public void setNoid(String noid) {
		this.noid = noid;
	}

	
	public String getDepWaiveFlag() {
		return depWaiveFlag;
	}

	public void setDepWaiveFlag(String depWaiveFlag) {
		this.depWaiveFlag = depWaiveFlag;
	}

	public String getDepAmtWaivedProc() {
		return depAmtWaivedProc;
	}

	public void setDepAmtWaivedProc(String depAmtWaivedProc) {
		this.depAmtWaivedProc = depAmtWaivedProc;
	}

	public String getDepAmtWaived() {
		return depAmtWaived;
	}

	public void setDepAmtWaived(String depAmtWaived) {
		this.depAmtWaived = depAmtWaived;
	}

	public String getCreditScoreDate() {
		return creditScoreDate;
	}

	public void setCreditScoreDate(String creditScoreDate) {
		this.creditScoreDate = creditScoreDate;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
