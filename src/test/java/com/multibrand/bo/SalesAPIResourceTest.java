package com.multibrand.bo;

import static org.mockito.Mockito.when;

import java.rmi.RemoteException;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.multibrand.domain.AccountInfoResponse;
import com.multibrand.dto.request.ValidateCARequest;
import com.multibrand.dto.request.ValidateUserIdRequest;
import com.multibrand.dto.response.ValidateCAResponse;
import com.multibrand.dto.response.ValidateUserIdResponse;
import com.multibrand.resources.SalesAPIResource;
import com.multibrand.util.Constants;
import com.multibrand.util.LoggerUtil;
import com.multibrand.web.i18n.WebI18nMessageSource;

@Test(singleThreaded = true)
public class SalesAPIResourceTest  implements Constants{

	@Mock 
	private LoggerUtil logger;
	
	@Mock
	private SalesBO salesBO;
	
	@InjectMocks 
	private SalesAPIResource salesAPIResource;
	
	@Spy
	ReloadableResourceBundleMessageSource appConstMessageSource = new ReloadableResourceBundleMessageSource();

	@Spy
	private ReloadableResourceBundleMessageSource environmentMessageSource = new ReloadableResourceBundleMessageSource();

	@Spy
	WebI18nMessageSource msgSource = new WebI18nMessageSource();

	
	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(logger.isDebugEnabled()).thenReturn(true);
		appConstMessageSource.setUseCodeAsDefaultMessage(true);
		appConstMessageSource.setDefaultEncoding("UTF-8");
		appConstMessageSource.setFallbackToSystemLocale(Boolean.TRUE);
		appConstMessageSource.setUseCodeAsDefaultMessage(true);
		
		msgSource.setUseCodeAsDefaultMessage(true);
		msgSource.setDefaultEncoding("UTF-8");
		msgSource.setFallbackToSystemLocale(Boolean.TRUE);
		msgSource.setUseCodeAsDefaultMessage(true);
		
		
		environmentMessageSource.setUseCodeAsDefaultMessage(true);
		environmentMessageSource.setDefaultEncoding("UTF-8");
		environmentMessageSource.setFallbackToSystemLocale(Boolean.TRUE);
		environmentMessageSource.setUseCodeAsDefaultMessage(true);
    
	}

	@BeforeMethod
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
		when(logger.isDebugEnabled()).thenReturn(true);
		appConstMessageSource.setUseCodeAsDefaultMessage(true);
		appConstMessageSource.setDefaultEncoding("UTF-8");
		appConstMessageSource.setFallbackToSystemLocale(Boolean.TRUE);
		appConstMessageSource.setUseCodeAsDefaultMessage(true);
		
		msgSource.setUseCodeAsDefaultMessage(true);
		msgSource.setDefaultEncoding("UTF-8");
		msgSource.setFallbackToSystemLocale(Boolean.TRUE);
		msgSource.setUseCodeAsDefaultMessage(true);
		
		environmentMessageSource.setUseCodeAsDefaultMessage(true);
		environmentMessageSource.setDefaultEncoding("UTF-8");
		environmentMessageSource.setFallbackToSystemLocale(Boolean.TRUE);
		environmentMessageSource.setUseCodeAsDefaultMessage(true);
    
	}
	@Test
	public void testvalidateCA() throws RemoteException {
		ValidateCARequest validateCARequest= new ValidateCARequest();
		ValidateCAResponse validateCAResponse =new ValidateCAResponse();
		when(salesBO.validateCA(validateCARequest)).thenReturn(validateCAResponse);
		salesAPIResource.validateCA(validateCARequest);
		Assert.assertEquals(validateCAResponse,validateCAResponse);
	}
	
	@Test
	public void testgvalidateUsername() throws RemoteException {
		ValidateUserIdRequest validateUserIdRequest= new ValidateUserIdRequest();
		ValidateUserIdResponse validateUserIdResponse = new ValidateUserIdResponse();
		when(salesBO.validateUserName(validateUserIdRequest)).thenReturn(validateUserIdResponse);
		salesAPIResource.validateUsername(validateUserIdRequest);
		Assert.assertEquals(validateUserIdResponse,validateUserIdResponse);
	}
	
}
