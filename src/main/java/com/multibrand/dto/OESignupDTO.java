package com.multibrand.dto;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.multibrand.util.CommonUtil;


/**
 * 
 * @author vsood30
 *
 */

//@Component("oeSignUpDTO")
public class OESignupDTO extends BaseDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6690933516952166000L;
	private String rumoving="";
	private String pjm="";
	private String businessPartnerID="";
	private String idocNumber="";
	private String trackingNumber="";
	private String serviceReqTypeCd=""; //MVI or SWI
	private String reqStatusCd="";
	private String providerAgreementFlag="";
	private String contractAccountNum="";
	private String serviceStartDate="";
	private String guid="";
	private String signupChannelCode="";
	private String accountName="";
	private String geoZone="";
    private String tdspCode="";
	private String tdspCodeCCS="";
	private String tdspName="";
	private String checkDigit="";
	private String serviceZipOverrideFlag="N";		//YES or NO
	private String serviceAddressOverrideFlag="";	//YES or NO
	private String sameBillingServiceAddressFlag="";	//YES or NO
	private String billingAddOverrideFlag="";
	private String errorCode="";
	private String dwellingType="";
	private String recentPageAccessed="";
	private String recentCallMade="";
	private String nonCommodityProduct="";
	private String prepayDocId="";
	private String productSKUCode="";
	private String enrollSource="";
	private String verifyAdditionalOptions="";
	private String ambProgramCode="";
	private String paperlessProgramCode="";
	private String rhsProgramCodeOne="";
	private String rhsProgramCodeTwo="";
	private String rhsOfferType="";
	private String frequentFlyerNumber="";
	private String frequentFlyerFirstName="";
	private String frequentFlyerLastName="";
	private String referralID="";
	private String esidMatchFlag="";
	private String esidNumber="";
	private String localeLanguageCode ="";
	private String transactionType="";
	private String offerDate="";
	private String offerTime="";
	private String cid="";					//	CEP partner ID
	private String fromLandingPage=""; //For e.g. SmartStartLanding
	private String promoCodeEntered=""; //Promo code entered by the customer or present in vanity URL
	private String offerCodeFromVanity=""; //Offer code coming from vanity URL
	private String productType=""; //Used by ARE
	

	private String pjmAgentId=""; //Passed in vanity URL
	private String program=""; //Passed in vanity URL
	private String agreementNumber="";// passed in session for use during DOD enrollment
	
	private String esidEligibility="";
	private String newConstruction =""; //Yes or No
	private String mobileHomeOrRewire=""; //Yes or No
	private String previousProviderName=""; //Previous provider name for Switching
	private String previousProviderCd=""; //Previous provider id for Switching
	private String nestSID=""; //NEST SID received from NEST website. Can be null
	private String realtorID=""; //Used for Moving and Conceirge (MC)
	private String promoError=""; //In case of invalid promo, Populated with MSG_CCSERR_8_GET_PROMO_OFFERS or MSG_CCSERR_E_GET_PROMO_OFFERS
	private String httpReferrer=""; 
	private String tdspHolidayDates=""; //Holds TDSP Specific Holidays returned from CCS
	private String meterTypePTU=""; //Permanent, Temporary, Unsure
	private String smartStartlandingPageError=null;
	
	

/* START GOALZERO OE:AU */
	private String gz_product_id=null;
	private String applicationArea=null;
/* END GOALZERO OE:AU */
	
	/*
	private OfferDTO alternateOfferDTO;
	private PersonDTO person;
	private AddressDTO serviceAddress;
	private AddressDTO mailingAddressDTO;
	
	
	private PaymentDTO depositPaymentDTO;
	
	private EPlanDTO eplanDTO;
	private PermitDTO permitDTO;
	private BPMatchDTO bpMatch;
	private PrepayDTO prepayDTO;
	private ServiceStartDateDTO serviceStartDateDTO;*/

	private boolean htmlCapture=false;
	private boolean autoPayFlag=false; 
	private boolean esuiteFlag=false;
	private boolean priorityMovein=false;
	private boolean bpMatchFlag=false;
	private boolean prepayOnly=false;
	private boolean weeklyEnrollmentFlag=false;
	private boolean multipleOffers=true;
	private boolean esenseFlag=false;
	private boolean amsrOffered=false;
	private boolean enrollmentAttempted=false;
	
	// Enrollment properties - Y. Jenith
	private AddressDTO billingAddress;
	private AddressDTO serviceAddress;
	private AddressDTO mailAddress;
	private OfferDTO selectedOffer;
	private PersonDTO person;
	private BPMatchDTO bpMatch;
	private CreditCheckDTO creditCheck;
	private ESIDDTO esid;
	private RhsDTO rhs;
	private String bpMatchText = null;	
	private String isLightboxDisplayed;
	private String chkDodEnrollment;
	private String ebillFlag = null;
	private String emailSubscription = null;
	private String thirdPartyEmailSharing = null; 
	private String eveningPhoneNo1;
	private String eveningPhoneNo2;
	private String eveningPhoneNo3;
	private String errorVariable = null;
	private boolean enrolled = false;
	// Enrollment properties - Y. Jenith
	
	private String switchHoldStatus = null;
	
	private int numberOfPosidTrys=0;
	
	// PC redesign Changes by Msadriw1- Start
	private boolean pcVisitFlag = false ;
	// PC redesign Changes by Msadriw1- Start
	
	private EPlanDTO eplanDTO;
	private PermitDTO permitDTO;
	private PrepayDTO prepayDTO;
	
	private String kbaSuggestionFlag;
	
	public OESignupDTO() {
		
	}
	
	//START : OE :Sprint61 :US21009 :Kdeshmu1
		String agentID;
		String agentType;
		String agentFirstName;
		String agentLastName;
		String vendorCode;
		String vendorName;
		String tlpReportApiStatus;
		String errorCdList;
		String systemNotes;
		private String ccsAgentUpdateStatus;
		///Start : OE : Sprint3 : 13643 - Add Missing Columns to  SLA table :Kdeshmu1
		String tpvStatus;
		String campaignCd;
		String pdfCaptureFlag;
		String agentUpResponse;
		String kbaTransactionKey;
		
		public String getPdfCaptureFlag() {
			return pdfCaptureFlag;
		}

		public void setPdfCaptureFlag(String pdfCaptureFlag) {
			this.pdfCaptureFlag = pdfCaptureFlag;
		}

		public String getAgentUpResponse() {
			return agentUpResponse;
		}

		public void setAgentUpResponse(String agentUpResponse) {
			this.agentUpResponse = agentUpResponse;
		}

		public String getKbaTransactionKey() {
			return kbaTransactionKey;
		}

		public void setKbaTransactionKey(String kbaTransactionKey) {
			this.kbaTransactionKey = kbaTransactionKey;
		}

		public String getTpvStatus() {
			return tpvStatus;
		}

		public void setTpvStatus(String tpvStatus) {
			this.tpvStatus = tpvStatus;
		}

		///END : OE : Sprint3 : 13643 - Add Missing Columns to  SLA table :Kdeshmu1
		public String getCampaignCd() {
			return campaignCd;
		}

		public void setCampaignCd(String campaignCd) {
			this.campaignCd = campaignCd;
		}

		public String getCcsAgentUpdateStatus() {
			return ccsAgentUpdateStatus;
		}

		public void setCcsAgentUpdateStatus(String ccsAgentUpdateStatus) {
			this.ccsAgentUpdateStatus = ccsAgentUpdateStatus;
		}
		
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

		public String getErrorCdList() {
			return errorCdList;
		}

		public void setErrorCdList(String errorCdList) {
			this.errorCdList = errorCdList;
		}

		public String getSystemNotes() {
			return systemNotes;
		}

		public void setSystemNotes(String systemNotes) {
			this.systemNotes = systemNotes;
		}

		//END : OE :Sprint61 :US21009 :Kdeshmu1
	public String getRumoving() {
		return rumoving;
	}

	public void setRumoving(String rumoving) {
		this.rumoving = rumoving;
	}

	public String getBusinessPartnerID() {
		return businessPartnerID;
	}

	public void setBusinessPartnerID(String businessPartnerID) {
		this.businessPartnerID = businessPartnerID;
	}
	
	public String getIdocNumber() {
		return idocNumber;
	}

	public void setIdocNumber(String idocNumber) {
		this.idocNumber = idocNumber;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getServiceReqTypeCd() {
		return serviceReqTypeCd;
	}

	public void setServiceReqTypeCd(String serviceReqTypeCd) {
		this.serviceReqTypeCd = serviceReqTypeCd;
	}

	public String getReqStatusCd() {
		return reqStatusCd;
	}

	public void setReqStatusCd(String reqStatusCd) {
		this.reqStatusCd = reqStatusCd;
	}

	public String getProviderAgreementFlag() {
		return providerAgreementFlag;
	}

	public void setProviderAgreementFlag(String providerAgreementFlag) {
		this.providerAgreementFlag = providerAgreementFlag;
	}

	public String getContractAccountNum() {
		return contractAccountNum;
	}

	public void setContractAccountNum(String contractAccountNum) {
		this.contractAccountNum = contractAccountNum;
	}

	public String getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(String serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public String getGuid() {
		if(StringUtils.isBlank(guid))
		{
			UUID uuID= UUID.randomUUID();
			setGuid(uuID.toString());
		}
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getSignupChannelCode() {
		return signupChannelCode;
	}

	public void setSignupChannelCode(String signupChannelCode) {
		this.signupChannelCode = signupChannelCode;
	}
	
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getGeoZone() {
		return geoZone;
	}

	public void setGeoZone(String geoZone) {
		this.geoZone = geoZone;
	}

	public String getTdspCode() {
		return tdspCode;
	}

	public void setTdspCode(String tdspCode) {
		this.tdspCode = tdspCode;
	}

	public String getTdspCodeCCS() {
		return tdspCodeCCS;
	}

	public void setTdspCodeCCS(String tdspCodeCCS) {
		this.tdspCodeCCS = tdspCodeCCS;
	}

	public String getTdspName() {
		return tdspName;
	}

	public void setTdspName(String tdspName) {
		this.tdspName = tdspName;
	}

	public String getCheckDigit() {
		return checkDigit;
	}

	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}

	public String getBillingAddOverrideFlag() {
		return billingAddOverrideFlag;
	}

	public void setBillingAddOverrideFlag(String billingAddOverrideFlag) {
		this.billingAddOverrideFlag = billingAddOverrideFlag;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getDwellingType() {
		return dwellingType;
	}

	public void setDwellingType(String dwellingType) {
		this.dwellingType = dwellingType;
	}

	public String getRecentPageAccessed() {
		return recentPageAccessed;
	}

	public void setRecentPageAccessed(String recentPageAccessed) {
		this.recentPageAccessed = recentPageAccessed;
	}

	public String getRecentCallMade() {
		return recentCallMade;
	}

	public void setRecentCallMade(String recentCallMade) {
		this.recentCallMade = recentCallMade;
	}
	
	public String getNewConstruction() {
		return newConstruction;
	}

	public void setNewConstruction(String newConstruction) {
		this.newConstruction = newConstruction;
	}

	public String getMobileHomeOrRewire() {
		return mobileHomeOrRewire;
	}

	public void setMobileHomeOrRewire(String mobileHomeOrRewire) {
		this.mobileHomeOrRewire = mobileHomeOrRewire;
	}

	public String getPreviousProviderName() {
		return previousProviderName;
	}

	public void setPreviousProviderName(String previousProviderName) {
		this.previousProviderName = previousProviderName;
	}

	public String getPreviousProviderCd() {
		return previousProviderCd;
	}

	public void setPreviousProviderCd(String previousProviderCd) {
		this.previousProviderCd = previousProviderCd;
	}

	public String getNonCommodityProduct() {
		return nonCommodityProduct;
	}

	public void setNonCommodityProduct(String nonCommodityProduct) {
		this.nonCommodityProduct = nonCommodityProduct;
	}

	public String getPrepayDocId() {
		return prepayDocId;
	}

	public void setPrepayDocId(String prepayDocId) {
		this.prepayDocId = prepayDocId;
	}

	public String getProductSKUCode() {
		return productSKUCode;
	}

	public void setProductSKUCode(String productSKUCode) {
		this.productSKUCode = productSKUCode;
	}

	public String getEnrollSource() {
		return enrollSource;
	}

	public void setEnrollSource(String enrollSource) {
		this.enrollSource = enrollSource;
	}

	public String getVerifyAdditionalOptions() {
		return verifyAdditionalOptions;
	}

	public void setVerifyAdditionalOptions(String verifyAdditionalOptions) {
		this.verifyAdditionalOptions = verifyAdditionalOptions;
	}

	public String getAmbProgramCode() {
		return ambProgramCode;
	}

	public void setAmbProgramCode(String ambProgramCode) {
		this.ambProgramCode = ambProgramCode;
	}

	public String getPaperlessProgramCode() {
		return paperlessProgramCode;
	}

	public void setPaperlessProgramCode(String paperlessProgramCode) {
		this.paperlessProgramCode = paperlessProgramCode;
	}

	public String getRhsProgramCodeOne() {
		return rhsProgramCodeOne;
	}

	public void setRhsProgramCodeOne(String rhsProgramCodeOne) {
		this.rhsProgramCodeOne = rhsProgramCodeOne;
	}

	public String getRhsProgramCodeTwo() {
		return rhsProgramCodeTwo;
	}

	public void setRhsProgramCodeTwo(String rhsProgramCodeTwo) {
		this.rhsProgramCodeTwo = rhsProgramCodeTwo;
	}

	public String getRhsOfferType() {
		return rhsOfferType;
	}

	public void setRhsOfferType(String rhsOfferType) {
		this.rhsOfferType = rhsOfferType;
	}

	public String getFrequentFlyerNumber() {
		return frequentFlyerNumber;
	}

	public void setFrequentFlyerNumber(String frequentFlyerNumber) {
		this.frequentFlyerNumber = frequentFlyerNumber;
	}

	public String getFrequentFlyerFirstName() {
		return frequentFlyerFirstName;
	}

	public void setFrequentFlyerFirstName(String frequentFlyerFirstName) {
		this.frequentFlyerFirstName = frequentFlyerFirstName;
	}

	public String getFrequentFlyerLastName() {
		return frequentFlyerLastName;
	}

	public void setFrequentFlyerLastName(String frequentFlyerLastName) {
		this.frequentFlyerLastName = frequentFlyerLastName;
	}

	public String getReferralID() {
		return referralID;
	}

	public void setReferralID(String referralID) {
		this.referralID = referralID;
	}

	/*
	public OfferDTO getSelectedOfferDTO() {
		if(null==selectedOffer){
			selectedOffer = new OfferDTO();
			setSelectedOfferDTO(selectedOffer);
		}
		return selectedOffer;
	}

	public void setSelectedOfferDTO(OfferDTO selectedOffer) {
		this.selectedOffer = selectedOffer;
	}

	public OfferDTO getAlternateOfferDTO() {
		if(null==alternateOfferDTO){
			alternateOfferDTO = new OfferDTO();
			setAlternateOfferDTO(alternateOfferDTO);
		}
		return alternateOfferDTO;
	}

	public void setAlternateOfferDTO(OfferDTO alternateOfferDTO) {
		this.alternateOfferDTO = alternateOfferDTO;
	}

	

	

	public AddressDTO getMailingAddressDTO() {
		if(null==mailingAddressDTO){
			mailingAddressDTO = new AddressDTO();
			setMailingAddressDTO(mailingAddressDTO);
		}
		return mailingAddressDTO;
	}

	public void setMailingAddressDTO(AddressDTO mailingAddressDTO) {
		this.mailingAddressDTO = mailingAddressDTO;
	}



	public PaymentDTO getDepositPaymentDTO() {
		if(null==depositPaymentDTO){
			depositPaymentDTO = new PaymentDTO();
			setDepositPaymentDTO(depositPaymentDTO);
		}
		return depositPaymentDTO;
	}

	public void setDepositPaymentDTO(PaymentDTO depositPaymentDTO) {
		this.depositPaymentDTO = depositPaymentDTO;
	}

	

	public EPlanDTO getEplanDTO() {
		if(null==eplanDTO){
			eplanDTO = new EPlanDTO();
			setEplanDTO(eplanDTO);
		}
		return eplanDTO;
	}

	public void setEplanDTO(EPlanDTO eplanDTO) {
		this.eplanDTO = eplanDTO;
	}

	public PermitDTO getPermitDTO() {
		if(null==permitDTO){
			permitDTO = new PermitDTO();
			setPermitDTO(permitDTO);
		}
		return permitDTO;
	}

	public void setPermitDTO(PermitDTO permitDTO) {
		this.permitDTO = permitDTO;
	}

*/
	
	public String getTransactionType() {
		return transactionType;
	}
	
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getServiceZipOverrideFlag() {
		return serviceZipOverrideFlag;
	}

	public void setServiceZipOverrideFlag(String serviceZipOverrideFlag) {
		this.serviceZipOverrideFlag = serviceZipOverrideFlag;
	}

	public String getServiceAddressOverrideFlag() {
		return serviceAddressOverrideFlag;
	}

	public void setServiceAddressOverrideFlag(String serviceAddressOverrideFlag) {
		this.serviceAddressOverrideFlag = serviceAddressOverrideFlag;
	}

	public String getSameBillingServiceAddressFlag() {
		return sameBillingServiceAddressFlag;
	}

	public void setSameBillingServiceAddressFlag(
			String sameBillingServiceAddressFlag) {
		this.sameBillingServiceAddressFlag = sameBillingServiceAddressFlag;
	}

	public String getEsidMatchFlag() {
		return esidMatchFlag;
	}

	public void setEsidMatchFlag(String esidMatchFlag) {
		this.esidMatchFlag = esidMatchFlag;
	}

	public String getEsidNumber() {
		return esidNumber;
	}

	public void setEsidNumber(String esidNumber) {
		this.esidNumber = esidNumber;
	}

	public String getLocaleLanguageCode() {
		return localeLanguageCode;
	}

	public void setLocaleLanguageCode(String localeLanguageCode) {
		this.localeLanguageCode = localeLanguageCode;
	}

/*	public PrepayDTO getPrepayDTO() {
		return prepayDTO;
	}

	public void setPrepayDTO(PrepayDTO prepayDTO) {
		this.prepayDTO = prepayDTO;
	}*/

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

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getFromLandingPage() {
		return fromLandingPage;
	}

	public void setFromLandingPage(String fromLandingPage) {
		this.fromLandingPage = fromLandingPage;
	}

	public String getPromoCodeEntered() {
		return promoCodeEntered;
	}

	public void setPromoCodeEntered(String promoCodeEntered) {
		this.promoCodeEntered = promoCodeEntered;
	}

	public String getOfferCodeFromVanity() {
		return offerCodeFromVanity;
	}

	public void setOfferCodeFromVanity(String offerCodeFromVanity) {
		this.offerCodeFromVanity = offerCodeFromVanity;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getPjmAgentId() {
		return pjmAgentId;
	}

	public void setPjmAgentId(String pjmAgentId) {
		this.pjmAgentId = pjmAgentId;
	}


	public String getEsidEligibility() {
		return esidEligibility;
	}

	public void setEsidEligibility(String esidEligibility) {
		this.esidEligibility = esidEligibility;
	}

	public String getNestSID() {
		return nestSID;
	}

	public void setNestSID(String nestSID) {
		this.nestSID = nestSID;
	}

	public String getRealtorID() {
		return realtorID;
	}

	public void setRealtorID(String realtorID) {
		this.realtorID = realtorID;
	}

	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}
	
	public String getPjm() {
		return pjm;
	}

	public void setPjm(String pjm) {
		this.pjm = pjm;
	}

	public String getPromoError() {
		return promoError;
	}

	public void setPromoError(String promoError) {
		this.promoError = promoError;
	}
	
	public boolean isHtmlCapture() {
		return htmlCapture;
	}

	public void setHtmlCapture(boolean htmlCapture) {
		this.htmlCapture = htmlCapture;
	}

	public boolean isAutoPayFlag() {
		return autoPayFlag;
	}

	public void setAutoPayFlag(boolean autoPayFlag) {
		this.autoPayFlag = autoPayFlag;
	}
	
	public boolean isEsuiteFlag() {
		return esuiteFlag;
	}

	public void setEsuiteFlag(boolean esuiteFlag) {
		this.esuiteFlag = esuiteFlag;
	}

	public boolean isPriorityMovein() {
		return priorityMovein;
	}

	public void setPriorityMovein(boolean priorityMovein) {
		this.priorityMovein = priorityMovein;
	}

	public boolean isBpMatchFlag() {
		return bpMatchFlag;
	}

	public void setBpMatchFlag(boolean bpMatchFlag) {
		this.bpMatchFlag = bpMatchFlag;
	}

	public boolean isPrepayOnly() {
		return prepayOnly;
	}

	public void setPrepayOnly(boolean prepayOnly) {
		this.prepayOnly = prepayOnly;
	}

	public boolean isWeeklyEnrollmentFlag() {
		return weeklyEnrollmentFlag;
	}

	public void setWeeklyEnrollmentFlag(boolean weeklyEnrollmentFlag) {
		this.weeklyEnrollmentFlag = weeklyEnrollmentFlag;
	}

	public boolean isMultipleOffers() {
		return multipleOffers;
	}

	public void setMultipleOffers(boolean multipleOffers) {
		this.multipleOffers = multipleOffers;
	}

	public boolean isEsenseFlag() {
		return esenseFlag;
	}

	public void setEsenseFlag(boolean esenseFlag) {
		this.esenseFlag = esenseFlag;
	}

	public String getHttpReferrer() {
		return httpReferrer;
	}

	public void setHttpReferrer(String httpReferrer) {
		this.httpReferrer = httpReferrer;
	}

	public String getTdspHolidayDates() {
		return tdspHolidayDates;
	}

	public void setTdspHolidayDates(String tdspHolidayDates) {
		this.tdspHolidayDates = tdspHolidayDates;
	}

	public boolean isAmsrOffered() {
		return amsrOffered;
	}

	public void setAmsrOffered(boolean amsrOffered) {
		this.amsrOffered = amsrOffered;
	}
	
	public int getNumberOfPosidTrys() {
		return numberOfPosidTrys;
	}

	public void setNumberOfPosidTrys(int numberOfPosidTrys) {
		this.numberOfPosidTrys = numberOfPosidTrys;
	}

	public String getMeterTypePTU() {
		return meterTypePTU;
	}

	public void setMeterTypePTU(String meterTypePTU) {
		this.meterTypePTU = meterTypePTU;
	}

/*	public ServiceStartDateDTO getServiceStartDateDTO() {
		return serviceStartDateDTO;
	}

	public void setServiceStartDateDTO(ServiceStartDateDTO serviceStartDateDTO) {
		this.serviceStartDateDTO = serviceStartDateDTO;
	}*/

	public String getGz_product_id() {
		return gz_product_id;
	}

	public void setGz_product_id(String gz_product_id) {
		this.gz_product_id = gz_product_id;
	}

	public boolean isEnrollmentAttempted() {
		return enrollmentAttempted;
	}

	public void setEnrollmentAttempted(boolean enrollmentAttempted) {
		this.enrollmentAttempted = enrollmentAttempted;
	}

	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}

	public String getSmartStartlandingPageError() {
		return smartStartlandingPageError;
	}

	public void setSmartStartlandingPageError(String smartStartlandingPageError) {
		this.smartStartlandingPageError = smartStartlandingPageError;
	}
	
	
	public String printOETrackingID()
	{
		StringBuffer trackingId = new StringBuffer();
		trackingId.append('[');
		if(StringUtils.isNotBlank(this.trackingNumber)){
			trackingId.append("OETrackingNo ::"+this.trackingNumber);}
		else{
		    trackingId.append("OETrackingNo :: Not Generated");}	
		trackingId.append("] ");
		return trackingId.toString();
		
	}

	public String getApplicationArea() {
		return applicationArea;
	}

	public void setApplicationArea(String applicationArea) {
		this.applicationArea = applicationArea;
	}

	public boolean isPcVisitFlag() {
		return pcVisitFlag;
	}

	public void setPcVisitFlag(boolean pcVisitFlag) {
		this.pcVisitFlag = pcVisitFlag;
	}

	public AddressDTO getBillingAddress() {
		if(null==billingAddress){
			billingAddress = new AddressDTO();
			setBillingAddress(billingAddress);
		}
		return billingAddress;
	}

	public void setBillingAddress(AddressDTO billingAddressDTO) {
		this.billingAddress = billingAddressDTO;
	}
	
	
	public OfferDTO getSelectedOffer() {
		if(null==selectedOffer){
			selectedOffer = new OfferDTO();
			setSelectedOffer(selectedOffer);
		}
		return selectedOffer;
	}

	public void setSelectedOffer(OfferDTO selectedOffer) {
		this.selectedOffer = selectedOffer;
	}
	
	public PersonDTO getPerson() {
		if(null==person){
			person = new PersonDTO();
			setPerson(person);
		}
		return person;
	}

	public void setPerson(PersonDTO person) {
		this.person = person;
	}

	public AddressDTO getServiceAddress() {
		if(null==serviceAddress){
			serviceAddress = new AddressDTO();
			setServiceAddress(serviceAddress);
		}
		return serviceAddress;
	}

	public void setServiceAddress(AddressDTO serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	
	/**
	 * @return the mailAddress
	 */
	public AddressDTO getMailAddress() {
		if(mailAddress == null){
			mailAddress = new AddressDTO();
			setMailAddress(mailAddress);
		}
		return mailAddress;
	}

	/**
	 * @param mailAddress the mailAddress to set
	 */
	public void setMailAddress(AddressDTO mailAddress) {
		this.mailAddress = mailAddress;
	}

	public BPMatchDTO getBpMatch() {
		if(null==bpMatch){
			bpMatch = new BPMatchDTO();
			setBpMatch(bpMatch);
		}
		return bpMatch;
	}

	public void setBpMatch(BPMatchDTO bpMatch) {
		this.bpMatch = bpMatch;
	}
	
	public CreditCheckDTO getCreditCheck() {
		if(null==creditCheck){
			creditCheck = new CreditCheckDTO();
			setCreditCheck(creditCheck);
		}
		return creditCheck;
	}

	public void setCreditCheck(CreditCheckDTO creditCheck) {
		this.creditCheck = creditCheck;
	}

	/**
	 * @return the switchHoldStatus
	 */
	public String getSwitchHoldStatus() {
		return switchHoldStatus;
	}

	/**
	 * @param switchHoldStatus the switchHoldStatus to set
	 */
	public void setSwitchHoldStatus(String switchHoldStatus) {
		this.switchHoldStatus = switchHoldStatus;
	}
	
	public ESIDDTO getEsid() {
		if(null==esid){
			esid = new ESIDDTO();
			setEsid(esid);
		}
		return esid;
	}

	public void setEsid(ESIDDTO esid) {
		this.esid = esid;
	}

	/**
	 * @return the rhs
	 */
	public RhsDTO getRhs() {
		return rhs;
	}

	/**
	 * @param rhs the rhs to set
	 */
	public void setRhs(RhsDTO rhs) {
		this.rhs = rhs;
	}

	/**
	 * @return the isLightboxDisplayed
	 */
	public String getIsLightboxDisplayed() {
		return isLightboxDisplayed;
	}

	/**
	 * @param isLightboxDisplayed the isLightboxDisplayed to set
	 */
	public void setIsLightboxDisplayed(String isLightboxDisplayed) {
		this.isLightboxDisplayed = isLightboxDisplayed;
	}

	/**
	 * @return the chkDodEnrollment
	 */
	public String getChkDodEnrollment() {
		return chkDodEnrollment;
	}

	/**
	 * @param chkDodEnrollment the chkDodEnrollment to set
	 */
	public void setChkDodEnrollment(String chkDodEnrollment) {
		this.chkDodEnrollment = chkDodEnrollment;
	}

	
	public EPlanDTO getEplanDTO() {
		if(null==eplanDTO){
			eplanDTO = new EPlanDTO();
			setEplanDTO(eplanDTO);
		}
		return eplanDTO;
	}

	public void setEplanDTO(EPlanDTO eplanDTO) {
		this.eplanDTO = eplanDTO;
	}
	
	public PermitDTO getPermitDTO() {
		if(null==permitDTO){
			permitDTO = new PermitDTO();
			setPermitDTO(permitDTO);
		}
		return permitDTO;
	}

	public void setPermitDTO(PermitDTO permitDTO) {
		this.permitDTO = permitDTO;
	}
	
	public PrepayDTO getPrepayDTO() {
		return prepayDTO;
	}

	public void setPrepayDTO(PrepayDTO prepayDTO) {
		this.prepayDTO = prepayDTO;
	}

	
	/**
	 * @return the ebillFlag
	 */
	public String getEbillFlag() {
		return ebillFlag;
	}

	/**
	 * @param ebillFlag the ebillFlag to set
	 */
	public void setEbillFlag(String ebillFlag) {
		this.ebillFlag = ebillFlag;
	}

	/**
	 * @return the bpMatchText
	 */
	public String getBpMatchText() {
		return bpMatchText;
	}

	/**
	 * @param bpMatchText the bpMatchText to set
	 */
	public void setBpMatchText(String bpMatchText) {
		this.bpMatchText = bpMatchText;
	}

	/**
	 * @return the emailSubscription
	 */
	public String getEmailSubscription() {
		return emailSubscription;
	}

	/**
	 * @param emailSubscription the emailSubscription to set
	 */
	public void setEmailSubscription(String emailSubscription) {
		this.emailSubscription = emailSubscription;
	}

	/**
	 * @return the thirdPartyEmailSharing
	 */
	public String getThirdPartyEmailSharing() {
		return thirdPartyEmailSharing;
	}

	/**
	 * @param thirdPartyEmailSharing the thirdPartyEmailSharing to set
	 */
	public void setThirdPartyEmailSharing(String thirdPartyEmailSharing) {
		this.thirdPartyEmailSharing = thirdPartyEmailSharing;
	}

	/**
	 * @return the eveningPhoneNo1
	 */
	public String getEveningPhoneNo1() {
		return eveningPhoneNo1;
	}

	/**
	 * @param eveningPhoneNo1 the eveningPhoneNo1 to set
	 */
	public void setEveningPhoneNo1(String eveningPhoneNo1) {
		this.eveningPhoneNo1 = eveningPhoneNo1;
	}

	/**
	 * @return the eveningPhoneNo2
	 */
	public String getEveningPhoneNo2() {
		return eveningPhoneNo2;
	}

	/**
	 * @param eveningPhoneNo2 the eveningPhoneNo2 to set
	 */
	public void setEveningPhoneNo2(String eveningPhoneNo2) {
		this.eveningPhoneNo2 = eveningPhoneNo2;
	}

	/**
	 * @return the eveningPhoneNo3
	 */
	public String getEveningPhoneNo3() {
		return eveningPhoneNo3;
	}

	/**
	 * @param eveningPhoneNo3 the eveningPhoneNo3 to set
	 */
	public void setEveningPhoneNo3(String eveningPhoneNo3) {
		this.eveningPhoneNo3 = eveningPhoneNo3;
	}

	/**
	 * @return the errorVariable
	 */
	public String getErrorVariable() {
		return errorVariable;
	}

	/**
	 * @param errorVariable the errorVariable to set
	 */
	public void setErrorVariable(String errorVariable) {
		this.errorVariable = errorVariable;
	}

	/**
	 * @return the enrolled
	 */
	public boolean isEnrolled() {
		return enrolled;
	}

	/**
	 * @param enrolled the enrolled to set
	 */
	public void setEnrolled(boolean enrolled) {
		this.enrolled = enrolled;
	}

	public String getKbaSuggestionFlag() {
		return kbaSuggestionFlag;
	}

	public void setKbaSuggestionFlag(String kbaSuggestionFlag) {
		this.kbaSuggestionFlag = kbaSuggestionFlag;
	}
	
	
}
