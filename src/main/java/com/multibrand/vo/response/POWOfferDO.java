package com.multibrand.vo.response;

import java.io.Serializable;

public class POWOfferDO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String promotionCode;
	private String offerCode;
	private String campaignCode;
	private String eSenseFlag;
	private String prePayFlag;
	private String  term;
	private String error_code;
	
	public String getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	public String getCampaignCode() {
		return campaignCode;
	}
	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}
	public String geteSenseFlag() {
		return eSenseFlag;
	}
	public void seteSenseFlag(String eSenseFlag) {
		this.eSenseFlag = eSenseFlag;
	}
	public String getPrePayFlag() {
		return prePayFlag;
	}
	public void setPrePayFlag(String prePayFlag) {
		this.prePayFlag = prePayFlag;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	
	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}
	public String getPromotionCode() {
		return promotionCode;
	}
	
	
}
