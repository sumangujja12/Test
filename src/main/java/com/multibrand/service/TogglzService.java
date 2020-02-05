package com.multibrand.service;

import java.net.URI;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;
import com.multibrand.util.LoggerUtil;
import com.multibrand.vo.request.FeatureVO;



@Service
public class TogglzService implements Constants {

	@Autowired
	protected EnvMessageReader envMessageReader;
	
	LoggerUtil logger = LoggerUtil.getInstance("NRGREST_LOGGER");
	
	public String isFeatureEnabled(FeatureVO featureVO) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(buildRestURL() + "/getFeatureStatus")
				.queryParam("groupName", featureVO.getGroupName())
				.queryParam("featureName", featureVO.getFeatureName());

		return getResponseEntity(builder.build().toUri());
	}

	private String buildRestURL() {
		return getEndPointUrl(TOGGLZ_REST_BASE_URL);
	}
	
	public String getResponseEntity(final URI uri) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		return makeRestServiceCall(uri, HttpMethod.GET, entity);
	}

	public String getEndPointUrl(String srvEndPointUrlKey) {

		String endPointUrl = null;
		endPointUrl = envMessageReader.getMessage(srvEndPointUrlKey);
		return endPointUrl;
	}
	
	private String makeRestServiceCall(final URI uri, HttpMethod httpMethod, HttpEntity<?> entity) {

		ResponseEntity<String> responseEntity = null;
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
		responseEntity = restTemplate.exchange(uri, httpMethod, entity, String.class);
		return responseEntity.getBody();
	}
	
	private ClientHttpRequestFactory clientHttpRequestFactory() {

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

		// Default timeout is 30 seconds
		int timeOut = 30 * 1000;
		String timeOutStr = getTimeOutForKey(PROP_DEFAULT_WS_TIMEOUT_IN_SEC);
		
		if(StringUtils.isNotBlank(timeOutStr))
		{
			timeOut = Integer.parseInt(timeOutStr) * 1000;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("TIME OUT FOR THE REST CALL::::::" + timeOut);
		}

		factory.setReadTimeout(timeOut);
		factory.setConnectTimeout(timeOut);

		return factory;
	}
	
	public String getTimeOutForKey(String timeOutKey) {

		String endPointUrl = null;
		endPointUrl = envMessageReader.getMessage(timeOutKey);
		return endPointUrl;
	}
}
