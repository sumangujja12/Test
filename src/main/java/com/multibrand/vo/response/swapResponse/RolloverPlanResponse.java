package com.multibrand.vo.response.swapResponse;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="RolloverPlanDetailsResponse")
public class RolloverPlanResponse extends GenericResponse {

	private OfferDO rolloverOffer;

	public OfferDO getRolloverOffer() {
		return rolloverOffer;
	}
	
	public void setRolloverOffer(OfferDO rolloverOffer) {
		this.rolloverOffer = rolloverOffer;
	}
	
}
