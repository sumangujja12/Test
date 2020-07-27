package com.multibrand.dao;

import org.springframework.stereotype.Component;

import com.multibrand.dto.GMDPersonDetailsDTO;
import com.multibrand.dto.GMDServiceLocationDetailsDTO;
import com.multibrand.dto.request.GMDEnrollmentRequest;
@Component
public interface GMDDAO {

	public boolean inserPersonDetails(GMDPersonDetailsDTO enrollRequest);
	
	public boolean insertServiceLocationLocation(GMDServiceLocationDetailsDTO enrollRequest);
	
	public Integer getNextValForSequence(String sequence);
	
	public boolean checkTrackNo(GMDEnrollmentRequest enrollmentRequest);
}
