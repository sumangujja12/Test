package com.multibrand.vo.request;

public class EnrollmentReportDataRequest {
	
	private String guid;
	private String trackingId;
	private String prospectId;
	private String tdspCode;
	private String vendorCode;
	private String vendorName;
	private String agentId;
	private String partnerId;
	private String locationId;
	private String accountLastName;
	private String accountFirstName;
	private String depositHold;
	private String esid;
	private String planName;
	private String contractTerm;
	private String cancelFee;
	private String serviceAddressStreet;
	private String serviceAddressUnit;
	private String servicAddresseCity;
	private String serviceAddressState;
	private String serviceAddressZip;
	private String sameServiceBillAddressFlag;
	private String billingAddressStreet;
	private String billingAddressUnit;
	private String billingAddressCity;
	private String billingAddressState;
	private String billingAddressZip;
	private String serviceStartDate;
	private String creationDateTimestamp;
	private String promoCode;
	private String offerCode;
	private String offerTeaser;
	private String contractAccountNumber;
	private String deviceLatitude;
	private String deviceLongitude;
	private String deviceLocationAccuracy;
	private String enrollmentStatus;
	private String tabletID;
	private String agentTypeDescription;
	//Start : US15851 | Send Phone Number and Email Address | HChoudhary
	private String phoneNumber;
	private String emailAddress;
	//End : US15851 | Send Phone Number and Email Address | HChoudhary
	//Start : OE :Sprint54| US19044 | kdeshmu1
	private String kbaDecision;
	private String kbaReasonCodes;
	//End : OE :Sprint54| US19044 | kdeshmu1
	private String dateOfBirth;
	private String enrollmentType;
	private String deviceType;
	

	
	public String getGuid() {
		return guid;
	}
	public String getKbaDecision() {
		return kbaDecision;
	}
	public void setKbaDecision(String kbaDecision) {
		this.kbaDecision = kbaDecision;
	}
	public String getKbaReasonCodes() {
		return kbaReasonCodes;
	}
	public void setKbaReasonCodes(String kbaReasonCodes) {
		this.kbaReasonCodes = kbaReasonCodes;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	public String getProspectId() {
		return prospectId;
	}
	public void setProspectId(String prospectId) {
		this.prospectId = prospectId;
	}
	public String getTdspCode() {
		return tdspCode;
	}
	public void setTdspCode(String tdspCode) {
		this.tdspCode = tdspCode;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
		public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getAccountLastName() {
		return accountLastName;
	}
	public void setAccountLastName(String accountLastName) {
		this.accountLastName = accountLastName;
	}
	public String getAccountFirstName() {
		return accountFirstName;
	}
	public void setAccountFirstName(String accountFirstName) {
		this.accountFirstName = accountFirstName;
	}
	public String getDepositHold() {
		return depositHold;
	}
	public void setDepositHold(String depositHold) {
		this.depositHold = depositHold;
	}
	public String getEsid() {
		return esid;
	}
	public void setEsid(String esid) {
		this.esid = esid;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getContractTerm() {
		return contractTerm;
	}
	public void setContractTerm(String contractTerm) {
		this.contractTerm = contractTerm;
	}
	public String getCancelFee() {
		return cancelFee;
	}
	public void setCancelFee(String cancelFee) {
		this.cancelFee = cancelFee;
	}
	public String getServiceAddressStreet() {
		return serviceAddressStreet;
	}
	public void setServiceAddressStreet(String serviceAddressStreet) {
		this.serviceAddressStreet = serviceAddressStreet;
	}
	public String getServiceAddressUnit() {
		return serviceAddressUnit;
	}
	public void setServiceAddressUnit(String serviceAddressUnit) {
		this.serviceAddressUnit = serviceAddressUnit;
	}
	
	
	public String getServicAddresseCity() {
		return servicAddresseCity;
	}
	public void setServicAddresseCity(String servicAddresseCity) {
		this.servicAddresseCity = servicAddresseCity;
	}
	public String getServiceAddressState() {
		return serviceAddressState;
	}
	public void setServiceAddressState(String serviceAddressState) {
		this.serviceAddressState = serviceAddressState;
	}
	public String getServiceAddressZip() {
		return serviceAddressZip;
	}
	public void setServiceAddressZip(String serviceAddressZip) {
		this.serviceAddressZip = serviceAddressZip;
	}
	public String getSameServiceBillAddressFlag() {
		return sameServiceBillAddressFlag;
	}
	public void setSameServiceBillAddressFlag(String sameServiceBillAddressFlag) {
		this.sameServiceBillAddressFlag = sameServiceBillAddressFlag;
	}
	public String getBillingAddressStreet() {
		return billingAddressStreet;
	}
	public void setBillingAddressStreet(String billingAddressStreet) {
		this.billingAddressStreet = billingAddressStreet;
	}
	public String getBillingAddressUnit() {
		return billingAddressUnit;
	}
	public void setBillingAddressUnit(String billingAddressUnit) {
		this.billingAddressUnit = billingAddressUnit;
	}
	public String getBillingAddressCity() {
		return billingAddressCity;
	}
	public void setBillingAddressCity(String billingAddressCity) {
		this.billingAddressCity = billingAddressCity;
	}
	public String getBillingAddressState() {
		return billingAddressState;
	}
	public void setBillingAddressState(String billingAddressState) {
		this.billingAddressState = billingAddressState;
	}
	public String getBillingAddressZip() {
		return billingAddressZip;
	}
	public void setBillingAddressZip(String billingAddressZip) {
		this.billingAddressZip = billingAddressZip;
	}
	public String getServiceStartDate() {
		return serviceStartDate;
	}
	public void setServiceStartDate(String serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}
	public String getCreationDateTimestamp() {
		return creationDateTimestamp;
	}
	public void setCreationDateTimestamp(String creationDateTimestamp) {
		this.creationDateTimestamp = creationDateTimestamp;
	}
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public String getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	public String getOfferTeaser() {
		return offerTeaser;
	}
	public void setOfferTeaser(String offerTeaser) {
		this.offerTeaser = offerTeaser;
	}
	public String getContractAccountNumber() {
		return contractAccountNumber;
	}
	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}
	public String getDeviceLatitude() {
		return deviceLatitude;
	}
	public void setDeviceLatitude(String deviceLatitude) {
		this.deviceLatitude = deviceLatitude;
	}
	public String getDeviceLongitude() {
		return deviceLongitude;
	}
	public void setDeviceLongitude(String deviceLongitude) {
		this.deviceLongitude = deviceLongitude;
	}
	public String getDeviceLocationAccuracy() {
		return deviceLocationAccuracy;
	}
	public void setDeviceLocationAccuracy(String deviceLocationAccuracy) {
		this.deviceLocationAccuracy = deviceLocationAccuracy;
	}
	public String getEnrollmentStatus() {
		return enrollmentStatus;
	}
	public void setEnrollmentStatus(String enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}
	public String getTabletID() {
		return tabletID;
	}
	public void setTabletID(String tabletID) {
		this.tabletID = tabletID;
	}
	public String getAgentTypeDescription() {
		return agentTypeDescription;
	}
	public void setAgentTypeDescription(String agentTypeDescription) {
		this.agentTypeDescription = agentTypeDescription;
	}
	
	//Start : US15851 | Send Phone Number and Email Address | HChoudhary
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	//End : US15851 | Send Phone Number and Email Address | HChoudhary
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEnrollmentType() {
		return enrollmentType;
	}
	public void setEnrollmentType(String enrollmentType) {
		this.enrollmentType = enrollmentType;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
}
