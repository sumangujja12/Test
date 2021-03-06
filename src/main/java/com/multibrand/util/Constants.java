package com.multibrand.util;

import java.util.Arrays;
import java.util.List;

public interface Constants {
	
	public static final String PROP_DEFAULT_ENV = "default.env";
	public static final String ENV_PROD = "PROD";

	public static final String ENV_PROPERTIES_FILE = "classpath:properties/environment";
	public static final String EMPTY = "";
	public static final String BLANK = " ";
	
	public static final String NOUSERLDAP_HEADER = "noUserInLDAP";
	public static final String LOCKFLAG_HEADER = "LOCKFLAG";
	
	//START :: GME PH II OE Integration
	public static final String SM_SSO_LOCKEDFLAG             = "SSO_LOCKEDFLAG"; // SM prefix to indicate that it is to refer SiteMinder variable
	public static final String SM_SSO_FAILURECOUNT           = "SSO_FAILURECOUNT";
	public static final String SSO_LOCKEDFLAG_VALUE_UNLOCKED = "0";
	public static final String SSO_LOCKEDFLAG_VALUE_LOCKED   = "1";
	public static final String SSO_LOCKEDFLAG_VALUE_DENIED   = "2";
	
	public static final String ZERO  = "0";
	public static final String ONE   = "1";
	public static final String TWO   = "2";
	public static final String THREE = "3";
	public static final String FOUR  = "4";
	public static final String FIVE  = "5";
	public static final String NA    = "NA";
	public static final String NINENINETYNINE   = "999";
	
	public static final String LDAP_ATTRIB_CUSTOM_LOCK_OUT = "customLockOut";
	public static final String LDAP_ATTRIB_FAILURE_COUNT   = "FailureCount";
	public static final String LDAP_ATTRIB_INVALID_LOGIN_COUNT = "invalidlogincount";
	
	public static final String MSG_EXCEPTION_ERROR_CODE    = "MSG_EXCEPTION";      // if any exception occured either in Login Success or Login Failure Call
	public static final String MSG_LOCKED_ERROR_CODE       = "MSG_LOCKED";         // customLockOut = 1 OR 2
	public static final String MSG_BAD_LOGIN_ERROR_CODE    = "MSG_BAD_LOGIN";      // customLockOut = 0 AND FailureCount = 1 OR 2 OR 3
	public static final String MSG_LOCK_PENDING_ERROR_CODE = "MSG_LOCK_PENDING";   // customLockOut = 0 AND FailureCount = 4
	public static final String MSG_USER_NOT_FOUND          = "MSG_USER_NOT_FOUND"; // when userNotFound header exists
	
	public static final String LOG_TXN_GME_RES_SYNC_LDAP   = "GME_RES_synchronizeLDAP";
	public static final String USER_LOCKEDOUT_UNKNOWN_ERROR_CODE   = "ErrorOccured";
	
	//END :: GME PH II OE Integration
		
	public static final int WEBSERVICE_CALL_TIMEOUT=45;
	public static final String CCS_USER_NAME = "CCSUSERNAME";
    public static final String CCS_PSD = "CCSPASSWORD";
    public static final String CCS_CRM_PROD_UPDATE = "CCS_CRM_PROD_UPDATE";
    public static final String CONTACT_DETAIL_ENDPOINT_URL_JNDINAME="CCS_CRM_CONTACT_DETAILS";
    public static final String PENDING_SWAP_ENDPOINT_URL_JNDINAME="CCS_GET_PENDING_SWAP_DETAILS";
    public static final String PLAN_HISTORY_ENDPOINT_URL_JNDINAME="CCS_GET_PLAN_HISTORY";
    public static final String AUTOPAY_DEENROLL_ENDPOINT_URL_JNDINAME="CCS_AUTOPAY_DEENROLL";
    public static final String BILL_PAYMENT_HISTORY_ENDPOINT_URL_JNDINAME="CCS_GET_BILL_PAYMENT_HISTORY";
    public static final String GET_CONTRACT_INFO_ENDPOINT_URL_JNDINAME="CCS_GET_CONTRACT_INFO";
    public static final String ENVIRONMENT_IMPACT_ENDPOINT_URL_JNDINAME="CCS_ENVIRONMENTAL_IMPACT";
    public static final String CCS_SECONDARY_NAME_UPDATE_JNDINAME="CCS_SECONDARY_NAME_UPDATE";
    public static final String CCS_GET_BILL_INFO_JNDINAME = "CCS_GET_BILL_INFO";
    public static final String WEB_SUBSCRIBER_ID = "WEB";
    public static final String IM_REQUEST_TYPE_UPDATE = "2";
    public static final String CRM_UPDATE_ACTION = "U";
    public static final String TYPE_E = "E";
    public static final String EFL = "EFL";
	public static final String TOS = "TOS";
	public static final String YRAAC = "YRAAC";
	public static final String EV_PRODUCT_TYPE_LAST_THREE = "004";
	
	// TPV Service Implementation : Start : KB
	public static final String TPV_CCS_URL = "ccs.tpv.api.trans.upd.wsdl.url";
	// TPV Service Implementation : End : KB
	
	public static final String MSG_ERR_UPD_CONTACT = "MSG_ERR_UPD_CONTACT";
	public static final String MSG_ERR_SUBMIT_SWAP = "MSG_ERR_SUBMIT_SWAP";
	//LDAP Constants
	
	public static final String INITIAL_CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
    public static final String LDAP_UNIQUEID_ATTRIBUTE="uniqueId";
	public static final String LDAP_UID_ATTRIBUTE="uid";
	public static final String LDAP_ACCOUNTNUMBER_ATTRIBUTE="accountnumber";
	public static final String LDAP_EMAIL_ATTRIBUTE="email";
	public static final String LDAP_PROVIDER_URL="LDAP_PROVIDER_URL";
	public static final String LDAP_SECURITY_AUTHENTICATION = "LDAP_SECURITY_AUTHENTICATION";
	public static final String LDAP_ADMIN_UID= "LDAP_ADMIN_UID";
	public static final String LDAP_SECURITY_CREDENTIALS = "LDAP_SECURITY_CREDENTIALS";
    public static final String LDAP_ORGANISATION="greenmountainresidential.com";
    
    public static final String MSG_USER_EXISTS = "MSG_USER_EXISTS";
	
	
    public static final String USER_NOT_FOUND_ERROR_CODE = "userNotFound";
	public static final String CREDENTIALS_MISMATCH_ERROR_CODE = "credentialsMismatch";
	public static final String USER_LOCKEDOUT_STATUS_ERROR_CODE = "userLocked";
	public static final String USER_IMPLICIT_UNLOCKED_ERROR_CODE = "userImplicitlyUnlocked";
	public static final String INVALID_IDENTIFICATION_CODE_ERROR_CODE = "invalidIdentification";
	public static final String NO_USER_FOR_EMAILID = "noUserForEmailId";
	public static final String NO_ACCOUNT_FOR_USER = "noAccountForUserId";
	public static final String AUTHENTICATION_UNKNOWN_ERROR = "authenticationUnknownError";
	
	public static final String COMPANY_CODE_RELIANT = "0121";
	public static final String COMPANY_CODE_GME = "0271";
	public static final String COMPANY_CODE_PENNYWISE = "0391";
	public static final String COMPANY_CODE_CIRRO = "0391";
	public static final String COMPANY_CODE_EE = "0400";
	public static final String COMPANY_CODE_DISCOUNTPOWER = "0391";
	
	public static final String COMPANY_NAME_RELIANT = "Reliant";
	public static final String COMPANY_NAME_GME = "Green Mountain";
	public static final String COMPANY_NAME_PENNYWISE = "Discount Power";
	public static final String COMPANY_NAME_CIRRO = "Cirro";
	public static final String COMPANY_NAME_EE = "Everything Energy";
	
	public static final String BRAND_ID_RELIANT = "RE";
	public static final String BRAND_ID_GME = "GM";
	public static final String BRAND_ID_PENNYWISE = "PW";
	public static final String BRAND_ID_CIRRO = "CE";
	public static final String BRAND_ID_EE = "EE";
	public static final String BRAND_ID_DISCOUNTPOWER = "DP";
	public static final String CCS_BRAND_ID_GME = "GR";
	public static final String BRAND_ID_GMD = "GMD";
	
	public static final String ALT_BRAND_ID_RELIANT = "REL";
	public static final String ALT_BRAND_ID_GME = "GME";
	public static final String ALT_BRAND_ID_PENNYWISE = "PEN";
	public static final String ALT_BRAND_ID_CIRRO = "CIR";
	public static final String ALT_BRAND_ID_EE = "EVE";
	
	public static final String BRAND_NAME_RELIANT = "Reliant";
	public static final String BRAND_NAME_GME = "Green Mountain";
	public static final String BRAND_NAME_PENNYWISE = "Discount Power";
	public static final String BRAND_NAME_CIRRO = "Cirro";
	public static final String BRAND_NAME_EE = "Everything Energy";
	
	public static final String LDAP_ORG_GME = "greenmountainresidential.com";
	public static final int OAM_LOCKOUT_EXPIRY = 24;
	public static final int OAM_LOGIN_TIME_PERIOD = 30;
	public static final int DEFAULT_OAM_MAX_INVALID_LOGIN_COUNT = 5;
	public static final String OAM_MAX_INVALID_LOGIN_COUNT_FROM_ENV_PROP = "OAM_MAX_INVALID_LOGIN_COUNT";
	public static final String POOL_CONFIG="com.sun.jndi.ldap.connect.pool";
	public static final String BASE_STRING="ou=People,o=reliant.com";
	
	
	// Web services urls JNDI variable names
	public static final String PROFILE_SERVICE_ENDPOINT_URL = "ws.endpointURL.profileDomain";
	public static final String HISTORY_SERVICE_ENDPOINT_URL = "ws.endpointURL.historyDomain";
	public static final String BILLING_SERVICE_ENDPOINT_URL = "ws.endpointURL.billingDomain";
	public static final String LDAP_SERVICE_ENDPOINT_URL = "ws.endpointURL.ldapDomain";
	public static final String PAYMENT_SERVICE_ENDPOINT_URL = "ws.endpointURL.paymentDomain";
	public static final String ADDRESS_VALIDATION_SERVICE_ENDPOINT_URL = "ws.endpointURL.addressvalidationDomain";
	public static final String SWAP_SERVICE_ENDPOINT_URL = "ws.endpointURL.swapDomain";
	public static final String TOS_SERVICE_ENDPOINT_URL ="ws.endpointURL.tosDomain";
	public static final String OE_SERVICE_ENDPOINT_URL ="ws.endpointURL.oeDomain"; //OE
	//START ONLINE AFFILIATES PROJECT - JSINGH1
	public static final String VALIDATION_DOMAIN_END_POINT_URL_JNDI_NAME="ws.endpointURL.validationDomain";
	//END ONLINE AFFILIATES PROJECT - JSINGH1
	//DK
	public static final String SS_SERVICE_ENDPOINT_URL ="ws.endpointURL.ssDomain"; //Prepay
	public static final String SM_TEMP_SERVICE_ENDPOINT_URL = "ws.endpointURL.temperatureService";
	
	public static final String XI_HISTORY_ENDPOINT_URL = "xi.history.endpointURL";
	public static final String XI_PAYMENT_HISTORY_ENDPOINT_URL_QUERY_PARAM = "&interface=os_getPaymentHistory&service=Srv_GME&qos=BE&sap-user=SWAP_CPIC&sap-password=pawsC0mm&sap-client=110&sap-language=EN";
	public static final String XI_INVOICE_LIST_ENDPOINT_URL_QUERY_PARAM = "&interface=os_getInvoiceList&service=Srv_GME&qos=BE&sap-user=SWAP_CPIC&sap-password=pawsC0mm&sap-client=110&sap-language=EN";
	public static final String XI_PRODUCT_HISTORY_ENDPOINT_URL_QUERY_PARAM = "&interface=os_getProductHistory&service=Srv_GME&qos=BE&sap-user=SWAP_CPIC&sap-password=pawsC0mm&sap-client=110&sap-language=EN";
	public static final String XI_INTERVAL_DATA_ENDPOINT_URL_QUERY_PARAM   = "&interface=os_getIntervalData&service=Srv_GME&qos=BE&sap-user=SWAP_CPIC&sap-password=pawsC0mm&sap-client=110&sap-language=EN";
	
	
	public static final String EBILL_DOCUMENTUM_WS_USER_NAME= "EBILL_DOCUMENTUM_WS_USER_NAME";
	public static final String EBILL_DOCUMENTUM_WS_PSD ="EBILL_DOCUMENTUM_WS_PWD";
	public static final String EBILL_DOCUMENTUM_END_POINT_URL ="EBILL_DOCUMENTUM_END_POINT_URL";
	public static final String UTILITY_SERVICE_ENDPOINT_URL = "UTILITY_SERVICE_ENDPOINT_URL";
	public static final String DEFAULT_DOCTYPE="GMEINV";
	
    // Constant for paperless bill source   
    public static final String EBPP_SOURCE="0000";
    
    // Constant for paper flag
    public static final String PAPER_FLAG="O";
    
    
    //Constant for paperless flag
    public static final String PAPERLESS_FLAG="X";
    
    public static final String FLAG_YES="Y";
    public static final String NO_MATCH = "NO_MATCH";
    
    
    //Result codes for final responses
    public static final String RESULT_CODE_TWO="2";
    public static final String RESULT_CODE_THREE="3";
    public static final String RESULT_CODE_FOUR="4"; 
    public static final String RESULT_CODE_EIGHT="8"; 
        
    public static final String RESULT_CODE_CCS_ERROR="2";
    public static final String RESULT_CODE_SUCCESS="0";
    public static final String RESULT_CODE_EXCEPTION_FAILURE="1";
    public static final String RESULT_CODE_NO_DATA="3";
    public static final String RESULT_DESCRIPTION_EXCEPTION="Exception Occurred";
    public static final String RESULT_DESCRIPTION_CCS_EXCEPTION="Exception Occurred in CCS Call";
    public static final String RESULT_DESCRIPTION_BUSINESS_METER="Business meter";  
    public static final String RESULT_DESCRIPTION_ACTIVE_ESID="Active esid"; 
    public static final String RESULT_DESCRIPTION_SWITCH_HOLD="Switch hold on"; 
    public static final String RESULT_DESCRIPTION_TDSP_MISMATCH="TDSP mismatch";
    public static final String RESULT_DESCRIPTION_CREDIT_CHECK_FAILED="CCSD-Credit check call failed";
    public static final String ERROR_CODE_TDSP_MISMATCH="TDSP_MISMATCH";
    public static final String RESULT_DESCRIPTION_INVALID_DOB="Age must be atleast 18 years and atmost 100 years";
    
    //START US515 : GME_MyAccount_CompanyCode Validation :adadan
    public static final String RESULT_CODE_NOT_GME_TX_ACC="This account is ineligible to be managed via My Account";
    public static final String RESULT_CODE_FIVE="5";
    //END US515 : GME_MyAccount_CompanyCode Validation :adadan
    
    //Result code description
    
    public static final String RESULT_DESCRIPTION_USERNAME_EXISTS="Username Exists";
    public static final String RESULT_DESCRIPTION_ACCOUNT_EXISTS="Account Already Registered";
    public static final String RESULT_CODE_NO_MATCH="2";
    public static final String RESULT_CODE_NO_MATCH_DESCRIPTION="No Match";
    public static final String RESULT_CODE_INVALID_ACCOUNT_NUMBER_DESCRIPTION="Invalid Account Number";
    public static final String RESULT_CODE_ACCOUNT_ALREADY="Account Already Registered";
    public static final String RESULT_DESCRIPTION_ENROLL_FAILED="Enroll is failed";
    public static final String RESULT_DESCRIPTION_DEENROLL_FAILED="De-enroll is failed";
    public static final String RESULT_CODE_DATA_ALREADY_DESCRIPTION="User Name Already Available";
    public static final String RESULT_CODE_DESCRIPTION_NO_DATA="No Data";
    public static final String RESULT_CODE_DESC_PSD_MISMATCH="Old Password Mismatch";
    public static final String RESULT_CODE_DESC_AVG_TEMP="Average Temperature information is unavailable";
    
    public static final String RESULT_CODE_CCS_ERROR_DESCRIPTION_01 ="Invalid Contract Account";
    public static final String RESULT_CODE_CCS_ERROR_DESCRIPTION_02 ="Autopay de-enroll failed";
    public static final String RESULT_CODE_CCS_ERROR_DESCRIPTION_03 ="Invalid Payment Method";
    
    public static final String MSG_SUCCESS = "Success";
     public static final String FLAG_TRUE ="true";
    public static final String FLAG_FALSE ="false";
    public static final String METER_TYPE_AMSR ="AMSR";
    public static final String METER_TYPE_AMSM ="AMSM";
    public static final String METER_TYPE_NON_AMSR ="NAMSR";

    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String MMddyyyy = "MMddyyyy";
    public static final String invalidDate = "0000-00-00";
    public static final String invalidDate1 = "00000000";
	//For swap
    
    public static final String RESPONSE_DATE_FORMAT = "yyyy-MM-dd";
    public static final String MM_dd_yyyy = "MM/dd/yyyy";
    public static final String CONTRACT_END_DATE="9999-12-31";
    public static final String COUNTRY_US = "US";
    public static final String LANG_ES = "S";

    public static final String WEBGM = "WEBGM";
    public static final String WEBCE = "WEBCE";
    
    
    public static final String REFERENCE_ID="REFERENCE_ID";
    public static final String TRANSACTION_TYPE="TRANSACTION_TYPE";
    public static final String COMPANY_CODE="COMPANY_CODE";
    public static final String UPDATE_CRM_CONTACT_DETAILS="UPDATE_CRM_CONTACT_DETAILS";
    
    public static final String REMARK_HOME = "HOME";
    public static final String REMARK_WORK = "WORK";
    public static final String REMARK_CELL = "CELL";
    
    //Start: Payment History
    public  static final String CANCELLED = "CANCELLED";
    public  static final String REVERSE = "REVERSE";
    public  static final String SENT = "SENT";
    public  static final String POSTED = "POSTED";
    public  static final String RETRY = "RETRY";
    public  static final String SCHEDULED = "SCHEDULED";
    public static final String DECLINED = "DECLINED";
    //End: Payment History
    
    //START: OE
    public static final String DATE_FORMAT="MM/dd/yyyy";
    public static final String TIME_FORMAT="HH:mm:ss";
    public static final String EN_US = "en_US";
	public static final String ES_US = "es_US";
    public static final String LANG_EN = "E";
	public static final String STATE = "STATE";
	public static final String CITY = "CITY";
	public static final String PROP_COMPANY_CODE ="company.code";	
	public static final String PARTIAL_MATCH = "PARTIAL_MATCH";
	public static final String COMPLETE_MATCH = "COMPLETE_MATCH";
	public static final String NESID = "NESID";
	public static final String MESID = "MESID";
	public static final String NRESID = "NRESID";
	public static final String NOZ	= "NOZ";
	public static final String DELIMETER_COMMA = ",";
	public static final String PROMO_CODE_1="PromoCode1";
	public static final String PROMO_CODE_2="PromoCode2";
	public static final String AMSR = "AMSR";
	public static final String AMSM = "AMSM";
	public static final String SINGLE_QUOTE = "'";
	public static final String ERR_CODE_KEY = "error";
	public static final String ERROR_TYPE = "ErrorType";
	public static final String ERR_GET_POW_OFFER = "ERR_GET_POW_OFFER";
	public static final String ERR_GET_OFFER = "ERR_GET_OFFER";
	public static final String ERROR_TYPE_CCS = "CCS";
	public static final String UNKNOWN_ERROR_CODE = "MSG_UNKNOWN_ERROR";
	public static final String DELIMETER_PIPE = "|";
	public static final String S_BUNDLE="S_BUNDLE";
	public static final String S_CUSTCHRG="S_CUSTCHRG";
	public static final String ENERGY_CHARGE="S_UNBUNDLE";
	public static final String PROD_TYPE="PROD_TYPE";
	public static final String OFFERS_LIST = "OFFERS_LIST"; 
	public static final String DISPLAYED_OFFER_CODE_LIST = "DISPLAYED_OFFER_CODE_LIST";
	public static final String SDL_ERROR = "SDL_ERROR";
	public static final String CCS_ERROR = "CCS_ERROR";
	public static final String ERROR_TYPE_DB = "DB";
	public static final String OFFER_FETCH_SOURCE_POW="POW";
	public static final String OFFER_FETCH_SOURCE_REACTIVE="REACTIVE";
	public static final String PREPAY_OFFERS = "PREPAY_OFFERS";
	public static final String POSTPAY_OFFERS = "POSTPAY_OFFERS";
	public static final String ALL_OFFERS = "ALL_OFFERS";
	public static final String OE_OFFER_CATEGORY = "OFFER_CATEGORY";
	public static final String OFFER_CODE = "OFFER_CODE";
	public static final String SALEABLE = "SALEABLE";
	public static final String OFFER_CATEGORY_POSTPAY = "POSTPAY";
	public static final String OFFER_CATEGORY_PREPAY = "PREPAY";
	
	//END: OE
	
	public static final String DEFAULT_REL_TYPE="BUR001";
	
	// For UtilityService for Email and logging ::
	public static final String UTILITY_SERVICE_LOGGING_COMPANY = "NRGREST";
	public static final String METHOD_SYNCHRONIZE_LOGGING ="logTransaction";
	
	public static final String MSG_ERR_GET_PROFILE = "MSG_ERR_GET_PROFILE";
	public static final String MSG_ERR_GET_PROFILE_BILLING_ADDRESS = "MSG_ERR_PROFILE_BILLING_ADDRESS_NOT_FOUND";
	public static final String MSG_ERR_GET_PROFILE_CONTRACT_DETAILS = "MSG_ERR_PROFILE_CONTRACT_DETAILS_NOT_FOUND";
	public static final String MSG_ERR_GET_PROFILE_SERVICE_ADDRESS = "MSG_ERR_PROFILE_SERVICE_ADDRESS_NOT_FOUND";
	public static final String FLAG_X = "X";
	public static final String FLAG_O = "O";
	public static final String PROFILE_CADATA_ENDPOINT_URL_JNDINAME = "CCS_PROFILE_CADATA";
	public static final String SUCCESS_RESPONSE = "0";
	public static final String MSG_CCSERR_ = "MSG_CCSERR_";
	public static final String _GET_PROFILE = "_GET_PROFILE";
	public static final String MSG_SYSTEM_UNAVAILABLE = "MSG_SYSTEM_UNAVAILABLE";
	public static final String MSG_IDOC_NOT_PROCESSED = "MSG_IDOC_NOT_PROCESSED";
	public static final String FLAG_Y = "Y";
	
	public static final String FLAG_N = "N";
	 public static final String FLAG_NO = "NO";
	 public static final String FLAG_YES_PROFILE = "YES";
	 
	 public static final String DEPOSIT_OWED = "OWED";
	 public static final String DEPOSIT_NONE = "NONE";
	 
	 // Templates and properties for sending mails
	 public static final String CREATE_USER_EN = "GME.ACCOUNT.REGISTRATION.CONFIRM.EN_US";
	 public static final String CREATE_USER_ES = "GME.ACCOUNT.REGISTRATION.CONFIRM.ES_US";
	 public static final String BILL_PAY_BANK_EN ="GME.BILL.PAY.CONFIRM.BANK.EN_US"; 
	 public static final String BILL_PAY_BANK_ES ="GME.BILL.PAY.CONFIRM.BANK.ES_US";
	 public static final String BILL_PAY_CC_EN ="GME.BILL.PAY.CONFIRM.CC.EN_US"; 
	 public static final String BILL_PAY_CC_ES ="GME.BILL.PAY.CONFIRM.CC.ES_US";
	 public static final String AUTO_PAY_ENROLL_CONF_BANK_EN="GME.AUTO.PAY.ENROLL.CONFIRM.BANK.EN_US";
	 public static final String AUTO_PAY_ENROLL_CONF_BANK_ES="GME.AUTO.PAY.ENROLL.CONFIRM.BANK.ES_US";
	 public static final String AUTO_PAY_ENROLL_CONF_CC_EN="GME.AUTO.PAY.ENROLL.CONFIRM.CC.EN_US";
	 public static final String AUTO_PAY_ENROLL_CONF_CC_ES="GME.AUTO.PAY.ENROLL.CONFIRM.CC.ES_US";
	 public static final String SUBMIT_SWAP_CONF_EN ="GME.SUBMIT.SWAP.CONFIRM.EN_US";
	 public static final String SUBMIT_SWAP_CONF_ES ="GME.SUBMIT.SWAP.CONFIRM.ES_US";
	 public static final String SUBMIT_TOS_CONF_EN="GME.SUBMIT.TOS.CONFIRM.EN_US";
	 public static final String SUBMIT_TOS_CONF_ES="GME.SUBMIT.TOS.CONFIRM.ES_US";
	 public static final String AUTOPAY_DEENROLL_EN="GME.AUTO.PAY.DEENROLL.CONFIRM.EN_US";
	 public static final String AUTOPAY_DEENROLL_ES="GME.AUTO.PAY.DEENROLL.CONFIRM.ES_US";
	 
	 public static final String ACCOUNT_NUMBER ="accountNumber";
	 public static final String CONTRACT_NUMBER="contractNumber";
	 public static final String CHECK_DIGIT = "checkDigit";
	 public static final String BP_NUMBER = "bpNumber";
	 public static final String ACCOUNT_NUMBER_HIDDEN = "accountNumberHidden";
	 public static final String ACCOUNT_NAME="accountName";
	 public static final String USER_ID="userId";
	 
	 public static final String TRANSACTION_DATE="transactionDate";
	 public static final String PAYMENT_AMOUNT="paymentAmount";
	 public static final String SCHEDULED_PAYMENT_DATE="scheduledPaymentDate";
	 public static final String PAYMENT_METHOD="paymentMethod";
	 public static final String PAYMENT_METHOD_BANK="Bank";
	 public static final String PAYMENT_METHOD_BANK_ES="Banco";
	 public static final String PAYMENT_METHOD_CARD="Credit Card";
	 public static final String PAYMENT_METHOD_CARD_ES="Tarjeta de Cr&#233;dito";
	 
	 public static final String VISA = "Visa";
	 public static final String MASTERCARD = "MasterCard";
	 public static final String DISCOVER = "Discover";
	 public static final String AMERICANEXPRESS = "AmericanExpress";
	 public static final String ZVIS = "ZVIS";
	 public static final String ZMCD = "ZMCD";
	 public static final String ZDSC = "ZDSC";
	 public static final String ZAMX = "ZAMX";
	 
	 
	 public static final String CARD_TYPE="cardType";
	 public static final String CARD_NUMBER="cardNumber";
	 public static final String EXP_DATE="expDate";
	 public static final String BANK_ROUTING_NUMBER="bankRoutingNumber";
	 public static final String BANK_ACCOUNT_NUMBER="bankAccountNumber";
	 
	 public static final String SERVICE_ADDRESS="serviceAddress";
	 public static final String SERVICE_CITY="serviceCity";
	 public static final String SERVICE_STATE="serviceState";
	 public static final String SERVICE_ZIP="serviceZipCode";
	 public static final String BILLING_ADDRESS="billingAddress";
	 public static final String BILLING_CITY="billingCity";
	 public static final String BILLING_STATE="billingState";
	 public static final String BILLING_ZIP="billingZipCode";
	 public static final String ESID="esid";
	 public static final String NEW_PLAN_NAME="newPlanName";
	 public static final String AVG_PRICE="avgPrice";
	 public static final String PRODUCT_CONTENT="productContent";
	 public static final String CONTRACT_TERM="contractTerm";
	 public static final String CONTRACT_BEGINS="contractBegins";
	 public static final String CONTRACT_ENDS="contractEnds";
	 public static final String CANCEL_FEE="cancelFee";
	 public static final String EFL_URL="eflURL";
	 public static final String EFL_SMART_CODE="eflSmartCode";
	 public static final String TOS_URL="tosURL";	 
	 public static final String TOS_SMART_CODE="tosSmartCode";
	 public static final String YRAAC_URL="yraacURL";
	 public static final String YRAAC_SMART_CODE="yraacSmartCode";
	 
	 public static final String SUBMIT_DATE="submitDate";
	 public static final String TRANSACTION_NUMBER="transactionNumber";
	 public static final String POWER_ON="powerOn";
	 public static final String NEW_SERVICE_ADDRESS="newServiceAddress";
	 
	 public static final String POWER_OFF="powerOff";
	 
	 public static final String EXISTING_SERVICE_ADDRESS="existingServiceAddress";
	 public static final String EXISTING_SERVICE_CITY="existingServiceCity";
	 public static final String EXISTING_SERVICE_STATE="existingServiceState";
	 public static final String EXISTING_SERVICE_ZIP="existingServiceZipCode";
	 
	 public static final String PHONE_NUBMER="phoneNumber";
	 public static final String PLAN_NAME="planName";
	 public static final String PLAN_TYPE="planType";
	 public static final String ADDITION_PROGS="additionalProgs";
	 public static final String BILL_DELIVERY_METHOD="billDeliveryMethod";
	 public static final String CONTACT_NAME="contactName";
	 public static final String CONTACT_PHONE_NUMBER="contactPhoneNumber";
	 public static final String CONTACT_EMAIL_ADDRESS="contactEmailAddress";
	 
	 public static final String LANGUAGE_CODE_EN="E";
	 public static final String LANGUAGE_CODE_ES="S";
	 
	 public static final String MVI = "MVI";
	 public static final String NRGREST_LOGGER="NRGREST_LOGGER";
	 public static final String COMPANY_CODE_MANDATORY ="Company Code is mandatory";
	 public static final String ZIPCODE_OR_TDSP_OR_ESID_MANDATORY ="Zip code or TDSP code or ESID is mandatory";
	 
	 public static final String SWAP_BCC_MAIL = "swap.bcc.mail";
	 public static final String QC_BCC_MAIL ="qc.bcc.mail";
	 public static final String DT_FMT ="yyyyMMdd";
	 public static final String DT_AVGTEMP_FMT ="yyyy-MM-dd";
	 public static final String DT_SQL_FMT ="yyyy-MM-dd HH:mm:ss.sss";
	 public static final String DT_SQL_FMT_DB ="yyyy-MM-dd HH:mm:ss";
	 public static final String DT_FMT_REQUEST ="MM/dd/yyyy";
	 public static final String DT_FMT_DD_MM_YYYY= "dd/MM/yyyy";
	 
	 public static final String WS_CONTACT_LOG ="X";
	 public static final String WS_CONTACT_ACTIVITY_EN ="0810";
	 public static final String WS_CONTACT_ACTIVITY_D_EN ="0830";
	 public static final String WS_CONTACT_CLASS ="0300";
	 public static final String WS_CONTACT_TEXT_EN ="WSE Email Set Up-WEB";
	 public static final String WS_CONTACT_TEXT_D_EN ="WSE Denroll";
	 public static final String WS_PROGRAM ="ES";
	 public static final String WS_EN ="ENROLL";
	 public static final String WS_DE_EN ="DEENROLL";
	 public static final String WS_CHANNEL ="3";
	 public static final String WS_REQ_END_DATE= "9999-12-31";
	 
	 public static final String DAILY_INDICATOR ="D";
	 
	 String UTILITY_SERVICE_LOGGING_ENABLE ="utility.logging";
	 
	 //START - Cirro Energy
	 public static final String ZIPCODE_MANDATORY ="Zip code is mandatory";
	 
	 public static final String  CIRRO_BRAND_NAME="CE";
	 public static final String  CIRRO_COMPANY_CODE="0391";
	 public static final String  GME_BRAND_NAME="GM";
	 public static final String  GME_RES_COMPANY_CODE="0271";
	 public static final String  GME_RES_EMAIL_TYPE_ID_PREPAY="GME.EMAIL.PREPAY";	 
	 public static final String  CIRRO_DUMMY_CHARITY_ID="CIRO";
	 public static final String  CCS_ERETURN_CODE_INVALID_BPID="1";
	 public static final String  CCS_INVALID_BPID_RESULT_DESCRIPTION="INVALID_BUSINESS_PARTNER_ID";
	 public static final String  CCS_ERETURN_CODE_INVALID_BPID_OR_UPDATE_FLAG="2";
	 public static final String  CCS_INVALID_BPID_OR_UPDATE_FLAG_RESULT_DESCRIPTION="INVALID_BUSINESS_PARTNER_ID_OR_UPDATE_FLAG";
	 public static final String  CCS_ERETURN_CODE_INSERT_UPDATE_ERROR="1";
	 public static final String  CCS_ERETURN_CODE_INSERT_UPDATE_ERROR_RESULT_DESCRIPTION="INSERT_UPDATE_ERROR";
	 public static final String  CCS_ERETURN_CODE_DELETE_UPDATE_ERROR="3";
	 public static final String  CCS_ERETURN_CODE_DELETE_UPDATE_ERROR_RESULT_DESCRIPTION="DELETE_UPDATE_ERROR";
	 public static final String  CCS_ERETURN_CODE_INVALID_TOKEN_DATA_INPUT="5";
	 public static final String  CCS_ERETURN_CODE_INVALID_TOKEN_DATA_INPUT_RESULT_DESCRIPTION="INVALID_TOKEN_DATA_INPUT";
	 
	 public static final String  CCS_ERETURN_CODE_CC_UPDATE_ERROR="6";
	 public static final String  CCS_ERETURN_CODE_CC_UPDATE_ERROR_RESULT_DESCRIPTION="UPDATE_ERROR";
	 
	 public static final String  CCS_ERETURN_CODE_CC_BP_MISMATCH="7";
	 public static final String  CCS_ERETURN_CODE_CC_BP_MISMATCH_RESULT_DESCRIPTION="CC_BP_MISMATCH";
	 
	 public static final String  CCS_ERETURN_CODE_DELETE_UPDATE_ERROR_ON_INCOMING_METHOD="4";
	 public static final String  CCS_ERETURN_CODE_DELETE_UPDATE_ERROR_ON_INCOMING_METHOD_RESULT_DESCRIPTION="DELETE_UPDATE_ERROR_ON_INCOMING_METHOD";
	 public static final String  CCS_ERETURN_CODE_INPUT_ERROR_OR_INVALID_DATA = "1";
	 public static final String CCS_ERETURN_CODE_INPUT_ERROR_OR_INVALID_DATA_RESULT_DESCRIPTION = "INVALID_DATA_OR_INPUT_ERROR";
	 public static final String CCS_ERETURN_CODE_UPDATE_ERROR ="2";
	 public static final String CCS_ERETURN_CODE_UPDATE_ERROR_RESULT_DESCRIPTION="UPDATE_ERROR";
	 
	 public static final String ONLINE_ACCOUNT_TYPE_BANK="B";
	 public static final String ONLINE_ACCOUNT_TYPE_CC="C";
	 
	 public static final String UPDATE_BANK_INFO_ADD="I";
	 public static final String UPDATE_BANK_INFO_DELETE="D";
	 public static final String UPDATE_BANK_INFO_UPDATE="U";
	 
	 public static final String UPDATE_PAYMENT_OPTIONS_ADD="I";
	 public static final String UPDATE_PAYMENT_OPTIONS_DELETE="D";
	 public static final String UPDATE_PAYMENT_OPTIONS_UPDATE="U";
	 
	 public static final String UPDATE_CC_INFO_ADD="I";
	 public static final String UPDATE_CC_INFO_DELETE="D";
	 public static final String UPDATE_CC_INFO_UPDATE="U";
	 
	 public static final String CIRRO_SUBMIT_SWAP_CONF_EN ="CIRRO.SUBMIT.SWAP.CONFIRM.EN_US";
	 public static final String CIRRO_SUBMIT_SWAP_CONF_ES ="CIRRO.SUBMIT.SWAP.CONFIRM.ES_US";
	 public static final String MARKETING_TEXT="marketingText";
	 public static final String DISCLAIMER="disclaimer";
	 public static final String QC_CIRRO_BCC_MAIL ="qc.cirro.bcc.mail";
	 public static final String SWAP_CIRRO_BCC_MAIL ="swap.cirro.bcc.mail";	 

	 
	 public static final String PREFERENCE_SERVICE_ENDPOINT_URL="ws.endpointURL.preferenceDomain";
	 public static final String NO_ERROR_CODE="NO_ERROR";
	 
	 public static final String INVALID_REQ="INVALID_REQUEST";
	 public static final String SUCCESS_CODE="00";
	 public static final String PAYMENT_MODE_CC="CC";
	 public static final String PAYMENT_MODE_BANK="BD";

	 // START ONLINE AFFILIATES PROJECT - JYOGAPA1
	 public static final String TX = "TX";
	
	 public static final String Z1 = "Z1";
	 public static final String Z5 = "Z5";
	 public static final String SPANISH = "S";
	 public static final String DOT = ".";
	 public static final String SYMBOL_COMMA = ",";
	 public static final String SYMBOL_PIPE = "|";
	
  	 // UTILITY SERVICE CONSTANTS
	 public static final String LOGGING_SERVICE = "LoggingService";
	 public static final String EMAIL_SERVICE = "EmailService";
	 
	 public static final String RHS_MULTI_FAMILY  ="MF";
	public static final String BPTYPE = "1";
	public static final String BPGROUP = "0001";
	public static final String SWI = "SWI";
	public static final String MOVEIN = "MOVEIN";
	public static final String SWITCH = "SWITCH";
	public static final String CHANNEL = "WEB";
	public static final String TRANSACTION_TYPE_CREATE = "CREATE";

	public static final String INSTALLATION_TYPE = "NINV";
	public static final String SSN = "SSN";
	public static final String DL = "DL";
	public static final String BANK = "BANK";
	public static final String BNK = "BNK";
	public static final String ES = "ES";
	public static final String EN="EN";
	public static final String S = "S";
	public static final String E = "E";
	public static final String ACCSECHOLD = "ACCSECHOLD";
	public static final String PPYHOLD = "PPYHOLD";
	public static final String SWITCHHOLD = "SWITCHHOLD";
	public static final String SEXAVAILABLE = "X";
	public static final String ADDRESS_TYPE = "B1";
	public static final String TIMEZONE = "CST";
	public static final String DIVISION = "10";
	public static final String SURETY_BOND = "ACTIVATION_FEE";
	
	public static final String BAL_ALERT = "WSB1";
	public static final String LOW_ALERT = "TMD1";
	public static final String PAY_ALERT = "PAY1";
	public static final String GME_BAL_ALERT = "DBA1 ";
	public static final String GME_PP_ALERT_DBA1="DBA1";
	 public static final String PP_ALERT4 = "TTS1";
	public static final String COMM_PREF = "COMMPREF";
	public static final String PREPAY = "PREPAY";
	public static final String ESIDNOTFOUND = "<ESIDNOTFOUND>";
	public static final String ON = "ON";
	
	public static final String ERROR_VARIABLE = "errorVariable";

	public static final String ENROLLMENT_STATUS = "enrollmentStatus";
	
	public static final String INIT = "INIT";

	public static final String TIBCOSD = "TIBCOSD";
	
	// enrollment status
	public static final String CA = "CA";
	public static final String N_VALUE = "N";

	public static final String I_VALUE = "I";
	public static final String U_VALUE = "U";

	public static final String T_VALUE = "T";
	public static final String BPSD = "BPSD";
	public static final String SD = "SD";
	public static final String TDSPSD= "TDSPSD";

	// account status
	public static final String NSD = "NSD";
	public static final String D_VALUE = "D";
	
	public static final String FLAG_C = "C";
	public static final String FLAG_B = "B";
	
	public static final String OFFER_CATEGORY_UA = "UA";
	public static final String OFFER_CATEGORY_AA = "AA";
	public static final String OFFER_CATEGORY_SOUTHWEST = "SOUTHWEST";
	public static final String OFFER_CATEGORY_UNITED = "UNITED";
	
	public static final String CALL_NAME_CREATE_PREPAY_DOC = "PREPAY_DOC_CREATION";
	public static final String CALL_NAME_UPDATE_ALERT_PREFERENCES = "PREPAY_ALERT_PREFERENCE_UPDATION";
	public static final String CALL_NAME_EMAIL_CONFIRMATION = "SEND_TEMPLATE_EMAIL_CONFIRMATION";
	public static final String CALL_NAME_EPLAN_AUTO_GENERATED_MAIL = "EPLAN_SEND_AUTOGENERATED_MAIL_TO_CCR";
	public static final String CALL_NAME_UPDATE_CONTACT_INFO = "UPDATE_CONTACT_INFO";
	
	public static final String EXCEPTION_IN_PREPAY_PAYMENT_SUBMISSION = "Exception_In_Prepay_Payment_Submission_In_Submit_Enrollment";
	public static final String EXCEPTION_IN_UPDATE_ALERT_PREFERENCE = "Exception_In_Update_Alert_Preference_In_Submit_Enrollment";
	public static final String EXCEPTION_IN_AUTH_CAPTURE_CALL = "Exception_In_AuthCapture_Call";
	public static final String EXCEPTION_IN_SUBMIT_ENROLLMENT_CALL = "Exception_In_Submit_Enrollment_Call";
	public static final String EXCEPTION_IN_PROC_ADD_DOD_ENROLLMENT = "Exception_In_Proc_Add_Dod_Enrollment";
	public static final String EXCEPTION_IN_PROC_ADD_NON_COMMODITY_BILLING = "Exception_In_Proc_Add_Non_Comodity_billing";
	public static final String ERROR_IN_PROC_ADD_NON_COMMODITY_BILLING = "Error_In_Proc_Add_Non_Comodity_billing";
	public static final String Error_IN_PROC_ADD_DOD_ENROLLMENT = "Error_In_Proc_Add_Dod_Enrollment";
	public static final String ERROR_IN_AUTH_CAPTURE_CALL = "Error_In_AuthCapture_Call";
	public static final String ERROR_IN_CREATE_PREPAY_DOC_CALL = "Error_in_Create_PrepayDoc_Call";
	public static final String ERROR_IN_SUBMIT_ENROLLMENT_CALL = "Error_In_Submit_Enrollment_call";
	public static final String ERROR_IN_ADD_ALERT_PREFERENCE = "Error_In_ADD_Alert_Preference_In_Submit_Enrollment";
	public static final String ERROR_ENROLLMENT_CONFIRMATION_EMAIL = "Enrollment_Confirmation_Email_Fail";
	
	// Enrollment Module DB Calls
	public static final String CALL_NAME_ADD_ALERT_PREFERENCES = "PREPAY_PROC_ADD_ALERT_PREFERENCE";
	public static final String CALL_NAME_SAVE_NCB_DETAILS = "PROC_ADD_NON_COMMODITY_BILLING";
	
	public static final String OE_SIGNUP_DTO = "OE_SIGNUP_DTO";
	public static final String OFFER_LANDING_DTO = "OFFER_LANDING_DTO";
	
	public static final Boolean BOOLEAN_TRUE = true;
	public static final Boolean BOOLEAN_FALSE = false;
	
	public static final String TDSP_CD_SHARYLAND_UTILITIES = "03109";
	public static final String ESID_STATUS_DE_ENERGIZED = "De-Energized";
	
	public static final String PROMODATEEXP = "PROMODATEEXP";
	public static final String TDSPPROMONOTMATCH = "TDSPPROMONOTMATCH";
	public static final String PROMOSTDOFFER = "PROMOSTDOFFER";
	public static final String PROMOISVALID = "PROMOISVALID";
	
	public static final String RHS_SINGLE_FAMILY = "SF";
	public static final String SHRHOLD = "SHRHOLD"; 
	public static final String CALL_NAME_SUBMIT_ENROLLMENT = "SUBMIT_ENROLLMENT";

	public static final String SERVICE_START_DATE = "SERVICE_START_DATE";
 	public static final String DESCRIPTION = "DESCRIPTION";
 	public static final String REP_CD = "REP_CD";
 	
 	public static final String ACTION_CODE_INVALID ="Action code is invalid. Try either CE (Credit Card Token), AE (Bank Account Token), PE (DL Token), or SE (SSN Token).";
 	
	public static final String APP_CONST_PROPERTIES_FILE = "/properties/appConstants.properties";
	
	/** The PAPERLESS flag. */
	public static final String PAPER_FLAG_OFF = "O";
	
	public static final String RESULT_DESCRIPTION_BP_MATCH_UNRESOLVED = "RESULT_DESCRIPTION_BP_MATCH_UNRESOLVED";
	
	
	public static final String ERROR_CODE_BPMATCH = "BPMATCH";
		
	// constants for esid profile call - Mayank Mishra
	public static final String FWD_SLASH = "/";
	public static final char SEMI_COLON = ';';
	public static final String MESSAGE_CODE_BUSINESS_METER = "BUSINESS_METER";
	public static final String MESSAGE_CODE_SWITCH_HOLD = "SWITCH_HOLD";
	public static final String MESSAGE_CODE_NOTIFY_SWITCH_HOLD = "NOTIFY_SWITCH_HOLD";
	public static final String MESSAGE_CODE_ESID_ACTIVE = "ESID_ACTIVE";
	public static final String MESSAGE_CODE_TDSP_MISMATCH = "TDSP_MISMATCH";
	public static final String MESSAGE_CODE_INVALID_DOB = "INVALID_DOB";
	public static final String MESSAGE_CODE_TECHNICAL_ERROR = "TECHNICAL_ERROR";
	public static final String MESSAGE_CODE_DUPLICATE_SUBMISSION = "DUPLICATE_SUBMISSION";
	
	
	public static final String MESSAGE_CODE_MESID = "MESSAGE_CODE_MESID";
	public static final String MESSAGE_CODE_NRESID = "MESSAGE_CODE_NRESID";
	public static final String MESSAGE_CODE_NESID = "MESSAGE_CODE_NESID";
	public static final String MESSAGE_CODE_CREDIT_CHECK_EMPTY_MVI_DATE = "CREDIT_CHECK_EMPTY_MVI_DATE";
	public static final String MESSAGE_CODE_TEXT_DUPLICATE_SUBMISSION = "DUPLICATE_SUBMISSION";
	public static final String MESSAGE_TEXT_BUSINESS_METER = "Business meter message would go here";
	public static final String MESSAGE_TEXT_SWITCH_HOLD = "Switch hold message would go here";
	public static final String MESSAGE_TEXT_ESID_ACTIVE = "Esid Active message would go here";
	public static final String MESSAGE_TEXT_TDSP_MISMATCH = "Tdsp mismatch message would go here";
	public static final String TRANSACTION_TYPE_SWITCH = "SWI";
	public static final String TRANSACTION_TYPE_MOVE_IN = "MVI";
	public static final String SWITCH_HOLD_STATUS_ON = "ON";
	public static final String STATUS_CODE_CONTINUE = "00";
	public static final String STATUS_CODE_STOP = "01";
	public static final String STATUS_CODE_ASK = "02";
	public static final int ZIPCODE_SIZE = 5;
	public static final String RESI = "RESIDENTIAL";
	public static final String STATUS_ACTIVE = "ACTIVE";
	public static final int PUSH_2 = 2;
	public static final int PUSH_4 = 4;
	public static final int PUSH_7 = 7;
	public static final int PUSH_9 = 9;
	
	public static final String ENV_UPDATE_CRM_ASYNC_DELAY_MS="env.updatecrm.async.delay.ms";
	public static final String METHOD_UPDATE_CONTACT_ASYNC="updateContactInfoWithAsyncDelay";
	public static final String METHOD_PREPAY_ALERT_TO_CRM="updateAlertPreferences";
	
	public static final String LOCALE_LANGUAGE_CODE_PARAM = "languageCode";
	public static final String LOCALE_LANGUAGE_CODE_EN_US = "en_US";
	public static final String LOCALE_LANGUAGE_CODE_ES_US = "es_US";
	
	//Jasveen
	public static final String PERSON_AFFILIATE_PERSON_ID = "personId";
	public static final String PERSON_AFFILIATE_RETRY_COUNT = "retryCount";
	public static final String POSID_FAIL_MAX="POSID_FAIL_MAX";
	public static final String POSID_FAIL_MAX_MSG_TXT="msg_posid_max";
	public static final String POSID_FAIL_MSG_TXT="msg_posid_fail";
	public static final String BP_MATCH_PAST_BALANCE_MSG_TXT="msg_bpMatch_past_balance";
	public static final String BP_MATCH_CURRENT_CUSTOMER_MSG_TXT="msg_bpMatch_current_customer";
	public static final String BP_MATCH_PAST_SERVICE_HISTORY_MSG_TXT="msg_bpMatch_past_service_history";
	public static final String TOKEN_SERVER_DOWN_MSG_TXT="msg_token_server_down";
	public static final String POSID_FAIL="POSID_FAIL";
	public static final String PAST_BALANCE="PAST_BALANCE";
	public static final String POSID_PASTDUE="POSID_PASTDUE";
	public static final String POSID_PASTSERVICE="POSID_PASTSERVICE";
	public static final String CURRENT_CUSTOMER="CURRENT_CUSTOMER";
	public static final String TOKEN_SERVER_DOWN="TOKEN_SERVER_DOWN";
	public static final String PAST_SERVICE_HISTORY="PAST_SERVICE_HISTORY";
	public static final String ACTION_CODE_SSN_ACTION="SE";
	public static final String ACTION_CODE_DL_ACTION="PE";
	public static final String POSID_FLAG_YES="Y";
	public static final String POSID_FLAG_NO="N";
	public static final String X_VALUE="X";
	public static final String O_VALUE="O";
	public static final String OE_DOMAIN_END_POINT_URL_JNDI_NAME="ws.endpointURL.oeDomain";
	public static final String EMAIL_ID="EMAIL";
	public static final String US="US";
	public static final String PHONE_REGEX="\\d{10}";
	public static final String EMAIL_REGEX="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String SSN_REGEX="\\d{9}";
	public static final String TDL_REGEX="\\d{10}";
	public static final String LOCALE_LANGUAGE_CODE_E = "E";
	public static final String LOCALE_LANGUAGE_CODE_S = "S";
	public static final String INSUFFICIENT_INPUTS="INSUFFICIENT INPUTS";
	// Jenith
	public static final String APP_KEY_CCS_TDSP_TO_WEB_TDSP_PREFIX = "ccs.tdsp.web.equivalent.";
	
	public static enum ADDRESS_MATCH {
		COMPLETE_MATCH, PARTIAL_MATCH, NO_MATCH, NOT_VALID_ADDRESS, INVALID_APT, MISSING_APT, SYS_ERROR
	};
	
	public static enum ADDRESS_MATCH_MESSAGE {
		COMPLETE_MATCH, PARTIAL_MATCH, NO_MATCH, UNVERIFIED_ADDRESS, INCORRECT_UNIT, UNIT_NEEDED
	};
	
	public static final String CCSERR = "CCSERR";
	
	public static String BILLING_ADDRESS_ERROR_MESSAGE = "Billing address can either have Street address or PO Box, not both";
	public static String BILLING_ADDRESS_EMPTY_ERROR_MESSAGE = "Billing address should have atleast Street address or PO Box, not blank";
	
	public static final String DELIMITER_SEMI_COLON = ";";
	
	public static final String WEB_PREFIX_OA_RELIANT = "WEBOA";
	public static final String WEB_PREFIX_OA_GME = "WEBGMOA";
	public static final String WEB_PREFIX_OA_PW = "WEBPWOA";
	public static final String WEB_PREFIX_OA_CE = "WEBCEOA";
	public static final String WEB_PREFIX_OA_EE = "WEBEEOA";
	
	public static final String BRAND_ID_PW = "PW";
	public static final String BRAND_ID_CE = "CE";
	public static final String UNDERSCORE = "_";
	
	public static final String ADDRESS_VALIDATION_UNVERIFIED_ADDRESS_MSG = "ADDRESS_VALIDATION_UNVERIFIED_ADDRESS";
	public static final String ADDRESS_VALIDATION_UNIT_NEEDED_MSG = "ADDRESS_VALIDATION_UNIT_NEEDED";
	public static final String ADDRESS_VALIDATION_INCORRECT_UNIT_MSG = "ADDRESS_VALIDATION_INCORRECT_UNIT";
	public static final String SUBMIT_ENROLLMENT_TECHNICAL_ERROR_MSG = "SUBMIT_ENROLLMENT_TECHNICAL_ERROR";
	public static final String POSID_BPMATCH = "POSID_BPMATCH";
	public static final String RECENT_CALL_MADE_POSID="POSID_CALL"; 
	public static final String RECENT_CALL_MADE_BP_MATCH="BP_MATCH_CALL";
	public static final String ESID_CALENDAR_DATES = "ESID_CALENDAR_DATES";
	public static final String CREDIT_CHECK = "CREDIT_CHECK";
	public static final String SUBMIT_ENROLLMENT = "SUBMIT_ENROLLMENT";
	public static final String SUBMIT_BANK_DEPOSIT_PAYMENT = "SUBMIT_BANK_DEPOSIT_PAYMENT";
	public static final String SUBMIT_CC_DEPOSIT_PAYMENT = "SUBMIT_CC_DEPOSIT_PAYMENT";
	public static final String DEPOSIT_CODE_FULL = "FULL";
	public static final String PAYMENT_FAIL = "PAYMENT_FAIL";
	public static final String PAYMENT_PASS = "PAYMENT_PASS";
	public static final String DEPOSIT_PAID = "PAID";
    public static final String DEPOSIT_FAIL = "FAIL";
	public static final String YES = "YES";
	public static final String HOLD = "HOLD";
	public static final String NOTICE = "NOTICE";
	public static final String RELEASE = "RELEASE";
	public static final String HISTORICAL_DATE = "01012000";
	public static final String CREDIT_ZERO = "0000";
	public static final String EQ_INFO = "EQ_INFO";
	public static final String TU_INFO = "TU_INFO";
	public static final String EQ_NAME = "EQ_NAME";
	public static final String TU_NAME = "TU_NAME";
	public static final String EQ = "EQ";
	public static final String TU = "TU";
	public static final String DEFAULT_INFO="DEFAULT_INFO";
	

	
	//Start GME Prepay OAM
	public static final String MKT_EMAIL="MKT_EMAIL";
	//End GME Prepay OAM

	public static final String CONSTRIANT_ATTR_MESSAGE_CODE = "messageCode";
	public static final String CONSTRIANT_ATTR_MESSAGE_CODE_TEXT = "messageCodeText";

	public static final String MSG_KEY_URL_NEW_CONF_FORM_RELIANT = "url.new.occupant.confirmation.form.reliant";
	public static final String MSG_KEY_URL_NEW_CONF_FORM_GME = "url.new.occupant.confirmation.form.gme";
	public static final String MSG_KEY_URL_NEW_CONF_FORM_CIRRO = "url.new.occupant.confirmation.form.cirro";
	public static final String MSG_KEY_URL_NEW_CONF_FORM_PENNYWISE = "url.new.occupant.confirmation.form.pennywise";
	
	public static final String PLAN_TYPE_FIXED = "Fixed";
	public static final String PLAN_TYPE_VARIABLE = "Variable";
	public static final String AVG_PRICE_500_KEY = "EFL_BR500";
	public static final String AVG_PRICE_1000_KEY = "EFL_BR1000";
	public static final String AVG_PRICE_2000_KEY = "EFL_BR2000";
	public static final String S_UNBUNDLE = "S_UNBUNDLE";
	public static final String S_UNBUNDLE2 = "S_UNBNDLE2";
	public static final String CONSERVATION_CATEGORY = "CONSERVATION";
	public static final String LPP_CAP="LPP_CAP";	
	public static final String S_CUSTCHR2="S_CUSTCHR2";
	public static final String CATEGORY_TWW="TWW";
	public static final String EFL_1R0500 = "EFL_1R0500";
	public static final String EFL_1R1000 = "EFL_1R1000";
	public static final String EFL_1R2000 = "EFL_1R2000";
	public static final String E_ENRGPB_P="E_ENRGPB_P";
	public static final String S_GME_UNB = "S_GME_UNB";
	public static final String TDSP_CHRG1="TDSP_CHRG1";
	public static final String TDSP_CHRG2="TDSP_CHRG2";
	public static final String AREA_NOT_SERVICED = "AREA_NOT_SERVICED";
	public static final String AREA_NOT_SERVICED_TEXT = "area.not.serviced.error.message";
	public static final String PROMO_INVALID="PROMO_INVALID";
	public static final String PROMO_INVALID_TEXT = "invalid.promocode.error.message";
	public static final String MSG_CCSERR_8_GET_PROMO_OFFERS = "MSG_CCSERR_8_GET_PROMO_OFFERS";
	public static final String MSG_CCSERR_E_GET_PROMO_OFFERS = "MSG_CCSERR_E_GET_PROMO_OFFERS";
	public static final String CONST_FILES = "/files/";
	public static final String CONST_DOT_PDF = ".pdf";
	public static final String CONSERVATION_ENERGY_CHARGE= "conservation.energycharge";
	public static final String NOT_CONSERVATION_ENERGY_CHARGE="not.conservation.energycharge";
	public static final String BASE_CHARGE_PER_MONTH ="base.charge.per.month";
	public static final String USAGE_CHARGE_PER_MONTH = "usage.charge.per.month";
	public static final String TDSP_CHARGE_TEXT="tdsp.charge.text";
	public static final String SERVICED_TDSP_CODES = "serviced.tdsp.codes";
	
	public static final String GME_SUBMIT_AMB_EN_US= "GME.SUBMIT.AMB.CONFIRM.EN_US";
	public static final String GME_SUBMIT_AMB_ES_US ="GME.SUBMIT.AMB.CONFIRM.ES_US";
	public static final String GME_SUBMIT_RETRO_AMB_EN_US= "GME.SUBMIT.RETRO.AMB.CONFIRM.EN_US";
	public static final String GME_SUBMIT_RETRO_AMB_ES_US ="GME.SUBMIT.RETRO.AMB.CONFIRM.ES_US";
	
	public static final String DATE_SUBMITTED ="dateSubmitted";
	public static final String AMB_AMOUNT ="ambAmount";
	
	@Deprecated
	public static enum VALIDATION_CONSTRAINTS {
		SIZE
	};
	
	public static enum PRIVACY_DATA_PARAMETERS {
		ssn, tdl, cvvNumber, password, strNum
	};
	
	public static enum ESID_CALENDAR_REQ_TYPE {
		ESID_ONLY, FULL, CALENDAR_ONLY
	};
	
	public static final String MASK_CHAR = "****";
	
	public static enum LOG_EXCLUDE_RESPONSE_METHODS {
		getOffers
	};
	
	//pREPAY TEMPERATURE REST SERVICE
	public static final String USAGE_HISTORY_REST_PREPAY_TEMPDATA = "/prepay/temperatureData";
	
	public String MSG_EXCP_ERROR_CODE    = "MSG_EXCEPTION_ERROR_CODE";
	public String MSG_USR_NOT_FOUND="User Not Found";
	
	//nnp
	public static final String NNP_SUBJECT = "";
	public static final String NNP_DELIVERY_METHOD_VAL_EMAIL_EN = "Receive my contract related notices by email only (Paperless)";
	public static final String NNP_DELIVERY_METHOD_VAL_EMAIL_ES = "Recibir mis avisos relacionados con el contrato solo por correo electr??nico (sin papel)";
	public static final String NNP_DELIVERY_METHOD_VAL_MAIL_EN ="Receive my contract related notices by mail only";
	public static final String NNP_DELIVERY_METHOD_VAL_MAIL_ES ="Recibir mis avisos relacionados con el contrato solo por correo";
	
	
	
	/* START EMAIL CHANGES */
	public static final String EBILL_SUBJECT = "";
	public static final String EBILL_EXTERNAL_ID_EN = "EBILL.CONFR.CBOC.EN_US";
	public static final String EBILL_EXTERNAL_ID_ES = "EBILL.CONFR.CBOC.ES_US";
	public static final String EBILL_TEMPLATE_ID = "XSLT";
	public static final String EBILL_HTML_TEMPLATE_ID = "HTML";
	
	public static final String GME_EBILL_EXTERNAL_ID_EN = "EBILL.CONFR.GME.EN_US";
	public static final String GME_EBILL_EXTERNAL_ID_ES = "EBILL.CONFR.GME.ES_US";
	public static final String GME_EBILL_TEMPLATE_ID = "HTML";
	
	public static final String RELIANT_COMPANY_CODE = "0121";
	public static final String GME_COMPANY_CODE = "0271";
	
	public static final String RELIANT_BRAND_NAME = "RE";
	
	public static final String COLON = ":";
	
	public static final String EBILL_CA_RAW = "ca-raw";
	public static final String EBILL_NAME_ON_ACCOUNT = "name-on-account";
	public static final String EBILL_CHECK_DIGIT = "check-digit";
	public static final String EBILL_CONFIRM_NUMBER = "confirmation-number";
	public static final String EBILL_DELIVERY_METHOD = "bill-delivery-method";
	
	public static final String EBILL_DELIVERY_METHOD_VAL_EMAIL_EN = "Receive my bills by email only (Paperless Billing)";
	public static final String EBILL_DELIVERY_METHOD_VAL_EMAIL_ES = "Recibe mis cuentas solamente por email (facturaci????n electr????nica)";
	
	public static final String EBILL_DELIVERY_METHOD_VAL_MAIL_EN = "Receive my bills by mail only";
	public static final String EBILL_DELIVERY_METHOD_VAL_MAIL_ES = "Recibir facturas por correo";
	
	public static final String EBILL_DELIVERY_METHOD_VAL_BOTH_EN = "Receive my bills by email and mail";
	public static final String EBILL_DELIVERY_METHOD_VAL_BOTH_ES = "Recibir facturas por email Y por correo";
	
	public static final String MAIL = "mail";
	public static final String EMAIL = "email";
	public static final String BOTH = "both";
	
	
	//ccs Ebill email GME BCC EMAIL
	public static final String EBILL_GME_BCC_EMAIL = "ebill.gme.bcc.email";
	/* END EMAIL CHANGES */
	
	public static final String ERR_CODE_53="53";
	public static final String ERR_CODE_53_DESC="Invalid Routing Number";
	
	//Rest url for personalize api bbachin1
	public static final String BASE_PERSONALIZE_REST_URL = "rest.url.base.personalize";
	public static final String BASE_SDL_REST_URL = "rest.url.base.sdl";
	public static final String PROP_DEFAULT_WS_TIMEOUT_IN_SEC = "rest.url.timeout";
	public static final String GET_OFFER_REST_URL = "/messagecontent";
	public static final String READ_COMPONENT_BY_ITEMID = "/component/itemid";
	public static final String READ_COMPONENT_BY_ITEMIDS = "/component/itemids";
	public static final String READ_MESSAGE_CONTENT = "/gme/messagecontent";
	public static final String USER_UPDATE_PREF_REST_URL = "/userpreference/update";
	public static final String SUBMIT_PAY_EMAIL_FLAG = "submit.pay.email.flag";

	/* START Community Solar CHANGES */
	public static final String SALESFORCE_TIME_OUT_IN_SEC = "salesforce.timeout.in.sec";
	public static final String SALESFORCE_TOKEN_SERVICE = "ws.rest.salesforce.token.service.url";
	public static final String SALESFORCE_GET_ACCOUNT_SERVICE = "ws.rest.salesforce.get.account.service.url";
	public static final String SALESFORCE_ACCOUNT_REG_SERVICE = "ws.rest.salesforce.account.reg.service.url";
	public static final String SALESFORCE_DASHBOARD_SERVICE ="ws.rest.salesforce.dashboard.service.url";
	public static final String SALESFORCE_UPDATE_ACCOUNT_SERVICE ="ws.rest.salesforce.updateaccount.service.url";
	public static final String SALESFORCE_GET_AGREEMENT_PDF_SERVICE ="ws.rest.salesforce.get.agreement.pdf.service.url";
	public static final String CCS_GET_CSLR_PROFILE = "ws.rest.ccs.get.cslr.profile.url";
	public static final String CCS_UPDATE_CSLR_BILLING_ADDR_AT_BP_LEVEL = "ws.rest.ccs.update.cslr.billing.address.at.bp.level.url";
	public static final String GRANNT_TYPE = "grant_type";
	public static final String CLIENT_ID = "client_id";
	public static final String CLIENT_SECRET = "client_secret";
	public static final String PSD = "password";
	public static final String PWRD = "password"; //change this to check
	public static final String USER_NAME = "username";
	public static final String SALESFORCE_LEASE_ID = "LeaseId";
	public static final String SALESFORCE_UTILITY_ACC_NO = "UtilityAccountNumber";
	public static final String SALESFORCE_AUTHORIZATION = "Authorization";
	public static final String SALESFORCE_OAUTH = "OAuth ";

	
	public static final String SALESFORCE_GRANT_TYPE = "salesforce.grant.type";
	public static final String SALESFORCE_CLIENT_ID = "salesforce.client.id";
	public static final String SALESFORCE_CLIENT_SECRET = "salesforce.client.secret";
	public static final String SALESFORCE_USER_NAME = "salesforce.username";
	public static final String SALESFORCE_USER_PSD = "salesforce.user.password";
	
	public static final String BASIC_AUTH_HEADER_NAME = "Authorization";
	public static final String AUTH_TYPE_BASIC = "Basic";
	
	public static final String SALESFORCE_INVALID_SESSION_ID ="Session expired or invalid";
	public static final String RESULT_CODE_SALESFORCE_SESSION_ERROR_CODE="01";
	public static final String RESULT_CODE_SALESFORCE_ERROR_DESCRIPTION_01= "Session expired or invalid";
	
	public static final String MSG_ERR_UPDATE_USER = "MSG_ERR_UPDATE_USER";
	public static final String MSG_ERR_DELETE_USER = "MSG_ERR_DELETE_USER";
	
	public static final String LDAP_ORG_KEY_CSLR =  "nrgcommunitysolr.com";
	public static final String LDAP_PROVIDER_URL_CSLR = "LDAP_PROVIDER_URL_CSLR";
	public static final String LDAP_SECURITY_AUTHENTICATION_CSLR = "LDAP_SECURITY_AUTHENTICATION_CSLR";
	public static final String LDAP_ADMIN_UID_CSLR= "LDAP_ADMIN_UID_CSLR";
	public static final String LDAP_SECURITY_CREDENTIALS_CSLR = "LDAP_SECURITY_CREDENTIALS_CSLR";
	 public static final String SF_SYNC_ERROR_CODE01="01";
	 public static final String SF_SYNC_ERROR_CODE01_DESC="User does not Exist";
	 public static final String SF_SYNC_ERROR_CODE02="02";
	 public static final String SF_SYNC_ERROR_CODE02_DESC="New Email Address already used by another user";
	 
	 public static final String SF_SYNC_ERROR_CODE03="03";
	 public static final String SF_SYNC_ERROR_CODE03_DESC="LDAP Update failed";
	 
	 public static final String SF_SYNC_ERROR_CODE04="04";
	 public static final String SF_SYNC_ERROR_CODE04_DESC="Web Database Update failed";
	 
	 public static final String SF_SYNC_ERROR_CODE05="05";
	 public static final String SF_SYNC_ERROR_CODE05_DESC="Processing Error";
	 
	 public static final String SF_SYNC_ERROR_CODE_DESC="Update Successful";
	 public static final String META_CONTENT_DISPOSITION = "Content-Disposition";
	 public static final String META_FILENAME = "filename=";
	 public static final String CONST_AGREEMENT_PDF_DEFAULT_NAME = "nrgCommSlrAgreementDoc";
	 public static final String CONST_DOCID = "docid";
	 public static final String CCS_UPD_BILL_ADDR_SUCCESS_RESPONSE_MSG = "BP address updated successfully";
	 public static final String DEFAULT_BIG_DECIMAL_VALUE="0.00";
	 public static final String SF_RESP_MSG_SUCCESS="Success";
	 public static final String SF_RESP_NO_MATCH="No Match";
	 public static final String PROP_CS_DEFAULT_WS_TIMEOUT_IN_SEC="cs.rest.url.timeout";
		
	/* END Community Solar changes CHANGES */
	
	public static final String CIRRO_AUTO_PAY_UPDATE_EXTERNAL_ID_EN="CE.AUTO.PAY.UPDATE.EN_US";
	public static final String CUSTOMER_NAME="CUSTOMER_NAME";

	
	public static final String CIRRO_AVG_BILLING_CONF_EXTERNAL_ID_EN="CE.AVG.BILLING.CONF.EN_US";	
	public static final String CIRRO_EMAIL_CHANGE_EXTERNAL_ID_EN="CE.EMAIL.UPDATE.EN_US";	
	public static final String CIRRO_BILLING_OPTION_CHANGE_EN ="CE.BILLING.OPTION.CHANGE.EN_US";
	
	public static final String CIRRO_PAYMENT_OPTIONS_UPDATE_EN="CE.PAYMENT.OPTIONS.UPDATE.EN_US";
	public static final String CIRRO_CREDIT_DEBIT_UPDATE_EN="CE.ADD.CREDIT.DEBIT.UPDATE.EN_US";
	public static final String CIRRO_ADD_BANK_CONF_EXTERNAL_ID="CE.ADD.BANK.CONF.EN_US";
	
	public static final String CIRRO_NEW_SERVICE_ADDRESS_ADD_EXTERNAL_ID="CE.ADD.NEW.SERVICE.ADDED.EN_US";
	
	public static final String CIRRO_PSD_CHANGE_EXTERNAL_ID="CE.PASSWORD.CHANGE.EN_US";
	
	// Start : Validate for Power Genius Online Affiliates by KB
	public static final String POWER_GENIUS_ENROLL_CONF_EN="POWER.GENIUS.ENROLL.CONF.EN_US";	
	// End : Validate for Power Genius Online Affiliates by KB
	
	public static final String COURTESY_CREDIT_SUCCESS_CODE   = "01";
	public static final String COURTESY_CREDIT_FAILURE_CODE   = "99";
	public static final String FAILURE_CODE_01   = "01";
	public static final String COURTESY_CREDIT_RES_CODE_DESC   = "Failure";
	public static final String MESSAGE_CREDIT_FREEZE= "CREDIT_FREEZE";
	public static final String MESSAGE_CREDIT_FRAUD= "CREDIT_FRAUD";
	

	//Digital and AutoPay Discounts for Online Affiliates
	public static final String AVG_PRICE_EFL_500_KEY = "EFL_EP500";
	public static final String AVG_PRICE_EFL_1000_KEY = "EFL_EP1000";
	public static final String AVG_PRICE_EFL_2000_KEY = "EFL_EP2000";
	public static final String EFL_EPD_KEY = "EFL_EPD";
	public static final String EFL_AP_KEY = "EFL_AP";

	//Start - Alt Channels -- US14171 | Pratyush -- 11/13/2018
	public static final String E_USAGE_CR = "E_USAGE_CR"; 
	public static final String MAX_THRESHOLD = "E_MAX_KWH"; 
	public static final String MIN_THRESHOLD = "E_MIN_KWH"; 
	//End - Alt Channels -- US14171
	
	public static final String CCS_READ_RELIANT_CUSTOMER_STATUS_URL = "ws.rest.ccs.read.reliant.customer.status.url";
	public static final String CCS_CHECK_SECURITY_ELIGIBILITY_URL = "ws.rest.ccs.check.security.eligibility.url";

	
	public static final String TEXT_FREEZE_CREDIT_CHECK = "text.reason.for.freeze.credit.check";
	public static final String TEXT_FRAUD_CREDIT_CHECK = "text.reason.for.fraud.credit.check";

	public static final String CCS_TOS_ELIGIBLE_NONELIGIBLE_PRODUCTS_URL = "ws.rest.ccs.tos.eligible.noneligible.products.url";
	public static final String CCS_TOS_SUBMIT_ELIGIBLE_PRODUCTS_URL = "ws.rest.ccs.tos.submit.eligible.products.url";

	

	public static final String UCC_DATA = "UCC_DATA";
	public static final String SECURITY_METHOD_UCC = "UCC";
	public static final String MESSAGE_TEXT_TRACKING_NUMBER_NOT_UPDATED = "Tracking details are not updated";
	public static final String MESSAGE_TEXT_TRACKING_NUMBER_NOT_FOUND = "Tracking details are not found";
	public static final String MESSAGE_TEXT_PERSON_NOT_UPDATED = "Person details are not updated";
	public static final String MESSAGE_TEXT_PERSON_NOT_FOUND = "Person details are not found";
	public static final String MESSAGE_CODE_INFO_MISMATCH = "INFO_MISMATCH";
	public static final String MESSAGE_TEXT_INFO_MISMATCH  = "First Name, Last Name and / or SSN did not match the information??previously??used for??this enrollment.";
	
	public static final String CCS_UPDATE_ETF_FLAG_TO_CRM_URL = "ws.rest.ccs.update.etfflag.crm.url";
	
	// Start | US18891 | MBAR: Sprint 23 -GIACT REST IMPL : validate bank details  | Jyothi | 5/31/2019
	public static final String CCS_BANK_GIACT_CALL_URL="ws.rest.ccs.bank.giact.call.url";
	// End | US18891 | MBAR: Sprint 23 -GIACT REST IMPL : validate bank details  | Jyothi | 5/31/2019
	
	public static final String CONSTANT_S ="S";


	public static final String CCS_GET_AGENT_DETAILS_URL = "ws.rest.ccs.get.agent.details.url";
	
	// Start | US16458 | MBAR: Sprint 14 - GME Admin tool password reset issue fixes. | Jenith | 2/5/2019  	
	public static final String PROFILE_RESPONSE_KEY = "profileResponse";
	// End | US16458 | MBAR: Sprint 14 - GME Admin tool password reset issue fixes. | Jenith | 2/5/2019  	

	//Mobile Content API Data
	public static final String language_EN = "EN";
	public static final String ERRORS = "ERRORS";
		
	 public static final String USERNAME_VALID="Username is valid";
	 public static final String ACCOUNT_INFO_VALID="Account information valid";
	 public static final String USER_ALREADY_EXISTS_DESC = "User Already Exists";
	 public static final String RESULT_CODE_INVALID_INPUT_PARAMETERS="Invalid Input Parameters";
	 public static final String RESULT_CODE_INVALID_INPUT_PARAMETERS_DESC="Invalid Input Parameters - Please verify";
	 public static final String RESULT_CODE_BAD_REQUEST = "Bad Request";

	 //Refresh Token 
	 public static final int REFRESH_TOKEN_LENGTH = 250;
	 
	 public static final String GME_PSD_CHANGE_EN_US="GME.PASSWORD.CHANGE.EN_US";
	 public static final String GME_PSD_CHANGE_ES_US="GME.PASSWORD.CHANGE.ES_US";
	 public static final String GME_USERNAME_EN_US = "GME.USERNAME.EN_US";
	 public static final String GME_USERNAME_ES_US = "GME.USERNAME.ES_US";
	 
	 public static final String GME_MYACCOUNT_PSD_RESET_URL ="GME.MYACCOUNT.PASSWORD.RESET.URL";
	 public static final String GME_MYACCOUNT_LOGIN_URL="GME.MYACCOUNT.LOGIN.URL";
	 
	 //Added for GME Mobile
	 
	 public static final String RESULT_CODE_SIX="6";
	 public static final String RESULT_DESCRIPTION_EMAIL_NOTFOUND="Email Address validation failed";
	 public static final String RESULT_CODE_SEVEN="7";
	 public static final String RESULT_DESCRIPTION_INVALID_ZIP_CODE="Invalid Zip Code";

		
	public static final String CCS_GOOGLE_PRODUCT_SET_URL ="ws.rest.ccs.google.product.set.url";
	 
	 //Constants for Payment status
	 public static final String  PAYMENT_PENDING_STATUS = "Pending";
	 public static final String PAYMENT_PAID_STATUS = "Paid";
	 
	 //Constants for Weekly Usage Details
	 public static final String CURRENT_DAY_INDICATOR_N = "N";   
	 public static final String DAILY_HOUR_INDICATOR_D = "D";
	 
	 public static final String DAILY_COST_DUMMY_DATA = "0:00";
	 public static final String DAILY_HIGH_TEMP_DUMMY_DATA = "0";
	 public static final String DAILY_LOW_TEMP_DUMMY_DATA = "0";
	 public static final String DAIY_USAGE_DUMMY_DATA = "0:00";
	
	 public static final String ACTION_CODE_DETOKNIZED_INVALID ="Action code is invalid. Try either  PE (DL Token), or SE (SSN Token) or SD (SSN DE-Token) or PD (DL DE-Token).";
	 

	 public static final String PY ="PY";
	 public static final String SY ="SY";
	
	 public static final String AVG_BILL_FLAG_Y = "Y";
	 public static final String AVG_BILL_FLAG_N = "N";
	 public static final String AVG_BILL_FLAG_YES = "Yes";
	 public static final String AVG_BILL_FLAG_NO = "No";
	 
	 //enviornmental impact for GME Mobile app
	 
	 public static final String TREES_PER_SECOND = "trees.per.second";
	 public static final String CO2_PER_SECOND = "co2.per.second";
	 public static final String TOATL_BASELINE = "total.baseline";
	 public static final String TOTAL_BASELINE_TREE = "total.baseline.tree";
	 public static final String CONTENT_SERVER_ENDPOINT_URL = ".content.server.endpoint.url";
	 public static final String CONTENT_REST_SERVER_URL = "content.rest.server.url";
	 public static final String PUB = ".pub.id_";
	 public static final String TEMPLATE_DESKTOP = ".tempalte.id_desktop";
	 public static final String TEMPLATE_MOBILE = ".tempalte.id_mobile";
	 public static final String RE_TAXONOMY_ID = "re.taxonomy.id";
	 public static final String PW_TAXONOMY_ID = "pw.taxonomy.id";
	 public static final String CE_TAXONOMY_ID = "ce.taxonomy.id";
	 public static final String GM_TAXONOMY_ID = "gm.taxonomy.id";
	 public static final String PROD_OFFER_SCHEMA_ID = ".prodoffer.schema.id";
	 public static final String PROD_BONUS_SCHEMA_ID = ".prodbonus.schema.id";
	 public static final String CONTENT_TITLE_JSON_END_POINT_URL = "endPointUri";
	 public static final String CONTENT_TITLE_JSON_PUBLICATION_ID = "publicationId";
	 public static final String CONTENT_TITLE_JSON_BRAND_ID = "brandId";
	 public static final String CONTENT_TITLE_JSON_TRANSACTION_ID = "transactionId";
	 public static final String CONTENT_TITLE_TEMPLATE_ID = "templateId";
	 public static final String CONTENT_TITLE_PROD_BONUS_SCHEMA_ID = "prodBonusSchemaId";
	 public static final String CONTENT_TITLE_PROD_OFFER_SCHEMA_ID = "prodOfferSchemaId";
	 public static final String CONTENT_OFFER_MESSAGE_KEY = ".offer.messagekey.name";
	 public static final String CONTENT_TITLE_TAXONOMY_ID = "taxonomyId";
	 public static final String CONTENT_TITLE_MESSAGEKEYS = "messageKeys";
	 public static final String CONTENT_TITLE_MESSAGEKEY_LIST = "messageKeyList";
	 public static final String CONTET_ERROR_MESSAGEKEY = "No Mapping for offer Code in the content server or Something went wrong!!!!!";
	 public static final String ERROR_CONTENT_CACHE ="ErrorContent";
	 public static final String STR_SYMBOL_EIPHEN = "-";
	 public static final String ERROR_CONTENT_DEFAULT = "Sorry! Something went wrong. Please try again";
	 
	 public static final String PAY_ACCOUNT_ALREADY_EXISTS = "Pay Account already Exists";
	 public static final String NICKNAME_ALREADY_EXISTS = "Nickname already Exists";
	 public static final String NO_ACCOUNT_UPDATE = "No Payment Account Update";
	 public static final String ACCOUNT_ALREADY_ENROLLED = "Account already enrolled";
	 
	 public static final String GME_BASE_URL = "gm.base.url";
	 public static final String GME_PROD_BASE_URL = "gm.prod.base.url";
	 
	 
	 public static final String IMG_URL = "<img src=\"{0}\"/>";
	 public static final String INVALID_REQUEST = "Please provide valid request for {0}";
	 public static final String CONTRACT_ID ="contractId";
	 public static final String LANGUAGE_CODE ="languageCode";

	 public static final String ZONE_ID_IN ="zoneId";
	 public static final String PRODUCT_CONTENT_ONE ="100% Wind";
	 public static final String PRODUCT_CONTENT_TWO ="15% Wind";
	 public static final String PRODUCT_CONTENT_THREE ="95% Wind, 5% Solar";
	 public static final String PRODUCT_CONTENT_FOUR ="100% Solar";
	
	 public static enum ENUM_PRODUCT_CONTENT {
		ONE(1, PRODUCT_CONTENT_ONE), TWO(2, PRODUCT_CONTENT_TWO), THREE(3, PRODUCT_CONTENT_THREE),
		FOUR(4, PRODUCT_CONTENT_FOUR);

		public final String productContent;
		public final Integer productKey;

		ENUM_PRODUCT_CONTENT(Integer productKey, String productContent) {
			this.productContent = productContent;
			this.productKey = productKey;
		}
		
		public String getProductContent() {
			return productContent;
		}

		public int getProductKey() {
			return productKey;
		}
	 }
	
	 public static final String EMPTY_DATE = "00000000";
	 public static final String EMPTY_OFFER = "00000000";
	 public static final String PROACTIVE_FLAG = "P";
	 public static final String CUMTREES = "CUMTREES";
	 public static final String NO_CONTRACT = "No Contracts found for the Given Account";
	public static final String METHOD_SYNCHRONIZE_CSS_CONTACT_LOG = "updateContactLog";
	public static final String CONTACT_LOG_CC_CONTACT_CLASS = "1400";
	public static final String CONTACT_LOG_BANK_CONTACT_CLASS = "0400";
	public static final String CONTACT_LOG_SWAP_CONTACT_CLASS = "2000";
	public static final String CONTACT_LOG_SUN_CULB_TX_DRIVER_CONTACT_CLASS = "3550";
	public static final String CONTACT_LOG_TREE_FREE_BILL_CONTACT_CLASS = "2400";
	public static final String CONTACT_LOG_CREATE_USER_CONTACT_CLASS = "2400";
	public static final String CONTACT_LOG_ENROLL_CONTACT_ACTIVITY = "0700";
	public static final String CONTACT_LOG_DEENROLL_CONTACT_ACTIVITY = "0750";
	public static final String CONTACT_LOG_SWAP_CONTACT_ACTIVITY = "0092";
	public static final String CONTACT_LOG_SUN_CULB_TX_DRIVER_OPT_IN_CONTACT_ACTIVITY = "0401";
	public static final String CONTACT_LOG_SUN_CULB_TX_DRIVER_OPT_OUT_CONTACT_ACTIVITY = "0325";
	public static final String CONTACT_LOG_TREE_FREE_BILL_ENROLL_CONTACT_ACTIVITY = "1260";
	public static final String CONTACT_LOG_TREE_FREE_BILL_DEENROLL_CONTACT_ACTIVITY = "1560";
	public static final String CONTACT_LOG_CREATE_USER_CONTACT_ACTIVITY = "1800";
	public static final String SUN_CLUB = "S001";
	public static final String TX_DRIVER = "G001";
	public static final String SUN_CLUB_TX_DRIVER_ENROLL_ACTION = "2";
	public static final String SUN_CLUB_TX_DRIVER_DE_ENROLL_ACTION = "3";
	public static final String CONTACT_LOG_COMMIT_FLAG = "X";
	public static final String CONTACT_LOG_CONTACT_TYPE = "018";
	public static final String CONTACT_LOG_DIVISION = "10";
	public static final String BASE64HTML = "BASE64HTML"; 
    public static final String TXN_DATE = "TXN_DATE"; 
    public static final String E_PAYMENT_AMOUNT = "PAYMENT_AMOUNT"; 
    public static final String E_CONTR_ACCT_ID = "CONTR_ACCT_ID";
    public static final String E_CHECK_DIGIT = "CHECK_DIGIT"; 
    public static final String SCH_PAYMENT_DATE = "SCH_PAYMENT_DATE"; 
    public static final String E_CARD_NUMBER = "CARD_NUMBER"; 
    public static final String E_EXP_DATE = "EXP_DATE"; 
    public static final String E_CONFIRM_NUM = "CONFIRM_NUM";
    public static final String SCHEDULE_CC_PAYMENT_GME = "SCHEDULE_CC_PAYMENT_GME"; 
    public static final String SCHEDULE_CC_PAYMENT_GME_SUB = "Scheduled Payment Acknowledgment";
    public static final String MOBILE = "Mobile";
    public static final String AUTOPAY_G_FLAG = "G";

    public static final String DOLLAR_SIGN = "$";

   //START : OE :Sprint62 :US21019 :Kdeshmu1
    public static final String DSI_AGENT_ID = "270519";
    public static final String MESSAGE_CODE_INVALID_AGENT_ID = "INVALID_AGENT_ID";
    
    public static final String REST_API_USER_NAME="rest.api.user.name";
	public static final String REST_API_PSD="rest.api.password";
	public static final String REST_API_AUTHORIZATION="Authorization";
	public static final String REST_IOT_ENROLLMENT_REPORT_DATA_SUBMIT_URL = "rest.ws.endpointURL.iot.enrollment.report.data.submit";
	public static final String IOT_ENROLLMENT_REPORT_DATA_SUBMIT_REST_TIME_OUT_IN_SEC = "iot.enrollment.report.data.submit.rest.timeout.in.sec";
	public static final String SPACE = " ";
	public static final String FLAG_E = "E";
	public static final String S_VALUE = "S";
	public static final String UPDATE_AGENT_SUCCESS_FLAG = "Success";
	public static final String UPDATE_AGENT_ERROR_FLAG = "CCSError";
	//END : OE :Sprint62 :US21019 :Kdeshmu1
	
	//Start || US23692: Affiliate API - Hard Stop Blocked ESIDs || atiwari || 15/12/2019
	public static final String ESID_RESTRICTION_TEXT_MESSAGE = "esid.restriction.text.message";
	public static final String BP_RESTRICTION_TEXT_MESSAGE = "bp.restriction.text.message";
	public static final String ESID_RESTRICTION = "ESID_RESTRICTION";
	public static final String BP_RESTRICTION = "BP_RESTRICTION";
	public static final String BP_RESTRICT = "BPRESTRICT";
	//End || US23692: Affiliate API - Hard Stop Blocked ESIDs || atiwari || 15/12/2019
	
	//WSE Email Archive
	public static final String WSE_REPORT_WEEKS_FROM_ENV_PROP = "wse.report.weeks";
	public static final int DEFAULT_WSE_REPORT_WEEKS = 12;
	public static final String ERR_NO_DATA = "No data found for the CA and Company code";
	public static final String ERR_DB = "Error occured while fetching data from database";
	public static final String ERR_UNKNOWN = "An application error occured.  Please check NRG REST logs.";
	//Cancel Payment Email
	public static final String CANCEL_PAYMENT_GME_TEMPLATE_EN_US = "CANCEL_PAYMENT_GME.EN_US";
	public static final String CANCEL_PAYMENT_GME_TEMPLATE_ES_US = "CANCEL_PAYMENT_GME.ES_US";
	public static final String CANCEL_PAYMENT_TXN_DATE = "TXN_DATE";
	public static final String CANCEL_PAYMENT_PAYMENT_DATE = "PAYMENT_DATE";
	public static final String CANCEL_PAYMENT_PAYMENT_AMOUNT = "PAYMENT_AMOUNT";
	public static final String CANCEL_PAYMENT_CONFIRM_NUM = "CONFIRM_NUM";
	public static final String CANCEL_PAYMENT_CONTR_ACCT_ID = "CONTR_ACCT_ID";
	public static final String CANCEL_PAYMENT_CHECK_DIGIT = "CHECK_DIGIT";

	public static final String CHANNEL_TYPE_AA="AA";
    public static final String DRL_STATE_TX = "TX";
    public static final String POSID_BLANK_DATE = "0000-00-00";
    public static final String CALLER_WEB="WEB";
    public static final String MSG_KEY_KBA_QUESTION_LIST_EMPTY="msg.key.kba.question.list.empty";
          
    
    public static final String DELIMITER = "_";
    public static final String POSIDHOLD  = "POSIDHOLD";
    public static final String CALL_NAME_KBA_SUBMIT = "KBASUBMIT";
    public static final String OE_SPRING_CALL_LOG_STATEMENT="{}: OE Spring JDBC Response Call Name : {} : {} Time in ms  ";
    
    public static final String DELIMETER_COMMA_REGEX=",$";
    
    public static final String DEFAULT_GROUP_NAME = "togglz.default.groupName";
    public static final String TOGGLZ_REST_BASE_URL = "ws.rest.togglz.base.url";
    public static final String TOGGLZ_FEATURE_NEW_POSID_CALL="salesapi.newposid.flag";
	public static final String TOGGLZ_FEATURE_CMS_OFFER_DATA="salesapi.cmsofferdata.flag";
	public static final String TOGGLZ_FEATURE_DEFAULT_REACTIVE_OFFER="salesapi.dros.flag";
	public static final String TOGGLZ_FEATURE_HOLD_CMS_DATA="salesapi.hold.content";
 
    //***************** SALES APIs *************************
    //Legacy Sales APIs - Currently being used by Affiliates - To be deprecated
    public static final String API_LEGACY_GET_AFFILIATE_OFFERS = "getAffiliateOffers";
    public static final String API_LEGACY_PERFORM_POSID_AND_BPMATCH= "performPosidAndBpMatch";
    public static final String API_LEGACY_GET_ESID_AND_CALENDAR_DATES= "getESIDAndCalendarDates";
    public static final String API_LEGACY_PERFORM_CREDIT_CHECK= "performCreditCheck";
    public static final String API_LEGACY_SUBMIT_UCC_DATA= "submitUCCData";
    public static final String API_LEGACY_SUBMIT_ENROLLMENT= "submitEnrollment";
    
    //New Sales APIs - To be used by all channels and brands
    public static final String SALES_API_BASE_PATH = "sales";
    public static final String OE_RESOURCE_API_BASE_PATH = "oeResource";
    public static final String API_OFFERS = "offers";
    public static final String API_IDENTITY= "identity";
    public static final String API_AVAILABLE_DATES= "available-dates";
    public static final String API_CHECK_CREDIT= "check-credit";
    public static final String API_CREDIT_DATA= "credit-data";
    public static final String API_SUBMIT_ENROLLMENT= "enroll";
    public static final String API_GET_KBA_QUESTIONS = "kba-questions";
    public static final String API_KBA_RESULT = "kba-result";
    public static final String API_TOKEN = "token";
    public static final String API_PROSPECT = "prospect";
    public static final String API_ESID = "esid";
    public static final String API_RECHECK_CREDIT= "recheck-credit";
    public static final String API_CLEANUP_ADDRESS="cleanup-address";
    public static final String API_GET_HOLD="holds";
    public static final String API_ESID_RESIDENTIAL = "esid/residential";
   
    
    //Submit new KBA Answers API
    public static final String RETRY_NOT_ALLOWED = "RETRY_NOT_ALLOWED";
    public static final String RETRY_NOT_ALLOWED_TXT = "Retry of KBA Answers is not allowed on same transactionKey. Please rerun the getKBAQuestions API to generate a new transactionKey";
    public static final String NO_PROSPECT_MATCH_FOUND = "NO_PROSPECT_MATCH_FOUND";
    public static final String NO_PROSPECT_MATCH_FOUND_TEXT = "msg_prospect_match_not_found";
    public static final String HTTP_BAD_REQUEST  = "BAD_REQUEST";
    public static final String CHANNEL_WEB = "WEB";
    public static final String CHANNEL_AA = "AA";
    public static final String CHANNEL_AFF = "AFF";
    
    public static final String DEFAULT_PRICE_VALUE= "0.00";
    public static final String EFL_OFFPK1 = "EFL_OFFPK1";
	public static final String EFL_ONPK = "EFL_ONPK";
    
	public static final String OFFER_CATEGORY_NESTCAM = "NESTCAM";
	public static final String OFFER_CATEGORY_NESTCONS = "NESTCONS";
	public static final String OFFER_CATEGORY_NESTCAMCONS = "NESTCAMCONS";
	public static final String OFFER_CATEGORY_NESTTRUFREEWKND = "NESTTRUFREEWKND";
	public static final String OFFER_CATEGORY_NESTCAMTRUFREEWKND = "NESTCAMTRUFREEWKND";	
	public static final String OFFER_CATEGORY_NESTTSTATE = "NESTTSTATE";
	public static final String OFFER_CATEGORY_NESTTSTATECONS = "NESTTSTATECONS";
	public static final String OFFER_CATEGORY_NESTSTATETRUFREEWKND = "NESTSTATETRUFREEWKND";
	public static final String OFFER_CATEGORY_CONSAPT = "CONSAPT";
	public static final String OFFER_CATEGORY_TRULY_FREE_WEEKENDS = "TRULYFREEWKND";
	public static final String OFFER_CATEGORY_CONS600="CONS600";
	public static final String OFFER_CATEGORY_SEASONAL = "SEASONAL";
	public static final String OFFER_CATEGORY_3TIER_500 = "3TIER-500";
	public static final String OFFER_CATEGORY_3TIER_1350= "3TIER-1350";
	public static final String OFFER_CATEGORY_EV_PLAN = "EVPLAN";
	public static final String OFFER_CATEGORY_TRUELY_FREE_NIGHTS= "TRULYFREENIGHTS";
	public static final String OFFER_CATEGORY_TRUELY_FREE_DAYS= "TRULYFREEDAYS";
	
	public static final String KBA_OE="kba-oe";
	public static final String DEFAULT_PRICE_VALUE_ZERO_DOT_ZERO= "0.0";
	
	public static final String SYMBOL_DOLLAR = "$";
	public static final String SYMBOL_CENTS = "??";

	public static final String CREDFREEZE = "CREDFREEZE";
	public static final String CCSD = "CCSD";
	public static final String TRANSACTIONTYPE_N = "N";
	public static final String PBSD = "PBSD";
	public static final String HOLD_DNP  = "DNP";
	   
	
	public static final String TOGGLZ_FEATURE_ALLOW_POSID_SUBMISSION="salesapi.allow.posidhold.submission";
	public static final String POSID_HOLD_MSG_TXT="msg_posid_hold";
	public static final String POSID_PASTDUE_MSG_TXT="msg_posid_pastdue";
	public static final String POSID_PASTSERVICE_MSG_TXT="msg_posid_past_history";
	public static final String MESSAGE_CODE_NO_MATCH_FOUND = "NO_MATCH_FOUND";
	
	public static final String PROSPECT_PREAPPROVAL_FLAG_PASS= "P";
	public static final String PROSPECT_MISMATCH= "PROSPECT_MISMATCH";
	public static final String PROSPECT_MISMATCH_TEXT = "msg_prospect_mismatch";
	public static final String CCS_UPDATE_ERROR="SAP Update failed";
	public static final String PAYMENTEXTENSION_BYPASS_ELIGIBLE_FLAG ="paymentextension.bypass.eligible.flag";

//***************** GMD APP APIs *************************    
	public static final String DPP_BYPASS_ELIGIBLE_FLAG ="dpp.bypass.eligible.flag";
	public static final String DPP_DEFAULT_FLAG = "dpp.default.flag";
	public static final String DPP_NO_OF_INST = "dpp.no.of.installments";
	

	public static final String TRANSACTIONTYPE_S = "S";

	public static final String RESIDENTIAL = "RESIDENTIAL";
	public static final String ACTIVE = "ACTIVE";

	public static final String POSID  = "POSID";
	
	public static final String ERROR_CD_LIST_SPLIT_PATTERN = "\\|";
	public static final String ERROR_CD_ENROLLMENT_NOT_ALLOWED = "ENROLLMENT_NOT_ALLOWED";
	
	public static final String KBA_SET_POSIDHOLD  = "KBA sets POSID hold";
    public static final String KBA_LIFT_POSIDHOLD  = "KBA lifts POSID hold";
    
    public static final String DEPOSITHOLD  = "DEPOSITHOLD";
    
	public static final String CONST_TRACKING_ID= "trackingId";
	
	public static final String MESSAGE_CODE_ENROLLMENT_ALREADY_REQUESTED = "ENROLLMENT_ALREADY_REQUESTED";
	
	public static final String CCS_STATUS_CODE_NO_HOLDS="01";
	
	public static final String MESSAGE_CODE_NO_HOLD="NO_HOLD_ON_ACCOUNT";
	public static final String MESSAGE_TEXT_NO_HOLD="No Hold for this Account";
	
	public static final String MESSAGE_CODE_CCS_HOLD_FAILURE="HOLD_LOOKUP_ERROR";
	public static final String MESSAGE_CODE_TEXT_HOLD_FAILURE="Failure to obtain hold information";
	
	public static final String HTTP_INTERNAL_SERVER_ERROR  = "INTERNAL_SERVER_ERROR";
	 
	 public static final String ENROLLMENT_NOT_ALLOWED = "ENROLLMENT_NOT_ALLOWED";
	 public static final String ENROLLMENT_NOT_ALLOWED_TEXT = "Enrollment cannot be accepted since the Credit check API failed and customer's credit requirements could not be assessed";
	 
	 public static final String PROSPECT_BP_USED = "PROSPECT_BP_USED";
	 public static final String SOLD_TO_BP_USED = "SOLD_TO_BP_USED";
	

	 public static final String DUE_AMOUNT = "dueAmount";
	 public static final String EXTENSION_DATE = "extensionDate";
	 
	 public static final String GME_PAYMTXTN_EMAIL_EN_US ="GME.PAYMTXTN.EMAIL.EN_US"; 
	 public static final String GME_PAYMTXTN_EMAIL_ES_US ="GME.PAYMTXTN.EMAIL.ES_US"; 
	 
	 public static final String DP_PAYMTXTN_EMAIL_EN_US ="DP.PAYMTXTN.EMAIL.EN_US"; 
	 public static final String DP_PAYMTXTN_EMAIL_ES_US ="DP.PAYMTXTN.EMAIL.ES_US"; 	
	 public static final String DP_ACCOUNT_NAME="ACCOUNT_NAME";
	 public static final String DP_ACCOUNT_NUMBER ="CA_NUMBER";
	 public static final String DP_DUE_AMOUNT = "PAST_DUEAMOUNT";
	 public static final String DP_EXTENSION_DATE = "EXTENSION_DATE";
//***************** GMD APP APIs *************************    
   public static final String API_GET_GMD_STATEMENT_DATA = "getGMDStatementData";
   public static final String API_GET_GMD_PRICE_DATA = "getGMDPrice";
   public static final String API_CREATE_GMD_MOVE_OUT = "createMoveOut";
   public static final String API_GET_GMD_PRICE_SPIKE_ALERT_DATA = "getGmdPriceSpikeAlertData";
   public static final String API_GET_GMD_MD_STMT_DATA = "getGmdMdStmtData";
   public static final String API_GET_GMD_LMP_PRICE_SPIKE = "getGmdLmpPriceSpike";
   public static final String API_GET_GMD_HOURHEAD_SPIKE = "getGmdHourHeadSpike";
   
   public static final String API_KBA_MATRIX_UPDATE = "protected/kbaMatriUpdate";
   
   public static final String GMD_STATEMENT_ENDPOINT_URL_JNDINAME = "CCS_GMD_STMT";
   public static final String GMD_PRICET_ENDPOINT_URL_JNDINAME = "CCS_PRICE_STMT";
   
   public static final String GMD_ENERGY_CHARGE = "Energy Charge";
   public static final String GMD_ENERGY_TRUE_UP = "Energy True-up";
   public static final String GMD_USAGE_TRUE_UP = "Usage True-up";
   
   public static final String SOLAR_RECS = "Solar Recs";
   public static final String FIXED_RATE_THIRD_PARTY_CHRG = "Fixed ERCOT Charges";
   public static final String ANCILLARY_SERVICES = "Ancillary Services";
   public static final String ELECTRICITY_USAGE = "Electricity Usage";
   
   public static final String QUALITY_OTHER_CREDIT = "Quality Other Credit";
   
   
   public static final String TDSP_DELIVERY_CHARGES = "TDSP Delivery Charges";
   public static final String SALES_TAX = "Sales Tax";
   
   public static final String GROSS_RECP_TAX = "Gross Receipt Tax";
   
   public static final String PUC_FEE = "PUC Fee";
   
   
   public static final String GMD_MEMBERSHIP = "Membership Fee";
   public static final String  TAXES_FEES = "Taxes & Fees";
  
   public static final String ENERGY_CHARGES = "Energy Charges";
   
   public static final String INDEFINITE_END_DATE = "12/31/9999";
   
   public static final String GMD_PRICE_IRW_DATE = "gmd_price_current_date"; 
   
	//Content Service metadata
	public static final String CUSTOM_METADATA= "CustomMetas%28%29?&$filter=(PublicationId%20eq%20";
	
	public static final String KEY_NAME = "%20and%20keyName%20eq%20%27offerCodesLookup%27%20and%20(";
	
	public static final String STRING_VALUE = "StringValue%20eq%20%27";
	
	public static final String JSON_FORMAT_COMPONENT_PRESENTATION = "))&$expand=Component/ComponentPresentations&$format=json";
	
	public static final String OR = "%20OR%20";
	public static final String ERROR = "error";
	public static final String TEFLF = "TEFLF";
	public static final String DISC = "DISC";
	 
	public static final String XOOM_COMPANY_CODE = "0586";
	public static final String XOOM_BRAND_NAME = "XM";
	public static final String XOOM_EBILL_EXTERNAL_ID_EN = "XOOM.EBILL.UPDATE.CONFIRMATION.EN_US";
	public static final String XOOM_EBILL_EXTERNAL_ID_ES = "XOOM.EBILL.UPDATE.CONFIRMATION.ES_US";
	public static final String XOOM_EBILL_TEMPLATE_ID = "HTML";
	
	public static final String XOOM_EBILL_CA = "CA_NUMBER";
	public static final String XOOM_EBILL_CHECK_DIGIT = "CHECK_DIGIT";
	public static final String XOOM_EBILL_NAME_ON_ACCOUNT = "CA_NAME";
	public static final String XOOM_EBILL_DELIVERY_METHOD = "BILL_DELIVERY_METHOD";
	public static final String XOOM_EBILL_DELIVERY_LOCATION = "DELIVERY_LOCATION";
	
	
	
	public static final String XOOM_NNP_EXTERNAL_ID_EN = "XOOM.NNP.UPDATE.CONFIRMATION.EN_US";
	public static final String XOOM_NNP_EXTERNAL_ID_ES = "XOOM.NNP.UPDATE.CONFIRMATION.ES_US";
	public static final String XOOM_NNP_TEMPLATE_ID = "HTML";
	
	public static final String XOOM_NNP_CA = "CA_NUMBER";
	public static final String XOOM_NNP_CHECK_DIGIT = "CHECK_DIGIT";
	public static final String XOOM_NNP_NAME_ON_ACCOUNT = "CA_NAME";
	public static final String XOOM_NNP_DELIVERY_METHOD = "BILL_DELIVERY_METHOD";
	public static final String XOOM_NNP_DELIVERY_LOCATION = "DELIVERY_LOCATION";

	public static final String TOGGLZ_ENROLLMENT_FRAUDULENT_CHECK = "salesapi.enrollment.fraudulent.check";
	
	public static final String CONST_GUID= "guId";
	
	public static final String AFFILIATE_ID_COMPAREPOWER="232793";
	public static final String AFFILIATE_ID_DSI="270519";
	public static final String TOGGLZ_ENROLLMENT_MADATORY_CALL_CHECK = "salesapi.enrollment.mandatorycall.check";	
	public static final String CONST_USE_MOCK_DATA = "usemockdata";
	public static final String CONST_IS_MOCK_RESPONSE = "isMockResponse";
	
	public static final String APPLICATION_SWAP_AREA = "SWAP";
	
	 public static final String API_ESID_VALIDATION = "esidValidation";
	public static final String DATE_FRMT_DB ="dd-MMM-YY";	
	public static final String DUP_PAYMENT_CHECK = "duplicate.payment.check";
	
	public static final String PLAN_TYPE_INDEXED = "Indexed";
	public static final String TOU = "TOU";
	public static final String IND = "IND";
	public static final String RATETYPE_VARIABLE = "00FIX";

	public static final String ERCOT_CHECK_BY_ADDRESS_IOT_CALL_URL="ws.rest.iot.ercot.check.by.address.url";
	public static final String ERCOT_STREET="street";
	public static final String ERCOT_CITY="city";
	public static final String ERCOT_STATE="state";
	public static final String ERCOT_ZIP="zip";
	public static final String ERCOT_COMPANY="companyName";	
	public static final String NRG_API_IOT_ERCOT_ESID_CHECK_BYADDRESS="iot/ercot/getByAddress";
	
	public static final String API_OFFER_DETAILS = "offer-details";
	public static final String CHANNEL_WEB_CLOUD = "WEBCLOUD";
	
	public static final String API_TDSP = "tdsp";
	public static final String VALIDATE_POSID_WITH_KBA_REST = "validatePOSIdwithKBA";

	public static final String TEMPLATE_REPORTSUITE = "template.url.parameter.reportsuite";
	public static final String BRAND_NAME ="GME";
	public static final String PARAMETER_VARIABLE_REPORTSUITE ="ReportSuite";
	public static final String PARAMETER_VARIABLE_BRAND ="BRAND";
	public static final String PARAMETER_VARIABLE_CANUMBER ="CANumber";
	public static final String PARAMETER_VARIABLE_COMPANYCODE ="CompanyCode";
	public static final String PARAMETER_VARIABLE_MSGID ="MsgId";
	public static final String PARAMETER_VARIABLE_ACTIONDATE ="ActionDate";
	public static final String PARAMETER_VARIABLE_MESSAGETYPE ="MessageType";
	public static final String PARAMETER_VARIABLE_MESSAGECAT ="MessageCat";
	public static final String PARAMETER_VARIABLE_MESSAGE ="Message";
	public static final String PARAMETER_VARIABLE_MESSAGESTATUS ="MsgStatus";
	public static final String PARAMETER_VARIABLE_LANGUAGE ="Language";
	public static final String PARAMETER_VARIABLE_OSTYPE = "OSType";
	public static final String PARAMETER_VARIABLE_CONTRACTID = "ContractId";
	public static final String PARAMETER_VARIABLE_BPNUMBER ="BPNumber";
	public static final String PARAMETER_VARIABLE_ERRORMESSAGE ="ErrorMessage";
	public static final String CURRENT_DATE_FMT="MMddyyyy";
	public static final String IOT_POST_URL = "iot.post.url";
	public static final String HEADER_CONTENT_TYPE_KEY = "content-type";
	public static final String HEADER_CONTENT_TYPE_VALUE_JSON = "application/json";
	public static final String HEADER_CONTENT_TYPE_VALUE_URL_ENCODED = "application/x-www-form-urlencoded";
	public static final String HTTP_METHOD_POST = "POST";
	public static final String HTTP_METHOD_GET = "GET";
	public static final String ADOBE_MESSAGE_TYPE = "Submit";
	public static final String ADOBE_MESSAGE_FUNCTION = "SwapSubmit";
	public static final String SWAP_SUBMIT_SUCESS = "SWAP_SUBMITTED";
	public static final String ADOBE_ANALYTIC_TEMPLATE_URL ="adobe.analytic.template.url";
	public static final String TEMPLATE_URL_QUERY_LIST_PARAMETER_ONE ="template.url.query.list.parameter.one";
	public static final String TEMPLATE_URL_QUERY_LIST_PARAMETER_TWO ="template.url.query.list.parameter.two";
	public static final String SWAP_SUBMIT_FAIL = "SWAP_SUBMIT_FAIL";
	public static final String GET_PLAN_OFFER_FAIL = "GET_PLAN_OFFER_FAIL";
	public static final String GET_PLAN_OFFER = "GET_PLAN_OFFER";
	public static final String ADOBE_MESSAGE_PLAN_TYPE = "PLAN_OFFER";
	public static final String PLAN_OFFER_FUNCTION = "SEARCH_PLAN";
	public static final String PLAN_OFFER_MESSAGE_TYPE = "OPEN";
	public static final String PARAMETER_VARIABLE_MSGINSTANCE = "msgInstance";
	public static final String SALES_API_ESID_VALIDATION = "esidValidation"; 

	public static final String SMARTCODE_URL_SUB_STR = "/defl/";
	public static final String EFL_URL_ERROR = "EFL_URL_ERROR";
	public static final String DOCID_URL_SUB_STR = "/files/";
	public static final String BUSINESS = "BUSINESS";	
	public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
	public static final String VEHICLE_ID = "VEHICLE_ID";
	public static final String FB = "FB";
	public static final String DEPOSIT = "DEPOSIT";
	public static final String TWOZEROAFTERPOINT = ".00";
	public static final String ZEROPLUSTWOZEROAFTERPOINT = "0.00";
	public static final String CUSTOMER_PAY = "CUSTOMER_PAY";
	public static final String DEPOSIT_OPTION_CONTENT_SNIPPET="oe_creditcheck_deposit_pay_alt_tlp_tctxt";
	

	public static final String DATE_PATTERN_YYYY_MM_DD = "^\\d{4}-\\d{2}-\\d{2}$";
	
	
    public static final String ERROR_01="01";
    public static final String ERROR_02="02";
    public static final String ERROR_03="03";
    
	public static final String GIACT_ERROR_01="GIACT_ERROR_01";
	public static final String GIACT_ERROR_02="GIACT_ERROR_02";
	public static final String GIACT_ERROR_03="GIACT_ERROR_03";
	public static final String BANK_AUTOPAY_INVALID_BANK_ACCOUNT = "MSG_CCSERR_2_INVALID_BNK";
		
	public static final String FLAG_OTHER = "Other";
	public static final String SALES_API_SUBMIT_UCC_DATA= "submitUCCData";
	
	public static final String OESIGNUPVO="OESIGNUPVO";
	public static final String PROMOCHARITYOUTDATA="promoCharityOutData[";
	public static final String CHARITYDETAILSVO="charityDetailsVO[";
	public static final String START="Start:";
	public static final String OEBO_EXCEPTION_LOG="OEBO.getESIDInfo() Exception occurred when invoking getESIDInfo";
	public static final String PROCESSING_UPDATESERVICELOCATION="Processing updateServiceLocation ...";
	public static final String TRACKING_NOT_NULL="trackingId must not be null.";
	public static final String LOGGER_REQUEST="request = ";
	public static final String LOGGER_PERFORMPOSIDBPMATCH="inside performPosidAndBpMatch:: affiliate Id : ";
	public static final String TOKENRESPONSE="tokenResponse";
	public static final String TOKENTDL="tokenTdl";
	public static final String TOKENSSN="tokenSSN";
	public static final String FINISH_PROCESS_UPDATESERVLOCATION="Finished processing updateServiceLocation, errorCode = ";
	public static final String PROMOCODE_NOT_EMPTY="promoCode may not be Empty";
	public static final String COMPANYCODE="Company code ";
}
