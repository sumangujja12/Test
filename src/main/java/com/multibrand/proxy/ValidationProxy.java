/**
 * 
 */
package com.multibrand.proxy;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.LoggerUtil;
import com.reliant.domain.AddressValidateRequest;
import com.reliant.domain.AddressValidateResponse;
import com.reliant.domain.AddressValidationDomain;

/**
 * 
 * @author jyogapa1
 * 
 *         THIS PROXY CLASS IS UPDATED WITH ALL NRGWS RELIANT ADDRESS VALIDATION DOMAIN PORT STUBS
 *         ARE AUTOWIRED AS SPRING SINGLETON SCOPE BEANS
 * 
 */
@Component
public class ValidationProxy extends BaseProxy {
	LoggerUtil logger = LoggerUtil.getInstance("ProfileProxy");

	// ~Autowire entries
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;

	@Autowired
	private AddressValidationDomain addressvalidationDomainPortProxy;

	/**
	 * @author jyogapa1
	 * 
	 * Validates address using Trillium from NRGWS.
	 * 
	 * @param addressValidateRequest
	 * @return
	 * @throws Exception
	 */
	public AddressValidateResponse validateAddress(AddressValidateRequest addressValidateRequest) throws Exception {
		String METHOD_NAME = "ValidationProxy: validateAddress(..)";
		logger.debug("Start:" + METHOD_NAME);

		AddressValidateResponse response = null;

		try {

			// Call NRGWS ProfileDomain.updateContactInfo web service call
			response = addressvalidationDomainPortProxy.validateAddress(addressValidateRequest);

		} catch (RemoteException re) {
			logger.error(METHOD_NAME
					+ " : Exception while getting data from NRGWS:"
					, re);

			throw re;// it is required to throw exception back to BO layer for
						// proper response generation
		} catch (Exception e) {
			logger.error(METHOD_NAME
					+ " : Exception while getting data from NRGWS:"
					, e);

			throw e;// it is required to throw exception back to BO layer for
					// proper response generation
		}

		logger.debug("End:" + METHOD_NAME);

		return response;
	}

}