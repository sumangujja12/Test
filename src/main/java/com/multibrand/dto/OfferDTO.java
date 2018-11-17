package com.multibrand.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class OfferDTO implements Serializable {

	private static final long serialVersionUID = 6126902486675531317L;

	private String offerTeaser; // Same as Tagline
	private String offerTeaserFromCCS;
	private String signupBonus;
	private String signupBonusPopupText;
	private String planType;
	private String cancelFee;
	private String elfId; // TODO : refractor to strEflId
	private String offerCode;
	private String campaignCode;
	private String campaignDescription;
	private String custClass;
	private String customerSegment;
	private String dwellingType;
	private String eflSmartCode;
	private String incentiveCode;
	private String incentiveDescription;
	private String incentiveValue;
	private String invoiceOptions;
	private String marketSegment;
	private String offerCodeTitle;
	private String payOptions;
	private String penaltyDesciption;
	private String penaltyValue;
	private String planName;
	private String planDesc;
	private String productCode;
	private String productPriceCode;
	private String tosDocID;
	private String tosSmartCode;
	private String validFromDate;
	private String validToDate;
	private String yraacDocID;
	private String yraacSmartCode;
	private String ccsContractLength;
	private String offerCellTrackCodeSelected;
	private String offerCategory;
	// Start predictable12 - Thabitha Sethuraman
	private boolean predictOfferFlag;
	private boolean conserveOfferFlag;
	private String additionalText;
	private String additionalPricingText;
	private String prepaySSDescID;
	private String popupText;
	private String productDetail;
	private String productDetailPopupText;
	private String tdspSurchargeMessage;
	private String dateLineText;
	private String filtersText;

	// private OfferPriceDTO offerPriceDTO;

	private List<String> legaleaseList;
	private List<String> productTypeList;
	private List<String> productPopupList;
	private List<String> productSEEligibleList;
	private List<String> productSESignupTextList;

	private boolean featuredPlan = false;

	// Added for Swap - start
	private Boolean isSaveOffer = false;
	private Boolean isRollOverOffer = false;
	private String productAuthText;
	private String eflPrice;
	private String offerRank;
	private String eflIssueDate;
	private String bundlingGroup;
	private String tarrifType;
	private String tdspCodeCCS;
	// Added for Swap - end
	// Start predictable12 - Vsood
	private BigDecimal[] bdFixedPriceKWHCharges;
	// Start predictable12 - Vsood

	// Start: Product Chart Redesign
	private boolean smartMeterRequired = false;
	private boolean greenPlan = false;
	// private OfferBannerDTO offerBanner;
	private List<String> filters;
	// End :Product Chart Redesign
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
	 * @return the offerTeaserFromCCS
	 */
	public String getOfferTeaserFromCCS() {
		return offerTeaserFromCCS;
	}
	/**
	 * @param offerTeaserFromCCS the offerTeaserFromCCS to set
	 */
	public void setOfferTeaserFromCCS(String offerTeaserFromCCS) {
		this.offerTeaserFromCCS = offerTeaserFromCCS;
	}
	/**
	 * @return the signupBonus
	 */
	public String getSignupBonus() {
		return signupBonus;
	}
	/**
	 * @param signupBonus the signupBonus to set
	 */
	public void setSignupBonus(String signupBonus) {
		this.signupBonus = signupBonus;
	}
	/**
	 * @return the signupBonusPopupText
	 */
	public String getSignupBonusPopupText() {
		return signupBonusPopupText;
	}
	/**
	 * @param signupBonusPopupText the signupBonusPopupText to set
	 */
	public void setSignupBonusPopupText(String signupBonusPopupText) {
		this.signupBonusPopupText = signupBonusPopupText;
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
	 * @return the cancelFee
	 */
	public String getCancelFee() {
		return cancelFee;
	}
	/**
	 * @param cancelFee the cancelFee to set
	 */
	public void setCancelFee(String cancelFee) {
		this.cancelFee = cancelFee;
	}
	/**
	 * @return the elfId
	 */
	public String getElfId() {
		return elfId;
	}
	/**
	 * @param elfId the elfId to set
	 */
	public void setElfId(String elfId) {
		this.elfId = elfId;
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
	 * @return the campaignDescription
	 */
	public String getCampaignDescription() {
		return campaignDescription;
	}
	/**
	 * @param campaignDescription the campaignDescription to set
	 */
	public void setCampaignDescription(String campaignDescription) {
		this.campaignDescription = campaignDescription;
	}
	/**
	 * @return the custClass
	 */
	public String getCustClass() {
		return custClass;
	}
	/**
	 * @param custClass the custClass to set
	 */
	public void setCustClass(String custClass) {
		this.custClass = custClass;
	}
	/**
	 * @return the customerSegment
	 */
	public String getCustomerSegment() {
		return customerSegment;
	}
	/**
	 * @param customerSegment the customerSegment to set
	 */
	public void setCustomerSegment(String customerSegment) {
		this.customerSegment = customerSegment;
	}
	/**
	 * @return the dwellingType
	 */
	public String getDwellingType() {
		return dwellingType;
	}
	/**
	 * @param dwellingType the dwellingType to set
	 */
	public void setDwellingType(String dwellingType) {
		this.dwellingType = dwellingType;
	}
	/**
	 * @return the eflSmartCode
	 */
	public String getEflSmartCode() {
		return eflSmartCode;
	}
	/**
	 * @param eflSmartCode the eflSmartCode to set
	 */
	public void setEflSmartCode(String eflSmartCode) {
		this.eflSmartCode = eflSmartCode;
	}
	/**
	 * @return the incentiveCode
	 */
	public String getIncentiveCode() {
		return incentiveCode;
	}
	/**
	 * @param incentiveCode the incentiveCode to set
	 */
	public void setIncentiveCode(String incentiveCode) {
		this.incentiveCode = incentiveCode;
	}
	/**
	 * @return the incentiveDescription
	 */
	public String getIncentiveDescription() {
		return incentiveDescription;
	}
	/**
	 * @param incentiveDescription the incentiveDescription to set
	 */
	public void setIncentiveDescription(String incentiveDescription) {
		this.incentiveDescription = incentiveDescription;
	}
	/**
	 * @return the incentiveValue
	 */
	public String getIncentiveValue() {
		return incentiveValue;
	}
	/**
	 * @param incentiveValue the incentiveValue to set
	 */
	public void setIncentiveValue(String incentiveValue) {
		this.incentiveValue = incentiveValue;
	}
	/**
	 * @return the invoiceOptions
	 */
	public String getInvoiceOptions() {
		return invoiceOptions;
	}
	/**
	 * @param invoiceOptions the invoiceOptions to set
	 */
	public void setInvoiceOptions(String invoiceOptions) {
		this.invoiceOptions = invoiceOptions;
	}
	/**
	 * @return the marketSegment
	 */
	public String getMarketSegment() {
		return marketSegment;
	}
	/**
	 * @param marketSegment the marketSegment to set
	 */
	public void setMarketSegment(String marketSegment) {
		this.marketSegment = marketSegment;
	}
	/**
	 * @return the offerCodeTitle
	 */
	public String getOfferCodeTitle() {
		return offerCodeTitle;
	}
	/**
	 * @param offerCodeTitle the offerCodeTitle to set
	 */
	public void setOfferCodeTitle(String offerCodeTitle) {
		this.offerCodeTitle = offerCodeTitle;
	}
	/**
	 * @return the payOptions
	 */
	public String getPayOptions() {
		return payOptions;
	}
	/**
	 * @param payOptions the payOptions to set
	 */
	public void setPayOptions(String payOptions) {
		this.payOptions = payOptions;
	}
	/**
	 * @return the penaltyDesciption
	 */
	public String getPenaltyDesciption() {
		return penaltyDesciption;
	}
	/**
	 * @param penaltyDesciption the penaltyDesciption to set
	 */
	public void setPenaltyDesciption(String penaltyDesciption) {
		this.penaltyDesciption = penaltyDesciption;
	}
	/**
	 * @return the penaltyValue
	 */
	public String getPenaltyValue() {
		return penaltyValue;
	}
	/**
	 * @param penaltyValue the penaltyValue to set
	 */
	public void setPenaltyValue(String penaltyValue) {
		this.penaltyValue = penaltyValue;
	}
	/**
	 * @return the planName
	 */
	public String getPlanName() {
		return planName;
	}
	/**
	 * @param planName the planName to set
	 */
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	/**
	 * @return the planDesc
	 */
	public String getPlanDesc() {
		return planDesc;
	}
	/**
	 * @param planDesc the planDesc to set
	 */
	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
	}
	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * @return the productPriceCode
	 */
	public String getProductPriceCode() {
		return productPriceCode;
	}
	/**
	 * @param productPriceCode the productPriceCode to set
	 */
	public void setProductPriceCode(String productPriceCode) {
		this.productPriceCode = productPriceCode;
	}
	/**
	 * @return the tosDocID
	 */
	public String getTosDocID() {
		return tosDocID;
	}
	/**
	 * @param tosDocID the tosDocID to set
	 */
	public void setTosDocID(String tosDocID) {
		this.tosDocID = tosDocID;
	}
	/**
	 * @return the tosSmartCode
	 */
	public String getTosSmartCode() {
		return tosSmartCode;
	}
	/**
	 * @param tosSmartCode the tosSmartCode to set
	 */
	public void setTosSmartCode(String tosSmartCode) {
		this.tosSmartCode = tosSmartCode;
	}
	/**
	 * @return the validFromDate
	 */
	public String getValidFromDate() {
		return validFromDate;
	}
	/**
	 * @param validFromDate the validFromDate to set
	 */
	public void setValidFromDate(String validFromDate) {
		this.validFromDate = validFromDate;
	}
	/**
	 * @return the validToDate
	 */
	public String getValidToDate() {
		return validToDate;
	}
	/**
	 * @param validToDate the validToDate to set
	 */
	public void setValidToDate(String validToDate) {
		this.validToDate = validToDate;
	}
	/**
	 * @return the yraacDocID
	 */
	public String getYraacDocID() {
		return yraacDocID;
	}
	/**
	 * @param yraacDocID the yraacDocID to set
	 */
	public void setYraacDocID(String yraacDocID) {
		this.yraacDocID = yraacDocID;
	}
	/**
	 * @return the yraacSmartCode
	 */
	public String getYraacSmartCode() {
		return yraacSmartCode;
	}
	/**
	 * @param yraacSmartCode the yraacSmartCode to set
	 */
	public void setYraacSmartCode(String yraacSmartCode) {
		this.yraacSmartCode = yraacSmartCode;
	}
	/**
	 * @return the ccsContractLength
	 */
	public String getCcsContractLength() {
		return ccsContractLength;
	}
	/**
	 * @param ccsContractLength the ccsContractLength to set
	 */
	public void setCcsContractLength(String ccsContractLength) {
		this.ccsContractLength = ccsContractLength;
	}
	/**
	 * @return the offerCellTrackCodeSelected
	 */
	public String getOfferCellTrackCodeSelected() {
		return offerCellTrackCodeSelected;
	}
	/**
	 * @param offerCellTrackCodeSelected the offerCellTrackCodeSelected to set
	 */
	public void setOfferCellTrackCodeSelected(String offerCellTrackCodeSelected) {
		this.offerCellTrackCodeSelected = offerCellTrackCodeSelected;
	}
	/**
	 * @return the offerCategory
	 */
	public String getOfferCategory() {
		return offerCategory;
	}
	/**
	 * @param offerCategory the offerCategory to set
	 */
	public void setOfferCategory(String offerCategory) {
		this.offerCategory = offerCategory;
	}
	/**
	 * @return the predictOfferFlag
	 */
	public boolean isPredictOfferFlag() {
		return predictOfferFlag;
	}
	/**
	 * @param predictOfferFlag the predictOfferFlag to set
	 */
	public void setPredictOfferFlag(boolean predictOfferFlag) {
		this.predictOfferFlag = predictOfferFlag;
	}
	/**
	 * @return the conserveOfferFlag
	 */
	public boolean isConserveOfferFlag() {
		return conserveOfferFlag;
	}
	/**
	 * @param conserveOfferFlag the conserveOfferFlag to set
	 */
	public void setConserveOfferFlag(boolean conserveOfferFlag) {
		this.conserveOfferFlag = conserveOfferFlag;
	}
	/**
	 * @return the additionalText
	 */
	public String getAdditionalText() {
		return additionalText;
	}
	/**
	 * @param additionalText the additionalText to set
	 */
	public void setAdditionalText(String additionalText) {
		this.additionalText = additionalText;
	}
	/**
	 * @return the additionalPricingText
	 */
	public String getAdditionalPricingText() {
		return additionalPricingText;
	}
	/**
	 * @param additionalPricingText the additionalPricingText to set
	 */
	public void setAdditionalPricingText(String additionalPricingText) {
		this.additionalPricingText = additionalPricingText;
	}
	/**
	 * @return the prepaySSDescID
	 */
	public String getPrepaySSDescID() {
		return prepaySSDescID;
	}
	/**
	 * @param prepaySSDescID the prepaySSDescID to set
	 */
	public void setPrepaySSDescID(String prepaySSDescID) {
		this.prepaySSDescID = prepaySSDescID;
	}
	/**
	 * @return the popupText
	 */
	public String getPopupText() {
		return popupText;
	}
	/**
	 * @param popupText the popupText to set
	 */
	public void setPopupText(String popupText) {
		this.popupText = popupText;
	}
	/**
	 * @return the productDetail
	 */
	public String getProductDetail() {
		return productDetail;
	}
	/**
	 * @param productDetail the productDetail to set
	 */
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	/**
	 * @return the productDetailPopupText
	 */
	public String getProductDetailPopupText() {
		return productDetailPopupText;
	}
	/**
	 * @param productDetailPopupText the productDetailPopupText to set
	 */
	public void setProductDetailPopupText(String productDetailPopupText) {
		this.productDetailPopupText = productDetailPopupText;
	}
	/**
	 * @return the tdspSurchargeMessage
	 */
	public String getTdspSurchargeMessage() {
		return tdspSurchargeMessage;
	}
	/**
	 * @param tdspSurchargeMessage the tdspSurchargeMessage to set
	 */
	public void setTdspSurchargeMessage(String tdspSurchargeMessage) {
		this.tdspSurchargeMessage = tdspSurchargeMessage;
	}
	/**
	 * @return the dateLineText
	 */
	public String getDateLineText() {
		return dateLineText;
	}
	/**
	 * @param dateLineText the dateLineText to set
	 */
	public void setDateLineText(String dateLineText) {
		this.dateLineText = dateLineText;
	}
	/**
	 * @return the legaleaseList
	 */
	public List<String> getLegaleaseList() {
		return legaleaseList;
	}
	/**
	 * @param legaleaseList the legaleaseList to set
	 */
	public void setLegaleaseList(List<String> legaleaseList) {
		this.legaleaseList = legaleaseList;
	}
	/**
	 * @return the productTypeList
	 */
	public List<String> getProductTypeList() {
		return productTypeList;
	}
	/**
	 * @param productTypeList the productTypeList to set
	 */
	public void setProductTypeList(List<String> productTypeList) {
		this.productTypeList = productTypeList;
	}
	/**
	 * @return the productPopupList
	 */
	public List<String> getProductPopupList() {
		return productPopupList;
	}
	/**
	 * @param productPopupList the productPopupList to set
	 */
	public void setProductPopupList(List<String> productPopupList) {
		this.productPopupList = productPopupList;
	}
	/**
	 * @return the productSEEligibleList
	 */
	public List<String> getProductSEEligibleList() {
		return productSEEligibleList;
	}
	/**
	 * @param productSEEligibleList the productSEEligibleList to set
	 */
	public void setProductSEEligibleList(List<String> productSEEligibleList) {
		this.productSEEligibleList = productSEEligibleList;
	}
	/**
	 * @return the productSESignupTextList
	 */
	public List<String> getProductSESignupTextList() {
		return productSESignupTextList;
	}
	/**
	 * @param productSESignupTextList the productSESignupTextList to set
	 */
	public void setProductSESignupTextList(List<String> productSESignupTextList) {
		this.productSESignupTextList = productSESignupTextList;
	}
	/**
	 * @return the featuredPlan
	 */
	public boolean isFeaturedPlan() {
		return featuredPlan;
	}
	/**
	 * @param featuredPlan the featuredPlan to set
	 */
	public void setFeaturedPlan(boolean featuredPlan) {
		this.featuredPlan = featuredPlan;
	}
	/**
	 * @return the isSaveOffer
	 */
	public Boolean getIsSaveOffer() {
		return isSaveOffer;
	}
	/**
	 * @param isSaveOffer the isSaveOffer to set
	 */
	public void setIsSaveOffer(Boolean isSaveOffer) {
		this.isSaveOffer = isSaveOffer;
	}
	/**
	 * @return the isRollOverOffer
	 */
	public Boolean getIsRollOverOffer() {
		return isRollOverOffer;
	}
	/**
	 * @param isRollOverOffer the isRollOverOffer to set
	 */
	public void setIsRollOverOffer(Boolean isRollOverOffer) {
		this.isRollOverOffer = isRollOverOffer;
	}
	/**
	 * @return the productAuthText
	 */
	public String getProductAuthText() {
		return productAuthText;
	}
	/**
	 * @param productAuthText the productAuthText to set
	 */
	public void setProductAuthText(String productAuthText) {
		this.productAuthText = productAuthText;
	}
	/**
	 * @return the eflPrice
	 */
	public String getEflPrice() {
		return eflPrice;
	}
	/**
	 * @param eflPrice the eflPrice to set
	 */
	public void setEflPrice(String eflPrice) {
		this.eflPrice = eflPrice;
	}
	/**
	 * @return the offerRank
	 */
	public String getOfferRank() {
		return offerRank;
	}
	/**
	 * @param offerRank the offerRank to set
	 */
	public void setOfferRank(String offerRank) {
		this.offerRank = offerRank;
	}
	/**
	 * @return the eflIssueDate
	 */
	public String getEflIssueDate() {
		return eflIssueDate;
	}
	/**
	 * @param eflIssueDate the eflIssueDate to set
	 */
	public void setEflIssueDate(String eflIssueDate) {
		this.eflIssueDate = eflIssueDate;
	}
	/**
	 * @return the bundlingGroup
	 */
	public String getBundlingGroup() {
		return bundlingGroup;
	}
	/**
	 * @param bundlingGroup the bundlingGroup to set
	 */
	public void setBundlingGroup(String bundlingGroup) {
		this.bundlingGroup = bundlingGroup;
	}
	/**
	 * @return the tarrifType
	 */
	public String getTarrifType() {
		return tarrifType;
	}
	/**
	 * @param tarrifType the tarrifType to set
	 */
	public void setTarrifType(String tarrifType) {
		this.tarrifType = tarrifType;
	}
	/**
	 * @return the tdspCodeCCS
	 */
	public String getTdspCodeCCS() {
		return tdspCodeCCS;
	}
	/**
	 * @param tdspCodeCCS the tdspCodeCCS to set
	 */
	public void setTdspCodeCCS(String tdspCodeCCS) {
		this.tdspCodeCCS = tdspCodeCCS;
	}
	/**
	 * @return the bdFixedPriceKWHCharges
	 */
	public BigDecimal[] getBdFixedPriceKWHCharges() {
		return bdFixedPriceKWHCharges;
	}
	/**
	 * @param bdFixedPriceKWHCharges the bdFixedPriceKWHCharges to set
	 */
	public void setBdFixedPriceKWHCharges(BigDecimal[] bdFixedPriceKWHCharges) {
		this.bdFixedPriceKWHCharges = bdFixedPriceKWHCharges;
	}
	/**
	 * @return the smartMeterRequired
	 */
	public boolean isSmartMeterRequired() {
		return smartMeterRequired;
	}
	/**
	 * @param smartMeterRequired the smartMeterRequired to set
	 */
	public void setSmartMeterRequired(boolean smartMeterRequired) {
		this.smartMeterRequired = smartMeterRequired;
	}
	/**
	 * @return the greenPlan
	 */
	public boolean isGreenPlan() {
		return greenPlan;
	}
	/**
	 * @param greenPlan the greenPlan to set
	 */
	public void setGreenPlan(boolean greenPlan) {
		this.greenPlan = greenPlan;
	}
	
	/**
	 * @return the filters
	 */
	public List<String> getFilters() {
		
		if (filters == null && filtersText != null){
			filters = Arrays.asList(filtersText.split("\\s*,\\s*"));
		}
		
		return filters;
	}
	
	/**
	 * @param filters the filters to set
	 */
	public void setFilters(List<String> filters) {
		this.filters = filters;
	}
	/**
	 * @return the filtersText
	 */
	public String getFiltersText() {
		return filtersText;
	}
	/**
	 * @param filtersText the filtersText to set
	 */
	public void setFiltersText(String filtersText) {
		this.filtersText = filtersText;
	}
		
}
