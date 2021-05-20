package com.multibrand.dto.request;

import javax.validation.constraints.Size;

import com.multibrand.request.validation.CurrentOrFutureDate;
import com.multibrand.request.validation.NotEmpty;

public class SalesCreditCheckRequest extends SalesOERequest {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7130908621282785224L;
	
	
	@NotEmpty
	@Size(min= 8, max = 8)
	String offerCode;
	
	@CurrentOrFutureDate
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
