package com.multibrand.dto.request;


import com.multibrand.request.validation.NotEmpty;

public class ValidateCARequest extends SalesBaseRequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String contractNumber;
	
	@NotEmpty
	private String contractAccountNumber;
	
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