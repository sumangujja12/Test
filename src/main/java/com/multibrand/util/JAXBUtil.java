package com.multibrand.util;



//~--- JDK imports ------------------------------------------------------------

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import com.sun.xml.bind.marshaller.DataWriter;

/**
*
* @author nulthi1
* @version 1.0
*
*/
public class JAXBUtil {

  /**
   * The logger
   */
 // private static final LoggerUtil logger = LoggerUtil.getInstance(JAXBUtil.class);

  @SuppressWarnings("unchecked")
  private static Class getClass(String className) {
      Class retClass = null;

      try {
          retClass = JAXBUtil.class.getClassLoader().loadClass(className);
      } catch (ClassNotFoundException e) {
          throw new RuntimeException("reponse Class '" + retClass + "' not found :", e);
      }

      return retClass;
  }

  @SuppressWarnings("unchecked")
  private static Object getNewInstance(Class name) {
      try {
          return name.newInstance();
      } catch (InstantiationException e) {
          throw new RuntimeException("reponse Class '" + name.getName() + "' cannot be instantiate :", e);
      } catch (IllegalAccessException e) {
          throw new RuntimeException("reponse Class '" + name.getName() + "' cannot be instantiate :", e);
      }
  }

  public static String marshal(Object pObject) {
      String xmlString = null;

      try {
    	  StringWriter sw  = new StringWriter();
          PrintWriter printWriter = new PrintWriter(sw);
          DataWriter dataWriter = new DataWriter(printWriter, "UTF-8", new XmlCharacterHandler());
          Marshaller   marshaller = JAXBContext.newInstance(pObject.getClass()).createMarshaller();
          marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
          marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
          
          marshaller.marshal(pObject, dataWriter);
          xmlString = sw.toString();

      } catch (JAXBException e) {
          throw new RuntimeException("marshal() Failed:", e);
      }

      return xmlString;
  }

  @SuppressWarnings("unchecked")
  public static Object unmarshal(String xmlString, String responseClassName) {
      ByteArrayInputStream bis           = null;
      Object               response      = null;
      Class                responseClass = null;

      try {
          bis           = new ByteArrayInputStream(xmlString.getBytes());
          responseClass = getClass(responseClassName);

          JAXBContext  jc = JAXBContext.newInstance(responseClass);
          Unmarshaller u  = jc.createUnmarshaller();

          response = getNewInstance(responseClass);
          response = u.unmarshal(bis);
      } catch (UnmarshalException e) {
        //  logger.error("Not able to bind xml string to response object. unmarshal() Failed: cause: " + e.getCause(),
          //             e);

          return xmlString;
      } catch (JAXBException e) {
          throw new RuntimeException("unmarshal() Failed:", e);
      }

      return response;
  }
}