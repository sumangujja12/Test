package com.multibrand.resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.multibrand.bo.AutoPayBO;
import com.multibrand.domain.PaymentDomain;
import com.multibrand.domain.ValidateBankRequest;
import com.multibrand.resources.AutoPayResource;
import com.multibrand.service.PaymentService;
import com.multibrand.domain.ValidateBankResponse;

public class AutoPayResourceTest extends AbstractJunitTest{

	private MockMvc mockMvc;
	/** providing configuration for the web application */
	@Mock
	private WebApplicationContext wac;
    
	@Mock
	PaymentDomain pmentDomain;
    //@Mock
    //private WebSecurityConfiguration webSecurityConfiguration;
    
    
    //@Mock
	//private HttpServletRequest httpRequest;


    
    @Autowired
	@InjectMocks
    private AutoPayResource autoPayResource;
    
    @Autowired
    @InjectMocks
    private AutoPayBO autoPayBO;
    
    //@Autowired
    @Mock
	private PaymentService paymentService;
    
    
    
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
    	validateBankResp1.setStrYCODE("500");
    	validateBankResp1.setErrorCode("400");
    	try {
    		
    	 //Mockito.when(autoPayBO.validateBankDetails(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(validateBankResp);	
    		
		  Mockito.when(paymentService.validateBankDetails(Mockito.any(String.class),
		  Mockito.any(String.class),Mockito.any(String.class),
		  Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class))).thenReturn(validateBankResp1);
		  
		  
		  //ValidateBankResponse bankResp = new ValidateBankResponse();
  		//Mockito.when(pmentDomain.validateBankDetails(Mockito.any(ValidateBankRequest.class))).thenReturn(bankResp);	
		  
		  autoPayResource.validateBankDetails("000072938063", "3UNKK6SY-", "111000", "0008418028", "REST");
    }catch(Exception ex) {
    	ex.printStackTrace();
    }
    
    }  
    
    @Test
    public void validateBankDetailsTest_Error() throws Exception {    	
    	ValidateBankResponse validateBankResp=new ValidateBankResponse();
    	
    	com.multibrand.domain.ValidateBankResponse validateBankResp1=new com.multibrand.domain.ValidateBankResponse();
    	//validateBankResp1.setStrYCODE("500");
    	//validateBankResp1.setErrorCode("400");
    	try {
    		
    	 //Mockito.when(autoPayBO.validateBankDetails(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(validateBankResp);	
    		
		  /*Mockito.when(paymentService.validateBankDetails(Mockito.any(String.class),
		  Mockito.any(String.class),Mockito.any(String.class),
		  Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class)
		  )).thenReturn(validateBankResp1);*/
		  
    		ValidateBankResponse bankResp = new ValidateBankResponse();
    		Mockito.when(pmentDomain.validateBankDetails(Mockito.any(ValidateBankRequest.class))).thenReturn(bankResp);
		  
		  autoPayResource.validateBankDetails("000072938063", "3UNKK6SY-", "111000", "0008418028", "REST");
    }catch(Exception ex) {
    	ex.printStackTrace();
    }
    
    }  
}
