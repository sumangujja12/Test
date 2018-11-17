package com.multibrand.dto;

import java.io.Serializable;


/**
 * 
 * @author vsood30
 *
 */
public class CreditCardDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6189221274480220754L;
	private String ccType;
	private String ccExpiryMonth;
	private String ccExpiryYear;
	private String ccBillZip;
	private String ccNumber;
	private String ccToken;
	private String accountName;
	private String institutionCode;
	private String cvvNumber;
	private String ccTypeNum;
	
	public String getCcTypeNum() {
		return ccTypeNum;
	}

	public void setCcTypeNum(String ccTypeNum) {
		this.ccTypeNum = ccTypeNum;
	}

	public String getCvvNumber() {
		return cvvNumber;
	}

	public void setCvvNumber(String cvvNumber) {
		this.cvvNumber = cvvNumber;
	}

	public String getCcType() {
		return ccType;
	}

	public void setCcType(String ccType) {
		this.ccType = ccType;
	}

	public String getCcExpiryMonth() {
		return ccExpiryMonth;
	}

	public void setCcExpiryMonth(String ccExpiryMonth) {
		this.ccExpiryMonth = ccExpiryMonth;
	}

	public String getCcExpiryYear() {
		return ccExpiryYear;
	}

	public void setCcExpiryYear(String ccExpiryYear) {
		this.ccExpiryYear = ccExpiryYear;
	}

	public String getCcBillZip() {
		return ccBillZip;
	}

	public void setCcBillZip(String ccBillZip) {
		this.ccBillZip = ccBillZip;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getCcToken() {
		return ccToken;
	}

	public void setCcToken(String ccToken) {
		this.ccToken = ccToken;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}
	/*
	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}
	*/
}
