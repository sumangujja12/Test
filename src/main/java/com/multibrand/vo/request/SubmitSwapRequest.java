package com.multibrand.vo.request;

import java.io.Serializable;

/**
 * 
 * @author rbansal30
 *
 */

public class SubmitSwapRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String campaignCode;
	private String offerCode;
	private String contractId;
	private String esid;
	private String currentContractEndDate;
	private String languageCode;
	private String companyCode;
	private String accountNumber;
	private String checkDigit;
	private String bpNumber;
	private String caName;
	private String servStreetNum;
	private String servStreetName;
	private String servStreetAptNum;
	private String servCity;
	private String servState;
	private String servZipCode;
	private String billStreetNum;
	private String billStreetName;
	private String billStreetAptNum;
	private String billAddrPOBox;
	private String billCity;
	private String billState;
	private String billZipCode;
	private String newContractBegins;
	private String newContractEnds;
	private String planName;
	private String marketingText;
	private String incentives;
	private String avgPrice;
	private String energyCharge;
	private String baseCharge;
	private String tdspCharge;
	private String planType;
	private String contractTerm;
	private String cancelFee;
	private String eflURL;
	private String eflSmartCode;
	private String tosURL;
	private String tosSmartCode;
	private String yraacURL;
	private String yraacSmartCode;
	private String disclaimer;
	private String toEmail;
	private String productContent;
	private String promoCode;
	private String brandName;
	private String offerDate;
	private String offerTime;	
	private String clientSource;//CHG0020873 
	
	//CHG0020873 
	public String getClientSource() {
		return clientSource;
	}
	public void setClientSource(String clientSource) {
		this.clientSource = clientSource;
	}
	
	public String getOfferDate() {
		return offerDate;
	}
	public void setOfferDate(String offerDate) {
		this.offerDate = offerDate;
	}
	public String getOfferTime() {
		return offerTime;
	}
	public void setOfferTime(String offerTime) {
		this.offerTime = offerTime;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public String getProductContent() {
		return productContent;
	}
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	public String getCampaignCode() {
		return campaignCode;
	}
	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}
	public String getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getEsid() {
		return esid;
	}
	public void setEsid(String esid) {
		this.esid = esid;
	}
	public String getCurrentContractEndDate() {
		return currentContractEndDate;
	}
	public void setCurrentContractEndDate(String currentContractEndDate) {
		this.currentContractEndDate = currentContractEndDate;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getCheckDigit() {
		return checkDigit;
	}
	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}
	public String getBpNumber() {
		return bpNumber;
	}
	public void setBpNumber(String bpNumber) {
		this.bpNumber = bpNumber;
	}
	public String getCaName() {
		return caName;
	}
	public void setCaName(String caName) {
		this.caName = caName;
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
	public String getBillAddrPOBox() {
		return billAddrPOBox;
	}
	public void setBillAddrPOBox(String billAddrPOBox) {
		this.billAddrPOBox = billAddrPOBox;
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
	public String getNewContractBegins() {
		return newContractBegins;
	}
	public void setNewContractBegins(String newContractBegins) {
		this.newContractBegins = newContractBegins;
	}
	public String getNewContractEnds() {
		return newContractEnds;
	}
	public void setNewContractEnds(String newContractEnds) {
		this.newContractEnds = newContractEnds;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getMarketingText() {
		return marketingText;
	}
	public void setMarketingText(String marketingText) {
		this.marketingText = marketingText;
	}
	public String getIncentives() {
		return incentives;
	}
	public void setIncentives(String incentives) {
		this.incentives = incentives;
	}
	public String getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}
	public String getEnergyCharge() {
		return energyCharge;
	}
	public void setEnergyCharge(String energyCharge) {
		this.energyCharge = energyCharge;
	}
	public String getBaseCharge() {
		return baseCharge;
	}
	public void setBaseCharge(String baseCharge) {
		this.baseCharge = baseCharge;
	}
	public String getTdspCharge() {
		return tdspCharge;
	}
	public void setTdspCharge(String tdspCharge) {
		this.tdspCharge = tdspCharge;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
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
	public String getEflURL() {
		return eflURL;
	}
	public void setEflURL(String eflURL) {
		this.eflURL = eflURL;
	}
	public String getEflSmartCode() {
		return eflSmartCode;
	}
	public void setEflSmartCode(String eflSmartCode) {
		this.eflSmartCode = eflSmartCode;
	}
	public String getTosURL() {
		return tosURL;
	}
	public void setTosURL(String tosURL) {
		this.tosURL = tosURL;
	}
	public String getTosSmartCode() {
		return tosSmartCode;
	}
	public void setTosSmartCode(String tosSmartCode) {
		this.tosSmartCode = tosSmartCode;
	}
	public String getYraacURL() {
		return yraacURL;
	}
	public void setYraacURL(String yraacURL) {
		this.yraacURL = yraacURL;
	}
	public String getYraacSmartCode() {
		return yraacSmartCode;
	}
	public void setYraacSmartCode(String yraacSmartCode) {
		this.yraacSmartCode = yraacSmartCode;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
}
