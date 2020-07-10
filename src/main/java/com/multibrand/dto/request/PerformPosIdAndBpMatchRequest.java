package com.multibrand.dto.request;



import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.multibrand.request.validation.MandotoryFieldCheck;
import com.multibrand.request.validation.FormatConstraint;
import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.RepetitiveDigitCheck;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.request.validation.ValidDateTime;
import com.multibrand.util.CommonUtil;

/**
 * @author jsingh1
 */

@MandotoryFieldCheck(fields = {"ssn", "tdl"}, message = "please provide valid ssn or dl")
public class PerformPosIdAndBpMatchRequest extends BaseAffiliateRequest {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@Length(max = 40, groups = SizeConstraint.class)
	String lastName;
	
	@NotEmpty
	@Length(max = 40, groups = SizeConstraint.class)
	String firstName;
	
	@Length(max = 1,groups = SizeConstraint.class)
	String middleName;
	
	@NotEmpty
	@ValidDateTime(format = "MMddyyyy", groups = FormatConstraint.class, message = "must be in MMddyyyy format",messageCode="INVALID_DOB",messageCodeText="INVALID_DOB")
	String dob;
	
	@RepetitiveDigitCheck
	String tdl;
	@RepetitiveDigitCheck
	String ssn ;
	
	@Length(max = 40,groups = SizeConstraint.class)
	String maidenName; 
	
	String trackingId;
	
	@NotEmpty
	@Length(max = 100, groups = SizeConstraint.class)
	@Email(groups = FormatConstraint.class)
	String email;
	
	@NotEmpty
	@Length(max = 10, groups = SizeConstraint.class)
	@Pattern(regexp = "\\d{10}", groups = FormatConstraint.class, message="is invalid")
	String phoneNum;
	
	@Length(max = 1, groups = SizeConstraint.class)
	String mktPref;
	
	String transactionType;
	
	@NotEmpty
	@Length(max = 10, groups = SizeConstraint.class)
	private String servStreetNum;

	@NotEmpty
	@Length(max = 60, groups = SizeConstraint.class)
	private String servStreetName;

	@Length(max = 10, groups = SizeConstraint.class)
	private String servStreetAptNum;
	
	@NotEmpty
	@Length(max = 30, groups = SizeConstraint.class)
	String servCity;
	
	@NotEmpty
	@Length(min = 2, max = 2, groups = SizeConstraint.class)
	String servState;
	
	@NotEmpty
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
	
	@Length(max = 20, groups = SizeConstraint.class)
	String tokenizedTDL;
	
	@Length(max = 20, groups = SizeConstraint.class)
	String tokenizedSSN;
	
	String dobForPosId;
	
	//START : OE :Sprint61 :US21009 :Kdeshmu1
	String agentID;
	
	String ipAddress;
	
	
	//Start : OE : Sprint3 : 13643 - Add Missing Columns to  SLA table :Kdeshmu1
	String entryPoint ;
	
	String tpvStatus ;
	String campaignCd ;
	String pageRevisited ;
	String prospectId;
	String bypassPosid;
	String tabletId;
	String abandonedEnrollStatFlag;
	String etfFlag;
	
	//Start : OE : Sprint3 : 13643 - Add Missing Columns to  SLA table :Kdeshmu1
	@Length(max = 10, groups = SizeConstraint.class)
	String partnerId;
	
	@Length(max = 40, groups = SizeConstraint.class)
	String partnerName;
	
	@Length(max = 10, groups = SizeConstraint.class)
	String locationId;
	
	@Length(max = 40, groups = SizeConstraint.class)
	String locationName;
	
	String guid;
	String noid;
	String etf;
	
	
	public String getEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}


	public String getTpvStatus() {
		return tpvStatus;
	}

	public void setTpvStatus(String tpvStatus) {
		this.tpvStatus = tpvStatus;
	}

	public String getCampaignCd() {
		return campaignCd;
	}

	public void setCampaignCd(String campaignCd) {
		this.campaignCd = campaignCd;
	}

	public String getPageRevisited() {
		return pageRevisited;
	}

	public void setPageRevisited(String pageRevisited) {
		this.pageRevisited = pageRevisited;
	}

	public String getProspectId() {
		return prospectId;
	}

	public void setProspectId(String prospectId) {
		this.prospectId = prospectId;
	}


	public String getBypassPosid() {
		return bypassPosid;
	}

	public void setBypassPosid(String bypassPosid) {
		this.bypassPosid = bypassPosid;
	}

	public String getTabletId() {
		return tabletId;
	}

	public void setTabletId(String tabletId) {
		this.tabletId = tabletId;
	}

	public String getAgentID() {
		return agentID;
	}

	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}
	

	//END : OE :Sprint61 :US21009 :Kdeshmu1
	
	
	public String getAbandonedEnrollStatFlag() {
		return abandonedEnrollStatFlag;
	}

	public void setAbandonedEnrollStatFlag(String abandonedEnrollStatFlag) {
		this.abandonedEnrollStatFlag = abandonedEnrollStatFlag;
	}


	public String getEtfFlag() {
		return etfFlag;
	}

	public void setEtfFlag(String etfFlag) {
		this.etfFlag = etfFlag;
	}


	public String getDobForPosId() {
		return dobForPosId;
	}

	public void setDobForPosId(String dobForPosId) {
		this.dobForPosId = dobForPosId;
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

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
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

	public String getEtf() {
		return etf;
	}

	public void setEtf(String etf) {
		this.etf = etf;
	}

	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}

	
}
