package com.multibrand.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.multibrand.web.i18n.WebI18nMessageSource;
import com.multibrand.ws.helper.JaxWsClientFactoryBean;
import com.reliant.domain.AddressValidationDomainPortBindingStub;

@Configuration
@ComponentScan(basePackages = {"com.multibrand.resources","com.multibrand"})
@PropertySource({ "classpath:properties/environment.properties", "classpath:properties/stubs.properties" })
@Import({DatabaseConfig.class})
@EnableAspectJAutoProxy
@EnableWebMvc
public class AppConfig {

	@Value("${NRGWS_BILLING_DOMAIN_NAMESPACE_URI}")
	private String billingDomainSpaceURI;
	@Value("${NRGWS_BILLING_DOMAIN_PORT_NAME}")
	private String billingDomainNamePortName;
	@Value("${ws.endpointURL.billingDomain}")
	private String billingDomainEndpont;
	@Value("${NRGWS_ADDRESSVALIDATION_DOMAIN_NAMESPACE_URI}")
	private String addressNameSpaceURI;
	@Value("${NRGWS_ADDRESSVALIDATION_DOMAIN_PORT_NAME}")
	private String addressPortName;
	@Value("${ws.endpointURL.addressvalidationDomain}")
	private String addressEndpont;
	@Value("${NRGWS_PROFILE_DOMAIN_NAMESPACE_URI}")
	private String ProfileDomainSpaceURI;
	@Value("${NRGWS_PROFILE_DOMAIN_PORT_NAME}")
	private String ProfileDomainPortName;
	@Value("${ws.endpointURL.profileDomain}")
	private String ProfileDomainEndpont;
	@Value("${NRGWS_OE_DOMAIN_NAMESPACE_URI}")
	private String oeDomainSpaceURI;
	@Value("${NRGWS_OE_DOMAIN_PORT_NAME}")
	private String oeDomainPortName;
	@Value("${ws.endpointURL.oeDomain}")
	private String oeDomainEndpont;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	/**
	 * Property placeholder configurer needed to process @Value annotations
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean(name = "appConstMessageSource")
	public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String envPath = rootPath + "/properties/appConstants.properties";
		messageSource.setBasenames(envPath);
		InputStream input;
		try {
			input = new FileInputStream(envPath);
		
        Properties prop = new Properties();
        prop.load(input);
		messageSource.setCommonMessages(prop);
		} catch (Exception e) {
			logger.error("appConstants.properties file not found or exception in loading");
		}
		messageSource.setCacheSeconds(0);
		messageSource.setBasenames(envPath);
		return messageSource;
	}

	@Bean(name = "sqlQuerySource")
	public ReloadableResourceBundleMessageSource reloadableResourceSQLBundleMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String envPath = rootPath + "/properties/db/dbsql";
		messageSource.setBasenames(envPath);
		InputStream input;
		try {
			input = new FileInputStream(envPath);
		
        Properties prop = new Properties();
        prop.load(input);
		messageSource.setCommonMessages(prop);
		} catch (Exception e) {
			logger.error("dbsql.properties file not found or exception in loading");
		}
		messageSource.setBasenames(envPath);
		messageSource.setCacheSeconds(0);
		return messageSource;
	}

	@Bean(name = "environmentMessageSource")
	public ReloadableResourceBundleMessageSource reloadableResourceEnvBundleMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String envPath = rootPath + "/properties/environment.properties";
		InputStream input;
		try {
			input = new FileInputStream(envPath);
		
        Properties prop = new Properties();
        prop.load(input);
		messageSource.setCommonMessages(prop);
		} catch (Exception e) {
			logger.error("environment.properties file not found or exception in loading");
		}
		messageSource.setBasenames(envPath);
		//System.out.println(messageSource.getMessage("OAM_MAX_INVALID_LOGIN_COUNT", null, null));
		messageSource.setCacheSeconds(0);
		return messageSource;
	}
	

	@Bean(name = "addressvalidationDomainPortProxy")
	public JaxWsClientFactoryBean addressValidationDomainJaxWsClientFactoryBean() throws MalformedURLException {
		System.out.println("{ws.endpointURL.addressvalidationDomain}" + addressEndpont);
		System.out.println("${NRGWS_ADDRESSVALIDATION_DOMAIN_PORT_NAME}" + addressPortName);
		System.out.println("${NRGWS_ADDRESSVALIDATION_DOMAIN_NAMESPACE_URI}" + addressNameSpaceURI);
		JaxWsClientFactoryBean address = new JaxWsClientFactoryBean();
		address.setWsdlDocumentLocation(new URL(addressEndpont + "?wsdl"));
		address.setServiceEndpointInterface(AddressValidationDomainPortBindingStub.class);
		address.setNamespaceURI(addressNameSpaceURI);
		address.setPortName(addressPortName);
		address.setEndpointAddress(addressEndpont);
		return address;
	}

	@Bean(name = "billingDomainPortProxy")
	public JaxWsClientFactoryBean billingDomainJaxWsClientFactoryBean() throws MalformedURLException {
		System.out.println("{ws.endpointURL.billingDomain}" + billingDomainEndpont);
		System.out.println("${NRGWS_BILLING_DOMAIN_PORT_NAME}" + billingDomainNamePortName);
		System.out.println("${NRGWS_BILLING_DOMAIN_NAMESPACE_URI}" + billingDomainSpaceURI);
		JaxWsClientFactoryBean billingDomain = new JaxWsClientFactoryBean();
		billingDomain.setWsdlDocumentLocation(new URL(billingDomainEndpont + "?wsdl"));
		billingDomain.setServiceEndpointInterface(com.multibrand.domain.BillingDomainPortBindingStub.class);
		billingDomain.setNamespaceURI(billingDomainSpaceURI);
		billingDomain.setPortName(billingDomainNamePortName);
		billingDomain.setEndpointAddress(billingDomainEndpont);
		return billingDomain;
	}

	@Bean(name = "oeDomainPortProxy")
	public JaxWsClientFactoryBean oeDomainJaxWsClientFactoryBean() throws MalformedURLException {
		System.out.println("{ws.endpointURL.oeDomain}" + oeDomainEndpont);
		System.out.println("${NRGWS_OE_DOMAIN_PORT_NAME}" + oeDomainPortName);
		System.out.println("${NRGWS_OE_DOMAIN_NAMESPACE_URI}" + oeDomainSpaceURI);
		JaxWsClientFactoryBean oeDomain = new JaxWsClientFactoryBean();
		oeDomain.setWsdlDocumentLocation(new URL(oeDomainEndpont + "?wsdl"));
		oeDomain.setServiceEndpointInterface(com.multibrand.domain.OEDomainPortBindingStub.class);
		oeDomain.setNamespaceURI(oeDomainSpaceURI);
		oeDomain.setPortName(oeDomainPortName);
		oeDomain.setEndpointAddress(oeDomainEndpont);
		return oeDomain;
	}

	@Bean(name = "profileDomainPortProxy")
	public JaxWsClientFactoryBean profileDomainJaxWsClientFactoryBean() throws MalformedURLException {
		System.out.println("{ws.endpointURL.profileDomain}" + ProfileDomainEndpont);
		System.out.println("${NRGWS_PROFILE_DOMAIN_PORT_NAME}" + ProfileDomainPortName);
		System.out.println("${NRGWS_PROFILE_DOMAIN_NAMESPACE_URI}" + ProfileDomainSpaceURI);
		JaxWsClientFactoryBean profileDomain = new JaxWsClientFactoryBean();
		profileDomain.setWsdlDocumentLocation(new URL(ProfileDomainEndpont + "?wsdl"));
		profileDomain.setServiceEndpointInterface(com.multibrand.domain.ProfileDomainPortBindingStub.class);
		profileDomain.setNamespaceURI(ProfileDomainSpaceURI);
		profileDomain.setPortName(ProfileDomainPortName);
		profileDomain.setEndpointAddress(ProfileDomainEndpont);
		return profileDomain;
	}

	@Bean(name = "webI18nMessageSource")
	public WebI18nMessageSource getMessageBundle() {
		WebI18nMessageSource webI18nMessageSource = new WebI18nMessageSource();
		webI18nMessageSource.setBasename("classpath:messages/message_resources");
		webI18nMessageSource.setCacheSeconds(0);
		return webI18nMessageSource;

	}

	@Bean(name = "oweRPMFactors")
	public WebI18nMessageSource getOweMessageBundle() {
		WebI18nMessageSource webI18nMessageSource = new WebI18nMessageSource();
		webI18nMessageSource.setBasename("classpath:messages/oweRPMFactors");
		webI18nMessageSource.setCacheSeconds(0);
		return webI18nMessageSource;

	}

	@Bean(name = "validator")
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(getMessageBundle());
		return localValidatorFactoryBean;
	}

}
