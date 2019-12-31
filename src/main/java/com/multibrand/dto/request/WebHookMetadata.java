package com.multibrand.dto.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebHookMetadata {
	@SerializedName("key1")
	@Expose
	private String key1;
	@SerializedName("key2")
	@Expose
	private String key2;
	@SerializedName("external_account_id")
	@Expose
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
