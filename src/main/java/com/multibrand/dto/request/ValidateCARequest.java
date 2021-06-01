package com.multibrand.dto.request;


import javax.validation.constraints.Size;
import javax.ws.rs.QueryParam;

import com.multibrand.request.validation.NotEmpty;

public class ValidateCARequest extends SalesBaseRequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@QueryParam(value = "contractNumber")
	@NotEmpty
	@Size(min=10 , max = 10)
	private String contractNumber;
	
	@QueryParam(value = "contractAccountNumber")
	@NotEmpty
	@Size(min=12 , max = 12)
	private String contractAccountNumber;
	
	@QueryParam(value = "checkDigit")
	@NotEmpty
	private String checkDigit;

	

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getContractAccountNumber() {
		return contractAccountNumber;
	}

	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}

	public String getCheckDigit() {
		return checkDigit;
	}

	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}

	


}