package com.multibrand.request.handler;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.multibrand.bo.OEBO;
import com.multibrand.domain.SubmitEnrollRequest;
import com.multibrand.dto.AddressDTO;
import com.multibrand.dto.CreditCheckDTO;
import com.multibrand.dto.OESignupDTO;
import com.multibrand.dto.OfferDTO;
import com.multibrand.dto.PersonDTO;
import com.multibrand.dto.request.EnrollmentRequest;
import com.multibrand.dto.response.PersonResponse;
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
	
	@Mock
	private OEBO oebo;
	
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
	
	@Test
	public void testCreateSubmitEnrollRequestForSecurityMethodSuretyBond(){
		SubmitEnrollRequest request = new SubmitEnrollRequest();
		OESignupDTO oeSignUpDTO = new OESignupDTO();
		PersonDTO persondto =new PersonDTO();
		oeSignUpDTO.setPerson(persondto);
		AddressDTO addressdto=new AddressDTO();
		oeSignUpDTO.setBillingAddress(addressdto);
		oeSignUpDTO.setServiceAddress(addressdto);
		CreditCheckDTO creditcheckdto =new CreditCheckDTO();
		creditcheckdto.setSecurityMethod(Constants.SURETY_BOND);
		creditcheckdto.setActivationFee("45");
		creditcheckdto.setBondPrice("81");
		creditcheckdto.setAccSecStatus("Y");
		creditcheckdto.setIsPayUpfront(Constants.FLAG_X);
		oeSignUpDTO.setCreditCheck(creditcheckdto);
		oeSignUpDTO.setServiceStartDate("02021990");
		OfferDTO offerdto=new OfferDTO();
		oeSignUpDTO.setSelectedOffer(offerdto);
		request=oeRequestHandler.createSubmitEnrollRequest(oeSignUpDTO);
		Assert.assertEquals("Y",request.getAcctSecStatus());
	}
	
	@Test
	public void testCreateSubmitEnrollRequestForSuretyBondWithPayupfrontEmpty(){
		SubmitEnrollRequest request = new SubmitEnrollRequest();
		OESignupDTO oeSignUpDTO = new OESignupDTO();
		PersonDTO persondto =new PersonDTO();
		oeSignUpDTO.setPerson(persondto);
		AddressDTO addressdto=new AddressDTO();
		oeSignUpDTO.setBillingAddress(addressdto);
		oeSignUpDTO.setServiceAddress(addressdto);
		CreditCheckDTO creditcheckdto =new CreditCheckDTO();
		creditcheckdto.setSecurityMethod(Constants.SURETY_BOND);
		creditcheckdto.setActivationFee("45");
		creditcheckdto.setBondPrice("81");
		creditcheckdto.setAccSecStatus("Y");
		creditcheckdto.setIsPayUpfront(Constants.EMPTY);
		oeSignUpDTO.setCreditCheck(creditcheckdto);
		oeSignUpDTO.setServiceStartDate("02021990");
		OfferDTO offerdto=new OfferDTO();
		oeSignUpDTO.setSelectedOffer(offerdto);
		request=oeRequestHandler.createSubmitEnrollRequest(oeSignUpDTO);
		Assert.assertEquals("Y",request.getAcctSecStatus());
	}
	
	@Test
	public void testCreateSubmitEnrollRequestForSecurityMEthodDeposit(){
		SubmitEnrollRequest request = new SubmitEnrollRequest();
		OESignupDTO oeSignUpDTO = new OESignupDTO();
		PersonDTO persondto =new PersonDTO();
		oeSignUpDTO.setPerson(persondto);
		AddressDTO addressdto=new AddressDTO();
		oeSignUpDTO.setBillingAddress(addressdto);
		oeSignUpDTO.setServiceAddress(addressdto);
		CreditCheckDTO creditcheckdto =new CreditCheckDTO();
		creditcheckdto.setSecurityMethod(Constants.DEPOSIT);
		oeSignUpDTO.setCreditCheck(creditcheckdto);
		oeSignUpDTO.setServiceStartDate("02021990");
		OfferDTO offerdto=new OfferDTO();
		oeSignUpDTO.setSelectedOffer(offerdto);
		request=oeRequestHandler.createSubmitEnrollRequest(oeSignUpDTO);
		Assert.assertEquals(null,request.getCustFee());
	}
	
	@Test
	public void testCreateOeSignupDtoByMinimal(){
		EnrollmentRequest enrollmentRequest=new EnrollmentRequest();
		enrollmentRequest.setSecurityMethod(Constants.SURETY_BOND);
		OESignupDTO oeSignUpDTO = new OESignupDTO();
		ServiceLocationResponse serviceLoationResponse=new ServiceLocationResponse();
		serviceLoationResponse.setActivationFee("45");
		serviceLoationResponse.setBondPrice("81");
		serviceLoationResponse.setAccSecStatus("Y");
		serviceLoationResponse.setIsPayUpFront("X");
		PersonResponse personResponse=new PersonResponse();
		serviceLoationResponse.setPersonResponse(personResponse);
		when(oebo.getEnrollmentData(enrollmentRequest.getTrackingId())).thenReturn(serviceLoationResponse);
		oeSignUpDTO=oeRequestHandler.createOeSignupDtoByMinimal(enrollmentRequest, oeSignUpDTO, serviceLoationResponse);
		Assert.assertEquals(true,null != oeSignUpDTO.getCreditCheck());
	}
}
