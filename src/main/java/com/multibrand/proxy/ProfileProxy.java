/**
 * 
 */
package com.multibrand.proxy;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.domain.ProfileDomain;
import com.multibrand.domain.ProfileDomainPortBindingStub;
import com.multibrand.domain.UpdateAlertPrefRequest;
import com.multibrand.domain.UpdateAlertPrefResponse;
import com.multibrand.domain.UpdateContactRequest;
import com.multibrand.domain.UpdateContactResponse;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.LoggerUtil;

/**
 * 
 * @author jyogapa1
 * 
 *         THIS PROXY CLASS IS UPDATED WITH ALL NRGWS PROFILE DOMAIN PORT STUBS
 *         ARE AUTOWIRED AS SPRING SINGLETON SCOPE BEANS
 * 
 */
@Component
public class ProfileProxy extends BaseProxy {
	LoggerUtil logger = LoggerUtil.getInstance("ProfileProxy");

	// ~Autowire entries
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;

	@Autowired
	private ProfileDomain profileDomainPortProxy;

	/**
	 * @author jyogapa1
	 * @param request
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public UpdateContactResponse updateContactInfo(
			UpdateContactRequest request, String sessionId) throws Exception {
		String METHOD_NAME = "ProfileProxy: updateContactInfo(..)";
		logger.debug("Start:" + METHOD_NAME);

		UpdateContactResponse response = null;

		try {

			// Call NRGWS ProfileDomain.updateContactInfo web service call
			response = profileDomainPortProxy.updateContactInfo(request);

		} catch (RemoteException re) {
			logger.error(METHOD_NAME
					+ " : Exception while getting data from nrgws:"
					+ re.getMessage());

			throw re;// it is required to throw exception back to BO layer for
						// proper response generation
		} catch (Exception e) {
			logger.error(METHOD_NAME
					+ " : Exception while getting data from nrgws:"
					+ e.getMessage());

			throw e;// it is required to throw exception back to BO layer for
					// proper response generation
		}

		logger.debug("End:" + METHOD_NAME);

		return response;
	}

	/**
	 * To updated the contact detail i.e. email, phone and marketing preferences
	 * 
	 * @param updateContactRequest
	 * @return UpdateContactResponse
	 */
	public UpdateContactResponse updateContactInfoWithAsyncDelay(
			UpdateContactRequest updateContactRequest) {
		UpdateContactResponse contactResponse = null;
		String responseStatus = "";
		long startTime = CommonUtil.getStartTime();
		long delayInMS = 2000L;
		try {
			delayInMS = Integer.parseInt(getEnvMessageReader().getMessage(
					ENV_UPDATE_CRM_ASYNC_DELAY_MS));
			logger.info("updateContact(...) Introducing delay of " + delayInMS
					+ "ms.");
		} catch (Exception ex) {
			logger.error("Error getting env.updatecrm.async.delay.ms "
					+ ex.getMessage());
			delayInMS = 2000L;
		}
		try {
			Thread.sleep(delayInMS); // Adding 2 sec delay by default
		} catch (Exception ex) {
			logger.info("updateContact(...): Error in setting delay for Async call "
					+ ex.getMessage());
		}
		try {
			// Call NRGWS ProfileDomain.updateContactInfo web service call
			contactResponse = getProfileDomainProxy().updateContactInfo(
					updateContactRequest);
			
			if (contactResponse == null) {
				responseStatus = "Call_Fail";
				throw new RuntimeException(
						"updatePhones(..) retured null Object! Throwing back Explicitly");
			}
		} catch (Exception e) {
			throw new RuntimeException("updatePhones Failed :", e);
		}

		/*finally {
			try {
				// utilityLogger.logTransaction(UPDATE_CONTACT_INFO_LOGGING,
				// false, updateContactRequest, contactResponse, responseStatus,
				// getElapsedTime(startTime));
			} catch (Exception e) {
				logger.error("Exception While logging UPDATE_CONTACT_INFO_LOGGING::"
						+ e.getMessage());
			}
		}*/

		return contactResponse;
	}

	/**
	 * @author jyogapa1
	 * @param request
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public UpdateAlertPrefResponse updateAlertPreferences(
			UpdateAlertPrefRequest request) throws Exception {
		String METHOD_NAME = "ProfileProxy: updateAlertPreferences(..)";
		logger.debug("Start:" + METHOD_NAME);

		UpdateAlertPrefResponse response = null;

		try {

			// Call NRGWS ProfileDomain.updateContactInfo web service call
			response = getProfileDomainProxy().updateAlertPreferences(request);

		} catch (RemoteException re) {
			logger.error(METHOD_NAME
					+ " : Exception while getting data from nrgws:"
					+ re.getMessage());

			throw re;// it is required to throw exception back to BO layer for
						// proper response generation
		} catch (Exception e) {
			logger.error(METHOD_NAME
					+ " : Exception while getting data from nrgws:"
					+ e.getMessage());

			throw e;// it is required to throw exception back to BO layer for
					// proper response generation
		}

		logger.debug("End:" + METHOD_NAME);

		return response;
	}

	/**
	 * This will return ProfileDomainProxy and set EndPoint URL
	 * 
	 * @return profileDomainProxy The ProfileDomainProxy Object
	 */
	private ProfileDomain getProfileDomainProxy() {

		if (this.profileDomainPortProxy == null) {
			logger.info("ProfileDomainPortProxy is null coz of Asynchronize call: Loading new instance of ProfileDomainPortProxy with "
					+ PROFILE_SERVICE_ENDPOINT_URL);

			return (ProfileDomain) getServiceProxy(
					ProfileDomainPortBindingStub.class,
					PROFILE_SERVICE_ENDPOINT_URL);
		}

		return profileDomainPortProxy;

	}

}