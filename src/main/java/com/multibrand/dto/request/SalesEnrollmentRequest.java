package com.multibrand.dto.request;

import org.hibernate.validator.constraints.Length;

import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.SizeConstraint;

public class SalesEnrollmentRequest extends SalesOERequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty
	@Length(min= 8, max = 8, groups = SizeConstraint.class)
	private String offerCode;
	
	@NotEmpty
	@Length(max = 10, groups = SizeConstraint.class)
	private String promoCode;
	
	@NotEmpty
	@Length(max = 10, groups = SizeConstraint.class)
	private String campaignCode;
	
	@NotEmpty
	@Length(max = 10, groups = SizeConstraint.class)
	private String productPriceCode;
	
	@NotEmpty
	@Length(max = 15, groups = SizeConstraint.class)
	private String incentiveCode;
	
	@NotEmpty
	@Length(max = 2, groups = SizeConstraint.class)
	private String marketSegment;
	
	@Length(max = 1, groups = SizeConstraint.class)
	private String ebillFlag;
	
	private String securityMethod;

	
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
	public String getSecurityMethod() {
		return securityMethod;
	}
	public void setSecurityMethod(String securityMethod) {
		this.securityMethod = securityMethod;
	}
	
	
	
	
}

