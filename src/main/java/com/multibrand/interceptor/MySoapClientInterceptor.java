package com.multibrand.interceptor;

import org.apache.axis.utils.XMLUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapBody;
import org.springframework.ws.soap.SoapEnvelope;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapMessage;
import org.w3c.dom.Element;
import javax.xml.transform.Result;

import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import com.multibrand.exception.WebServiceException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MySoapClientInterceptor implements ClientInterceptor {


	Logger logger = LogManager.getLogger("NRGREST_LOGGER");


    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {

        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {

        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {

       	logger.info("intercepted a fault...");
        SoapBody soapBody = getSoapBody(messageContext);
        SoapFault soapFault = soapBody.getFault();
        logger.error(soapFault.getFaultStringOrReason());
		try {
			throw new WebServiceException(String.format("Error occured while invoking SOAP service - %s ", soapFault.getFaultStringOrReason()));
		} catch (WebServiceException e) {
			logger.error("WebServiceException:{}",e);
		}
		return true;

    }

    public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException {

    }

    private SoapBody getSoapBody(MessageContext messageContext) {
        SoapMessage soapMessage = (SoapMessage) messageContext.getResponse();
        SoapEnvelope soapEnvelope = soapMessage.getEnvelope();
        return soapEnvelope.getBody();
    }    
}  