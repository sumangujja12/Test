package com.multibrand.dto.request;


import com.multibrand.request.validation.NotEmpty;

public class ValidateUserIdRequest extends SalesBaseRequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String userId;
	
	@NotEmpty
	private String bpNumber;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBpNumber() {
		return bpNumber;
	}

	public void setBpNumber(String bpNumber) {
		this.bpNumber = bpNumber;
	}
	
	

	
	


}