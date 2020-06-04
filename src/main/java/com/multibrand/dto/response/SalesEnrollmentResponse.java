package com.multibrand.dto.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SalesEnrollmentResponse")
public class SalesEnrollmentResponse extends  SalesBaseResponse{
	private String trackingId = null;
	private String idocNumber = null;
	private String caNumber = null;
	private String checkDigit = null;
	private String bpid = null;
	
	public String getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	public String getIdocNumber() {
		return idocNumber;
	}
	public void setIdocNumber(String idocNumber) {
		this.idocNumber = idocNumber;
	}
	public String getCaNumber() {
		return caNumber;
	}
	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}
	public String getCheckDigit() {
		return checkDigit;
	}
	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}
	public String getBpid() {
		return bpid;
	}
	public void setBpid(String bpid) {
		this.bpid = bpid;
	}

}
