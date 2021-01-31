/**
 * 
 */
package com.multibrand.dto.request;

import javax.ws.rs.QueryParam;

import org.hibernate.validator.constraints.Length;

import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.SizeConstraint;

public class ProspectEFLRequest {
	
	private static final long serialVersionUID = 1L;

	@QueryParam(value = "enrollDate")
	private String enrollDate;

	@QueryParam(value = "enrollFrom")
	private String enrollFrom;

	@QueryParam(value = "offerCode")
	private String offerCode;

	@QueryParam(value = "smartCode")
	private String smartCode;
	
	@QueryParam(value = "companyCode")
	private String companyCode;
	
	@QueryParam(value = "nrgBrand")
	private String nrgBrand;
	
	@QueryParam(value = "errorMsg")
	private String errorMsg;

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getEnrollFrom() {
		return enrollFrom;
	}

	public void setEnrollFrom(String enrollFrom) {
		this.enrollFrom = enrollFrom;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public String getSmartCode() {
		return smartCode;
	}

	public void setSmartCode(String smartCode) {
		this.smartCode = smartCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getNrgBrand() {
		return nrgBrand;
	}

	public void setNrgBrand(String nrgBrand) {
		this.nrgBrand = nrgBrand;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


}