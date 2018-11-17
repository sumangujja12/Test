package com.multibrand.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.multibrand.util.CommonUtil;


/*
 * @Author bbachin1
 */

@Component
public class BaseRestService extends BaseAbstractService {
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	
	public String createRestTemplateAndCallService(String restReqJSON , String urlConstant, String endPoint){
		
		String restResponse = null;
		String url = buildRestURL(urlConstant,endPoint);
		try{
			if(logger.isDebugEnabled()){logger.debug("REST REQUEST JSON::::::"+restReqJSON);}
			RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType( MediaType.APPLICATION_JSON );
			HttpEntity<String> entity = new HttpEntity<String>(restReqJSON, headers);
			Map<String, String> map = new HashMap<String, String>();
			ResponseEntity<String> responseEntity= restTemplate.postForEntity(url, entity, String.class, map);
			restResponse = null != responseEntity.getBody()?responseEntity.getBody():"";
		}catch(Exception ex){
			logger.error("ERROR OCCURED CALLING THE REST CALL FOR THE URL:::::"+url+"::",ex);
			return CommonUtil.getErrorJson(ex, "001");
		}
		return restResponse;
	}
	
	
	private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        //int timeOut = getRestCallTimeOut();
        int timeOut = getRestCallTimeOut(PROP_DEFAULT_WS_TIMEOUT_IN_SEC, 45);
        if(logger.isDebugEnabled()){logger.debug("TIME OUT FOR THE REST CALL::::::"+timeOut);}
        factory.setReadTimeout(timeOut);
        factory.setConnectTimeout(timeOut);
        return factory;
    }
	
	
	private String buildRestURL(String restUrl, String endPoint){
		
		if(StringUtils.equalsIgnoreCase(endPoint, "PSZ")){
			return getEndPointUrl(BASE_PERSONALIZE_REST_URL)+restUrl;
		}
		if(StringUtils.equalsIgnoreCase(endPoint, "SDL")){
			return getEndPointUrl(BASE_SDL_REST_URL)+restUrl;
		}
		return BASE_PERSONALIZE_REST_URL;
	}
	/*
	private int getRestCallTimeOut(){
		String timeOutStr = getEndPointUrl(PROP_DEFAULT_WS_TIMEOUT_IN_SEC);
		if(StringUtils.isNotBlank(timeOutStr)){
			return Integer.parseInt(timeOutStr)*1000;
		}
		return 45*1000;
	}
	*/
}

