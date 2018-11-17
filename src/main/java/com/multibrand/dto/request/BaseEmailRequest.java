package com.multibrand.dto.request;

import com.multibrand.exception.ValidateRequestException;


public interface BaseEmailRequest {
	
	public void validateRequest() throws ValidateRequestException;

}
