package com.multibrand.service;

import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.multibrand.domain.CheckPrepayOfferRequest;
import com.multibrand.domain.CheckPrepayOfferResponse;
import com.multibrand.domain.SSDomain;
import com.multibrand.domain.SSDomainPortBindingStub;
import com.multibrand.domain.StartTollTagMonitorRequest;
import com.multibrand.domain.StartTollTagMonitorResponse;
import com.multibrand.dto.request.UsageHistoryRequest;
import com.multibrand.dto.response.DailyTemperatureResponse;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;

/**
 * 
 * @author gkrishn1
 *
 * This class is responsible for fetching information from  NRG Services SSDomain 
 */

@Service
public class SSService extends BaseAbstractService {
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	/**
	 * This will return SSDomainProxy and set EndPoint URL
	 * @return ssDomainProxy The SSDomainProxy Object
	 */
	protected SSDomain getSSDomainProxy(){
        
		return (SSDomain) getServiceProxy(SSDomainPortBindingStub.class, SS_SERVICE_ENDPOINT_URL);
		
	}
	
	public String getTemperatureDataServiceProxy() throws ServiceException {
		return getUsageHistoryRestUrl(SM_TEMP_SERVICE_ENDPOINT_URL,USAGE_HISTORY_REST_PREPAY_TEMPDATA);
	}
	
	/**
	 * 
	 * @param checkPrepayOfferRequest
	 * @return CheckPrepayOfferResponse  
	 * @throws ServiceException
	 */
	public CheckPrepayOfferResponse checkPrepayOffer(CheckPrepayOfferRequest checkPrepayOfferRequest,String sessionId) throws ServiceException{
		logger.debug("SSService.checkPrepayOffer : Entering the method:" );
		CheckPrepayOfferResponse checkPrepayOfferResponse = null;
		long startTime = CommonUtil.getStartTime();
		try{
			SSDomain swapproxyclient = getSSDomainProxy();
			checkPrepayOfferResponse = swapproxyclient.checkIfPrePayOffer(checkPrepayOfferRequest);
			
			logger.debug("PrepayOfferFlag:::::::::::::::"+checkPrepayOfferResponse.getPrepayOfferFlag());
			if(StringUtils.isNotBlank(checkPrepayOfferResponse.getErrorMessage())){
				logger.debug("SSService.checkPrepayOffer() Error Message:" + checkPrepayOfferResponse.getErrorMessage());
				logger.debug("SSService.checkPrepayOffer() Error Code:" + checkPrepayOfferResponse.getErrorCode());
			}
				
		} catch (Exception e) {
			logger.error("SSService.checkPrepayOffer : Exception from NRGWS Service call:"+ e.getMessage());
			utilityloggerHelper.logTransaction("checkPrepayOffer", false, checkPrepayOfferRequest,checkPrepayOfferResponse, checkPrepayOfferResponse.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, checkPrepayOfferRequest.getCompanyCode());
			throw new ServiceException("MSG_ERR_CHECK_PREPYA_OFFER_CCS");
		} 
		utilityloggerHelper.logTransaction("checkPrepayOffer", false, checkPrepayOfferRequest,checkPrepayOfferResponse, checkPrepayOfferResponse.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, checkPrepayOfferRequest.getCompanyCode());
		logger.debug("SSService.checkPrepayOffer() : end : ::checkPrepayOfferResponse - "+checkPrepayOfferResponse);
		return checkPrepayOfferResponse; 
	}
	
	/**
	 * Calling SSDomain startTollTagMonitor operation.
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public StartTollTagMonitorResponse startTollTag(StartTollTagMonitorRequest request,String sessionId)throws ServiceException{
		
		StartTollTagMonitorResponse response = new StartTollTagMonitorResponse();
		long startTime = CommonUtil.getStartTime();
		try{
			SSDomain swapproxyclient = getSSDomainProxy();
			response = swapproxyclient.tollTagMonitor(request);
			
			if(StringUtils.isNotBlank(response.getErrorMessage())){
				logger.debug("SSService.startTollTagMonitor() Error Message:" + response.getErrorMessage());
				logger.debug("SSService.startTollTagMonitor() Error Code:" + response.getErrorCode());
			}
				
		} catch (Exception e) {
			logger.error("SSService.startTollTagMonitor : Exception from NRGWS Service call:"+ e.getMessage());
			utilityloggerHelper.logTransaction("startTollTagMonitor", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw new ServiceException("ERR_START_TOLLTAG");
		} 
		utilityloggerHelper.logTransaction("startTollTagMonitor", false, request,response,response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		logger.debug("SSService.startTollTagMonitor() : end : ::startTollTagMonitor - "+response);
		
		return response;
	}
	
	
	/**
	 * Get Balance and Usage Data
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public DailyTemperatureResponse getTemperatureData(UsageHistoryRequest usageHistoryRequest) throws Exception {

		DailyTemperatureResponse response = null;
		Gson gson = new Gson();
		String url = getTemperatureDataServiceProxy();
		try{
			String usageHistoryReq = gson.toJson(usageHistoryRequest, UsageHistoryRequest.class);
			if(logger.isDebugEnabled()){logger.debug("USAGE HISTORY REQUEST JSON::::::"+usageHistoryReq+"::AND URL::"+url);}
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType( MediaType.APPLICATION_JSON );
			HttpEntity<String> entity = new HttpEntity<String>(usageHistoryReq, headers);
			Map<String, String> map = new HashMap<String, String>();
			ResponseEntity<String> responseEntity= restTemplate.postForEntity(url, entity, String.class, map);
			String restResponse = null != responseEntity.getBody()?responseEntity.getBody():"";
			if(logger.isDebugEnabled()){logger.debug("USAGE HISTORY RESPONSE JSON::::::"+restResponse+"::AND URL::"+url);}
			response = gson.fromJson(restResponse, DailyTemperatureResponse.class);
		}catch(Exception ex){
			logger.error("ERROR OCCURED CALLING THE REST CALL FOR THE URL:::::"+url+"::",ex);
		}
		return response;
	}
	
	private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        int timeOut = 20*1000;
        if(logger.isDebugEnabled()){logger.debug("TIME OUT FOR THE REST CALL::::::{}",timeOut);}
        factory.setReadTimeout(timeOut);
        factory.setConnectTimeout(timeOut);
        return factory;
    }
}
