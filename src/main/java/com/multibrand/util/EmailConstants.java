package com.multibrand.util;

public interface EmailConstants {

	/**
	 * Email Common Constants
	 */
	public static final String RELIANT_COMPANY_CODE = "0121";
	public static final String RELIANT_EMAIL_SPANISH = "es_US";
	public static final String RELIANT_EMAIL_ENGLISH = "en_US";
	public static final String RETRO_FLAG="x";
	public static final String RELIANT_ENGLISH = "English";
	public static final String RELIANT_SPANISH = "Spanish";
	public static final String PERSONAL_ATTRIBUTE_DELIMITER =  "*";

	/**
	 * Email Subjects... subject will be overridden if it's already defined in template
	 */

	public static final String UPDATE_BILLING_ADDRESS_CONFIRMATION_SUBJECT = "Reliant User Name Assistance";
	public static final String UPDATE_PERSONAL_INFO_CONFIRMATION_SUBJECT = "Update Personal Information";
	public static final String CANCEL_PAYMENT_CONFIRM_SUBJECT = "Cancel Payment Confirmation";
	public static final String PAYMENT_CONFIRM_SUBJECT_EN = "Bill Pay Confirmation";
	public static final String AUTOMATIC_PAYMENT_WITH_CC_CONFIRM_SUBJECT_EN = "Set Up Auto Pay With Credit Card - Confirmation";
	public static final String DEENROLL_AUTOMATIC_PAYMENT_CONFIRM_SUBJECT_EN = "Reliant.com AutoPay Cancellation Request Confirmation";
	public static final String AUTOMATIC_PAYMENT_WITH_BANK_CONFIRM_SUBJECT_EN = "Reliant.com Automatic Bank Draft Confirmation";
	public static final String STORED_PAYMENT_ACCOUNT_WITH_BANK_CONFIRM_SUBJECT_EN = "Add Bank Account Confirmation";
	public static final String STORED_PAYMENT_ACCOUNT_WITH_CC_CONFIRM_SUBJECT_EN = "Add Payment Card Confirmation";
	public static final String REMOVE_BANK_ACCOUNT_CONFIRM_SUBJECT_EN = "Remove Bank Account Confirmation";
	public static final String REMOVE_CREDIT_CARD_CONFIRM_SUBJECT_EN = "Remove Credit Card Confirmation";
	public static final String WEEKLY_SUMMARY_EMAIL_ENROLL_CONFIRMATION_SUBJECT_EN = "e-Sense Services";
	public static final String WEEKLY_SUMMARY_EMAIL_ENROLL_CONFIRMATION_SUBJECT_ES = "Servicios de e-Sense";
	public static final String SUBMIT_PAYMENT_EXTENSION_CONFIRM_SUBJECT_EN = " Payment Extension";
	public static final String OAM_REGISTRATION_CONFIRM_SUBJECT_EN= "Welcome to reliant.com";
	public static final String REQUEST_DUPLICATE_BILL_CONFIRM_SUBJECT_EN="Request Duplicate Bill - Confirmation";
	public static final String SMB_OE_CONFIRM_CUSTOMER_SUBJECT_EN="Small Business Enrollment";
	public static final String SMB_OE_CONFIRM_COMPANY_SUBJECT_EN="Web Business Switch Enrollment_";
	public static final String SMB_OE_CONFIRM_COMPANY_SUBJECT_ES="Inscripciï¿½n en Lï¿½nea de Transferencia Comercial_";
	public static final String AVERAGE_BILLING_CONFIRMATION_SUBJECT_EN="Average Billing - Confirmation";
	public static final String AVERAGE_BILLING_BUSINESS_REQUEST_SUBJECT_EN = "Request Average Billing - CSG";
	public static final String AVERAGE_BILLING_BUSINESS_REQUEST_SUBJECT_ES = "Solicitar facturación media - CSG";
	public static final String AVERAGE_BILLING_BUSINESS_CONFIRMATION_SUBJECT_EN = "Request Average Billing - Confirmation";
	public static final String AVERAGE_BILLING_BUSINESS_CONFIRMATION_SUBJECT_ES = "Confirmación de la solicitud para la Facturación Promediada";

	public static final String ADD_ELECTRICITY_ACCOUNT_SUBJECT_EN = "Add Electricity Account - Confirmation";
	public static final String REMOVE_ELECTRICITY_ACCOUNT_SUBJECT_EN = "Remove Electricity Account - Confirmation";
	//CSAT SCHEDULE CCPAY GK 
	public static final String PAYMENT_SCHEDULE_CONFIRM_SUBJECT_EN = "Scheduled Payment Acknowledgment";

	/** Email Subject - Spanish */
	public static final String SUBMIT_PAYMENT_EXTENSION_CONFIRM_SUBJECT_ES = " Extensión de Pago";
	public static final String PAYMENT_CONFIRM_SUBJECT_ES = "Confirmación de pago de factura";
	public static final String AUTOMATIC_PAYMENT_WITH_CC_CONFIRM_SUBJECT_ES = "Se estableció Pago Automático con Tarjeta de Crédito - Confirmación";
	public static final String AUTOMATIC_PAYMENT_WITH_BANK_CONFIRM_SUBJECT_ES = "Confirmación de Giro Automático Bancario de Reliant.com";
	public static final String STORED_PAYMENT_ACCOUNT_WITH_BANK_CONFIRM_SUBJECT_ES = "Se agregó cuenta de banco Confirmación";
	public static final String STORED_PAYMENT_ACCOUNT_WITH_CC_CONFIRM_SUBJECT_ES = "Se agregó tarjeta de pago Confirmación";
	public static final String REMOVE_BANK_ACCOUNT_CONFIRM_SUBJECT_ES = "Se sacó cuenta de banco Confirmación";
	public static final String REMOVE_CREDIT_CARD_CONFIRM_SUBJECT_ES = "Confirmación de la eliminación de la tarjeta de pago";
	public static final String OAM_REGISTRATION_CONFIRM_SUBJECT_ES= "Bienvenido a reliant.com";
	public static final String REQUEST_DUPLICATE_BILL_CONFIRM_SUBJECT_ES="Solicitud de duplicado de factura - Confirmación";
	public static final String AVERAGE_BILLING_CONFIRMATION_SUBJECT_ES = "Confirmación de facturación promediada";
	public static final String ADD_ELECTRICITY_ACCOUNT_SUBJECT_ES = "Se agregó cuenta de electricidad - Confirmación";
	public static final String REMOVE_ELECTRICITY_ACCOUNT_SUBJECT_ES = "Se sacó cuenta de electricidad - Confirmación";
	public static final String DEENROLL_AUTOMATIC_PAYMENT_CONFIRM_SUBJECT_ES = "Confirmación de Solicitud de Cancelación de Reliant.com AutoPay";
	//CSAT SCHEDULE CCPAY GK 	
	public static final String PAYMENT_SCHEDULE_CONFIRM_SUBJECT_ES = "Reconocimiento de pago programado";
	

	/**
	 * Email Template Variabes
	 */
	// # Common
	public static final String CONTRACT_ACCOUNT_NUMBER = "contract-account-number";
	public static final String CONTRACT_ACCOUNT_NAME = "contract-account-name";
	public static final String CHECK_DIGIT = "check-digit";
	public static final String ACCOUNT_NAME ="name-on-account";
	public static final String CA_NUMBER_RAW = "ca-raw";
	public static final String CONFIRM_NUMBER = "confirmation-number";

	public static final String ADDRESS_STREET_NAME = "street-name";
	public static final String ADDRESS_STREET_NUMBER = "street-number";
	public static final String ADDRESS_PO_BOX = "po-box";
	public static final String ADDRESS_CITY = "city";
	public static final String ADDRESS_STATE = "state";
	public static final String ADDRESS_ZIP_CODE = "zip-code";
	public static final String ADDRESS_UNIT_NUMBER = "unit-number";

	// ## Profile
	public static final String PROFILE_EMAIL_ADDRESS = "email-address";
	public static final String PROFILE_USER_NAME = "user-name";
	public static final String PROFILE_NEW_USER_NAME = "new-user-name";
	public static final String PROFILE_GREETING_NAME = "greeting-name";
	public static final String PROFILE_TELEPHONE_NUMBER = "telephone-number";
	public static final String PROFILE_SEQ_QNS = "security-question-text";
	public static final String PROFILE_CONTACT_PREF_EMAIL = "email_contact_pref";
	public static final String PROFILE_CONTACT_PREF_MAIL = "mail_contact_pref";
	public static final String PROFILE_CONTACT_PREF_PHONE = "phone_contact_pref";
	public static final String PROFILE_MARKETING_PREF_PROD = "prodserv_mkt_pref";
	public static final String PROFILE_MARKETING_PREF_THIRDPARTY = "thirdparty_mkt_pref";
	public static final String PROFILE_MARKETING_PREF_EFFICIENCY = "efficency_mkt_pref";
	public static final String PROFILE_EMAIL_OPT_OUT_FLAG = "email-opt-out-text";
	public static final String PROFILE_UPDATED_ATTRIBUTE_FLAG = "changed-flag";
	

	// # Payment
	public static final String REQUEST_DATE = "request-date";
	public static final String CANCEL_PAYMENT_ACC_NAME = "name_on_payaccount";
    public static final String CANCEL_PAYMENT_PAYMENT_SUB_DATE = "payment_submission_date";
    public static final String CANCEL_PAYMENT_PAYMENT_AMOUNT = "payment_amount";
    public static final String CANCEL_PAYMENT_CONFIRM_NUMBER = "confirmation_number";
    public static final String CANCEL_PAYMENT_SCH_DATE  = "scheduled_payment_date";
    public static final String DUPLICATE_BILL_INVOICE_DATE="invoice-date";
	public static final String DUPLICATE_BILL_AMOUNT_DUE="amount-due";
	public static final String DUPLICATE_BILL_DATE_DUE="date-due";

    public static final String PAYMENT_CA_NAME = "contract-account-name";
	public static final String PAYMENT_CA_NUMBER = "contract-account-number";
	public static final String PAYMENT_CHECK_DIGIT = "check_digit";
	public static final String PAYMENT_ACC_TYPE = "pay-account-type";
	public static final String PAYMENT_CC_ACC_NUMBER = "pay-account-number";
	public static final String PAYMENT_PAYMENT_SUB_DATE = "payment_submission_date";
    public static final String PAYMENT_PAYMENT_AMOUNT = "payment_amount";
    public static final String PAYMENT_CONFIRM_NUMBER = "confirmation_number";
    public static final String PAYMENT_NICK_NAME = "pay_account_nickname";
    public static final String PAYMENT_CC_TYPE = "cc-institute-code";
    public static final String PAYMENT_CC_CA_NAME = "name-on-account";
    public static final String PAYMENT_CC_NUMBER = "cc-number";
    public static final String PAYMENT_CC_EXP_MONTH = "cc-expiration-month";
	public static final String PAYMENT_CC_EXP_YEAR = "cc-expiration-year";
	public static final String PAYMENT_CC_ZIP_CODE = "zip-code";
	public static final String PAYMENT_STORE_NICK_NAME = "pay-account-nickname";
	public static final String PAYMENT_STORE_CONFIRM_NUMBER = "confirmation-number";
	public static final String PAYMENT_STORE_BANK_ACC_NUMBER = "bank-account-number";
	public static final String PAYMENT_PAYMENT_OPTION = "PayOption ";	
	public static final String PAY_OPTION_DC = "DC";
	public static final String DISCOVER_CARD = "Discover Card";
	public static final String MASTER_CARD = "Mastercard";
	public static final String VISA_CARD = "Visa";
	public static final String MASTER_1 = "MasterCard";
	public static final String DISCOVER_1 = "Discover";
	public static final String VISA_1 = "Visa";
	
	

	public static final String PAYMENT_BANK_ACC_NUMBER = "bank_account_number";
	 public static final String PAYMENT_BANK_ROUTING_NUMBER = "bank-routing-number";

    public static final String AUTOMATIC_PAYMENT_BANK_CA_NUMBER = "ca-raw";
    public static final String AUTOMATIC_PAYMENT_BANK_CHECK_DIGIT = "contract-account-checkdigit";
    public static final String AUTOMATIC_PAYMENT_BANK_CA_NAME = "contract-account-name";
	public static final String AUTOMATIC_PAYMENT_BANK_ROUTING_NUMBER = "bank-routing-number";
	public static final String AUTOMATIC_PAYMENT_BANK_CONFIRM_NUMBER = "confirmation-number";
	public static final String AUTOMATIC_PAYMENT_BANK_ACC_NUMBER = "bank-account-number";


	public static final String AUTOMATIC_PAYMENT_CC_CONFIRM_NUMBER = "confirmation-number";
	public static final String AUTOMATIC_PAYMENT_CC_ACC_NUMBER = "ca-raw";
	public static final String AUTOMATIC_PAYMENT_CC_CHECK_DIGIT = "check-digit";
	public static final String AUTOMATIC_PAYMENT_CC_NAME_ON_CARD = "contract-account-name";
	public static final String AUTOMATIC_PAYMENT_CC_CA_NAME = "name-on-account";
	public static final String AUTOMATIC_PAYMENT_CC_TYPE = "cc-institute-code";
	public static final String AUTOMATIC_PAYMENT_CC_NUMBER = "cc-number";
	public static final String AUTOMATIC_PAYMENT_CC_EXP_MONTH = "cc-expiration-month";
	public static final String AUTOMATIC_PAYMENT_CC_EXP_YEAR = "cc-expiration-year";
	public static final String AUTOMATIC_PAYMENT_CC_ZIP_CODE = "zip-code";

	public static final String CONTRACTACCOUNTNAME = "ContractAccountName";
	public static final String CONTRACTACCOUNTNUMBER = "ContractAccountNumber";
	public static final String TXNTIMESTAMP = "TxnTimestamp";
	public static final String DEFERRALDATE = "DeferralDate";
	public static final String DISCONNECTAMOUNT = "DisconnectAmount";

	public static final String OAM_REGISTRATIN_USERNAME= "user-name";
	public static final String OAM_REGISTRATIN_CC_ACC_NUMBER = "ca-raw";
	public static final String OAM_REGISTRATIN_CC_CHECK_DIGIT = "check-digit";

	public static final String efl_link = "efl_link";
	public static final String AGREEMENT_TERM_LINK = "agreement_terms_link";
	public static final String CSTOMER_RIGHTS_LINK = "customer_rights_link";
	public static final String OUTDOOR_URL = "outdoorurl";
	public static final String DATE = "date";
	public static final String TIME = "time";
	public static final String AGREEMENT = "agreement";
	public static final String PRODUCT_NAME = "productName";
	public static final String AVG_PRICE = "avgPrice";
	public static final String ENERGY_CHARGEE = "energyCharge";
	public static final String BASECHARGE = "baseCharge";
	public static final String TDSPCHARGE = "tdspCharge";
	public static final String DEMANDCHARGE= "demandCharge";
	public static final String ADDITIONAL_PRI_URL= "additionalPriUrl";
	public static final String CONTRACTTERM= "contractTerm";
	public static final String CANCELLATION_FEE= "cancellationFee";
	public static final String BONUSDETAILS = "bonusDetail";
	public static final String ESIID1= "esid1";
	public static final String BUSINESS_NAME = "business_name";
	public static final String FED_TAX_ID = "fed_tax_id";
	public static final String BUSINESS_TYPE = "business_type";
	public static final String TOTAL_SERICE_ADDRESS= "total_service_addresses";
	public static final String FOOTAGE = "footage";
	public static final String CONTACT_FIRST_NAME = "contact_first_name";
	public static final String CONTACT_LAST_NAME = "contact_last_name";
	public static final String ADDRESS = "address";
	public static final String PHONE_NUM = "phone_num";
	public static final String ALT_PHONE_NUM = "altphone_num";
	public static final String FAX_NUM = "fax_num";
	public static final String EMAIL_ADDRESS = "email_address";
	public static final String PREF_LANGUAGE = "pref_language";
	public static final String CURRENT_ELECTRICITY_PROVIDER = "current_electricity_provider";
	public static final String CHECKBOX1 = "checkbox1";
	public static final String SAEMAILBODY = "saEmailBody";
	public static final String ESIID2 = "esid2";
	public static final String TXTOFFERCODE = "txtOfferCode";
	public static final String TXTPROMOCODE = "txtPromoCode";
	public static final String TDSPCODE = "tdsp_code";
	public static final String TXT_TDSP_NAME = "txtTdspName";
	public static final String GEO_ZIP = "geo_zip";
	public static final String PREF_CONT = "pref_contact";
	public static final String COMMENTS = "comments";
	public static final String GET_A_QUOTE_CONFIRM_SUBJECT = "Reliant Energy Business Services received your request";
	public static final String GET_A_QUOTE_CONFIRM_TO_CCRS_SUBJECT = "Request Customized Quote Information - Business Customer - ";
	public static final String POBOX="pobox";
	public static final String STREETNUMBER="streetNumber";
	public static final String STREETNAME = "streetName";
	public static final String UNITNUMBER ="unitnumber";
	public static final String ZIP_CODE = "zipCode";
	public static final String NAMEON_ACCOUNT ="nameonAccount";
	public static final String AVERAGE_BILLING_AMT ="average_billing_amt";
	public static final String SUBMISSION_DATE = "submissionDate";
	public static final String RETROFLAG = "retroFlag";
	public static final String CA_NUMBER = "contract_account_number";
    /**
	 * External Id's
	 */

	// For profile
	public static final String UPDATE_BILLING_ADDRESS_CONFIRMATION_EMAIL_EXTERNAL_ID_EN = "EBILL.CONFR.CBAC.EN_US";
	public static final String UPDATE_BILLING_ADDRESS_CONFIRMATION_EMAIL_EXTERNAL_ID_ES = "EBILL.CONFR.CBAC.ES_US";
	public static final String UPDATE_PERSONAL_INFO_CONFIRMATION_EMAIL_EXTERNAL_ID_EN = "EBILL.CONFR.UPIC.EN_US";
	public static final String UPDATE_PERSONAL_INFO_CONFIRMATION_EMAIL_EXTERNAL_ID_ES = "EBILL.CONFR.UPIC.ES_US";

	public static final String CANCEL_PAYMENT_EXTERNAL_ID = "EBILL.CONFR.CPC.EN_US";
    public static final String CANCEL_PAYMENT_SPANISH_EXTERNAL_ID = "EBILL.CONFR.CPC.ES_US";
    public static final String BILL_PAYMENT_EXTERNAL_ID = "EBILL.CONFR.BPCC";
    public static final String BILL_SCHEDULE_EXTERNAL_ID = "EBILL.CONFR.BP.SCC";
    public static final String AUTOMATIC_PAYMENT_WITH_CC_EXTERNAL_ID = "EBILL.CONFR.SUAPWCC";
    public static final String AUTOMATIC_PAYMENT_WITH_BANK_EXTERNAL_ID = "EBILL.CONFR.RSUAPWBA";
    public static final String DEENROLL_AUTOMATIC_PAYMENT_EXTERNAL_ID = "EBILL.CONFR.DEAP";
    public static final String ADD_BANK_ACCOUNT_EXTERNAL_ID = "EBILL.CONFR.ABAC";
    public static final String ADD_CREDIT_CARD_EXTERNAL_ID = "EBILL.CONFR.ACCC";
    public static final String REMOVE_BANK_ACCOUNT_EXTERNAL_ID = "EBILL.CONFR.RBAC";
    public static final String REMOVE_CREDIT_CARD_EXTERNAL_ID = "EBILL.CONFR.RCCC";
    public static final String WEEKLY_SUMMARY_CONFIRMATION_EMAIL_EXTERNAL_ID_EN = "EBILL.CONFR.NCBSERVICES.EN_US";
    public static final String WEEKLY_SUMMARY_CONFIRMATION_EMAIL_EXTERNAL_ID_ES = "EBILL.CONFR.NCBSERVICES.ES_US";
    public static final String PAYMENT_EXTENSION_EXTERNAL_ID_EN = "PAYMTXTN_EMAIL.en_US";
    public static final String PAYMENT_EXTENSION_EXTERNAL_ID_ES = "PAYMTXTN_EMAIL.es_US";
    public static final String OAM_REGISTRATION_PREPAY_CONFIRM_EXTERNAL_ID_EN =  "EBILL.CONFR.RYACONFSS.EN_US";
    public static final String OAM_REGISTRATION_PREPAY_CONFIRM_EXTERNAL_ID_ES =  "EBILL.CONFR.RYACONFSS.ES_US";
    public static final String OAM_REGISTRATION_CONFIRM_EXTERNAL_ID_EN =  "EBILL.CONFR.SUAARBC.EN_US";
    public static final String OAM_REGISTRATION_CONFIRM_EXTERNAL_ID_ES =  "EBILL.CONFR.SUAARBC.ES_US";
    public static final String REQUEST_DUPLICATE_BILL_EXTERNAL_ID_EN="EBILL.CONFR.RDBC.EN_US";
    public static final String REQUEST_DUPLICATE_BILL_EXTERNAL_ID_ES="EBILL.CONFR.RDBC.ES_US ";
    public static final String SMB_OE_CONFIRMATION_CUSTOMER_EXTERNAL_ID_EN="SMB.ENROLL.CONF.CUST.EMAIL.EN_US";
    public static final String SMB_OE_CONFIRMATION_CUSTOMER_EXTERNAL_ID_ES="SMB.ENROLL.CONF.CUST.EMAIL.ES_US";
    public static final String SMB_GET_A_QUOTE_CONFIRMATION_EN = "SMB.GET.A.QUOTE.ENROLL.CONF.EMAIL.EN_US";
    public static final String SMB_GET_A_QUOTE_CONFIRMATION_ES = "SMB.GET.A.QUOTE.ENROLL.CONF.EMAIL.ES_US";
    public static final String SMB_OE_CONFIRMATION_COMPANY_EXTERNAL_ID_EN="SMB.ENROLL.CONF.COMPANY.EMAIL.EN_US";
    public static final String SMB_OE_CONFIRMATION_COMPANY_EXTERNAL_ID_ES="SMB.ENROLL.CONF.COMPANY.EMAIL.ES_US";
    public static final String SMB_GET_A_QUOTE_TO_CCRS_CONFIRMATION_EN = "SMB.GET.A.QUOTE.TO.CCRS.ENROLL.CONF.EMAIL.EN_US";
    public static final String SMB_GET_A_QUOTE_TO_CCRS_CONFIRMATION_ES = "SMB.GET.A.QUOTE.TO.CCRS.ENROLL.CONF.EMAIL.ES_US";
    
    // Start: ME:Bill Pay Confirmation Email Redesign - Dipika
    public static final String BILL_BANK_PAYMENT_EXTERNAL_ID = "EBILL.CONFR.BPCCC";
    public static final String BILL_CC_PAYMENT_EXTERNAL_ID = "EBILL.CONFR.BPCBA";
    // End: ME:Bill Pay Confirmation Email Redesign - Dipika
    
    // Constants for AMB
    public static final String AMB_REQUEST_EMAIL_TO="AMB_REQUEST_EMAIL_TO";
    public static final String AVERAGE_BILLING_CONFIRMATION_EXTERNAL_ID_EN = "EBILL.CONFR.AVGBILL.EN_US";
    public static final String AVERAGE_BILLING_CONFIRMATION_EXTERNAL_ID_ES = "EBILL.CONFR.AVGBILL.ES_US";
    public static final String AVERAGE_BILLING_BUSINESS_REQUEST_EXTERNAL_ID_EN = "EBILL.CONFR.RBBCC";
    public static final String AVERAGE_BILLING_BUSINESS_REQUEST_EXTERNAL_ID_ES = "EBILL.CONFR.RBBCC";
    public static final String AVERAGE_BILLING_BUSINESS_CONFIRMATION_EXTERNAL_ID_EN = "EBILL.CONFR.RBBC.EN_US";
    public static final String AVERAGE_BILLING_BUSINESS_CONFIRMATION_EXTERNAL_ID_ES = "EBILL.CONFR.RBBC.ES_US";

    // Constants for Account Add-Remove
    public static final String ADD_ELECTRICITY_ACCOUNT_CONFIRMATION_EXTERNAL_ID_EN_US = "EBILL.CONFR.AEAC.EN_US";
    public static final String REMOVE_ELECTRICITY_ACCOUNT_CONFIRMATION_EXTERNAL_ID_EN_US = "EBILL.CONFR.REAC.EN_US";
    public static final String ADD_ELECTRICITY_ACCOUNT_CONFIRMATION_EXTERNAL_ID_ES_US = "EBILL.CONFR.AEAC.ES_US";
    public static final String REMOVE_ELECTRICITY_ACCOUNT_CONFIRMATION_EXTERNAL_ID_ES_US = "EBILL.CONFR.REAC.ES_US";

    // Constants for SWAP
    public static final String SWAP_CONFIRMATION_EMAIL_EXTERNAL_ID_EN = "SWAP_EMAIL.en_US";
    public static final String SWAP_CONFIRMATION_EMAIL_EXTERNAL_ID_ES = "SWAP_EMAIL.es_US";
    public static final String SWAP_CONFIRM_SUBJECT = "SWAP Plan Confirmation";
    public static final String SWAP_BP_NUMBER = "business-partner-number";
    public static final String SWAP_OFFER_TITLE = "offer-title1";
    public static final String SWAP_CONTRACT_TERM = "contract_term";
    public static final String SWAP_CONTRACT_BEGINS = "contract_begins";
    public static final String SWAP_CONTRACT_ENDS = "contract_ends";
    public static final String SWAP_CANCELLATION_FEE = "cancellation_fee";
    public static final String SWAP_AVERAGE_PRICE = "average_price";
    public static final String SWAP_ENERGY_CHARGE = "energy_charge";
    public static final String SWAP_BASE_CHARGE = "base_charge";
    public static final String SWAP_TDSP_SURCHARGE = "tdsp_surcharge";
    public static final String SWAP_ESID = "esid";
    public static final String SWAP_DOC1_SMARTCODE = "doc1_smartcode";
    public static final String SWAP_DOC2_SMARTCODE = "doc2_smartcode";
    public static final String SWAP_DOC3_SMARTCODE = "doc3_smartcode";
    public static final String SWAP_DOC1 = "doc1";
    public static final String SWAP_DOC2 = "doc2";
    public static final String SWAP_DOC3 = "doc3";
    public static final String SWAP_SERVICE_ADDRESS1 = "service-address1";
    public static final String SWAP_BILLING_ADDRESS1 = "billing-address1";
    public static final String SWAP_PAYMENT_INFO = "payment_info";
    public static final String SWAP_BILLING_INFO = "billing_info";
    public static final String BCC_EMAIL_ADDRESS = "bcc_email_address";
    
    /* Predicatble12 changes Start - msadriw1 */
    public static final String ADDITIONAL_PRICING_TXT= "additional_pricing_txt";
    public static final String SWAP_OFFER_CATEGORY_EMAIL="offer_category";
    /* Predicatble12 changes End - msadriw1 */

    public static final String FORGOT_USERNAME_EMAIL_EXTERNAL_ID_EN = "EBILL.CONFR.FUNC.EN_US";
    public static final String FORGOT_USERNAME_EMAIL_EXTERNAL_ID_ES = "EBILL.CONFR.FUNC.ES_US";
    public static final String FORGOT_USERNAME_EMAIL_ARG1_USERNAME = "user-name";
    public static final String FORGOT_USERNAME_EMAIL_ARG2_TEXT = "text";
    public static final String FORGOT_USERNAME_EMAIL_SUBJECT = "Reliant User Name Assistance";

    public static final String OAM_BILLING_OPTION_CONFIRM_EXTERNAL_ID_EN ="EBILL.CONFR.CBOC.EN_US";
    public static final String OAM_BILLING_OPTION_CONFIRM_EXTERNAL_ID_ES ="EBILL.CONFR.CBOC.ES_US";
    public static final String OAM_BILLING_OPTION_CONFIRM_SUBJECT_ID_EN ="Bill Delivery Method Confirmation";
    public static final String OAM_BILLING_OPTION_CONFIRM_SUBJECT_ID_ES ="Confirmación de la forma de entrega de la facturación";

    public static final String OAM_BILLING_OPTION_CONFIRM_BILL_DELIVERY_METHOD = "bill-delivery-method";
    public static final String OAM_BILLING_OPTION_CONFIRM_CONTRACT_ACCOUNT_NUMBER = "contract-account-number";
    public static final String OAM_BILLING_OPTION_CONFIRM_CONTRACT_NAME = "name-on-account";
    public static final String OAM_BILLING_OPTION_CONFIRM_CONFIRMATION_NUMBER= "confirmation-number";


    /**
     * OE Email Template
     **/

    public static final String OAM_FPS_SUBJECT_EN = "Reliant Password Assistance";
    public static final String OAM_FPS_SUBJECT_ES = "Ayuda para contraseña de Reliant";
    public static final String OAM_FPS_EXTERNAL_ID_EN = "EBILL.CONFR.CPE.EN_US";
    public static final String OAM_FPS_EXTERNAL_ID_ES = "EBILL.CONFR.CPE.ES_US";

    public static final String OAM_FPS_PASSWROD_RESET_EXTERNAL_ID_EN = "EBILL.CONFR.RPCC.EN_US";
    public static final String OAM_FPS_PASSWROD_RESET_SUBJECT_EN = "Reset Password Confirmation";

    public static final String OAM_FPS_PASSWROD_RESET_EXTERNAL_ID_ES = "EBILL.CONFR.RPCC.ES_US";
    public static final String OAM_FPS_PASSWROD_RESET_SUBJECT_ES = "Confirmación de cambio de contraseña";

    public static final String CONST_TRANSACTION_ID = "transaction-id";
    public static final String CONST_URL_FIELD = "url-field";

    public static final String OE_ENROLLMENT_CONFIRMATION_EXTERNAL_ID="EMAIL.EXTERNALID.ONLINE.ENROLLMENT.CONFIRMATION";

    public static final String OAM_SECURITY_QUESTION_UPDATE_SUBJECT_EN= "Security Questions Updated";
    public static final String OAM_SECURITY_QUESTION_UPDATE_SUBJECT_ES= "Preguntas de Seguridad Actualizadas";
    public static final String OAM_SECURITY_QUESTION_UPDATE_EXTERNAL_ID_EN = "EBILL.CONFR.SQ.UPDATE.EN_US";
    public static final String OAM_SECURITY_QUESTION_UPDATE_EXTERNAL_ID_ES = "EBILL.CONFR.SQ.UPDATE.ES_US";

    public static final String OAM_SECURITY_QUESTION_CONFIRMATION ="confirmation";

    public static final String OAM_SECURITY_QUESTION_ADD_SUBJECT_EN= "Security Question Confirmation";
    public static final String OAM_SECURITY_QUESTION_ADD_SUBJECT_ES= "Confirmaciï¿½n de su Pregunta de Seguridad";
    public static final String OAM_SECURITY_QUESTION_ADD_EXTERNAL_ID_EN = "EBILL.CONFR.SQ.ADD.EN_US";
    public static final String OAM_SECURITY_QUESTION_ADD_EXTERNAL_ID_ES = "EBILL.CONFR.SQ.ADD.ES_US";

    public static final String MBE_ESTIMATE_BY_ADDRESS_SUBJECT_EN= "Your electricity bill estimate is ready for %%address%%";
    public static final String MBE_ESTIMATE_BY_ADDRESS_SUBJECT_ES= "Su factura de electricidad para %%address%% est\u00e1 lista";

    public static final String MBE_ESTIMATE_BY_ATTR_SUBJECT_EN= "Your electricity bill estimate is ready";
    public static final String MBE_ESTIMATE_BY_ATTR_SUBJECT_ES= "El estimado de su factura de electricidad est\u00e1 listo.";

    public static final String  MBE_ESTIMATE_EXTERNAL_ID_EN = "MBE.EMAIL.CONFRM.EN_US";
    public static final String  MBE_ESTIMATE_EXTERNAL_ID_ES = "MBE.EMAIL.CONFRM.ES_US";


    public static final String MBE_URL ="url";
    public static final String MBE_AVG_PRICE ="avgPrice";
    public static final String MBE_ADDRESS ="address";
    public static final String MBE_FULL_ADDRESS ="fullAddress";
    public static final String MBE_ZIPCODE = "zipCode";
    public static final String MBE_YEAR_BUILT = "yearBuilt";
    public static final String MBE_SQUARE_FOOTAGE = "sqFootage";
    public static final String MBE_ERROR_MSG_FLAG = "errorMsgMatchFlag";
    public static final String MBE_HEATING_SYSTEM = "heatingSystem";

    public static final String MBE_JAN_AVG_COST = "janAvgCost";
    public static final String MBE_FEB_AVG_COST = "febAvgCost";
    public static final String MBE_MAR_AVG_COST = "marAvgCost";
    public static final String MBE_APR_AVG_COST = "aprAvgCost";
    public static final String MBE_MAY_AVG_COST = "mayAvgCost";
    public static final String MBE_JUN_AVG_COST = "junAvgCost";
    public static final String MBE_JUL_AVG_COST = "julAvgCost";
    public static final String MBE_AUG_AVG_COST = "augAvgCost";
    public static final String MBE_SEP_AVG_COST = "sepAvgCost";
    public static final String MBE_OCT_AVG_COST = "octAvgCost";
    public static final String MBE_NOV_AVG_COST = "novAvgCost";
    public static final String MBE_DEC_AVG_COST = "decAvgCost";

    public static final String MBE_JAN_LOW_COST = "janLowCost";
    public static final String MBE_FEB_LOW_COST = "febLowCost";
    public static final String MBE_MAR_LOW_COST = "marLowCost";
    public static final String MBE_APR_LOW_COST = "aprLowCost";
    public static final String MBE_MAY_LOW_COST = "mayLowCost";
    public static final String MBE_JUN_LOW_COST = "junLowCost";
    public static final String MBE_JUL_LOW_COST = "julLowCost";
    public static final String MBE_AUG_LOW_COST = "augLowCost";
    public static final String MBE_SEP_LOW_COST = "sepLowCost";
    public static final String MBE_OCT_LOW_COST = "octLowCost";
    public static final String MBE_NOV_LOW_COST = "novLowCost";
    public static final String MBE_DEC_LOW_COST = "decLowCost";

    public static final String MBE_JAN_HIGH_COST = "janHighCost";
    public static final String MBE_FEB_HIGH_COST = "febHighCost";
    public static final String MBE_MAR_HIGH_COST = "marHighCost";
    public static final String MBE_APR_HIGH_COST = "aprHighCost";
    public static final String MBE_MAY_HIGH_COST = "mayHighCost";
    public static final String MBE_JUN_HIGH_COST = "junHighCost";
    public static final String MBE_JUL_HIGH_COST = "julHighCost";
    public static final String MBE_AUG_HIGH_COST = "augHighCost";
    public static final String MBE_SEP_HIGH_COST = "sepHighCost";
    public static final String MBE_OCT_HIGH_COST = "octHighCost";
    public static final String MBE_NOV_HIGH_COST = "novHighCost";
    public static final String MBE_DEC_HIGH_COST = "decHighCost";

    public static final String MBE_JAN_AVG_USAGE = "janAvgUsage";
    public static final String MBE_FEB_AVG_USAGE = "febAvgUsage";
    public static final String MBE_MAR_AVG_USAGE = "marAvgUsage";
    public static final String MBE_APR_AVG_USAGE = "aprAvgUsage";
    public static final String MBE_MAY_AVG_USAGE = "mayAvgUsage";
    public static final String MBE_JUN_AVG_USAGE = "junAvgUsage";
    public static final String MBE_JUL_AVG_USAGE = "julAvgUsage";
    public static final String MBE_AUG_AVG_USAGE = "augAvgUsage";
    public static final String MBE_SEP_AVG_USAGE = "sepAvgUsage";
    public static final String MBE_OCT_AVG_USAGE = "octAvgUsage";
    public static final String MBE_NOV_AVG_USAGE = "novAvgUsage";
    public static final String MBE_DEC_AVG_USAGE = "decAvgUsage";

    public static final String MBE_JAN_LOW_USAGE = "janLowUsage";
    public static final String MBE_FEB_LOW_USAGE = "febLowUsage";
    public static final String MBE_MAR_LOW_USAGE = "marLowUsage";
    public static final String MBE_APR_LOW_USAGE = "aprLowUsage";
    public static final String MBE_MAY_LOW_USAGE = "mayLowUsage";
    public static final String MBE_JUN_LOW_USAGE = "junLowUsage";
    public static final String MBE_JUL_LOW_USAGE = "julLowUsage";
    public static final String MBE_AUG_LOW_USAGE = "augLowUsage";
    public static final String MBE_SEP_LOW_USAGE = "sepLowUsage";
    public static final String MBE_OCT_LOW_USAGE = "octLowUsage";
    public static final String MBE_NOV_LOW_USAGE = "novLowUsage";
    public static final String MBE_DEC_LOW_USAGE = "decLowUsage";

    public static final String MBE_JAN_HIGH_USAGE = "janHighUsage";
    public static final String MBE_FEB_HIGH_USAGE = "febHighUsage";
    public static final String MBE_MAR_HIGH_USAGE = "marHighUsage";
    public static final String MBE_APR_HIGH_USAGE = "aprHighUsage";
    public static final String MBE_MAY_HIGH_USAGE = "mayHighUsage";
    public static final String MBE_JUN_HIGH_USAGE = "junHighUsage";
    public static final String MBE_JUL_HIGH_USAGE = "julHighUsage";
    public static final String MBE_AUG_HIGH_USAGE = "augHighUsage";
    public static final String MBE_SEP_HIGH_USAGE = "sepHighUsage";
    public static final String MBE_OCT_HIGH_USAGE = "octHighUsage";
    public static final String MBE_NOV_HIGH_USAGE = "novHighUsage";
    public static final String MBE_DEC_HIGH_USAGE = "decHighUsage";
    /*
     * TOS Email Template Constants
     */
    //TOS Confirmaton Email
    public static final String TOS_CONFIRM_EMAIL_VAR_RECONNECTION_LINK="reconnection_link";
	public static final String TOS_CONFIRM_EMAIL_VAR_CONTRACT_ACCOUNT_INFO="contract_account_info";
	public static final String TOS_CONFIRM_EMAIL_VAR_WEB_TRANSACTION_TIMESTAMP="web_transaction_timestamp";
	public static final String TOS_CONFIRM_EMAIL_VAR_TRANSACTION_X_IDOC_NUMBER="transaction_x_idoc_number";
	public static final String TOS_CONFIRM_EMAIL_VAR_MI_DATE="mi_date";
	public static final String TOS_CONFIRM_EMAIL_VAR_NEW_SERVICE_ADDRESS_LINE1="new_service_address_line1";
	public static final String TOS_CONFIRM_EMAIL_VAR_NEW_SERVICE_ADDRESS_LINE2="new_service_address_line2";
	public static final String TOS_CONFIRM_EMAIL_VAR_ESI_ID="esi_id";
	public static final String TOS_CONFIRM_EMAIL_VAR_PERMIT_TYPE="permit_type";
	public static final String TOS_CONFIRM_EMAIL_VAR_BILLING_SERVICE_ADDRESS_LINE1="billing_service_address_line1";
	public static final String TOS_CONFIRM_EMAIL_VAR_BILLING_SERVICE_ADDRESS_LINE2="billing_service_address_line2";
	public static final String TOS_CONFIRM_EMAIL_VAR_MO_DATE="mo_date";
	public static final String TOS_CONFIRM_EMAIL_VAR_CURRENT_SERVICE_ADDRESS_LINE1="current_service_address_line1";
	public static final String TOS_CONFIRM_EMAIL_VAR_CURRENT_SERVICE_ADDRESS_LINE2="current_service_address_line2";
	public static final String TOS_CONFIRM_EMAIL_VAR_CONTACT_NAME="contact_name";
	public static final String TOS_CONFIRM_EMAIL_VAR_PHONE_NUMBER="phone_number";
	public static final String TOS_CONFIRM_EMAIL_VAR_CONTACT_EMAIL="contact_email";
	public static final String TOS_CONFIRM_EMAIL_VAR_PAYMENT_INFO="payment_info";
	public static final String TOS_CONFIRM_EMAIL_VAR_NEW_PLAN_NAME="new_plan_name";
	public static final String TOS_CONFIRM_EMAIL_VAR_AVERAGE_PRICE="average_price";
	public static final String TOS_CONFIRM_EMAIL_VAR_ENERGY_CHARGE="energy_charge";
	public static final String TOS_CONFIRM_EMAIL_VAR_BASE_CHARGE="base_charge";
	public static final String TOS_CONFIRM_EMAIL_VAR_SUR_CHARGE="sur_charge";
	public static final String TOS_CONFIRM_EMAIL_VAR_CONTRACT_TERM="contract_term";
	public static final String TOS_CONFIRM_EMAIL_VAR_CONTRACT_START="contract_start";
	public static final String TOS_CONFIRM_EMAIL_VAR_CONTRACT_END="contract_end";
	public static final String TOS_CONFIRM_EMAIL_VAR_CANCELLATION_FEE="cancellation_fee";
	public static final String TOS_CONFIRM_EMAIL_VAR_SIGNUP_BONUS="signup_bonus";
	public static final String TOS_CONFIRM_EMAIL_VAR_EFLID="eflId";
	public static final String TOS_CONFIRM_EMAIL_VAR_SMARTCODE_ELECTRICITYFACTSLABEL="SmartCode_ElectricityFactsLabel";
	public static final String TOS_CONFIRM_EMAIL_VAR_AGREEMENTTERMSID="agreementTermsId";
	public static final String TOS_CONFIRM_EMAIL_VAR_SMARTCODE_AGREEMENTTERMS="SmartCode_AgreementTerms";
	public static final String TOS_CONFIRM_EMAIL_VAR_CUSTOMERRIGHTSID="customerRightsId";
	public static final String TOS_CONFIRM_EMAIL_VAR_SMARTCODE_CUSTOMERRIGHTS="SmartCode_CustomerRights";
	public static final String TOS_CONFIRM_EMAIL_VAR_BILLING_INFO="billing_info";
	public static final String TOS_CONFIRM_EMAIL_SUBJECT_ES="ConfirmaciÃ³n de la transferencia del servicio";
	public static final String TOS_CONFIRM_EMAIL_SUBJECT_EN="Transfer Service Confirmation";
	public static final String TOS_CONFIRM_EMAIL_EMAIL_EXTERNAL_ID_ES="TOS_EMAIL.es_US";
	public static final String TOS_CONFIRM_EMAIL_EMAIL_EXTERNAL_ID_EN="TOS_EMAIL.en_US";
	// START Postprod fix for Defect #4010 - GK
	public static final String TOS_CONFIRM_EMAIL_VAR_BUSINESS_PARTNER_NUMBER="business_partner_number";
	public static final String TOS_CONFIRM_EMAIL_VAR_CONTRACT_ACCOUNT_NUMBER="contract_account_number";
	// END Postprod fix for Defect #4010 - GK

	//TOS Reconnect email
	public static final String TOS_RECONNECT_EMAIL_VAR_ZIP_CODE="zip_code";
	public static final String TOS_RECONNECT_EMAIL_VAR_REFERER_ID="referer_id";
	public static final String TOS_RECONNECT_EMAIL_VAR_CAMPAIGN_ID="campaign_id";
	public static final String TOS_RECONNECT_EMAIL_VAR_CONTACT_NAME="contact_name";
	public static final String TOS_RECONNECT_EMAIL_VAR_CONTRACT_ACCOUNT_INFO="contract_account_info";
	public static final String TOS_RECONNECT_EMAIL_VAR_PHONE_NUMBER="phone_number";
	public static final String TOS_RECONNECT_EMAIL_SUBJECT_ES="Haga su mudanza aún más fácil con Reliant Energy Connections";
	public static final String TOS_RECONNECT_EMAIL_SUBJECT_EN="Make your move even easier with Reliant Energy Connections";
	public static final String TOS_RECONNECT_EMAIL_EMAIL_EXTERNAL_ID_ES="REConnect_EMAIL.es_US";
	public static final String TOS_RECONNECT_EMAIL_EMAIL_EXTERNAL_ID_EN="REConnect_EMAIL.en_US";

	//Eco Email
	public static final String ECO_SEND_TO_EMAIL="readmeter@reliant.com";

	//Email Us
	public static final String EMAILUS_SEND_TO_EMAIL="services@reliant.com";

	public static final String EN_EMAIL_ENGLISH = "EN";
	public static final String ES_EMAIL_ENGLISH = "ES";
	public static final String CURRENT_YEAR = "current-year";
	
}
