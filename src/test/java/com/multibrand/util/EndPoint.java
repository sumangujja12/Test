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
	public static final String GET_AUTOPAY_INFO = "/billResource/getAutoPayInfo";

}
