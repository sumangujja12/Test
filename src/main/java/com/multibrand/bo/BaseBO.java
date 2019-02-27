package com.multibrand.bo;

import java.util.Locale;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.multibrand.exception.OEException;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.web.i18n.WebI18nMessageSource;

/**
 * 
 * 
 * @author jyogapa1
 * 
 */
@Component
public class BaseBO implements Constants {

	@Autowired
	@Resource(name = "webI18nMessageSource")
	protected WebI18nMessageSource msgSource;
	
	@Autowired
	@Qualifier("appConstMessageSource")
	protected ReloadableResourceBundleMessageSource appConstMessageSource;
	
	/**
	 * @author jyogapa1 (Jenith)
	 * 
	 * @param response
	 * @param message
	 * @param e
	 * @throws OEException
	 */
	protected void handleServiceException(GenericResponse response,
			String message, Exception e) throws OEException {

		this.handleServiceException(response, message, e, Boolean.TRUE);
	}
	
	/**
	 * @author jyogapa1 (Jenith)
	 * 
	 * @param response
	 * @param message
	 * @param e
	 * @param shouldThrow
	 * @throws OEException
	 */
	protected void handleServiceException(GenericResponse response,
			String message, Exception e, Boolean shouldThrow) throws OEException {

		String vMessage = StringUtils.isNotBlank(message) ? StringUtils
				.trim(message) : StringUtils.EMPTY;

		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION + ":" + e.getMessage());

		if (shouldThrow) {
			if (e instanceof ServiceException) {
				// throw ((ServiceException)e);
			} else {
				throw new OEException(vMessage, e);
			}
		}
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
		
		propertyValue = CommonUtil.getAppProperty(appConstMessageSource, keyName, locale);

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
		
		propertyValue = CommonUtil.getAppProperty(appConstMessageSource, keyName);

		return propertyValue;

	}
	/**
	 * Gets a value from the message properties file named webI18nMessageSource
	 * for the given message property key.
	 * 
	 * @param messageKey
	 * @return
	 */
	protected String getMessage(String messageKey) {
		return msgSource.getMessage(messageKey);
	}
	
	// Start | US16458 | MBAR: Sprint 14 - GME Admin tool password reset issue fixes. | Jenith | 2/5/2019 
	protected void logRequestAndResponse(Logger logger, String requestParams, Object responseObject){
		if (logger.isDebugEnabled()) {
			logger.debug(XmlUtil.pojoToXML(requestParams));
			logger.debug(XmlUtil.pojoToXML(responseObject));
		}
	}
	// End | US16458 | MBAR: Sprint 14 - GME Admin tool password reset issue fixes. | Jenith | 2/5/2019 

	// Start | US16458 | MBAR: Sprint 14 - GME Admin tool password reset issue fixes. | Jenith | 2/5/2019 
	protected void logRequestAndResponse(Logger logger, String requestParams, Object responseObject){
		if (logger.isDebugEnabled()) {
			logger.debug(XmlUtil.pojoToXML(requestParams));
			logger.debug(XmlUtil.pojoToXML(responseObject));
		}
	}
	// End | US16458 | MBAR: Sprint 14 - GME Admin tool password reset issue fixes. | Jenith | 2/5/2019 

}
