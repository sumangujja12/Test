package com.multibrand.vo.response;

import java.util.List;

import com.multibrand.dto.SmallBusinessOfferDTO;

public class SmallBusinessProductOfferResponse {
	
	private List<SmallBusinessOfferDTO> plans;
	private String errorMessage;
	
	
	public List<SmallBusinessOfferDTO> getPlans() {
		return plans;
	}
	public void setPlans(List<SmallBusinessOfferDTO> plans) {
		this.plans = plans;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

}
