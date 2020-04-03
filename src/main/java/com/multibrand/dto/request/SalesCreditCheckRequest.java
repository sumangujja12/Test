package com.multibrand.dto.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.FormatConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.request.validation.ValidDateTime;

public class SalesCreditCheckRequest extends SalesOERequest {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7130908621282785224L;
	
	
	@NotEmpty (groups = BasicConstraint.class,message = "offerCode is empty")
	@Length(min= 8, max = 8, groups = SizeConstraint.class)
	String offerCode;
	
	@ValidDateTime(format = "MMddyyyy", groups = FormatConstraint.class)
	private String mviDate;

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public String getMviDate() {
		return mviDate;
	}

	public void setMviDate(String mviDate) {
		this.mviDate = mviDate;
	}
	
	
	
}
