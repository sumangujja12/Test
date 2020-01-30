package com.multibrand.util;

public interface DBConstants {

	public static final String OUTPUT = "OUTPUT";
	public static final String INPUT = "INPUT";
	public static final String IN_COMPANY_CODE = "in_company_code";
	public static final String IN_USER_UNIQUE_ID = "in_user_unique_id";
	public static final String IN_USER_LOGIN_ID = "in_user_login_id";
	public static final String IN_USER_FIRST_NAME = "in_user_first_name";
	public static final String IN_USER_LAST_NAME = "in_user_last_name";
	public static final String IN_GREETING_NAME = "in_greeting_name";
	public static final String IN_EMAIL_ADDRESS = "in_email_address";
	public static final String IN_MOBILE_NUMBER = "in_mobile_number";
	public static final String IN_CONTRACT_ACCOUNT_NUMBER = "in_contract_account_number";
	public static final String IN_OUT_CA_CHECK_DIGIT = "in_out_ca_check_digit";
	public static final String IN_EMAIL_OPT_IN_FLAG = "in_email_opt_in_flag";
	public static final String OUT_ERROR_CODE = "out_error_code";

	public static final String OUT_USER_NAME = "out_user_name";
	public static final String OUT_CA_VALID_FLAG = "out_ca_valid_flag";
	public static final String OUT_EMAIL_BOUNCED_FLAG = "out_email_bounced_flag";

	public static String GET_USERNAME_PROC = "GME_RES_MAIN.PKG_GME_RES_ONLINE_USER_READ.SP_USER_NAME_GET";
	public static String CREATEUSER_PROC = "GME_RES_MAIN.PKG_GME_RES_ONLINE_USER_ADD.SP_NEW_USER_ADD";
	public static String GME_OAM_MSSG_READ_PROC = "GME_RES_MAIN.ODS_TGT_MSG_PKG.GET_OAM_MSG";
	public static String GME_OAM_MSSG_WRITE_PROC = "GME_RES_MAIN.ODS_TGT_MSG_PKG.WRITE_OAM_MSG";
	public static String GME_OAM_MSSG_CHECK_PROC = "GME_RES_MAIN.ODS_TGT_MSG_PKG.CHECK_OAM_MSG";

	public static final String PROC_CPDB_GET_POW_OFFER = "proc.getPOWoffer";
	// getPOWoffer
	public static final String IN_ESID = "in_esid";
	public static final String IN_TRANSACTION_TYPE = "in_transaction_type";
	public static final String OUT_CUR_POW_OFFER = "out_rowset";
	public static final String OUT_ERROR_CODE_LOWER = "out_error_code";

	public static final String OFFER = "OFFER";
	public static final String PROMO = "PROMO";
	public static final String CAMPAIGN_CD = "CAMPAIGN_CD";
	public static final String ESENCE_ELIG_FLAG = "ESENSE_ELIG_FLAG";
	public static final String VALUE_SEGMENT = "VALUE_SEGMENT";
	public static final String PREPAY_FLAG = "PREPAY_FLAG";
	public static final String TERM = "TERM";
	// START : OAM :KD
	String BILL_STRT_DT = "bill_strt_dt_in_v";
	String BILL_END_DT = "bill_end_dt_in_v";
	String ZONE_ID = "zone_id_in_v";
	String RET_CD = "ret_cd_out_v";
	String AVG_TEMP = "avg_temp_out_v";
	String TYP_TEMP = "calc_typ_in_v";

	public static String PROJECTEDBILL_PROC_AVG = "SMART_SYNC.WP_WEB_UTIL.WP_CALC_AVG_WTHR_PRC";

	public static String WP_GET_DLY_USG_GME = "SMART_SYNC.WP_WEB_UTIL.WP_GET_DLY_USG_GME";
	public static String ESIID_IN_V = "esiid_in_v";
	public static String CONTRACT_ACT_ID_IN_V = "contract_act_id_in_v";
	public static String RET_TYP_OUT_V = "ret_typ_out_v";
	public static final String OUT_CURSOR = "SYS_REFCURSOR";
	public static String CONTRACT_ID_IN_V = "contract_id_in_v";
	public static String ZONE_ID_IN_V = "zone_id_in_v";
	public static String CUR_DT_IN_V = "cur_dt_in_v";
	public static String CUR_DAY_IND_IN_V = "cur_day_ind_in_v";
	public static String DYHR_IND_IN_V = "dyhr_ind_in_v";
	public static String DYHR_OUT_REC = "dyhr_out_rec";
	public static String BILPRD_OUT_REC = "bilprd_out_rec";
	public static String WK_WSE_OUT_REC = "wk_wse_out_rec";
	public static String WK_DT_IN_V = "wk_dt_in_v";
	public static String CUR_WK_IN_IN_V = "cur_wk_in_in_v";
	public static String WK_OUT_REC = "wk_out_rec";
	public static String WK_PCT_OUT_REC = "wk_pct_out_rec";

	public static String PROJECTEDBILL_PROC = "SMART_SYNC.WP_WEB_UTIL.WP_GET_BILPRD_GME";
	public static String MONTHLYUSAGE_PROC = "SMART_SYNC.WP_WEB_UTIL.WP_GET_MNTHLY_USG_GME";
	public static String WEEKLYUSAGE_PROC = "SMART_SYNC.WP_WEB_UTIL.WP_GET_WSE_GME";
	public static String DAILYWEEKLYUSAGE_PROC = "SMART_SYNC.WP_WEB_UTIL.WP_GET_DLYWKY_USG_GME";
	// END : OAM :KD

	// START OERedesign Phase 2
	public static final String OUT_CUR_PENDING_REQUEST = "out_rowset";
	public static final String in_addr1 = "in_addr1";
	public static final String in_addr2 = "in_addr2";

	public static final String in_zip_code = "in_zip_code";
	public static final String in_street_number = "in_street_number";
	public static final String in_check_digit = "in_check_digit";
	public static final String in_unit_number = "in_unit_number";
	public static final String in_city = "in_city";
	public static final String in_state = "in_state";
	public static final String in_esid = "in_esid";
	public static final String in_req_status_cd = "in_req_status_cd";
	public static final String OUT_TRACKING_NUMBER = "tracking_number";
	public static final String OUT_NAME_FIRST = "name_first";
	public static final String OUT_NAME_MIDDLE = "name_middle";
	public static final String OUT_NAME_LAST = "name_last";
	public static final String OUT_SERVICE_ADD_LINE_1 = "srvc_address_line_1";
	public static final String OUT_SERVICE_ADD_LINE_2 = "srvc_address_line_2";
	public static final String OUT_SERVICE_CITY = "srvc_city";
	public static final String OUT_SERVICE_ZIP = "srvc_zip";
	public static final String OUT_CREATION_DATE = "creation_date";
	public static final String SP_DEFAULT_EMPTY_VALUE = "$blank$";

	public static final String PROC_GET_SERVICE_REQUEST_DETAILS = "proc.getsrvcreqdetails";
	public static final String SQL_GET_PENDING_REQUEST_DETAILS = "sqlQuery.getPendingRequestDetails";
	public static final String SQL_GET_PREVIOUS_PROVIDER_NAME = "sqlQuery.getPreviousProviderName";
	
	// Service Location Affiliate procedure parameters:
	public static final String in_tracking_number = "in_tracking_number";
	public static final String in_person_id = "in_person_id";
	public static final String in_srvc_req_type_cd = "in_srvc_req_type_cd";
	public static final String in_rep_cd = "in_rep_cd";
	public static final String in_offer_cd = "in_offer_cd";
	public static final String in_srvc_address_line_1 = "in_srvc_address_line_1";
	public static final String in_srvc_address_line_2 = "in_srvc_address_line_2";
	public static final String in_srvc_city = "in_srvc_city";
	public static final String in_srvc_state = "in_srvc_state";
	public static final String in_srvc_zip = "in_srvc_zip";
	public static final String in_addr_bill_sameas_srvc_flag = "in_addr_bill_sameas_srvc_flag";
	public static final String in_srvc_address_override_flag = "in_srvc_address_override_flag";
	public static final String in_blng_address_override_flag = "in_blng_address_override_flag";
	public static final String in_bill_address_line_1 = "in_bill_address_line_1";
	public static final String in_bill_address_line_2 = "in_bill_address_line_2";
	public static final String in_bill_city = "in_bill_city";
	public static final String in_bill_state = "in_bill_state";
	public static final String in_bill_zip = "in_bill_zip";
	public static final String in_service_start_date = "in_service_start_date";
	public static final String in_signup_channel_cd = "in_signup_channel_cd";
	public static final String in_srvc_zip_override_flag = "in_srvc_zip_override_flag";
	public static final String in_referrer_cd = "in_referrer_cd";
	public static final String in_completion_status_cd = "in_completion_status_cd";
	public static final String in_geo_zone = "in_geo_zone";
	public static final String in_offer_cell_trk_cd = "in_offer_cell_trk_cd";
	public static final String in_tdsp_cd = "in_tdsp_cd";
	public static final String in_offer_teaser = "in_offer_teaser";
	public static final String in_guid_id = "in_guid_id";
	public static final String in_offer_cell_trk_cd_selected = "in_offer_cell_trk_cd_selected";
	public static final String in_error_cd = "in_error_cd";
	public static final String in_promo_value = "in_promo_value";
	public static final String in_dwelling_type = "in_dwelling_type";
	public static final String in_recent_page_accessed = "in_recent_page_accessed";
	public static final String in_offer_type = "in_offer_type";
	public static final String in_product_sku_code = "in_product_sku_code";
	public static final String in_offer_name = "in_offer_name";
	public static final String in_enroll_source = "in_enroll_source";
	public static final String in_freq_flyer_first_name = "in_freq_flyer_first_name";
	public static final String in_freq_flyer_last_name = "in_freq_flyer_last_name";
	public static final String in_referral_id = "in_referral_id";
	public static final String in_recent_call_made = "in_recent_call_made";
	public static final String in_nest_sid = "in_nest_sid";
	public static final String in_deposit_cd = "in_deposit_cd";
	public static final String in_spanish_req_flag = "in_spanish_req_flag";
	public static final String in_provider_agreement_flag = "in_provider_agreement_flag";
	public static final String in_deposit_amount = "in_deposit_amount";
	public static final String in_contract_account_num = "in_contract_account_num";
	public static final String in_acct_name = "in_acct_name";
	public static final String in_mail_address_line_1 = "in_mail_address_line_1";
	public static final String in_mail_address_line_2 = "in_mail_address_line_2";
	public static final String in_mail_address_city = "in_mail_address_city";
	public static final String in_mail_address_state = "in_mail_address_state";
	public static final String in_mail_address_zip = "in_mail_address_zip";
	public static final String in_esid_match_flag = "in_esid_match_flag";
	public static final String in_ad_id = "in_ad_id";
	public static final String in_deposit_required_cd = "in_deposit_required_cd";
	public static final String in_ca_check_digit = "in_ca_check_digit";
	public static final String in_promo_type = "in_promo_type";
	public static final String in_cep_processed_flag = "in_cep_processed_flag";
	public static final String in_esuite_flag = "in_esuite_flag";
	public static final String in_esuite_status = "in_esuite_status";
	public static final String in_esuite_auth_flag = "in_esuite_auth_flag";
	public static final String in_esuite_oam_flag = "in_esuite_oam_flag";
	public static final String in_esuite_email_flag = "in_esuite_email_flag";
	public static final String in_esuite_auto_pay_flag = "in_esuite_auto_pay_flag";
	public static final String in_esuite_oam_date = "in_esuite_oam_date";
	public static final String in_esuite_email_date = "in_esuite_email_date";
	public static final String in_esuite_auto_pay_date = "in_esuite_auto_pay_date";
	public static final String in_esuite_auto_pay_option = "in_esuite_auto_pay_option";
	public static final String in_user_name = "in_user_name";
	public static final String in_password = "in_password";
	public static final String in_weekly_summary_enroll = "in_weekly_summary_enroll";
	public static final String in_permit_type = "in_permit_type";
	public static final String in_permit_class = "in_permit_class";
	public static final String in_permit_detail = "in_permit_detail";
	public static final String in_city_country = "in_city_country";
	public static final String in_city_country_name = "in_city_country_name";
	public static final String in_permit_phone = "in_permit_phone";
	public static final String in_priority_movein_flag = "in_priority_movein_flag";
	public static final String in_html_capture_flag = "in_html_capture_flag";
	public static final String in_non_com_prd = "in_non_com_prd";
	public static final String in_eco_share = "in_eco_share";
	public static final String in_active_customer_flag = "in_active_customer_flag";
	public static final String in_address_match_flag = "in_address_match_flag";
	public static final String in_pending_bal_flag = "in_pending_bal_flag";
	public static final String in_bp_active_contract = "in_bp_active_contract";
	public static final String in_matched_partner_id = "in_matched_partner_id";
	public static final String in_add_search_performed = "in_add_search_performed";
	public static final String in_bpmatch_no_ccs_resp = "in_bpmatch_no_ccs_resp";
	public static final String in_activation_fee = "in_activation_fee";
	public static final String in_bond_price = "in_bond_price";
	public static final String in_acc_sec_status = "in_acc_sec_status";
	public static final String in_is_pay_upfront = "in_is_pay_upfront";
	public static final String in_security_method = "in_security_method";
	public static final String in_activation_fee_cd = "in_activation_fee_cd";
	public static final String in_meter_type = "in_meter_type";
	public static final String in_sw_hold_status = "in_sw_hold_status";
	public static final String in_rec_disconnect_flag = "in_rec_disconnect_flag";
	public static final String in_premise_type = "in_premise_type";
	public static final String in_esid_status = "in_esid_status";
	public static final String in_verify_additional_options = "in_verify_additional_options";
	public static final String in_amb_prg_code = "in_amb_prg_code";
	public static final String in_paperless_prg_code = "in_paperless_prg_code";
	public static final String in_rhs_prg_code_one = "in_rhs_prg_code_one";
	public static final String in_rhs_prg_code_two = "in_rhs_prg_code_two";
	public static final String in_rhs_offer_type = "in_rhs_offer_type";
	public static final String in_prepay_flag = "in_prepay_flag";
	public static final String in_prepay_total_today = "in_prepay_total_today";
	public static final String in_prepay_doc_id = "in_prepay_doc_id";
	public static final String in_affiliate_id = "in_affiliate_id";
	public static final String in_brand_name = "in_brand_name";
	public static final String in_company_code = "in_company_code";
	public static final String in_gz_product_id = "in_gz_product_id";
	public static final String out_error_code = "out_error_code";
	
	// Person Affiliate procedure parameters:
	public static final String in_name_first = "in_name_first";
	public static final String in_name_last = "in_name_last";
	public static final String in_date_of_birth = "in_date_of_birth";
	public static final String in_ssn = "in_ssn";
	public static final String in_id_cd = "in_id_cd";
	public static final String in_id_number = "in_id_number";
	public static final String in_id_state_of_issue = "in_id_state_of_issue";
	public static final String in_phone_number = "in_phone_number";
	public static final String in_address_email = "in_address_email";
	public static final String in_language_cd = "in_language_cd";
	public static final String in_name_middle = "in_name_middle";
	public static final String in_email_option_rps = "in_email_option_rps";
	public static final String in_email_option_so = "in_email_option_so";
	public static final String in_email_option_ee = "in_email_option_ee";
	public static final String in_drl_four = "in_drl_four";
	public static final String in_ssn_four = "in_ssn_four";
	public static final String in_name_maiden = "in_name_maiden";
	public static final String in_posid_status = "in_posid_status";
	public static final String in_posid_date = "in_posid_date";
	public static final String in_contact_time_cd = "in_contact_time_cd";
	public static final String in_additional_name_first = "in_additional_name_first";
	public static final String in_additional_name_last = "in_additional_name_last";
	public static final String in_keep_me_informed_flag = "in_keep_me_informed_flag";
	public static final String in_contact_by_email_flag = "in_contact_by_email_flag";
	public static final String in_contact_by_phone_flag = "in_contact_by_phone_flag";
	public static final String in_person_status = "in_person_status";
	public static final String in_name_title = "in_name_title";
	public static final String in_fax_number = "in_fax_number";
	public static final String in_cred_level_num = "in_cred_level_num";
	public static final String in_cred_score_num = "in_cred_score_num";
	public static final String in_cred_source_num = "in_cred_source_num";
	public static final String in_business_partner_id = "in_business_partner_id";
	public static final String in_enrollment_number = "in_enrollment_number";
	public static final String in_cred_status_cd = "in_cred_status_cd";
	public static final String in_cred_status_date = "in_cred_status_date";
	public static final String in_idoc_number = "in_idoc_number";
	public static final String in_routing_number = "in_routing_number";
	public static final String in_banking_institution_name = "in_banking_institution_name";
	public static final String in_cc_type = "in_cc_type";
	public static final String in_cc_expiry_month = "in_cc_expiry_month";
	public static final String in_cc_expiry_year = "in_cc_expiry_year";
	public static final String in_cc_billzip = "in_cc_billzip";
	public static final String in_account_number = "in_account_number";
	public static final String in_account_name = "in_account_name";
	public static final String in_bankaccount_last_three = "in_bankaccount_last_three";
	public static final String in_adv_action_data = "in_adv_action_data";
	
	public static final String PROC_CHOICE_PERSON_ADD = "proc.addperson";
	public static final String PROC_CHOICE_PERSON_UPD = "proc.updateperson";
	
	public static final String PROC_CHOICE_SERVICE_LOCATION_ADD = "proc.addservicelocation";
	public static final String PROC_CHOICE_SERVICE_LOCATION_UPD = "proc.updateservicelocation";

	public static final String AFFILIATE_TRACKING_NO_SEQ = "affiliate_tracking_number_seq";
	public static final String AFFILIATE_PERSON_SEQ = "affiliate_person_id_seq";
	public static final String QUERY_SEQUENCE_NEXTVAL = "query.sequence.nextVal";
	
	public static final String SUCCESS = "success";
	public static final String FAILED = "failed";
	public static final String UPDATE = "update";
	public static final String ADD = "add";
	
	public static final String QUERY_GET_PERSON_AFFILIATE_ID_BY_TRACKING_NO = "query.get.personAffiliate.id.by.trackingNo";
	public static final String QUERY_GET_PERSON_AFFILIATE_ID_AND_RETRY_COUNT_BY_TRACKING_NO = "query.get.personAffiliate.id.and.retrycount.by.trackingNo";
	public static final String QUERY_GET_PERSON_AFFILIATE_DETAILS_BY_PERSON_ID = "query.get.personAffiliate.details.by.personId";
	public static final String QUERY_GET_SERVICE_LOCATION_AFFILIATE_DETAILS_BY_TRACKING_ID = "query.get.serviceLocationAffiliate.details.by.trackingId";
	public static final String QUERY_GET_ESIDCOUNT = "query.get.esidCount.sqlQuery";
	public static final String QUERY_GET_ESIDTYPE = "query.get.esidType.sqlQuery";
	
	//CHG0019468 Changes Start
	public static final String QUERY_GET_USER_ID="query.get.user.id";
	
	//CHG0026183 Changes Start
	public static final String QUERY_CIRRO_GET_USER_ID="query.cirro.get.user.id";
	
	public static final String SP_CSLR_UPDATE_USER_DETAILS  = "SP_UPDATE_USER_DETAILS" ;
	
	public static final String CONST_IN_OLD_USER_LOGIN_ID 					= "IN_OLD_USER_LOGIN_ID";
	public static final String CONST_IN_NEW_USER_LOGIN_ID             		= "IN_NEW_USER_LOGIN_ID";
	
	public static final String CONST_OUT_ERROR_MSG               = "OUT_ERR_MSG";
	public static final String CONST_OUT_OUT_ERROR_CODE          = "OUT_ERROR_CODE";
	
	public static final String DB_LEASE_ID ="LEASE_ID";
	public static final String DB_BUSINESS_PARTNER_ID ="BUSINESS_PARTNER_ID";
	public static final String DB_CONTRACT_ACCOUNT_NUMBER ="CONTRACT_ACCOUNT_NUMBER";
	public static final String DB_COMPANY_CODE ="COMPANY_CODE";
	
	public static final String DB_TCS_CA_BP_FROM_LEASE_ID ="db.tcs.ca.bp.from.leaseid";
	
    public static final String in_invoice_no = "in_invoice_no";
    public static final String in_contract_id = "in_contract_id";
    public static final String in_current_ar_amount = "in_current_ar_amount";
    
    public static final String CONST_OUT_RECORD_SET_CUR = "out_row_cur";

    public static String out_retro_elig_status = "out_retro_elig_status";

    public static String RETROPOPUP_PROC = "CPDB1_MAIN.PKG_ONLINE_USER_MANAGE_GME_MB.SP_GET_RETRO_ELIG_STATUS_GME";
    public static final String SQL_SAVE_PRIVACY_PREFERENCE = "sqlQuery.savePrivacyPreferenceSignUp";
    
    public static final String PROC_GET_HOUSE_AGE_HH_INCOME = "pod_pow.svcs_bus_prspct_dtls.sp_get_prspct_attr";
    public static final String CUR_OUT_ROWSET = "out_rowset";
    //US-F222-DK-10312018-MBAR
    public static final String QUERY_PAYMENT_RECEIPT_LOG= "sqlquery.cpdb.payment.validation.log";
	public static final String PAYMENT_VALIDATION_ACTIVE_ID = "PAYMENT_ACTIVE_ID";
	public static final String PAYMENT_AMOUNT = "PAYMENT_AMOUNT";
	public static final String PAYMENT_DATE = "PAYMENT_DATE";
	public static final String TP_PAYMENT_POPUP_SHOW_FLAG = "tpPaymentPopupShowFlag";
	public static final String TP_PAYMENT_POPUP_MESSAGE_SHOW_DAYS_CONFIG = "third.party.payment.popup.message.show.days";
	
	public static final String TP_PAYMENT_RECEIPT_SELECT_QUERY = "db.cpdb1.tpPaymentReceipt.selectPopupQuery";
	public static final String COL_TPP_PAYMENT_VALIDATION_ACTIVE_ID = "PAYMENT_VALIDATION_ACTIVE_ID";
	public static final String COL_TPP_PAYMENT_AMOUNT = "PAYMENT_AMOUNT";
	public static final String COL_TPP_PAYMENT_DATE = "PAYMENT_DATE";
	public static final String COL_TPP_RECEIPT_NUMBER = "RECEIPT_NUMBER";
	public static final String COL_TPP_VENDOR_ID = "VENDOR_ID";
	public static final String COL_TPP_SUCCESS_SHORT_MESSAGE = "SUCCESS_SHORT_MESSAGE";
	public static final String COL_TPP_CPDB_CREATION_DATE = "CPDB_CREATION_DATE";
	public static final String COL_TPP_CPDB_UPDATE_DATE = "CPDB_UPDATE_DATE";
	public static final String RESPONSE_DATE_FORMAT = "yyyy-MM-dd";
	public static final String CREATION_DATE = "CREATION_DATE";
	public static final String UPDATE_DATE = "UPDATE_DATE";
	
	//Columns of Mobile_content table
	public static final String COL_MOBILE_CONTENT_AREA = "AREA";
	public static final String COL_MOBILE_CONTENT_CNTNT_ID = "CNTNT_ID";
	public static final String COL_MOBILE_CONTENT_LANG = "LANG";
	public static final String COL_MOBILE_CONTENT_NAME = "NAME";
	public static final String COL_MOBILE_CONTENT_VALUE = "VALUE";
		
	public static final String QUERY_GET_ALL_CONTENT = "query.get.all.content";
	public static final String QUERY_GME_INSERT_PASSWORD_TNX = "query.gme.insert.password.tnx";
	public static final String QUERY_GME_INSERT_PASSWORD_STATUS_CODE = "query.gme.update.password.status.code";
	
	public static final String QUERY_GME_VALIDATE_PASSWORD_LINK = "query.gme.validate.password.link";
	public static final String QUERY_GET_USER_LOGIN_ID = "query.get.user.login.id";
	public static final String  OE_ADD_KBA_DETAILS_QUERY = "db.choice.reliant.addkbadetailsquery";
	
	
}