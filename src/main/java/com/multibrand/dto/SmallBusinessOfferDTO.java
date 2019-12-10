package com.multibrand.dto;

import java.io.Serializable;
import java.util.List;

import com.multibrand.domain.PromoOfferTDSPCharge;


public class SmallBusinessOfferDTO extends ProductOfferCommonVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strContractLength;
	private String strPaymentTerm;
	private String strLateFee;
	private List<SmallBusinessAvgPriceVO> avgPrices;
	private PromoOfferTDSPCharge[] offerTDSPCharges;
	private String strPaymentOption;
	
	
	
	
	
	
	public List<SmallBusinessAvgPriceVO> getAvgPrices() {
		return avgPrices;
	}

	public void setAvgPrices(List<SmallBusinessAvgPriceVO> avgPrices) {
		this.avgPrices = avgPrices;
	}

	public String getStrContractLength() {
		return strContractLength;
	}

	public void setStrContractLength(String strContractLength) {
		this.strContractLength = strContractLength;
	}

	public String getStrPaymentTerm() {
		return strPaymentTerm;
	}

	public void setStrPaymentTerm(String strPaymentTerm) {
		this.strPaymentTerm = strPaymentTerm;
	}


	public String getStrLateFee() {
		return strLateFee;
	}

	public void setStrLateFee(String strLateFee) {
		this.strLateFee = strLateFee;
	}

	public String getStrPaymentOption() {
		return strPaymentOption;
	}

	public void setStrPaymentOption(String strPaymentOption) {
		this.strPaymentOption = strPaymentOption;
	}


	public PromoOfferTDSPCharge[] getOfferTDSPCharges() {
		return offerTDSPCharges;
	}

	public void setOfferTDSPCharges(PromoOfferTDSPCharge[] offerTDSPCharges) {
		this.offerTDSPCharges = offerTDSPCharges;
	}

	
	
}
