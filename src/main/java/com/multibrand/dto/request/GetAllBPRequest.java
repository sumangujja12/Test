package com.multibrand.dto.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.util.Constants;

public class GetAllBPRequest implements FormEntityRequest, Constants, Serializable {

	
	@NotBlank(groups = BasicConstraint.class)
	private String companyCode;
	
	@NotBlank(groups = BasicConstraint.class)
	private String contactNumber;
	
	
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}



}
