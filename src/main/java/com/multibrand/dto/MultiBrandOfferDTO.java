package com.multibrand.dto;

import java.util.ArrayList;
import java.util.List;

import com.multibrand.dto.response.OfferContentResponse;

public class MultiBrandOfferDTO {
	
	
	private List<OfferContentResponse> multiBrandOfferList = new ArrayList<OfferContentResponse>();

	/**
	 * @return the multiBrandOfferList
	 */
	public List<OfferContentResponse> getMultiBrandOfferList() {
		return multiBrandOfferList;
	}

	/**
	 * @param multiBrandOfferList the multiBrandOfferList to set
	 */
	public void setMultiBrandOfferList(List<OfferContentResponse> multiBrandOfferList) {
		this.multiBrandOfferList = multiBrandOfferList;
	} 
	
	
	

}
