package com.multibrand.dto.request;

import com.multibrand.exception.ValidateRequestException;

public class ComponentByItemIdRequest  extends NRGServicesRequest implements BaseContentRequest {

	@Override
	public void validateRequest() throws ValidateRequestException {
		// TODO Auto-generated method stub
		
	}
	
	private String brand, languageCode, componentId, topic;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	

}
