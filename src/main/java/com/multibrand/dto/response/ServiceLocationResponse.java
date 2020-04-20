package com.multibrand.dto.response;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.multibrand.util.CommonUtil;

public class ServiceLocationResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String trackingId;
	private String personId;
	private String serviceRequestTypeCode;
	private String previousProviderCode;
	private String depositCode;
	private String requestStatusCode;
	private String offerCode;
	private String spanishReqFlag;
	private String providerAgreementFlag;
	private String depositAmount;
	private String esid;
	private String contractAccountNum;
	private String servAddressLine1;
	private String servAddressLine2;
	private String servCity;
	private String servState;
	private String servZipCode;
	private String servStreetNum;
	private String servStreetName;
	private String servStreetAptNum;
	private String serviceZipOverrideFlag;
	private String addressBillSameAsServiceFlag;
	private String serviceAddressOverrideFlag;
	private String billAddressLine1;
	private String billAddressLine2;
	private String billCity;
	private String billState;
	private String billZipCode;
	private String billStreetNum;
	private String billStreetName;
	private String billStreetAptNum;
	private String billPoBox;
	private String serviceStartDate;
	private String guid;
	private String signupChannelCode;
	private String referrerCode;
	private String accountName;
	private String mailAddressLine1;
	private String mailAddressLine2;
	private String mailCity;
	private String mailState;
	private String mailZipCode;
	private String mailStreetNum;
	private String mailStreetName;
	private String mailStreetAptNum;
	private String mailPoBox;
	private String esidMatchFlag;
	private String geoZone;
	private String promoCodeEntered;
	private String completionStatusCode;
	private String adId;
	private String payCode;
	private String tdspCode;
	private String offerCodeTitle;
	private String caCheckDigit;
	private String offerCellTrackCodeSelected;
	private String billingAddressOverrideFlag;
	private String errorCode;
	private String errorCdlist;
	private String promoType;
	private String promoValue;
	private String dwellingType;
	private String cepProcessedFlag;
	private String recentPageAccessed;
	private String esuiteFlag;
	private String esuiteStatus;
	private String esuiteAuthFlag;
	private String esuiteOamFlag;
	private String esuiteEmailFlag;
	private String esuiteAutoPayFlag;
	private String esuiteOamDate;
	private String esuiteEmailDate;
	private String esuiteAutoPayDate;
	private String esuiteAutoPayOption;
	private String userName;
	private String password;
	private String weeklySummaryEnroll;
	private String permitType;
	private String permitClass;
	private String permitDetail;
	private String cityCountry;
	private String cityCountryName;
	private String permitPhone;
	private String priorityMoveinFlag;
	private String htmlCaptureFlag;
	private String nonCommodityProduct;
	private String prepayFlag;
	private String prepayTotalToday;
	private String prepayDocId;
	private String ecoShare;
	private String activeCustomerFlag;
	private String addressMatchFlag;
	private String pendingBalanceFlag;
	private String bpActiveContract;
	private String matchedPartnerId;
	private String addressSearchPerformed;
	private String bpMatchNoCcsResponse;
	private String productType;
	private String productSkuCode;
	private String planName;
	private String enrollSource;
	private String activationFee;
	private String bondPrice;
	private String accSecStatus;
	private String isPayUpFront;
	private String securityMethod;
	private String activationFeeCode;
	private String meterType;
	private String switchHoldStatus;
	private String recentDisconnectFlag;
	private String premiseType;
	private String esidStatus;
	private String verifyAdditionalOptions;
	private String ambProgramCode;
	private String paperlessProgramCode;
	private String rhsProgramCodeOne;
	private String rhsProgramCodeTwo;
	private String rhsOfferType;
	private String freqFlyerFirstName;
	private String freqFlyerLastName;
	private String referralId;
	private String recentCallMade;
	private String nestSid;
	private String gzProductId;
	private String affiliateId;
	private String brandId;
	private String companyCode;
	private String sunClub;
	private String driverClub;
	private String giftCard;
	private String residentialSolar;
	private String messageCode;
	private String outErrorCode;
	private String offerCategory;
	private String frequentFlyerNumber;
	private String realtorId;
	private PersonResponse personResponse;
	//START : OE :Sprint62 :US21019 :Kdeshmu1
			String agentID;
			String agentType;
			String agentFirstName;
			String agentLastName;
			String vendorCode;
			String vendorName;
			String tlpReportApiStatus;
    private String posidSNRO;
    
    private String bpMatchScenarioId;
    private String prospectId;
    private String prospectPreapprovalFlag;
    private String prospectPartnerId;
    private String callExecutedFromDB;			
			
			public String getAgentID() {
				return agentID;
			}

			public void setAgentID(String agentID) {
				this.agentID = agentID;
			}
			
			public String getAgentType() {
				return agentType;
			}

			public void setAgentType(String agentType) {
				this.agentType = agentType;
			}

			public String getAgentFirstName() {
				return agentFirstName;
			}

			public void setAgentFirstName(String agentFirstName) {
				this.agentFirstName = agentFirstName;
			}

			public String getAgentLastName() {
				return agentLastName;
			}

			public void setAgentLastName(String agentLastName) {
				this.agentLastName = agentLastName;
			}

			public String getVendorCode() {
				return vendorCode;
			}

			public void setVendorCode(String vendorCode) {
				this.vendorCode = vendorCode;
			}

			public String getVendorName() {
				return vendorName;
			}

			public void setVendorName(String vendorName) {
				this.vendorName = vendorName;
			}

			public String getTlpReportApiStatus() {
				return tlpReportApiStatus;
			}

			public void setTlpReportApiStatus(String tlpReportApiStatus) {
				this.tlpReportApiStatus = tlpReportApiStatus;
			}

			//end : OE :Sprint62 :US21019 :Kdeshmu1

	public ServiceLocationResponse() {

	}

	/**
	 * @return the trackingId
	 */
	public String getTrackingId() {
		return trackingId;
	}

	/**
	 * @param trackingId
	 *            the trackingId to set
	 */
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	/**
	 * @return the personId
	 */
	public String getPersonId() {
		return personId;
	}

	/**
	 * @param personId
	 *            the personId to set
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}

	/**
	 * @return the serviceRequestTypeCode
	 */
	public String getServiceRequestTypeCode() {
		return serviceRequestTypeCode;
	}

	/**
	 * @param serviceRequestTypeCode
	 *            the serviceRequestTypeCode to set
	 */
	public void setServiceRequestTypeCode(String serviceRequestTypeCode) {
		this.serviceRequestTypeCode = serviceRequestTypeCode;
	}

	/**
	 * @return the previousProviderCode
	 */
	public String getPreviousProviderCode() {
		return previousProviderCode;
	}

	/**
	 * @param previousProviderCode
	 *            the previousProviderCode to set
	 */
	public void setPreviousProviderCode(String previousProviderCode) {
		this.previousProviderCode = previousProviderCode;
	}

	/**
	 * @return the depositCode
	 */
	public String getDepositCode() {
		return depositCode;
	}

	/**
	 * @param depositCode
	 *            the depositCode to set
	 */
	public void setDepositCode(String depositCode) {
		this.depositCode = depositCode;
	}

	/**
	 * @return the requestStatusCode
	 */
	public String getRequestStatusCode() {
		return requestStatusCode;
	}

	/**
	 * @param requestStatusCode
	 *            the requestStatusCode to set
	 */
	public void setRequestStatusCode(String requestStatusCode) {
		this.requestStatusCode = requestStatusCode;
	}

	/**
	 * @return the offerCode
	 */
	public String getOfferCode() {
		return offerCode;
	}

	/**
	 * @param offerCode
	 *            the offerCode to set
	 */
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	/**
	 * @return the spanishReqFlag
	 */
	public String getSpanishReqFlag() {
		return spanishReqFlag;
	}

	/**
	 * @param spanishReqFlag
	 *            the spanishReqFlag to set
	 */
	public void setSpanishReqFlag(String spanishReqFlag) {
		this.spanishReqFlag = spanishReqFlag;
	}

	/**
	 * @return the providerAgreementFlag
	 */
	public String getProviderAgreementFlag() {
		return providerAgreementFlag;
	}

	/**
	 * @param providerAgreementFlag
	 *            the providerAgreementFlag to set
	 */
	public void setProviderAgreementFlag(String providerAgreementFlag) {
		this.providerAgreementFlag = providerAgreementFlag;
	}

	/**
	 * @return the depositAmount
	 */
	public String getDepositAmount() {
		return depositAmount;
	}

	/**
	 * @param depositAmount
	 *            the depositAmount to set
	 */
	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}

	/**
	 * @return the esid
	 */
	public String getEsid() {
		return esid;
	}

	/**
	 * @param esid
	 *            the esid to set
	 */
	public void setEsid(String esid) {
		this.esid = esid;
	}

	/**
	 * @return the contractAccountNum
	 */
	public String getContractAccountNum() {
		return contractAccountNum;
	}

	/**
	 * @param contractAccountNum
	 *            the contractAccountNum to set
	 */
	public void setContractAccountNum(String contractAccountNum) {
		this.contractAccountNum = contractAccountNum;
	}

	/**
	 * @return the servAddressLine1
	 */
	public String getServAddressLine1() {
		return servAddressLine1;
	}

	/**
	 * @param servAddressLine1
	 *            the servAddressLine1 to set
	 */
	public void setServAddressLine1(String servAddressLine1) {
		this.servAddressLine1 = servAddressLine1;
	}

	/**
	 * @return the servAddressLine2
	 */
	public String getServAddressLine2() {
		return servAddressLine2;
	}

	/**
	 * @param servAddressLine2
	 *            the servAddressLine2 to set
	 */
	public void setServAddressLine2(String servAddressLine2) {
		this.servAddressLine2 = servAddressLine2;
	}

	/**
	 * @return the servCity
	 */
	public String getServCity() {
		return servCity;
	}

	/**
	 * @param servCity
	 *            the servCity to set
	 */
	public void setServCity(String servCity) {
		this.servCity = servCity;
	}

	/**
	 * @return the servState
	 */
	public String getServState() {
		return servState;
	}

	/**
	 * @param servState
	 *            the servState to set
	 */
	public void setServState(String servState) {
		this.servState = servState;
	}

	/**
	 * @return the servZipCode
	 */
	public String getServZipCode() {
		return servZipCode;
	}

	/**
	 * @param servZipCode
	 *            the servZipCode to set
	 */
	public void setServZipCode(String servZipCode) {
		this.servZipCode = servZipCode;
	}

	/**
	 * @return the servStreetNum
	 */
	public String getServStreetNum() {
		if(StringUtils.isEmpty(servStreetNum)) {
			servStreetNum = CommonUtil.stripStreetNum(servAddressLine1);
		}
		return servStreetNum;
	}

	/**
	 * @param servStreetNum
	 *            the servStreetNum to set
	 */
	public void setServStreetNum(String servStreetNum) {
		this.servStreetNum = servStreetNum;
	}

	/**
	 * @return the servStreetName
	 */
	public String getServStreetName() {
		if(StringUtils.isEmpty(servStreetName)) {
			servStreetName = CommonUtil.stripStreetName(servAddressLine1);
		}
		return servStreetName;
	}

	/**
	 * @param servStreetName
	 *            the servStreetName to set
	 */
	public void setServStreetName(String servStreetName) {
		this.servStreetName = servStreetName;
	}

	/**
	 * @return the servStreetAptNum
	 */
	public String getServStreetAptNum() {
		if(StringUtils.isEmpty(servStreetAptNum)) {
			servStreetAptNum = servAddressLine2;
		}
		return servStreetAptNum;
	}

	/**
	 * @param servStreetAptNum
	 *            the servStreetAptNum to set
	 */
	public void setServStreetAptNum(String servStreetAptNum) {
		this.servStreetAptNum = servStreetAptNum;
	}

	/**
	 * @return the serviceZipOverrideFlag
	 */
	public String getServiceZipOverrideFlag() {
		return serviceZipOverrideFlag;
	}

	/**
	 * @param serviceZipOverrideFlag
	 *            the serviceZipOverrideFlag to set
	 */
	public void setServiceZipOverrideFlag(String serviceZipOverrideFlag) {
		this.serviceZipOverrideFlag = serviceZipOverrideFlag;
	}

	/**
	 * @return the addressBillSameAsServiceFlag
	 */
	public String getAddressBillSameAsServiceFlag() {
		return addressBillSameAsServiceFlag;
	}

	/**
	 * @param addressBillSameAsServiceFlag
	 *            the addressBillSameAsServiceFlag to set
	 */
	public void setAddressBillSameAsServiceFlag(
			String addressBillSameAsServiceFlag) {
		this.addressBillSameAsServiceFlag = addressBillSameAsServiceFlag;
	}

	/**
	 * @return the serviceAddressOverrideFlag
	 */
	public String getServiceAddressOverrideFlag() {
		return serviceAddressOverrideFlag;
	}

	/**
	 * @param serviceAddressOverrideFlag
	 *            the serviceAddressOverrideFlag to set
	 */
	public void setServiceAddressOverrideFlag(String serviceAddressOverrideFlag) {
		this.serviceAddressOverrideFlag = serviceAddressOverrideFlag;
	}

	/**
	 * @return the billAddressLine1
	 */
	public String getBillAddressLine1() {
		return billAddressLine1;
	}

	/**
	 * @param billAddressLine1
	 *            the billAddressLine1 to set
	 */
	public void setBillAddressLine1(String billAddressLine1) {
		this.billAddressLine1 = billAddressLine1;
	}

	/**
	 * @return the billAddressLine2
	 */
	public String getBillAddressLine2() {
		return billAddressLine2;
	}

	/**
	 * @param billAddressLine2
	 *            the billAddressLine2 to set
	 */
	public void setBillAddressLine2(String billAddressLine2) {
		this.billAddressLine2 = billAddressLine2;
	}

	/**
	 * @return the billCity
	 */
	public String getBillCity() {
		return billCity;
	}

	/**
	 * @param billCity
	 *            the billCity to set
	 */
	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}

	/**
	 * @return the billState
	 */
	public String getBillState() {
		return billState;
	}

	/**
	 * @param billState
	 *            the billState to set
	 */
	public void setBillState(String billState) {
		this.billState = billState;
	}

	/**
	 * @return the billStreetNum
	 */
	public String getBillStreetNum() {
		if(StringUtils.isEmpty(billStreetNum)) {
			billStreetNum = CommonUtil.stripStreetNum(billAddressLine1);
		}
		return billStreetNum;
	}

	/**
	 * @param billStreetNum
	 *            the billStreetNum to set
	 */
	public void setBillStreetNum(String billStreetNum) {
		this.billStreetNum = billStreetNum;
	}

	/**
	 * @return the billStreetName
	 */
	public String getBillStreetName() {
		if(StringUtils.isEmpty(billStreetName)) {
			billStreetName = CommonUtil.stripStreetName(billAddressLine1);
		}
		return billStreetName;
	}

	/**
	 * @param billStreetName
	 *            the billStreetName to set
	 */
	public void setBillStreetName(String billStreetName) {
		this.billStreetName = billStreetName;
	}

	/**
	 * @return the billPoBox
	 */
	public String getBillPoBox() {
		return billPoBox;
	}

	/**
	 * @param billPoBox
	 *            the billPoBox to set
	 */
	public void setBillPoBox(String billPoBox) {
		this.billPoBox = billPoBox;
	}

	/**
	 * @return the billZipCode
	 */
	public String getBillZipCode() {
		return billZipCode;
	}

	/**
	 * @param billZipCode
	 *            the billZipCode to set
	 */
	public void setBillZipCode(String billZipCode) {
		this.billZipCode = billZipCode;
	}

	/**
	 * @return the billStreetAptNum
	 */
	public String getBillStreetAptNum() {
		return billStreetAptNum;
	}

	/**
	 * @param billStreetAptNum
	 *            the billStreetAptNum to set
	 */
	public void setBillStreetAptNum(String billStreetAptNum) {
		this.billStreetAptNum = billStreetAptNum;
	}

	/**
	 * @return the serviceStartDate
	 */
	public String getServiceStartDate() {
		return serviceStartDate;
	}

	/**
	 * @param serviceStartDate
	 *            the serviceStartDate to set
	 */
	public void setServiceStartDate(String serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	/**
	 * @return the guid
	 */
	public String getGuid() {
	
		return guid;
	}

	/**
	 * @param guid
	 *            the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * @return the signupChannelCode
	 */
	public String getSignupChannelCode() {
		return signupChannelCode;
	}

	/**
	 * @param signupChannelCode
	 *            the signupChannelCode to set
	 */
	public void setSignupChannelCode(String signupChannelCode) {
		this.signupChannelCode = signupChannelCode;
	}

	/**
	 * @return the referrerCode
	 */
	public String getReferrerCode() {
		return referrerCode;
	}

	/**
	 * @param referrerCode
	 *            the referrerCode to set
	 */
	public void setReferrerCode(String referrerCode) {
		this.referrerCode = referrerCode;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName
	 *            the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the mailAddressLine1
	 */
	public String getMailAddressLine1() {
		return mailAddressLine1;
	}

	/**
	 * @param mailAddressLine1
	 *            the mailAddressLine1 to set
	 */
	public void setMailAddressLine1(String mailAddressLine1) {
		this.mailAddressLine1 = mailAddressLine1;
	}

	/**
	 * @return the mailAddressLine2
	 */
	public String getMailAddressLine2() {
		return mailAddressLine2;
	}

	/**
	 * @param mailAddressLine2
	 *            the mailAddressLine2 to set
	 */
	public void setMailAddressLine2(String mailAddressLine2) {
		this.mailAddressLine2 = mailAddressLine2;
	}

	/**
	 * @return the mailCity
	 */
	public String getMailCity() {
		return mailCity;
	}

	/**
	 * @param mailCity
	 *            the mailCity to set
	 */
	public void setMailCity(String mailCity) {
		this.mailCity = mailCity;
	}

	/**
	 * @return the mailState
	 */
	public String getMailState() {
		return mailState;
	}

	/**
	 * @param mailState
	 *            the mailState to set
	 */
	public void setMailState(String mailState) {
		this.mailState = mailState;
	}

	/**
	 * @return the mailStreetNum
	 */
	public String getMailStreetNum() {
		return mailStreetNum;
	}

	/**
	 * @param mailStreetNum
	 *            the mailStreetNum to set
	 */
	public void setMailStreetNum(String mailStreetNum) {
		this.mailStreetNum = mailStreetNum;
	}

	/**
	 * @return the mailStreetName
	 */
	public String getMailStreetName() {
		return mailStreetName;
	}

	/**
	 * @param mailStreetName
	 *            the mailStreetName to set
	 */
	public void setMailStreetName(String mailStreetName) {
		this.mailStreetName = mailStreetName;
	}

	/**
	 * @return the mailZipCode
	 */
	public String getMailZipCode() {
		return mailZipCode;
	}

	/**
	 * @param mailZipCode
	 *            the mailZipCode to set
	 */
	public void setMailZipCode(String mailZipCode) {
		this.mailZipCode = mailZipCode;
	}

	/**
	 * @return the mailStreetAptNum
	 */
	public String getMailStreetAptNum() {
		return mailStreetAptNum;
	}

	/**
	 * @param mailStreetAptNum
	 *            the mailStreetAptNum to set
	 */
	public void setMailStreetAptNum(String mailStreetAptNum) {
		this.mailStreetAptNum = mailStreetAptNum;
	}

	/**
	 * @return the mailPoBox
	 */
	public String getMailPoBox() {
		return mailPoBox;
	}

	/**
	 * @param mailPoBox
	 *            the mailPoBox to set
	 */
	public void setMailPoBox(String mailPoBox) {
		this.mailPoBox = mailPoBox;
	}

	/**
	 * @return the esidMatchFlag
	 */
	public String getEsidMatchFlag() {
		return esidMatchFlag;
	}

	/**
	 * @param esidMatchFlag
	 *            the esidMatchFlag to set
	 */
	public void setEsidMatchFlag(String esidMatchFlag) {
		this.esidMatchFlag = esidMatchFlag;
	}

	/**
	 * @return the geoZone
	 */
	public String getGeoZone() {
		return geoZone;
	}

	/**
	 * @param geoZone
	 *            the geoZone to set
	 */
	public void setGeoZone(String geoZone) {
		this.geoZone = geoZone;
	}

	/**
	 * @return the promoCodeEntered
	 */
	public String getPromoCodeEntered() {
		return promoCodeEntered;
	}

	/**
	 * @param promoCodeEntered
	 *            the promoCodeEntered to set
	 */
	public void setPromoCodeEntered(String promoCodeEntered) {
		this.promoCodeEntered = promoCodeEntered;
	}

	/**
	 * @return the completionStatusCode
	 */
	public String getCompletionStatusCode() {
		return completionStatusCode;
	}

	/**
	 * @param completionStatusCode
	 *            the completionStatusCode to set
	 */
	public void setCompletionStatusCode(String completionStatusCode) {
		this.completionStatusCode = completionStatusCode;
	}

	/**
	 * @return the adId
	 */
	public String getAdId() {
		return adId;
	}

	/**
	 * @param adId
	 *            the adId to set
	 */
	public void setAdId(String adId) {
		this.adId = adId;
	}

	/**
	 * @return the payCode
	 */
	public String getPayCode() {
		return payCode;
	}

	/**
	 * @param payCode
	 *            the payCode to set
	 */
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	/**
	 * @return the tdspCode
	 */
	public String getTdspCode() {
		return tdspCode;
	}

	/**
	 * @param tdspCode
	 *            the tdspCode to set
	 */
	public void setTdspCode(String tdspCode) {
		this.tdspCode = tdspCode;
	}

	/**
	 * @return the offerCodeTitle
	 */
	public String getOfferCodeTitle() {
		return offerCodeTitle;
	}

	/**
	 * @param offerCodeTitle
	 *            the offerCodeTitle to set
	 */
	public void setOfferCodeTitle(String offerCodeTitle) {
		this.offerCodeTitle = offerCodeTitle;
	}

	/**
	 * @return the caCheckDigit
	 */
	public String getCaCheckDigit() {
		return caCheckDigit;
	}

	/**
	 * @param caCheckDigit
	 *            the caCheckDigit to set
	 */
	public void setCaCheckDigit(String caCheckDigit) {
		this.caCheckDigit = caCheckDigit;
	}

	/**
	 * @return the offerCellTrackCodeSelected
	 */
	public String getOfferCellTrackCodeSelected() {
		return offerCellTrackCodeSelected;
	}

	/**
	 * @param offerCellTrackCodeSelected
	 *            the offerCellTrackCodeSelected to set
	 */
	public void setOfferCellTrackCodeSelected(String offerCellTrackCodeSelected) {
		this.offerCellTrackCodeSelected = offerCellTrackCodeSelected;
	}

	/**
	 * @return the billingAddressOverrideFlag
	 */
	public String getBillingAddressOverrideFlag() {
		return billingAddressOverrideFlag;
	}

	/**
	 * @param billingAddressOverrideFlag
	 *            the billingAddressOverrideFlag to set
	 */
	public void setBillingAddressOverrideFlag(String billingAddressOverrideFlag) {
		this.billingAddressOverrideFlag = billingAddressOverrideFlag;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the promoType
	 */
	public String getPromoType() {
		return promoType;
	}

	/**
	 * @param promoType
	 *            the promoType to set
	 */
	public void setPromoType(String promoType) {
		this.promoType = promoType;
	}

	/**
	 * @return the promoValue
	 */
	public String getPromoValue() {
		return promoValue;
	}

	/**
	 * @param promoValue
	 *            the promoValue to set
	 */
	public void setPromoValue(String promoValue) {
		this.promoValue = promoValue;
	}

	/**
	 * @return the dwellingType
	 */
	public String getDwellingType() {
		return dwellingType;
	}

	/**
	 * @param dwellingType
	 *            the dwellingType to set
	 */
	public void setDwellingType(String dwellingType) {
		this.dwellingType = dwellingType;
	}

	/**
	 * @return the cepProcessedFlag
	 */
	public String getCepProcessedFlag() {
		return cepProcessedFlag;
	}

	/**
	 * @param cepProcessedFlag
	 *            the cepProcessedFlag to set
	 */
	public void setCepProcessedFlag(String cepProcessedFlag) {
		this.cepProcessedFlag = cepProcessedFlag;
	}

	/**
	 * @return the recentPageAccessed
	 */
	public String getRecentPageAccessed() {
		return recentPageAccessed;
	}

	/**
	 * @param recentPageAccessed
	 *            the recentPageAccessed to set
	 */
	public void setRecentPageAccessed(String recentPageAccessed) {
		this.recentPageAccessed = recentPageAccessed;
	}

	/**
	 * @return the esuiteFlag
	 */
	public String getEsuiteFlag() {
		return esuiteFlag;
	}

	/**
	 * @param esuiteFlag
	 *            the esuiteFlag to set
	 */
	public void setEsuiteFlag(String esuiteFlag) {
		this.esuiteFlag = esuiteFlag;
	}

	/**
	 * @return the esuiteStatus
	 */
	public String getEsuiteStatus() {
		return esuiteStatus;
	}

	/**
	 * @param esuiteStatus
	 *            the esuiteStatus to set
	 */
	public void setEsuiteStatus(String esuiteStatus) {
		this.esuiteStatus = esuiteStatus;
	}

	/**
	 * @return the esuiteAuthFlag
	 */
	public String getEsuiteAuthFlag() {
		return esuiteAuthFlag;
	}

	/**
	 * @param esuiteAuthFlag
	 *            the esuiteAuthFlag to set
	 */
	public void setEsuiteAuthFlag(String esuiteAuthFlag) {
		this.esuiteAuthFlag = esuiteAuthFlag;
	}

	/**
	 * @return the esuiteOamFlag
	 */
	public String getEsuiteOamFlag() {
		return esuiteOamFlag;
	}

	/**
	 * @param esuiteOamFlag
	 *            the esuiteOamFlag to set
	 */
	public void setEsuiteOamFlag(String esuiteOamFlag) {
		this.esuiteOamFlag = esuiteOamFlag;
	}

	/**
	 * @return the esuiteEmailFlag
	 */
	public String getEsuiteEmailFlag() {
		return esuiteEmailFlag;
	}

	/**
	 * @param esuiteEmailFlag
	 *            the esuiteEmailFlag to set
	 */
	public void setEsuiteEmailFlag(String esuiteEmailFlag) {
		this.esuiteEmailFlag = esuiteEmailFlag;
	}

	/**
	 * @return the esuiteAutoPayFlag
	 */
	public String getEsuiteAutoPayFlag() {
		return esuiteAutoPayFlag;
	}

	/**
	 * @param esuiteAutoPayFlag
	 *            the esuiteAutoPayFlag to set
	 */
	public void setEsuiteAutoPayFlag(String esuiteAutoPayFlag) {
		this.esuiteAutoPayFlag = esuiteAutoPayFlag;
	}

	/**
	 * @return the esuiteOamDate
	 */
	public String getEsuiteOamDate() {
		return esuiteOamDate;
	}

	/**
	 * @param esuiteOamDate
	 *            the esuiteOamDate to set
	 */
	public void setEsuiteOamDate(String esuiteOamDate) {
		this.esuiteOamDate = esuiteOamDate;
	}

	/**
	 * @return the esuiteEmailDate
	 */
	public String getEsuiteEmailDate() {
		return esuiteEmailDate;
	}

	/**
	 * @param esuiteEmailDate
	 *            the esuiteEmailDate to set
	 */
	public void setEsuiteEmailDate(String esuiteEmailDate) {
		this.esuiteEmailDate = esuiteEmailDate;
	}

	/**
	 * @return the esuiteAutoPayDate
	 */
	public String getEsuiteAutoPayDate() {
		return esuiteAutoPayDate;
	}

	/**
	 * @param esuiteAutoPayDate
	 *            the esuiteAutoPayDate to set
	 */
	public void setEsuiteAutoPayDate(String esuiteAutoPayDate) {
		this.esuiteAutoPayDate = esuiteAutoPayDate;
	}

	/**
	 * @return the esuiteAutoPayOption
	 */
	public String getEsuiteAutoPayOption() {
		return esuiteAutoPayOption;
	}

	/**
	 * @param esuiteAutoPayOption
	 *            the esuiteAutoPayOption to set
	 */
	public void setEsuiteAutoPayOption(String esuiteAutoPayOption) {
		this.esuiteAutoPayOption = esuiteAutoPayOption;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the weeklySummaryEnroll
	 */
	public String getWeeklySummaryEnroll() {
		return weeklySummaryEnroll;
	}

	/**
	 * @param weeklySummaryEnroll
	 *            the weeklySummaryEnroll to set
	 */
	public void setWeeklySummaryEnroll(String weeklySummaryEnroll) {
		this.weeklySummaryEnroll = weeklySummaryEnroll;
	}

	/**
	 * @return the permitType
	 */
	public String getPermitType() {
		return permitType;
	}

	/**
	 * @param permitType
	 *            the permitType to set
	 */
	public void setPermitType(String permitType) {
		this.permitType = permitType;
	}

	/**
	 * @return the permitClass
	 */
	public String getPermitClass() {
		return permitClass;
	}

	/**
	 * @param permitClass
	 *            the permitClass to set
	 */
	public void setPermitClass(String permitClass) {
		this.permitClass = permitClass;
	}

	/**
	 * @return the permitDetail
	 */
	public String getPermitDetail() {
		return permitDetail;
	}

	/**
	 * @param permitDetail
	 *            the permitDetail to set
	 */
	public void setPermitDetail(String permitDetail) {
		this.permitDetail = permitDetail;
	}

	/**
	 * @return the cityCountry
	 */
	public String getCityCountry() {
		return cityCountry;
	}

	/**
	 * @param cityCountry
	 *            the cityCountry to set
	 */
	public void setCityCountry(String cityCountry) {
		this.cityCountry = cityCountry;
	}

	/**
	 * @return the cityCountryName
	 */
	public String getCityCountryName() {
		return cityCountryName;
	}

	/**
	 * @param cityCountryName
	 *            the cityCountryName to set
	 */
	public void setCityCountryName(String cityCountryName) {
		this.cityCountryName = cityCountryName;
	}

	/**
	 * @return the permitPhone
	 */
	public String getPermitPhone() {
		return permitPhone;
	}

	/**
	 * @param permitPhone
	 *            the permitPhone to set
	 */
	public void setPermitPhone(String permitPhone) {
		this.permitPhone = permitPhone;
	}

	/**
	 * @return the priorityMoveinFlag
	 */
	public String getPriorityMoveinFlag() {
		return priorityMoveinFlag;
	}

	/**
	 * @param priorityMoveinFlag
	 *            the priorityMoveinFlag to set
	 */
	public void setPriorityMoveinFlag(String priorityMoveinFlag) {
		this.priorityMoveinFlag = priorityMoveinFlag;
	}

	/**
	 * @return the htmlCaptureFlag
	 */
	public String getHtmlCaptureFlag() {
		return htmlCaptureFlag;
	}

	/**
	 * @param htmlCaptureFlag
	 *            the htmlCaptureFlag to set
	 */
	public void setHtmlCaptureFlag(String htmlCaptureFlag) {
		this.htmlCaptureFlag = htmlCaptureFlag;
	}

	/**
	 * @return the nonCommodityProduct
	 */
	public String getNonCommodityProduct() {
		return nonCommodityProduct;
	}

	/**
	 * @param nonCommodityProduct
	 *            the nonCommodityProduct to set
	 */
	public void setNonCommodityProduct(String nonCommodityProduct) {
		this.nonCommodityProduct = nonCommodityProduct;
	}

	/**
	 * @return the prepayFlag
	 */
	public String getPrepayFlag() {
		return prepayFlag;
	}

	/**
	 * @param prepayFlag
	 *            the prepayFlag to set
	 */
	public void setPrepayFlag(String prepayFlag) {
		this.prepayFlag = prepayFlag;
	}

	/**
	 * @return the prepayTotalToday
	 */
	public String getPrepayTotalToday() {
		return prepayTotalToday;
	}

	/**
	 * @param prepayTotalToday
	 *            the prepayTotalToday to set
	 */
	public void setPrepayTotalToday(String prepayTotalToday) {
		this.prepayTotalToday = prepayTotalToday;
	}

	/**
	 * @return the prepayDocId
	 */
	public String getPrepayDocId() {
		return prepayDocId;
	}

	/**
	 * @param prepayDocId
	 *            the prepayDocId to set
	 */
	public void setPrepayDocId(String prepayDocId) {
		this.prepayDocId = prepayDocId;
	}

	/**
	 * @return the ecoShare
	 */
	public String getEcoShare() {
		return ecoShare;
	}

	/**
	 * @param ecoShare
	 *            the ecoShare to set
	 */
	public void setEcoShare(String ecoShare) {
		this.ecoShare = ecoShare;
	}

	/**
	 * @return the activeCustomerFlag
	 */
	public String getActiveCustomerFlag() {
		return activeCustomerFlag;
	}

	/**
	 * @param activeCustomerFlag
	 *            the activeCustomerFlag to set
	 */
	public void setActiveCustomerFlag(String activeCustomerFlag) {
		this.activeCustomerFlag = activeCustomerFlag;
	}

	/**
	 * @return the addressMatchFlag
	 */
	public String getAddressMatchFlag() {
		return addressMatchFlag;
	}

	/**
	 * @param addressMatchFlag
	 *            the addressMatchFlag to set
	 */
	public void setAddressMatchFlag(String addressMatchFlag) {
		this.addressMatchFlag = addressMatchFlag;
	}

	/**
	 * @return the pendingBalanceFlag
	 */
	public String getPendingBalanceFlag() {
		return pendingBalanceFlag;
	}

	/**
	 * @param pendingBalanceFlag
	 *            the pendingBalanceFlag to set
	 */
	public void setPendingBalanceFlag(String pendingBalanceFlag) {
		this.pendingBalanceFlag = pendingBalanceFlag;
	}

	/**
	 * @return the bpActiveContract
	 */
	public String getBpActiveContract() {
		return bpActiveContract;
	}

	/**
	 * @param bpActiveContract
	 *            the bpActiveContract to set
	 */
	public void setBpActiveContract(String bpActiveContract) {
		this.bpActiveContract = bpActiveContract;
	}

	/**
	 * @return the matchedPartnerId
	 */
	public String getMatchedPartnerId() {
		return matchedPartnerId;
	}

	/**
	 * @param matchedPartnerId
	 *            the matchedPartnerId to set
	 */
	public void setMatchedPartnerId(String matchedPartnerId) {
		this.matchedPartnerId = matchedPartnerId;
	}

	/**
	 * @return the addressSearchPerformed
	 */
	public String getAddressSearchPerformed() {
		return addressSearchPerformed;
	}

	/**
	 * @param addressSearchPerformed
	 *            the addressSearchPerformed to set
	 */
	public void setAddressSearchPerformed(String addressSearchPerformed) {
		this.addressSearchPerformed = addressSearchPerformed;
	}

	/**
	 * @return the bpMatchNoCcsResponse
	 */
	public String getBpMatchNoCcsResponse() {
		return bpMatchNoCcsResponse;
	}

	/**
	 * @param bpMatchNoCcsResponse
	 *            the bpMatchNoCcsResponse to set
	 */
	public void setBpMatchNoCcsResponse(String bpMatchNoCcsResponse) {
		this.bpMatchNoCcsResponse = bpMatchNoCcsResponse;
	}

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType
	 *            the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the productSkuCode
	 */
	public String getProductSkuCode() {
		return productSkuCode;
	}

	/**
	 * @param productSkuCode
	 *            the productSkuCode to set
	 */
	public void setProductSkuCode(String productSkuCode) {
		this.productSkuCode = productSkuCode;
	}

	/**
	 * @return the planName
	 */
	public String getPlanName() {
		return planName;
	}

	/**
	 * @param planName
	 *            the planName to set
	 */
	public void setPlanName(String planName) {
		this.planName = planName;
	}

	/**
	 * @return the enrollSource
	 */
	public String getEnrollSource() {
		return enrollSource;
	}

	/**
	 * @param enrollSource
	 *            the enrollSource to set
	 */
	public void setEnrollSource(String enrollSource) {
		this.enrollSource = enrollSource;
	}

	/**
	 * @return the activationFee
	 */
	public String getActivationFee() {
		return activationFee;
	}

	/**
	 * @param activationFee
	 *            the activationFee to set
	 */
	public void setActivationFee(String activationFee) {
		this.activationFee = activationFee;
	}

	/**
	 * @return the bondPrice
	 */
	public String getBondPrice() {
		return bondPrice;
	}

	/**
	 * @param bondPrice
	 *            the bondPrice to set
	 */
	public void setBondPrice(String bondPrice) {
		this.bondPrice = bondPrice;
	}

	/**
	 * @return the accSecStatus
	 */
	public String getAccSecStatus() {
		return accSecStatus;
	}

	/**
	 * @param accSecStatus
	 *            the accSecStatus to set
	 */
	public void setAccSecStatus(String accSecStatus) {
		this.accSecStatus = accSecStatus;
	}

	/**
	 * @return the isPayUpFront
	 */
	public String getIsPayUpFront() {
		return isPayUpFront;
	}

	/**
	 * @param isPayUpFront
	 *            the isPayUpFront to set
	 */
	public void setIsPayUpFront(String isPayUpFront) {
		this.isPayUpFront = isPayUpFront;
	}

	/**
	 * @return the securityMethod
	 */
	public String getSecurityMethod() {
		return securityMethod;
	}

	/**
	 * @param securityMethod
	 *            the securityMethod to set
	 */
	public void setSecurityMethod(String securityMethod) {
		this.securityMethod = securityMethod;
	}

	/**
	 * @return the activationFeeCode
	 */
	public String getActivationFeeCode() {
		return activationFeeCode;
	}

	/**
	 * @param activationFeeCode
	 *            the activationFeeCode to set
	 */
	public void setActivationFeeCode(String activationFeeCode) {
		this.activationFeeCode = activationFeeCode;
	}

	/**
	 * @return the meterType
	 */
	public String getMeterType() {
		return meterType;
	}

	/**
	 * @param meterType
	 *            the meterType to set
	 */
	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	/**
	 * @return the switchHoldStatus
	 */
	public String getSwitchHoldStatus() {
		return switchHoldStatus;
	}

	/**
	 * @param switchHoldStatus
	 *            the switchHoldStatus to set
	 */
	public void setSwitchHoldStatus(String switchHoldStatus) {
		this.switchHoldStatus = switchHoldStatus;
	}

	/**
	 * @return the recentDisconnectFlag
	 */
	public String getRecentDisconnectFlag() {
		return recentDisconnectFlag;
	}

	/**
	 * @param recentDisconnectFlag
	 *            the recentDisconnectFlag to set
	 */
	public void setRecentDisconnectFlag(String recentDisconnectFlag) {
		this.recentDisconnectFlag = recentDisconnectFlag;
	}

	/**
	 * @return the premiseType
	 */
	public String getPremiseType() {
		return premiseType;
	}

	/**
	 * @param premiseType
	 *            the premiseType to set
	 */
	public void setPremiseType(String premiseType) {
		this.premiseType = premiseType;
	}

	/**
	 * @return the esidStatus
	 */
	public String getEsidStatus() {
		return esidStatus;
	}

	/**
	 * @param esidStatus
	 *            the esidStatus to set
	 */
	public void setEsidStatus(String esidStatus) {
		this.esidStatus = esidStatus;
	}

	/**
	 * @return the verifyAdditionalOptions
	 */
	public String getVerifyAdditionalOptions() {
		return verifyAdditionalOptions;
	}

	/**
	 * @param verifyAdditionalOptions
	 *            the verifyAdditionalOptions to set
	 */
	public void setVerifyAdditionalOptions(String verifyAdditionalOptions) {
		this.verifyAdditionalOptions = verifyAdditionalOptions;
	}

	/**
	 * @return the ambProgramCode
	 */
	public String getAmbProgramCode() {
		return ambProgramCode;
	}

	/**
	 * @param ambProgramCode
	 *            the ambProgramCode to set
	 */
	public void setAmbProgramCode(String ambProgramCode) {
		this.ambProgramCode = ambProgramCode;
	}

	/**
	 * @return the paperlessProgramCode
	 */
	public String getPaperlessProgramCode() {
		return paperlessProgramCode;
	}

	/**
	 * @param paperlessProgramCode
	 *            the paperlessProgramCode to set
	 */
	public void setPaperlessProgramCode(String paperlessProgramCode) {
		this.paperlessProgramCode = paperlessProgramCode;
	}

	/**
	 * @return the rhsProgramCodeOne
	 */
	public String getRhsProgramCodeOne() {
		return rhsProgramCodeOne;
	}

	/**
	 * @param rhsProgramCodeOne
	 *            the rhsProgramCodeOne to set
	 */
	public void setRhsProgramCodeOne(String rhsProgramCodeOne) {
		this.rhsProgramCodeOne = rhsProgramCodeOne;
	}

	/**
	 * @return the rhsProgramCodeTwo
	 */
	public String getRhsProgramCodeTwo() {
		return rhsProgramCodeTwo;
	}

	/**
	 * @param rhsProgramCodeTwo
	 *            the rhsProgramCodeTwo to set
	 */
	public void setRhsProgramCodeTwo(String rhsProgramCodeTwo) {
		this.rhsProgramCodeTwo = rhsProgramCodeTwo;
	}

	/**
	 * @return the rhsOfferType
	 */
	public String getRhsOfferType() {
		return rhsOfferType;
	}

	/**
	 * @param rhsOfferType
	 *            the rhsOfferType to set
	 */
	public void setRhsOfferType(String rhsOfferType) {
		this.rhsOfferType = rhsOfferType;
	}

	/**
	 * @return the freqFlyerFirstName
	 */
	public String getFreqFlyerFirstName() {
		return freqFlyerFirstName;
	}

	/**
	 * @param freqFlyerFirstName
	 *            the freqFlyerFirstName to set
	 */
	public void setFreqFlyerFirstName(String freqFlyerFirstName) {
		this.freqFlyerFirstName = freqFlyerFirstName;
	}

	/**
	 * @return the freqFlyerLastName
	 */
	public String getFreqFlyerLastName() {
		return freqFlyerLastName;
	}

	/**
	 * @param freqFlyerLastName
	 *            the freqFlyerLastName to set
	 */
	public void setFreqFlyerLastName(String freqFlyerLastName) {
		this.freqFlyerLastName = freqFlyerLastName;
	}

	/**
	 * @return the referralId
	 */
	public String getReferralId() {
		return referralId;
	}

	/**
	 * @param referralId
	 *            the referralId to set
	 */
	public void setReferralId(String referralId) {
		this.referralId = referralId;
	}

	/**
	 * @return the recentCallMade
	 */
	public String getRecentCallMade() {
		return recentCallMade;
	}

	/**
	 * @param recentCallMade
	 *            the recentCallMade to set
	 */
	public void setRecentCallMade(String recentCallMade) {
		this.recentCallMade = recentCallMade;
	}

	/**
	 * @return the nestSid
	 */
	public String getNestSid() {
		return nestSid;
	}

	/**
	 * @param nestSid
	 *            the nestSid to set
	 */
	public void setNestSid(String nestSid) {
		this.nestSid = nestSid;
	}

	/**
	 * @return the gzProductId
	 */
	public String getGzProductId() {
		return gzProductId;
	}

	/**
	 * @param gzProductId
	 *            the gzProductId to set
	 */
	public void setGzProductId(String gzProductId) {
		this.gzProductId = gzProductId;
	}

	/**
	 * @return the affiliateId
	 */
	public String getAffiliateId() {
		return affiliateId;
	}

	/**
	 * @param affiliateId
	 *            the affiliateId to set
	 */
	public void setAffiliateId(String affiliateId) {
		this.affiliateId = affiliateId;
	}

	/**
	 * @return the brandId
	 */
	public String getBrandId() {
		return brandId;
	}

	/**
	 * @param brandId
	 *            the brandId to set
	 */
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * @param companyCode
	 *            the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * @return the sunClub
	 */
	public String getSunClub() {
		return sunClub;
	}

	/**
	 * @param sunClub
	 *            the sunClub to set
	 */
	public void setSunClub(String sunClub) {
		this.sunClub = sunClub;
	}

	/**
	 * @return the driverClub
	 */
	public String getDriverClub() {
		return driverClub;
	}

	/**
	 * @param driverClub
	 *            the driverClub to set
	 */
	public void setDriverClub(String driverClub) {
		this.driverClub = driverClub;
	}

	/**
	 * @return the giftCard
	 */
	public String getGiftCard() {
		return giftCard;
	}

	/**
	 * @param giftCard
	 *            the giftCard to set
	 */
	public void setGiftCard(String giftCard) {
		this.giftCard = giftCard;
	}

	/**
	 * @return the residentialSolar
	 */
	public String getResidentialSolar() {
		return residentialSolar;
	}

	/**
	 * @param residentialSolar
	 *            the residentialSolar to set
	 */
	public void setResidentialSolar(String residentialSolar) {
		this.residentialSolar = residentialSolar;
	}

	/**
	 * @return the messageCode
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * @param messageCode
	 *            the messageCode to set
	 */
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	/**
	 * @return the outErrorCode
	 */
	public String getOutErrorCode() {
		return outErrorCode;
	}

	/**
	 * @param outErrorCode
	 *            the outErrorCode to set
	 */
	public void setOutErrorCode(String outErrorCode) {
		this.outErrorCode = outErrorCode;
	}

	/**
	 * @return the offerCategory
	 */
	public String getOfferCategory() {
		return offerCategory;
	}

	/**
	 * @return the frequentFlyerNumber
	 */
	public String getFrequentFlyerNumber() {
		return frequentFlyerNumber;
	}

	/**
	 * @param frequentFlyerNumber
	 *            the frequentFlyerNumber to set
	 */
	public void setFrequentFlyerNumber(String frequentFlyerNumber) {
		this.frequentFlyerNumber = frequentFlyerNumber;
	}

	/**
	 * @param offerCategory
	 *            the offerCategory to set
	 */
	public void setOfferCategory(String offerCategory) {
		this.offerCategory = offerCategory;
	}

	/**
	 * @return the realtorId
	 */
	public String getRealtorId() {
		return realtorId;
	}

	/**
	 * @param realtorId
	 *            the realtorId to set
	 */
	public void setRealtorId(String realtorId) {
		this.realtorId = realtorId;
	}

	/**
	 * @return the personResponse
	 */
	public PersonResponse getPersonResponse() {
		return personResponse;
	}

	/**
	 * @param personResponse
	 *            the personResponse to set
	 */
	public void setPersonResponse(PersonResponse personResponse) {
		this.personResponse = personResponse;
	}
	
	

	public String getPosidSNRO() {
		return posidSNRO;
	}

	public void setPosidSNRO(String posidSNRO) {
		this.posidSNRO = posidSNRO;
	}

	public String getErrorCdlist() {
		return errorCdlist;
	}

	public void setErrorCdlist(String errorCdlist) {
		this.errorCdlist = errorCdlist;
	}
	
	

	public String getBpMatchScenarioId() {
		return bpMatchScenarioId;
	}

	public void setBpMatchScenarioId(String bpMatchScenarioId) {
		this.bpMatchScenarioId = bpMatchScenarioId;
	}
	
	public String getProspectId() {
		return prospectId;
	}

	public void setProspectId(String prospectId) {
		this.prospectId = prospectId;
	}
	

	public String getProspectPreapprovalFlag() {
		return prospectPreapprovalFlag;
	}

	public void setProspectPreapprovalFlag(String prospectPreapprovalFlag) {
		this.prospectPreapprovalFlag = prospectPreapprovalFlag;
	}

	public String getProspectPartnerId() {
		return prospectPartnerId;
	}

	public void setProspectPartnerId(String prospectPartnerId) {
		this.prospectPartnerId = prospectPartnerId;
	}


	public String getCallExecutedFromDB() {
		return callExecutedFromDB;
	}

	public void setCallExecutedFromDB(String callExecutedFromDB) {
		this.callExecutedFromDB = callExecutedFromDB;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
