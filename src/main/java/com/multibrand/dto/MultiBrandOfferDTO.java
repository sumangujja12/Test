package com.multibrand.dto;

import java.util.ArrayList;
import java.util.List;

import com.multibrand.vo.response.ContractOffer;

public class MultiBrandOfferDTO {
	
	
	private List<ContractOffer> multiBrandOfferList = new ArrayList<ContractOffer>();

	/**
	 * @return the multiBrandOfferList
	 */
	public List<ContractOffer> getMultiBrandOfferList() {
		return multiBrandOfferList;
	}

	/**
	 * @param multiBrandOfferList the multiBrandOfferList to set
	 */
	public void setMultiBrandOfferList(List<ContractOffer> multiBrandOfferList) {
		this.multiBrandOfferList = multiBrandOfferList;
	} 
	
	
	

}
