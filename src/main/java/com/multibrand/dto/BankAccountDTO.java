package com.multibrand.dto;

import java.io.Serializable;


/**
 * 
 * @author vsood30
 *
 */
public class BankAccountDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8401595169067541099L;
	private String routingNum;
	private String bankingInstitutionName;
	private String accountNumber;
	private String accountName;
	private String bankAccLastThree;
	private String bankAccToken;
	private String bankAccType; //CHECKIN or SAVINGS
	
	public String getRoutingNum() {
		return routingNum;
	}

	public void setRoutingNum(String routingNum) {
		this.routingNum = routingNum;
	}

	public String getBankingInstitutionName() {
		return bankingInstitutionName;
	}

	public void setBankingInstitutionName(String bankingInstitutionName) {
		this.bankingInstitutionName = bankingInstitutionName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankAccLastThree() {
		return bankAccLastThree;
	}

	public void setBankAccLastThree(String bankAccLastThree) {
		this.bankAccLastThree = bankAccLastThree;
	}

	public String getBankAccToken() {
		return bankAccToken;
	}

	public void setBankAccToken(String bankAccToken) {
		this.bankAccToken = bankAccToken;
	}

	public String getBankAccType() {
		return bankAccType;
	}

	public void setBankAccType(String bankAccType) {
		this.bankAccType = bankAccType;
	}

	/*
	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}
	*/
	
}
