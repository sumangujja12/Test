package com.multibrand.vo.response;

import com.multibrand.dto.response.SalesBaseResponse;

public class KbaAnswerResponse extends SalesBaseResponse{
	
	private String ssnVerifyDate;
	private String drivingLicenceVerifyDate;
	private String decision;
	
	
	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}
	public String getSsnVerifyDate() {
		return ssnVerifyDate;
	}
	public void setSsnVerifyDate(String ssnVerifyDate) {
		this.ssnVerifyDate = ssnVerifyDate;
	}
	public String getDrivingLicenceVerifyDate() {
		return drivingLicenceVerifyDate;
	}
	public void setDrivingLicenceVerifyDate(String drivingLicenceVerifyDate) {
		this.drivingLicenceVerifyDate = drivingLicenceVerifyDate;
	}

	
}
