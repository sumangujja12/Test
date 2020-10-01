package com.multibrand.config;

import java.net.URI;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;


public class CustomHttpComponentsMessageSender extends HttpComponentsMessageSender  implements Constants  {

		
	@Autowired
	protected EnvMessageReader envMessageReader;

	public CustomHttpComponentsMessageSender(HttpClient httpClient) {
		super.setHttpClient(httpClient);
	}

	@Override
	protected HttpContext createContext(URI uri) {
		HttpHost targetHost = new HttpHost(uri.getHost(), uri.getPort(), "http");
		if(envMessageReader == null) 
			envMessageReader = (EnvMessageReader) ApplicationContextBean.getApplicationContext().getBean("EnvMessageReader"); 
		
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY,
				new UsernamePasswordCredentials(envMessageReader.getMessage(CCS_USER_NAME), envMessageReader.getMessage(CCS_PASSWORD)));

		AuthCache authCache = new BasicAuthCache();
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(targetHost, basicAuth);

		HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);
		context.setAuthCache(authCache);
		return context;
	}

	
	

}
