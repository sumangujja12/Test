package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="CheckZipSecurityEligibilityOutData")
public class CheckZipSecurityEligibilityOutData {
	
	@SerializedName("ZipCode")
	private String zipCode;
	@SerializedName("Eligible")
	private String eligibleFlag;
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getEligibleFlag() {
		return eligibleFlag;
	}
	public void setEligibleFlag(String eligibleFlag) {
		this.eligibleFlag = eligibleFlag;
	}
	@Override
	public String toString() {
		return "CheckZipSecurityEligibilityOutData [zipCode=" + zipCode + ", eligibleFlag=" + eligibleFlag + "]";
	}
	
}
