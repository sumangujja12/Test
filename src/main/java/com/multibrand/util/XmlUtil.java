package com.multibrand.util;

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
	//private static final Logger LOGGER = LogManager.getLogger(XmlUtil.class.getName());

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

            // URL url = XmlUtil.class.getClassLoader().getResource(fileName);
            is = new FileInputStream(new File(fileName));

            Document     doc        = documentBuilderFactory.newDocumentBuilder().parse(is);
            StringWriter stw        = new StringWriter();
            Transformer  serializer = TransformerFactory.newInstance().newTransformer();

            serializer.transform(new DOMSource(doc), new StreamResult(stw));
            convertedString = stw.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error while writing the document into String:" + e);
        } finally {
        	if(is!=null){
        		try {is.close();} 
        		catch (Exception e) {
        			System.out.println("convertXMLFileToString() InputStream Close Exception" + e.getMessage());
        		}
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
        	System.out.println("START: Parsing the XML inputStream");
            doc = builder.parse(inputStream);
            System.out.println("DONE: Parsing the XML inputStream");
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
        	 System.out.println("START: Parsing the XML input Reader");
            doc = builder.parse(new InputSource(reader));
            System.out.println("DONE: Parsing the XML input Reader");
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
            	
            	System.out.println("file not found  in classpath:'" + configFilePath + "'. Failed laoding XML config.");
            	File file = new File(configFilePath);
            	System.out.println("Trying to load from file system : absoulte path:" +file.getAbsolutePath());
            		doc  =  createDocument(new FileInputStream(file));
            	
            }
            else {
            
		            builder = createDocumentBuilder();
		            System.out.println("START: Parsing the XML file. Path:" +url);
		            doc = builder.parse(url.toString());
		            System.out.println("DONE: Parsing the XML file. ");
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
        	
        	System.out.println("creating Dom Document Builder");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

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
    
    /**
     * <pre>
     * Gets a String value from an File
     * </pre>
     *
     * @param filePath
     *            A physical path of an any file to be converted as String.
     * @return String from an give File.
     * @throws IOException
     */
    public static String readFileAsString(String filePath) throws java.io.IOException {
        byte[]              buffer = new byte[(int) new File(filePath).length()];
        BufferedInputStream f      = null;

        try {
            f = new BufferedInputStream(new FileInputStream(filePath));
            f.read(buffer);
        } finally {
            if (f != null) {
                try {
                    f.close();
                } catch (IOException ignored) {
                	System.out.println("readFileAsString() BufferedInputStream Close Exception" + ignored.getMessage());
                }
            }
        }

        return new String(buffer);
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

