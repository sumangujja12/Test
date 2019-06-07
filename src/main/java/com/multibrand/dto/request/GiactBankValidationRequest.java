package com.multibrand.dto.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;

/*
 * Start | US19653 | MBAR: Sprint 23 -GIACT REST IMPL : validate bank details  | Jyothi | 5/31/2019
 */

public class GiactBankValidationRequest implements FormEntityRequest, Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 10, groups = SizeConstraint.class)
	private String trackingId;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 12, groups = SizeConstraint.class)
	private String caNumber;
	
	@NotBlank(groups = BasicConstraint.class)
	private String tokenizedBankAccountNumber; 
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 9, groups = SizeConstraint.class)
	private String tokenizedBankRoutingNumber; 
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 4, groups = SizeConstraint.class)
	private String companyCode;

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getCaNumber() {
		return caNumber;
	}

	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}

	public String getTokenizedBankAccountNumber() {
		return tokenizedBankAccountNumber;
	}

	public void setTokenizedBankAccountNumber(String tokenizedBankAccountNumber) {
		this.tokenizedBankAccountNumber = tokenizedBankAccountNumber;
	}

	public String getTokenizedBankRoutingNumber() {
		return tokenizedBankRoutingNumber;
	}

	public void setTokenizedBankRoutingNumber(String tokenizedBankRoutingNumber) {
		this.tokenizedBankRoutingNumber = tokenizedBankRoutingNumber;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
}
