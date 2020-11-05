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
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import com.multibrand.interceptor.MySoapClientInterceptor;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.auth.AuthScope;
import org.apache.http.client.CredentialsProvider;

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
	
	@Value("${CCS_GMD_PRICE_SPIKE_ALERT}")
	private String gmdPriceSpikeEndPoint;
	
	@Value("${CCS_GMD_MD_STMT}")
	private String gmdMdStmtEndPoint;
	
	@Value("${CCS_GMD_LMP_PRICE_SPIKE}")
	private String gmdLmpPriceSpike;
		
	@Value("${CRM_KBA_MATRIX}")
	private String kbaMatrixUpdate;	
	
	@Value("${http.max.connection.per.route}")
	private int defaultMaxConnectionPerRoute;
	
	@Value("${http.max.total.connection}")
	private int defaultMaxTotalConnection;
	
	@Value("${http.stocket.connection.timeout}")
	private int httpConnectTimeout;
	
	
	
	  @Bean Jaxb2Marshaller jaxb2Marshaller() { 
		  Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		  jaxb2Marshaller.setContextPaths("com.nrg.cxfstubs.kbamatrix","com.nrg.cxfstubs.gmdmoveout","com.nrg.cxfstubs.gmdpricespike"); 
		  return jaxb2Marshaller; 
	  }
	 

	@Bean(name = "webServiceTemplateForGMDStatement")
	public WebServiceTemplate webServiceTemplateForGMDStatement() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPaths("com.nrg.cxfstubs.gmdstatement");
		webServiceTemplate.setMarshaller(jaxb2Marshaller);
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller);
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
	
	@Bean(name = "webServiceTemplateForMDStmt")
	public WebServiceTemplate webServiceTemplateForMDStmt() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPaths("com.nrg.cxfstubs.md.gmdstatement");
		webServiceTemplate.setMarshaller(jaxb2Marshaller);
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller);
		webServiceTemplate.setDefaultUri(gmdMdStmtEndPoint);
		ClientInterceptor[] clientInterceptors = {new MySoapClientInterceptor()};
		webServiceTemplate.setInterceptors(clientInterceptors);
        webServiceTemplate.setMessageSender(httpComponentsMessageSender());
        return webServiceTemplate;
	}
	
	@Bean(name = "webServiceTemplateForLmpPriceSpike")
	public WebServiceTemplate webServiceTemplateForLmpPriceSpike() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPaths("com.nrg.cxfstubs.lmp.gmdpricespike");
		webServiceTemplate.setMarshaller(jaxb2Marshaller);
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller);
		webServiceTemplate.setDefaultUri(gmdLmpPriceSpike);
		ClientInterceptor[] clientInterceptors = {new MySoapClientInterceptor()};
		webServiceTemplate.setInterceptors(clientInterceptors);
        webServiceTemplate.setMessageSender(httpComponentsMessageSender());
        return webServiceTemplate;
	}
	
	@Bean(name = "webServiceTemplateForGMDPriceSpike")
	public WebServiceTemplate webServiceTemplateForGMDPriceSpike() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(jaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
		webServiceTemplate.setDefaultUri(gmdPriceSpikeEndPoint);
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
		CustomHttpComponentsMessageSender httpComponentsMessageSender = new CustomHttpComponentsMessageSender(httpClient());
		// set the basic authorization credentials
		httpComponentsMessageSender.setCredentials(usernamePasswordCredentials());
		
	        
		
		return httpComponentsMessageSender;
	}

	@Bean
	public UsernamePasswordCredentials usernamePasswordCredentials() {
		// pass the user name and password to be used
		return new UsernamePasswordCredentials(clientUserName, clientPass);
	}
	
	@Bean
	public CloseableHttpClient httpClient() {
		
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		
		connectionManager.setMaxTotal(defaultMaxTotalConnection);
		connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost(gmdStatementEndPoint)), defaultMaxConnectionPerRoute);
		connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost(kbaMatrixUpdate)), defaultMaxConnectionPerRoute);


		RequestConfig config = RequestConfig.custom()
				  .setConnectTimeout(httpConnectTimeout * 1000)
				  .setConnectionRequestTimeout(httpConnectTimeout * 1000)
				  .setSocketTimeout(httpConnectTimeout * 1000).build();
		
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY,
				new UsernamePasswordCredentials(clientUserName,clientPass));
		
		return HttpClientBuilder.create()
				 .setDefaultRequestConfig(config)
				  .setConnectionManager(connectionManager).addInterceptorFirst(new ContentLengthHeaderRemover())
				  .setDefaultCredentialsProvider(credsProvider)
				  .build();
	}	

}
