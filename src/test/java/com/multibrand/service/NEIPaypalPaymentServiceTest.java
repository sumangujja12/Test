package com.multibrand.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.ws.Holder;

import org.mockito.InjectMocks;
import org.mockito.Matchers;
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
import com.multibrand.dto.response.NEIPaypalPaymentResponse;
import com.multibrand.util.EnvMessageReader;
import com.nrg.cxfstubs.nei.paypal.ZEISUNEIPAYPALPAYMENT;
import com.nrg.cxfstubs.nei.paypal.ZEISUNEIPAYPALPAYMENTResponse;
import com.nrg.cxfstubs.nei.paypal.ZEISUNEIPAYPALPAYMENT_Service;

public class NEIPaypalPaymentServiceTest {

	boolean thrown = false;
	
	private Validator validator;

	@InjectMocks
	public NEISimplySmartService neiSimplySmartService;

	public NEIPaypalPaymentRequest paypalPaymentRequest;

	@Mock
	EnvMessageReader envMessageReader;

	@Mock
	ReloadableResourceBundleMessageSource environmentMessageSource;
	
	@Mock 
	ZEISUNEIPAYPALPAYMENT stub;
	 
	@Mock 
	ZEISUNEIPAYPALPAYMENT_Service zeISUNEIPAYPALPAYMENT_Service;
	 
	 

	@Mock
	@Qualifier("webServiceTemplateForNeiPaypalPayment")
	private WebServiceTemplate webServiceTemplateForNeiPaypalPayment;

	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
		Mockito.reset(stub);
		
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		this.validator = vf.getValidator();
	}

	@BeforeMethod
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
		Mockito.reset(stub);
		Mockito.reset(envMessageReader);
		Mockito.reset(environmentMessageSource);
		Mockito.reset(zeISUNEIPAYPALPAYMENT_Service);
		
		//envMessageReader = new EnvMessageReader();
	    //ReflectionTestUtils.setField(neiSimplySmartService, "envMessageReader", envMessageReader);
	
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

			Mockito.when((ZEISUNEIPAYPALPAYMENTResponse) webServiceTemplateForNeiPaypalPayment
					.marshalSendAndReceive(paypalPaymentRequest)).thenReturn(neiPaypalPaymentResponse);
			
			Mockito.when(envMessageReader.getMessage("CCSUSERNAME")).thenReturn("testUser");
			Mockito.when(envMessageReader.getMessage("CCSPASSWORD")).thenReturn("testPwd");
			
			Mockito.when(envMessageReader.getMessage("CCS_NEI_PAYPAL")).thenReturn("");
			
			Mockito.when(zeISUNEIPAYPALPAYMENT_Service.getZEISUNEIPAYPALPAYMENT()).thenReturn((ZEISUNEIPAYPALPAYMENT) Mockito.anyObject());
			
			Holder<String> eOTBDID =new Holder<String>();
			Holder<String> xcode =new Holder<String>();
			eOTBDID.value = "000000000000";
			xcode.value = "54";
			
			doNothing().when(stub).zEISUNEIPAYPALPAYMENT(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), eOTBDID, xcode);
			
		  neiSimplySmartService.paypalPayment(paypalPaymentRequest, "Session1");
			
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
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

			Mockito.when((NEIPaypalPaymentResponse) webServiceTemplateForNeiPaypalPayment
					.marshalSendAndReceive(paypalPaymentRequest)).thenReturn(neiPaypalPaymentResponse);

			Mockito.when(envMessageReader.getMessage("CCSUSERNAME")).thenReturn("testUser");
			
			Mockito.when(envMessageReader.getMessage("CCSPASSWORD")).thenReturn("testPwd");
			
			
			Mockito.when(envMessageReader.getMessage("CCS_NEI_PAYPAL")).thenReturn("");

			
			Mockito.when(zeISUNEIPAYPALPAYMENT_Service.getZEISUNEIPAYPALPAYMENT()).thenReturn((ZEISUNEIPAYPALPAYMENT) stub);
			
			
			Holder<String> eOTBDID =new Holder<String>();
			Holder<String> xcode =new Holder<String>();
			eOTBDID.value = "000000000000";
			xcode.value = "54";
			
//			Mockito.doNothing().when(stub).zEISUNEIPAYPALPAYMENT(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), eOTBDID, xcode);
			
			doNothing().when(stub).zEISUNEIPAYPALPAYMENT(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), eOTBDID, xcode);
			
			neiSimplySmartService.paypalPayment(paypalPaymentRequest, "Session1");
			
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
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

			Mockito.when((NEIPaypalPaymentResponse) webServiceTemplateForNeiPaypalPayment
					.marshalSendAndReceive(paypalPaymentRequest)).thenReturn(neiPaypalPaymentResponse);
			
			Mockito.when(envMessageReader.getMessage("CCSUSERNAME")).thenReturn("testUser");
			Mockito.when(envMessageReader.getMessage("CCSPASSWORD")).thenReturn("testPwd");
			
			Mockito.when(envMessageReader.getMessage("CCS_NEI_PAYPAL")).thenReturn("");
			
			Mockito.when(zeISUNEIPAYPALPAYMENT_Service.getZEISUNEIPAYPALPAYMENT()).thenReturn((ZEISUNEIPAYPALPAYMENT) Mockito.anyObject());
			
			Holder<String> eOTBDID =new Holder<String>();
			Holder<String> xcode =new Holder<String>();
			eOTBDID.value = "000000000000";
			xcode.value = "54";
			
			doNothing().when(stub).zEISUNEIPAYPALPAYMENT(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), eOTBDID, xcode);
			
		  neiSimplySmartService.paypalPayment(paypalPaymentRequest, "Session1");
			
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
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

			Mockito.when((ZEISUNEIPAYPALPAYMENTResponse) webServiceTemplateForNeiPaypalPayment
					.marshalSendAndReceive(paypalPaymentRequest)).thenThrow(new Exception());

			neiPaypalPaymentResponse = neiSimplySmartService.paypalPayment(paypalPaymentRequest, "Session1");

		} catch (Exception e) {
			thrown = true;

		}
		 assertTrue(thrown);
	}

}
