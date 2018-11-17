package com.multibrand.dto.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.multibrand.dao.jdbc.sp.Procedure;
import com.multibrand.dao.jdbc.sp.ProcedureInParameter;
import com.multibrand.dao.jdbc.sp.ProcedureOutParameter;

@Procedure(procedureMessageSourceId = "dbProcedureMappingMsgSourceId", value = "sp.add.person.affiliate")
public class AddPersonRequest implements FormEntityRequest, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_person_id", parameterIndex = 1)
	private String personId;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_name_first", parameterIndex = 2)
	private String firstName;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_name_last", parameterIndex = 3)
	private String lastName;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_date_of_birth", parameterIndex = 4)
	private String dob;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_ssn", parameterIndex = 5)
	private String ssn;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_id_cd", parameterIndex = 6)
	private String idType;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_id_number", parameterIndex = 7)
	private String idNumber;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_id_state_of_issue", parameterIndex = 8)
	private String idStateOfIssue;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_phone_number", parameterIndex = 9)
	private String phoneNum;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_address_email", parameterIndex = 10)
	private String email;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_language_cd", parameterIndex = 11)
	private String languageCode;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_name_middle", parameterIndex = 12)
	private String middleName;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_email_option_rps", parameterIndex = 13)
	private String emailOptionRps;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_email_option_so", parameterIndex = 14)
	private String emailOptionSo;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_email_option_ee", parameterIndex = 15)
	private String emailOptionEe;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_drl_four", parameterIndex = 16)
	private String drlFour;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_ssn_four", parameterIndex = 17)
	private String ssnFour;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_name_maiden", parameterIndex = 18)
	private String maidenName;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_posid_status", parameterIndex = 19)
	private String posIdStatus;

	@ProcedureInParameter(name = "sp.add.person.affiliate.in.param.in_posid_date", parameterIndex = 20)
	private String posIdDate;

	@ProcedureOutParameter(name = "sp.add.person.affiliate.out.param.out_error_code", parameterIndex = 21)
	private String errorCode;

	public AddPersonRequest() {

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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
