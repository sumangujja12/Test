package com.multibrand.ws.helper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.multibrand.exception.ServiceException;

public class JaxWsClientFactoryBean implements FactoryBean<Object>, InitializingBean {
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	private URL wsdlDocumentLocation;
	private Class<?> serviceEndpointInterface;
	private String namespaceURI;
	private String portName;
	private String endpointAddress;
	private String wsTimeout = null;
	// derived from namespaceURI and localPart
	private QName serviceName;

	@Override
	public void afterPropertiesSet() {
		serviceName = new QName(namespaceURI, portName);
	}

	@Override
	public Object getObject() throws ServiceException {
		Object proxyObject = null;

		logger.info("Registering Spring bean, JaxWSClientFactoryBean, for an Endpoint URL :- "
				+ endpointAddress);
		try {

			// Create web service proxy stub object
			proxyObject = this.createWsProxy();

			// Sets Timeout on the proxy object created
			this.setTimeout(proxyObject);

		} catch (Exception e) {
			throw new ServiceException("Creating Proxy Stub Bean Failed for an Endpoint URL :- "
					+ endpointAddress, e);
		} catch (Throwable t) {
			throw new ServiceException("Creating Proxy Stub Bean Failed for an Endpoint URL :- "
					+ endpointAddress, t);
		}
		
		return proxyObject;
	}

	@Override
	public Class<?> getObjectType() {
		return serviceEndpointInterface;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public URL getWsdlDocumentLocation() {
		return wsdlDocumentLocation;
	}

	public void setWsdlDocumentLocation(final URL wsdlDocumentLocation) {
		this.wsdlDocumentLocation = wsdlDocumentLocation;
	}

	public Class<?> getServiceEndpointInterface() {
		return serviceEndpointInterface;
	}

	public void setServiceEndpointInterface(
			final Class<?> serviceEndpointInterface) {
		this.serviceEndpointInterface = serviceEndpointInterface;
	}

	public String getNamespaceURI() {
		return namespaceURI;
	}

	public void setNamespaceURI(final String namespaceURI) {
		this.namespaceURI = namespaceURI;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(final String portName) {
		this.portName = portName;
	}

	/**
	 * @return the endpointAddress
	 */
	public String getEndpointAddress() {
		return endpointAddress;
	}

	/**
	 * @param endpointAddress
	 *            the endpointAddress to set
	 */
	public void setEndpointAddress(String endpointAddress) {
		this.endpointAddress = endpointAddress;
	}
		
	/**
	 * @return the wsTimeout
	 */
	public String getWsTimeout() {
		return wsTimeout;
	}

	/**
	 * @param wsTimeout the wsTimeout to set
	 */
	public void setWsTimeout(String wsTimeout) {
		this.wsTimeout = wsTimeout;
	}

	/**
	 * 
	 * Creates webservice client stub proxy object.
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private Object createWsProxy() throws MalformedURLException,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		Service service = null;
		Object proxyObject = null;

		Class[] parameterTypes = { java.net.URL.class, javax.xml.rpc.Service.class };
		Object[] parameters = { new java.net.URL(endpointAddress), service };

		Constructor constructor = serviceEndpointInterface.getConstructor(parameterTypes);
		proxyObject = constructor.newInstance(parameters);

		return proxyObject;
	}
	
	/**
	 * Sets Timeout on the ws client proxy object.
	 * 
	 * @param proxyObject
	 */
	private void setTimeout(Object proxyObject) {
		try {
			Method method = serviceEndpointInterface.getSuperclass()
					.getDeclaredMethod("setTimeout", Integer.TYPE);
			int timeout = computeWsRequestTimeout();
			method.invoke(proxyObject, timeout);
			logger.info("Timeout set is: " + timeout);
		} catch (Exception e) {
			logger.warn("Setting timeout has been failed:" + e.getMessage());
		}
	}
	
	/**
	 * Calculates web service timeout in millisecond by reading the value from
	 * environment.properties file.
	 * 
	 * @return
	 * @author jyogapa1
	 */
	private int computeWsRequestTimeout() {
		int timeout = 45;
		try {

			if (StringUtils.isNotBlank(wsTimeout)) {
				timeout = Integer.parseInt(wsTimeout);
			}
		} catch (Exception e) {
			logger.warn("WS Request Timeout: The property in environment.properties is not a number: "
					+ wsTimeout + " :: Message:" + e.getMessage());
			timeout = 45;
		}
		timeout = timeout * 1000;
		return timeout;
	}

}
