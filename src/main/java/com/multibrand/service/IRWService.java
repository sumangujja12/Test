package com.multibrand.service;

import org.springframework.stereotype.Component;

import com.multibrand.dto.request.OAMMessageRequest;
import com.multibrand.dto.response.OAMMessageResponse;

@Component
public interface IRWService {
	
	public OAMMessageResponse readOAMMessage(OAMMessageRequest request);
	
	public OAMMessageResponse writeOAMMessage(OAMMessageRequest request);
	
	public OAMMessageResponse checkOAMMessage(OAMMessageRequest request);

}
