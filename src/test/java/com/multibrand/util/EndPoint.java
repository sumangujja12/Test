package com.multibrand.util;

public interface EndPoint {
	
	/**** B I L L I N G       E N D P O I N T S  ****/
	public static final String GET_ACCOUNT_DETAILS = "/billResource/getAccountDetails";
	public static final String GET_BALANCE = "/billResource/getBalance";
	public static final String GET_BILLING_ADDRESS = "/billResource/getBillingAddress";
	public static final String UPDATE_PAPER_FREE_BILLING = "/billResource/updatePaperFreeBilling";
	public static final String GET_PROJECTED_BILL = "/billResource/projectedBill";
	public static final String GET_BALANCE_FOR_GME_MOBILE = "/billResource/getBalanceForGMEMobile";
	public static final String GET_BANK_CC_INFO = "/billResource/getBankCCInfo";
	public static final String SUBMIT_BANK_PAYMENT = "/billResource/submitBankPayment";
	public static final String SUBMIT_CC_PAYMENT = "/billResource/submitCCPayment";
	public static final String GET_AVERAGE_TEMP_BILL = "/billResource/avgTemperatureBill";
	public static final String GET_PAY_ACCOUNTS = "/billResource/getPayAccounts";
	public static final String SCHEDULE_ONETIME_CC_PAYMENT = "/billResource/scheduleOneTimeCCPayment";
	public static final String GET_PAYMENT_METHODS = "/billResource/getPaymentMethods";
	public static final String GET_BANK_PAYMENT_INSTITUTION = "/billResource/getBankPaymentInstitution";
	public static final String GET_AUTOPAY_INFO = "billResource/getAutoPayInfo";
	
	
	/***** P R O F I L E     E N D P O I N T S   ******/
	public static final String FORGOT_USERNAME = "/profile/forgotUserName";
	public static final String FORGOT_PASSWORD = "/profile/forgotPassword";
	public static final String VALIDATE_PASSWORD_LINK = "/profile/validatePasswordlink";
	public static final String GET_USER_OR_ACCOUNT_NUMBER = "/profile/getUserOrAcctNumber";
	public static final String GET_USER_ID = "/profile/getUserId";
	public static final String GET_PROFILE_CHECK = "/profile/profileCheck";
	public static final String SMART_METER_CHECK = "/profile/smartMeterCheck";
	public static final String VALIDATE_ACCOUNT = "/profile/validateAccount";
	public static final String GET_SVT_DATA = "/profile/getSVTData";

}
