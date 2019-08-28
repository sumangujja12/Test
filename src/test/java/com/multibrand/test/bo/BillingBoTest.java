package com.multibrand.test.bo;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Map;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.multibrand.bo.BillingBO;
import com.multibrand.domain.ProfileResponse;
import com.multibrand.exception.OAMException;
import com.multibrand.service.ProfileService;
import com.multibrand.vo.response.billingResponse.GetBillingAddressResponse;

public class BillingBoTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	BillingBO billingBo;
	
	@Mock
	private ProfileService profileService;
	
	//@Rule
	//public ExpectedException exceptionRule = ExpectedException.none();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this); 
		mockMvc = MockMvcBuilders.standaloneSetup(billingBo).build(); 
	}
	
	
	@Test
	public void test_getBillingAddress_success() throws Exception{
		String getBillingAddressSuccessJson = getContent("json-response/get-billing-address-success.json"); 
		ProfileResponse profileResponse = mapFromJson(getBillingAddressSuccessJson,ProfileResponse.class); 
		Map<String,Object> profileMap = new HashedMap();
		profileMap.put("profileResponse", profileResponse);
		when(profileService.getProfile(anyString(),anyString(),anyString())).thenReturn(profileMap); 
		GetBillingAddressResponse response = billingBo.getBillingAddress(anyString(), anyString(), anyString());
		assertEquals("3138",response.getStrApartNum());
	}
	
	@Test
	public void test_getBillingAddress_noProfileData() throws Exception {
		Map<String,Object> profileMap =null;
		when(profileService.getProfile(anyString(),anyString(),anyString())).thenReturn(profileMap); 
		GetBillingAddressResponse response = billingBo.getBillingAddress(anyString(), anyString(), anyString());
		assertEquals("3",response.getResultCode());
		assertEquals("No Data",response.getResultDescription());
	}
	
	@Test(expected = OAMException.class)
	public void test_getBillingAddress_excption() throws Exception {
		when(profileService.getProfile(anyString(),anyString(),anyString())).thenThrow(new Exception()); 
		GetBillingAddressResponse response = billingBo.getBillingAddress(anyString(), anyString(), anyString());
	}
	
	@Test(expected = OAMException.class)
	public void test_getBillingAddress_remoteException() throws Exception {
		when(profileService.getProfile(anyString(),anyString(),anyString())).thenThrow(new RemoteException()); 
		GetBillingAddressResponse response = billingBo.getBillingAddress(anyString(), anyString(), anyString());
	}
	
	
	private String getContent(String fileName) throws IOException {
		InputStream fileStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
		return IOUtils.toString(fileStream);

	}
	
	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}    

}
