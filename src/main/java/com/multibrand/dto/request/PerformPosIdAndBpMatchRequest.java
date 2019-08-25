package com.multibrand.dto.request;



import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.FormatConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.request.validation.ValidDateTime;
import com.multibrand.util.CommonUtil;

/**
 * @author jsingh1
 */
public class PerformPosIdAndBpMatchRequest extends BaseAffiliateRequest {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 40, groups = SizeConstraint.class)
	String lastName;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 40, groups = SizeConstraint.class)
	String firstName;
	
	@Length(max = 1,groups = SizeConstraint.class)
	String middleName;
	
	@NotBlank(groups = BasicConstraint.class)
	@ValidDateTime(format = "MMddyyyy", groups = FormatConstraint.class, message = "must be in MMddyyyy format",messageCode="INVALID_DOB",messageCodeText="INVALID_DOB")
	String dob;
	
	String tdl;
	String ssn ;
	
	@Length(max = 40,groups = SizeConstraint.class)
	String maidenName; 
	
	String trackingId;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 100, groups = SizeConstraint.class)
	@Email(groups = FormatConstraint.class)
	String email;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 10, groups = SizeConstraint.class)
	@Pattern(regexp = "\\d{10}", groups = FormatConstraint.class, message="is invalid")
	String phoneNum;
	
	@Length(max = 1, groups = SizeConstraint.class)
	String mktPref;
	
	String transactionType;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 10, groups = SizeConstraint.class)
	private String servStreetNum;

	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 60, groups = SizeConstraint.class)
	private String servStreetName;

	@Length(max = 10, groups = SizeConstraint.class)
	private String servStreetAptNum;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 30, groups = SizeConstraint.class)
	String servCity;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(min = 2, max = 2, groups = SizeConstraint.class)
	String servState;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 10, groups = SizeConstraint.class)
	String servZipCode;
	
	@Length(max = 10, groups = SizeConstraint.class)
	String billStreetNum;
	
	@Length(max = 60, groups = SizeConstraint.class)
	String billStreetName;
	
	@Length(max = 10, groups = SizeConstraint.class)
	String billStreetAptNum;
	
	@Length(max = 30, groups = SizeConstraint.class)
	String billCity;
	
	@Length(min = 2, max = 2, groups = SizeConstraint.class)
	String billState;
	
	@Length(max = 10, groups = SizeConstraint.class)
	String billZipCode;
	
	@Length(max = 25, groups = SizeConstraint.class)
	String billPOBox;
	String preferredLanguage;
	String tokenTDL;
	
	@Length(max = 20, groups = SizeConstraint.class)
	String tokenSSN;
	String dobForPosId;
	
	//START : OE :Sprint61 :US21009 :Kdeshmu1
	String agentID;
	
	
	public String getAgentID() {
		return agentID;
	}

	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}
	

	//END : OE :Sprint61 :US21009 :Kdeshmu1
	public String getDobForPosId() {
		return dobForPosId;
	}

	public void setDobForPosId(String dobForPosId) {
		this.dobForPosId = dobForPosId;
	}

	@Length(max = 20, groups = SizeConstraint.class)
	public String getTokenTDL() {
		return tokenTDL;
	}

	public void setTokenTDL(String tokenTDL) {
		this.tokenTDL = tokenTDL;
	}

	public String getTokenSSN() {
		return tokenSSN;
	}

	public void setTokenSSN(String tokenSSN) {
		this.tokenSSN = tokenSSN;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getTdl() {
		return tdl;
	}

	public void setTdl(String tdl) {
		this.tdl = tdl;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getMktPref() {
		return mktPref;
	}

	public void setMktPref(String mktPref) {
		this.mktPref = mktPref;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getServStreetNum() {
		return servStreetNum;
	}

	public void setServStreetNum(String servStreetNum) {
		this.servStreetNum = servStreetNum;
	}

	public String getServStreetName() {
		return servStreetName;
	}

	public void setServStreetName(String servStreetName) {
		this.servStreetName = servStreetName;
	}

	public String getServStreetAptNum() {
		return servStreetAptNum;
	}

	public void setServStreetAptNum(String servStreetAptNum) {
		this.servStreetAptNum = servStreetAptNum;
	}

	public String getServCity() {
		return servCity;
	}

	public void setServCity(String servCity) {
		this.servCity = servCity;
	}

	public String getServState() {
		return servState;
	}

	public void setServState(String servState) {
		this.servState = servState;
	}

	public String getServZipCode() {
		return servZipCode;
	}

	public void setServZipCode(String servZipCode) {
		this.servZipCode = servZipCode;
	}

	public String getBillStreetNum() {
		return billStreetNum;
	}

	public void setBillStreetNum(String billStreetNum) {
		this.billStreetNum = billStreetNum;
	}

	public String getBillStreetName() {
		return billStreetName;
	}

	public void setBillStreetName(String billStreetName) {
		this.billStreetName = billStreetName;
	}

	public String getBillStreetAptNum() {
		return billStreetAptNum;
	}

	public void setBillStreetAptNum(String billStreetAptNum) {
		this.billStreetAptNum = billStreetAptNum;
	}

	public String getBillCity() {
		return billCity;
	}

	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}

	public String getBillState() {
		return billState;
	}

	public void setBillState(String billState) {
		this.billState = billState;
	}

	public String getBillZipCode() {
		return billZipCode;
	}

	public void setBillZipCode(String billZipCode) {
		this.billZipCode = billZipCode;
	}

	public String getBillPOBox() {
		return billPOBox;
	}

	public void setBillPOBox(String billPOBox) {
		this.billPOBox = billPOBox;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}

	
}
