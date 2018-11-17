package com.multibrand.dto;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;

/**
 * 
 * @author vsood30
 *
 */
public class PersonDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -780709648179026606L;
	private String personID="";
	private String firstName="";
	private String middleName="";
	private String lastName="";
	private String maidenName="";
	private String fullName="";
	private String dateOfBirth="";
	private String ssn="";
	private String idType=""; //SSN or DL
	private String dlNumber="";
	private String stateOfIssue="";
	private String phoneNumber="";
	private String emailAddress="";
	private String languagePref="";	// ES or EN
	private String emailOptionRPS="";
	private String emailOptionSO="";
	private String emailOptionEE="";
	private String posIdStatus="";
	private String posIdDate="";
	private String tokenizedSSN="";
	private String tokenizedDL="";
	private ContactPreferencesDTO contactPrefDTO;
	private String posidDLDate="";	//for DL verify date @jsingh1
	private String posidSSNDate=""; //for SSN verify date @jsingh1
	
	public PersonDTO() {
		
	}
	

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
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

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
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


	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getIdType() {
		if(StringUtils.isBlank(idType))
			idType=Constants.DL;
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getDlNumber() {
		return dlNumber;
	}

	public void setDlNumber(String dlNumber) {
		this.dlNumber = dlNumber;
	}

	public String getStateOfIssue() {
		return stateOfIssue;
	}

	public void setStateOfIssue(String stateOfIssue) {
		this.stateOfIssue = stateOfIssue;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		phoneNumber = CommonUtil.getRawPhoneNumberFromFormatedPhone(phoneNumber);
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getLanguagePref() {
		return languagePref;
	}

	public void setLanguagePref(String languagePref) {
		this.languagePref = languagePref;
	}

	public String getEmailOptionRPS() {
		return emailOptionRPS;
	}

	public void setEmailOptionRPS(String emailOptionRPS) {
		this.emailOptionRPS = emailOptionRPS;
	}

	public String getEmailOptionSO() {
		return emailOptionSO;
	}

	public void setEmailOptionSO(String emailOptionSO) {
		this.emailOptionSO = emailOptionSO;
	}

	public String getEmailOptionEE() {
		return emailOptionEE;
	}

	public void setEmailOptionEE(String emailOptionEE) {
		this.emailOptionEE = emailOptionEE;
	}

	
	public String getPosIdStatus() {
		return posIdStatus;
	}

	public void setPosIdStatus(String posIdStatus) {
		this.posIdStatus = posIdStatus;
	}

	public String getPosIdDate() {
		return posIdDate;
	}

	public void setPosIdDate(String posIdDate) {
		this.posIdDate = posIdDate;
	}

	public String getTokenizedSSN() {
		return tokenizedSSN;
	}

	public void setTokenizedSSN(String tokenizedSSN) {
		this.tokenizedSSN = tokenizedSSN;
	}

	public String getTokenizedDL() {
		return tokenizedDL;
	}

	public void setTokenizedDL(String tokenizedDL) {
		this.tokenizedDL = tokenizedDL;
	}

	public ContactPreferencesDTO getContactPrefDTO() {
		if(null==contactPrefDTO){
			contactPrefDTO = new ContactPreferencesDTO();
			setContactPrefDTO(contactPrefDTO);
		}
		return contactPrefDTO;
	}

	public void setContactPrefDTO(ContactPreferencesDTO contactPrefDTO) {
		this.contactPrefDTO = contactPrefDTO;
	}

	public String getPosidDLDate() {
		return posidDLDate;
	}

	public void setPosidDLDate(String posidDLDate) {
		this.posidDLDate = posidDLDate;
	}

	public String getPosidSSNDate() {
		return posidSSNDate;
	}

	public void setPosidSSNDate(String posidSSNDate) {
		this.posidSSNDate = posidSSNDate;
	}
	
	public String getFullName() {
		StringBuffer buff = new StringBuffer();
		
			buff.append(firstName).append(' ')
			  .append(middleName).append(' ')
			  .append(lastName).append(' ')
			  .append(maidenName);
		
		fullName=buff.toString();
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}
	
	
}
