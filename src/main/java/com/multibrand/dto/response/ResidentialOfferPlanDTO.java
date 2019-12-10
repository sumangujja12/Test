package com.multibrand.dto.response;

import java.io.Serializable;

import com.multibrand.domain.PromoOfferOutDataAvgPriceMapEntry;
import com.multibrand.domain.PromoOfferTDSPCharge;
import com.multibrand.dto.ProductOfferCommonVO;

public class ResidentialOfferPlanDTO  extends ProductOfferCommonVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PromoOfferOutDataAvgPriceMapEntry [] avgPrices;
	private PromoOfferTDSPCharge[] offerTDSPCharges;
	public PromoOfferOutDataAvgPriceMapEntry[] getAvgPrices() {
		return avgPrices;
	}
	public void setAvgPrices(PromoOfferOutDataAvgPriceMapEntry[] avgPrices) {
		this.avgPrices = avgPrices;
	}
	public PromoOfferTDSPCharge[] getOfferTDSPCharges() {
		return offerTDSPCharges;
	}
	public void setOfferTDSPCharges(PromoOfferTDSPCharge[] offerTDSPCharges) {
		this.offerTDSPCharges = offerTDSPCharges;
	}
	

}
