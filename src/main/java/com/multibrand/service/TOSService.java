package com.multibrand.service;

import java.rmi.RemoteException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.multibrand.domain.CheckPendingMVORequest;
import com.multibrand.domain.CheckPendingMVOResponse;
import com.multibrand.domain.CheckPendingMoveInRequest;
import com.multibrand.domain.CheckPendingMoveInResponse;
import com.multibrand.domain.ContractDataRequest;
import com.multibrand.domain.ContractDataResponse;
import com.multibrand.domain.CreateContactLogRequest;
import com.multibrand.domain.CreateContactLogResponse;
import com.multibrand.domain.EsiDforAddressRequest;
import com.multibrand.domain.EsiDforAddressResponse;
import com.multibrand.domain.OfferOfContractRequest;
import com.multibrand.domain.OfferOfContractResponse;
import com.multibrand.domain.ProgramAccountInfoRequest;
import com.multibrand.domain.ProgramAccountInfoResponse;
import com.multibrand.domain.TOSDomain;
import com.multibrand.domain.TOSDomainPortBindingStub;
import com.multibrand.domain.TdspByESIDRequest;
import com.multibrand.domain.TdspByESIDResponse;
import com.multibrand.domain.TdspByZipRequest;
import com.multibrand.domain.TdspByZipResponse;
import com.multibrand.domain.TosAmbWebResponse;
import com.multibrand.domain.TransferServiceRequest;
import com.multibrand.domain.TransferServiceResponse;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.request.TOSEligibleNonEligibleProductsRequest;
import com.multibrand.vo.request.TOSSubmitEligibleProductsRequest;
import com.multibrand.vo.response.TOSEligibleNonEligibleProductsResponse;
import com.multibrand.vo.response.TOSSubmitEligibleProductsResponse;


@Service
public class TOSService extends BaseAbstractService
{
  private Logger logger = LogManager.getLogger("NRGREST_LOGGER");

  @Autowired
  private UtilityLoggerHelper utilityloggerHelper;
  @Context 
	private HttpServletRequest httpRequest;

  protected TOSDomain getTOSDomainProxy()
  {
    return (TOSDomain)getServiceProxy(TOSDomainPortBindingStub.class, 
      "ws.endpointURL.tosDomain");
  }

  public CheckPendingMVOResponse checkingPendingMVO(CheckPendingMVORequest request, String companyCode, String sessionId)
    throws Exception
  {
    TOSDomain proxy = getTOSDomainProxy();
    long startTime = CommonUtil.getStartTime();
    CheckPendingMVOResponse response = null;
    try{
    	response= proxy.checkPendingMoveOut(request);
    }catch(RemoteException ex){
    	logger.error(ex);
    	this.utilityloggerHelper.logTransaction("checkingPendingMVO", false, request, ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
    	if(logger.isDebugEnabled())
    		logger.debug(XmlUtil.pojoToXML(request));
    	throw ex;// it is required to throw exception back to BO layer for proper response generation
    }
    catch(Exception ex){
    	logger.error(ex);
    	this.utilityloggerHelper.logTransaction("checkingPendingMVO", false, request, ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
    	if(logger.isDebugEnabled())
    		logger.debug(XmlUtil.pojoToXML(request));
    	throw ex;// it is required to throw exception back to BO layer for proper response generation
    }
    this.utilityloggerHelper.logTransaction("checkingPendingMVO", false, request, response, response.getErrMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
    if(logger.isDebugEnabled()){
	    logger.debug(XmlUtil.pojoToXML(request));
		logger.debug(XmlUtil.pojoToXML(response));
    }
    return response;
  }

  public CheckPendingMoveInResponse checkingPendingMVI(CheckPendingMoveInRequest request, String companyCode, String sessionId)
    throws Exception
  {
    TOSDomain proxy = getTOSDomainProxy();
    long startTime = CommonUtil.getStartTime();
    CheckPendingMoveInResponse response= null;
    try{
    	response = proxy.checkPendingMVI(request);
    }catch(RemoteException ex){
    	logger.error(ex);
    	this.utilityloggerHelper.logTransaction("checkingPendingMVI", false, request, ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
    	if(logger.isDebugEnabled())
    		logger.debug(XmlUtil.pojoToXML(request));
    	throw ex;// it is required to throw exception back to BO layer for proper response generation
    }
    catch(Exception ex){
    	logger.error(ex);
    	this.utilityloggerHelper.logTransaction("checkingPendingMVI", false, request, ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
    	if(logger.isDebugEnabled())
    		logger.debug(XmlUtil.pojoToXML(request));
    	throw ex;// it is required to throw exception back to BO layer for proper response generation
    }
    this.utilityloggerHelper.logTransaction("checkingPendingMVI", false, request, response, response.getErrMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
    if(logger.isDebugEnabled()){
	    logger.debug(XmlUtil.pojoToXML(request));
		logger.debug(XmlUtil.pojoToXML(response));
    }
    return response;
  }

  public EsiDforAddressResponse getESIDForAddress(EsiDforAddressRequest request, String companyCode, String sessionId)
    throws Exception
  {
    TOSDomain proxy = getTOSDomainProxy();
    long startTime = CommonUtil.getStartTime();
    EsiDforAddressResponse response = null;
    try{
    	response = proxy.getESIDforAddress(request);
    }catch(RemoteException ex){
    	logger.error(ex);
    	this.utilityloggerHelper.logTransaction("getESIDForAddress", false, request, ex,"", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
    	if(logger.isDebugEnabled())
    		logger.debug(XmlUtil.pojoToXML(request));
    	throw ex;// it is required to throw exception back to BO layer for proper response generation
    }catch(Exception ex){
    	logger.error(ex);
    	this.utilityloggerHelper.logTransaction("getESIDForAddress", false, request, ex,"", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
    	if(logger.isDebugEnabled())
    		logger.debug(XmlUtil.pojoToXML(request));
    	throw ex;// it is required to throw exception back to BO layer for proper response generation
    }
    this.utilityloggerHelper.logTransaction("getESIDForAddress", false, request, response, response.getErrMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
    if(logger.isDebugEnabled()){
	    logger.debug(XmlUtil.pojoToXML(request));
		logger.debug(XmlUtil.pojoToXML(response));
    }
    return response;
  }

  public TdspByESIDResponse ccsGetTDSPFromESID(String pointofDeliveryID, String companyCode, String sessionId) throws Exception
  {
    this.logger.info("TOSService.ccsGetTDSPFromESID start");
    TdspByESIDResponse tdspByESIDResponse = null;
    TdspByESIDRequest tdspByESIDRequest = new TdspByESIDRequest();
   
		if (StringUtils.isNotBlank(companyCode)) {
			tdspByESIDRequest.setCompanyCode(companyCode);
		} else {
			tdspByESIDRequest.setCompanyCode(this.appConstMessageSource.getMessage("company.code", null, null));
		}
	    tdspByESIDRequest.setPointOfDeliveryId(pointofDeliveryID);
        TOSDomain proxyclient = getTOSDomainProxy();
        long startTime = CommonUtil.getStartTime();
    try {
        tdspByESIDResponse = proxyclient.getTDSPFromESID(tdspByESIDRequest);
        this.utilityloggerHelper.logTransaction("ccsGetTDSPFromESID", false, tdspByESIDRequest, tdspByESIDResponse, tdspByESIDResponse.getStrErrMsg(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
        if(logger.isDebugEnabled()){
	        logger.debug(XmlUtil.pojoToXML(tdspByESIDRequest));
			logger.debug(XmlUtil.pojoToXML(tdspByESIDResponse));
        }
    } catch (RemoteException e) {
    	this.logger.error("TOSService.ccsGetTDSPFromESID : Exception while getting data from ccs:" + e.getMessage());
    	this.utilityloggerHelper.logTransaction("ccsGetTDSPFromESID", false, tdspByESIDRequest, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
    	if(logger.isDebugEnabled())
    		logger.debug(XmlUtil.pojoToXML(tdspByESIDRequest));
        throw e;// it is required to throw exception back to BO layer for proper response generation
    }catch (Exception e) {
    	this.logger.error("TOSService.ccsGetTDSPFromESID : Exception while getting data from ccs:" + e.getMessage());
    	this.utilityloggerHelper.logTransaction("ccsGetTDSPFromESID", false, tdspByESIDRequest, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
    	if(logger.isDebugEnabled())
    		logger.debug(XmlUtil.pojoToXML(tdspByESIDRequest));
        throw e;
    }// it is required to throw exception back to BO layer for proper response generation

    this.logger.info("TOSService.ccsGetTDSPFromESID end");
    return tdspByESIDResponse;
  }
  public TdspByZipResponse ccsGetTDSPFromZip(String zip, String companyCode, String sessionId) throws Exception
  {
    this.logger.info("TOSService.ccsgetTdspByZip start");
    TdspByZipResponse tdspByZIPResponse = null;
    
        TdspByZipRequest tdspByZIPRequest = new TdspByZipRequest();
        if(StringUtils.isNotBlank(companyCode)){
        	tdspByZIPRequest.setCompanyCode(companyCode);
        } else {
        	tdspByZIPRequest.setCompanyCode(this.appConstMessageSource.getMessage("company.code", null, null));
        }
        
        tdspByZIPRequest.setZip(zip);
        TOSDomain proxyclient = getTOSDomainProxy();
        long startTime = CommonUtil.getStartTime();
   try{
        tdspByZIPResponse = proxyclient.getTdspByZip(tdspByZIPRequest);
        this.utilityloggerHelper.logTransaction("ccsGetTDSPFromZip", false, tdspByZIPRequest, tdspByZIPResponse, tdspByZIPResponse.getStrErrMsg(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
        if(logger.isDebugEnabled()){
	        logger.debug(XmlUtil.pojoToXML(tdspByZIPRequest));
	    	logger.debug(XmlUtil.pojoToXML(tdspByZIPResponse));
        }
    } catch (RemoteException e) {
    	this.logger.error("TOSService.ccsgetTdspByZip : Exception while getting data from ccs:" + e.getMessage());
    	this.utilityloggerHelper.logTransaction("ccsGetTDSPFromZip", false, tdspByZIPRequest, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
    	if(logger.isDebugEnabled())
    		logger.debug(XmlUtil.pojoToXML(tdspByZIPRequest));
        throw e;
    }
    catch (Exception e) {
    	this.logger.error("TOSService.ccsgetTdspByZip : Exception while getting data from ccs:" + e.getMessage());
    	this.utilityloggerHelper.logTransaction("ccsGetTDSPFromZip", false, tdspByZIPRequest, e,"", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
    	if(logger.isDebugEnabled())
    		logger.debug(XmlUtil.pojoToXML(tdspByZIPRequest));
        throw e;
    }

    this.logger.info("TOSService.ccsgetTdspByZip end");
    return tdspByZIPResponse;
  } 
  
  /**
   * 
   * @param request
   * @param sessionId
   * @return
 * @throws Exception 
   */
  public OfferOfContractResponse getOfferOfContract(OfferOfContractRequest request, String sessionId) throws Exception
  {
    this.logger.info("TOSService.getOfferOfContract start");
    OfferOfContractResponse response = null;
    long startTime = CommonUtil.getStartTime();
    try
    {
        TOSDomain proxyclient = getTOSDomainProxy();
        response = proxyclient.getOfferOfContract(request);
        this.utilityloggerHelper.logTransaction("getOfferOfContract", false, request, response, response.getErrMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
        if(logger.isDebugEnabled()){
	        logger.debug(XmlUtil.pojoToXML(request));
	    	logger.debug(XmlUtil.pojoToXML(response));
        }
    }catch (RemoteException e) {
    	this.logger.error("TOSService.getOfferOfContract : Exception while getting data from ccs:" + e.getMessage());
    	this.utilityloggerHelper.logTransaction("getOfferOfContract", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
    	if(logger.isDebugEnabled())
    		logger.debug(XmlUtil.pojoToXML(request));
        throw e; // it is required to throw exception back to BO layer for proper response generation
    }
    catch (Exception e) {
    	this.logger.error("TOSService.getOfferOfContract : Exception while getting data from ccs:" + e.getMessage());
    	this.utilityloggerHelper.logTransaction("getOfferOfContract", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
    	if(logger.isDebugEnabled())
    		logger.debug(XmlUtil.pojoToXML(request));
        throw e; // it is required to throw exception back to BO layer for proper response generation
    }

    this.logger.info("TOSService.getOfferOfContract end");
    return response;
  } 
  
  /**
   * 
   * @param request
   * @param sessionId
   * @return
 * @throws Exception 
   */
  public ProgramAccountInfoResponse getProgramAccountInfo(ProgramAccountInfoRequest request, String sessionId) throws Exception
  {
    this.logger.info("TOSService.getProgramAccountInfo start");
    ProgramAccountInfoResponse response = null;
    long startTime = CommonUtil.getStartTime();
    try
    {
        TOSDomain proxyclient = getTOSDomainProxy();
        
        response = proxyclient.getProgramAccountInfo(request);
        this.utilityloggerHelper.logTransaction("getProgramAccountInfo", false, request, response, response.getErrMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
        if(logger.isDebugEnabled()){
	        logger.debug(XmlUtil.pojoToXML(request));
	    	logger.debug(XmlUtil.pojoToXML(response));
        }
    }catch(RemoteException e) {
    	this.logger.error("TOSService.getProgramAccountInfo : Exception while getting data from ccs:" + e.getMessage());
    	this.utilityloggerHelper.logTransaction("getProgramAccountInfo", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
    	if(logger.isDebugEnabled())
    		logger.debug(XmlUtil.pojoToXML(request));
        throw e;// throwing of exception back to BO layer is required for proper response generation
    }
    catch (Exception e) {
    	this.logger.error("TOSService.getProgramAccountInfo : Exception while getting data from ccs:" + e.getMessage());
    	this.utilityloggerHelper.logTransaction("getProgramAccountInfo", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
    	if(logger.isDebugEnabled())	
    		logger.debug(XmlUtil.pojoToXML(request));
        throw e;// throwing of exception back to BO layer is required for proper response generation
    }

    this.logger.info("TOSService.getProgramAccountInfo end");
    return response;
  } 
  
  /**
   * @author ahanda1
   * @param request
   * @param sessionId
   * @return
 * @throws Exception 
   */
  public CreateContactLogResponse updateContactLog(CreateContactLogRequest request, String sessionId) throws Exception
  {
    this.logger.info("TOSService.updateContactLog start");
    CreateContactLogResponse response = null;
    long startTime = CommonUtil.getStartTime();
    try
    {
        TOSDomain proxyclient = getTOSDomainProxy();
        
        response = proxyclient.updateContactLog(request);
        this.utilityloggerHelper.logTransaction("updateContactLog", false, request, response, response.getErrMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
        if(logger.isDebugEnabled()){
	        logger.debug(XmlUtil.pojoToXML(request));
	    	logger.debug(XmlUtil.pojoToXML(response));
        }
    }catch (RemoteException e) {
    	this.logger.error("TOSService.updateContactLog : Exception while getting data from ccs:" + e.getMessage());
    	this.utilityloggerHelper.logTransaction("updateContactLog", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
    	if(logger.isDebugEnabled())
    		logger.debug(XmlUtil.pojoToXML(request));
        throw e;
    }
    catch (Exception e) {
    	this.logger.error("TOSService.updateContactLog : Exception while getting data from ccs:" + e.getMessage());
    	this.utilityloggerHelper.logTransaction("updateContactLog", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
    	if(logger.isDebugEnabled())
    		logger.debug(XmlUtil.pojoToXML(request));
        throw e;// required to throw exception to BO layer back for proper response generation
    }

    this.logger.info("TOSService.updateContactLog end");
    return response;
  } 
  
  
  /**
   * @author ahanda1
   * @param request
   * @param sessionId
   * @return
 * @throws Exception 
   */
  public ContractDataResponse getContractData(ContractDataRequest request, String sessionId) throws Exception
  {
    this.logger.info("TOSService.getContractData start");
    ContractDataResponse response = null;
      TOSDomain proxyclient = getTOSDomainProxy();
        long startTime = CommonUtil.getStartTime();
   try{     
        response = proxyclient.getContractData(request);
        this.utilityloggerHelper.logTransaction("getContractData", false, request, response, response.getErrMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
        if(logger.isDebugEnabled()){
        	logger.info(XmlUtil.pojoToXML(request));
    		logger.info(XmlUtil.pojoToXML(response));
        }
    }
   catch (RemoteException e) {
   	this.logger.error("TOSService.getContractData : Exception while getting data from ccs:" + e.getMessage());
   	this.utilityloggerHelper.logTransaction("getContractData", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
   	if(logger.isDebugEnabled())
   	   logger.debug(XmlUtil.pojoToXML(request));
       throw e;
   }
    catch (Exception e) {
    	this.logger.error("TOSService.getContractData : Exception while getting data from ccs:" + e.getMessage());
    	this.utilityloggerHelper.logTransaction("getContractData", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
    	if(logger.isDebugEnabled())
    		logger.debug(XmlUtil.pojoToXML(request));
        throw e;
    }

    this.logger.info("TOSService.getContractData end");
    return response;
  } 
  
  
	  /**
	   * @author ahanda1
	   * @param request
	   * @param sessionId
	   * @return
	 * @throws Exception 
	   */
	  public TransferServiceResponse saveTransferServiceDetails(TransferServiceRequest request, String sessionId) throws Exception
	  {
		  this.logger.info("TOSService.saveTransferServiceDetails start");
		  TransferServiceResponse response = null;
	
		  TOSDomain proxyclient = getTOSDomainProxy();
		  long startTime = CommonUtil.getStartTime();
		  String strPointOfDeliveryId = request.getPointofDeliveryID();
		  try{
			  // Changing this request property to pass angular brackets in CCS if the value is ESIDNOTFOUND
			  if(request.getPointofDeliveryID()!=null && request.getPointofDeliveryID().trim().equalsIgnoreCase("ESIDNOTFOUND"))
				  request.setPointofDeliveryID("<"+request.getPointofDeliveryID()+">");
			  response = proxyclient.saveTransferServiceDetails(request);
			  request.setPointofDeliveryID(strPointOfDeliveryId);
			  this.utilityloggerHelper.logTransaction("saveTransferServiceDetails", false, request, response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			  if(logger.isDebugEnabled()){
				  logger.info(XmlUtil.pojoToXML(request));
				  logger.info(XmlUtil.pojoToXML(response));
			  }
		  }
		  catch (RemoteException e) {
			  this.logger.error("TOSService.saveTransferServiceDetails : Exception while getting data from ccs:" + e.getMessage());
			  this.utilityloggerHelper.logTransaction("saveTransferServiceDetails", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			  if(logger.isDebugEnabled())
				  logger.debug(XmlUtil.pojoToXML(request));
			  throw e;
		  }
		  catch (Exception e) {
			  this.logger.error("TOSService.saveTransferServiceDetails : Exception while getting data from ccs:" + e.getMessage());
			  this.utilityloggerHelper.logTransaction("saveTransferServiceDetails", false, request, e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			  if(logger.isDebugEnabled())
				  logger.debug(XmlUtil.pojoToXML(request));
			  throw e;
		  }
	
		  this.logger.info("TOSService.saveTransferServiceDetails end");
		  return response;
	  }
	  
	  /**
	   * @author DKRISHN1
	   * @param tosAMBRequest
	   * @return
	   */
	  public TosAmbWebResponse getTosAMBDetails(com.multibrand.domain.TosAmbWebRequest tosAMBRequest, String sessionId) throws Exception
	  {
		  TOSDomain tosDomain = getTOSDomainProxy();
		  long startTime = CommonUtil.getStartTime();
		  com.multibrand.domain.TosAmbWebResponse response = null;
		  try{ 
			  response = tosDomain.getTosAMBDetails(tosAMBRequest);
		  }catch(RemoteException ex){
		  	logger.error("RemoteException:::::::::::::::::::::::"+ex);
		  	this.utilityloggerHelper.logTransaction("getTosAMBDetails", false, tosAMBRequest, ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, tosAMBRequest.getCompanyCode());
		  	if(logger.isDebugEnabled())
		  		logger.debug(XmlUtil.pojoToXML(tosAMBRequest));
		  	throw ex;// it is required to throw exception back to BO layer for proper response generation
		  }
		  catch(Exception ex){
		  	logger.info("Exception in getTosAMBDetails()::: "+ex);
		  	this.utilityloggerHelper.logTransaction("getTosAMBDetails", false, tosAMBRequest, ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, tosAMBRequest.getCompanyCode());
		  	if(logger.isDebugEnabled())
		  		logger.debug(XmlUtil.pojoToXML(tosAMBRequest));
		  	throw ex;// it is required to throw exception back to BO layer for proper response generation
		  }
		  this.utilityloggerHelper.logTransaction("getTosAMBDetails", false, tosAMBRequest, response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, tosAMBRequest.getCompanyCode());
		  if(logger.isDebugEnabled()){
			    logger.debug(XmlUtil.pojoToXML(tosAMBRequest));
				logger.debug(XmlUtil.pojoToXML(response));
		  }
		  return response;
	  }
	 
		/**
		 * Call CCS REST service call to get eligible/Non-eligible TOS products list
		 * @param request TOSEligibleNonEligibleProductsRequest
		 * @return TOSEligibleNonEligibleProductsResponse
		 * @throws Exception 
		 */
		public TOSEligibleNonEligibleProductsResponse tosEligibleNonEligibleProducts(TOSEligibleNonEligibleProductsRequest request) throws Exception {
			logger.debug("START :: TOSService.tosEligibleNonEligibleProducts");
			TOSEligibleNonEligibleProductsResponse tosEligibleNonEligibleProductsResponse = new TOSEligibleNonEligibleProductsResponse();
			
				logger.info("Building the input args for TOS Eligible, Non-Eligible Products CCS REST call");
				String[] args = getTOSEligibleNonEligibleProductsArgs(request);
				String url = buildTOSEligibleNonEligibleProductsURL();
				MessageFormat urlFormat = new MessageFormat(url);
				url = urlFormat.format(args);
				logger.info("TOS Eligible, Non-Eligible Products CCS URL["+url+"]");

				org.springframework.http.HttpHeaders headers = getBasicAuthSpringHttpHeadersForCCS();
				
				RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactoryForBasicAuth(PROP_CS_DEFAULT_WS_TIMEOUT_IN_SEC));
				HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
				ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
				logger.info("Response received after TOS Eligible, Non-Eligible Products in CCS");
				
				String responseAsString = responseEntity.getBody();
				
				Gson gson = new Gson();
				boolean isCcsResponseSuccess = false;
				if(null != responseAsString) {
					logger.info("TOS Eligible, Non-Eligible Products Response is NOT empty");
					tosEligibleNonEligibleProductsResponse = gson.fromJson(responseAsString, TOSEligibleNonEligibleProductsResponse.class);
					logger.info("TOS Eligible, Non-Eligible Products Response is NOT empty and converted into required respose object");
					if(null != tosEligibleNonEligibleProductsResponse 
							&& null != tosEligibleNonEligibleProductsResponse.getTosOtherServicesProductsOutData().getTosOtherServicesProducts()
							&& tosEligibleNonEligibleProductsResponse.getTosOtherServicesProductsOutData().getTosOtherServicesProducts().length > 0) {
						
						logger.info("TOS Eligible, Non-Eligible Products Response is NOT empty and received SUCCESS :"+
								tosEligibleNonEligibleProductsResponse.getTosOtherServicesProductsOutData().getTosOtherServicesProducts().length +"Products in the list");
						isCcsResponseSuccess = true;
						tosEligibleNonEligibleProductsResponse.setResultCode(RESULT_CODE_SUCCESS);
						
					} else {
						logger.info("TOS Eligible, Non-Eligible Products Response is empty and marked as FAILURE");
					}
				}
				
				if(!isCcsResponseSuccess) {
					tosEligibleNonEligibleProductsResponse = new TOSEligibleNonEligibleProductsResponse();
					tosEligibleNonEligibleProductsResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
					tosEligibleNonEligibleProductsResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
				}
				
			logger.debug("END :: TOSService.tosEligibleNonEligibleProducts");
			return tosEligibleNonEligibleProductsResponse;
		}

		/**
		 * @param request
		 * @return
		 */
		private String[] getTOSEligibleNonEligibleProductsArgs(TOSEligibleNonEligibleProductsRequest request) {
			
			// /sap/opu/odata/sap/ZE_WEB_DT_REQUEST_SRV/TOS_Product_Eligibility?ESID='1008901012126233830100'&VKONT='000000279951'&VERTRAG=''&OFFER='50498249'&PROMO=''&ZIPCODE=''&BUKRS='0121'
					
			String[] tosEligibleNonEligibleProductsInputArgs = new String[7];
			StringBuilder strBuilder = null;
			if(null == request) {
				return tosEligibleNonEligibleProductsInputArgs;
			}
			
			/*
			 * Important Note:
			 * The order of building the String was made based on the CCS URL parameters input position.
			 * It is advised to keep the order as-is.  If it is required to modify, carefully 
			 * verify the CCS URL and change their position accordingly while building the String. 
			 */
			int iCount = 0;
			// ESID
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getEsid());
			strBuilder.append(SINGLE_QUOTE);
			tosEligibleNonEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;
			
			// CA Number  - VKONT
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getCaNumber());
			strBuilder.append(SINGLE_QUOTE);
			tosEligibleNonEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;
			
			// CO Number - VERTRAG
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getCoNumber());
			strBuilder.append(SINGLE_QUOTE);
			tosEligibleNonEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;

			// Offer Code
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getOfferCode());
			strBuilder.append(SINGLE_QUOTE);
			tosEligibleNonEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;

			// Promo Code
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getPromoCode());
			strBuilder.append(SINGLE_QUOTE);
			tosEligibleNonEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;

			// ZIP Code
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getZipCode());
			strBuilder.append(SINGLE_QUOTE);
			tosEligibleNonEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;

			// Company Code
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getCompanyCode());
			strBuilder.append(SINGLE_QUOTE);
			tosEligibleNonEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;

			return tosEligibleNonEligibleProductsInputArgs;
		}
		
		private String buildTOSEligibleNonEligibleProductsURL() {
			return getEndPointUrl(CCS_TOS_ELIGIBLE_NONELIGIBLE_PRODUCTS_URL);
		}

		/**
		 * Call CCS REST service call to submit user selected Other Services Products in TOS flow
		 * @param request TOSSubmitEligibleProductsRequest
		 * @return TOSSubmitEligibleProductsResponse
		 * @throws Exception 
		 */
		public TOSSubmitEligibleProductsResponse tosSubmitEligibleProducts(TOSSubmitEligibleProductsRequest request) throws Exception {
			logger.debug("START :: TOSService.tosSubmitEligibleProducts");
			TOSSubmitEligibleProductsResponse tosSubmitEligibleProductsResponse = new TOSSubmitEligibleProductsResponse();
			
				logger.info("Building input args for TOS Submit Eligible Products to CCS REST call");
				String[] args = getTOSSubmitEligibleProductsArgs(request);
				String url = buildTOSSubmitEligibleProductsURL();
				MessageFormat urlFormat = new MessageFormat(url);
				url = urlFormat.format(args);
				logger.info("TOS Submit Eligible Products CCS URL["+url+"]");

				org.springframework.http.HttpHeaders headers = getBasicAuthSpringHttpHeadersForCCS();
				
				RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactoryForBasicAuth(PROP_CS_DEFAULT_WS_TIMEOUT_IN_SEC));
				HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
				ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
				logger.info("Response received after TOS Submit Eligible Products in CCS");
				
				String responseAsString = responseEntity.getBody();
				
				Gson gson = new Gson();
				boolean isCcsResponseSuccess = false;
				if(null != responseAsString) {
					logger.info("TOS Submit Eligible Products Response is NOT empty");
					tosSubmitEligibleProductsResponse = gson.fromJson(responseAsString, TOSSubmitEligibleProductsResponse.class);
					logger.info("TOS Submit Eligible Products Response is NOT empty and converted into required respose object");
					
					isCcsResponseSuccess = true;
					tosSubmitEligibleProductsResponse.setResultCode(RESULT_CODE_SUCCESS);
				}
				
				if(!isCcsResponseSuccess) {
					logger.info("TOS Submit Eligible Products Response is empty");
					tosSubmitEligibleProductsResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
					tosSubmitEligibleProductsResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
				}
				
			logger.debug("END :: TOSService.tosSubmitEligibleProducts");
			return tosSubmitEligibleProductsResponse;
		}

		/**
		 * @param request
		 * @return
		 */
		private String[] getTOSSubmitEligibleProductsArgs(TOSSubmitEligibleProductsRequest request) {
			
			// ESID='10443720008442246'&MVO_VERTRAG='1234567890'&VKONT='000003040103'&BUKRS='0121'&Product1='3000212'&Product2='3000090'&Product3=''&Product4=''&Product5=''&Product6=''&Product7=''&Product8=''&Product9=''&Product10=''
					
			String[] tosSubmitEligibleProductsInputArgs = new String[14];
			StringBuilder strBuilder = null;
			if(null == request) {
				return tosSubmitEligibleProductsInputArgs;
			}
			
			/*
			 * Important Note:
			 * The order of building the String was made based on the CCS URL parameters input position.
			 * It is advised to keep the order as-is.  If it is required to modify, carefully 
			 * verify the CCS URL and change their position accordingly while building the String. 
			 */
			int iCount = 0;
			// ESID
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getEsid());
			strBuilder.append(SINGLE_QUOTE);
			tosSubmitEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;
			
			//CO Number //VERTRAG
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getCoNumber());
			strBuilder.append(SINGLE_QUOTE);
			tosSubmitEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;
			
			// CA Number  - VKONT
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getCaNumber());
			strBuilder.append(SINGLE_QUOTE);
			tosSubmitEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;
			
			// CO Number - BUKRS
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getCompanyCode());
			strBuilder.append(SINGLE_QUOTE);
			tosSubmitEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;

			// Product1
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getProduct1());
			strBuilder.append(SINGLE_QUOTE);
			tosSubmitEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;

			// Product2
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getProduct2());
			strBuilder.append(SINGLE_QUOTE);
			tosSubmitEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;

			// Product3
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getProduct3());
			strBuilder.append(SINGLE_QUOTE);
			tosSubmitEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;

			// Product4
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getProduct4());
			strBuilder.append(SINGLE_QUOTE);
			tosSubmitEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;
			
			// Product5
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getProduct5());
			strBuilder.append(SINGLE_QUOTE);
			tosSubmitEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;

			// Product6
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getProduct6());
			strBuilder.append(SINGLE_QUOTE);
			tosSubmitEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;

			// Product7
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getProduct7());
			strBuilder.append(SINGLE_QUOTE);
			tosSubmitEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;

			// Product8
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getProduct8());
			strBuilder.append(SINGLE_QUOTE);
			tosSubmitEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;
			
			// Product9
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getProduct9());
			strBuilder.append(SINGLE_QUOTE);
			tosSubmitEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;

			// Product10
			strBuilder = new StringBuilder();
			strBuilder.append(SINGLE_QUOTE);
			strBuilder.append(request.getProduct10());
			strBuilder.append(SINGLE_QUOTE);
			tosSubmitEligibleProductsInputArgs[iCount] = strBuilder.toString();
			iCount++;

			return tosSubmitEligibleProductsInputArgs;
		}
		
		private String buildTOSSubmitEligibleProductsURL() {
			return getEndPointUrl(CCS_TOS_SUBMIT_ELIGIBLE_PRODUCTS_URL);
		}
  
	public CreateContactLogResponse updateContactLog(CreateContactLogRequest request) throws Exception {
		TOSDomain proxy = getTOSDomainProxy();
		long startTime = CommonUtil.getStartTime();
		CreateContactLogResponse response = null;
		String companyCode = COMPANY_CODE_GME;
		try {
			response = proxy.updateContactLog(request);
		} catch (RemoteException ex) {
			logger.error(": Exception while getting data from ccs:"+ex);
			this.utilityloggerHelper.logTransaction("updateContactLog", false, request, ex, "",
					CommonUtil.getElapsedTime(startTime), "", "",companyCode);
		if (logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			throw ex;// it is required to throw exception back to BO layer for
		} catch (Exception ex) {
			logger.error(": Exception while getting data from ccs:"+ex);
			this.utilityloggerHelper.logTransaction("updateContactLog", false, request, ex, "",
					CommonUtil.getElapsedTime(startTime), "", "", companyCode);
			if (logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			throw ex;// it is required to throw exception back to BO layer for
		}
		this.utilityloggerHelper.logTransaction("updateContactLog", false, request, response, response.getErrMessage(),
				CommonUtil.getElapsedTime(startTime), "", "", companyCode);
		if (logger.isDebugEnabled()) {
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}
}
