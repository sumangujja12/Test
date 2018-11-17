package com.multibrand.vo.response;

public class PhoneInformation {

	private String businessPartner;
	private String externalId;
	private String isBounce;
	private String isSMSActive;
	private String remark;
	private String secureCodeSent;
	private String telephone;

	public String getBusinessPartner() {
		return businessPartner;
	}

	public void setBusinessPartner(String businessPartner) {
		this.businessPartner = businessPartner;
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

	public String getIsSMSActive() {
		return isSMSActive;
	}

	public void setIsSMSActive(String isSMSActive) {
		this.isSMSActive = isSMSActive;
	}

	public String getSecureCodeSent() {
		return secureCodeSent;
	}

	public void setSecureCodeSent(String secureCodeSent) {
		this.secureCodeSent = secureCodeSent;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "PhoneInformation [businessPartner=" + businessPartner + ", externalId=" + externalId + ", isBounce="
				+ isBounce + ", isSMSActive=" + isSMSActive + ", remark=" + remark + ", secureCodeSent="
				+ secureCodeSent + ", telephone=" + telephone + "]";
	}
	
}
