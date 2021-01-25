package com.multibrand.resource;

import static org.junit.Assert.assertTrue;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.multibrand.dto.request.NEIPaypalPaymentRequest;
import com.multibrand.dto.response.NEIPaypalPaymentResponse;
import com.multibrand.resources.NEISimplySmartResource;
import com.multibrand.service.NEISimplySmartService;
import com.nrg.cxfstubs.nei.paypal.ZEISUNEIPAYPALPAYMENTResponse;

public class NEIPaypalPaymentResourceTest {

	boolean thrown = false;

	@InjectMocks
	private NEISimplySmartResource neiSimplySmartResource;

	@Mock
	public NEISimplySmartService neiSimplySmartService;

	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@BeforeMethod
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
		neiSimplySmartResource.setHttpRequest(new MockHttpServletRequest());
	}

	@Test
	public void testPaypalPayment() {
		NEIPaypalPaymentRequest paypalPaymentRequest = new NEIPaypalPaymentRequest();
		paypalPaymentRequest.setUername("");
		paypalPaymentRequest.setPpalauth("1234567890");
		paypalPaymentRequest.setPayment("45.00");
		paypalPaymentRequest.setSsId("SSID1");
		ZEISUNEIPAYPALPAYMENTResponse zResVruPaypalPaymentResponse = new ZEISUNEIPAYPALPAYMENTResponse();
		zResVruPaypalPaymentResponse.setEOTBDID("000000000000");
		zResVruPaypalPaymentResponse.setXCODE("00");

		ZEISUNEIPAYPALPAYMENTResponse ccsResponse = new ZEISUNEIPAYPALPAYMENTResponse();
		ccsResponse.setEOTBDID("000000000000");
		ccsResponse.setXCODE("00");
		

		NEIPaypalPaymentResponse neiPaypalPaymentResponse = new NEIPaypalPaymentResponse();

		neiPaypalPaymentResponse.setEOTBDID("000000000");
		neiPaypalPaymentResponse.setXCODE("00");
		
		try {
			Mockito.when((NEIPaypalPaymentResponse)neiSimplySmartService.paypalPayment(paypalPaymentRequest, "SessionId1"))
					.thenReturn(neiPaypalPaymentResponse);
			 neiSimplySmartResource.paypalPayment(paypalPaymentRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testPaypalPayment_SYSTEMERROR() {
		NEIPaypalPaymentRequest paypalPaymentRequest = new NEIPaypalPaymentRequest();
		paypalPaymentRequest.setUername("");
		paypalPaymentRequest.setPpalauth("1234567890");
		paypalPaymentRequest.setPayment("45.00");
		paypalPaymentRequest.setSsId("SSID1");
		ZEISUNEIPAYPALPAYMENTResponse zResVruPaypalPaymentResponse = new ZEISUNEIPAYPALPAYMENTResponse();
		zResVruPaypalPaymentResponse.setEOTBDID("000000000000");
		zResVruPaypalPaymentResponse.setXCODE("07");

		ZEISUNEIPAYPALPAYMENTResponse ccsResponse = new ZEISUNEIPAYPALPAYMENTResponse();
		ccsResponse.setEOTBDID("000000000000");
		ccsResponse.setXCODE("07");
		

		NEIPaypalPaymentResponse neiPaypalPaymentResponse = new NEIPaypalPaymentResponse();

		neiPaypalPaymentResponse.setEOTBDID("000000000");
		neiPaypalPaymentResponse.setXCODE("07");
		


		try {
			Mockito.when((NEIPaypalPaymentResponse)neiSimplySmartService.paypalPayment(paypalPaymentRequest, "SessionId1"))
					.thenReturn(neiPaypalPaymentResponse);
			 neiSimplySmartResource.paypalPayment(paypalPaymentRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testPaypalPayment_FAILEDPAYMENT() {
		NEIPaypalPaymentRequest paypalPaymentRequest = new NEIPaypalPaymentRequest();
		paypalPaymentRequest.setUername("");
		paypalPaymentRequest.setPpalauth("1234567890");
		paypalPaymentRequest.setPayment("45.00");
		paypalPaymentRequest.setSsId("SSID1");
		ZEISUNEIPAYPALPAYMENTResponse zResVruPaypalPaymentResponse = new ZEISUNEIPAYPALPAYMENTResponse();
		zResVruPaypalPaymentResponse.setEOTBDID("000000000000");
		zResVruPaypalPaymentResponse.setXCODE("54");

		ZEISUNEIPAYPALPAYMENTResponse ccsResponse = new ZEISUNEIPAYPALPAYMENTResponse();
		ccsResponse.setEOTBDID("000000000000");
		ccsResponse.setXCODE("54");
		

		NEIPaypalPaymentResponse neiPaypalPaymentResponse = new NEIPaypalPaymentResponse();

		neiPaypalPaymentResponse.setEOTBDID("000000000");
		neiPaypalPaymentResponse.setXCODE("54");
		

		try {
			Mockito.when((NEIPaypalPaymentResponse)neiSimplySmartService.paypalPayment(paypalPaymentRequest, "SessionId1"))
					.thenReturn(neiPaypalPaymentResponse);
			 neiSimplySmartResource.paypalPayment(paypalPaymentRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPaypalPayment_Exception() {
		NEIPaypalPaymentRequest paypalPaymentRequest = new NEIPaypalPaymentRequest();
		paypalPaymentRequest.setUername("");
		paypalPaymentRequest.setPpalauth("1234567890");
		paypalPaymentRequest.setPayment("45.00");
		paypalPaymentRequest.setSsId("SSID1");
		
		ZEISUNEIPAYPALPAYMENTResponse zResVruPaypalPaymentResponse = new ZEISUNEIPAYPALPAYMENTResponse();
		zResVruPaypalPaymentResponse.setEOTBDID("000000000000");
		zResVruPaypalPaymentResponse.setXCODE("54");

		ZEISUNEIPAYPALPAYMENTResponse ccsResponse = new ZEISUNEIPAYPALPAYMENTResponse();
		ccsResponse.setEOTBDID("000000000000");
		ccsResponse.setXCODE("54");
		

		NEIPaypalPaymentResponse neiPaypalPaymentResponse = new NEIPaypalPaymentResponse();

		neiPaypalPaymentResponse.setEOTBDID("000000000");
		neiPaypalPaymentResponse.setXCODE("54");
		
		try {
			Mockito.when(neiSimplySmartService.paypalPayment(paypalPaymentRequest, Mockito.anyString()))
					.thenThrow(new Exception());
			 neiSimplySmartResource.paypalPayment(paypalPaymentRequest);
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);

	}

}
