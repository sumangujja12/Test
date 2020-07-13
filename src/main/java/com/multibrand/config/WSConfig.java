package com.multibrand.config;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

@Configuration
@PropertySource({ "classpath:properties/environment.properties" })
public class WSConfig {
		
	@Value("${CCS_GMD_STMT}")
	private String gmdStatementEndPoint;	
	
	@Value("${CCSUSERNAME}")
	private String clientUserName;	
	
	@Value("${CCSPASSWORD}")
	private String clientPass;	
	
	@Value("${CCS_CREATE_MOVE_OUT}")
	private String gmdCreateMoveOutEndPoint;
		
	
	
	@Bean
	Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPaths("com.nrg.cxfstubs.gmdstatement");
		return jaxb2Marshaller;
	}
	
	@Bean
	Jaxb2Marshaller jaxb2MarshallerForGMDMoveOut() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPaths("com.nrg.cxfstubs.gmdmoveout");
		return jaxb2Marshaller;
	}

	@Bean(name = "webServiceTemplateForGMDStatement")
	public WebServiceTemplate webServiceTemplateForGMDStatement() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(jaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
		webServiceTemplate.setDefaultUri(gmdStatementEndPoint);
		// set the Apache HttpClient which provides support for basic
		// authentication
		webServiceTemplate.setMessageSender(httpComponentsMessageSender());

		return webServiceTemplate;
	}
	
	@Bean(name = "webServiceTemplateForGMDCreateMoveOut")
	public WebServiceTemplate webServiceTemplateForGMDCreateMoveOut() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(jaxb2MarshallerForGMDMoveOut());
		webServiceTemplate.setUnmarshaller(jaxb2MarshallerForGMDMoveOut());
		webServiceTemplate.setDefaultUri(gmdCreateMoveOutEndPoint);
		webServiceTemplate.setMessageSender(httpComponentsMessageSender());
		return webServiceTemplate;
	}

	@Bean
	public HttpComponentsMessageSender httpComponentsMessageSender() {
		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
		// set the basic authorization credentials
		httpComponentsMessageSender.setCredentials(usernamePasswordCredentials());
		
		return httpComponentsMessageSender;
	}

	@Bean
	public UsernamePasswordCredentials usernamePasswordCredentials() {
		// pass the user name and password to be used
		return new UsernamePasswordCredentials(clientUserName, clientPass);
	}
}
