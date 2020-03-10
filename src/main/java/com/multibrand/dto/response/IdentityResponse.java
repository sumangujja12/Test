package com.multibrand.dto.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="IdentityResponse")
public class IdentityResponse extends  SalesBaseResponse {

	private String posidDLDate;	
	private String posidSSNDate;
	private String trackingId;
	private String tokenizedSSN;
	private String tokenizedTDL;
	private String bpMatchFlag;
	private String matchedBP;
	private String existingStreetAddress;
	private String existingAptNum;
	private String existingCity;
	private String existingState;
	private String existingZip;
	private String retryCount;
	private String kbaSuggestionFlag;
	private String guid;
	
	public String getPosidDLDate() {
		return posidDLDate;
	}
	public void setPosidDLDate(String posidDLDate) {
		this.posidDLDate = posidDLDate;
	}
	public String getPosidSSNDate() {
		return posidSSNDate;
	}
	public void setPosidSSNDate(String posidSSNDate) {
		this.posidSSNDate = posidSSNDate;
	}
	public String getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	public String getTokenizedSSN() {
		return tokenizedSSN;
	}
	public void setTokenizedSSN(String tokenizedSSN) {
		this.tokenizedSSN = tokenizedSSN;
	}
	public String getTokenizedTDL() {
		return tokenizedTDL;
	}
	public void setTokenizedTDL(String tokenizedTDL) {
		this.tokenizedTDL = tokenizedTDL;
	}
	public String getBpMatchFlag() {
		return bpMatchFlag;
	}
	public void setBpMatchFlag(String bpMatchFlag) {
		this.bpMatchFlag = bpMatchFlag;
	}
	public String getMatchedBP() {
		return matchedBP;
	}
	public void setMatchedBP(String matchedBP) {
		this.matchedBP = matchedBP;
	}
	public String getExistingStreetAddress() {
		return existingStreetAddress;
	}
	public void setExistingStreetAddress(String existingStreetAddress) {
		this.existingStreetAddress = existingStreetAddress;
	}
	public String getExistingAptNum() {
		return existingAptNum;
	}
	public void setExistingAptNum(String existingAptNum) {
		this.existingAptNum = existingAptNum;
	}
	public String getExistingCity() {
		return existingCity;
	}
	public void setExistingCity(String existingCity) {
		this.existingCity = existingCity;
	}
	public String getExistingState() {
		return existingState;
	}
	public void setExistingState(String existingState) {
		this.existingState = existingState;
	}
	public String getExistingZip() {
		return existingZip;
	}
	public void setExistingZip(String existingZip) {
		this.existingZip = existingZip;
	}
	public String getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(String retryCount) {
		this.retryCount = retryCount;
	}
	public String getKbaSuggestionFlag() {
		return kbaSuggestionFlag;
	}
	public void setKbaSuggestionFlag(String kbaSuggestionFlag) {
		this.kbaSuggestionFlag = kbaSuggestionFlag;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}	
	
	
}
