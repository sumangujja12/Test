package com.multibrand.dto.response;

import java.util.List;

import com.multibrand.dto.EnrollmentHoldDTO;


public class SalesHoldLookupResponse extends  SalesBaseResponse{

	private List<EnrollmentHoldDTO> enrollmentHoldList;	
	private String caNumber;
	private String checkDigit;
	private String bpNumber;
	private String enrollmentStatus;
	
	
	public List<EnrollmentHoldDTO> getEnrollmentHoldList() {
		return enrollmentHoldList;
	}
	public void setEnrollmentHoldList(List<EnrollmentHoldDTO> enrollmentHoldList) {
		this.enrollmentHoldList = enrollmentHoldList;
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
	public String getBpNumber() {
		return bpNumber;
	}
	public void setBpNumber(String bpNumber) {
		this.bpNumber = bpNumber;
	}

	public String getEnrollmentStatus() {
		return enrollmentStatus;
	}
	public void setEnrollmentStatus(String enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}
	
	
}
