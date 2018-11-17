package com.multibrand.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;
import com.multibrand.util.LoggerUtil;

/**
 * 
 * 
 * @author jyogapa1
 * 
 */
@Component
public class BaseProxy implements Constants {
	LoggerUtil logger = LoggerUtil.getInstance("BaseProxy");

	@Autowired
	protected EnvMessageReader envMessageReader;

	@Autowired
	@Qualifier("appConstMessageSource")
	protected ReloadableResourceBundleMessageSource appConstMessageSource;

	/**
	 * Returns a Environment properties file handle if it is not found in Spring
	 * managed (autowired).
	 * 
	 * @return
	 */
	public EnvMessageReader getEnvMessageReader() {

		return CommonUtil.getEnvMessageReader(envMessageReader);
	}

	/**
	 * @author jyogapa1
	 * @param keyName
	 * @param locale
	 * 
	 * @return
	 * @throws Exception
	 * @author jyogapa1
	 */
	protected String getAppProperty(String keyName, Locale locale) {

		String propertyValue = null;

		propertyValue = CommonUtil.getAppProperty(appConstMessageSource,
				keyName, locale);

		return propertyValue;

	}

	/**
	 * @author jyogapa1
	 * @param keyName
	 * @return
	 * @throws Exception
	 * @author jyogapa1
	 */
	protected String getAppProperty(String keyName) {

		String propertyValue = null;

		propertyValue = CommonUtil.getAppProperty(appConstMessageSource,
				keyName);

		return propertyValue;

	}

	/**
	 * @author ahanda1
	 * 
	 *         This is generic method will used to get the Proxy of target Web
	 *         Service by given class Proxy and endpoint URL of target
	 *         web-service
	 * @param srvProxyClass
	 * @param endPointUrlKey
	 * @return
	 */
	public Object getServiceProxy(Class srvProxyClass, String endPointUrlKey) {
		Service service = null;
		Object proxyObject = null;
		String endPoint = null;
		Class[] parmTypes = { java.net.URL.class, javax.xml.rpc.Service.class };

		try {

			endPoint = getEndPointUrl(endPointUrlKey);
			// endPoint= "http://txaixebnbld01:9088/NRGWS/SwapDomain";
			System.out.println("endPoint ================================== "
					+ endPoint);
			Constructor constructor = srvProxyClass.getConstructor(parmTypes);
			proxyObject = constructor.newInstance(new Object[] {
					new java.net.URL(endPoint), service });
			try {
				Method method = srvProxyClass.getSuperclass()
						.getDeclaredMethod("setTimeout", Integer.TYPE);
				// int timeout = getWSConnTimeout(endPointUrlKey);
				method.invoke(proxyObject, WEBSERVICE_CALL_TIMEOUT * 1000);
			} catch (Exception e) {
				// logger.warn("Setting timeout has been failed:"
				// +e.getMessage());
			}

		} catch (Exception e) {
			// throw new ServiceException("getServiceProxy() Failed :", e);
		} catch (Throwable t) {
			// throw new ServiceException("getServiceProxy() Failed :", t);
		}
		return proxyObject;
	}

	public String getEndPointUrl(String srvEndPointUrlKey) {

		String endPointUrl = null;
		endPointUrl = getEnvMessageReader().getMessage(srvEndPointUrlKey);
		return endPointUrl;
	}

}
