package com.multibrand.dto.response;

import javax.ws.rs.core.Response;

import com.multibrand.vo.response.AffiliateOfferDO;

public class SalesOfferResponse extends SalesBaseResponse {
	private String offerDate;
	private String offerTime;
	private String tdspCodeCCS;
	private String cmsErrorOffers;
	private String erpErrorOffers;
	private AffiliateOfferDO[] affiliateOfferList;


	public String getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(String offerDate) {
		this.offerDate = offerDate;
	}

	public String getOfferTime() {
		return offerTime;
	}

	public void setOfferTime(String offerTime) {
		this.offerTime = offerTime;
	}

	public String getTdspCodeCCS() {
		return tdspCodeCCS;
	}

	public void setTdspCodeCCS(String tdspCodeCCS) {
		this.tdspCodeCCS = tdspCodeCCS;
	}

	public AffiliateOfferDO[] getAffiliateOfferList() {
		return affiliateOfferList;
	}

	public void setAffiliateOfferList(AffiliateOfferDO[] affiliateOfferList) {
		this.affiliateOfferList = affiliateOfferList;
	}

	public String getCmsErrorOffers() {
		return cmsErrorOffers;
	}

	public void setCmsErrorOffers(String cmsErrorOffers) {
		this.cmsErrorOffers = cmsErrorOffers;
	}

	public String getErpErrorOffers() {
		return erpErrorOffers;
	}

	public void setErpErrorOffers(String erpErrorOffers) {
		this.erpErrorOffers = erpErrorOffers;
	}

}
