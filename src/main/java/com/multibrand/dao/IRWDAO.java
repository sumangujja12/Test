package com.multibrand.dao;

import org.springframework.stereotype.Component;

import com.multibrand.dto.request.OAMMessageRequest;
import com.multibrand.dto.response.OAMMessageResponse;

@Component
public interface IRWDAO {
	
	public OAMMessageResponse readOAMMessage(OAMMessageRequest request);
	
	public OAMMessageResponse writeOAMMessage(OAMMessageRequest request);
	
	public OAMMessageResponse checkOAMMessage(OAMMessageRequest request);

}
