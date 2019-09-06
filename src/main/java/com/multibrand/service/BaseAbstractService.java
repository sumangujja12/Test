package com.multibrand.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
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
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;
import com.multibrand.web.i18n.WebI18nMessageSource;


/**
 * 
 * @author ahanda1
 * 
 * This is the base service class for fetching the proxy objects for all soap web service clients.
 * 
 */
@Service
public class BaseAbstractService implements Constants{
	
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	protected EnvMessageReader envMessageReader;
	
	@Autowired
	@Qualifier("appConstMessageSource")
	protected ReloadableResourceBundleMessageSource appConstMessageSource;
	
	@Resource(name = "webI18nMessageSource")
	protected WebI18nMessageSource msgSource;
	
	/**
	 * Get the Spring based HttpHeaders instance by having Basic Auth details to for CCS
	 * @return
	 */
	public HttpHeaders getBasicAuthSpringHttpHeadersForCCS() {
		
		String plainCreds = this.envMessageReader.getMessage(CCS_USER_NAME)+":"+ this.envMessageReader.getMessage(CCS_PASSWORD);
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		
		HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add(BASIC_AUTH_HEADER_NAME, AUTH_TYPE_BASIC + " " + base64Creds);

		return headers;
	}
	
	/**
	 * @author ahanda1
	 * 
	 * This is generic method will used to get the Proxy of target Web Service by given class Proxy 
	 * and endpoint URL of target web-service
	 * @param srvProxyClass
	 * @param endPointUrlKey
	 * @return
	 */
	public Object getServiceProxy(Class srvProxyClass,  String endPointUrlKey  ) {
		Service service = null;
		Object proxyObject = null;
		String endPoint = null;
		Class[] parmTypes = {java.net.URL.class,javax.xml.rpc.Service.class};
		
		try {
			
				endPoint = getEndPointUrl(endPointUrlKey);
				//endPoint= "http://txaixebnbld01:9088/NRGWS/SwapDomain";
				logger.info("endPoint ================================== "+endPoint);
				Constructor constructor = srvProxyClass.getConstructor(parmTypes);
				proxyObject =  constructor.newInstance(new Object[] { new java.net.URL(endPoint) ,service });  
				try {
					Method method = srvProxyClass.getSuperclass().getDeclaredMethod("setTimeout", Integer.TYPE);
				   // int timeout = getWSConnTimeout(endPointUrlKey);
				    method.invoke(proxyObject, WEBSERVICE_CALL_TIMEOUT * 1000);
				}
				catch(Exception e) {
					//logger.warn("Setting timeout has been failed:" +e.getMessage());
					logger.error("Setting timeout has been failed:"+e.getMessage());
				}
				
			} catch (Exception e) {
				//throw new ServiceException("getServiceProxy() Failed :", e);
				logger.error("ServiceException :: "+e.getMessage());
			} catch (Throwable t) {
				//throw new ServiceException("getServiceProxy() Failed :", t);
				logger.error("Throwable ServiceException ::"+t.getMessage());
			}	
		return proxyObject;
	}
    
	
	public String getUsageHistoryRestUrl(String endPointUrl, String resource){
		String url = "";
		try{
			url = getEndPointUrl(endPointUrl)+resource;
		}catch(Exception ex){
			logger.error("ServiceException :: "+ex.getMessage());
		}
		return url;
	}
	
	
	/*private int getWSConnTimeout(String srvEndPointUrlKey){
		int timeout = 45;
		String timeoutStr=null;
		try {
			try{
				timeoutStr = getEnvMessageReader().getMessage(srvEndPointUrlKey +".timeout" );
			}
			catch(Exception e){
				timeoutStr = getEnvMessageReader().getMessage(PROP_DEFAULT_WS_TIMEOUT_IN_SEC);
			}
			if (StringUtils.isBlank(timeoutStr)) {			
				timeout = Integer.parseInt(timeoutStr);
			}
		}
		catch(Exception e) {
			logger.warn("getWSConnTimeout: the property in environment.properties '" +PROP_DEFAULT_WS_TIMEOUT_IN_SEC  +"' is not a number" + timeoutStr +" :: Message:" +e.getMessage());
			timeout = 45;
		}		
		timeout = timeout * 1000;
		return timeout;
	}*/
	
	/*public String getEndPointUrl(String srvEndPointUrlKey) {
		String endPointUrl = CommonUtil.jndiLookup(srvEndPointUrlKey); 
		return endPointUrl;
	}*/
	
	public String getEndPointUrl(String srvEndPointUrlKey) {
		
		String endPointUrl = null;
		try{
			endPointUrl = getEnvMessageReader().getMessage(srvEndPointUrlKey);	
			
		}catch(Exception ex){
			logger.error("error occured while getting the environment key::::"+srvEndPointUrlKey);
		}
		return endPointUrl;
	}

	/**
	 * @param srvEndPointUrlKey
	 * @param args
	 * @return
	 */
	public String getEndPointUrl(String srvEndPointUrlKey, Object[] args) {
		String endPointUrl = null;
		endPointUrl = getEnvMessageReader().getMessage(srvEndPointUrlKey, args, false, true);;	
		logger.info("inside getEndPointUrl:: endpointurl is :: "+endPointUrl);
		return endPointUrl;
	}
	
	public String getTimeOutForKey(String timeOutKey) {
		
		String timeOutVal = null;
		timeOutVal = getEnvMessageReader().getMessage(timeOutKey);
		return timeOutVal;
	}
	public EnvMessageReader getEnvMessageReader() {
		
		
		if(envMessageReader == null) {
			logger.info("EnvMessageReader is null coz of Asynchronize call: Loading new instance of EnvMessageReader with " +ENV_PROPERTIES_FILE);
			envMessageReader = new EnvMessageReader(ENV_PROPERTIES_FILE);
		}
		
		return envMessageReader;
	}
	
	/**
	 * @author jyogapa1
	 * @param keyName
	 * @param locale
	 * 
	 * @return
	 * @throws Exception
	 * @author jyogapa1
	 */
	protected String getAppProperty(String keyName, Locale locale) {

		String propertyValue = null;
		
		propertyValue = CommonUtil.getAppProperty(appConstMessageSource, keyName, locale);

		return propertyValue;

	}
	

	/**
	 * @author jyogapa1
	 * @param keyName
	 * @return
	 * @throws Exception
	 * @author jyogapa1
	 */
	protected String getAppProperty(String keyName) {

		String propertyValue = null;
		
		propertyValue = CommonUtil.getAppProperty(appConstMessageSource, keyName);

		return propertyValue;

	}
	//START: US1629: Logic for name in Title Case - adadan
	 protected static String toTitleCase(String input) {

	        StringBuilder titleCase = new StringBuilder();
	        boolean nextTitleCase = true;

	        for (char c : input.toLowerCase().toCharArray()) {
	            if (!Character.isLetterOrDigit(c)) {
	                nextTitleCase = true;
	            } else if (nextTitleCase) {
	                c = Character.toTitleCase(c);
	                nextTitleCase = false;
	            }
	            titleCase.append(c);
	        }

	        return titleCase.toString();
	    }
	//END: US1629: Logic for name in Title Case - adadan
	 
	 	/**
		 * @return
		 * @throws Exception
		 */
		protected ClientHttpRequestFactory clientHttpRequestFactoryForBasicAuth() throws Exception {
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

			int timeOut = getRestCallTimeOut();
			if (logger.isDebugEnabled()) {
				logger.debug("TIME OUT FOR THE REST CALL::::::" + timeOut);
			}
			factory.setReadTimeout(timeOut);
			factory.setConnectTimeout(timeOut);
			//factory.setHttpClient(getCloseableHttpClient());
			return factory;
		}
		/**
		 * @return
		 * @throws Exception
		 */
		protected ClientHttpRequestFactory clientHttpRequestFactoryForBasicAuth(String timeOutInSec) throws Exception {
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

			int timeOut = getRestCallTimeOut(timeOutInSec);
			if (logger.isDebugEnabled()) {
				logger.debug("TIME OUT FOR THE REST CALL::::::" + timeOut);
			}
			factory.setReadTimeout(timeOut);
			factory.setConnectTimeout(timeOut);
			return factory;
		}
		
		/**
		 * @return
		 */
		protected int getRestCallTimeOut() {
			String timeOutStr = getTimeOutForKey(PROP_CS_DEFAULT_WS_TIMEOUT_IN_SEC);
			if (org.apache.commons.lang.StringUtils.isNotBlank(timeOutStr)) {
				return Integer.parseInt(timeOutStr) * 1000;
			}
			return 60 * 1000;
		}	
		
		/**
		 * @return
		 */
		protected int getRestCallTimeOut(String timeOutInSecPropKey) {
			String timeOutStr = getTimeOutForKey(timeOutInSecPropKey);
			if (org.apache.commons.lang.StringUtils.isNotBlank(timeOutStr)) {
				return Integer.parseInt(timeOutStr) * 1000;
			}
			return 60 * 1000;
		}

		protected int getRestCallTimeOut(String timeOutInSecPropKey, int defaultTimeoutInSec) {
			String timeOutStr = getTimeOutForKey(timeOutInSecPropKey);
			if (org.apache.commons.lang.StringUtils.isNotBlank(timeOutStr)) {
				return Integer.parseInt(timeOutStr) * 1000;
			}
			return (defaultTimeoutInSec > 0 ? (defaultTimeoutInSec * 1000) : (45 * 1000));
		}

		/**
		 * //START : OE :Sprint62 :US21019 :Kdeshmu1
		 * @param requestObject
		 * @param restURL
		 * @param timeOutSec
		 * @return
		 */
public <T> String createAndCallServiceReturnStatus(T requestObject, String restURL, String timeOutSec){
			
			Gson gson = new Gson();
			String restResponse = null;
			HttpStatus status = null;
			String url = getEndPointUrl(restURL);
			logger.info("Other Services URL:"+url);
			try{
				String request = gson.toJson(requestObject);

				logger.info("Other Services URL request param:"+request);
				//if(logger.isDebugEnabled()){logger.debug("REQUEST JSON::::::"+request);}
				RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactoryForBasicAuth(timeOutSec));
				HttpHeaders headers = new HttpHeaders();
				headers.add("Authorization", getBasicAuthHeader());
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> entity = new HttpEntity<String>(request, headers);
				//Map<String, String> map = new HashMap<String, String>();
				ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
				//restResponse = null != responseEntity.getBody()?responseEntity.getBody():"";
				status = responseEntity.getStatusCode();
				restResponse = status.toString();
				
			}catch(HttpServerErrorException httpEx){
				logger.error("HttpServerErrorException OCCURED CALLING THE REST CALL FOR THE URL:::::"+url+"::"+httpEx.getMessage());			
				return "500";
			} catch(Exception ex){
				logger.error("ERROR OCCURED CALLING THE REST CALL FOR THE URL:::::"+url+"::",ex);			
				return "400";
			}
			logger.info("Other Services URL restResponse :"+restResponse);
			
			return restResponse;
		}
//END : OE :Sprint62 :US21019 :Kdeshmu1
		/**
		 * Get encoded string representing HTTP Basic authorization credentials for
		 * the request.
		 */
		protected String getBasicAuthHeader() {
			String user = getEnvMessageReader().getMessage(REST_API_USER_NAME);
			String password = getEnvMessageReader().getMessage(REST_API_PASSWORD);
			String token = user + ":" + password;

			byte[] encodedToken = Base64.encodeBase64(token.getBytes());

			return "Basic " + new String(encodedToken);
		}
	 
}
