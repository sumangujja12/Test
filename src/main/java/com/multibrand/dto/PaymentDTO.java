package com.multibrand.dto;

import java.io.Serializable;

import com.multibrand.util.CommonUtil;

public class PaymentDTO implements Serializable{
 
	private static final long serialVersionUID = -6278520723637961021L;
	
	private String paymentType;
	
	private CreditCardDTO creditCardDTO;
	private BankAccountDTO bankAccountDTO;
	private String paymentStatusCode; 		// for status of submitCCPayment
	private String paymentMethod;   //CC or BANK or GC
	private String auth_number;       // for storing XVALIDNUM from CCpayment response for DB
	private String ccDeclineErrorCode;    // used to store invalid CC detail Snippet name
	
	/*
	 * Payment Method placed in OEsignupDTO
	 * as it is used for two instances prepayDTO and
	 *  depositpaymentDTO and needs to be singleton
	 *  
	 */
	
	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public CreditCardDTO getCreditCardDTO() {
		return creditCardDTO;
	}

	public void setCreditCardDTO(CreditCardDTO creditCardDTO) {
		this.creditCardDTO = creditCardDTO;
	}

	public BankAccountDTO getBankAccountDTO() {
		return bankAccountDTO;
	}

	public void setBankAccountDTO(BankAccountDTO bankAccountDTO) {
		this.bankAccountDTO = bankAccountDTO;
	}

	public String getPaymentStatusCode() {
		return paymentStatusCode;
	}

	public void setPaymentStatusCode(String paymentStatusCode) {
		this.paymentStatusCode = paymentStatusCode;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getAuth_number() {
		return auth_number;
	}

	public void setAuth_number(String auth_number) {
		this.auth_number = auth_number;
	}

	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}

	public String getCcDeclineErrorCode() {
		return ccDeclineErrorCode;
	}

	public void setCcDeclineErrorCode(String ccDeclineErrorCode) {
		this.ccDeclineErrorCode = ccDeclineErrorCode;
	}
}
