package com.multibrand.resources;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.multibrand.exception.OEException;
import com.multibrand.util.CommonUtil;
import com.multibrand.vo.response.GenericResponse;

/**
 * 
 * 
 * @author jyogapa1
 * 
 */
@Component
public class ValidationAddressResource extends BaseResource {

	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
		
	
	/**
	 * @author jyogapa1 (Jenith)
	 * 
	 * Validates API address input parameters basic constraints as follow.
	 * 
	 * <p>
	 * List of constraints to be checked.
	 * </p>
	 * <ul>
	 * <li>1. Billing address can either have Street address or PO Box, not both
	 * <ul>
	 * 
	 * @param response
	 * @param message
	 * @param e
	 * @throws OEException
	 */
	protected ResponseEntity<GenericResponse> validateBillingAddressParameters(String streetAddress,
			String poBox) {

		String METHOD_NAME = "ValidationAddressResource: validateBillingAddressParameters(..)";
		logger.debug("Start:" + METHOD_NAME);

		ResponseEntity<GenericResponse> errors = null;

		if (StringUtils.isNotBlank(streetAddress)
				&& StringUtils.isNotBlank(poBox)) {

			errors = CommonUtil.buildErrorsResponse(BILLING_ADDRESS_ERROR_MESSAGE, STATUS_CODE_ASK);
		}

		logger.debug("End:" + METHOD_NAME);

		return errors;
	}
	
}
