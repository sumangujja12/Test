package com.multibrand.helper;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.multibrand.dto.request.BaseAffiliateRequest;
import com.multibrand.dto.request.SalesBaseRequest;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.service.UtilityService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.JAXBUtil;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.request.LoggingVO;
import com.nrgenergy.utility.webservices.Client;
import com.nrgenergy.utility.webservices.TransactionLogMessage;
import com.nrgenergy.utility.webservices.TransactionLogRequest;


/**
 * 
 * @author ahanda1
 *
 */


@Component
public class UtilityLoggerHelper extends BaseAbstractService implements Constants{
	
	
	@Autowired
	  @Qualifier("appConstMessageSource")
	  protected ReloadableResourceBundleMessageSource appConstMessageSource;
	
	@Autowired
	private AsyncHelper asycHelper = new AsyncHelper();
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	
    public void logTransaction(LoggingVO logvo) throws Exception{
    	logger.info("Start :: UtilityLoggerHelper.logTransaction(LoggingVO)");
    	String strResponse = "";
    	
	   try{
	    TransactionLogRequest txnLogRequest = new TransactionLogRequest();
	    txnLogRequest.setTransactionType(logvo.getTransactionType());
	    txnLogRequest.setDisableMasking(true);
	    txnLogRequest.setSessionID(logvo.getSessionId());
	    if((StringUtils.contains(logvo.getTransactionType(), OE_RESOURCE_API_BASE_PATH) 
	    		|| StringUtils.contains(logvo.getTransactionType(), SALES_API_BASE_PATH))){
	    	Gson gson = new Gson();
	    	txnLogRequest.setRequestData(gson.toJson(logvo.getRequestData()));
	    	txnLogRequest.setResponseData(gson.toJson(logvo.getResponseData()));
	    	
	    } else {
	 	    txnLogRequest.setRequestData(XmlUtil.pojoToXMLwithRootElement(logvo.getRequestData(),logvo.getTransactionType()));
		    txnLogRequest.setResponseData(XmlUtil.pojoToXMLwithRootElement(logvo.getResponseData(), logvo.getTransactionType()));
	    }
	    /*if(StringUtils.isNotBlank(logvo.getUserUniqueId()) && StringUtils.isNotBlank(logvo.getUserId())){
	    	Client client= new Client();
	    	if(StringUtils.isNotBlank(logvo.getConfirmationNumber()))
			client.setConfirmationNumber(CommonUtil.generateConfirmationNumber());
			client.setUserLoginID(logvo.getUserId());
		    client.setUserUniqueID(logvo.getUserUniqueId());
	    txnLogRequest.setClient(client);}*/
	    if(StringUtils.isNotBlank(logvo.getUserId()) 
	    		&& (StringUtils.contains(logvo.getTransactionType(), OE_RESOURCE_API_BASE_PATH) || StringUtils.contains(logvo.getTransactionType(), SALES_API_BASE_PATH))){
	    	Client client= new Client();
	    	client.setUserLoginID(logvo.getUserId());
	    	if(StringUtils.isNotBlank(logvo.getConfirmationNumber())) 	client.setConfirmationNumber(CommonUtil.generateConfirmationNumber());
	    	if(StringUtils.isNotBlank(logvo.getUserUniqueId()))  
	    		client.setUserUniqueID(logvo.getUserUniqueId());
	    	else
	    		client.setUserUniqueID(logvo.getUserId());
		    txnLogRequest.setClient(client);
	    }
	    txnLogRequest.setResponseStatus(logvo.getResponseStatus());
	    txnLogRequest.setTransactionProcessingTimeInMS(logvo.getResponseTime());
	    txnLogRequest.setCompanyCode(logvo.getCompanyCode());
		txnLogRequest.setClientAppName(UTILITY_SERVICE_LOGGING_COMPANY);
		TransactionLogMessage logMessage = new TransactionLogMessage();
		txnLogRequest.setTransactionID(CommonUtil.getGuid());
		logMessage.setTransactionLogRequest(txnLogRequest);
		String strRequest = JAXBUtil.marshal(logMessage);
	    UtilityService utilityService = new UtilityService();
	    logger.debug("UtilityService object :: "+ utilityService);
	    //strResponse = utilityService.logTransaction(strRequest, logvo.getEndPointURL());
		logger.info("Before calling service layer utilityService.logTransaction::::");
	    strResponse = utilityService.logTransaction(strRequest,logvo.getEndPointURL());
	    logger.info("After calling service layer utilityService.logTransaction::::");
		logMessage =  (TransactionLogMessage) JAXBUtil.unmarshal(strResponse, "com.nrgenergy.utility.webservices.TransactionLogMessage");
		logger.info("logging transaction::::"+logMessage.getTransactionLogResponse().getResultStatus().getResultCode());
		if(("Success").equalsIgnoreCase(logMessage.getTransactionLogResponse().getResultStatus().getResultCode())){
			logger.info("THE REQUEST AND RESPONSE FOR THE "+logvo.getTransactionType()+" HAS BEEN LOGGED");
		}else{
			logger.info("THE REQUEST AND RESPONSE FOR THE "+logvo.getTransactionType()+" HAS NOT BEEN LOGGED");
		}
	  }catch(Exception e){
		logger.error("Exception in logTransaction()  : " + e.getMessage());
		logger.error("Cause :: "+ e.getCause());
		}
	   
	   logger.info("End :: UtilityLoggerHelper.logTransaction(LoggingVO)");
	}	
	
  public void logTransaction(String transactionType, boolean isMask, Object requestData,Object responseData, String responseSts, long responseTime, String confirmNum,String sessionId, String companyCode){
		if(isLoggingEnable()){
			logger.info("LOGGING SERVICE IS ENABLED:::");
			try{
				LoggingVO logvo = this.setLoggingVO(transactionType, isMask, requestData, responseData, responseSts, responseTime, confirmNum, sessionId, companyCode);			
				logvo.setEndPointURL(this.envMessageReader.getMessage(Constants.UTILITY_SERVICE_ENDPOINT_URL));
				asycHelper.asychLogging(logvo);
			}catch(Exception e){
				logger.info("Error logging using UtilityService!!! "+e.getMessage());
			}
		}
		
	}
	
	/*public void logTransaction(String transactionType, boolean isMask, Object requestData,Object responseData, String responseSts, long responseTime, String confirmNum){
		
		if(isLoggingEnable()){
			logger.debug("LOGGING SERVICE IS ENABLED:::");
		LoggingVO logvo = this.setLoggingVO(transactionType, isMask, requestData, responseData, responseSts, responseTime, confirmNum, "");
        asycHelper.asychLogging(logvo);
		}
		
	}*/
   
	/*public void logTransaction(String transactionType, boolean isMask, Object requestData,Object responseData, String responseSts, long responseTime,OAMSignupDTO oamSignupDTO){
		logger.debug(" IN LOG TRANSACTION:::::");
		if(isLoggingEnable()){
		logger.debug("LOGGING SERVICE IS ENABLED:::");
		
		LoggingVO logvo = this.setLoggingVO(transactionType, isMask, requestData, responseData, responseSts, responseTime, "", "");
		
	    asycHelper.asychLogging(logvo);
		}
	}*/
	
	private LoggingVO setLoggingVO(String transactionType, boolean isMask, Object requestData,Object responseData, String responseSts, long responseTime, String confirmNum,String sessionId, String companyCode){
		
		LoggingVO logvo = new LoggingVO();
		//logvo.setUserId(StringUtils.defaultIfEmpty(sessUserDTO.getUserId(), ""));
		//logvo.setUserUniqueId(StringUtils.defaultIfEmpty(sessUserDTO.getUserUniqueId(), ""));
		//if(StringUtils.isNotBlank(sessionId))
		logvo.setSessionId(StringUtils.defaultIfEmpty(sessionId, ""));
		//else
		//logvo.setSessionId(StringUtils.defaultIfEmpty("AB2D3EE9A2DD0F41E02566D049916C7E", ""));	
		//logvo.setCompanyCode("0271");
		logvo.setCompanyCode(companyCode);
		logvo.setRequestData(requestData);
		logvo.setResponseData(responseData);
		logvo.setResponseStatus(responseSts);
		logvo.setMask(isMask);
		if(StringUtils.isNotBlank(confirmNum))
		logvo.setConfirmationNumber(confirmNum);
		logvo.setTransactionType(transactionType);
		logvo.setResponseTime(responseTime);
		return logvo;
		
	}
	private boolean isLoggingEnable(){
		String loggingEnable = null;
		/*if(appConstMessageSource == null) {
			envMessageReader = new EnvMessageReader(ENV_PROPERTIES_FILE);
		}*/
		loggingEnable = appConstMessageSource.getMessage(UTILITY_SERVICE_LOGGING_ENABLE, null, null);
		if(("true").equalsIgnoreCase(loggingEnable)){
			return true;
		}else{
			return false;
		}
		
		//return true;
	}

	public void logSalesAPITransaction(String apiName, boolean isLogMaskingRequired, BaseAffiliateRequest request, Response response, long responseTime, String trackingId, String caNumber,
			String affiliateId)  {
		LoggingVO logVO = new LoggingVO();	
		logVO.setTransactionType(Constants.OE_RESOURCE_API_BASE_PATH+"/"+apiName);
		logVO.setCompanyCode(request.getCompanyCode());
		logVO.setRequestData(request);
		logVO.setResponseData(response);
		logVO.setResponseStatus(Integer.toString(response.getStatus()));
		logVO.setMask(isLogMaskingRequired);
		logVO.setUserId(trackingId);
		logVO.setConfirmationNumber(caNumber);
		logVO.setResponseTime(responseTime);
		logVO.setUserUniqueId(affiliateId);
		if(isLoggingEnable()){
			logger.debug("LOGGING SERVICE IS ENABLED:::");
			try{
				logVO.setEndPointURL(this.envMessageReader.getMessage(Constants.UTILITY_SERVICE_ENDPOINT_URL));
				asycHelper.asychLogging(logVO);
			}catch(Exception e){
				logger.error("Error logging using UtilityService!!! "+e.getMessage());
			}
		}
	}
	
	public void logSalesAPITransaction(String apiName, boolean isLogMaskingRequired, SalesBaseRequest request, Response response, long responseTime, String trackingId, String caNumber,
			String affiliateId, String guId) {
		LoggingVO logVO = new LoggingVO();	
		logVO.setTransactionType(Constants.SALES_API_BASE_PATH+"/"+apiName);
		logVO.setCompanyCode(request.getCompanyCode());
		logVO.setRequestData(request);
		logVO.setResponseData(response);
		logVO.setResponseStatus(Integer.toString(response.getStatus()));
		logVO.setMask(isLogMaskingRequired);
		logVO.setUserId(trackingId);
		logVO.setConfirmationNumber(caNumber);
		logVO.setResponseTime(responseTime);
		logVO.setUserUniqueId(affiliateId);
		logVO.setSessionId(guId);
		if(isLoggingEnable()){
			logger.debug("LOGGING SERVICE IS ENABLED:::");
			try{
				logVO.setEndPointURL(this.envMessageReader.getMessage(Constants.UTILITY_SERVICE_ENDPOINT_URL));
				asycHelper.asychLogging(logVO);
			}catch(Exception e){
				logger.error("Error logging using UtilityService!!! "+e.getMessage());
			}
		}
	}
	
}
