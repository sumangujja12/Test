package com.multibrand.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 */


//~--- non-JDK imports --------------------------------------------------------

/*import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;*/
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;

//~--- JDK imports ------------------------------------------------------------

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;

import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author nulthi1
 * @version 1.0
 *
 */
public class XmlUtil {
	
	/**
	 * The LOGGER
	 */
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	/**
     * <pre>
     * Gets a Formated String from an XML File
     * </pre>
	 * 
	 * @param fileName
	 * 			A physical path of an any Xml file to be converted to String.
	 * @return String
	 */
    public static String convertXMLFileToString(String fileName) {
        InputStream is              = null;
        String      convertedString = null;

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, ""); 
            documentBuilderFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

            // URL url = XmlUtil.class.getClassLoader().getResource(fileName);
            is = new FileInputStream(new File(fileName));

            Document     doc        = documentBuilderFactory.newDocumentBuilder().parse(is);
            StringWriter stw        = new StringWriter();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, ""); 
            
            Transformer  serializer = transformerFactory.newTransformer();
           

            serializer.transform(new DOMSource(doc), new StreamResult(stw));
            convertedString = stw.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error while writing the document into String:" + e);
        } finally {
            try {
            	if (is != null) {
            		is.close();
            	}
            } catch (Exception e) {
            	 logger.error("convertXMLFileToString() InputStream Close Exception:{}" , e.getMessage());
            }
        }

        return convertedString;
    }
    
    /**
     * <pre>
     * Gets org.w3c.dom.Document instance from InputStream of an any Xml file. 
     * </pre>
     * 
     * @param inputStream
     * @return Document
     */
    public static Document createDocument(InputStream inputStream) {
        DocumentBuilder builder = null;
        Document        doc     = null;

        builder = createDocumentBuilder();

        try {
            doc = builder.parse(inputStream);
        } catch (SAXException e) {
            throw new RuntimeException("Error while parsing the document:" + e);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading the document:" + e);
        }

        return doc;
    }
    
    /**
     * <pre>
     *  Gets org.w3c.dom.Document instance from Reader of an any Xml file. 
     * </pre>
     * 
     * @param reader
     * @return Document
     */
    public static Document createDocument(Reader reader) {
        DocumentBuilder builder = null;
        Document        doc     = null;

        builder = createDocumentBuilder();

        try {
            doc = builder.parse(new InputSource(reader));
        } catch (SAXException e) {
            throw new RuntimeException("Error while parsing the document:" + e);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading the document:" + e);
        }

        return doc;
    }
    
    /**
     * <pre>
     * gets org.w3c.dom.Document instance from A physical path 
     * (Either absolute path or from relative path from classpath) of an any XML file.
     * </pre>
     * 
     * @param configFilePath
     * 			A physical path (Either absolute path or from relative path from classpath)
     *           of an any XML file to be created as org.w3c.dom.Document instance.
     * @return Document
     */
    public static Document createDocument(String configFilePath) {
        DocumentBuilder builder = null;
        Document        doc     = null;

        try {
            URL url = XmlUtil.class.getClassLoader().getResource(configFilePath);

            if (url == null) {
            	
            	File file = new File(configFilePath);
            	doc  =  createDocument(new FileInputStream(file));
            	
            }
            else {
            
		            builder = createDocumentBuilder();
		            doc = builder.parse(url.toString());
            }
        } catch (SAXException e) {
            throw new RuntimeException("Error while parsing the document:" + e);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading the document:" + e);
        }

        return doc;
    }
    
    /**
     * <pre>
     * sets all core configuration of xml DocumentBuilderFactory 
     * and returns the javax.xml.parsers.DocumentBuilder Instance
     * </pre>
     *
     * @return javax.xml.parsers.DocumentBuilder instance.
     */
    
    public static DocumentBuilder createDocumentBuilder() {
        DocumentBuilder builder = null;
      
        try {
        	
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

            // factory.setValidating(validation);
            factory.setNamespaceAware(false);
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(false);
            factory.setCoalescing(false);
            factory.setExpandEntityReferences(true);
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
           throw new RuntimeException("Error while instantiating document builder:" + e);
        }

        return builder;
    }
    
    /**
     * <pre>
     * gets the text value of any Element of an XML
     * </pre>
     *
     * @param anElement
     * 			An XML Element to be passed to get the associated value
     * @return String instance, textValue of an XML element
     */
    
    public static String getTextContent(Element anElement) {
    	
    	if(anElement.getFirstChild()!= null){
    	
        String textValue = anElement.getFirstChild().getNodeValue();

        return (textValue != null)
               ? textValue.trim()
               : "";
        }else{
        	return "";
        }
    }
    
    
    
    public static String pojoToXMLwithRootElement(Object pObject, String transactionType){
    	
    	XStream xstream = new XStream();
		String xmlRequest =xstream.toXML(pObject);
		xmlRequest = "<?xml version="+"1.0 ?><"+transactionType+">"+xmlRequest+"</"+transactionType+">";
		return "<![CDATA["+xmlRequest+"]]>";
    }
    
   public static String pojoToXML(Object pObject){
    	
    	XStream xstream = new XStream();
		String xmlRequest =xstream.toXML(pObject);
		return xmlRequest;
   }
}

