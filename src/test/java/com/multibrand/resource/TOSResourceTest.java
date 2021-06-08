package com.multibrand.resource;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.multibrand.bo.OEBO;
import com.multibrand.resources.TOSResource;
import com.multibrand.util.AbstractJUnitTest;
import com.multibrand.vo.response.OfferResponse;

public class TOSResourceTest extends AbstractJUnitTest{

	boolean thrown = false;

	@InjectMocks
	private TOSResource tOSResource;
	

	@Mock
	public OEBO oEBO;

	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@BeforeMethod
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
		tOSResource.setHttpRequest(new MockHttpServletRequest());
	}

	@Test
	public void testTosMoveOutPlans() {
		
		
		try {
			
			String offerJsonResponse = getContent("json/tos/plan_offers_response.json");
			
			OfferResponse offerResponse = mapFromJson(offerJsonResponse, OfferResponse.class);
			
			Mockito.when((oEBO).getOffers(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), 
					Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(offerResponse);
			

			tOSResource.tosMoveOutPlans("E","0271","GM","3030","BRYAN ST","405","75204","","D0002","<ESIDNOTFOUND>","");
						
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
