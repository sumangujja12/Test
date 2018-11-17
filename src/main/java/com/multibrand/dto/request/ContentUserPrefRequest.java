package com.multibrand.dto.request;

import org.apache.commons.lang.StringUtils;

import com.multibrand.exception.ValidateRequestException;

public class ContentUserPrefRequest extends NRGServicesRequest implements BaseContentRequest {
	
	private String bpNumber, caNumber, coNumber, esiid, userPrefCode;
	
	
	public String getBpNumber() {
		return bpNumber;
	}


	public void setBpNumber(String bpNumber) {
		this.bpNumber = bpNumber;
	}

	
	public String getCaNumber() {
		return caNumber;
	}


	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}


	public String getEsiid() {
		return esiid;
	}


	public void setEsiid(String esiid) {
		this.esiid = esiid;
	}


	public String getUserPrefCode() {
		return userPrefCode;
	}


	public void setUserPrefCode(String userPrefCode) {
		this.userPrefCode = userPrefCode;
	}
	
	
	public String getCoNumber() {
		return coNumber;
	}


	public void setCoNumber(String coNumber) {
		this.coNumber = coNumber;
	}


	@Override
	public void validateRequest() throws ValidateRequestException {
		
		if(StringUtils.isBlank(this.bpNumber)){throw new ValidateRequestException("BP NUMBER SHOULD NOT BE EMPTY");}
		if(StringUtils.isBlank(this.caNumber)){throw new ValidateRequestException("CA NUMBER SHOULD NOT BE EMPTY");}
		if(StringUtils.isBlank(this.esiid)){throw new ValidateRequestException("ESIID SHOULD NOT BE EMPTY");}
	}

}
