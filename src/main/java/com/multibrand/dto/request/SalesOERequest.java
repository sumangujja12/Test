package com.multibrand.dto.request;

import org.springframework.stereotype.Component;

import com.multibrand.request.validation.NotEmpty;

@Component
public class SalesOERequest extends SalesBaseRequest {

	private static final long serialVersionUID = -8124673383761196410L;	
	
	@NotEmpty
	private String trackingId;
	@NotEmpty
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
