package com.multibrand.vo.request;

import java.util.List;

public class PushNotifiPreferenceRequest{
	
	private String brandName;
	private String companyCode;
	private String contractAccountNumber;
	private List<PushNotificationStatus> pushStatTabs;
	private List<String> alertIds;
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getContractAccountNumber() {
		return contractAccountNumber;
	}
	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}
	public List<PushNotificationStatus> getPushStatTabs() {
		return pushStatTabs;
	}
	public void setPushStatTabs(List<PushNotificationStatus> pushStatTabs) {
		this.pushStatTabs = pushStatTabs;
	}
	public List<String> getAlertIds() {
		return alertIds;
	}
	public void setAlertIds(List<String> alertIds) {
		this.alertIds = alertIds;
	}
}
