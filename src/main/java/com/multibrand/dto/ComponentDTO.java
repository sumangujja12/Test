package com.multibrand.dto;

import java.util.List;

public class ComponentDTO {
	
	private String componentId,topic,page;
	private List<String> dynamicFields;

	public List<String> getDynamicFields() {
		return dynamicFields;
	}

	public void setDynamicFields(List<String> dynamicFields) {
		this.dynamicFields = dynamicFields;
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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
}
