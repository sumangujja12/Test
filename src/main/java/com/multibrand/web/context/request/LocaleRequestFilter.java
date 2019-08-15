package com.multibrand.web.context.request;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;


/**
 * A customized web locale filter that does extra processing to determine the
 * language code to support internationalization.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.0 and Jersey 1.7
 */
public class LocaleRequestFilter extends OncePerRequestFilter{

	private final static Logger LOGGER = LogManager
			.getLogger(LocaleRequestFilter.class);

	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		ServletRequestAttributes attributes = new ServletRequestAttributes(request, response);
        initContextHolders(request, attributes);
 
        try {
            filterChain.doFilter(request, response);
        }
        finally {
            resetContextHolders();
            if (logger.isTraceEnabled()) {
                logger.trace("Cleared thread-bound request context: " + request);
            }
            attributes.requestCompleted();
        }
		
	}
	
	
	private void initContextHolders(HttpServletRequest request, ServletRequestAttributes requestAttributes) {
		
		String langCodeSeparator = "_";

		// set to default language code as en_US.
		String languageCode = Constants.LOCALE_LANGUAGE_CODE_EN_US;
							
		// Try to find out the locale in cookie, session, request
		// parameter, attribute or header using the attribute name as
		// "languageCode":
		if (StringUtils.isNotEmpty(request.getHeader(Constants.LOCALE_LANGUAGE_CODE_PARAM))
				&& (Constants.LOCALE_LANGUAGE_CODE_E.equalsIgnoreCase(request
						.getHeader(Constants.LOCALE_LANGUAGE_CODE_PARAM)) || Constants.LOCALE_LANGUAGE_CODE_S
						.equalsIgnoreCase(request
								.getHeader(Constants.LOCALE_LANGUAGE_CODE_PARAM)))) {
			LOGGER.debug("Found languageCode in Request Header.");
			languageCode = CommonUtil.localeCode(request
					.getHeader(Constants.LOCALE_LANGUAGE_CODE_PARAM));
			LOGGER.debug("inside setLocale:: langaugecode is ::" + languageCode);
			/*
			 * languageCode = request
			 * .getHeaderValue(Constants.LOCALE_LANGUAGE_CODE_PARAM);
			 */
		} else if (StringUtils.isNotEmpty(request
				.getHeader(org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE))
				&& (Constants.LOCALE_LANGUAGE_CODE_E.equalsIgnoreCase(request
						.getHeader(HttpHeaders.ACCEPT_LANGUAGE)) || Constants.LOCALE_LANGUAGE_CODE_S
						.equalsIgnoreCase(request
								.getHeader(HttpHeaders.ACCEPT_LANGUAGE)))) {
			LOGGER.debug("Found 'Accept-Language' in Request Header.");
			languageCode = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
		} else if (HttpMethod.POST.toString().equalsIgnoreCase(request.getMethod())) {
			LOGGER.debug("inside POST");
			Map<String, String[]> queryParmaMap = request.getParameterMap();
			if (queryParmaMap != null) {
				if (queryParmaMap.get(Constants.LOCALE_LANGUAGE_CODE_PARAM) != null
						&& queryParmaMap.get(
								Constants.LOCALE_LANGUAGE_CODE_PARAM).length > 0) {
					String langValue = queryParmaMap.get(Constants.LOCALE_LANGUAGE_CODE_PARAM)[0];
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
		} else if (HttpMethod.GET.toString().equalsIgnoreCase(request.getMethod())) {
			Map<String, String[]> queryParmaMap = request.getParameterMap();
			if (queryParmaMap != null) {
				if (queryParmaMap.get(Constants.LOCALE_LANGUAGE_CODE_PARAM) != null
						&& queryParmaMap.get(
								Constants.LOCALE_LANGUAGE_CODE_PARAM).length > 0) {
					String langValue = queryParmaMap.get(Constants.LOCALE_LANGUAGE_CODE_PARAM)[0];
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
		} else if (request.getCookies() != null && request.getCookies().length > 0 ) {
			Cookie[]  cookArr = request.getCookies();
			 for (Cookie cook :cookArr) {
			
					String cookieValue = cook.getValue();
					if (Constants.LOCALE_LANGUAGE_CODE_PARAM
							.equalsIgnoreCase(cook.getName())
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
		LOGGER.debug("Determined languageCode = " + languageCode);
		String language = languageCode.split(langCodeSeparator)[0];
		String country = languageCode.split(langCodeSeparator)[1];
		Locale determinedLocale = new Locale(language, country);
		LocaleContextHolder.setLocale(determinedLocale);
        RequestContextHolder.setRequestAttributes(requestAttributes);
        if (logger.isTraceEnabled()) {
            logger.trace("Bound request context to thread: " + request);
        }
    }
	
	
	private void resetContextHolders() {
		LocaleContextHolder.resetLocaleContext();
		RequestContextHolder.resetRequestAttributes();
	}

}
