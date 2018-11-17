/**
 * 
 */
package com.multibrand.proxy;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.domain.ActEbillRequest;
import com.multibrand.domain.ActEbillResponse;
import com.multibrand.domain.BillingDomain;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.LoggerUtil;

/**
 * 
 * @author jyogapa1
 * 
 *         THIS PROXY CLASS IS UPDATED WITH ALL NRGWS BILLING DOMAIN PORT STUBS ARE
 *         AUTOWIRED AS SPRING SINGLETON SCOPE BEANS
 * 
 */
@Component
public class BillingProxy {
	LoggerUtil logger = LoggerUtil.getInstance("BillingProxy");

	// ~Autowire entries
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;

	@Autowired
	private BillingDomain billingDomainPortProxy;

	/**
	 * @author jyogapa1
	 * @param request
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public ActEbillResponse activateEbill(
			ActEbillRequest request, String sessionId) throws Exception {
		String METHOD_NAME = "BillingProxy: activateEbill(..)";
		logger.debug("Start:" + METHOD_NAME);

		ActEbillResponse response = null;

		try {

			// Call NRGWS BillingDomain.activateEbill web service call
			response = billingDomainPortProxy.activateEbill(request);

		} catch (RemoteException re) {
			logger.error(METHOD_NAME
					+ " : Exception while getting data from ccs:"
					+ re.getMessage());

			throw re;// it is required to throw exception back to BO layer for
						// proper response generation
		} catch (Exception e) {
			logger.error(METHOD_NAME
					+ " : Exception while getting data from ccs:"
					+ e.getMessage());

			throw e;// it is required to throw exception back to BO layer for
					// proper response generation
		}

		logger.debug("End:" + METHOD_NAME);

		return response;
	}
	
}