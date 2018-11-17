package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="SalesforceTokenResponse")
public class SalesforceTokenResponse extends GenericResponse {
	

	@SerializedName("id")
	private String id;
	
	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("instance_url")
	private String environmetURL;
	
	@SerializedName("signature")
	private String signature;
	
	@SerializedName("issued_at")
	private String issuedAt;
	
	@SerializedName("token_type")
	private String tokenType;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the environmetURL
	 */
	public String getEnvironmetURL() {
		return environmetURL;
	}

	/**
	 * @param environmetURL the environmetURL to set
	 */
	public void setEnvironmetURL(String environmetURL) {
		this.environmetURL = environmetURL;
	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @return the issuedAt
	 */
	public String getIssuedAt() {
		return issuedAt;
	}

	/**
	 * @param issuedAt the issuedAt to set
	 */
	public void setIssuedAt(String issuedAt) {
		this.issuedAt = issuedAt;
	}

	/**
	 * @return the tokenType
	 */
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * @param tokenType the tokenType to set
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	


}
