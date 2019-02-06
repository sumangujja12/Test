/**
 * 
 */
package com.multibrand.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.multibrand.vo.response.GoogleProductSetResponse;


/**
 * @author HChoudhary
 *
 */
@Service
public class ECommerceService extends BaseAbstractService {
		
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	public GoogleProductSetResponse googleProductSet() throws Exception {

		GoogleProductSetResponse response = new GoogleProductSetResponse();
		String url = buildGoogleProductSetURL();
		logger.info("Google Product Set URL ["+url+"]");
		
		HttpHeaders headers = getBasicAuthSpringHttpHeadersForCCS();
		
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactoryForBasicAuth(PROP_CS_DEFAULT_WS_TIMEOUT_IN_SEC));
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
		
		String responseAsString = responseEntity.getBody();
		logger.info("Response received after Google Product Set CCS call : " +responseAsString);
		Gson gson = new Gson();
		if(null != responseAsString) {
			logger.info("Read Google Product Response is NOT empty");
			
			response = gson.fromJson(responseAsString, GoogleProductSetResponse.class);
			
			logger.info("reponse json : "+ response);
			response.setResultCode(RESULT_CODE_SUCCESS);
		}else{
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		}
		return response;
		
	}

	private String buildGoogleProductSetURL() {
		
		return getEndPointUrl(CCS_GOOGLE_PRODUCT_SET_URL);
	}
}
