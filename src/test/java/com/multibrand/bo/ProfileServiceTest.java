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
import com.multibrand.service.ProfileService;
import com.multibrand.util.Constants;
import com.multibrand.util.LoggerUtil;
import com.multibrand.web.i18n.WebI18nMessageSource;

@Test(singleThreaded = true)
public class ProfileServiceTest  implements Constants{

	@Mock 
	private LoggerUtil logger;
	
	@InjectMocks 
	private ProfileService profileService;
	
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
	public void testgetContractDetailsFromCO() throws  RemoteException{
		String contractNumber = "123456";
		String companyCode = "654321";
		AccountInfoResponse accountInfoResponse = new AccountInfoResponse();
		profileService.getContractDetailsFromCO(contractNumber,companyCode);
		Assert.assertEquals(accountInfoResponse,accountInfoResponse);
	}
	
}
