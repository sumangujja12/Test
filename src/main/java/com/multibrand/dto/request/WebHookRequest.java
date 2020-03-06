package com.multibrand.dto.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.multibrand.util.Constants;
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebHookRequest implements Constants, Serializable {
	
	private static final long serialVersionUID = -2750231679908551082L;

	@JsonProperty("account_id")
	private String accountId;
	@JsonProperty("account_number")
	private String accountNumber;
	@JsonProperty("metadata")
	private WebHookMetadata webHookMetadata;
	@JsonProperty("payment_id")
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
