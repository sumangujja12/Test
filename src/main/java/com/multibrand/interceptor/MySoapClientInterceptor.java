package com.multibrand.interceptor;

import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.jackson.XmlConstants;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapBody;
import org.springframework.ws.soap.SoapEnvelope;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapMessage;
import com.multibrand.exception.WebServiceException;

public class MySoapClientInterceptor implements ClientInterceptor {


	Logger logger = LogManager.getLogger("NRGREST_LOGGER");


    @Override
    public boolean handleRequest(MessageContext messageContext) {

        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) {

        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) {

       	logger.info("intercepted a fault...");
        SoapBody soapBody = getSoapBody(messageContext);
        SoapFault soapFault = soapBody.getFault();

        TransformerFactory factory = TransformerFactory.newInstance();
        
        Transformer transformer;
        String strResult = "";
		try {
			
			Source sourceInput = soapFault.getSource();
			StringWriter outWriter = new StringWriter();
			
			Result result = new StreamResult(outWriter);
			
			factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			
			transformer = factory.newTransformer();
			transformer.transform(sourceInput, result);
			strResult = outWriter.getBuffer().toString();
			
			throw new WebServiceException(String.format("Error occured while invoking SOAP service - %s ", strResult));
		} catch (WebServiceException e) {
			logger.error("WebServiceException:{}",e);
		} catch (TransformerConfigurationException  e) {
			logger.error("TransformerConfiguration Exception:{}",e);
		} catch (TransformerException e) {
			logger.error("Transformer Exception:{}",e);
		}
		return true;

    }

   
    private SoapBody getSoapBody(MessageContext messageContext) {
        SoapMessage soapMessage = (SoapMessage) messageContext.getResponse();
        SoapEnvelope soapEnvelope = soapMessage.getEnvelope();
        return soapEnvelope.getBody();
    }    
}  