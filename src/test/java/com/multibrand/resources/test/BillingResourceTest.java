package com.multibrand.resources.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.multibrand.helper.AsyncHelper;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.resources.BillingResource;
import com.multibrand.service.ProfileService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@TestPropertySource({"file:src/test/resources/properties/environment.properties","file:src/test/resources/properties/appConstants.properties"})
@ContextConfiguration({"file:src/test/resources/webapp/WEB-INF/spring/NRGREST-appContext.xml"})
public class BillingResourceTest {
	
	@Value("${isMock}")
	private boolean isMock;
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private BillingResource billingResource;
	
	@InjectMocks
	ProfileService profileService;
	
	
	@MockBean
	private UtilityLoggerHelper utilityloggerHelper;
	
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	  @Qualifier("appConstMessageSource")
	  public ReloadableResourceBundleMessageSource appConstMessageSource;
	
	 
	 

	 
	
	private AsyncHelper asycHelper = new AsyncHelper();
	
	@Before
	public void init() {
		 MockitoAnnotations.initMocks(this); 
		 if (isMock) { 
			  MockitoAnnotations.initMocks(this); 
			  mockMvc = MockMvcBuilders.standaloneSetup().build(); 
		 } else { 
			 mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); 
		} 
	}
	
	
	@Test
	public void test_getBillingAddress_success() throws Exception {
		if(isMock) {
			System.out.println("isMock :::::   ********************************************************************************************************** " +isMock);
		} else {
			mockMvc.perform( MockMvcRequestBuilders
					  .post("/billResource/getBillingAddress")
					  .param("accountNumber","000072938063") 
					  .param("companyCode","0271")
					  .contentType(MediaType.APPLICATION_FORM_URLENCODED)
					  .accept(MediaType.APPLICATION_JSON)) 
					  .andExpect(status().isOk());
		}
		
		 
		 
		 
		 
	}
}


