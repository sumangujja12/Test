package com.multibrand.dto.request;

public class ProductOfferRequest {
	
	private String zipCode;
	
	private String tdspCode;
	
	private String channelPartnerCode;
	
	private String customerType;
	
	private String langCode;
	
	private String companyCode;
	
	
	
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getTdspCode() {
		return tdspCode;
	}

	public void setTdspCode(String tdspCode) {
		this.tdspCode = tdspCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getChannelPartnerCode() {
		return channelPartnerCode;
	}

	public void setChannelPartnerCode(String channelPartnerCode) {
		this.channelPartnerCode = channelPartnerCode;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	@Override
	public String toString() {
		return "ProductOfferRequest [zipCode=" + zipCode + ", tdspCode=" + tdspCode + ", channelPartnerCode="
				+ channelPartnerCode + ", customerType=" + customerType + ", langCode=" + langCode + ", companyCode="
				+ companyCode + "]";
	}

	
}
