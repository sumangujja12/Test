package com.multibrand.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

public class MySoapClientInterceptor implements ClientInterceptor {


	Logger logger = LogManager.getLogger("NRGREST_LOGGER");


    @Override
    public boolean handleRequest(MessageContext messageContext) {

    	logger.info("Request :");
    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    	
	   	 try {
	   		 
				messageContext.getRequest().writeTo(outputStream);
		        String httpMessage = new String(outputStream.toByteArray());
		        
		        logger.debug("SOAP Request= :{}",httpMessage);
		        
			} catch (IOException exception) {
				logger.error("Exception Occured in RuntimeException  handleRequest {} ", exception.getMessage());
			} finally {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error("Exception Occured in finally closing resources in handleRequest{} ", e.getMessage());
				}
			}
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) {

    	logger.info("Response :");
    	
    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    	
    	 try {
    		 
			messageContext.getResponse().writeTo(outputStream);
	        String httpMessage = new String(outputStream.toByteArray());
	        
	        logger.debug("SOAP Response= :{}",httpMessage);
	        
		} catch (IOException exception) {
			logger.error("Exception Occured in RuntimeException  handleResponse {} ", exception.getMessage());
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				logger.error("Exception Occured in finally closing handleResponse {} ", e.getMessage());
			}
		}
        return true;
    }

    @Override
	public boolean handleFault(MessageContext messageContext) {

    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    	
		try {

			messageContext.getResponse().writeTo(outputStream);
	        String httpMessage = new String(outputStream.toByteArray());

			throw new RuntimeException(httpMessage);
			
		} catch (IOException e) {
			logger.error("IOException Exception:{}", e.getMessage());
		}
		return true;

	} 
}  