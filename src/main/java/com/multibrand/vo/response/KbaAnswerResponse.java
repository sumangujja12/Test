package com.multibrand.vo.response;

public class KbaAnswerResponse extends GenericResponse{
	
	private String ssnVerifyDate;
	private String drivingLicenceVerifyDate;
	
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
