package com.multibrand.dto.response;

import com.multibrand.vo.response.AffiliateOfferDO;
import com.multibrand.vo.response.GenericResponse;

public class AffiliateOfferResponse extends GenericResponse {
	private String offerDate;
	private String offerTime;
	private String tdspCodeCCS;
	
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


}
