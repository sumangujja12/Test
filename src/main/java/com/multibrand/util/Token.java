package com.multibrand.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

/**
 * Provides various methods to get tokenized text for Credit Card Number, Bank Account Number, Driver License Number and SSN.
 *  
 * @author mdasari1 - Enhanced from previous version.
 * 
 */
/*
 * Change history
 * [Murali Dasari - 0713/2015]
 * The compatible version of httpclient-4.1.2 having issues while upgrading the NRGREST to Websphere 8.5.5 and Java 7.  
 * Thus team has taken decision to upgrade the httpclient version to 4.5 from 4.1.2 and also updated this Token.java 
 * compatible with httpclient 4.5 version.    
 */
public class Token {

	private static final String CREDIT_CARD_ACTION = "CE";
	private static final String BANK_ACCOUNT_ACTION = "AE";
	private static final String DRIVER_LICENCE_ACTION = "PE";
	private static final String SSN_ACTION = "SE";
	private static final String ERROR_ACTION = "ER";
	private static final String CONTENT_TYPE_HEADER_ACTION = "action";
	private static final String CONTENT_TYPE_HEADER_DATA = "data";
	private static final String SSN_DETOKEN_ACTION = "SD";
	private static final String DRIVER_LICENCE_DETOKEN_ACTION = "PD";
	
	private static LoggerUtil logger = LoggerUtil.getInstance(Token.class.getName());

	/*
	 * @return the creditCardAction
	 */
	public static String getCreditCardAction() {
		return CREDIT_CARD_ACTION;
	}

	/**
	 * @return the bankAccountAction
	 */
	public static String getBankAccountAction() {
		return BANK_ACCOUNT_ACTION;
	}

	/**
	 * @return the driverLicenceAction
	 */
	public static String getDriverLicenceAction() {
		return DRIVER_LICENCE_ACTION;
	}

	/**
	 * @return the ssnAction
	 */
	public static String getSsnAction() {
		return SSN_ACTION;
	}
	
	/**
	 * To tokenize Credit Card Number
	 * 
	 * @param strCCNo - Credit Card Number
	 * @return
	 */
	public static String getToken(String strCCNo){
		return getTokenizeText(CREDIT_CARD_ACTION, strCCNo);
	}

	/**
	 * To tokenize Bank Account Number
	 * @param bankAcctNbr - Bank Account Number
	 * @return
	 */
	public static String getBankAccountToken(String bankAcctNbr) {
		return getTokenizeText(BANK_ACCOUNT_ACTION, bankAcctNbr);
	}
	
	/**
	 * To tokenize Driver License Number
	 * @param drlNumber - Driver License Number
	 * @return
	 */
	public static String getDRLToken(String drlNumber) {
		return getTokenizeText(DRIVER_LICENCE_ACTION, drlNumber);
	}

	/**
	 * To tokenize SSN
	 * @param ssnNumber - SSN
	 * @return
	 */
	public static String getSSNToken(String ssnNumber) {
		return getTokenizeText(SSN_ACTION, ssnNumber);
	}
	
	/**
	 * A generalized token action method.
	 *  
	 * @param tokenAction - The action type to tokenize an input number to a token text. It is required to specify one of the below token action -<br/>
	 * &nbsp;&nbsp;&nbsp;CE - for Credit Card Number<br/>
	 * &nbsp;&nbsp;&nbsp;AE - for Bank Account Number<br/>
	 * &nbsp;&nbsp;&nbsp;PE - for Driver License Number<br/>
	 * &nbsp;&nbsp;&nbsp;SE - for SSN<br/>
	 * @param tobeTokenizedTxt - The number/text to be converted as tokenized text.
	 * @return
	 */
	protected static String getTokenizeText(String tokenAction, String tobeTokenizedTxt) {
		if(StringUtils.isBlank(tokenAction) || StringUtils.isBlank(tobeTokenizedTxt)) {
			throw new RuntimeException("Error due to invalid token action or number/text to be tokenized. Action Requested[" + tokenAction +"] To be tokenized text [" + tobeTokenizedTxt + "]");
		}
		switch (tokenAction) {
		case CREDIT_CARD_ACTION:
			return connDirectTkServer(CREDIT_CARD_ACTION, tobeTokenizedTxt);
		case BANK_ACCOUNT_ACTION:
			return connectTokenServer(BANK_ACCOUNT_ACTION, tobeTokenizedTxt);
		case SSN_ACTION:
			return connectTokenServer(SSN_ACTION, tobeTokenizedTxt);
		case DRIVER_LICENCE_ACTION:
			return connectTokenServer(DRIVER_LICENCE_ACTION, tobeTokenizedTxt);
		case SSN_DETOKEN_ACTION:
			return connectTokenServer(SSN_DETOKEN_ACTION, tobeTokenizedTxt);
		case DRIVER_LICENCE_DETOKEN_ACTION:
			return connectTokenServer(DRIVER_LICENCE_DETOKEN_ACTION, tobeTokenizedTxt);
		default:
			throw new RuntimeException("Invalid token action. Action Requested[" + tokenAction +"] To be tokenized text [" + tobeTokenizedTxt + "]");
		} 
	}
	
	/**
	 * Tokenizes Credit Card Number
	 * 
	 * @param tokenAction - The action type to tokenize an input number to a token text.  
	 * Typically it is 'CE' to tokenize the Credit Card Number for this method.
	 * @param strNum - The number/text to be tokenized. Example any credit card number.
	 * @return String - The tokenized text as a String
	 * @throws RuntimeException - Converts all exceptions and throws it as a runtime exception.
	 */
	private static String connDirectTkServer(String tokenAction, String strNum) throws RuntimeException {

		String statusString = "";
		HttpClient httpclient = null;
		try {
			httpclient = getCloseableHttpClient();
			String url = "";
			statusString += "\n" + "Initializing Token PropertyHolder";
			Properties ph = TokenPropertyHolder.getInstance();
			url = ph.getProperty("tokenServerUrl");
			if (StringUtils.isBlank(url)) {
				throw new RuntimeException("tokenServerUrl value is empty for in TokenPropertyHolder ");
			}
			logger.debug("Server URL = " + url);
			logger.info("URL FROM JNDI = " + url);
			statusString += "\n" + "URL = " + url;
			HttpPost httppost = new HttpPost(url);
			httppost.addHeader("Connection", "close");
			
			// Add post data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair(CONTENT_TYPE_HEADER_ACTION, tokenAction));
	        nameValuePairs.add(new BasicNameValuePair(CONTENT_TYPE_HEADER_DATA, strNum));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		    statusString += "\n" + "Executing client";
		    HttpResponse response = ((CloseableHttpClient) httpclient).execute(httppost);
	    	
		    StatusLine statusLine = response.getStatusLine();
		    
	    	statusString += "\n" + "Client Execution Complete : Status :" +response.getStatusLine();
	    	logger.debug("statusCode = " + response.getStatusLine());
	    	
	    	if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
	    		logger.error("Method failed: " + statusLine.getReasonPhrase());
	    		throw new RuntimeException("Method failed: " + statusLine.getReasonPhrase());
	    	}
            
	    	HttpEntity entity = response.getEntity();
	    	String responseBody = entity == null ? "" : EntityUtils.toString(entity);
	    	
	    	statusString += "\n" + "Response " + responseBody;
	    	String[] NVpair = responseBody.split("&");
	    	if(NVpair.length>0){
	    		//Verify for error action in response (action=ER means error in the response)
	    		for(int i=0; i<NVpair.length; i++){
	    			if(NVpair[i].startsWith(CONTENT_TYPE_HEADER_ACTION)){
	    				String[] nv = NVpair[i].split("=");
	    				if(null != nv[1]) { // written like this to log the action value
	    					logger.debug("action returned from server is ["+nv[1]+"]");
	    					if(nv[1].equalsIgnoreCase(ERROR_ACTION)) {
	    						throw new RuntimeException("Error received from the server (action=ER). Response Body is [" + responseBody + "]");
	    					}
	    				}
	    			}
	    		}
	    		
	    		//Verify for data in the response (data=.... in the response)
	    		for(int i=0;i<NVpair.length;i++){
	    			if(NVpair[i].startsWith(CONTENT_TYPE_HEADER_DATA)){
	    				String[] nv = NVpair[i].split("=");
	    				logger.debug("Post Encrpted Value = " + nv[1]);
	    				if (StringUtils.isBlank(nv[1])) {
	    					throw new RuntimeException("Tokenized text is empty, thus throwing exception.");
	    				}
	    			 	return nv[1];
	    			}
	    		}
	    	}
	    } catch(Exception e){
	    	logger.error("Fatal transport error: " + e.getMessage());
	    	statusString += "\n" + "Fatal protocol violation: " + e.getMessage();
	    	throw new RuntimeException(e);
	    } finally{
	    	// When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            //httpclient.close();
            try {
            	if(null != httpclient) {
            		((CloseableHttpClient) httpclient).close();
            	}
            } catch (Exception ex) {
            	logger.error("Error in closing the HttpClient connection " + ex.getMessage());
            }
	    }
	    return statusString;
	}
	
	
	/**
	 * Tokenizes Bank A/c Number, DRL and SSN
	 * 
	 * @param tokenAction - The action type to tokenize an input number to a token text.  
	 * Typically it is any of 'AE'/'PE'/'SE' to tokenize the Bank account number (or) Driver linense number (or) SSN for this method.
	 * @param strNum - The number/text to be tokenized. Example, any bank a/c number or DL or SSN.
	 * @return String - The tokenized text as a String.
	 * @throws RuntimeException - Converts all exceptions and throws it as a runtime exception. 
	 */
	private static String connectTokenServer(String tokenAction, String strNum) throws RuntimeException {
		String setOfOPPerformed = "";
		HttpClient httpclient = null;
		try {
			httpclient = getCloseableHttpClient();
			String url = "";
			setOfOPPerformed += "Init Token Property holder";
			Properties ph = TokenPropertyHolder.getInstance();
			url = ph.getProperty("cardsecureUrl");
			logger.debug("CardSecure Server URL = " + url);
			if (StringUtils.isBlank(url)) {
				throw new RuntimeException("cardsecureUrl value is empty in TokenPropertyHolder");
			}
			setOfOPPerformed += "CardSecure URL = " + url;
			logger.debug("CardSecure URL = " + url);
			
			HttpPost httppost = new HttpPost(url);
			httppost.addHeader("Connection", "close");
			
			// Add post data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair(CONTENT_TYPE_HEADER_ACTION, tokenAction));
	        nameValuePairs.add(new BasicNameValuePair(CONTENT_TYPE_HEADER_DATA, strNum));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        
	        HttpResponse response = ((CloseableHttpClient) httpclient).execute(httppost);
	        
	    	StatusLine statusLine = response.getStatusLine();
		    setOfOPPerformed += "\n" + "Client Execution Complete : Status :" +response.getStatusLine();
	    	
		    setOfOPPerformed += "\n" + "statusCode = " + statusLine.getStatusCode();
	    	logger.debug("statusCode = " + statusLine.getStatusCode());
	    	
	    	if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
	    		logger.error("Method failed: " + statusLine.getReasonPhrase());
	    		throw new RuntimeException("Method failed: " + statusLine.getReasonPhrase());
	    	}

	    	// Read the response body.
	    	HttpEntity entity = response.getEntity();
	    	String responseBody = entity == null ? "" : EntityUtils.toString(entity);
	    	
	    	setOfOPPerformed += "\n Response Body:" + responseBody;
	    	
	    	String[] NVpair = responseBody.split("&");
	    	if(NVpair.length>0){
	    		//Verify for error action in response (action=ER means error in the response)
	    		for(int i=0; i<NVpair.length; i++){
	    			if(NVpair[i].startsWith(CONTENT_TYPE_HEADER_ACTION)){
	    				String[] nv = NVpair[i].split("=");
	    				if(null != nv[1]) { // written like this to log the action value
	    					logger.debug("action returned from server is ["+nv[1]+"]");
	    					if(nv[1].equalsIgnoreCase(ERROR_ACTION)) {
	    						throw new RuntimeException("Error received from the server (action=ER). Response Body is [" + responseBody + "]");
	    					}
	    				}
	    			}
	    		}
	    		
	    		//Verify for data in the response (data=.... in the response)
	    		for(int i=0;i<NVpair.length;i++){
	    			if(NVpair[i].startsWith(CONTENT_TYPE_HEADER_DATA)){
	    				String[] nv = NVpair[i].split("=");
	    				logger.debug("Post Encrpted Value = " + nv[1]);
	    				if (StringUtils.isBlank(nv[1])) {
	    					throw new RuntimeException("Tokenized text is empty, thus throwing exception.");
	    				}
	    				return nv[1];
	    			}
	    		}
	    	}
	    } catch(Exception e){
	    	logger.error("Fatal transport error: " + e.getMessage());
	    	setOfOPPerformed += "\n" + "Fatal transport error: " + e.getMessage();
	    	throw new RuntimeException(e);
	    } finally {
            try {
            	if(null != httpclient) {
            		((CloseableHttpClient) httpclient).close();
            	}
            } catch (Exception ex) {
            	ex.printStackTrace();
            	//Don't worry about this exception as it is just trying to close the httpclient connection.
            	logger.error("Error in closing the HttpClient connection: " + ex.getMessage());
            }
	    }
	    return setOfOPPerformed;
	}
	
	/**
	 * Create a thread safe CloseableHttpClient to communicate with Token server to tokenize the Bank A/c, CC, SSN and DL
	 *  
	 * @return CloseableHttpClient - Returns the thread safe CloseableHttpClient object
	 * @throws Exception - Throws the exception.
	 */
	public static HttpClient getCloseableHttpClient() throws Exception {
		
		//Create SSLContext by loading the trust store available in the JVM it is running including self-signed certs.    
		SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(new TrustSelfSignedStrategy()).build();

		
		//Create SSL Connection factory. Use of NoopHostnameVerifier prevents accessing the token server other than from the designated host.
		//Note: NoopHostnameVerifier is a hostname verifier essentially turns hostname verification off. It accepts any SSL session as valid and matching the target host.
		//      You should not use DefaultHostnameVerifier in creating the SSLConnectionSocketFactory as this matches any of the alternative names specified by the certificate, 
		//      or in case no alternative names are given the most specific CN of the certificate subject. A wildcard can occur in the CN, and in any of the subject-alts. 
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, NoopHostnameVerifier.INSTANCE);
        HttpClientBuilder builder = HttpClients.custom();
        
        //Set retry count to 3.  This tries 3 times before give-up.
        builder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, false));
        
        //Get the thread safe CloseableHttpClient
        return builder.setSSLSocketFactory(sslsf).build();
	}
	
	
	
	public static String getSSNDeTokenization(String ssnTokenNumber) {
		return getTokenizeText(SSN_DETOKEN_ACTION, ssnTokenNumber);
	}
	
	public static String getDriverLicenceDeTokenization(String dlTokenNumber) {
		return getTokenizeText(DRIVER_LICENCE_DETOKEN_ACTION, dlTokenNumber);
	}

	public static String getSSNDetokenAction() {
		return SSN_DETOKEN_ACTION;
	}

	public static String getDriverLicenceDetokenAction() {
		return DRIVER_LICENCE_DETOKEN_ACTION;
	}
	
	

}