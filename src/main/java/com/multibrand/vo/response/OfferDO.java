package com.multibrand.vo.response;

import java.util.List;

public class OfferDO {
  private String strCancelFee = "";
  private String strContractTerm = "";
  private String strEFLDocID = "";
  private String strEFLSmartCode = "";
  private String strYRAACDocID = "";
  private String strYRAACSmartCode = "";
  private String strTOSDocID = "";
  private String strTOSSmartCode = "";
  private String strPlanName = "";
  private String strCampaignCode = "";
  private String strPromoCode = "";
  private String strOfferCode = "";
  private String strProductCode = "";
  private String strOfferCellTrackCode = "";
  private String strProductPriceCode = "";
  private String strIncentiveCode = "";
  private String strValidToDate = "";
  private String strValidFromDate = "";
  private String strCustomerSegment = "";
  private String strMarketSegment = "";
  private String strPlanType = "";
  private String strInvoiceOptions = "";
  private String strPayOptions = "";
  private String strIncentiveDescription = "";
  private String strOfferTeaser = "";
  private String strIncentiveValue = "";
  private String strCampaignDescription = "";
  private String strCustClass = "";
  private String strPenaltyDesciption = "";
  private String strOfferCodeTitle = "";
  private String strPenaltyValue = "";
  private String strDwellingType = "";
  private List<OfferPriceWraperDO> avgPriceMap;
  
  private String strTarrifType = "";
  private String strBundlingGroup = "";
  private String strOfferRank = "";
  private String strEFLIssueDate = "";
  private String strOfferCategory = "";
  private String strPlanDesc = "";
  OfferPriceDO[] offerPriceEntry;
  
  private CampEnvironmentDO[] campEnvironmentDetails;
  private SegmentedFlagDO[] segmentedFlags;
  private String attribute1 = "";
  private String attribute2 = "";
  private String attribute3 = "";
  private String attribute4 = "";
  private String attribute5 = "";
  private String attribute6 = "";
  private String attribute7 = "";
  private String attribute8 = "";
  private String attribute9 = "";
  private String attribute10 = "";
  
  private String strTDSPCode = "";

  private TDSPChargeDO tdspChargeDO;
  
  
public String getStrTDSPCode() {
	return strTDSPCode;
}
public void setStrTDSPCode(String strTDSPCode) {
	this.strTDSPCode = strTDSPCode;
}
public String getStrCancelFee() {
	return strCancelFee;
}
public void setStrCancelFee(String strCancelFee) {
	this.strCancelFee = strCancelFee;
}
public String getStrContractTerm() {
	return strContractTerm;
}
public void setStrContractTerm(String strContractTerm) {
	this.strContractTerm = strContractTerm;
}
public String getStrEFLDocID() {
	return strEFLDocID;
}
public void setStrEFLDocID(String strEFLDocID) {
	this.strEFLDocID = strEFLDocID;
}
public String getStrEFLSmartCode() {
	return strEFLSmartCode;
}
public void setStrEFLSmartCode(String strEFLSmartCode) {
	this.strEFLSmartCode = strEFLSmartCode;
}
public String getStrYRAACDocID() {
	return strYRAACDocID;
}
public void setStrYRAACDocID(String strYRAACDocID) {
	this.strYRAACDocID = strYRAACDocID;
}
public String getStrYRAACSmartCode() {
	return strYRAACSmartCode;
}
public void setStrYRAACSmartCode(String strYRAACSmartCode) {
	this.strYRAACSmartCode = strYRAACSmartCode;
}
public String getStrTOSDocID() {
	return strTOSDocID;
}
public void setStrTOSDocID(String strTOSDocID) {
	this.strTOSDocID = strTOSDocID;
}
public String getStrTOSSmartCode() {
	return strTOSSmartCode;
}
public void setStrTOSSmartCode(String strTOSSmartCode) {
	this.strTOSSmartCode = strTOSSmartCode;
}
public String getStrPlanName() {
	return strPlanName;
}
public void setStrPlanName(String strPlanName) {
	this.strPlanName = strPlanName;
}
public String getStrCampaignCode() {
	return strCampaignCode;
}
public void setStrCampaignCode(String strCampaignCode) {
	this.strCampaignCode = strCampaignCode;
}
public String getStrPromoCode() {
	return strPromoCode;
}
public void setStrPromoCode(String strPromoCode) {
	this.strPromoCode = strPromoCode;
}
public String getStrOfferCode() {
	return strOfferCode;
}
public void setStrOfferCode(String strOfferCode) {
	this.strOfferCode = strOfferCode;
}
public String getStrProductCode() {
	return strProductCode;
}
public void setStrProductCode(String strProductCode) {
	this.strProductCode = strProductCode;
}
public String getStrOfferCellTrackCode() {
	return strOfferCellTrackCode;
}
public void setStrOfferCellTrackCode(String strOfferCellTrackCode) {
	this.strOfferCellTrackCode = strOfferCellTrackCode;
}
public String getStrProductPriceCode() {
	return strProductPriceCode;
}
public void setStrProductPriceCode(String strProductPriceCode) {
	this.strProductPriceCode = strProductPriceCode;
}
public String getStrIncentiveCode() {
	return strIncentiveCode;
}
public void setStrIncentiveCode(String strIncentiveCode) {
	this.strIncentiveCode = strIncentiveCode;
}
public String getStrValidToDate() {
	return strValidToDate;
}
public void setStrValidToDate(String strValidToDate) {
	this.strValidToDate = strValidToDate;
}
public String getStrValidFromDate() {
	return strValidFromDate;
}
public void setStrValidFromDate(String strValidFromDate) {
	this.strValidFromDate = strValidFromDate;
}
public String getStrCustomerSegment() {
	return strCustomerSegment;
}
public void setStrCustomerSegment(String strCustomerSegment) {
	this.strCustomerSegment = strCustomerSegment;
}
public String getStrMarketSegment() {
	return strMarketSegment;
}
public void setStrMarketSegment(String strMarketSegment) {
	this.strMarketSegment = strMarketSegment;
}
public String getStrPlanType() {
	return strPlanType;
}
public void setStrPlanType(String strPlanType) {
	this.strPlanType = strPlanType;
}
public String getStrInvoiceOptions() {
	return strInvoiceOptions;
}
public void setStrInvoiceOptions(String strInvoiceOptions) {
	this.strInvoiceOptions = strInvoiceOptions;
}
public String getStrPayOptions() {
	return strPayOptions;
}
public void setStrPayOptions(String strPayOptions) {
	this.strPayOptions = strPayOptions;
}
public String getStrIncentiveDescription() {
	return strIncentiveDescription;
}
public void setStrIncentiveDescription(String strIncentiveDescription) {
	this.strIncentiveDescription = strIncentiveDescription;
}
public String getStrOfferTeaser() {
	return strOfferTeaser;
}
public void setStrOfferTeaser(String strOfferTeaser) {
	this.strOfferTeaser = strOfferTeaser;
}
public String getStrIncentiveValue() {
	return strIncentiveValue;
}
public void setStrIncentiveValue(String strIncentiveValue) {
	this.strIncentiveValue = strIncentiveValue;
}
public String getStrCampaignDescription() {
	return strCampaignDescription;
}
public void setStrCampaignDescription(String strCampaignDescription) {
	this.strCampaignDescription = strCampaignDescription;
}
public String getStrCustClass() {
	return strCustClass;
}
public void setStrCustClass(String strCustClass) {
	this.strCustClass = strCustClass;
}
public String getStrPenaltyDesciption() {
	return strPenaltyDesciption;
}
public void setStrPenaltyDesciption(String strPenaltyDesciption) {
	this.strPenaltyDesciption = strPenaltyDesciption;
}
public String getStrOfferCodeTitle() {
	return strOfferCodeTitle;
}
public void setStrOfferCodeTitle(String strOfferCodeTitle) {
	this.strOfferCodeTitle = strOfferCodeTitle;
}
public String getStrPenaltyValue() {
	return strPenaltyValue;
}
public void setStrPenaltyValue(String strPenaltyValue) {
	this.strPenaltyValue = strPenaltyValue;
}
public String getStrDwellingType() {
	return strDwellingType;
}
public void setStrDwellingType(String strDwellingType) {
	this.strDwellingType = strDwellingType;
}

public List<OfferPriceWraperDO> getAvgPriceMap() {
	return avgPriceMap;
}
public void setAvgPriceMap(List<OfferPriceWraperDO> avgPriceMap) {
	this.avgPriceMap = avgPriceMap;
}
public String getStrTarrifType() {
	return strTarrifType;
}
public void setStrTarrifType(String strTarrifType) {
	this.strTarrifType = strTarrifType;
}
public String getStrBundlingGroup() {
	return strBundlingGroup;
}
public void setStrBundlingGroup(String strBundlingGroup) {
	this.strBundlingGroup = strBundlingGroup;
}
public String getStrOfferRank() {
	return strOfferRank;
}
public void setStrOfferRank(String strOfferRank) {
	this.strOfferRank = strOfferRank;
}
public String getStrEFLIssueDate() {
	return strEFLIssueDate;
}
public void setStrEFLIssueDate(String strEFLIssueDate) {
	this.strEFLIssueDate = strEFLIssueDate;
}
public String getStrPlanDesc() {
	return strPlanDesc;
}
public void setStrPlanDesc(String strPlanDesc) {
	this.strPlanDesc = strPlanDesc;
}

public OfferPriceDO[] getOfferPriceEntry() {
	return offerPriceEntry;
}
public void setOfferPriceEntry(OfferPriceDO[] offerPriceEntry) {
	this.offerPriceEntry = offerPriceEntry;
}
public CampEnvironmentDO[] getCampEnvironmentDetails() {
	return campEnvironmentDetails;
}
public void setCampEnvironmentDetails(CampEnvironmentDO[] campEnvironmentDetails) {
	this.campEnvironmentDetails = campEnvironmentDetails;
}
public SegmentedFlagDO[] getSegmentedFlags() {
	return segmentedFlags;
}
public void setSegmentedFlags(SegmentedFlagDO[] segmentedFlags) {
	this.segmentedFlags = segmentedFlags;
}
public String getStrOfferCategory() {
	return strOfferCategory;
}
public void setStrOfferCategory(String strOfferCategory) {
	this.strOfferCategory = strOfferCategory;
}
/**
 * @return the attribute1
 */
public String getAttribute1()
{
	return attribute1;
}
/**
 * @param attribute1 the attribute1 to set
 */
public void setAttribute1(String attribute1)
{
	this.attribute1 = attribute1;
}
/**
 * @return the attribute2
 */
public String getAttribute2()
{
	return attribute2;
}
/**
 * @param attribute2 the attribute2 to set
 */
public void setAttribute2(String attribute2)
{
	this.attribute2 = attribute2;
}
/**
 * @return the attribute3
 */
public String getAttribute3()
{
	return attribute3;
}
/**
 * @param attribute3 the attribute3 to set
 */
public void setAttribute3(String attribute3)
{
	this.attribute3 = attribute3;
}
/**
 * @return the attribute4
 */
public String getAttribute4()
{
	return attribute4;
}
/**
 * @param attribute4 the attribute4 to set
 */
public void setAttribute4(String attribute4)
{
	this.attribute4 = attribute4;
}
/**
 * @return the attribute5
 */
public String getAttribute5()
{
	return attribute5;
}
/**
 * @param attribute5 the attribute5 to set
 */
public void setAttribute5(String attribute5)
{
	this.attribute5 = attribute5;
}
/**
 * @return the attribute6
 */
public String getAttribute6()
{
	return attribute6;
}
/**
 * @param attribute6 the attribute6 to set
 */
public void setAttribute6(String attribute6)
{
	this.attribute6 = attribute6;
}
/**
 * @return the attribute7
 */
public String getAttribute7()
{
	return attribute7;
}
/**
 * @param attribute7 the attribute7 to set
 */
public void setAttribute7(String attribute7)
{
	this.attribute7 = attribute7;
}
/**
 * @return the attribute8
 */
public String getAttribute8()
{
	return attribute8;
}
/**
 * @param attribute8 the attribute8 to set
 */
public void setAttribute8(String attribute8)
{
	this.attribute8 = attribute8;
}
/**
 * @return the attribute9
 */
public String getAttribute9()
{
	return attribute9;
}
/**
 * @param attribute9 the attribute9 to set
 */
public void setAttribute9(String attribute9)
{
	this.attribute9 = attribute9;
}
/**
 * @return the attribute10
 */
public String getAttribute10()
{
	return attribute10;
}
/**
 * @param attribute10 the attribute10 to set
 */
public void setAttribute10(String attribute10)
{
	this.attribute10 = attribute10;
}
public TDSPChargeDO getTdspChargeDO() {
	return tdspChargeDO;
}
public void setTdspChargeDO(TDSPChargeDO tdspChargeDO) {
	this.tdspChargeDO = tdspChargeDO;
}

  
  
}