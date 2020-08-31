package com.multibrand.service;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.multibrand.util.CommonUtil;

public class AdodeAnalyticService implements Runnable {
	
	private String url = "";
	private Map<String,String> inputJson = null;
	private String iotURL = "";
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	public AdodeAnalyticService (String iotUrl, String url, Map<String,String> inputJson) {
		this.inputJson =inputJson;
		this.url = url;
		this.iotURL = iotUrl;
	}
	
	@Override
	public void run() {
		logger.info(":::::::::: INFO AdodeAnalyticService INPUT JSON:::::::::::");
		logger.info(":::::::::: INFO AdodeAnalyticService endPoint URL:::::::::::" + url);
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("REST REQUEST JSON::::::" + inputJson);
			}
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> responseEntity =  restTemplate.exchange(iotURL, HttpMethod.POST,
					CommonUtil.getHttpEntity(inputJson, url), String.class);
			logger.info("Adobe Analytics Response  [{}]", responseEntity.getStatusCode());
		} catch (Exception ex) {
			logger.error("ERROR OCCURED CALLING THE REST CALL FOR THE URL:::::" + url + "::", ex);
			
		}
		
		
		
	}

	

	
}
