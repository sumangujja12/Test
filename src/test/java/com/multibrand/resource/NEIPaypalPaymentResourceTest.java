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
import com.multibrand.resources.NEIPaypalPaymentResource;
import com.multibrand.service.NEIPaypalPaymentService;
import com.nrg.cxfstubs.nei.paypal.ZEISUNEIPAYPALPAYMENTResponse;

public class NEIPaypalPaymentResourceTest {

	boolean thrown = false;

	@InjectMocks
	private NEIPaypalPaymentResource paypalPaymentResource;

	@Mock
	public NEIPaypalPaymentService paypalPaymentService;

	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@BeforeMethod
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
		paypalPaymentResource.setHttpRequest(new MockHttpServletRequest());
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

		neiPaypalPaymentResponse.setEotbdId("000000000");
		neiPaypalPaymentResponse.setXcode("00");
		neiPaypalPaymentResponse.setMessage("SUCCESSFULPAYMENT");

		try {
			Mockito.when((ZEISUNEIPAYPALPAYMENTResponse)paypalPaymentService.paypalBillPayment(paypalPaymentRequest, "SessionId1"))
					.thenReturn(zResVruPaypalPaymentResponse);
			 paypalPaymentResource.paypalPayment(paypalPaymentRequest);
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

		neiPaypalPaymentResponse.setEotbdId("000000000");
		neiPaypalPaymentResponse.setXcode("07");
		neiPaypalPaymentResponse.setMessage("SYSTEMERROR");


		try {
			Mockito.when((ZEISUNEIPAYPALPAYMENTResponse)paypalPaymentService.paypalBillPayment(paypalPaymentRequest, "SessionId1"))
					.thenReturn(zResVruPaypalPaymentResponse);
			 paypalPaymentResource.paypalPayment(paypalPaymentRequest);
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

		neiPaypalPaymentResponse.setEotbdId("000000000");
		neiPaypalPaymentResponse.setXcode("54");
		neiPaypalPaymentResponse.setMessage("FAILEDPAYMENT");


		try {
			Mockito.when((ZEISUNEIPAYPALPAYMENTResponse)paypalPaymentService.paypalBillPayment(paypalPaymentRequest, "SessionId1"))
					.thenReturn(zResVruPaypalPaymentResponse);
			 paypalPaymentResource.paypalPayment(paypalPaymentRequest);
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

		neiPaypalPaymentResponse.setEotbdId("000000000");
		neiPaypalPaymentResponse.setXcode("54");
		neiPaypalPaymentResponse.setMessage("FAILEDPAYMENT");

		try {
			Mockito.when(paypalPaymentService.paypalBillPayment(paypalPaymentRequest, Mockito.anyString()))
					.thenThrow(new Exception());
			 paypalPaymentResource.paypalPayment(paypalPaymentRequest);
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);

	}

}
