package com.multibrand.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;

import com.multibrand.bo.AutoPayBO;
import com.multibrand.domain.ValidateBankRequest;
import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.resources.AutoPayResource;
import com.multibrand.service.PaymentService;
import com.multibrand.vo.response.ValidateBankResponse;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

public class AutoPayResourceTest {

	private MockMvc mockMvc;
	/** providing configuration for the web application */
	@Autowired
	private WebApplicationContext wac;
    
    @InjectMocks
    private WebSecurityConfiguration webSecurityConfiguration;
    
    @Autowired
	@InjectMocks
    private AutoPayBO autoPayBO;
    
    @Mock
	private HttpServletRequest httpRequest;

    @Mock
	ErrorContentHelper errorContentHelper;
    
    @Autowired
	@InjectMocks
    private AutoPayResource autoPayResource;
    
    @Mock
	private UtilityLoggerHelper utilityloggerHelper;
    
    @Mock
    @Autowired
	private PaymentService paymentService;
    
    @Mock
    com.multibrand.domain.ValidateBankResponse response;
    @Mock
    private Response res;
   
    
    @BeforeMethod
	public void init() {

		// Process mock annotations for mockito
		MockitoAnnotations.initMocks(this);
		// Set up Spring test in web app mode
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();  
	}
    @Test
    public void validateBankDetailsTest() throws Exception {    	
    	ValidateBankResponse validateBankResp=new ValidateBankResponse();
    	validateBankResp.setStatusCode("200");
    	ValidateBankRequest request = new ValidateBankRequest();
		request.setStrBankAccNumber("000003250225");
		request.setStrBankRoutingNumber("111000012");
		request.setStrCompanyCode("10443720001032191");
    	Mockito.when(paymentService.validateBankDetails(Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class)))
	       .thenReturn(response);
    	Mockito.when(autoPayBO.validateBankDetails(Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class)))
    	       .thenReturn(validateBankResp);
    	
    	this.mockMvc.perform(post("/autoPay/validateBankDetails")
				.content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("accountNumber", "000003250225")
				.param("bankAccountNumber", "ASVL07N6-316")
				.param("bankRoutingNumber", "111000012")
				.param("companyCode", "10443720001032191")
				.param("brandName", "10443720001032191"))
    	        .andReturn();
			
    }
 
        
}
