package com.multibrand.handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
public class JaxWsLoggingHandler implements SOAPHandler<SOAPMessageContext> {
 

	private static final Logger LOG = LogManager.getLogger(JaxWsLoggingHandler.class);
    @Override
    public boolean handleFault(SOAPMessageContext context) {
        
    	LOG.info("***handleFault***");
    	
    	logSoapMessage(context);
    	
        return true;
    }
 
    @Override
    public boolean handleMessage(SOAPMessageContext context) {
    	
    	LOG.info("***handleMessage***");
    	
         logSoapMessage(context);
         
        return true;
    }
 


	@Override
	public void close(MessageContext arg0) {
		LOG.info("_______________close_____________ ");
	}

	@Override
	public Set<QName> getHeaders() {
		return new HashSet<QName>();
	}
	
    private void logSoapMessage(SOAPMessageContext context) {
        Boolean isOutBound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        SOAPMessage soapMsg = context.getMessage();
 
        try {
            if (isOutBound) {
            	LOG.info("Intercepting outbound message:");
            } else {
            	LOG.info("Intercepting inbound message:");
            }
 
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            soapMsg.writeTo(baos);
            LOG.info(baos);
 
            LOG.info("\n________________________________");
 
        } catch (SOAPException | IOException e) {
        	LOG.error("Error in handleFault.  Error is:{}",e.getMessage());
        } 
    }
 
}

