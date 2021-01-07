package com.multibrand.dto.request;

import javax.ws.rs.QueryParam;

import com.multibrand.request.validation.NotEmpty;

public class ValidateEsidRequest extends SalesBaseRequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@QueryParam(value ="esid") 
	@NotEmpty
	private String esid;

	public String getEsid() {
		return esid;
	}

	public void setEsid(String esid) {
		this.esid = esid;
	}

}