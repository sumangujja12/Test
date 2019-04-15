package com.multibrand.vo.response;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.google.gson.annotations.SerializedName;
import com.multibrand.util.Constants;

public class ContractOffer implements Constants {
	
	private String averageMonthlyPlanUsage = "2320";
	private String offerName;
	private String price;
	private String termLength;
	private String cancellationFee;
	private String yrracDocId;
	private String tosDocId;
	private String numberOfTreesSaved;
	private String offerTeaser;
	private String planDisclaimer;
	private String eflDocId;
	private String offerHeadline;
	private String offerDescription;
	private String energyTypeDescription;
	private String energyTypeIcon;
	private String specialOfferDescription;
	private String specialOfferIcon;
	private String productDisclaimer;
	private String genericDisclaimer;
	@SerializedName("messageKey")
	@JsonIgnore
	private String offerCode;
	private String errorMessage;
	
	/**
	 * @return the offerName
	 */
	public String getOfferName() {
		return offerName;
	}
	/**
	 * @param offerName the offerName to set
	 */
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the termLength
	 */
	public String getTermLength() {
		return termLength;
	}
	/**
	 * @param termLength the termLength to set
	 */
	public void setTermLength(String termLength) {
		this.termLength = termLength;
	}
	/**
	 * @return the cancellationFee
	 */
	public String getCancellationFee() {
		return cancellationFee;
	}
	/**
	 * @param cancellationFee the cancellationFee to set
	 */
	public void setCancellationFee(String cancellationFee) {
		this.cancellationFee = cancellationFee;
	}
	/**
	 * @return the yrracDocId
	 */
	public String getYrracDocId() {
		return yrracDocId;
	}
	/**
	 * @param yrracDocId the yrracDocId to set
	 */
	public void setYrracDocId(String yrracDocId) {
		this.yrracDocId = yrracDocId;
	}
	/**
	 * @return the tosDocId
	 */
	public String getTosDocId() {
		return tosDocId;
	}
	/**
	 * @param tosDocId the tosDocId to set
	 */
	public void setTosDocId(String tosDocId) {
		this.tosDocId = tosDocId;
	}
	/**
	 * @return the numberOfTreesSaved
	 */
	public String getNumberOfTreesSaved() {
		return numberOfTreesSaved;
	}
	/**
	 * @param numberOfTreesSaved the numberOfTreesSaved to set
	 */
	public void setNumberOfTreesSaved(String numberOfTreesSaved) {
		this.numberOfTreesSaved = numberOfTreesSaved;
	}
	/**
	 * @return the offerTeaser
	 */
	public String getOfferTeaser() {
		return offerTeaser;
	}
	/**
	 * @param offerTeaser the offerTeaser to set
	 */
	public void setOfferTeaser(String offerTeaser) {
		this.offerTeaser = offerTeaser;
	}
	/**
	 * @return the planDisclaimer
	 */
	public String getPlanDisclaimer() {
		return planDisclaimer;
	}
	/**
	 * @param planDisclaimer the planDisclaimer to set
	 */
	public void setPlanDisclaimer(String planDisclaimer) {
		this.planDisclaimer = planDisclaimer;
	}
	/**
	 * @return the eflDocId
	 */
	public String getEflDocId() {
		return eflDocId;
	}
	/**
	 * @param eflDocId the eflDocId to set
	 */
	public void setEflDocId(String eflDocId) {
		this.eflDocId = eflDocId;
	}
	
	/**
	 * @return the offerCode
	 */
	public String getOfferCode() {
		return offerCode;
	}
	/**
	 * @param offerCode the offerCode to set
	 */
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	/**
	 * @return the offerHeadline
	 */
	public String getOfferHeadline() {
		return offerHeadline;
	}
	/**
	 * @param offerHeadline the offerHeadline to set
	 */
	public void setOfferHeadline(String offerHeadline) {
		this.offerHeadline = offerHeadline;
	}
	/**
	 * @return the offerDescription
	 */
	public String getOfferDescription() {
		return offerDescription;
	}
	/**
	 * @param offerDescription the offerDescription to set
	 */
	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}
	/**
	 * @return the energyTypeDescription
	 */
	public String getEnergyTypeDescription() {
		return energyTypeDescription;
	}
	/**
	 * @param energyTypeDescription the energyTypeDescription to set
	 */
	public void setEnergyTypeDescription(String energyTypeDescription) {
		this.energyTypeDescription = energyTypeDescription;
	}
	/**
	 * @return the energyTypeIcon
	 */
	public String getEnergyTypeIcon() {
		return energyTypeIcon;
	}
	/**
	 * @param energyTypeIcon the energyTypeIcon to set
	 */
	public void setEnergyTypeIcon(String energyTypeIcon) {
		this.energyTypeIcon = energyTypeIcon;
	}
	/**
	 * @return the specialOfferDescription
	 */
	public String getSpecialOfferDescription() {
		return specialOfferDescription;
	}
	/**
	 * @param specialOfferDescription the specialOfferDescription to set
	 */
	public void setSpecialOfferDescription(String specialOfferDescription) {
		this.specialOfferDescription = specialOfferDescription;
	}
	/**
	 * @return the specialOfferIcon
	 */
	public String getSpecialOfferIcon() {
		return specialOfferIcon;
	}
	/**
	 * @param specialOfferIcon the specialOfferIcon to set
	 */
	public void setSpecialOfferIcon(String specialOfferIcon) {
		this.specialOfferIcon = specialOfferIcon;
	}
	/**
	 * @return the productDisclaimer
	 */
	public String getProductDisclaimer() {
		return productDisclaimer;
	}
	/**
	 * @param productDisclaimer the productDisclaimer to set
	 */
	public void setProductDisclaimer(String productDisclaimer) {
		this.productDisclaimer = productDisclaimer;
	}
	/**
	 * @return the genericDisclaimer
	 */
	public String getGenericDisclaimer() {
		return genericDisclaimer;
	}
	/**
	 * @param genericDisclaimer the genericDisclaimer to set
	 */
	public void setGenericDisclaimer(String genericDisclaimer) {
		this.genericDisclaimer = genericDisclaimer;
	}
	
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		if(this.errorMessage != null && this.errorMessage.contains("ERROR")) {
			return CONTET_ERROR_MESSAGEKEY;
		}
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the averageMonthlyPlanUsage
	 */
	public String getAverageMonthlyPlanUsage() {
		return averageMonthlyPlanUsage;
	}
	/**
	 * @param averageMonthlyPlanUsage the averageMonthlyPlanUsage to set
	 */
	public void setAverageMonthlyPlanUsage(String averageMonthlyPlanUsage) {
		this.averageMonthlyPlanUsage = averageMonthlyPlanUsage;
	}
	
	
	
	
	
	
	
}
