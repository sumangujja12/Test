package com.multibrand.service;

import java.rmi.RemoteException;
import java.text.MessageFormat;

import org.apache.logging.log4j.Logger;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.multibrand.domain.BpMatchCCSRequest;
import com.multibrand.domain.BpMatchCCSResponse;
import com.multibrand.domain.KbaQuestionRequest;
import com.multibrand.domain.KbaQuestionResponse;
import com.multibrand.domain.KbaSubmitAnswerRequest;
import com.multibrand.domain.KbaSubmitAnswerResponse;
import com.multibrand.domain.OEDomain;
import com.multibrand.domain.OEDomainPortBindingStub;
import com.multibrand.domain.OetdspRequest;
import com.multibrand.domain.OfferPricingRequest;
import com.multibrand.domain.PermitCheckRequest;
import com.multibrand.domain.PermitCheckResponse;
import com.multibrand.domain.PromoOfferRequest;
import com.multibrand.domain.PromoOfferResponse;
import com.multibrand.domain.ProspectRequest;
import com.multibrand.domain.ProspectResponse;
import com.multibrand.domain.UpdateCRMAgentInfoRequest;
import com.multibrand.domain.UpdateCRMAgentInfoResponse;
import com.multibrand.dto.request.UpdateETFFlagToCRMRequest;
import com.multibrand.dto.response.UpdateETFFlagToCRMResponse;
import com.multibrand.dto.OESignupDTO;
import com.multibrand.dto.request.AgentDetailsRequest;
import com.multibrand.dto.request.GiactBankValidationRequest;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.response.AgentDetailsResponse;
import com.multibrand.vo.response.GiactBankValidationResponse;


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
		public AgentDetailsResponse getAgentDetails(AgentDetailsRequest request) throws Exception {
			logger.debug("START :: oeService.getAgentDetails");
			AgentDetailsResponse response = new AgentDetailsResponse();
			
				logger.info("Building the input args for Agent Details CCS REST call");
				String[] args = readAgentIDArgs(request.getAgentID());
				String url = buildGetAgentDetailsURL();
				MessageFormat urlFormat = new MessageFormat(url);
				url = urlFormat.format(args);
				logger.info("Get Agent Details CSS URL["+url+"]");

				org.springframework.http.HttpHeaders headers = getBasicAuthSpringHttpHeadersForCCS();
				
				RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactoryForBasicAuth(PROP_CS_DEFAULT_WS_TIMEOUT_IN_SEC));
				HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
				ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
				
				
				String responseAsString = responseEntity.getBody();
				logger.info("Response received after  Agent Details CCS call : " +responseAsString);
				Gson gson = new Gson();
				if(null != responseAsString) {
					logger.info("Read Reliant  Agent Details Response is NOT empty");
					response = gson.fromJson(responseAsString, AgentDetailsResponse.class);
					logger.info("Read Reliant Agent Details Response is NOT empty and converted into required respose object");
					
					if(null != response && null != response.getAgentDetailsResponseOutData() && null!= response.getAgentDetailsResponseOutData().getResult()
							&& (response.getAgentDetailsResponseOutData().getResult().size()==0
							|| StringUtils.isBlank(response.getAgentDetailsResponseOutData().getResult().get(0).getAgentVendorCode()))){
						
						response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
					} else {
						response.setResultCode(RESULT_CODE_SUCCESS);
					}
				}
				
			logger.debug("END :: OEService.getAgentDetails");
			return response;
		}
		
		private String[] readAgentIDArgs(String agentID) {
			String[] agentIDArgs = new String[1];
			StringBuilder strBuilder = null;
			if(null == agentID) {
				return agentIDArgs;
			}
			
			/*
			 * Important Note:
			 * The order of building the String was made based on the CCS URL parameters input position.
			 * It is advised to keep the order as-is.  If it is required to modify, carefully 
			 * verify the CCS URL and change their position accordingly while building the String. 
			 */
			int iCount = 0;
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(agentID);
			strBuilder.append(SINGLE_QUOTE);
			agentIDArgs[iCount] = strBuilder.toString();
			iCount++;
			
			return agentIDArgs;
		}
		private String buildGetAgentDetailsURL() {
			return getEndPointUrl(CCS_GET_AGENT_DETAILS_URL);
		}
		/**
		 * START : OE | Sprint 46 | US15066 | Kdeshmu1
		 * @param request
		 * @return
		 * @throws Exception
		 */
		public UpdateETFFlagToCRMResponse updateETFFlagToCRM(UpdateETFFlagToCRMRequest request) throws Exception {
			logger.debug("START :: oeService.updateETFFlagToCRM");
			UpdateETFFlagToCRMResponse response = new UpdateETFFlagToCRMResponse();
			
				String[] args = readInputArgs(request,3);
				String url = buildUpdateETFFlafToCRMURL();
				MessageFormat urlFormat = new MessageFormat(url);
				url = urlFormat.format(args);
				
				org.springframework.http.HttpHeaders headers = getBasicAuthSpringHttpHeadersForCCS();
				
				RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactoryForBasicAuth(PROP_CS_DEFAULT_WS_TIMEOUT_IN_SEC));
				HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
				ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
				
				
				String responseAsString = responseEntity.getBody();
				Gson gson = new Gson();
				if(null != responseAsString) {
					response = gson.fromJson(responseAsString, UpdateETFFlagToCRMResponse.class);
					if(null != response && response.getUpdateETFFlagToCRMResponseOutData().getActivateETF().getMsgType()!=CONSTANT_S){
						
						response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
						
					} else if(response!= null) {
						response.setResultCode(RESULT_CODE_SUCCESS);
					}
				}
				
			logger.debug("END :: OEService.updateETFFlagToCRM");
			return response;
		}
		/**
		 * START : OE | Sprint 46 | US15066 | Kdeshmu1
		 * @param request
		 * @param totalArgs
		 * @return
		 */
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
			
			
			return inputArgs;
		}
			

		private String buildUpdateETFFlafToCRMURL() {
			return getEndPointUrl(CCS_UPDATE_ETF_FLAG_TO_CRM_URL);
		}
		// Start | US19653 | MBAR: Sprint 23 -GIACT REST IMPL : validate bank details  | Jyothi | 5/31/2019
		/**
		 * START : OE | Sprint 23 | US19653 | Nkatragadda
		 * @param request
		 * @return
		 * @throws Exception
		 */
		public GiactBankValidationResponse validateBankDetailsGiact(GiactBankValidationRequest bankDetailsValidationRequest) throws Exception {
			logger.debug("START :: OEService.ValidateBankDetailsGiact(..)");
			GiactBankValidationResponse bankDetailsValidationResponse = new GiactBankValidationResponse();
				
				String[] args = readBankDetailsArgs(bankDetailsValidationRequest,6);
				
				String url = buildBankGiactCallURL();
				MessageFormat urlFormat = new MessageFormat(url);
				url = urlFormat.format(args);
				
				org.springframework.http.HttpHeaders headers = getBasicAuthSpringHttpHeadersForCCS();
				
				RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactoryForBasicAuth(PROP_CS_DEFAULT_WS_TIMEOUT_IN_SEC));
				HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
				ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
				
				
				String responseAsString = responseEntity.getBody();
				Gson gson = new Gson();
				if(null != responseAsString) {
					bankDetailsValidationResponse = gson.fromJson(responseAsString, GiactBankValidationResponse.class);
				}
			logger.debug("END :: oeService.ValidateBankDetailsGiact(..)");
			return bankDetailsValidationResponse;
		}
		
		private String buildBankGiactCallURL() {
			return getEndPointUrl(CCS_BANK_GIACT_CALL_URL);
		}
		
		private String[] readBankDetailsArgs(GiactBankValidationRequest request, int totalArgs) {
			
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
			//ContractAccountNumber
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getCaNumber());
			strBuilder.append(SINGLE_QUOTE);
			inputArgs[iCount] = strBuilder.toString();
			iCount++;
			
			//CompanyCode
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getCompanyCode());
			strBuilder.append(SINGLE_QUOTE);
			inputArgs[iCount] = strBuilder.toString();
			iCount++;
			
			//BankAccountNumber
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getTokenizedBankAccountNumber());
			strBuilder.append(SINGLE_QUOTE);
			inputArgs[iCount] = strBuilder.toString();
			iCount++;
			
			//RoutingNumber
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getBankRoutingNumber());
			strBuilder.append(SINGLE_QUOTE);
			inputArgs[iCount] = strBuilder.toString();
			iCount++;
			
			//TrackingNumber
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getTrackingId());
			strBuilder.append(SINGLE_QUOTE);
			inputArgs[iCount] = strBuilder.toString();
			
			return inputArgs;
		}
		// Start | US19653 | MBAR: Sprint 23 -GIACT REST IMPL: validate bank details  | Jyothi | 5/31/2019	
		
		
		public UpdateCRMAgentInfoResponse updateCRMAgentInfo(OESignupDTO oeSignUpDTO) {
			UpdateCRMAgentInfoResponse updateResponse =new UpdateCRMAgentInfoResponse(); 
			try{
			
				logger.debug("{} updateCRMAgentInfo :: inside function in enrollmentService"+oeSignUpDTO.printOETrackingID());
				
				OEDomain proxyClient = getOEServiceProxy();
				
				UpdateCRMAgentInfoRequest updateRequest = createUpdateCRMAgentInfo(oeSignUpDTO);
				
				logger.info("UpdateCRMAgentInfoRequest  is {}"+ ReflectionToStringBuilder.toString(updateRequest,
						ToStringStyle.MULTI_LINE_STYLE));
				 
				updateResponse =  proxyClient.updateOEAgentInfo(updateRequest);
				logger.info(oeSignUpDTO.printOETrackingID()+"updateCRMAgentInfo:: "+ReflectionToStringBuilder.toString(updateResponse,
						ToStringStyle.MULTI_LINE_STYLE));
				logger.info("responseStatus ="+ updateResponse.getResponseCode());		
			}
			catch(Exception e)
			{			
				updateResponse.setResponseCode(FLAG_E);
				updateResponse.setResponseMsg("Exception while executing the service");
				logger.error("{} inside OEService:: in updateCRMAgentInfo() ::SubmitupdateCRMAgentInfoEnrollment Call Failed with error ::{}" + e);
				
			}
			
			return updateResponse;
		}
		/**
		 * OE :Sprint 62 : US210019  : Tppo pass agent data to CCS
		 * @param oeSignUpDTO
		 * @return
		 */
		private UpdateCRMAgentInfoRequest createUpdateCRMAgentInfo(OESignupDTO oeSignUpDTO) {
			UpdateCRMAgentInfoRequest updateRequest = new UpdateCRMAgentInfoRequest();
			updateRequest.setCompanyCode(oeSignUpDTO.getCompanyCode());
			updateRequest.setCaNumber(CommonUtil.addLeadingZeroes(oeSignUpDTO.getContractAccountNum(), 12));
			updateRequest.setBpNumber(CommonUtil.addLeadingZeroes(oeSignUpDTO.getBusinessPartnerID(), 10));
			updateRequest.setWebTrackingID(oeSignUpDTO.getTrackingNumber());
			//START - OE Alternate Channel Sprint15| US12758 | Kdeshmu1 :Code cleanup
			if (StringUtils.isNotBlank(oeSignUpDTO.getAgentID())) {
				updateRequest.setAgentID(oeSignUpDTO.getAgentID());
				updateRequest.setVendorID(oeSignUpDTO.getVendorCode());
			}
			//END - OE Alternate Channel Sprint15| US12758 | Kdeshmu1 :Code cleanup
			return updateRequest;

		}
		
/**
 * @author 289347
 * @param kbaQuestionRequest
 * @return
 */
		public KbaQuestionResponse getKBAQuestionList(KbaQuestionRequest kbaQuestionRequest)   {
			KbaQuestionResponse kbaQuestionResponse = null;
			try {
				OEDomain proxyclient = getOEServiceProxy();
				kbaQuestionResponse = proxyclient.getKBAQuestionList(kbaQuestionRequest);
			} catch (Exception e) {
				logger.error("ERROR_IN_GETKBAQUESTIOINFO");
			} 
			return kbaQuestionResponse;
		}
/**
 * 	
 * @param kbaSubmitAnswerRequest
 * @return
 */
		public KbaSubmitAnswerResponse submitKBAAnswer(KbaSubmitAnswerRequest kbaSubmitAnswerRequest)   {
			KbaSubmitAnswerResponse kbaSubmitAnswerResponse = null;
			try {
				OEDomain proxyclient = getOEServiceProxy();
				kbaSubmitAnswerResponse = proxyclient.submitAnswerKBA(kbaSubmitAnswerRequest);
			} catch (Exception e) {
				logger.error("Error in method: submitKbaAnswer");
			} 
			return kbaSubmitAnswerResponse;
		}
		
/**
 * START :OE ADO SPrint4 : To get Prospect Data
 * @author 289347
 * @param request
 * @return
 */
public ProspectResponse getProspectData(ProspectRequest request)   {
			
			ProspectResponse  response = null;
			try {
				OEDomain proxyclient = getOEServiceProxy();
				response= proxyclient.getProsectData(request);
			} catch (Exception e) {
				logger.error("Error in method: submitKbaAnswer");
			} 
			return response;
		}
		
}
