package com.multibrand.dto.request;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebHookMetadata {
	@JsonProperty("key1")
	private String key1;
	@JsonProperty("key2")
	private String key2;
	@JsonProperty("external_account_id")
	private String externalAccountId;

	public String getKey1() {
	return key1;
	}

	public void setKey1(String key1) {
	this.key1 = key1;
	}

	public String getKey2() {
	return key2;
	}

	public void setKey2(String key2) {
	this.key2 = key2;
	}

	public String getExternalAccountId() {
	return externalAccountId;
	}

	public void setExternalAccountId(String externalAccountId) {
	this.externalAccountId = externalAccountId;
	}

}
