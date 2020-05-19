package com.multibrand.request.handler;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.multibrand.dto.OESignupDTO;
import com.multibrand.dto.response.ServiceLocationResponse;
import com.multibrand.request.handlers.OERequestHandler;
import com.multibrand.util.Constants;
import com.multibrand.util.LoggerUtil;

@Test(singleThreaded = true)
public class OERequestHandlerTest {
		
	@InjectMocks
	OERequestHandler oeRequestHandler;

	@Mock
	private LoggerUtil logger;
	
	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(logger.isDebugEnabled()).thenReturn(true);
	}
	
	@Test
	public void testsetMatchedBPForSoldAndProspectBPWithOutError(){		
		OESignupDTO oeSignUpDTO = new OESignupDTO();
		ServiceLocationResponse serviceLocationResponse = new ServiceLocationResponse();
		serviceLocationResponse.setErrorCdlist("");
		serviceLocationResponse.setProspectPartnerId("123456");
		serviceLocationResponse.setMatchedPartnerId("98765");
		Assert.assertEquals(oeRequestHandler.setMatchedBP(oeSignUpDTO,serviceLocationResponse).getMatchedPartnerID(),serviceLocationResponse.getMatchedPartnerId());
	}
	
	@Test
	public void testsetMatchedBPForSoldAndProspectBPWithBPSD(){		
		OESignupDTO oeSignUpDTO = new OESignupDTO();
		ServiceLocationResponse serviceLocationResponse = new ServiceLocationResponse();
		serviceLocationResponse.setErrorCdlist("BPSD");
		serviceLocationResponse.setProspectPartnerId("123456");
		serviceLocationResponse.setMatchedPartnerId("98765");
		Assert.assertEquals(oeRequestHandler.setMatchedBP(oeSignUpDTO,serviceLocationResponse).getMatchedPartnerID(),serviceLocationResponse.getProspectPartnerId());
	}
	
	@Test
	public void testsetMatchedBPForSoldBPWithOutError(){		
		OESignupDTO oeSignUpDTO = new OESignupDTO();
		ServiceLocationResponse serviceLocationResponse = new ServiceLocationResponse();
		serviceLocationResponse.setErrorCdlist("");
		serviceLocationResponse.setMatchedPartnerId("98765");
		Assert.assertEquals(oeRequestHandler.setMatchedBP(oeSignUpDTO,serviceLocationResponse).getMatchedPartnerID(),serviceLocationResponse.getMatchedPartnerId());
	}
	
	@Test
	public void testsetMatchedBPForProspectBP(){		
		OESignupDTO oeSignUpDTO = new OESignupDTO();
		ServiceLocationResponse serviceLocationResponse = new ServiceLocationResponse();
		serviceLocationResponse.setErrorCdlist("");
		serviceLocationResponse.setProspectPartnerId("123456");
		Assert.assertEquals(oeRequestHandler.setMatchedBP(oeSignUpDTO,serviceLocationResponse).getMatchedPartnerID(),serviceLocationResponse.getProspectPartnerId());
	}
	
}
