package com.multibrand.dto.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.util.Constants;

public class SaveUpdateAlertPrefRequest implements FormEntityRequest, Constants, Serializable {

	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 10, groups = SizeConstraint.class)
	private String businessPartner;
	
	private String contract;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 12, groups = SizeConstraint.class)
	private String contractAccount;
	
	private String efficiencyMktFlag;
	private String lowBalEmailPref;
	private String lowBalPhonePref;
	private String lowBalTextPref;
	private String myBillIsDuePref;
	private String myCostExceeds;
	private String myCostExceedsPref;
	private String myDailyUsageExceeds;
	private String myDailyUsageExceedsPref;
	private String myDayUsageSpikesPref;
	private String myProjBillExceeds;
	private String myProjBillExceedsPref;
	private String payConfEmailPref;
	private String payConfPhonePref;
	private String paymentConfTextPref;
	private String prodServMktFlag;
	private String thirdPartyMktFlag;
	private String weeklyBalSumEmailPref;
	private String weeklyBalSumPhonePref;
	private String weeklyBalSummTextPref;
	private String dailyBalSummTextPref;
	private String dailyBalSummEmailPref;
	
	//START SMS - REDESIGN ALERTS
	private String billReadyPref;
	private String ccExpirePref;
	private String payConfPostPayPref;
	private String autoPayRemovalPref;
	private String payFailurePostPayPref;
	private String serviceAlertsPref;
	private String newAlertNotificationPref;
	//END SMS - REDESIGN ALERTS
	
	@NotBlank(groups = BasicConstraint.class)
	private String isPrepay;
	
	private String userId;
	private String userUniqueId;

	@NotBlank(groups = BasicConstraint.class)
	private String companyCode;
	
	private String brandId;
	
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBusinessPartner() {
		return businessPartner;
	}

	public void setBusinessPartner(String businessPartner) {
		this.businessPartner = businessPartner;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getContractAccount() {
		return contractAccount;
	}

	public void setContractAccount(String contractAccount) {
		this.contractAccount = contractAccount;
	}

	public String getEfficiencyMktFlag() {
		return efficiencyMktFlag;
	}

	public void setEfficiencyMktFlag(String efficiencyMktFlag) {
		this.efficiencyMktFlag = efficiencyMktFlag;
	}

	public String getLowBalEmailPref() {
		return lowBalEmailPref;
	}

	public void setLowBalEmailPref(String lowBalEmailPref) {
		this.lowBalEmailPref = lowBalEmailPref;
	}

	public String getLowBalPhonePref() {
		return lowBalPhonePref;
	}

	public void setLowBalPhonePref(String lowBalPhonePref) {
		this.lowBalPhonePref = lowBalPhonePref;
	}

	public String getLowBalTextPref() {
		return lowBalTextPref;
	}

	public void setLowBalTextPref(String lowBalTextPref) {
		this.lowBalTextPref = lowBalTextPref;
	}

	public String getMyBillIsDuePref() {
		return myBillIsDuePref;
	}

	public void setMyBillIsDuePref(String myBillIsDuePref) {
		this.myBillIsDuePref = myBillIsDuePref;
	}

	public String getMyCostExceeds() {
		return myCostExceeds;
	}

	public void setMyCostExceeds(String myCostExceeds) {
		this.myCostExceeds = myCostExceeds;
	}

	public String getMyCostExceedsPref() {
		return myCostExceedsPref;
	}

	public void setMyCostExceedsPref(String myCostExceedsPref) {
		this.myCostExceedsPref = myCostExceedsPref;
	}

	public String getMyDailyUsageExceeds() {
		return myDailyUsageExceeds;
	}

	public void setMyDailyUsageExceeds(String myDailyUsageExceeds) {
		this.myDailyUsageExceeds = myDailyUsageExceeds;
	}

	public String getMyDailyUsageExceedsPref() {
		return myDailyUsageExceedsPref;
	}

	public void setMyDailyUsageExceedsPref(String myDailyUsageExceedsPref) {
		this.myDailyUsageExceedsPref = myDailyUsageExceedsPref;
	}

	public String getMyDayUsageSpikesPref() {
		return myDayUsageSpikesPref;
	}

	public void setMyDayUsageSpikesPref(String myDayUsageSpikesPref) {
		this.myDayUsageSpikesPref = myDayUsageSpikesPref;
	}

	public String getMyProjBillExceeds() {
		return myProjBillExceeds;
	}

	public void setMyProjBillExceeds(String myProjBillExceeds) {
		this.myProjBillExceeds = myProjBillExceeds;
	}

	public String getMyProjBillExceedsPref() {
		return myProjBillExceedsPref;
	}

	public void setMyProjBillExceedsPref(String myProjBillExceedsPref) {
		this.myProjBillExceedsPref = myProjBillExceedsPref;
	}

	public String getPayConfEmailPref() {
		return payConfEmailPref;
	}

	public void setPayConfEmailPref(String payConfEmailPref) {
		this.payConfEmailPref = payConfEmailPref;
	}

	public String getPayConfPhonePref() {
		return payConfPhonePref;
	}

	public void setPayConfPhonePref(String payConfPhonePref) {
		this.payConfPhonePref = payConfPhonePref;
	}

	public String getPaymentConfTextPref() {
		return paymentConfTextPref;
	}

	public void setPaymentConfTextPref(String paymentConfTextPref) {
		this.paymentConfTextPref = paymentConfTextPref;
	}

	public String getProdServMktFlag() {
		return prodServMktFlag;
	}

	public void setProdServMktFlag(String prodServMktFlag) {
		this.prodServMktFlag = prodServMktFlag;
	}

	public String getThirdPartyMktFlag() {
		return thirdPartyMktFlag;
	}

	public void setThirdPartyMktFlag(String thirdPartyMktFlag) {
		this.thirdPartyMktFlag = thirdPartyMktFlag;
	}

	public String getWeeklyBalSumEmailPref() {
		return weeklyBalSumEmailPref;
	}

	public void setWeeklyBalSumEmailPref(String weeklyBalSumEmailPref) {
		this.weeklyBalSumEmailPref = weeklyBalSumEmailPref;
	}

	public String getWeeklyBalSumPhonePref() {
		return weeklyBalSumPhonePref;
	}

	public void setWeeklyBalSumPhonePref(String weeklyBalSumPhonePref) {
		this.weeklyBalSumPhonePref = weeklyBalSumPhonePref;
	}

	public String getWeeklyBalSummTextPref() {
		return weeklyBalSummTextPref;
	}

	public void setWeeklyBalSummTextPref(String weeklyBalSummTextPref) {
		this.weeklyBalSummTextPref = weeklyBalSummTextPref;
	}

	public String getDailyBalSummTextPref() {
		return dailyBalSummTextPref;
	}

	public void setDailyBalSummTextPref(String dailyBalSummTextPref) {
		this.dailyBalSummTextPref = dailyBalSummTextPref;
	}

	public String getDailyBalSummEmailPref() {
		return dailyBalSummEmailPref;
	}

	public void setDailyBalSummEmailPref(String dailyBalSummEmailPref) {
		this.dailyBalSummEmailPref = dailyBalSummEmailPref;
	}

	public String getIsPrepay() {
		return isPrepay;
	}

	public void setIsPrepay(String isPrepay) {
		this.isPrepay = isPrepay;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserUniqueId() {
		return userUniqueId;
	}

	public void setUserUniqueId(String userUniqueId) {
		this.userUniqueId = userUniqueId;
	}

	public String getBillReadyPref() {
		return billReadyPref;
	}

	public void setBillReadyPref(String billReadyPref) {
		this.billReadyPref = billReadyPref;
	}

	public String getCcExpirePref() {
		return ccExpirePref;
	}

	public void setCcExpirePref(String ccExpirePref) {
		this.ccExpirePref = ccExpirePref;
	}

	public String getPayConfPostPayPref() {
		return payConfPostPayPref;
	}

	public void setPayConfPostPayPref(String payConfPostPayPref) {
		this.payConfPostPayPref = payConfPostPayPref;
	}

	public String getAutoPayRemovalPref() {
		return autoPayRemovalPref;
	}

	public void setAutoPayRemovalPref(String autoPayRemovalPref) {
		this.autoPayRemovalPref = autoPayRemovalPref;
	}

	public String getPayFailurePostPayPref() {
		return payFailurePostPayPref;
	}

	public void setPayFailurePostPayPref(String payFailurePostPayPref) {
		this.payFailurePostPayPref = payFailurePostPayPref;
	}

	public String getServiceAlertsPref() {
		return serviceAlertsPref;
	}

	public void setServiceAlertsPref(String serviceAlertsPref) {
		this.serviceAlertsPref = serviceAlertsPref;
	}

	public String getNewAlertNotificationPref() {
		return newAlertNotificationPref;
	}

	public void setNewAlertNotificationPref(String newAlertNotificationPref) {
		this.newAlertNotificationPref = newAlertNotificationPref;
	}
	
	/**
	 * @return String representation of this request.
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
