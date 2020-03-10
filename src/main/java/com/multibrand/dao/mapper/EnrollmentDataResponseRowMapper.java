package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.dto.response.PersonResponse;
import com.multibrand.dto.response.ServiceLocationResponse;
import com.multibrand.util.Constants;
import com.multibrand.util.DBConstants;
import com.multibrand.util.DateUtil;
import com.multibrand.vo.request.ESIDData;


public class EnrollmentDataResponseRowMapper implements RowMapper<ServiceLocationResponse>, DBConstants ,Constants{

	@Override
	public ServiceLocationResponse mapRow(ResultSet rs, int arg1) throws SQLException {
		ServiceLocationResponse dataRow = new ServiceLocationResponse();
		dataRow.setTrackingId(rs.getString("tracking_number"));
		dataRow.setPersonId(rs.getString("person_id"));
		dataRow.setServiceRequestTypeCode(rs.getString("srvc_req_type_cd"));
		dataRow.setPreviousProviderCode(rs.getString("rep_cd"));
		dataRow.setDepositCode(rs.getString("deposit_cd"));
		dataRow.setRequestStatusCode(rs.getString("req_status_cd"));
		dataRow.setOfferCode(rs.getString("offer_cd"));
		dataRow.setSpanishReqFlag(rs.getString("spanish_req_flag"));
		dataRow.setProviderAgreementFlag(rs.getString("provider_agreement_flag"));
		dataRow.setDepositAmount(rs.getString("deposit_amount"));
		dataRow.setEsid(rs.getString("esid"));
		dataRow.setContractAccountNum(rs.getString("contract_account_num"));
		dataRow.setServAddressLine1(rs.getString("srvc_address_line_1"));
		dataRow.setServAddressLine2(rs.getString("srvc_address_line_2"));
		dataRow.setServCity(rs.getString("srvc_city"));
		dataRow.setServState(rs.getString("srvc_state"));
		dataRow.setServZipCode(rs.getString("srvc_zip"));
		dataRow.setServiceZipOverrideFlag(rs.getString("srvc_zip_override_flag"));
		dataRow.setAddressBillSameAsServiceFlag(rs.getString("address_bill_sameas_srvc_flag"));
		dataRow.setServiceAddressOverrideFlag(rs.getString("srvc_address_override_flag"));
		dataRow.setBillAddressLine1(rs.getString("bill_address_line_1"));
		dataRow.setBillAddressLine2(rs.getString("bill_address_line_2"));
		dataRow.setBillCity(rs.getString("bill_city"));
		dataRow.setBillState(rs.getString("bill_state"));
		dataRow.setBillZipCode(rs.getString("bill_zip"));
		dataRow.setServiceStartDate(DateUtil.getFormattedDate(MMddyyyy,	DT_SQL_FMT_DB,rs.getString("service_start_date")));
		dataRow.setGuid(rs.getString("guid_id"));
		dataRow.setSignupChannelCode(rs.getString("signup_channel_cd"));
		dataRow.setReferrerCode(rs.getString("referrer_cd"));
		dataRow.setAccountName(rs.getString("acct_name"));
		dataRow.setMailAddressLine1(rs.getString("mail_address_line_1"));
		dataRow.setMailAddressLine2(rs.getString("mail_address_line_2"));
		dataRow.setMailCity(rs.getString("mail_address_city"));
		dataRow.setMailState(rs.getString("mail_address_state"));
		dataRow.setMailZipCode(rs.getString("mail_address_zip"));
		dataRow.setEsidMatchFlag(rs.getString("esid_match_flag"));
		dataRow.setGeoZone(rs.getString("geo_zone"));
		dataRow.setPromoCodeEntered(rs.getString("offer_cell_trk_cd"));
		dataRow.setCompletionStatusCode(rs.getString("completion_status_cd"));
		dataRow.setAdId(rs.getString("ad_id"));
		dataRow.setPayCode(rs.getString("deposit_required_cd"));
		dataRow.setTdspCode(rs.getString("tdsp_cd"));
		dataRow.setOfferCodeTitle(rs.getString("offer_teaser"));
		dataRow.setCaCheckDigit(rs.getString("ca_check_digit"));
		dataRow.setOfferCellTrackCodeSelected(rs.getString("offer_cell_trk_cd_selected"));
		dataRow.setBillingAddressOverrideFlag(rs.getString("blng_address_override_flag"));
		dataRow.setErrorCode(rs.getString("error_cd"));
		dataRow.setErrorCdlist(rs.getString("error_cd_list"));
		dataRow.setPromoType(rs.getString("promo_type"));
		dataRow.setPromoValue(rs.getString("promo_value"));
		dataRow.setDwellingType(rs.getString("dwelling_type"));
		dataRow.setCepProcessedFlag(rs.getString("cep_processed_flag"));
		dataRow.setRecentPageAccessed(rs.getString("recent_page_accessed"));
		dataRow.setEsuiteFlag(rs.getString("esuite_flag"));
		dataRow.setEsuiteStatus(rs.getString("esuite_status"));
		dataRow.setEsuiteAuthFlag(rs.getString("esuite_auth_flag"));
		dataRow.setEsuiteOamFlag(rs.getString("esuite_oam_flag"));
		dataRow.setEsuiteEmailFlag(rs.getString("esuite_email_flag"));
		dataRow.setEsuiteAutoPayFlag(rs.getString("esuite_auto_pay_flag"));
		dataRow.setEsuiteOamDate(rs.getString("esuite_oam_date"));
		dataRow.setEsuiteEmailDate(DateUtil.getFormattedDate(MMddyyyy,DT_SQL_FMT_DB,rs.getString("esuite_email_date")));
		dataRow.setEsuiteAutoPayDate(DateUtil.getFormattedDate(MMddyyyy,DT_SQL_FMT_DB,rs.getString("esuite_auto_pay_date")));
		dataRow.setEsuiteAutoPayOption(rs.getString("esuite_auto_pay_option"));
		dataRow.setUserName(rs.getString("user_name"));
		dataRow.setPassword(rs.getString("password"));
		dataRow.setWeeklySummaryEnroll(rs.getString("wse_flag"));
		dataRow.setPermitType(rs.getString("permit_type"));
		dataRow.setPermitClass(rs.getString("permit_class"));
		dataRow.setPermitDetail(rs.getString("permit_detail"));
		dataRow.setCityCountry(rs.getString("city_county"));
		dataRow.setCityCountryName(rs.getString("city_county_name"));
		dataRow.setPermitPhone(rs.getString("permit_phone"));
		dataRow.setPriorityMoveinFlag(rs.getString("priority_movein_flag"));
		dataRow.setHtmlCaptureFlag(rs.getString("html_capture_flag"));
		dataRow.setNonCommodityProduct(rs.getString("value_product_code"));
		dataRow.setPrepayFlag(rs.getString("prepay_flag"));
		dataRow.setPrepayTotalToday(rs.getString("prepay_total_today"));
		dataRow.setPrepayDocId(rs.getString("prepay_doc_id"));
		dataRow.setEcoShare(rs.getString("eco_share"));
		dataRow.setActiveCustomerFlag(rs.getString("active_customer_flag"));
		dataRow.setAddressMatchFlag(rs.getString("address_match_flag"));
		dataRow.setPendingBalanceFlag(rs.getString("pending_bal_flag"));
		dataRow.setBpActiveContract(rs.getString("bp_match_active_contract"));
		dataRow.setMatchedPartnerId(rs.getString("matched_partner_id"));
		dataRow.setAddressSearchPerformed(rs.getString("add_search_performed"));
		dataRow.setBpMatchNoCcsResponse(rs.getString("bpmatch_no_ccs_resp"));
		dataRow.setProductType(rs.getString("offer_type"));
		dataRow.setProductSkuCode(rs.getString("product_sku_code"));
		dataRow.setPlanName(rs.getString("offer_name"));
		dataRow.setEnrollSource(rs.getString("enroll_source"));
		dataRow.setActivationFee(rs.getString("activation_fee"));
		dataRow.setBondPrice(rs.getString("bond_price"));
		dataRow.setAccSecStatus(rs.getString("acc_sec_status"));
		dataRow.setIsPayUpFront(rs.getString("is_pay_upfront"));
		dataRow.setSecurityMethod(rs.getString("security_method"));
		dataRow.setActivationFeeCode(rs.getString("activation_fee_cd"));
		dataRow.setMeterType(rs.getString("meter_type"));
		dataRow.setSwitchHoldStatus(rs.getString("sw_hold_status"));
		dataRow.setRecentDisconnectFlag(rs.getString("rec_disconnect_flag"));
		dataRow.setPremiseType(rs.getString("premise_type"));
		dataRow.setEsidStatus(rs.getString("esid_status"));
		dataRow.setVerifyAdditionalOptions(rs.getString("verify_lightbox_options"));
		dataRow.setAmbProgramCode(rs.getString("average_billing_request"));
		dataRow.setPaperlessProgramCode(rs.getString("paperless_billing_request"));
		dataRow.setRhsProgramCodeOne(rs.getString("rhs_offer_name_one"));
		dataRow.setRhsProgramCodeTwo(rs.getString("rhs_offer_name_two"));
		dataRow.setRhsOfferType(rs.getString("rhs_offer_type"));
		dataRow.setFreqFlyerFirstName(rs.getString("freq_flyer_first_name"));
		dataRow.setFreqFlyerLastName(rs.getString("freq_flyer_last_name"));
		dataRow.setReferralId(rs.getString("referral_id"));
		dataRow.setRecentCallMade(rs.getString("recent_call_made"));
		dataRow.setNestSid(rs.getString("nest_sid"));
		dataRow.setGzProductId(rs.getString("gz_product_id"));
		dataRow.setAffiliateId(rs.getString("affiliate_id"));
		dataRow.setBrandId(rs.getString("brand_name"));
		dataRow.setCompanyCode(rs.getString("company_code"));
		dataRow.setSunClub(rs.getString("sun_club"));
		dataRow.setDriverClub(rs.getString("driver_club"));
		dataRow.setGiftCard(rs.getString("gift_card"));
		dataRow.setResidentialSolar(rs.getString("residential_solar"));
		dataRow.setMessageCode(rs.getString("recent_msg_cd"));
		dataRow.setAgentID(rs.getString("agent_id"));
		dataRow.setAgentType(rs.getString("agent_type"));
		dataRow.setAgentFirstName(rs.getString("agent_first_name"));
		dataRow.setAgentLastName(rs.getString("agent_last_name"));
		dataRow.setVendorCode(rs.getString("vendor_code"));
		dataRow.setVendorName(rs.getString("vendor_name"));
		dataRow.setTlpReportApiStatus(rs.getString("tlp_report_api_status"));
		dataRow.setPosidSNRO(rs.getString("posid_snro"));
		dataRow.setBpMatchScenarioId("bpmatch_scenario_id");
		
		
		PersonResponse personResponse = new PersonResponse();
		personResponse.setPersonId(rs.getString("person_id"));
		personResponse.setFirstName(rs.getString("name_first"));
		personResponse.setLastName(rs.getString("name_last"));
		personResponse.setDob(DateUtil.getFormattedDate(
				MMddyyyy, DT_SQL_FMT_DB,
				rs.getString("date_of_birth")));
		personResponse.setSsn(rs.getString("ssn"));
		personResponse.setIdType(rs.getString("id_cd"));
		personResponse.setIdNumber(rs.getString("id_number"));
		personResponse.setIdStateOfIssue(rs
				.getString("id_state_of_issue"));
		personResponse.setContactTimeCode(rs
				.getString("contact_time_cd"));
		personResponse.setPhoneNum(rs
				.getString("phone_number"));
		personResponse.setEmail(rs.getString("address_email"));
		personResponse.setAdditionalNameFirst(rs
				.getString("additional_name_first"));
		personResponse.setAdditionalNameLast(rs
				.getString("additional_name_last"));
		personResponse.setKeepMeInformedFlag(rs
				.getString("keep_me_informed_flag"));
		personResponse.setContactByEmailFlag(rs
				.getString("contact_by_email_flag"));
		personResponse.setContactByPhoneFlag(rs
				.getString("contact_by_phone_flag"));
		personResponse.setPersonStatus(rs
				.getString("person_status"));
		personResponse.setTitleName(rs.getString("name_title"));
		personResponse.setLanguageCode(rs
				.getString("language_cd"));
		personResponse.setFaxNumber(rs.getString("fax_number"));
		personResponse.setCredLevelNum(rs
				.getString("cred_level_num"));
		personResponse.setCredScoreNum(rs
				.getString("cred_score_num"));
		personResponse.setCredSourceNum(rs
				.getString("cred_source_num"));
		personResponse.setMiddleName(rs
				.getString("name_middle"));
		personResponse.setBusinessPartnerId(rs
				.getString("business_partner_id"));
		personResponse.setEnrollmentNumber(rs
				.getString("enrollment_number"));
		personResponse.setCredStatusCode(rs
				.getString("cred_status_cd"));
		personResponse.setCredStatusDate(DateUtil.getFormattedDate(
				MMddyyyy, DT_SQL_FMT_DB,
				rs.getString("cred_status_date")));
		personResponse.setIdocNumber(rs
				.getString("idoc_number"));
		personResponse.setEmailOptionRps(rs
				.getString("email_option_rps"));
		personResponse.setEmailOptionSo(rs
				.getString("email_option_so"));
		personResponse.setEmailOptionEe(rs
				.getString("email_option_ee"));
		personResponse.setRoutingNumber(rs
				.getString("routing_number"));
		personResponse.setBankInstitutionName(rs
				.getString("banking_institution_name"));
		personResponse.setCcType(rs.getString("cc_type"));
		personResponse.setCcExpiryMonth(rs
				.getString("cc_expiry_month"));
		personResponse.setCcExpiryYear(rs
				.getString("cc_expiry_year"));
		personResponse.setCcBillzip(rs.getString("cc_billzip"));
		personResponse.setAccountNumber(rs
				.getString("account_number"));
		personResponse.setAccountName(rs
				.getString("account_name"));
		personResponse.setBankAccountLastThree(rs
				.getString("bf22"));
		personResponse.setDrlFour(rs.getString("sf31"));
		personResponse.setSsnFour(rs.getString("sf11"));
		personResponse.setMaidenName(rs
				.getString("name_maiden"));
		personResponse.setPosIdStatus(rs
					.getString("pos_id_flag"));
		personResponse.setPosIdDate(DateUtil.getFormattedDate(
				MMddyyyy, DT_SQL_FMT_DB,
				rs.getString("pos_id_date")));
		personResponse.setAdvActionData(rs
				.getString("adv_action_data"));
		personResponse.setRetryCount(rs
				.getString("retry_count"));
		dataRow.setPersonResponse(personResponse);
		return dataRow;
	}

}


