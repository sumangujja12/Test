package com.multibrand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.dao.IRWDAO;
import com.multibrand.dto.request.OAMMessageRequest;
import com.multibrand.dto.response.OAMMessageResponse;

@Component
public class IRWServiceImpl implements IRWService{
	
	@Autowired
	private IRWDAO irwDAO;

	@Override
	public OAMMessageResponse readOAMMessage(OAMMessageRequest request) {
		return irwDAO.readOAMMessage(request);
	}

	@Override
	public OAMMessageResponse writeOAMMessage(OAMMessageRequest request) {
		return irwDAO.writeOAMMessage(request);
	}

	@Override
	public OAMMessageResponse checkOAMMessage(OAMMessageRequest request) {
		return irwDAO.checkOAMMessage(request);
	}

}
