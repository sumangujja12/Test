package com.multibrand.dto.request;

import javax.validation.constraints.Size;

import com.multibrand.request.validation.NotEmpty;

public class SalesCreditReCheckRequest extends SalesCreditCheckRequest {


	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1518355435519472437L;
	

	@NotEmpty
	@Size(max = 20)
	private String tokenizedSSN;



	public String getTokenizedSSN() {
		return tokenizedSSN;
	}



	public void setTokenizedSSN(String tokenizedSSN) {
		this.tokenizedSSN = tokenizedSSN;
	}
	
	
	
	
	
	
}
