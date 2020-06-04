package com.multibrand.dto.request;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.multibrand.dao.jdbc.sp.Procedure;
import com.multibrand.dao.jdbc.sp.ProcedureInParameter;
import com.multibrand.dao.jdbc.sp.ProcedureOutParameter;

@Procedure("spk_choice_manage_affiliate.srvc_location_affiliate_add")
public class AddServiceLocationRequest implements FormEntityRequest,
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

	@ProcedureInParameter(name = "in_offer_cd", parameterIndex = 5)
	private String offerCode;

	@ProcedureInParameter(name = "in_esid", parameterIndex = 6)
	private String esid;

	@ProcedureInParameter(name = "in_srvc_address_line_1", parameterIndex = 7)
	private String servAddressLine1;

	@ProcedureInParameter(name = "in_srvc_address_line_2", parameterIndex = 8)
	private String servAddressLine2;

	@ProcedureInParameter(name = "in_srvc_city", parameterIndex = 9)
	private String servCity;

	@ProcedureInParameter(name = "in_srvc_state", parameterIndex = 10)
	private String servState;

	@ProcedureInParameter(name = "in_srvc_zip", parameterIndex = 11)
	private String servZipCode;

	private String servStreetNum;
	private String servStreetName;
	private String servStreetAptNum;

	@ProcedureInParameter(name = "in_addr_bill_sameas_srvc_flag", parameterIndex = 12)
	private String addressBillSameAsServiceFlag;

	@ProcedureInParameter(name = "in_srvc_address_override_flag", parameterIndex = 13)
	private String serviceAddressOverrideFlag;

	@ProcedureInParameter(name = "in_blng_address_override_flag", parameterIndex = 14)
	private String billingAddressOverrideFlag;

	@ProcedureInParameter(name = "in_bill_address_line_1", parameterIndex = 15)
	private String billAddressLine1;

	@ProcedureInParameter(name = "in_bill_address_line_2", parameterIndex = 16)
	private String billAddressLine2;

	@ProcedureInParameter(name = "in_bill_city", parameterIndex = 17)
	private String billCity;

	@ProcedureInParameter(name = "in_bill_state", parameterIndex = 18)
	private String billState;

	@ProcedureInParameter(name = "in_bill_zip", parameterIndex = 19)
	private String billZipCode;

	private String billStreetNum;
	private String billStreetName;
	private String billStreetAptNum;
	private String billPoBox;

	@ProcedureInParameter(name = "in_service_start_date", parameterIndex = 20)
	private String serviceStartDate;

	@ProcedureInParameter(name = "in_signup_channel_cd", parameterIndex = 21)
	private String signupChannelCode;

	@ProcedureInParameter(name = "in_srvc_zip_override_flag", parameterIndex = 22)
	private String serviceZipOverrideFlag;

	@ProcedureInParameter(name = "in_referrer_cd", parameterIndex = 23)
	private String referrerCode;

	@ProcedureInParameter(name = "in_completion_status_cd", parameterIndex = 24)
	private String completionStatusCode;

	@ProcedureInParameter(name = "in_geo_zone", parameterIndex = 25)
	private String geoZone;

	@ProcedureInParameter(name = "in_offer_cell_trk_cd", parameterIndex = 26)
	private String promoCodeEntered;

	@ProcedureInParameter(name = "in_tdsp_cd", parameterIndex = 27)
	private String tdspCode;

	@ProcedureInParameter(name = "in_offer_teaser", parameterIndex = 28)
	private String offerCodeTitle;

	@ProcedureInParameter(name = "in_guid_id", parameterIndex = 29)
	private String guid;

	@ProcedureInParameter(name = "in_offer_cell_trk_cd_selected", parameterIndex = 30)
	private String offerCellTrackCodeSelected;

	@ProcedureInParameter(name = "in_error_cd", parameterIndex = 31)
	private String errorCode;

	@ProcedureInParameter(name = "in_promo_type", parameterIndex = 32)
	private String promoType;

	@ProcedureInParameter(name = "in_promo_value", parameterIndex = 33)
	private String promoValue;

	@ProcedureInParameter(name = "in_dwelling_type", parameterIndex = 34)
	private String dwellingType;

	@ProcedureInParameter(name = "in_recent_page_accessed", parameterIndex = 35)
	private String recentPageAccessed;

	@ProcedureInParameter(name = "in_non_com_prd", parameterIndex = 36)
	private String nonCommodityProduct;

	@ProcedureInParameter(name = "in_offer_type", parameterIndex = 37)
	private String productType;

	@ProcedureInParameter(name = "in_product_sku_code", parameterIndex = 38)
	private String productSkuCode;

	@ProcedureInParameter(name = "in_offer_name", parameterIndex = 39)
	private String planName;

	@ProcedureInParameter(name = "in_enroll_source", parameterIndex = 40)
	private String enrollSource;

	@ProcedureInParameter(name = "in_freq_flyer_first_name", parameterIndex = 41)
	private String freqFlyerFirstName;

	@ProcedureInParameter(name = "in_freq_flyer_last_name", parameterIndex = 42)
	private String freqFlyerLastName;

	@ProcedureInParameter(name = "in_referral_id", parameterIndex = 43)
	private String referralId;

	@ProcedureInParameter(name = "in_recent_call_made", parameterIndex = 44)
	private String recentCallMade;

	@ProcedureInParameter(name = "in_nest_sid", parameterIndex = 45)
	private String nestSid;

	@ProcedureInParameter(name = "in_affiliate_id", parameterIndex = 46)
	private String affiliateId;

	@ProcedureInParameter(name = "in_brand_name", parameterIndex = 47)
	private String brandId;

	@ProcedureInParameter(name = "in_company_code", parameterIndex = 48)
	private String companyCode;

	@ProcedureInParameter(name = "in_sun_club", parameterIndex = 49)
	private String sunClub;

	@ProcedureInParameter(name = "in_driver_club", parameterIndex = 50)
	private String driverClub;

	@ProcedureInParameter(name = "in_gift_card", parameterIndex = 51)
	private String giftCard;

	@ProcedureInParameter(name = "in_residential_solar", parameterIndex = 52)
	private String residentialSolar;

	@ProcedureInParameter(name = "in_recent_msg_cd", parameterIndex = 53)
	private String messageCode;

	private String offerCategory;
	private String frequentFlyerNumber;
	private String realtorId;
	
	//START : OE :Sprint61 :US21009 :Kdeshmu1
		
	@ProcedureInParameter(name = "in_agent_id", parameterIndex = 54)
	String agentID;
	@ProcedureInParameter(name = "in_agent_first_name", parameterIndex = 55)
	String agentFirstName;
	@ProcedureInParameter(name = "in_agent_last_name", parameterIndex = 56)
	String agentLastName;
	@ProcedureInParameter(name = "in_agent_type", parameterIndex = 57)
	String agentType;
	@ProcedureInParameter(name = "in_vendor_code", parameterIndex = 58)
	String vendorCode;
	@ProcedureInParameter(name = "in_vendor_name", parameterIndex = 59)
	String vendorName;
	@ProcedureInParameter(name = "in_tlp_report_api_status", parameterIndex = 60)
	String tlpReportApiStatus;
	@ProcedureInParameter(name = "in_error_cd_list", parameterIndex = 61)
	String errorCdList;
	@ProcedureInParameter(name = "in_system_notes", parameterIndex = 62)
	String systemNotes;
	
	///Start : OE : Sprint3 : 13643 - Add Missing Columns to  SLA table :Kdeshmu1
	@ProcedureInParameter(name = "in_entry_point", parameterIndex = 63)
	private String entryPoint ;
	@ProcedureInParameter(name = "in_partner_id", parameterIndex = 64)
	private String partnerId;
	@ProcedureInParameter(name = "in_partner_desc", parameterIndex = 65)
	private String partnerDesc;
	@ProcedureInParameter(name = "in_location_id ", parameterIndex = 66)
	private String locationId ;
	@ProcedureInParameter(name = "in_location_desc ", parameterIndex = 67)
	private String locationDesc ;
	@ProcedureInParameter(name = "in_page_revisited ", parameterIndex = 68)
	private String pageRevisited ;
	@ProcedureInParameter(name = "in_prospect_id", parameterIndex = 69)
	private String prospectId;
	@ProcedureInParameter(name = "in_prospect_preapproved_flag", parameterIndex = 70)
	private String prospectPreapprovedFlag;
	@ProcedureInParameter(name = "in_prospect_partner_id", parameterIndex = 71)
	private String prospectPartnerId;
	@ProcedureInParameter(name = "in_bypass_posid", parameterIndex = 72)
	private String bypassPosid;
	@ProcedureInParameter(name = "in_ip_address", parameterIndex = 73)
	private String ipAddress;
	@ProcedureInParameter(name = "in_tablet_id", parameterIndex = 74)
	private String tabletId;
	@ProcedureInParameter(name="in_channel", parameterIndex=75)
	private String channel;
	@ProcedureInParameter(name = "in_abandoned_enroll_stat_flag", parameterIndex = 76)
	private String abandonedEnrollStatFlag;
	@ProcedureInParameter(name = "in_bp_name_match_code", parameterIndex = 77)
	private String bpNameMatchCode;
	@ProcedureInParameter(name = "in_device_latitude", parameterIndex = 78)
	private String deviceLatitude;
	@ProcedureInParameter(name = "in_device_longitude", parameterIndex = 79)
	private String deviceLongitude;
	@ProcedureInParameter(name = "in_device_accuracy", parameterIndex = 80)
	private String deviceAccuracy;
	@ProcedureInParameter(name = "in_etf_flag", parameterIndex = 81)
	private String etfFlag;
	@ProcedureInParameter(name = "in_kba_suggestion_flag", parameterIndex = 82)
	private String kbaSuggestionFlag;
	@ProcedureInParameter(name = "in_pending_bal_amount", parameterIndex = 83)
	private String pendingBalAmount;
	@ProcedureInParameter(name = "in_past_service_ca", parameterIndex = 84)
	private String pastServiceCa;
	
	@ProcedureInParameter(name = "in_posid_snro", parameterIndex = 85)
	private String posidSNRO;
	
	@ProcedureInParameter(name = "in_bpmatch_scenario_id", parameterIndex = 86)
	private String bpMatchScenarioId;
	
	@ProcedureInParameter(name = "in_call_executed", parameterIndex = 87)
	private String callExecuted;
	
	@ProcedureOutParameter(name = "out_error_code", parameterIndex = 88)
	private String outErrorCode;
	
	

	///END : OE : Sprint3 : 13643 - Add Missing Columns to  SLA table :Kdeshmu1
		
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

	public AddServiceLocationRequest() {

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
	 * @return the guid
	 */
	public String getGuid() {
		if (StringUtils.isBlank(guid)) {
			UUID uuID = UUID.randomUUID();
			setGuid(uuID.toString());
		}
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
	 * @param offerCategory
	 *            the offerCategory to set
	 */
	public void setOfferCategory(String offerCategory) {
		this.offerCategory = offerCategory;
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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
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

	public String getBpMatchScenarioId() {
		return bpMatchScenarioId;
	}

	public void setBpMatchScenarioId(String bpMatchScenarioId) {
		this.bpMatchScenarioId = bpMatchScenarioId;
	}

	/**
	 * @return the callExecuted
	 */
	public String getCallExecuted() {
		return callExecuted;
	}

	/**
	 * @param callExecuted the callExecuted to set
	 */
	public void setCallExecuted(String callExecuted) {
		this.callExecuted = callExecuted;
	}
	
	
}