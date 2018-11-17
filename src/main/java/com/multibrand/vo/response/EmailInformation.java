package com.multibrand.vo.response;

public class EmailInformation {

	private String businessPartner;
	private String emailAddress;
	private String externalId;
	private String isBounce;
	
	public String getBusinessPartner() {
		return businessPartner;
	}
	public void setBusinessPartner(String businessPartner) {
		this.businessPartner = businessPartner;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getIsBounce() {
		return isBounce;
	}
	public void setIsBounce(String isBounce) {
		this.isBounce = isBounce;
	}
	@Override
	public String toString() {
		return "EmailInformation [businessPartner=" + businessPartner + ", emailAddress=" + emailAddress
				+ ", externalId=" + externalId + ", isBounce=" + isBounce + "]";
	}
	
	
}
