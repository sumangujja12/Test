/**
 * 
 */
package com.multibrand.proxy;

import java.rmi.RemoteException;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.domain.EnrollmentHoldInfoRequest;
import com.multibrand.domain.EnrollmentHoldInfoResponse;
import com.multibrand.domain.EsidAddressRequest;
import com.multibrand.domain.EsidAddressResponse;
import com.multibrand.domain.NewCreditScoreRequest;
import com.multibrand.domain.NewCreditScoreResponse;
import com.multibrand.domain.OEDomain;
import com.multibrand.domain.PermitCheckRequest;
import com.multibrand.domain.PermitCheckResponse;
import com.multibrand.domain.SubmitEnrollRequest;
import com.multibrand.domain.SubmitEnrollResponse;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.util.Constants;
import com.multibrand.util.LoggerUtil;

/**
 * 
 * @author jyogapa1
 * 
 *         THIS PROXY CLASS IS UPDATED WITH ALL NRGWS OE DOMAIN PORT STUBS ARE
 *         AUTOWIRED AS SPRING SINGLETON SCOPE BEANS
 * 
 */
@Component
public class OEProxy extends BaseAbstractService {
	LoggerUtil logger = LoggerUtil.getInstance("OEProxy");

	// ~Autowire entries
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;

	@Autowired
	private OEDomain oeDomainPortProxy;

	/**
	 * @author jyogapa1
	 * @param request
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public PermitCheckResponse checkPermitRequirment(
			PermitCheckRequest request, String sessionId) throws Exception {
		String METHOD_NAME = "OEProxy: checkPermitRequirement(..)";
		logger.debug("Start:" + METHOD_NAME);

		PermitCheckResponse response = null;

		try {

			// Call NRGWS OEDomain.checkPermitRequirment
			response = oeDomainPortProxy.checkPermitRequirment(request);

		} catch (RemoteException re) {
			logger.error(METHOD_NAME
					+ " : Exception while getting data from ccs:"
					, re);

			throw re;// it is required to throw exception back to BO layer for
						// proper response generation
		} catch (Exception e) {
			logger.error(METHOD_NAME
					+ " : Exception while getting data from ccs:"
					, e);

			throw e;// it is required to throw exception back to BO layer for
					// proper response generation
		}

		logger.debug("End:" + METHOD_NAME);

		return response;
	}

	/**
	 * @author jyogapa1
	 * @param submitEnrollRequest
	 * @return
	 * @throws Exception
	 */
	public SubmitEnrollResponse submitEnrollment(
			SubmitEnrollRequest submitEnrollRequest) throws Exception {
		String METHOD_NAME = "OEProxy: submitEnrollment(..)";
		logger.debug("Start:" + METHOD_NAME);

		SubmitEnrollResponse response = null;

		try {

			// Call NRGWS OEDomain.submitEnrollment web service call
			response = oeDomainPortProxy.submitEnrollment(submitEnrollRequest);

		} catch (RemoteException re) {
			logger.error(METHOD_NAME
					+ " : Exception while getting data from ccs:"
					, re);

			throw re;// it is required to throw exception back to BO layer for
						// proper response generation
		} catch (Exception e) {
			logger.error(METHOD_NAME
					+ " : Exception while getting data from ccs:"
					, e);

			throw e;// it is required to throw exception back to BO layer for
					// proper response generation
		}

		logger.debug("End:" + METHOD_NAME);

		return response;
	}

	/**
	 * @param creditScoreRequest
	 * @return NewCreditScoreResponse
	 * @throws Exception
	 */
	public NewCreditScoreResponse getNewCreditScore(
			NewCreditScoreRequest creditScoreRequest) throws Exception {
		String METHOD_NAME = "OEProxy: getNewCreditScore(..)";
		logger.debug("Start:" + METHOD_NAME);
		NewCreditScoreResponse response = null;

		try {

			// Call NRGWS OEDomain.getNewCreditScore
			response = oeDomainPortProxy.getNewCreditScore(creditScoreRequest);
		
		} catch (RemoteException re) {
			logger.error(METHOD_NAME
					+ " : Exception while getting data from ccs:"
					, re);

			throw re;// it is required to throw exception back to BO layer for
						// proper response generation
		} catch (Exception e) {
			logger.error(METHOD_NAME
					+ " : Exception while getting data from ccs:"
					, e);

			throw e;// it is required to throw exception back to BO layer for
					// proper response generation
		}

		logger.debug("End:" + METHOD_NAME);

		return response;
	}
	
	public EnrollmentHoldInfoResponse getEnrollmentHoldInfo(EnrollmentHoldInfoRequest request) {
		EnrollmentHoldInfoResponse response = new EnrollmentHoldInfoResponse();
		try{
				
		logger.debug("getEnrollmentHoldInfo :: inside function in enrollmentService");
					
		
		logger.info("EnrollmentHoldInfoRequest  is " + ReflectionToStringBuilder.toString(request,
				ToStringStyle.MULTI_LINE_STYLE));
		 
		response =  oeDomainPortProxy.getEnrollmentHoldInformation(request);
		logger.info("EnrollmentHoldInfoResponse:: "+ReflectionToStringBuilder.toString(response,
				ToStringStyle.MULTI_LINE_STYLE));
		}
		catch(Exception e)
		{									
			logger.error("inside OEProxy:: in getEnrollmentHoldInfo() ::getEnrollmentHoldInfo Call Failed with error ::",e);
			
		}
		
		return response;
	}

	public EsidAddressResponse getESIDAddress(EsidAddressRequest esidAddressRequest) {
		EsidAddressResponse esidAddressResponse = null;
		
		try {
			esidAddressResponse = oeDomainPortProxy.getESIDAddress(esidAddressRequest);

		} catch (Exception e) {
			logger.error("Error validateEsiid : " + e.getMessage());
			esidAddressResponse = new EsidAddressResponse();
			esidAddressResponse.setStrErrCode("SYSTEM_ERROR");
		}
		
		return esidAddressResponse;
		
	}
}