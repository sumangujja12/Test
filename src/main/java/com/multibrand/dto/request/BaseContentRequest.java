package com.multibrand.dto.request;

import com.multibrand.exception.ValidateRequestException;

public interface BaseContentRequest {
	
	public void validateRequest() throws ValidateRequestException;

}
