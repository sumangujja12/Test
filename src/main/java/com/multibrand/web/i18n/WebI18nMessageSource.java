package com.multibrand.web.i18n;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Web I18N message source to support internationalization.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.0 and Jersey 1.7
 */
public class WebI18nMessageSource extends ReloadableResourceBundleMessageSource {

	/**
	 * Gets locale based message.
	 * 
	 * @param msgCode
	 *            Message code from underlying properties file.
	 * @return Locale based message.
	 */
	public String getMessage(String msgCode) {
		return super.getMessage(msgCode, null, LocaleContextHolder.getLocale());
	}

	/**
	 * Gets locale based message.
	 * 
	 * @param msgCode
	 *            Message code from underlying properties file.
	 * @param msgParams
	 *            Message place holders to be replaced by <code>msgParams</code>
	 *            values.
	 * @return Locale based message.
	 */
	public String getMessage(String msgCode, Object[] msgParams) {
		return super.getMessage(msgCode, msgParams,
				LocaleContextHolder.getLocale());
	}

	/**
	 * Gets locale based message.
	 * 
	 * @param msgCode
	 *            Message code from underlying properties file.
	 * @param msgParams
	 *            Message place holders to be replaced by <code>msgParams</code>
	 *            values.
	 * @param defaultMessage
	 *            Default message is used when <code>msgCode</code> is not found
	 *            in properties file.
	 * @return Locale based message.
	 */
	public String getMessage(String msgCode, Object[] msgParams,
			String defaultMessage) {
		return super.getMessage(msgCode, msgParams, defaultMessage,
				LocaleContextHolder.getLocale());
	}

}
