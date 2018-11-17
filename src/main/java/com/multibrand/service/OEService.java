package com.multibrand.service;

import java.rmi.RemoteException;
import java.text.MessageFormat;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.multibrand.domain.BpMatchCCSRequest;
import com.multibrand.domain.BpMatchCCSResponse;
import com.multibrand.domain.OEDomain;
import com.multibrand.domain.OEDomainPortBindingStub;
import com.multibrand.domain.OetdspRequest;
import com.multibrand.domain.OfferPricingRequest;
import com.multibrand.domain.PermitCheckRequest;
import com.multibrand.domain.PermitCheckResponse;
import com.multibrand.domain.PromoOfferRequest;
import com.multibrand.domain.PromoOfferResponse;
import com.multibrand.dto.request.UpdateETFFlagToCRMRequest;
import com.multibrand.dto.response.UpdateETFFlagToCRMResponse;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.XmlUtil;

/**
 * 
 * @author vsood30
 *
 * This class is responsible for fetching information from Redbull Service OEDomain 
 */

@Service
public class OEService extends BaseAbstractService {
	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	/**
	 * This will return SwapDomainProxy and set EndPoint URL
	 * @return swapDomainProxy The SwapDomainProxy Object
	 */
	protected OEDomain getOEDomainProxy(){
		return (OEDomain) getServiceProxy(OEDomainPortBindingStub.class, OE_SERVICE_ENDPOINT_URL);
	}
	
	/**
	 * getOfferPricingFromCCS CCS service call
	 * @param offerPricingRequest OfferPricingRequest
	 * @return PromoOfferResponse
	 * @throws RemoteException
	 */
	public PromoOfferResponse getOfferPricingFromCCS(OfferPricingRequest offerPricingRequest) throws RemoteException{
		logger.debug("OEService.getOfferPricingFromCCS() Start");
		PromoOfferResponse promoOfferResponse = null;
		try {
			promoOfferResponse = null;
			OEDomain proxy = getOEDomainProxy();
			promoOfferResponse=proxy.getOfferPricingFromCCS(offerPricingRequest);
		} catch (Exception e) {
			logger.error("getOfferPricingFromCCS : Exception while fetching Offers:", e);
		}	
		logger.debug("OEService.getOfferPricingFromCCS() End");
		return promoOfferResponse; 
	}
	
	/**
	 * getPromoOffers CCS service call
	 * @param promoOfferRequest PromoOfferRequest
	 * @return PromoOfferResponse
	 * @throws RemoteException
	 */
	public PromoOfferResponse getOfferWithPricingFromCCS(PromoOfferRequest promoOfferRequest,String companyCode, String sessionId) throws RemoteException {
		logger.debug("OEService.getOfferWithPricingFromCCS() Start");
		PromoOfferResponse promoOfferResponse = null;
		try {
			OEDomain proxy = getOEDomainProxy();
			long startTime = CommonUtil.getStartTime();
			promoOfferResponse=proxy.getPromoOffers(promoOfferRequest);
			 this.utilityloggerHelper.logTransaction("getOfferWithPricingFromCCS", false, promoOfferRequest, promoOfferResponse, promoOfferResponse.getStrErrMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			 if(logger.isDebugEnabled()){
				 logger.debug(XmlUtil.pojoToXML(promoOfferRequest));
				 logger.debug(XmlUtil.pojoToXML(promoOfferResponse));
			 }
		} catch (Exception e) {
			logger.error("getOfferWithPricingFromCCS : Exception while fetching Offers:", e);
		}	
		logger.debug("OEService.getOfferWithPricingFromCCS() End");
		return promoOfferResponse;
	}
	
	
	/**
	   * @author ahanda1
	   * @param request
	   * @param sessionId
	   * @return
	 * @throws Exception 
	   */
	  public String getTDSPSpecificCalendarDates(OetdspRequest request, String sessionId) throws Exception
	  {
	    this.logger.debug("OEService.getTDSPSpecificCalendarDates start");
	    String response = null;
	    
	        OEDomain proxyclient = getOEDomainProxy();
	        long startTime = CommonUtil.getStartTime();
	 try{
	        response = proxyclient.getTDSPSpecificCalendarDates(request);
	        this.utilityloggerHelper.logTransaction("getTDSPSpecificCalendarDates", false, request, response,"", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getStrCompanyCode());
	        if(logger.isDebugEnabled()){
		        logger.debug(XmlUtil.pojoToXML(request));
		    	logger.debug(XmlUtil.pojoToXML(response));
	        }
	    }
	 catch (RemoteException e) {
	    	this.logger.error("OEService.getTDSPSpecificCalendarDates : Exception while getting data from ccs:", e);
	    	this.utilityloggerHelper.logTransaction("getTDSPSpecificCalendarDates", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getStrCompanyCode());
	    	if(logger.isDebugEnabled())
	    		logger.debug(XmlUtil.pojoToXML(request));
	        throw e;// it is required to throw exception back to BO layer for proper response generation
	    }
	    catch (Exception e) {
	    	this.logger.error("OEService.getTDSPSpecificCalendarDates : Exception while getting data from ccs:", e);
	    	this.utilityloggerHelper.logTransaction("getTDSPSpecificCalendarDates", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getStrCompanyCode());
	    	if(logger.isDebugEnabled())
	    		logger.debug(XmlUtil.pojoToXML(request));
	        throw e;// it is required to throw exception back to BO layer for proper response generation
	    }

	    this.logger.debug("OEService.getTDSPSpecificCalendarDates end");
	    return response;
	  } 
	
	  
	  /**
	   * @author ahanda1
	   * @param request
	   * @param sessionId
	   * @return
	 * @throws Exception 
	   */
	  public PermitCheckResponse checkPermitRequirment(PermitCheckRequest request, String sessionId) throws Exception
	  {
	    this.logger.debug("OEService.checkPermitRequirment start");
	    PermitCheckResponse response = null;
	   
	        OEDomain proxyclient = getOEDomainProxy();
	        long startTime = CommonUtil.getStartTime();
	try
	    {	 
	        response = proxyclient.checkPermitRequirment(request);
	        this.utilityloggerHelper.logTransaction("checkPermitRequirment", false, request, response, response.getStrErrMsg(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getStrCompanyCode());
	        logger.debug(XmlUtil.pojoToXML(request));
	    	logger.debug(XmlUtil.pojoToXML(response));
	    }
	   catch (RemoteException e) {
    	this.logger.error("OEService.checkPermitRequirment : Exception while getting data from ccs:", e);
    	this.utilityloggerHelper.logTransaction("checkPermitRequirment", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getStrCompanyCode());
        logger.debug(XmlUtil.pojoToXML(request));
        throw e;// it is required to throw exception back to BO layer for proper response generation
    }
	    catch (Exception e) {
	    	this.logger.error("OEService.checkPermitRequirment : Exception while getting data from ccs:", e);
	    	this.utilityloggerHelper.logTransaction("checkPermitRequirment", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getStrCompanyCode());
	        logger.debug(XmlUtil.pojoToXML(request));
	        throw e;// it is required to throw exception back to BO layer for proper response generation
	    }

	    this.logger.debug("OEService.checkPermitRequirment end");
	    return response;
	  } 
	  
	  
	  
	  /**
		 * Method to make getBPMatchStatusFromCCS service call from OEDomain web service
		 * @param oeSignupDTO
		 * @return BpMatchCCSResponse
		 * @throws ServiceException
		 */
		public BpMatchCCSResponse getBPMatchStatusFromCCS(BpMatchCCSRequest request) throws Exception 
		{
			BpMatchCCSResponse bpMatchCCSResponse = null ;
			try {
				OEDomain proxyclient = getOEServiceProxy();
				bpMatchCCSResponse = proxyclient.getBPMatchStatusFromCCS(request);
			} catch (Exception e) {
				logger.error("error while executing getBPMatchStatusFromCCS() method from OEDomain web service");
				throw new Exception("Exception in ValidationService:getBPMatchStatusFromCCS():", e);
			} 
			
			logger.debug("ValidationService::getBPMatchStatusFromCCS() Response :"+bpMatchCCSResponse);
			return bpMatchCCSResponse;
		}
		
		
		/**
		 * This will return OEDomain and set EndPoint URL
		 * @return proxy The OEDomain Object
		 */
		protected OEDomain getOEServiceProxy() throws Exception{
			return (OEDomain) getServiceProxy(OEDomainPortBindingStub.class,
					OE_DOMAIN_END_POINT_URL_JNDI_NAME);
		}
		
		/**
		 * 
		 * @param request
		 * @return
		 * @throws Exception
		 */
		public UpdateETFFlagToCRMResponse updateETFFlagToCRM(UpdateETFFlagToCRMRequest request) throws Exception {
			logger.debug("START :: oeService.getAgentDetails");
			UpdateETFFlagToCRMResponse response = new UpdateETFFlagToCRMResponse();
			
				logger.info("Building the input args for Agent Details CCS REST call");
				String[] args = readInputArgs(request,3);
				String url = buildUpdateETFFlafToCRMURL();
				MessageFormat urlFormat = new MessageFormat(url);
				url = urlFormat.format(args);
				logger.info("Get Agent Details CSS URL["+url+"]");

				org.springframework.http.HttpHeaders headers = getBasicAuthSpringHttpHeadersForCCS();
				
				RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactoryForBasicAuth(PROP_CS_DEFAULT_WS_TIMEOUT_IN_SEC));
				HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
				ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
				
				
				String responseAsString = responseEntity.getBody();
				logger.info("Response received after  updateETFFlagToCRM CRM call : " +responseAsString);
				Gson gson = new Gson();
				if(null != responseAsString) {
					logger.info(" updateETFFlagToCRM Response is NOT empty");
					response = gson.fromJson(responseAsString, UpdateETFFlagToCRMResponse.class);
					logger.info("updateETFFlagToCRM Response is NOT empty and converted into required respose object");
					
					if(null != response && response.getUpdateETFFlagToCRMResponseOutData().getActivateETF().getMsgType()!="S"){
						
						response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
						
					} else {
						response.setResultCode(RESULT_CODE_SUCCESS);
					}
				}
				
			logger.debug("END :: OEService.getAgentDetails");
			return response;
		}
		
		private String[] readInputArgs(UpdateETFFlagToCRMRequest request, int totalArgs) {
			
			String[] inputArgs = new String[totalArgs];
			StringBuilder strBuilder = null;
			if(null == request) {
				return inputArgs;
			}
			
			/*
			 * Important Note:
			 * The order of building the String was made based on the CCS URL parameters input position.
			 * It is advised to keep the order as-is.  If at at it is required to modify, carefully 
			 * verify the CCS URL and change their position accordingly while building the String. 
			 */
			int iCount = 0;
			//BP Number
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getPartner());
			strBuilder.append(SINGLE_QUOTE);
			inputArgs[iCount] = strBuilder.toString();
			iCount++;
			
			//ContractAccountNumber
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getAccount());
			strBuilder.append(SINGLE_QUOTE);
			inputArgs[iCount] = strBuilder.toString();
			/*iCount++;
			
			//Active
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getActivate());
			strBuilder.append(SINGLE_QUOTE);
			inputArgs[iCount] = strBuilder.toString();*/
			
			
			return inputArgs;
		}
			

		private String buildUpdateETFFlafToCRMURL() {
			return getEndPointUrl(CCS_UPDATE_ETF_FLAG_TO_CRM_URL);
		}
		
	  
}
