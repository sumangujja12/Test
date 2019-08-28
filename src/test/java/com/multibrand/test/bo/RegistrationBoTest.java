package com.multibrand.test.bo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.multibrand.bo.RegistrationBO;
import com.multibrand.domain.ProfileResponse;
import com.multibrand.service.ProfileService;
import com.multibrand.vo.response.ValidateAccountResponse;

public class RegistrationBoTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	RegistrationBO registrationBO;
	
	@Mock
	ProfileService profileService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this); 
		mockMvc = MockMvcBuilders.standaloneSetup(registrationBO).build(); 
	}
	
	@Test
	public void test_validateAccount_success() throws Exception {
		/*
		 * String getBillingAddressSuccessJson =
		 * getContent("json-response/validate-account-registration-success.json");
		 * ProfileResponse profileResponse =
		 * mapFromJson(getBillingAddressSuccessJson,ProfileResponse.class);
		 * Map<String,Object> profileMap = new HashedMap();
		 * profileMap.put("profileResponse", profileResponse);
		 * when(profileService.getProfile("","","")).thenReturn(profileMap);
		 * ValidateAccountResponse validateAccountResponse =
		 * registrationBO.validateAccount("","","",""); assertEquals("0",
		 * validateAccountResponse.getResultCode());
		 */
		
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
