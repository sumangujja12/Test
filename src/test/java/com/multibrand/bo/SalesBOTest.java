package com.multibrand.bo;



import static org.mockito.Mockito.when;

import java.rmi.RemoteException;

import javax.ws.rs.core.Response;


import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.multibrand.dao.AddressDAOIF;
import com.multibrand.dao.ServiceLocationDao;
import com.multibrand.dto.OESignupDTO;
import com.multibrand.dto.request.IdentityRequest;
import com.multibrand.dto.request.SalesEnrollmentRequest;

import com.multibrand.dto.request.ValidateCARequest;
import com.multibrand.dto.request.ValidateUserIdRequest;

import com.multibrand.dto.request.SalesUCCDataRequest;

import com.multibrand.dto.response.PersonResponse;
import com.multibrand.dto.response.SalesEnrollmentResponse;
import com.multibrand.dto.response.SalesUCCDataResponse;
import com.multibrand.dto.response.ServiceLocationResponse;
import com.multibrand.dto.response.ValidateCAResponse;
import com.multibrand.dto.response.ValidateUserIdResponse;
import com.multibrand.exception.OEException;
import com.multibrand.proxy.OEProxy;
import com.multibrand.request.handlers.OERequestHandler;
import com.multibrand.service.OfferService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.EnrollmentFraud.ENROLLMENT_FRAUD_ENUM;
import com.multibrand.util.LoggerUtil;
import com.multibrand.util.TogglzUtil;
import com.multibrand.web.i18n.WebI18nMessageSource;

@Test(singleThreaded = true)
public class SalesBOTest implements Constants{

	

	@InjectMocks
	private SalesBO salesBO;
	
	@Mock
	private OEBO oebo;


	@Mock
	private ServiceLocationDao serviceLocationDAO;

	

	@Mock
	private LoggerUtil logger;

	/*@Mock
	protected WebI18nMessageSource msgSource;*/
	
	@Mock
	private AddressDAOIF addressDAO;

	@Spy
	ReloadableResourceBundleMessageSource viewResolverMessageSource = new ReloadableResourceBundleMessageSource();
	
	@Mock
	private OERequestHandler oeRequestHandler;
	
	@Spy
	OESignupDTO oeSignUpDTO = new OESignupDTO();
	
	
	@Mock
	private BaseBO baseBO;
	
	@Mock
	private CommonUtil commonUtil;
	
	@Mock
	private TogglzUtil togglzUtil;

	@Mock 
	OfferService offerService;
	
	@Mock
	private OEProxy oeProxy;
	
		
	@Spy
	ReloadableResourceBundleMessageSource appConstMessageSource = new ReloadableResourceBundleMessageSource();

	@Spy
	private ReloadableResourceBundleMessageSource environmentMessageSource = new ReloadableResourceBundleMessageSource();

	@Spy
	WebI18nMessageSource msgSource = new WebI18nMessageSource();

	
	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(logger.isDebugEnabled()).thenReturn(true);
		appConstMessageSource.setUseCodeAsDefaultMessage(true);
		appConstMessageSource.setDefaultEncoding("UTF-8");
		appConstMessageSource.setFallbackToSystemLocale(Boolean.TRUE);
		appConstMessageSource.setUseCodeAsDefaultMessage(true);
		
		msgSource.setUseCodeAsDefaultMessage(true);
		msgSource.setDefaultEncoding("UTF-8");
		msgSource.setFallbackToSystemLocale(Boolean.TRUE);
		msgSource.setUseCodeAsDefaultMessage(true);
		//appConstMessageSource.setBasenames("/src/main/resources/properties/appConstants");
		
		
		environmentMessageSource.setUseCodeAsDefaultMessage(true);
		environmentMessageSource.setDefaultEncoding("UTF-8");
		environmentMessageSource.setFallbackToSystemLocale(Boolean.TRUE);
		environmentMessageSource.setUseCodeAsDefaultMessage(true);
		//environmentMessageSource.setBasenames("/src/main/resources/properties/environment");
    
	}

	@BeforeMethod
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
		when(logger.isDebugEnabled()).thenReturn(true);
		appConstMessageSource.setUseCodeAsDefaultMessage(true);
		appConstMessageSource.setDefaultEncoding("UTF-8");
		appConstMessageSource.setFallbackToSystemLocale(Boolean.TRUE);
		appConstMessageSource.setUseCodeAsDefaultMessage(true);
		
		msgSource.setUseCodeAsDefaultMessage(true);
		msgSource.setDefaultEncoding("UTF-8");
		msgSource.setFallbackToSystemLocale(Boolean.TRUE);
		msgSource.setUseCodeAsDefaultMessage(true);
		//appConstMessageSource.setBasenames("/src/main/resources/properties/appConstants");
		
		environmentMessageSource.setUseCodeAsDefaultMessage(true);
		environmentMessageSource.setDefaultEncoding("UTF-8");
		environmentMessageSource.setFallbackToSystemLocale(Boolean.TRUE);
		environmentMessageSource.setUseCodeAsDefaultMessage(true);
		//environmentMessageSource.setBasenames("/src/main/resources/properties/environment");
    
	}
	
	@Test
	public void testsubmitEnrollmentSuertyNotAvailable() throws OEException{
	    ServiceLocationResponse serviceLocationResponse = new ServiceLocationResponse();
	    serviceLocationResponse.setPersonResponse(new PersonResponse());
	    serviceLocationResponse.setRequestStatusCode(I_VALUE);
	    when( oebo.getEnrollmentData(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(serviceLocationResponse);
	    
	    SalesEnrollmentRequest enrollmentRequest = new SalesEnrollmentRequest();
	    enrollmentRequest.setSecurityMethod(SURETY_BOND);
	    SalesEnrollmentResponse response = null;
		try {
			response = salesBO.submitEnrollment(enrollmentRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(response.getHttpStatus(), Response.Status.BAD_REQUEST);
	}

	@Test
	public void testValidateUserName(){
		ValidateUserIdResponse validateUserIdResponse = new ValidateUserIdResponse();
		ValidateUserIdRequest validateUserIdRequest= new ValidateUserIdRequest();
		when(oebo.validateUserName(validateUserIdRequest)).thenReturn(validateUserIdResponse);
		salesBO.validateUserName(validateUserIdRequest);
		Assert.assertEquals(validateUserIdResponse,validateUserIdResponse);
	}
	@Test
	public void testValidateCA() throws  RemoteException{
		ValidateCARequest validateCARequest = new ValidateCARequest();
		ValidateCAResponse validateCAResponse= new ValidateCAResponse();
		when(oebo.validateCA(validateCARequest)).thenReturn(validateCAResponse);
		salesBO.validateCA(validateCARequest);
		Assert.assertEquals(validateCAResponse,validateCAResponse);
	}
	@Test
	public void testPerformPosidAndBpMatchBothSSNandNoid() throws OEException{
	   IdentityRequest request = new IdentityRequest();
	   request.setTokenizedSSN("testSSN");
	   request.setNoid("true");
	   Response response = null;
		try {
			response = salesBO.performPosidAndBpMatch(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(response.getStatus(), 400);
	}
	
	@Test
	public void testPerformPosidAndBpMatchBothDLandNoid() throws OEException{
	   IdentityRequest request = new IdentityRequest();
	   request.setTokenizedTDL("testTDL");
	   request.setNoid("true");
	   Response response = null;
		try {
			response = salesBO.performPosidAndBpMatch(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(response.getStatus(), 400);
	}



	@Test
	public void testSubmitUCCDataForMandatoryParam(){
		SalesUCCDataRequest salesUCCDatarequest =new SalesUCCDataRequest();
		salesUCCDatarequest.setDepositAmount("125");
		SalesUCCDataResponse salesUCCDataResponse =new SalesUCCDataResponse();
		String httpServletRequest = "123456";
		ServiceLocationResponse serviceLocationResponse = null;
		try{
			when(oebo.isEnrollmentAlreadySubmitted(serviceLocationResponse)).thenReturn(false);
			when(oebo.getEnrollmentData(salesUCCDatarequest.getTrackingId())).thenReturn(serviceLocationResponse);
	    salesUCCDataResponse = salesBO.submitUCCData(salesUCCDatarequest, httpServletRequest);
		}catch(Exception e){
			e.printStackTrace();
		}
		Assert.assertEquals(salesUCCDataResponse.getStatusCode(), STATUS_CODE_STOP);
	}
	
	@Test
	public void testSubmitUCCDataForNegaviteValueInParam(){
		SalesUCCDataRequest salesUCCDatarequest =new SalesUCCDataRequest();
		salesUCCDatarequest.setDepositAmount("-1");
		salesUCCDatarequest.setFirstName("test");
		salesUCCDatarequest.setLastName("test");
		SalesUCCDataResponse salesUCCDataResponse =new SalesUCCDataResponse();
		String httpServletRequest = "123456";
		ServiceLocationResponse serviceLocationResponse = null;
		try{
			when(oebo.isEnrollmentAlreadySubmitted(serviceLocationResponse)).thenReturn(false);
			when(oebo.getEnrollmentData(salesUCCDatarequest.getTrackingId())).thenReturn(serviceLocationResponse);
	    salesUCCDataResponse = salesBO.submitUCCData(salesUCCDatarequest, httpServletRequest);
		}catch(Exception e){
			e.printStackTrace();
		}
		Assert.assertEquals(salesUCCDataResponse.getStatusCode(), STATUS_CODE_STOP);
	}
	
	@Test
	public void testSubmitUCCDataForDepositamount(){
		SalesUCCDataRequest salesUCCDatarequest =new SalesUCCDataRequest();
		salesUCCDatarequest.setDepositAmount("0");
		salesUCCDatarequest.setFirstName("test");
		salesUCCDatarequest.setLastName("test");
		SalesUCCDataResponse salesUCCDataResponse =new SalesUCCDataResponse();
		String httpServletRequest = "123456";
		ServiceLocationResponse serviceLocationResponse = null;
		try{
			when(oebo.getEnrollmentData(salesUCCDatarequest.getTrackingId())).thenReturn(serviceLocationResponse);
			when(oebo.isEnrollmentAlreadySubmitted(serviceLocationResponse)).thenReturn(false);
	    salesUCCDataResponse = salesBO.submitUCCData(salesUCCDatarequest, httpServletRequest);
		}catch(Exception e){
			e.printStackTrace();
		}
		Assert.assertEquals(salesUCCDataResponse.getStatusCode(), STATUS_CODE_STOP);
	}
	
	@Test
	public void testSubmitUCCDataForMismatchData(){
		SalesUCCDataRequest salesUCCDatarequest =new SalesUCCDataRequest();
		salesUCCDatarequest.setDepositAmount("125");
		salesUCCDatarequest.setFirstName("test1");
		salesUCCDatarequest.setLastName("test");
		SalesUCCDataResponse salesUCCDataResponse =new SalesUCCDataResponse();
		String httpServletRequest = "123456";
		ServiceLocationResponse serviceLocationResponse = new ServiceLocationResponse();
		PersonResponse personResponse=new PersonResponse();
		personResponse.setFirstName("test");
		personResponse.setLastName("test1");
		serviceLocationResponse.setPersonResponse(personResponse);
		try{
			when(oebo.isEnrollmentAlreadySubmitted(serviceLocationResponse)).thenReturn(false);
			when(oebo.getEnrollmentData(salesUCCDatarequest.getTrackingId())).thenReturn(serviceLocationResponse);
	    salesUCCDataResponse = salesBO.submitUCCData(salesUCCDatarequest, httpServletRequest);
		}catch(Exception e){
			e.printStackTrace();
		}
		Assert.assertEquals(salesUCCDataResponse.getStatusCode(), STATUS_CODE_STOP);
	}
	
	@Test
	public void testSubmitUCCDataForEnrollmentAlreadysubmitted(){
		SalesUCCDataRequest salesUCCDatarequest =new SalesUCCDataRequest();
		salesUCCDatarequest.setDepositAmount("125");
		salesUCCDatarequest.setFirstName("test1");
		salesUCCDatarequest.setLastName("test");
		SalesUCCDataResponse salesUCCDataResponse =new SalesUCCDataResponse();
		String httpServletRequest = "123456";
		ServiceLocationResponse serviceLocationResponse = new ServiceLocationResponse();
		PersonResponse personResponse=new PersonResponse();
		serviceLocationResponse.setPersonResponse(personResponse);
		try{
			when(oebo.isEnrollmentAlreadySubmitted(serviceLocationResponse)).thenReturn(true);
			when(oebo.getEnrollmentData(salesUCCDatarequest.getTrackingId())).thenReturn(serviceLocationResponse);
	    salesUCCDataResponse = salesBO.submitUCCData(salesUCCDatarequest, httpServletRequest);
		}catch(Exception e){
			e.printStackTrace();
		}
		Assert.assertEquals(salesUCCDataResponse.getStatusCode(), STATUS_CODE_STOP);
	}
	
	@Test
	public void testSubmitUCCDataForInvalidTrackingId(){
		SalesUCCDataRequest salesUCCDatarequest =new SalesUCCDataRequest();
		salesUCCDatarequest.setDepositAmount("125");
		salesUCCDatarequest.setFirstName("test1");
		salesUCCDatarequest.setLastName("test");
		SalesUCCDataResponse salesUCCDataResponse =new SalesUCCDataResponse();
		String httpServletRequest = "123456";
		ServiceLocationResponse serviceLocationResponse = null;
		
		try{
			when(oebo.isEnrollmentAlreadySubmitted(serviceLocationResponse)).thenReturn(true);
			when(oebo.getEnrollmentData(salesUCCDatarequest.getTrackingId())).thenReturn(serviceLocationResponse);
	    salesUCCDataResponse = salesBO.submitUCCData(salesUCCDatarequest, httpServletRequest);
		}catch(Exception e){
			e.printStackTrace();
		}
		Assert.assertEquals(salesUCCDataResponse.getStatusCode(), STATUS_CODE_STOP);
	}
	


}