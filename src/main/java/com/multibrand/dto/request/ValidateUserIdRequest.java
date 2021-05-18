package com.multibrand.dto.request;

import javax.validation.constraints.Size;
import javax.ws.rs.QueryParam;
import com.multibrand.request.validation.NotEmpty;

public class ValidateUserIdRequest extends SalesBaseRequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@QueryParam(value = "userId")
	@NotEmpty
	private String userId;
	
	@QueryParam(value = "bpNumber")
	@Size(min=10 , max = 10)
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