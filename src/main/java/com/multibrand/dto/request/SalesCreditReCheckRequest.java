package com.multibrand.dto.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.FormatConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.request.validation.ValidDateTime;

public class SalesCreditReCheckRequest extends SalesCreditCheckRequest {


	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1518355435519472437L;
	

	@NotEmpty
	@Length(max = 20, groups = SizeConstraint.class)
	private String tokenizedSSN;



	public String getTokenizedSSN() {
		return tokenizedSSN;
	}



	public void setTokenizedSSN(String tokenizedSSN) {
		this.tokenizedSSN = tokenizedSSN;
	}
	
	
	
	
	
	
}
