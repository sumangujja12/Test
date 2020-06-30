package com.multibrand.vo.request;

import com.multibrand.dto.request.BaseRequest;

public class MaintenanceScheduleRequest extends BaseRequest {
	
	private String applicationType;
	private String brandId;
	
	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

}
