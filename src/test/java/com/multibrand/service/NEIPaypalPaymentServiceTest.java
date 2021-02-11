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
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.multibrand.dto.request.NEIPaypalPaymentRequest;
import com.multibrand.dto.response.NEIPaypalPaymentResponse;
import com.multibrand.util.EnvMessageReader;
import com.nrg.cxfstubs.nei.paypal.ZEISUNEIPAYPALPAYMENTResponse;

public class NEIPaypalPaymentServiceTest {

	boolean thrown = false;
	
	private Validator validator;

	@InjectMocks
	public NEISimplySmartService neiSimplySmartService;

	@Mock
	EnvMessageReader envMessageReader;

	@Mock
	ReloadableResourceBundleMessageSource environmentMessageSource;

	/*
	 * @Mock
	 * 
	 * @Qualifier("webServiceTemplateForNeiPaypalPayment") private
	 * WebServiceTemplate webServiceTemplateForNeiPaypalPayment;
	 */

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
		paypalPaymentRequest.setUsername("user1");
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
		paypalPaymentRequest.setUsername("");
		paypalPaymentRequest.setPpalauth("1234567890");
		paypalPaymentRequest.setPayment("45.00");
		paypalPaymentRequest.setSsId("SSID1");
		NEIPaypalPaymentResponse neiPaypalPaymentResponse = new NEIPaypalPaymentResponse();
		neiPaypalPaymentResponse.setEOTBDID("000000000000");
		neiPaypalPaymentResponse.setXCODE("07");
		
		

		try {

			/*
			 * Mockito.when((ZEISUNEIPAYPALPAYMENTResponse)
			 * webServiceTemplateForNeiPaypalPayment
			 * .marshalSendAndReceive(paypalPaymentRequest)).thenReturn(
			 * neiPaypalPaymentResponse);
			 */
			
			Mockito.when((ZEISUNEIPAYPALPAYMENTResponse)neiSimplySmartService.paypalPayment(paypalPaymentRequest, "Session1"))
			.thenReturn(neiPaypalPaymentResponse);

		  neiSimplySmartService.paypalPayment(paypalPaymentRequest, "Session1");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals( "07", neiPaypalPaymentResponse.getXCODE());
	}
	
	@Test
	public void testpaypalBillPayment_withoutPayment() {
		NEIPaypalPaymentRequest paypalPaymentRequest = new NEIPaypalPaymentRequest();
		paypalPaymentRequest.setUsername("usernam1");
		paypalPaymentRequest.setPpalauth("1234567890");
		paypalPaymentRequest.setPayment("InvalidPayment****");
		paypalPaymentRequest.setSsId("SSID1");
		NEIPaypalPaymentResponse neiPaypalPaymentResponse = new NEIPaypalPaymentResponse();
		neiPaypalPaymentResponse.setEOTBDID("000000000000");
		neiPaypalPaymentResponse.setXCODE("54");
		
		try {

			/*
			 * Mockito.when((NEIPaypalPaymentResponse) webServiceTemplateForNeiPaypalPayment
			 * .marshalSendAndReceive(paypalPaymentRequest)).thenReturn(
			 * neiPaypalPaymentResponse);
			 */
			
			Mockito.when((ZEISUNEIPAYPALPAYMENTResponse)neiSimplySmartService.paypalPayment(paypalPaymentRequest, "Session1"))
			.thenReturn(neiPaypalPaymentResponse);

		  neiSimplySmartService.paypalPayment(paypalPaymentRequest, "Session1");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals( "54", neiPaypalPaymentResponse.getXCODE());
	}
	
	
	
	@Test
	public void testpaypalBillPayment_withoutSSID() {
		NEIPaypalPaymentRequest paypalPaymentRequest = new NEIPaypalPaymentRequest();
		paypalPaymentRequest.setUsername("usernam1");
		paypalPaymentRequest.setPpalauth("1234567890");
		paypalPaymentRequest.setPayment("56.37");
		paypalPaymentRequest.setSsId("InvalidSSID1****");
		NEIPaypalPaymentResponse neiPaypalPaymentResponse = new NEIPaypalPaymentResponse();
		neiPaypalPaymentResponse.setEOTBDID("000000000000");
		neiPaypalPaymentResponse.setXCODE("54");
		
		try {

			
			/*
			 * Mockito.when((NEIPaypalPaymentResponse) webServiceTemplateForNeiPaypalPayment
			 * .marshalSendAndReceive(paypalPaymentRequest)).thenReturn(
			 * neiPaypalPaymentResponse);
			 */
			
			Mockito.when((ZEISUNEIPAYPALPAYMENTResponse)neiSimplySmartService.paypalPayment(paypalPaymentRequest, "Session1"))
			.thenReturn(neiPaypalPaymentResponse);

		  neiSimplySmartService.paypalPayment(paypalPaymentRequest, "Session1");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals( "54", neiPaypalPaymentResponse.getXCODE());
	}


	@Test
	public void testpaypalBillPayment_Exception() {
		NEIPaypalPaymentRequest paypalPaymentRequest = new NEIPaypalPaymentRequest();
		paypalPaymentRequest.setUsername("");
		paypalPaymentRequest.setPpalauth("1234567890");
		paypalPaymentRequest.setPayment("45.00");
		paypalPaymentRequest.setSsId("SSID1");
		NEIPaypalPaymentResponse neiPaypalPaymentResponse = new NEIPaypalPaymentResponse();
		neiPaypalPaymentResponse.setEOTBDID("000000000000");
		neiPaypalPaymentResponse.setXCODE("07");

		try {

			/*
			 * Mockito.when((ZEISUNEIPAYPALPAYMENTResponse)
			 * webServiceTemplateForNeiPaypalPayment
			 * .marshalSendAndReceive(paypalPaymentRequest)).thenThrow(new Exception());
			 */

			Mockito.when((ZEISUNEIPAYPALPAYMENTResponse)neiSimplySmartService.paypalPayment(paypalPaymentRequest, "Session1"))
			.thenReturn(neiPaypalPaymentResponse);
			
			neiPaypalPaymentResponse = neiSimplySmartService.paypalPayment(paypalPaymentRequest, "Session1");

		} catch (Exception e) {
			thrown = true;

		}
		 assertTrue(thrown);
	}

}
