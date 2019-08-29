package com.multibrand.resources;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.ValidationBO;
import com.multibrand.dto.request.ValidateAddressRequest;
import com.multibrand.exception.OEException;
import com.multibrand.util.CommonUtil;

/**
 * 
 * 
 * @author jyogapa1
 * 
 */
@Component
public class ValidationAddressResource extends BaseResource {

	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	/** Object of ValidationBO class. */
	@Autowired
	private ValidationBO validationBO;
	
	/**
	 * @author jyogapa1 (Jenith)
	 * 
	 * Validates API address input parameters basic constraints as follow.
	 * 
	 * <p>
	 * List of constraints to be checked.
	 * </p>
	 * <ul>
	 * <li>1. Not null for required parameters
	 * <li>2. Billing address can either have Street address or PO Box, not both
	 * <ul>
	 * 
	 * @param response
	 * @param message
	 * @param e
	 * @throws OEException
	 */
	@Deprecated
	protected Response validateAddressParametersBasicConstraint(
			ValidateAddressRequest validateAddressRequest) throws OEException {

		String METHOD_NAME = "ValidationAddressResource: validateAddressParametersBasicConstraint(..)";
		logger.debug("Start:" + METHOD_NAME);

		Response errors = null;

		// Set mandatory parameters
		/*Map<String, Object> mandatoryParamList = validationBO
				.buildAddressValidationRequiredParams(validateAddressRequest);
		
		// Set size boundary parameters for State
		Map<String, Object> sizeParamList = validationBO
				.buildAddressValidationSizeParams(validateAddressRequest);

		errors =  validateParameters(mandatoryParamList,
				sizeParamList, 2);

		if (errors == null) {

			errors = this.validateBillingAddressParameters(
					validateAddressRequest.getStreetAddress(),
					validateAddressRequest.getPoBox());
		}*/

		logger.debug("End:" + METHOD_NAME);

		return errors;
	}

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
	protected Response validateBillingAddressParameters(String streetAddress,
			String poBox) {

		String METHOD_NAME = "ValidationAddressResource: validateBillingAddressParameters(..)";
		logger.debug("Start:" + METHOD_NAME);

		Response errors = null;

		if (StringUtils.isNotBlank(streetAddress)
				&& StringUtils.isNotBlank(poBox)) {

			errors = CommonUtil.buildErrorsResponse(BILLING_ADDRESS_ERROR_MESSAGE, STATUS_CODE_ASK);
		}

		logger.debug("End:" + METHOD_NAME);

		return errors;
	}
	
}
