package com.multibrand.resource;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.multibrand.bo.AutoPayBO;
import com.multibrand.resources.AutoPayResource;
import com.multibrand.service.PaymentService;
import com.multibrand.vo.response.ValidateBankResponse;

public class AutoPayResourceTest {

	private MockMvc mockMvc;
	/** providing configuration for the web application */
@Mock
	private WebApplicationContext wac;
    
    @Mock
    private WebSecurityConfiguration webSecurityConfiguration;
    
    
    @Mock
	private HttpServletRequest httpRequest;


    
    @Autowired
	@InjectMocks
    private AutoPayResource autoPayResource;
    
    
    @Mock
	private PaymentService paymentService;
    
    @Autowired
    @InjectMocks
    private AutoPayBO autoPayBO;
    
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		autoPayResource.setHttpRequest(new MockHttpServletRequest());

	}

	/*@Before
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
		autoPayResource.setHttpRequest(new MockHttpServletRequest());
	}*/
    @Test
    public void validateBankDetailsTest() throws Exception {    	
    	ValidateBankResponse validateBankResp=new ValidateBankResponse();
    	
    	com.multibrand.domain.ValidateBankResponse validateBankResp1=new com.multibrand.domain.ValidateBankResponse();
    	try {
    		
    	 //Mockito.when(autoPayBO.validateBankDetails(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(validateBankResp);	
    		
		  Mockito.when(paymentService.validateBankDetails(Mockito.any(String.class),
		  Mockito.any(String.class),Mockito.any(String.class),
		  Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class)
		  )).thenReturn(validateBankResp1);
		  
		  
			/*
			 * Mockito.when(autoPayBO.validateBankDetails(Mockito.any(String.class),
			 * Mockito.any(String.class),Mockito.any(String.class),
			 * Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class)
			 * )).thenReturn(validateBankResp);
			 */
		  
		  autoPayResource.validateBankDetails("000072938063", "3UNKK6SY-", "111000", "0008418028", "REST");
		  //autoPayBO.validateBankDetails("000072938063", "3UNKK6SY-", "111000", "0008418028","SessionId1","REST");
    }catch(Exception ex) {
    	ex.printStackTrace();
    }
    
    }   
}
