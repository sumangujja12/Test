package com.multibrand.dto.request;

import java.util.List;

import com.multibrand.dto.ComponentDTO;
import com.multibrand.exception.ValidateRequestException;

public class ComponentByItemIdsRequest extends NRGServicesRequest implements BaseContentRequest {

	@Override
	public void validateRequest() throws ValidateRequestException {
		// TODO Auto-generated method stub
	}
	
	private String brand;
	private String languageCode;
	private List<ComponentDTO> componentArray;
	
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
	public List<ComponentDTO> getComponentArray() {
		return componentArray;
	}
	public void setComponentArray(List<ComponentDTO> componentArray) {
		this.componentArray = componentArray;
	}
	
	

}
