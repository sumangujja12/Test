package com.multibrand.util;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ahanda1
 * Apache httpclient api based XI client for any XI calls
 */

@Component
public class XIApacheClient {

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	/**
	 * This method takes the string URL for XI, Request POJO object and timeout as parameters. Timeout can be null or a string representing milliseconds.
	 * @param strURL
	 * @param request
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	 public static String getResponseBody(String strURL, Object request, Integer timeout) throws Exception {
	        PostMethod    post       =  new PostMethod(strURL);
	        RequestEntity entity     = null;
	        String        responseString   = "";
	        HttpClient    httpclient = null;
	       
	        try {
	            logger.debug("****************************************************");
	            logger.debug("connecting to the URL: " + strURL);
	            httpclient = new HttpClient();
	           
	            
	            // marshalling request
	            String reqString = JAXBUtil.marshal(request);
	            
	            logger.debug("Request : " + request);
	            logger.debug(reqString);
	            logger.info(reqString);
	            
	            entity     = new StringRequestEntity(reqString, "text/xml" /* contentType */, "ISO-8859-1" /* charset */);
	            
	            post.setRequestEntity(entity);

	            // consult documentation for your web service
	            // post.setRequestHeader("SOAPAction", strSoapAction);
	            if ((timeout != null) && (timeout.intValue() > 0)) {
	                httpclient.getParams().setSoTimeout(timeout.intValue());
	            }

	            // Execute request
	            int result = httpclient.executeMethod(post);

	            // Display status code
	            logger.debug("Response Status code: " + result);
	            responseString = post.getResponseBodyAsString();
	            logger.debug("Response body: " + responseString);
	            logger.debug("****************************************************");
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	throw new Exception("getResponseBody() Failed while connecting through Http: ", e);
	        } finally {

	            // Release current connection to the connection pool
	            post.releaseConnection();
	        }

	        return responseString;
	    }
	
	 
}
