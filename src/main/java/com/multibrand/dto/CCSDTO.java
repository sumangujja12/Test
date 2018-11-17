package com.multibrand.dto;

import java.util.List;

public class CCSDTO {
	
	private String service, flag;
	private List<String> dynamicFields;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<String> getDynamicFields() {
		return dynamicFields;
	}

	public void setDynamicFields(List<String> dynamicFields) {
		this.dynamicFields = dynamicFields;
	}
	
}
