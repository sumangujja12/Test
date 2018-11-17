package com.multibrand.service;

import org.springframework.stereotype.Component;

import com.multibrand.dto.response.RetailServicesResponse;
import com.multibrand.vo.request.CheckReliantCustomerStatusRequest;
import com.multibrand.vo.response.CheckReliantCustomerStatusResponse;
import com.multibrand.vo.response.CheckZipSecurityEligibilityResponse;

@Component
public interface RetailServicesService {
	
	public RetailServicesResponse readHouseAgeAndHHIncome(String addressIdorESID);
	
	public CheckReliantCustomerStatusResponse readReliantCustomerStatus(CheckReliantCustomerStatusRequest request) throws Exception;

	public CheckZipSecurityEligibilityResponse checkZipForSecurityEligibility(String zipCode) throws Exception;
	
}
