package com.multibrand.bo.test;

import static org.mockito.Mockito.when;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.core.JsonParseException;
import com.multibrand.bo.BillingBO;
import com.multibrand.util.test.TestConstants;
import com.multibrand.vo.request.StoreUpdatePayAccountRequest;
import com.multibrand.vo.response.billingResponse.GetAccountDetailsResponse;
import org.junit.Before;



public class BillingBOTest implements TestConstants {
	
	/** providing configuration for the web application */
	@Autowired
	private WebApplicationContext wac;

	@Mock
	BillingBO billingBoMock;
	
	@Autowired
	@InjectMocks
	BillingBO billingBo;
	
	
	
	
	@Test
	public void test_getAccountDetails_withAccountId() throws IOException{
		String accountNumber = CONTRACT_ACCOUNT_NUMBER;
		String companyCode = COMPANY_CODE;
		String brandName = BRAND_NAME;
		String sessionId = SESSION_ID;
		String billingBoJson = getContent(GET_ACCOUNT_DETAILS_JSON_RES);
		GetAccountDetailsResponse accDeatilsMockReponse = mapFromJson(billingBoJson,GetAccountDetailsResponse.class);
		when(billingBoMock.getAccountDetails(accountNumber, companyCode, brandName, sessionId)).thenReturn(accDeatilsMockReponse);
		Assert.assertEquals("N", accDeatilsMockReponse.getPaymentReceiptPopupShowFlag());
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
	
	/**** Begining of modifyPayAccount test cases *****/
	
	@Test
	public void modifyPayAccount_EditccExpMonth_Success(){
		StoreUpdatePayAccountRequest storeUpdatePayAccountRequest = new StoreUpdatePayAccountRequest();
		storeUpdatePayAccountRequest.setCcExpMonth("02");
		storeUpdatePayAccountRequest.setNameOnAccount("James Jonah Jameson");
		storeUpdatePayAccountRequest.setZipCode("12345");
		storeUpdatePayAccountRequest.setContractAccountNumber("000072938063");
		storeUpdatePayAccountRequest.setOnlinePayAccountId(6); 
		storeUpdatePayAccountRequest.setActiveFlag("Y");
		storeUpdatePayAccountRequest.setPayAccountToken("T651000698351711248");
		storeUpdatePayAccountRequest.setLastFourDigit("");
		storeUpdatePayAccountRequest.setVerifyCard("");
		storeUpdatePayAccountRequest.setCcExpYear("2023");
	
		
	}
	
	
	/**** End of modifyPayAccount test cases *****/
	
	
	
	/**
	 * Initializing class members and mocks
	 */
	@Before
	public void init() {
		// Process mock annotations for mockito
		MockitoAnnotations.initMocks(this);
	}
}
