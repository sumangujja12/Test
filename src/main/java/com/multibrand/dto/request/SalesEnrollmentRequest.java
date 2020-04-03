package com.multibrand.dto.request;

public class SalesEnrollmentRequest extends SalesOERequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String offerCode;
	private String promoCode;
	private String campaignCode;
	private String productPriceCode;
	private String incentiveCode;
	private String marketSegment;
	private String ebillFlag;
	

	
	public String getEbillFlag() {
		return ebillFlag;
	}
	public void setEbillFlag(String ebillFlag) {
		this.ebillFlag = ebillFlag;
	}
	public String getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public String getCampaignCode() {
		return campaignCode;
	}
	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}
	public String getProductPriceCode() {
		return productPriceCode;
	}
	public void setProductPriceCode(String productPriceCode) {
		this.productPriceCode = productPriceCode;
	}
	public String getIncentiveCode() {
		return incentiveCode;
	}
	public void setIncentiveCode(String incentiveCode) {
		this.incentiveCode = incentiveCode;
	}
	public String getMarketSegment() {
		return marketSegment;
	}
	public void setMarketSegment(String marketSegment) {
		this.marketSegment = marketSegment;
	}
	
	
	
	
}

