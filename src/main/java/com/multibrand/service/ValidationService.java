package com.multibrand.service;

import java.rmi.RemoteException;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multibrand.domain.ValidateCustReferralIdResponse;
import com.multibrand.domain.ValidatePosIdKBARequest;
import com.multibrand.domain.ValidatePosIdKBAResponse;
import com.multibrand.domain.ValidatePosIdRequest;
import com.multibrand.domain.ValidatePosIdResponse;
import com.multibrand.domain.ValidateReferralIdRequest;
import com.multibrand.domain.ValidationDomain;
import com.multibrand.domain.ValidationDomainPortBindingStub;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.XmlUtil;
import com.reliant.domain.AddressValidateRequest;
import com.reliant.domain.AddressValidateResponse;
import com.reliant.domain.AddressValidationDomain;
import com.reliant.domain.AddressValidationDomainPortBindingStub;


/**
 * 
 * @author rbansal30
 *
 * This class is responsible for fetching information from Redbull Service ValidationDomain 
 */

@Service
public class ValidationService extends BaseAbstractService {
	
	/**
	 * This will return ValidationDomainProxy and set EndPoint URL
	 * @return ValidationDomainProxy The ValidationDomainProxy Object
	 */
	
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	
	protected AddressValidationDomain getAddressValidationDomainProxy(){
        
		return (AddressValidationDomain) getServiceProxy(AddressValidationDomainPortBindingStub.class,
				ADDRESS_VALIDATION_SERVICE_ENDPOINT_URL);
	}
	
	/**
	 * @author rbansal30
	 * @param addressValidateRequest 		com.multibrand.domain.AddressValidateRequest
	 * @return addressValidateResponse		com.multibrand.domain.AddressValidateResponse
	 * @throws Exception 
	 */
	public AddressValidateResponse validateBillingAddress(AddressValidateRequest addressValidateRequest, String companyCode, String sessionId) throws Exception{		
		logger.debug("ValidationService.validateBillingAddress :::: Start");
		AddressValidationDomain proxy = getAddressValidationDomainProxy();
		long startTime = CommonUtil.getStartTime();
		AddressValidateResponse addressValidateResponse = null;
		try{
			addressValidateResponse= proxy.validateAddress(addressValidateRequest);
		}catch(RemoteException ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("validateBillingAddress", false, addressValidateRequest,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			logger.debug(XmlUtil.pojoToXML(addressValidateRequest));
			throw ex;// it is required to throw exception back to BO layer for proper response generation
		}catch(Exception ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("validateBillingAddress", false, addressValidateRequest,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			logger.debug(XmlUtil.pojoToXML(addressValidateRequest));
			throw ex;// it is required to throw exception back to BO layer for proper response generation
		}
		utilityloggerHelper.logTransaction("validateBillingAddress", false, addressValidateRequest,addressValidateResponse, addressValidateResponse.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		logger.debug(XmlUtil.pojoToXML(addressValidateRequest));
    	logger.debug(XmlUtil.pojoToXML(addressValidateResponse));
    	logger.debug("ValidationService.validateBillingAddress :::: End");
		return addressValidateResponse;
	}
	
	//START ONLINE AFFILIATES PROJECT - JSINGH1
	
	
		/**
		 * This will return ValidationDomain and set EndPoint URL
		 * @return proxy The ValidationDomain Object
		 */
		protected ValidationDomain getValidationServiceProxy() {
			return (ValidationDomain) getServiceProxy(ValidationDomainPortBindingStub.class,
					VALIDATION_DOMAIN_END_POINT_URL_JNDI_NAME);
		}
		
		
		
		public ValidatePosIdResponse validatePosId(ValidatePosIdRequest validatePosIdReq) throws Exception{
		
			ValidatePosIdResponse response= new ValidatePosIdResponse();
			String responseStatus=null;
			long startTime=CommonUtil.getStartTime();
			logger.debug(" START *******ValidationService:: validatePosID API**********");
			try{
				logger.debug("inside service validatePosId:: inside try");
				ValidationDomain proxy = getValidationServiceProxy();

				response = proxy.validatePosID(validatePosIdReq);
				logger.debug("inside service validatePosId:: after call ::"+CommonUtil.doRender(validatePosIdReq));
				logger.debug("inside validatePosId:: response is :: "+CommonUtil.doRender(response));

				if(StringUtils.isNotBlank(response.getStrErroMessage())){
					responseStatus = response.getStrErroMessage();
				}
			}
			catch(Exception e )
			{			
				logger.error("Exception in ValidatePosIdResponse.validatePosId :: ",e);
			}
			
			finally{
				try{
					 utilityloggerHelper.logTransaction("ValidatePosId", false, validatePosIdReq, response, responseStatus, CommonUtil.getElapsedTime(startTime),"","",validatePosIdReq.getStrCompanyCode());
					}catch(Exception e){
						logger.error("Exception While logging::", e);
					}
			}
			
			
			return response;
			
		}
		
		public ValidatePosIdKBAResponse validatePosIdWihKBA(ValidatePosIdKBARequest validatePosIdKBAReq) throws Exception{
			
			ValidatePosIdKBAResponse response= new ValidatePosIdKBAResponse();
			String responseStatus=null;
			long startTime=CommonUtil.getStartTime();
			logger.debug(" START *******ValidationService:: validatePosIdWihKBA API**********");
			try{
				logger.debug("inside service validatePosIdWihKBA:: inside try");
				ValidationDomain proxy = getValidationServiceProxy();

				response = proxy.validatePosidWithKBA(validatePosIdKBAReq);
				logger.debug("inside service validatePosIdWihKBA:: after call ::"+CommonUtil.doRender(validatePosIdKBAReq));
				logger.debug("inside validatePosIdWihKBA:: response is :: "+CommonUtil.doRender(response));

				if(StringUtils.isNotBlank(response.getStrErroMessage())){
					responseStatus = response.getStrErroMessage();
				}
			}
			catch(Exception e )
			{			
				logger.error("Exception in validatePosIdWihKBA.validatePosId :: ",e);
			}
			
			finally{
				try{
					 utilityloggerHelper.logTransaction("ValidatePosId", false, validatePosIdKBAReq, response, responseStatus, CommonUtil.getElapsedTime(startTime),"","",validatePosIdKBAReq.getCompanyCode());
					}catch(Exception e){
						logger.error("Exception While logging::", e);
					}
			}
						
			return response;
			
		}
		
		
		//END ONLINE AFFILIATES PROJECT - JSINGH1
	
		//START -- Security and Services Sprint 2
		
		public ValidateCustReferralIdResponse validateReferralId(ValidateReferralIdRequest validateReferralIdRequest) throws Exception{
			ValidateCustReferralIdResponse response= new ValidateCustReferralIdResponse();
			String responseStatus=null;
			long startTime=CommonUtil.getStartTime();
			logger.debug(" START *******ValidationService:: validateReferralId API**********");
			try {
				logger.debug("inside service validateReferralId:: inside try");
				ValidationDomain proxy = getValidationServiceProxy();

				response = proxy.validateReferralId(validateReferralIdRequest);
				logger.debug("inside service validateReferralId:: after call ::"+CommonUtil.doRender(validateReferralIdRequest));
				logger.debug("inside validateReferralId:: response is :: "+CommonUtil.doRender(response));

				if(StringUtils.isNotBlank(response.getErrMessage())){
					responseStatus = response.getErrMessage();
				}
			} catch(Exception e ) {			
				logger.error("Exception in ValidationService.validateReferralId :: ",e);
			} finally {
				try {
					 utilityloggerHelper.logTransaction("validateReferralId", false, validateReferralIdRequest, response, responseStatus, CommonUtil.getElapsedTime(startTime),"","",validateReferralIdRequest.getCompanyCode());
					} catch(Exception e){
						logger.error("Exception While logging::", e);
					}
			}
			
			return response;
		}
		//END -- Security and Services Sprint 2
	
}
