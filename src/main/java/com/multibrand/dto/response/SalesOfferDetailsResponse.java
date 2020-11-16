package com.multibrand.dto.response;

import com.multibrand.vo.response.AffiliateOfferDO;

public class SalesOfferDetailsResponse extends SalesBaseResponse{

	private AffiliateOfferDO[] affiliateOfferList;

	public AffiliateOfferDO[] getAffiliateOfferList() {
		return affiliateOfferList;
	}

	public void setAffiliateOfferList(AffiliateOfferDO[] affiliateOfferList) {
		this.affiliateOfferList = affiliateOfferList;
	}
	
}
