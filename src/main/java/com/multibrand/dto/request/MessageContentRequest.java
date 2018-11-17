package com.multibrand.dto.request;

import java.util.List;

import com.multibrand.exception.ValidateRequestException;

public class MessageContentRequest extends NRGServicesRequest implements
		BaseContentRequest {

	private String brand;
	private String languageCode;
	private String priorityLevel;
	private String region;


	private List<String> retention;


	private List<String> services;


	private List<String> messages;

	public String getBrand() {
		return brand;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public List<String> getMessages() {
		return messages;
	}

	public String getPriorityLevel() {
		return priorityLevel;
	}


	public String getRegion() {
		return region;
	}


	public List<String> getRetention() {
		return retention;
	}



	public List<String> getServices() {
		return services;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}


	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public void setPriorityLevel(String priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	public void setRegion(String region) {
		this.region = region;
	}


	public void setRetention(List<String> retention) {
		this.retention = retention;
	}

	public void setServices(List<String> services) {
		this.services = services;
	}

	@Override
	public void validateRequest() throws ValidateRequestException {
		// TODO Auto-generated method stub
	}

}
