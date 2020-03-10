package com.multibrand.dto.request;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.multibrand.dao.jdbc.sp.Procedure;
import com.multibrand.dao.jdbc.sp.ProcedureInParameter;
import com.multibrand.dao.jdbc.sp.ProcedureOutParameter;

@Procedure("spk_choice_manage_affiliate.srvc_location_affiliate_upd")
public class UpdateServiceLocationRequest implements FormEntityRequest,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ProcedureInParameter(name = "in_tracking_number", parameterIndex = 1)
	private String trackingId;

	@ProcedureInParameter(name = "in_person_id", parameterIndex = 2)
	private String personId;

	@ProcedureInParameter(name = "in_srvc_req_type_cd", parameterIndex = 3)
	private String serviceRequestTypeCode;

	@ProcedureInParameter(name = "in_rep_cd", parameterIndex = 4)
	private String previousProviderCode;

	@ProcedureInParameter(name = "in_deposit_cd", parameterIndex = 5)
	private String depositCode;

	@ProcedureInParameter(name = "in_req_status_cd", parameterIndex = 6)
	private String requestStatusCode;

	@ProcedureInParameter(name = "in_offer_cd", parameterIndex = 7)
	private String offerCode;

	@ProcedureInParameter(name = "in_spanish_req_flag", parameterIndex = 8)
	private String spanishReqFlag;

	@ProcedureInParameter(name = "in_provider_agreement_flag", parameterIndex = 9)
	private String providerAgreementFlag;

	@ProcedureInParameter(name = "in_deposit_amount", parameterIndex = 10)
	private String depositAmount;

	@ProcedureInParameter(name = "in_esid", parameterIndex = 11)
	private String esid;

	@ProcedureInParameter(name = "in_contract_account_num", parameterIndex = 12)
	private String contractAccountNum;

	@ProcedureInParameter(name = "in_srvc_address_line_1", parameterIndex = 13)
	private String servAddressLine1;

	@ProcedureInParameter(name = "in_srvc_address_line_2", parameterIndex = 14)
	private String servAddressLine2;

	@ProcedureInParameter(name = "in_srvc_city", parameterIndex = 15)
	private String servCity;

	@ProcedureInParameter(name = "in_srvc_state", parameterIndex = 16)
	private String servState;

	@ProcedureInParameter(name = "in_srvc_zip", parameterIndex = 17)
	private String servZipCode;

	private String servStreetNum;
	private String servStreetName;
	private String servStreetAptNum;

	@ProcedureInParameter(name = "in_srvc_zip_override_flag", parameterIndex = 18)
	private String serviceZipOverrideFlag;

	@ProcedureInParameter(name = "in_addr_bill_sameas_srvc_flag", parameterIndex = 19)
	private String addressBillSameAsServiceFlag;

	@ProcedureInParameter(name = "in_srvc_address_override_flag", parameterIndex = 20)
	private String serviceAddressOverrideFlag;

	@ProcedureInParameter(name = "in_bill_address_line_1", parameterIndex = 21)
	private String billAddressLine1;

	@ProcedureInParameter(name = "in_bill_address_line_2", parameterIndex = 22)
	private String billAddressLine2;

	@ProcedureInParameter(name = "in_bill_city", parameterIndex = 23)
	private String billCity;

	@ProcedureInParameter(name = "in_bill_state", parameterIndex = 24)
	private String billState;

	@ProcedureInParameter(name = "in_bill_zip", parameterIndex = 25)
	private String billZipCode;

	private String billStreetNum;
	private String billStreetName;
	private String billStreetAptNum;
	private String billPoBox;

	@ProcedureInParameter(name = "in_service_start_date", parameterIndex = 26)
	private String serviceStartDate;

	@ProcedureInParameter(name = "in_guid_id", parameterIndex = 27)
	private String guid;

	@ProcedureInParameter(name = "in_signup_channel_cd", parameterIndex = 28)
	private String signupChannelCode;

	@ProcedureInParameter(name = "in_referrer_cd", parameterIndex = 29)
	private String referrerCode;

	@ProcedureInParameter(name = "in_acct_name", parameterIndex = 30)
	private String accountName;

	@ProcedureInParameter(name = "in_mail_address_line_1", parameterIndex = 31)
	private String mailAddressLine1;

	@ProcedureInParameter(name = "in_mail_address_line_2", parameterIndex = 32)
	private String mailAddressLine2;

	@ProcedureInParameter(name = "in_mail_address_city", parameterIndex = 33)
	private String mailCity;

	@ProcedureInParameter(name = "in_mail_address_state", parameterIndex = 34)
	private String mailState;

	@ProcedureInParameter(name = "in_mail_address_zip", parameterIndex = 35)
	private String mailZipCode;

	private String mailStreetNum;
	private String mailStreetName;
	private String mailStreetAptNum;
	private String mailPoBox;

	@ProcedureInParameter(name = "in_esid_match_flag", parameterIndex = 36)
	private String esidMatchFlag;

	@ProcedureInParameter(name = "in_geo_zone", parameterIndex = 37)
	private String geoZone;

	@ProcedureInParameter(name = "in_offer_cell_trk_cd", parameterIndex = 38)
	private String promoCodeEntered;

	@ProcedureInParameter(name = "in_completion_status_cd", parameterIndex = 39)
	private String completionStatusCode;

	@ProcedureInParameter(name = "in_ad_id", parameterIndex = 40)
	private String adId;

	@ProcedureInParameter(name = "in_deposit_required_cd", parameterIndex = 41)
	private String payCode;

	@ProcedureInParameter(name = "in_tdsp_cd", parameterIndex = 42)
	private String tdspCode;

	@ProcedureInParameter(name = "in_offer_teaser", parameterIndex = 43)
	private String offerCodeTitle;

	@ProcedureInParameter(name = "in_ca_check_digit", parameterIndex = 44)
	private String caCheckDigit;

	@ProcedureInParameter(name = "in_offer_cell_trk_cd_selected", parameterIndex = 45)
	private String offerCellTrackCodeSelected;

	@ProcedureInParameter(name = "in_blng_address_override_flag", parameterIndex = 46)
	private String billingAddressOverrideFlag;

	@ProcedureInParameter(name = "in_error_cd", parameterIndex = 47)
	private String errorCode;

	@ProcedureInParameter(name = "in_promo_type", parameterIndex = 48)
	private String promoType;

	@ProcedureInParameter(name = "in_promo_value", parameterIndex = 49)
	private String promoValue;

	@ProcedureInParameter(name = "in_dwelling_type", parameterIndex = 50)
	private String dwellingType;

	@ProcedureInParameter(name = "in_cep_processed_flag", parameterIndex = 51)
	private String cepProcessedFlag;

	@ProcedureInParameter(name = "in_recent_page_accessed", parameterIndex = 52)
	private String recentPageAccessed;

	@ProcedureInParameter(name = "in_esuite_flag", parameterIndex = 53)
	private String esuiteFlag;

	@ProcedureInParameter(name = "in_esuite_status", parameterIndex = 54)
	private String esuiteStatus;

	@ProcedureInParameter(name = "in_esuite_auth_flag", parameterIndex = 55)
	private String esuiteAuthFlag;

	@ProcedureInParameter(name = "in_esuite_oam_flag", parameterIndex = 56)
	private String esuiteOamFlag;

	@ProcedureInParameter(name = "in_esuite_email_flag", parameterIndex = 57)
	private String esuiteEmailFlag;

	@ProcedureInParameter(name = "in_esuite_auto_pay_flag", parameterIndex = 58)
	private String esuiteAutoPayFlag;

	@ProcedureInParameter(name = "in_esuite_oam_date", parameterIndex = 59)
	private String esuiteOamDate;

	@ProcedureInParameter(name = "in_esuite_email_date", parameterIndex = 60)
	private String esuiteEmailDate;

	@ProcedureInParameter(name = "in_esuite_auto_pay_date", parameterIndex = 61)
	private String esuiteAutoPayDate;

	@ProcedureInParameter(name = "in_esuite_auto_pay_option", parameterIndex = 62)
	private String esuiteAutoPayOption;

	@ProcedureInParameter(name = "in_user_name", parameterIndex = 63)
	private String userName;

	@ProcedureInParameter(name = "in_password", parameterIndex = 64)
	private String password;

	@ProcedureInParameter(name = "in_weekly_summary_enroll", parameterIndex = 65)
	private String weeklySummaryEnroll;

	@ProcedureInParameter(name = "in_permit_type", parameterIndex = 66)
	private String permitType;

	@ProcedureInParameter(name = "in_permit_class", parameterIndex = 67)
	private String permitClass;

	@ProcedureInParameter(name = "in_permit_detail", parameterIndex = 68)
	private String permitDetail;

	@ProcedureInParameter(name = "in_city_country", parameterIndex = 69)
	private String cityCountry;

	@ProcedureInParameter(name = "in_city_country_name", parameterIndex = 70)
	private String cityCountryName;

	@ProcedureInParameter(name = "in_permit_phone", parameterIndex = 71)
	private String permitPhone;

	@ProcedureInParameter(name = "in_priority_movein_flag", parameterIndex = 72)
	private String priorityMoveinFlag;

	@ProcedureInParameter(name = "in_html_capture_flag", parameterIndex = 73)
	private String htmlCaptureFlag;

	@ProcedureInParameter(name = "in_non_com_prd", parameterIndex = 74)
	private String nonCommodityProduct;

	@ProcedureInParameter(name = "in_prepay_flag", parameterIndex = 75)
	private String prepayFlag;

	@ProcedureInParameter(name = "in_prepay_total_today", parameterIndex = 76)
	private String prepayTotalToday;

	@ProcedureInParameter(name = "in_prepay_doc_id", parameterIndex = 77)
	private String prepayDocId;

	@ProcedureInParameter(name = "in_eco_share", parameterIndex = 78)
	private String ecoShare;

	@ProcedureInParameter(name = "in_active_customer_flag", parameterIndex = 79)
	private String activeCustomerFlag;

	@ProcedureInParameter(name = "in_address_match_flag", parameterIndex = 80)
	private String addressMatchFlag;

	@ProcedureInParameter(name = "in_pending_bal_flag", parameterIndex = 81)
	private String pendingBalanceFlag;

	@ProcedureInParameter(name = "in_bp_active_contract", parameterIndex = 82)
	private String bpActiveContract;

	@ProcedureInParameter(name = "in_matched_partner_id", parameterIndex = 83)
	private String matchedPartnerId;

	@ProcedureInParameter(name = "in_add_search_performed", parameterIndex = 84)
	private String addressSearchPerformed;

	@ProcedureInParameter(name = "in_bpmatch_no_ccs_resp", parameterIndex = 85)
	private String bpMatchNoCcsResponse;

	@ProcedureInParameter(name = "in_offer_type", parameterIndex = 86)
	private String productType;

	@ProcedureInParameter(name = "in_product_sku_code", parameterIndex = 87)
	private String productSkuCode;

	@ProcedureInParameter(name = "in_offer_name", parameterIndex = 88)
	private String planName;

	@ProcedureInParameter(name = "in_enroll_source", parameterIndex = 89)
	private String enrollSource;

	@ProcedureInParameter(name = "in_activation_fee", parameterIndex = 90)
	private String activationFee;

	@ProcedureInParameter(name = "in_bond_price", parameterIndex = 91)
	private String bondPrice;

	@ProcedureInParameter(name = "in_acc_sec_status", parameterIndex = 92)
	private String accSecStatus;

	@ProcedureInParameter(name = "in_is_pay_upfront", parameterIndex = 93)
	private String isPayUpFront;

	@ProcedureInParameter(name = "in_security_method", parameterIndex = 94)
	private String securityMethod;

	@ProcedureInParameter(name = "in_activation_fee_cd", parameterIndex = 95)
	private String activationFeeCode;

	@ProcedureInParameter(name = "in_meter_type", parameterIndex = 96)
	private String meterType;

	@ProcedureInParameter(name = "in_sw_hold_status", parameterIndex = 97)
	private String switchHoldStatus;

	@ProcedureInParameter(name = "in_rec_disconnect_flag", parameterIndex = 98)
	private String recentDisconnectFlag;

	@ProcedureInParameter(name = "in_premise_type", parameterIndex = 99)
	private String premiseType;

	@ProcedureInParameter(name = "in_esid_status", parameterIndex = 100)
	private String esidStatus;

	@ProcedureInParameter(name = "in_verify_additional_options", parameterIndex = 101)
	private String verifyAdditionalOptions;

	@ProcedureInParameter(name = "in_amb_prg_code", parameterIndex = 102)
	private String ambProgramCode;

	@ProcedureInParameter(name = "in_paperless_prg_code", parameterIndex = 103)
	private String paperlessProgramCode;

	@ProcedureInParameter(name = "in_rhs_prg_code_one", parameterIndex = 104)
	private String rhsProgramCodeOne;

	@ProcedureInParameter(name = "in_rhs_prg_code_two", parameterIndex = 105)
	private String rhsProgramCodeTwo;

	@ProcedureInParameter(name = "in_rhs_offer_type", parameterIndex = 106)
	private String rhsOfferType;

	@ProcedureInParameter(name = "in_freq_flyer_first_name", parameterIndex = 107)
	private String freqFlyerFirstName;

	@ProcedureInParameter(name = "in_freq_flyer_last_name", parameterIndex = 108)
	private String freqFlyerLastName;

	@ProcedureInParameter(name = "in_referral_id", parameterIndex = 109)
	private String referralId;

	@ProcedureInParameter(name = "in_recent_call_made", parameterIndex = 110)
	private String recentCallMade;

	@ProcedureInParameter(name = "in_nest_sid", parameterIndex = 111)
	private String nestSid;

	@ProcedureInParameter(name = "in_gz_product_id", parameterIndex = 112)
	private String gzProductId;

	@ProcedureInParameter(name = "in_affiliate_id", parameterIndex = 113)
	private String affiliateId;

	@ProcedureInParameter(name = "in_brand_name", parameterIndex = 114)
	private String brandId;

	@ProcedureInParameter(name = "in_company_code", parameterIndex = 115)
	private String companyCode;

	@ProcedureInParameter(name = "in_sun_club", parameterIndex = 116)
	private String sunClub;

	@ProcedureInParameter(name = "in_driver_club", parameterIndex = 117)
	private String driverClub;

	@ProcedureInParameter(name = "in_gift_card", parameterIndex = 118)
	private String giftCard;

	@ProcedureInParameter(name = "in_residential_solar", parameterIndex = 119)
	private String residentialSolar;

	@ProcedureInParameter(name = "in_recent_msg_cd", parameterIndex = 120)
	private String messageCode;

	
	private String offerCategory;
	private String frequentFlyerNumber;
	private String realtorId;
	
	//START : OE :Sprint61 :US21009 :Kdeshmu1
	@ProcedureInParameter(name = "in_agent_id", parameterIndex = 121)
	String agentID;
	@ProcedureInParameter(name = "in_agent_first_name", parameterIndex = 122)
	String agentFirstName;
	@ProcedureInParameter(name = "in_agent_last_name", parameterIndex = 123)
	String agentLastName;
	@ProcedureInParameter(name = "in_agent_type", parameterIndex = 124)
	String agentType;
	@ProcedureInParameter(name = "in_vendor_code", parameterIndex = 125)
	String vendorCode;
	@ProcedureInParameter(name = "in_vendor_name", parameterIndex = 126)
	String vendorName;
	@ProcedureInParameter(name = "in_tlp_report_api_status", parameterIndex = 127)
	String tlpReportApiStatus;
	@ProcedureInParameter(name = "in_error_cd_list", parameterIndex = 128)
	String errorCdList;
	@ProcedureInParameter(name = "in_system_notes", parameterIndex = 129)
	String systemNotes;
	//Start : OE : Sprint3 : 13643 - Add Missing Columns to  SLA table :Kdeshmu1
	@ProcedureInParameter(name = "in_pdf_capture_flag", parameterIndex = 130)
	String pdfCaptureFlag;
	@ProcedureInParameter(name = "in_agent_upd_response", parameterIndex = 131)
	String agentUpResponse;
	@ProcedureInParameter(name = "in_kba_transaction_key", parameterIndex = 132)
	String kbaTransactionKey;
	@ProcedureInParameter(name = "in_tpv_status", parameterIndex = 133)
	String tpv_status;
	@ProcedureInParameter(name = "in_campaign_cd", parameterIndex = 134)
	String campaignCd;
	
		@ProcedureInParameter(name = "in_entry_point", parameterIndex = 135)
		private String entryPoint ;
		@ProcedureInParameter(name = "in_partner_id", parameterIndex = 136)
		private String partnerId;
		@ProcedureInParameter(name = "in_partner_desc", parameterIndex = 137)
		private String partnerDesc;
		@ProcedureInParameter(name = "in_location_id ", parameterIndex = 138)
		private String locationId ;
		@ProcedureInParameter(name = "in_location_desc ", parameterIndex = 139)
		private String locationDesc ;
		@ProcedureInParameter(name = "in_page_revisited ", parameterIndex = 140)
		private String pageRevisited ;
		@ProcedureInParameter(name = "in_prospect_id", parameterIndex = 141)
		private String prospectId;
		@ProcedureInParameter(name = "in_prospect_preapproved_flag", parameterIndex = 142)
		private String prospectPreapprovedFlag;
		@ProcedureInParameter(name = "in_prospect_partner_id", parameterIndex = 143)
		private String prospectPartnerId;
		@ProcedureInParameter(name = "in_bypass_posid", parameterIndex = 144)
		private String bypassPosid;
		@ProcedureInParameter(name = "in_ip_address", parameterIndex = 145)
		private String ipAddress;
		@ProcedureInParameter(name = "in_tablet_id", parameterIndex = 146)
		private String tabletId;
		@ProcedureInParameter(name="in_channel", parameterIndex=147)
		private String channel;
		@ProcedureInParameter(name = "in_abandoned_enroll_stat_flag", parameterIndex = 148)
		private String abandonedEnrollStatFlag;
		@ProcedureInParameter(name = "in_bp_name_match_code", parameterIndex = 149)
		private String bpNameMatchCode;
		@ProcedureInParameter(name = "in_device_latitude", parameterIndex = 150)
		private String deviceLatitude;
		@ProcedureInParameter(name = "in_device_longitude", parameterIndex = 151)
		private String deviceLongitude;
		@ProcedureInParameter(name = "in_device_accuracy", parameterIndex = 152)
		private String deviceAccuracy;
		@ProcedureInParameter(name = "in_etf_flag", parameterIndex = 153)
		private String etfFlag;
		@ProcedureInParameter(name = "in_kba_suggestion_flag", parameterIndex = 154)
		private String kbaSuggestionFlag;
		@ProcedureInParameter(name = "in_pending_bal_amount", parameterIndex = 155)
		private String pendingBalAmount;
		@ProcedureInParameter(name = "in_past_service_ca", parameterIndex = 156)
		private String pastServiceCa;
		@ProcedureInParameter(name = "in_posid_snro", parameterIndex = 157)
		private String posidSNRO;
		@ProcedureInParameter(name = "in_bpmatch_scenario_id", parameterIndex = 158)
		private String bpMatchScenarioId;
	@ProcedureOutParameter(name = "out_error_code", parameterIndex = 159)
	private String outErrorCode;

	
	
	public String getBpMatchScenarioId() {
		return bpMatchScenarioId;
	}

	public void setBpMatchScenarioId(String bpMatchScenarioId) {
		this.bpMatchScenarioId = bpMatchScenarioId;
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


	public UpdateServiceLocationRequest() {

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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

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

	public String getTpv_status() {
		return tpv_status;
	}

	public void setTpv_status(String tpv_status) {
		this.tpv_status = tpv_status;
	}

	public String getCampaignCd() {
		return campaignCd;
	}

	public void setCampaignCd(String campaignCd) {
		this.campaignCd = campaignCd;
	}

	public String getEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerDesc() {
		return partnerDesc;
	}

	public void setPartnerDesc(String partnerDesc) {
		this.partnerDesc = partnerDesc;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationDesc() {
		return locationDesc;
	}

	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}

	public String getPageRevisited() {
		return pageRevisited;
	}

	public void setPageRevisited(String pageRevisited) {
		this.pageRevisited = pageRevisited;
	}

	public String getProspectId() {
		return prospectId;
	}

	public void setProspectId(String prospectId) {
		this.prospectId = prospectId;
	}

	public String getProspectPreapprovedFlag() {
		return prospectPreapprovedFlag;
	}

	public void setProspectPreapprovedFlag(String prospectPreapprovedFlag) {
		this.prospectPreapprovedFlag = prospectPreapprovedFlag;
	}

	public String getProspectPartnerId() {
		return prospectPartnerId;
	}

	public void setProspectPartnerId(String prospectPartnerId) {
		this.prospectPartnerId = prospectPartnerId;
	}

	public String getBypassPosid() {
		return bypassPosid;
	}

	public void setBypassPosid(String bypassPosid) {
		this.bypassPosid = bypassPosid;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getTabletId() {
		return tabletId;
	}

	public void setTabletId(String tabletId) {
		this.tabletId = tabletId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAbandonedEnrollStatFlag() {
		return abandonedEnrollStatFlag;
	}

	public void setAbandonedEnrollStatFlag(String abandonedEnrollStatFlag) {
		this.abandonedEnrollStatFlag = abandonedEnrollStatFlag;
	}

	public String getBpNameMatchCode() {
		return bpNameMatchCode;
	}

	public void setBpNameMatchCode(String bpNameMatchCode) {
		this.bpNameMatchCode = bpNameMatchCode;
	}

	public String getDeviceLatitude() {
		return deviceLatitude;
	}

	public void setDeviceLatitude(String deviceLatitude) {
		this.deviceLatitude = deviceLatitude;
	}

	public String getDeviceLongitude() {
		return deviceLongitude;
	}

	public void setDeviceLongitude(String deviceLongitude) {
		this.deviceLongitude = deviceLongitude;
	}

	public String getDeviceAccuracy() {
		return deviceAccuracy;
	}

	public void setDeviceAccuracy(String deviceAccuracy) {
		this.deviceAccuracy = deviceAccuracy;
	}

	public String getEtfFlag() {
		return etfFlag;
	}

	public void setEtfFlag(String etfFlag) {
		this.etfFlag = etfFlag;
	}

	public String getKbaSuggestionFlag() {
		return kbaSuggestionFlag;
	}

	public void setKbaSuggestionFlag(String kbaSuggestionFlag) {
		this.kbaSuggestionFlag = kbaSuggestionFlag;
	}

	public String getPendingBalAmount() {
		return pendingBalAmount;
	}

	public void setPendingBalAmount(String pendingBalAmount) {
		this.pendingBalAmount = pendingBalAmount;
	}

	public String getPastServiceCa() {
		return pastServiceCa;
	}

	public void setPastServiceCa(String pastServiceCa) {
		this.pastServiceCa = pastServiceCa;
	}

	public String getPosidSNRO() {
		return posidSNRO;
	}

	public void setPosidSNRO(String posidSNRO) {
		this.posidSNRO = posidSNRO;
	}
	
	

	
	
	
}