package com.multibrand.vo.response;

import java.util.List;

import com.multibrand.dto.response.ResidentialOfferPlanDTO;

public class ResidentialProductOfferResponse {
	
	private List<ResidentialOfferPlanDTO> plans;
	private String errorMessage;
	public List<ResidentialOfferPlanDTO> getPlans() {
		return plans;
	}
	public void setPlans(List<ResidentialOfferPlanDTO> plans) {
		this.plans = plans;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
