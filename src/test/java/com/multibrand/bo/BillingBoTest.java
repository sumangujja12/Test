package com.multibrand.bo;


import static org.mockito.Mockito.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.fasterxml.jackson.core.JsonParseException;
import com.multibrand.bo.BillingBO;
import com.multibrand.domain.ProfileResponse;
import com.multibrand.service.ProfileService;
import com.multibrand.util.TestConstants;

public class BillingBoTest implements TestConstants{
	
	@Mock
	ProfileService profileService;
	
	@InjectMocks
	BillingBO billingBo;
	
	@Test
	public void test_getBillingAddress_success() throws Exception {
		String profileResponseJson_success = getContent(GET_PROFILE_RESPONSE_SUCCESS);
		ProfileResponse profileResponse = mapFromJson(profileResponseJson_success, ProfileResponse.class);
		Map<String, Object> profileMap = new HashMap<>();
		profileMap.put("profileResponse", profileResponse);
		when(profileService.getProfile(anyString(), anyString(), anyString())).thenReturn(profileMap);
		Assert.assertEquals("ACCENT DR",
				billingBo.getBillingAddress(anyString(), anyString(), anyString()).getStrStreetName());
	}
	
	@Test
	public void test_getBillingAddress_noData() throws Exception {
		Map<String, Object> profileMap = null;
		when(profileService.getProfile(anyString(), anyString(), anyString())).thenReturn(profileMap);
		Assert.assertEquals(RESULTCODE_03,
				billingBo.getBillingAddress(anyString(), anyString(), anyString()).getResultCode());
		Assert.assertEquals(NO_DATA,
				billingBo.getBillingAddress(anyString(), anyString(), anyString()).getResultDescription());
	}
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
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
