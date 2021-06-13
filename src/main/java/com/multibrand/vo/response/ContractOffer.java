package com.multibrand.vo.response;

import com.google.gson.annotations.SerializedName;
import com.multibrand.util.Constants;

public class ContractOffer implements Constants {
	private String averageMonthlyPlanUsage="";
	private String offerName="";
	private String price="";
	private String termLength="";
	private String cancellationFee="";
	private String yrracDocId="";
	private String tosDocId="";
	private String numberOfTreesSaved="";
	private String totalPoundOfCO2="";
	private String totalMilesNotDriven="";
	private String totalNewsPapersRecycled="";
	private String offerTeaser="";
	private String planDisclaimer="";
	private String eflDocId="";
	private String offerHeadline="";
	private String offerDescription="";
	private String energyTypeDescription="";
	private String energyTypeIcon="";
	private String specialOfferDescription="";
	private String specialOfferIcon="";
	private String productDisclaimer="";
	private String genericDisclaimer="";
	private String campaignCode="";
	private String newContractBegins="";
	private String newContractEnds="";
	private String avgPrice="";
	private String baseCharge="";
	private String bccEmail="";
	private String tdspCharge="";
	private String promoCode="";
	private String tosURL="";
	private String yraacURL="";
	private String marketingText="";
	private String incentives="";
	private String energyCharge="";
	private String eflURL="";
	private String disclaimer="";
	private String EFLSmartCode="";
	private String YRAACSmartCode ="";
	private String TOSSmartCode ="";
	private String OfferRank ="";
	private String planType ="";
	private String productContent ="";
	
	@SerializedName("messageKey")
	private String offerCode="";
	private String OfferCellTrackCode="";
	private String errorMessage="";
	private transient  String offerFamily ="";
	
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
	/**
	 * @return the campaignCode
	 */
	public String getCampaignCode() {
		return campaignCode;
	}
	/**
	 * @param campaignCode the campaignCode to set
	 */
	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}
	/**
	 * @return the newContractBegins
	 */
	public String getNewContractBegins() {
		return newContractBegins;
	}
	/**
	 * @param newContractBegins the newContractBegins to set
	 */
	public void setNewContractBegins(String newContractBegins) {
		this.newContractBegins = newContractBegins;
	}
	/**
	 * @return the newContractEnds
	 */
	public String getNewContractEnds() {
		return newContractEnds;
	}
	/**
	 * @param newContractEnds the newContractEnds to set
	 */
	public void setNewContractEnds(String newContractEnds) {
		this.newContractEnds = newContractEnds;
	}
	/**
	 * @return the avgPrice
	 */
	public String getAvgPrice() {
		return avgPrice;
	}
	/**
	 * @param avgPrice the avgPrice to set
	 */
	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}
	/**
	 * @return the baseCharge
	 */
	public String getBaseCharge() {
		return baseCharge;
	}
	/**
	 * @param baseCharge the baseCharge to set
	 */
	public void setBaseCharge(String baseCharge) {
		this.baseCharge = baseCharge;
	}
	/**
	 * @return the bccEmail
	 */
	public String getBccEmail() {
		return bccEmail;
	}
	/**
	 * @param bccEmail the bccEmail to set
	 */
	public void setBccEmail(String bccEmail) {
		this.bccEmail = bccEmail;
	}
	/**
	 * @return the tdspCharge
	 */
	public String getTdspCharge() {
		return tdspCharge;
	}
	/**
	 * @param tdspCharge the tdspCharge to set
	 */
	public void setTdspCharge(String tdspCharge) {
		this.tdspCharge = tdspCharge;
	}
	/**
	 * @return the promoCode
	 */
	public String getPromoCode() {
		return promoCode;
	}
	/**
	 * @param promoCode the promoCode to set
	 */
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	/**
	 * @return the tosURL
	 */
	public String getTosURL() {
		return tosURL;
	}
	/**
	 * @param tosURL the tosURL to set
	 */
	public void setTosURL(String tosURL) {
		this.tosURL = tosURL;
	}
	/**
	 * @return the yraacURL
	 */
	public String getYraacURL() {
		return yraacURL;
	}
	/**
	 * @param yraacURL the yraacURL to set
	 */
	public void setYraacURL(String yraacURL) {
		this.yraacURL = yraacURL;
	}
	/**
	 * @return the marketingText
	 */
	public String getMarketingText() {
		return marketingText;
	}
	/**
	 * @param marketingText the marketingText to set
	 */
	public void setMarketingText(String marketingText) {
		this.marketingText = marketingText;
	}
	/**
	 * @return the incentives
	 */
	public String getIncentives() {
		return incentives;
	}
	/**
	 * @param incentives the incentives to set
	 */
	public void setIncentives(String incentives) {
		this.incentives = incentives;
	}
	/**
	 * @return the energyCharge
	 */
	public String getEnergyCharge() {
		return energyCharge;
	}
	/**
	 * @param energyCharge the energyCharge to set
	 */
	public void setEnergyCharge(String energyCharge) {
		this.energyCharge = energyCharge;
	}
	/**
	 * @return the eflURL
	 */
	public String getEflURL() {
		return eflURL;
	}
	/**
	 * @param eflURL the eflURL to set
	 */
	public void setEflURL(String eflURL) {
		this.eflURL = eflURL;
	}
	/**
	 * @return the disclaimer
	 */
	public String getDisclaimer() {
		return disclaimer;
	}
	/**
	 * @param disclaimer the disclaimer to set
	 */
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	/**
	 * @return the eFLSmartCode
	 */
	public String getEFLSmartCode() {
		return EFLSmartCode;
	}
	/**
	 * @param eFLSmartCode the eFLSmartCode to set
	 */
	public void setEFLSmartCode(String eFLSmartCode) {
		EFLSmartCode = eFLSmartCode;
	}
	/**
	 * @return the yRAACSmartCode
	 */
	public String getYRAACSmartCode() {
		return YRAACSmartCode;
	}
	/**
	 * @param yRAACSmartCode the yRAACSmartCode to set
	 */
	public void setYRAACSmartCode(String yRAACSmartCode) {
		YRAACSmartCode = yRAACSmartCode;
	}
	/**
	 * @return the tOSSmartCode
	 */
	public String getTOSSmartCode() {
		return TOSSmartCode;
	}
	/**
	 * @param tOSSmartCode the tOSSmartCode to set
	 */
	public void setTOSSmartCode(String tOSSmartCode) {
		TOSSmartCode = tOSSmartCode;
	}
	/**
	 * @return the offerRank
	 */
	public String getOfferRank() {
		return OfferRank;
	}
	/**
	 * @param offerRank the offerRank to set
	 */
	public void setOfferRank(String offerRank) {
		OfferRank = offerRank;
	}
	/**
	 * @return the planType
	 */
	public String getPlanType() {
		return planType;
	}
	/**
	 * @param planType the planType to set
	 */
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	/**
	 * @return the productContent
	 */
	public String getProductContent() {
		return productContent;
	}
	/**
	 * @param productContent the productContent to set
	 */
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	/**
	 * @return the offerFamily
	 */
	public String getOfferFamily() {
		return offerFamily;
	}
	/**
	 * @param offerFamily the offerFamily to set
	 */
	public void setOfferFamily(String offerFamily) {
		this.offerFamily = offerFamily;
	}
	/**
	 * @return the totalPoundOfCO2
	 */
	public String getTotalPoundOfCO2() {
		return totalPoundOfCO2;
	}
	/**
	 * @param totalPoundOfCO2 the totalPoundOfCO2 to set
	 */
	public void setTotalPoundOfCO2(String totalPoundOfCO2) {
		this.totalPoundOfCO2 = totalPoundOfCO2;
	}
	/**
	 * @return the totalMilesNotDriven
	 */
	public String getTotalMilesNotDriven() {
		return totalMilesNotDriven;
	}
	/**
	 * @param totalMilesNotDriven the totalMilesNotDriven to set
	 */
	public void setTotalMilesNotDriven(String totalMilesNotDriven) {
		this.totalMilesNotDriven = totalMilesNotDriven;
	}
	/**
	 * @return the totalNewsPapersRecycled
	 */
	public String getTotalNewsPapersRecycled() {
		return totalNewsPapersRecycled;
	}
	/**
	 * @param totalNewsPapersRecycled the totalNewsPapersRecycled to set
	 */
	public void setTotalNewsPapersRecycled(String totalNewsPapersRecycled) {
		this.totalNewsPapersRecycled = totalNewsPapersRecycled;
	}
	/**
	 * @return the offerCellTrackCode
	 */
	public String getOfferCellTrackCode() {
		return OfferCellTrackCode;
	}
	/**
	 * @param offerCellTrackCode the offerCellTrackCode to set
	 */
	public void setOfferCellTrackCode(String offerCellTrackCode) {
		OfferCellTrackCode = offerCellTrackCode;
	}	
}
