package com.multibrand.dto.request;

import javax.ws.rs.QueryParam;

import com.multibrand.request.validation.NotEmpty;

public class SalesOfferDetailsRequest extends  SalesBaseRequest{
	
	private static final long serialVersionUID = 1L;

	@QueryParam(value = "tdspCodeCCS")
	private String tdspCodeCCS;
	
	@QueryParam(value = "offerCode")
	@NotEmpty
	private String offerCode;
	
	@QueryParam(value = "promoCode")
	@NotEmpty
	private String promoCode;
	
	@QueryParam(value = "campaignCode")
	@NotEmpty
	private String campaignCode;
	
	public String getTdspCodeCCS() {
		return tdspCodeCCS;
	}
	
	public void setTdspCodeCCS(String tdspCodeCCS) {
		this.tdspCodeCCS = tdspCodeCCS;
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

}
