package com.multibrand.dto.request;


import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;

public class GetOEKBAQuestionsRequest extends BaseAffiliateRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@NotBlank(groups = BasicConstraint.class)
	private String trackingId;
	@NotBlank(groups = BasicConstraint.class)
	private String guid;
	

	public String getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
}
