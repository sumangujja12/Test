package com.multibrand.config;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.multibrand.interceptor.MySoapClientInterceptor;

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
		
	@Value("${CRM_KBA_MATRIX}")
	private String kbaMatrixUpdate;	
	
	
	@Bean
	Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPaths("com.nrg.cxfstubs.gmdstatement","com.nrg.cxfstubs.kbamatrix","com.nrg.cxfstubs.gmdmoveout");
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
		webServiceTemplate.setMarshaller(jaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
		webServiceTemplate.setDefaultUri(gmdCreateMoveOutEndPoint);
		ClientInterceptor[] clientInterceptors = {new MySoapClientInterceptor()};
		webServiceTemplate.setInterceptors(clientInterceptors);
        
		webServiceTemplate.setMessageSender(httpComponentsMessageSender());
		return webServiceTemplate;
	}
	
	@Bean(name = "webServiceTemplateForKBAMatrixUpdate")
	public WebServiceTemplate webServiceTemplateForKBAMatrixUpdate() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(jaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
		webServiceTemplate.setDefaultUri(kbaMatrixUpdate);
		
		ClientInterceptor[] clientInterceptors = {new MySoapClientInterceptor()};
		webServiceTemplate.setInterceptors(clientInterceptors);
        
		webServiceTemplate.setMessageSender(httpComponentsMessageSender());
		return webServiceTemplate;
	}	

	@Bean
	public HttpComponentsMessageSender httpComponentsMessageSender() {
		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
		// set the basic authorization credentials
		httpComponentsMessageSender.setCredentials(usernamePasswordCredentials());
		
		httpComponentsMessageSender.setReadTimeout(60000);
		httpComponentsMessageSender.setConnectionTimeout(60000);
	        
		
		return httpComponentsMessageSender;
	}

	@Bean
	public UsernamePasswordCredentials usernamePasswordCredentials() {
		// pass the user name and password to be used
		return new UsernamePasswordCredentials(clientUserName, clientPass);
	}
}
