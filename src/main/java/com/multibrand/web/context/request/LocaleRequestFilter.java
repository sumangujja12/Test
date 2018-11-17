package com.multibrand.web.context.request;

import java.util.Locale;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.i18n.LocaleContextHolder;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

/**
 * A customized web locale filter that does extra processing to determine the
 * language code to support internationalization.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.0 and Jersey 1.7
 */
public class LocaleRequestFilter implements ContainerRequestFilter {

	private final static Logger LOGGER = LogManager
			.getLogger(LocaleRequestFilter.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContainerRequest filter(ContainerRequest request) {
		setLocale(request);
		return request;
	}

	/**
	 * Sets the locale.
	 * 
	 * <p>
	 * The <code>Locale</code> will be set if, any of the literal value as
	 * &quot;en_US&quot; or &quot;es_US&quot; is present in following request
	 * attributes:
	 * <ul>
	 * <li><strong>Request Parameter</strong>: &quot;languageCode&quot;</li>
	 * <li><strong>Request Header</strong>: &quot;Accept-Language&quot;</li>
	 * <li><strong>Request Header</strong>: &quot;languageCode&quot;</li>
	 * <li><strong>Request Cookie</strong>: &quot;languageCode&quot;</li>
	 * </ul>
	 * </p>
	 * 
	 * @param request
	 *            Instance of <code>ContainerRequest</code>.
	 */
	protected void setLocale(ContainerRequest request) {

		String langCodeSeparator = "_";

		// set to default language code as en_US.
		String languageCode = Constants.LOCALE_LANGUAGE_CODE_EN_US;
		MultivaluedMap<String, String> cookieMap = request
				.getCookieNameValueMap();

		// Try to find out the locale in cookie, session, request
		// parameter, attribute or header using the attribute name as
		// "languageCode":
		if (StringUtils.isNotEmpty(request
				.getHeaderValue(Constants.LOCALE_LANGUAGE_CODE_PARAM))
				&& (Constants.LOCALE_LANGUAGE_CODE_E.equalsIgnoreCase(request
						.getHeaderValue(Constants.LOCALE_LANGUAGE_CODE_PARAM)) || Constants.LOCALE_LANGUAGE_CODE_S
						.equalsIgnoreCase(request
								.getHeaderValue(Constants.LOCALE_LANGUAGE_CODE_PARAM)))) {
			LOGGER.debug("Found languageCode in Request Header.");
			languageCode = CommonUtil.localeCode(request
					.getHeaderValue(Constants.LOCALE_LANGUAGE_CODE_PARAM));
			LOGGER.debug("inside setLocale:: langaugecode is ::" + languageCode);
			/*
			 * languageCode = request
			 * .getHeaderValue(Constants.LOCALE_LANGUAGE_CODE_PARAM);
			 */
		} else if (StringUtils.isNotEmpty(request
				.getHeaderValue(HttpHeaders.ACCEPT_LANGUAGE))
				&& (Constants.LOCALE_LANGUAGE_CODE_E.equalsIgnoreCase(request
						.getHeaderValue(HttpHeaders.ACCEPT_LANGUAGE)) || Constants.LOCALE_LANGUAGE_CODE_S
						.equalsIgnoreCase(request
								.getHeaderValue(HttpHeaders.ACCEPT_LANGUAGE)))) {
			LOGGER.debug("Found 'Accept-Language' in Request Header.");
			languageCode = request.getHeaderValue(HttpHeaders.ACCEPT_LANGUAGE);
		} else if (HttpMethod.POST.equalsIgnoreCase(request.getMethod())) {
			LOGGER.debug("inside POST");
			Form form = request.getFormParameters();
			if (form != null) {
				if (form.get(Constants.LOCALE_LANGUAGE_CODE_PARAM) != null
						&& form.get(Constants.LOCALE_LANGUAGE_CODE_PARAM)
								.size() > 0) {
					String langValue = form.get(
							Constants.LOCALE_LANGUAGE_CODE_PARAM).get(0);
					if (StringUtils.isNotEmpty(langValue)
							&& (Constants.LOCALE_LANGUAGE_CODE_E
									.equalsIgnoreCase(langValue) || Constants.LOCALE_LANGUAGE_CODE_S
									.equalsIgnoreCase(langValue))) {
						LOGGER.debug("Found languageCode as a Form parameter of HTTP POST method.");
						languageCode = CommonUtil.localeCode(langValue);
						// languageCode = langValue;
					}
				}
			}
		} else if (HttpMethod.GET.equalsIgnoreCase(request.getMethod())) {
			MultivaluedMap<String, String> queryParmaMap = request
					.getQueryParameters();
			if (queryParmaMap != null) {
				if (queryParmaMap.get(Constants.LOCALE_LANGUAGE_CODE_PARAM) != null
						&& queryParmaMap.get(
								Constants.LOCALE_LANGUAGE_CODE_PARAM).size() > 0) {
					String langValue = queryParmaMap.get(
							Constants.LOCALE_LANGUAGE_CODE_PARAM).get(0);
					if (StringUtils.isNotEmpty(langValue)
							&& (Constants.LOCALE_LANGUAGE_CODE_E
									.equalsIgnoreCase(langValue) || Constants.LOCALE_LANGUAGE_CODE_S
									.equalsIgnoreCase(langValue))) {
						LOGGER.debug("Found languageCode as a Query parameter of HTTP GET method.");
						languageCode = CommonUtil.localeCode(langValue);
						// languageCode = langValue;
					}
				}
			}
		} else if (cookieMap != null && !cookieMap.isEmpty()) {
			for (String cookieName : cookieMap.keySet()) {
				if (cookieMap.get(cookieName) != null
						&& cookieMap.get(cookieName).size() > 0) {
					String cookieValue = cookieMap.get(cookieName).get(0);
					if (Constants.LOCALE_LANGUAGE_CODE_PARAM
							.equalsIgnoreCase(cookieName)
							&& StringUtils.isNotEmpty(cookieValue)
							&& (Constants.LOCALE_LANGUAGE_CODE_E
									.equalsIgnoreCase(cookieValue) || Constants.LOCALE_LANGUAGE_CODE_S
									.equalsIgnoreCase(cookieValue))) {
						LOGGER.debug("Found languageCode in Cookie.");
						languageCode = cookieValue;
						break;
					}
				}
			}
		}
		LOGGER.debug("Determined languageCode = " + languageCode);
		String language = languageCode.split(langCodeSeparator)[0];
		String country = languageCode.split(langCodeSeparator)[1];
		Locale determinedLocale = new Locale(language, country);
		LocaleContextHolder.setLocale(determinedLocale);
	}

}
