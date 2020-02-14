package com.multibrand.vo.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WseDO {
	@SerializedName("email_file_name")
	@Expose
	private String emailFileName;
	@SerializedName("email_sent_date")
	@Expose
	private String emailSentDate;
	@SerializedName("email_address")
	@Expose
	private String emailAddress;
	@SerializedName("contract_account_number")
	@Expose
	private String contractAccountNumber;
	@SerializedName("email_url")
	@Expose
	private String emailUrl;
	@SerializedName("contract_account")
	@Expose
	private String contractAccount;
	
	public String getEmailFileName() {
		return emailFileName;
	}
	public void setEmailFileName(String emailFileName) {
		this.emailFileName = emailFileName;
	}
	public String getEmailSentDate() {
		return emailSentDate;
	}
	public void setEmailSentDate(String emailSentDate) {
		this.emailSentDate = emailSentDate;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getContractAccountNumber() {
		return contractAccountNumber;
	}
	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}
	public String getEmailUrl() {
		return emailUrl;
	}
	public void setEmailUrl(String emailUrl) {
		this.emailUrl = emailUrl;
	}
	public String getContractAccount() {
		return contractAccount;
	}
	public void setContractAccount(String contractAccount) {
		this.contractAccount = contractAccount;
	}

}
