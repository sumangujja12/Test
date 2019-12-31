package com.multibrand.dto.request;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.multibrand.util.Constants;

public class WebHookRequest implements Constants, Serializable {
	
	private static final long serialVersionUID = -2750231679908551082L;

	@SerializedName("account_id")
	@Expose
	private String accountId;
	@SerializedName("account_number")
	@Expose
	private String accountNumber;
	@SerializedName("metadata")
	@Expose
	private WebHookMetadata webHookMetadata;
	@SerializedName("payment_id")
	@Expose
	private String paymentId;

	public String getAccountId() {
	return accountId;
	}

	public void setAccountId(String accountId) {
	this.accountId = accountId;
	}

	public String getAccountNumber() {
	return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
	}

	public WebHookMetadata getWebHookMetadata() {
		return webHookMetadata;
	}

	public void setWebHookMetadata(WebHookMetadata webHookMetadata) {
		this.webHookMetadata = webHookMetadata;
	}

	public String getPaymentId() {
	return paymentId;
	}

	public void setPaymentId(String paymentId) {
	this.paymentId = paymentId;
	}
	
}
