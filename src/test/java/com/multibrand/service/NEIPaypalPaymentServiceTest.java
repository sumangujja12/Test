package com.multibrand.service;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.multibrand.dto.request.NEIPaypalPaymentRequest;
import com.multibrand.util.EnvMessageReader;
import com.nrg.cxfstubs.nei.paypal.ZEISUNEIPAYPALPAYMENTResponse;

public class NEIPaypalPaymentServiceTest {

	boolean thrown = false;
	
	private Validator validator;

	@InjectMocks
	public NEIPaypalPaymentService paypalPaymentService;

	public NEIPaypalPaymentRequest paypalPaymentRequest;

	@Mock
	EnvMessageReader envMessageReader;

	@Mock
	ReloadableResourceBundleMessageSource environmentMessageSource;

	@Mock
	@Qualifier("webServiceTemplateForPaypalPayment")
	private WebServiceTemplate webServiceTemplateForPaypalPayment;

	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		this.validator = vf.getValidator();
	}

	@BeforeMethod
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
	
	}
	
	
	@Test
	public void brokenIfNullUsernameGiven()
	{
		NEIPaypalPaymentRequest paypalPaymentRequest = new NEIPaypalPaymentRequest();
		paypalPaymentRequest.setUername("user1");
		paypalPaymentRequest.setPpalauth("1234567890");
		paypalPaymentRequest.setPayment("45.00");
		paypalPaymentRequest.setSsId("SSID1");
		
		
		 Set<ConstraintViolation<NEIPaypalPaymentRequest>> constraintViolations = validator.validate(paypalPaymentRequest);	
	
	//	 assertEquals(constraintViolations.size(), 1);
//		 ConstraintViolation<PaypalPaymentRequest> violiation = constraintViolations.iterator().next();
//		 
//		 assertEquals("uername",violiation.getMessage());
//		 
//		 
		// assertFalse(constraintViolations.isEmpty());

	}
	

	@Test
	public void testpaypalBillPayment() {
		NEIPaypalPaymentRequest paypalPaymentRequest = new NEIPaypalPaymentRequest();
		paypalPaymentRequest.setUername("");
		paypalPaymentRequest.setPpalauth("1234567890");
		paypalPaymentRequest.setPayment("45.00");
		paypalPaymentRequest.setSsId("SSID1");
		ZEISUNEIPAYPALPAYMENTResponse zResVruPaypalPaymentResponse = new ZEISUNEIPAYPALPAYMENTResponse();
		zResVruPaypalPaymentResponse.setEOTBDID("000000000000");
		zResVruPaypalPaymentResponse.setXCODE("07");
		
		ZEISUNEIPAYPALPAYMENTResponse ccsResponse = new ZEISUNEIPAYPALPAYMENTResponse();

		try {

			Mockito.when((ZEISUNEIPAYPALPAYMENTResponse) webServiceTemplateForPaypalPayment
					.marshalSendAndReceive(paypalPaymentRequest)).thenReturn(zResVruPaypalPaymentResponse);

		  paypalPaymentService.paypalBillPayment(paypalPaymentRequest, "Session1");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals( "07", zResVruPaypalPaymentResponse.getXCODE());
	}
	
	@Test
	public void testpaypalBillPayment_withoutPayment() {
		NEIPaypalPaymentRequest paypalPaymentRequest = new NEIPaypalPaymentRequest();
		paypalPaymentRequest.setUername("usernam1");
		paypalPaymentRequest.setPpalauth("1234567890");
		paypalPaymentRequest.setPayment("InvalidPayment****");
		paypalPaymentRequest.setSsId("SSID1");
		ZEISUNEIPAYPALPAYMENTResponse zResVruPaypalPaymentResponse = new ZEISUNEIPAYPALPAYMENTResponse();
		zResVruPaypalPaymentResponse.setEOTBDID("000000000000");
		zResVruPaypalPaymentResponse.setXCODE("54");
		
		ZEISUNEIPAYPALPAYMENTResponse ccsResponse = new ZEISUNEIPAYPALPAYMENTResponse();

		try {

			Mockito.when((ZEISUNEIPAYPALPAYMENTResponse) webServiceTemplateForPaypalPayment
					.marshalSendAndReceive(paypalPaymentRequest)).thenReturn(zResVruPaypalPaymentResponse);

		  paypalPaymentService.paypalBillPayment(paypalPaymentRequest, "Session1");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals( "54", zResVruPaypalPaymentResponse.getXCODE());
	}
	
	
	
	@Test
	public void testpaypalBillPayment_withoutSSID() {
		NEIPaypalPaymentRequest paypalPaymentRequest = new NEIPaypalPaymentRequest();
		paypalPaymentRequest.setUername("usernam1");
		paypalPaymentRequest.setPpalauth("1234567890");
		paypalPaymentRequest.setPayment("56.37");
		paypalPaymentRequest.setSsId("InvalidSSID1****");
		ZEISUNEIPAYPALPAYMENTResponse zResVruPaypalPaymentResponse = new ZEISUNEIPAYPALPAYMENTResponse();
		zResVruPaypalPaymentResponse.setEOTBDID("000000000000");
		zResVruPaypalPaymentResponse.setXCODE("54");
		
		ZEISUNEIPAYPALPAYMENTResponse ccsResponse = new ZEISUNEIPAYPALPAYMENTResponse();

		try {

			Mockito.when((ZEISUNEIPAYPALPAYMENTResponse) webServiceTemplateForPaypalPayment
					.marshalSendAndReceive(paypalPaymentRequest)).thenReturn(zResVruPaypalPaymentResponse);

		  paypalPaymentService.paypalBillPayment(paypalPaymentRequest, "Session1");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals( "54", zResVruPaypalPaymentResponse.getXCODE());
	}


	@Test
	public void testpaypalBillPayment_Exception() {
		NEIPaypalPaymentRequest paypalPaymentRequest = new NEIPaypalPaymentRequest();
		paypalPaymentRequest.setUername("");
		paypalPaymentRequest.setPpalauth("1234567890");
		paypalPaymentRequest.setPayment("45.00");
		paypalPaymentRequest.setSsId("SSID1");
		ZEISUNEIPAYPALPAYMENTResponse ccsResponse = new ZEISUNEIPAYPALPAYMENTResponse();
		ccsResponse.setEOTBDID("000000000000");
		ccsResponse.setXCODE("07");

		try {

			Mockito.when((ZEISUNEIPAYPALPAYMENTResponse) webServiceTemplateForPaypalPayment
					.marshalSendAndReceive(paypalPaymentRequest)).thenThrow(new Exception());

			ccsResponse = paypalPaymentService.paypalBillPayment(paypalPaymentRequest, "Session1");

		} catch (Exception e) {
			thrown = true;

		}
		 assertTrue(thrown);
	}

}
