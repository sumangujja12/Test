package com.multibrand.dto.request;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.multibrand.request.validation.FormatConstraint;
import com.multibrand.request.validation.MandotoryFieldCheck;
import com.multibrand.request.validation.NoidCheck;
import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.request.validation.ValidAge;
import com.multibrand.request.validation.ValidDateTime;

@MandotoryFieldCheck(fields = {"tokenizedSSN", "tokenizedTDL", "noid"}, message = "please provide ssn or dl or noid")
@NoidCheck
public class IdentityRequest extends SalesBaseRequest {

	private static final long serialVersionUID = 1068351198219357955L;
	
	@NotEmpty
	@Size(max = 40, groups = SizeConstraint.class)
	String lastName;
	
	@NotEmpty
	@Size(max = 40, groups = SizeConstraint.class)
	String firstName;
	
	@Size(max = 40 ,groups = SizeConstraint.class)
	String middleName;

	@NotEmpty
	@ValidDateTime(format = "MMddyyyy", groups = FormatConstraint.class, message = "must be in MMddyyyy format",messageCode="INVALID_DOB",messageCodeText="INVALID_DOB")
	@ValidAge
	String dob;
	
	@Size(max = 40,groups = SizeConstraint.class)
	String maidenName; 
	
	String trackingId;
	
	@NotEmpty
	@Size(max = 100, groups = SizeConstraint.class)
	@Email(groups = FormatConstraint.class)
	String email;
	
	@NotEmpty
	@Size(max = 10, groups = SizeConstraint.class)
	@Pattern(regexp = "\\d{10}", groups = FormatConstraint.class, message="is invalid")
	String phoneNum;
	
	@Size(max = 1, groups = SizeConstraint.class)
	String mktPref;
	
	String transactionType;
	
	@NotEmpty
	@Size(max = 10, groups = SizeConstraint.class)
	private String servStreetNum;

	@NotEmpty
	@Size(max = 60, groups = SizeConstraint.class)
	private String servStreetName;

	@Size(max = 10, groups = SizeConstraint.class)
	private String servStreetAptNum;
	
	@NotEmpty
	@Size(max = 30, groups = SizeConstraint.class)
	String servCity;
	
	@NotEmpty
	@Size(min = 2, max = 2, groups = SizeConstraint.class)
	String servState;
	
	@NotEmpty
	@Size(max = 10, groups = SizeConstraint.class)
	String servZipCode;
	
	@Size(max = 10, groups = SizeConstraint.class)
	String billStreetNum;
	
	@Size(max = 60, groups = SizeConstraint.class)
	String billStreetName;
	
	@Size(max = 10, groups = SizeConstraint.class)
	String billStreetAptNum;
	
	@Size(max = 30, groups = SizeConstraint.class)
	String billCity;
	
	@Size(min = 2, max = 2, groups = SizeConstraint.class)
	String billState;
	
	@Size(max = 10, groups = SizeConstraint.class)
	String billZipCode;
	
	@Size(max = 25, groups = SizeConstraint.class)
	String billPOBox;
	
	String preferredLanguage;
	
	
	@Size(max = 20, groups = SizeConstraint.class)
	String tokenizedTDL;
	
	
	@Size(max = 20, groups = SizeConstraint.class)
	String tokenizedSSN;
	
	String agentID;
	
	String ipAddress;
	
	String entryPoint ;

	String prospectId;
	String tabletId;
	String etfFlag;
	
	//Start : OE : Sprint3 : 13643 - Add Missing Columns to  SLA table :Kdeshmu1
	@Size(max = 10, groups = SizeConstraint.class)
	String partnerId;
	
	@Size(max = 40, groups = SizeConstraint.class)
	String partnerName;
	
	@Size(max = 10, groups = SizeConstraint.class)
	String locationId;
	
	@Size(max = 40, groups = SizeConstraint.class)
	String locationName;
	
	String guid;
	
	String noid;


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

	public String getTokenizedTDL() {
		return tokenizedTDL;
	}
	public void setTokenizedTDL(String tokenizedTDL) {
		this.tokenizedTDL = tokenizedTDL;
	}
	public String getTokenizedSSN() {
		return tokenizedSSN;
	}
	public void setTokenizedSSN(String tokenizedSSN) {
		this.tokenizedSSN = tokenizedSSN;
	}
	public String getAgentID() {
		return agentID;
	}
	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getEntryPoint() {
		return entryPoint;
	}
	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}
	public String getProspectId() {
		return prospectId;
	}
	public void setProspectId(String prospectId) {
		this.prospectId = prospectId;
	}
	public String getTabletId() {
		return tabletId;
	}
	public void setTabletId(String tabletId) {
		this.tabletId = tabletId;
	}

	public String getEtfFlag() {
		return etfFlag;
	}
	public void setEtfFlag(String etfFlag) {
		this.etfFlag = etfFlag;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getNoid() {
		return noid;
	}
	public void setNoid(String noid) {
		this.noid = noid;
	}
	
}
