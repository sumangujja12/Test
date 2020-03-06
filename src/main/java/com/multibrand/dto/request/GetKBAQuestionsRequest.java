package com.multibrand.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.FormatConstraint;
import com.multibrand.request.validation.SizeConstraint;


public class GetKBAQuestionsRequest extends BaseAffiliateRequest implements Serializable {

	/**
	 * 
	 */
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
	String dob;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 100, groups = SizeConstraint.class)
	@Email(groups = FormatConstraint.class)
	String email;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 10, groups = SizeConstraint.class)
	@Pattern(regexp = "\\d{10}", groups = FormatConstraint.class, message="is invalid")
	String phoneNum;
	
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
	
	@Length(max = 25, groups = SizeConstraint.class)
	String tokenTDL;
	
	@Length(max = 20, groups = SizeConstraint.class)
	String tokenSSN;
	
	private String drivingLicenseState;
	private String esid;
	private String transactionType;
	
	private String middleNameInitials;
	private String ipAddress;
	private String posidHoldFlag;
	private String posidUniqueKey;
	
	
	public String getPosidHoldFlag() {
		return posidHoldFlag;
	}
	public void setPosidHoldFlag(String posidHoldFlag) {
		this.posidHoldFlag = posidHoldFlag;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getDrivingLicenseState() {
		return drivingLicenseState;
	}
	public void setDrivingLicenseState(String drivingLicenseState) {
		this.drivingLicenseState = drivingLicenseState;
	}
	
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
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
	public String getMiddleNameInitials() {
		return middleNameInitials;
	}
	public void setMiddleNameInitials(String middleNameInitials) {
		this.middleNameInitials = middleNameInitials;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
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
	public String getEsid() {
		return esid;
	}
	public void setEsid(String esid) {
		this.esid = esid;
	}
	public String getPosidUniqueKey() {
		return posidUniqueKey;
	}
	public void setPosidUniqueKey(String posidUniqueKey) {
		this.posidUniqueKey = posidUniqueKey;
	}
	
	
	
}
