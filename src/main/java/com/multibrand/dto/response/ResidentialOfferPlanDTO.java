package com.multibrand.dto.response;

import java.io.Serializable;

import com.multibrand.domain.PromoOfferOutDataAvgPriceMapEntry;
import com.multibrand.domain.PromoOfferTDSPCharge;
import com.multibrand.dto.ProductOfferCommonVO;
import com.multibrand.vo.response.TDSPChargeDO;

public class ResidentialOfferPlanDTO  extends ProductOfferCommonVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PromoOfferOutDataAvgPriceMapEntry [] avgPrices;
	private TDSPChargeDO offerTDSPCharges;
	public PromoOfferOutDataAvgPriceMapEntry[] getAvgPrices() {
		return avgPrices;
	}
	public void setAvgPrices(PromoOfferOutDataAvgPriceMapEntry[] avgPrices) {
		this.avgPrices = avgPrices;
	}
	public TDSPChargeDO getOfferTDSPCharges() {
		return offerTDSPCharges;
	}
	public void setOfferTDSPCharges(TDSPChargeDO offerTDSPCharges) {
		this.offerTDSPCharges = offerTDSPCharges;
	}
	

}
