package com.multibrand.dto.request;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import com.multibrand.request.validation.BasicConstraint;

@Component
public class SalesOERequest extends SalesBaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8124673383761196410L;
	
	
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
