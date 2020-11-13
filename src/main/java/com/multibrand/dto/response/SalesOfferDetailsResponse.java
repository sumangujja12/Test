package com.multibrand.dto.response;

import com.multibrand.vo.response.AffiliateOfferDO;

public class SalesOfferDetailsResponse  extends  SalesBaseResponse{

	private String cmsLegalese;
	private String baseCharge;
	private String usageCharge;
	private String usageChargeThreshold;
	private String cmsAdditionalPricingText;
	private AffiliateOfferDO[] affiliateOfferList;

	public String getCmsLegalese() {
		return cmsLegalese;
	}

	public void setCmsLegalese(String cmsLegalese) {
		this.cmsLegalese = cmsLegalese;
	}

	public String getBaseCharge() {
		return baseCharge;
	}

	public void setBaseCharge(String baseCharge) {
		this.baseCharge = baseCharge;
	}

	public String getUsageCharge() {
		return usageCharge;
	}

	public void setUsageCharge(String usageCharge) {
		this.usageCharge = usageCharge;
	}

	public String getUsageChargeThreshold() {
		return usageChargeThreshold;
	}

	public void setUsageChargeThreshold(String usageChargeThreshold) {
		this.usageChargeThreshold = usageChargeThreshold;
	}

	public String getCmsAdditionalPricingText() {
		return cmsAdditionalPricingText;
	}

	public void setCmsAdditionalPricingText(String cmsAdditionalPricingText) {
		this.cmsAdditionalPricingText = cmsAdditionalPricingText;
	}

	public AffiliateOfferDO[] getAffiliateOfferList() {
		return affiliateOfferList;
	}

	public void setAffiliateOfferList(AffiliateOfferDO[] affiliateOfferList) {
		this.affiliateOfferList = affiliateOfferList;
	}
	
}
