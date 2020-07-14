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
    private Element getFirstChildElement(Element el) {
        return getFirstChildElement(el, null);
    }
    
    private Element getFirstChildElement(Element el, String tagName) {
        Element childEl = null;
        NodeList nlist = el != null ? el.getChildNodes() : null;
        int len = nlist != null ? nlist.getLength() : 0;
        for (int i = 0; childEl == null && i < len; i++) {
            Node node = nlist.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (tagName == null || tagName.equals(node.getLocalName()))
                    childEl = (Element) node;
            }
        }
            String responseObtained = convertNodeToXMLString(childEl);
            logger.info("Response obtained: %s" + responseObtained);
        return childEl;
    }   
    
    /**
	 * Convert a org.w3c.dom.Node into an XML representation.
	 * @param node - containing an xml tree.
	 * @return - String containing the XML.
	 */
	public  String convertNodeToXMLString(Node node)
	  {
		  String xml=null;
		  try {
		      Source source = new DOMSource(node);
		      StringWriter stringWriter = new StringWriter();
		      javax.xml.transform.Result result = new StreamResult(stringWriter);
		      TransformerFactory factory = TransformerFactory.newInstance();
		      Transformer transformer = factory.newTransformer();
		      transformer.transform(source, result);
		      xml=stringWriter.getBuffer().toString();
		  } catch (TransformerConfigurationException e) {
			  logger.error("Could not obtain the XML. ", e);
		  } catch (TransformerException e) {
			  logger.error("Could not obtain the XML. ",e);
		  }
		  return xml;
	  }    
}  