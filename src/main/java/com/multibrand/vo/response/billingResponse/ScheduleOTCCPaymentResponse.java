package com.multibrand.vo.response.billingResponse;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;


@XmlRootElement(name="ScheduleOTCCPaymentResponse")
public class ScheduleOTCCPaymentResponse extends GenericResponse {
	
	private String eTrackingId;
	
	private String errorCode;

	public String geteTrackingId() {
		return eTrackingId;
	}

	public void seteTrackingId(String eTrackingId) {
		this.eTrackingId = eTrackingId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
