package com.multibrand.bo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.service.BaseAbstractService;
import com.multibrand.service.RetailServicesService;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.CheckReliantCustomerStatusRequest;
import com.multibrand.vo.response.CheckReliantCustomerStatusResponse;
import com.multibrand.vo.response.CheckZipSecurityEligibilityResponse;

@Component
public class RetailServicesBO extends BaseAbstractService implements Constants {
	
	@Autowired
	private RetailServicesService retailServicesService;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	public CheckReliantCustomerStatusResponse readReliantCustomerStatus(CheckReliantCustomerStatusRequest request) {
		
		CheckReliantCustomerStatusResponse response = new CheckReliantCustomerStatusResponse();
		try {
			response = retailServicesService.readReliantCustomerStatus(request);
		}  catch (Exception ex) {
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			logger.error("Error occured while reading reliant customer status from CCS.", ex);
		}
		return response;
	}

	public CheckZipSecurityEligibilityResponse checkZipForSecurityEligibility(String zipCode) {
		CheckZipSecurityEligibilityResponse response = new CheckZipSecurityEligibilityResponse();
		try {
			response = retailServicesService.checkZipForSecurityEligibility(zipCode);
		}  catch (Exception ex) {
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			logger.error("Error occured while reading security eligibility for the given zip code from CCS.", ex);
		}
		return response;
	}
	
}
