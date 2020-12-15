package com.multibrand.bo;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;
/*
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
*/import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
import com.multibrand.domain.KbaQuestionDTO;
import com.multibrand.bo.helper.OeBoHelper;
import com.multibrand.dao.AddressDAOIF;
import com.multibrand.dao.KbaDAO;
import com.multibrand.dao.ServiceLocationDao;
import com.multibrand.dao.impl.AddressDAOImpl;
import com.multibrand.domain.KbaAnswerDTO;
import com.multibrand.domain.KbaErrorDTO;
import com.multibrand.domain.KbaQuestionRequest;
import com.multibrand.domain.KbaQuestionResponse;
import com.multibrand.domain.KbaResponseAssessmentDTO;
import com.multibrand.domain.KbaResponseOutputDTO;
import com.multibrand.domain.KbaResponseReasonDTO;
import com.multibrand.domain.KbaSubmitAnswerRequest;
import com.multibrand.domain.KbaSubmitAnswerResponse;
import com.multibrand.domain.OfferPricingRequest;
import com.multibrand.domain.PromoOfferAvgPriceData;
import com.multibrand.domain.PromoOfferOutData;
import com.multibrand.domain.PromoOfferResponse;
import com.multibrand.domain.PromoOfferTDSPCharge;
import com.multibrand.dto.AddressDTO;
import com.multibrand.dto.ESIDDTO;
import com.multibrand.dto.KBASubmitResultsDTO;
import com.multibrand.dto.OESignupDTO;
import com.multibrand.dto.OfferDTO;
import com.multibrand.dto.request.EnrollmentRequest;
import com.multibrand.dto.request.EsidRequest;
import com.multibrand.dto.request.GetKBAQuestionsRequest;
import com.multibrand.dto.request.KbaAnswerRequest;
import com.multibrand.dto.request.SalesOfferDetailsRequest;
import com.multibrand.dto.request.UpdatePersonRequest;
import com.multibrand.dto.request.UpdateServiceLocationRequest;
import com.multibrand.service.OEService;
import com.multibrand.service.OfferService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.EnrollmentFraud.ENROLLMENT_FRAUD_ENUM;
import com.multibrand.util.LoggerUtil;
import com.multibrand.util.TogglzUtil;
import com.multibrand.vo.request.KBAQuestionAnswerVO;
import com.multibrand.vo.response.GetKBAQuestionsResponse;
import com.multibrand.vo.response.KbaAnswerResponse;
import com.multibrand.vo.response.PromoOfferOutDataAvgPriceMapEntry;
import com.multibrand.web.i18n.WebI18nMessageSource;
import com.multibrand.vo.request.ESIDData;
import com.multibrand.vo.request.EnrollmentReportDataRequest;
import com.multibrand.dto.response.AffiliateOfferResponse;
import com.multibrand.dto.response.EnrollmentResponse;
import com.multibrand.dto.response.EsidResponse;
import com.multibrand.dto.response.PersonResponse;
import com.multibrand.dto.response.ServiceLocationResponse;
import com.multibrand.exception.OEException;
import com.multibrand.request.handlers.OERequestHandler;

@Test(singleThreaded = true)
public class OEBOTest implements Constants{

	@InjectMocks
	private OEBO oebo;

	@Mock
	OEService oeService;

	@Mock
	private ServiceLocationDao serviceLocationDAO;

	@Mock
	private KbaDAO kbaDao;

	@Mock
	private LoggerUtil logger;

	@Mock
	protected WebI18nMessageSource msgSource;
	
	@Mock
	private AddressDAOIF addressDAO;

	@Spy
	ReloadableResourceBundleMessageSource viewResolverMessageSource = new ReloadableResourceBundleMessageSource();
	
	@Mock
	private OERequestHandler oeRequestHandler;
	
	@Spy
	OESignupDTO oeSignUpDTO = new OESignupDTO();
	
	@Mock
	private OeBoHelper oeBoHelper;
	
	@Mock
	private BaseBO baseBO;
	
	@Mock
	private CommonUtil commonUtil;
	
	@Mock
	private TogglzUtil togglzUtil;

	@Mock 
	OfferService offerService;
	
	@Spy
	ReloadableResourceBundleMessageSource appConstMessageSource = new ReloadableResourceBundleMessageSource();
	
	String apiCallExecuted = API_CHECK_CREDIT+"|"+API_LEGACY_SUBMIT_UCC_DATA+"|"+API_RECHECK_CREDIT+"|"+API_LEGACY_PERFORM_CREDIT_CHECK+"|"+API_AVAILABLE_DATES+"|"+API_LEGACY_GET_ESID_AND_CALENDAR_DATES;
	
	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(logger.isDebugEnabled()).thenReturn(true);
		appConstMessageSource.setUseCodeAsDefaultMessage(true);
		appConstMessageSource.setDefaultEncoding("UTF-8");
		appConstMessageSource.setFallbackToSystemLocale(Boolean.TRUE);
		appConstMessageSource.setUseCodeAsDefaultMessage(true);
		appConstMessageSource.setBasenames("/WEB-INF/classes/properties/appConstants");
    
	}

	@BeforeMethod
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
		when(logger.isDebugEnabled()).thenReturn(true);
		appConstMessageSource.setUseCodeAsDefaultMessage(true);
		appConstMessageSource.setDefaultEncoding("UTF-8");
		appConstMessageSource.setFallbackToSystemLocale(Boolean.TRUE);
		appConstMessageSource.setUseCodeAsDefaultMessage(true);
		appConstMessageSource.setBasenames("/WEB-INF/classes/properties/appConstants");
    
	}

	// FOR JUNIT
	
	 /* @Before
	  public void init()
	  { 
		  MockitoAnnotations.initMocks(this);
		  when(logger.isDebugEnabled()).thenReturn(true);
		  
	  }*/
	 
/*
	@Test
	public void testSubmitanswerskbaWithoutQuestionAnswerList() {
		// without que answer from input
		KbaAnswerRequest request = new KbaAnswerRequest();
		KbaAnswerResponse response = new KbaAnswerResponse();
		KbaSubmitAnswerRequest kbaAnswerRequest = new KbaSubmitAnswerRequest();
		KbaSubmitAnswerResponse kbaSubmitAnswerResponse = new KbaSubmitAnswerResponse();
		kbaSubmitAnswerResponse.setStrErrCode("03");
		kbaSubmitAnswerResponse.setReturnCode("09");
		request.setTransactionKey("12345678");
		request.setTrackingId("");
		List<KbaErrorDTO> kbaErrorDTOList1 = new ArrayList<KbaErrorDTO>();
		KbaErrorDTO errorDTO = new KbaErrorDTO();
		errorDTO.setTransactionKey("5756756");
		errorDTO.setErrorCode("66");
		errorDTO.setErrorMsg("error");
		errorDTO.setErrorDescription("error");

		kbaAnswerRequest.setTransactionKey(request.getTransactionKey());
		kbaErrorDTOList1.add(errorDTO);
		KbaErrorDTO[] km = new KbaErrorDTO[kbaErrorDTOList1.size()];
		int i = 0;
		for (KbaErrorDTO k : kbaErrorDTOList1) {
			km[i] = k;
			i++;
		}
		kbaSubmitAnswerResponse.setErrorList(km);

		try {
			when(oeService.submitKBAAnswer(Matchers.any(KbaSubmitAnswerRequest.class)))
					.thenReturn(kbaSubmitAnswerResponse);
			response = oebo.submitKBAAnswers(request);
			Assert.assertEquals(response.getStatusCode(), Constants.STATUS_CODE_CONTINUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/*
	@Test
	public void testSubmitanswerskbaWithQuestionAnswerList() {
		// with que answer from input with return code
		KbaAnswerRequest request = new KbaAnswerRequest();
		KbaAnswerResponse response = new KbaAnswerResponse();
		KbaSubmitAnswerRequest kbaAnswerRequest = new KbaSubmitAnswerRequest();
		kbaAnswerRequest.setTransactionKey(request.getTransactionKey());
		KbaSubmitAnswerResponse kbaSubmitAnswerResponse = new KbaSubmitAnswerResponse();
		kbaSubmitAnswerResponse.setReturnCode("02");
		request.setTransactionKey("12345678");
		request.setTrackingId("11");

		List<KBAQuestionAnswerVO> kBAQuestionAnswerVOList = new ArrayList<KBAQuestionAnswerVO>();
		KBAQuestionAnswerVO dto = new KBAQuestionAnswerVO();
		dto.setQuestionId(01);
		dto.setQuizId(02);
		dto.setAnswerId(12);
		kBAQuestionAnswerVOList.add(dto);
		request.setQuestionList(kBAQuestionAnswerVOList);
		try {
			when(oeService.submitKBAAnswer(Matchers.any(KbaSubmitAnswerRequest.class)))
					.thenReturn(kbaSubmitAnswerResponse);
			when(serviceLocationDAO.updateServiceLocation(Matchers.any(UpdateServiceLocationRequest.class)))
					.thenReturn("errorcode");
			when(kbaDao.updateKbaDetails(Matchers.any(KBASubmitResultsDTO.class))).thenReturn(true);
			response = oebo.submitKBAAnswers(request);
			Assert.assertEquals(response.getStatusCode(), Constants.STATUS_CODE_CONTINUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
/*	@Test
	public void testSubmitanswerskbaWithQuestionAnswerListWithoutkbaSubmitAnswerResponse() {
		// with que answer from input without return code and with no
		// kbaSubmitAnswerResponse
		KbaAnswerRequest request = new KbaAnswerRequest();
		KbaAnswerResponse response = new KbaAnswerResponse();
		KbaSubmitAnswerRequest kbaAnswerRequest = new KbaSubmitAnswerRequest();
		kbaAnswerRequest.setTransactionKey(request.getTransactionKey());
		KbaSubmitAnswerResponse kbaSubmitAnswerResponse = new KbaSubmitAnswerResponse();
		request.setTransactionKey("12345678");
		request.setTrackingId("11");

		List<KBAQuestionAnswerVO> kBAQuestionAnswerVOList = new ArrayList<KBAQuestionAnswerVO>();
		KBAQuestionAnswerVO dto = new KBAQuestionAnswerVO();
		dto.setQuestionId(01);
		dto.setQuizId(02);
		dto.setAnswerId(12);
		kBAQuestionAnswerVOList.add(dto);
		request.setQuestionList(kBAQuestionAnswerVOList);
		try {
			when(oeService.submitKBAAnswer(Matchers.any(KbaSubmitAnswerRequest.class)))
					.thenReturn(kbaSubmitAnswerResponse);
			when(serviceLocationDAO.updateServiceLocation(Matchers.any(UpdateServiceLocationRequest.class)))
					.thenReturn("errorcode");
			when(kbaDao.updateKbaDetails(Matchers.any(KBASubmitResultsDTO.class))).thenReturn(true);
			response = oebo.submitKBAAnswers(request);
			Assert.assertEquals(response.getStatusCode(), Constants.STATUS_CODE_CONTINUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
/*	@Test
	public void testSubmitanswerskbaWithQuestionAnswerListkbaSubmitAnswerResponseSsnVerifyDate() {
		// kbaSubmitAnswerResponse with SsnVerifyDate
		KbaAnswerRequest request = new KbaAnswerRequest();
		KbaAnswerResponse response = new KbaAnswerResponse();
		KbaSubmitAnswerRequest kbaAnswerRequest = new KbaSubmitAnswerRequest();
		kbaAnswerRequest.setTransactionKey(request.getTransactionKey());
		KbaSubmitAnswerResponse kbaSubmitAnswerResponse = new KbaSubmitAnswerResponse();
		kbaSubmitAnswerResponse.setSsnVerifyDate("2013-12-20");
		KbaResponseOutputDTO kbaResponseOutputDTO = new KbaResponseOutputDTO();
		kbaResponseOutputDTO.setDecision("Y");
		kbaResponseOutputDTO.setTransactionKey("12345678");
		kbaResponseOutputDTO.setFraudlevel("no");
		kbaResponseOutputDTO.setIdentityScore("03");
		kbaResponseOutputDTO.setInteractiveQscore("05");
		kbaResponseOutputDTO.setOverallScore("10");
		kbaSubmitAnswerResponse.setKbaSubmitAnswerResponseOutput(kbaResponseOutputDTO);
		request.setTransactionKey("12345678");
		request.setTrackingId("");

		List<KBAQuestionAnswerVO> kBAQuestionAnswerVOList = new ArrayList<KBAQuestionAnswerVO>();
		KBAQuestionAnswerVO dto = new KBAQuestionAnswerVO();
		dto.setQuestionId(01);
		dto.setQuizId(02);
		dto.setAnswerId(12);
		kBAQuestionAnswerVOList.add(dto);
		request.setQuestionList(kBAQuestionAnswerVOList);
		try {
			when(oeService.submitKBAAnswer(Matchers.any(KbaSubmitAnswerRequest.class)))
					.thenReturn(kbaSubmitAnswerResponse);
			when(serviceLocationDAO.updateServiceLocation(Matchers.any(UpdateServiceLocationRequest.class)))
					.thenReturn("errorcode");
			when(kbaDao.updateKbaDetails(Matchers.any(KBASubmitResultsDTO.class))).thenReturn(true);
			response = oebo.submitKBAAnswers(request);
			Assert.assertEquals(response.getSsnVerifyDate(), "12/20/2013");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
/*
	@Test
	public void testSubmitanswerskbaWithKbareasonAndVerificationAssessmentInKbaResponse() {
		// kba reasonlist and verification assessment list
		KbaAnswerRequest request = new KbaAnswerRequest();
		KbaAnswerResponse response = new KbaAnswerResponse();
		KbaSubmitAnswerRequest kbaAnswerRequest = new KbaSubmitAnswerRequest();
		kbaAnswerRequest.setTransactionKey(request.getTransactionKey());
		KbaSubmitAnswerResponse kbaSubmitAnswerResponse = new KbaSubmitAnswerResponse();
		kbaSubmitAnswerResponse.setSsnVerifyDate("2013-12-20");
		KbaResponseOutputDTO kbaResponseOutputDTO = new KbaResponseOutputDTO();
		kbaResponseOutputDTO.setDecision("Y");
		kbaResponseOutputDTO.setTransactionKey("12345678");
		kbaResponseOutputDTO.setFraudlevel("no");
		kbaResponseOutputDTO.setIdentityScore("03");
		kbaResponseOutputDTO.setInteractiveQscore("05");
		kbaResponseOutputDTO.setOverallScore("10");

		List<KbaResponseReasonDTO> reasonDtoList = new ArrayList<>();
		KbaResponseReasonDTO kbaReasonDTO = new KbaResponseReasonDTO();
		kbaReasonDTO.setReasonCode("11");
		kbaReasonDTO.setReasonDesc("22");
		reasonDtoList.add(kbaReasonDTO);

		KbaResponseReasonDTO[] reasonDto = new KbaResponseReasonDTO[reasonDtoList.size()];
		int j = 0;
		for (KbaResponseReasonDTO r : reasonDtoList) {
			reasonDto[j] = r;
			j++;
		}
		kbaResponseOutputDTO.setKbaReasonList(reasonDto);

		// for verification assessment
		List<KbaResponseAssessmentDTO> verificationDtoList = new ArrayList<>();
		KbaResponseAssessmentDTO kbaVerificationDTO = new KbaResponseAssessmentDTO();
		kbaVerificationDTO.setAssessmentName("name");
		kbaVerificationDTO.setAssessmentValue("88");
		verificationDtoList.add(kbaVerificationDTO);

		KbaResponseAssessmentDTO[] verificationDto = new KbaResponseAssessmentDTO[verificationDtoList.size()];
		int m = 0;
		for (KbaResponseAssessmentDTO r : verificationDtoList) {
			verificationDto[m] = r;
			m++;
		}
		kbaResponseOutputDTO.setVerificationAssessmentList(verificationDto);
		kbaSubmitAnswerResponse.setKbaSubmitAnswerResponseOutput(kbaResponseOutputDTO);
		request.setTransactionKey("12345678");
		request.setTrackingId("");

		List<KBAQuestionAnswerVO> kBAQuestionAnswerVOList = new ArrayList<KBAQuestionAnswerVO>();
		KBAQuestionAnswerVO dto = new KBAQuestionAnswerVO();
		dto.setQuestionId(01);
		dto.setQuizId(02);
		dto.setAnswerId(12);
		kBAQuestionAnswerVOList.add(dto);
		request.setQuestionList(kBAQuestionAnswerVOList);
		try {
			when(oeService.submitKBAAnswer(Matchers.any(KbaSubmitAnswerRequest.class)))
					.thenReturn(kbaSubmitAnswerResponse);
			when(serviceLocationDAO.updateServiceLocation(Matchers.any(UpdateServiceLocationRequest.class)))
					.thenReturn("errorcode");
			when(kbaDao.updateKbaDetails(Matchers.any(KBASubmitResultsDTO.class))).thenReturn(true);
			response = oebo.submitKBAAnswers(request);
			Assert.assertEquals(response.getSsnVerifyDate(), "12/20/2013");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
/*	@Test
	public void testSubmitanswerskbaWithQuestionAnswerListkbaSubmitAnswerResponseDlVerifyDate() {
		// kbaSubmitAnswerResponse with DlVerifyDate
		KbaAnswerRequest request = new KbaAnswerRequest();
		KbaAnswerResponse response = new KbaAnswerResponse();
		KbaSubmitAnswerRequest kbaAnswerRequest = new KbaSubmitAnswerRequest();
		kbaAnswerRequest.setTransactionKey(request.getTransactionKey());
		KbaSubmitAnswerResponse kbaSubmitAnswerResponse = new KbaSubmitAnswerResponse();
		kbaSubmitAnswerResponse.setDlVerifyDate("2013-12-20");
		request.setTransactionKey("12345678");
		request.setTrackingId("");

		List<KBAQuestionAnswerVO> kBAQuestionAnswerVOList = new ArrayList<KBAQuestionAnswerVO>();
		KBAQuestionAnswerVO dto = new KBAQuestionAnswerVO();
		dto.setQuestionId(01);
		dto.setQuizId(02);
		dto.setAnswerId(12);
		kBAQuestionAnswerVOList.add(dto);
		request.setQuestionList(kBAQuestionAnswerVOList);
		try {
			when(oeService.submitKBAAnswer(Matchers.any(KbaSubmitAnswerRequest.class)))
					.thenReturn(kbaSubmitAnswerResponse);
			when(serviceLocationDAO.updateServiceLocation(Matchers.any(UpdateServiceLocationRequest.class)))
					.thenReturn("errorcode");
			when(kbaDao.updateKbaDetails(Matchers.any(KBASubmitResultsDTO.class))).thenReturn(true);
			response = oebo.submitKBAAnswers(request);
			Assert.assertEquals(response.getDrivingLicenceVerifyDate(), "2013-12-20");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	@Test
	public void testSubmitanswerskbaWithException() {
		// Exception
		KbaAnswerRequest request = new KbaAnswerRequest();
		KbaAnswerResponse response = new KbaAnswerResponse();
		KbaSubmitAnswerRequest kbaAnswerRequest = new KbaSubmitAnswerRequest();
		KbaSubmitAnswerResponse kbaSubmitAnswerResponse = new KbaSubmitAnswerResponse();
		kbaSubmitAnswerResponse.setStrErrCode("03");
		kbaSubmitAnswerResponse.setReturnCode("09");
		request.setTransactionKey("12345678");
		request.setTrackingId("");
		kbaAnswerRequest.setTransactionKey(request.getTransactionKey());

		try {
			when(oeService.submitKBAAnswer(Matchers.any(KbaSubmitAnswerRequest.class))).thenThrow(Exception.class);
			response = oebo.submitKBAAnswers(request);
			Assert.assertEquals(response.getStatusCode(), Constants.STATUS_CODE_STOP);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetKBAQuestionsWithTokenDLWithQuestionList(){
		GetKBAQuestionsRequest request = getRequestForGetKBAQuestionsRequest();
		request.setTokenizedTDL("543546");
		request.setDrivingLicenseState("TX");
		//request.setTrackingId("34444");
		KbaQuestionResponse kbaQuestionResponse = new KbaQuestionResponse();
		GetKBAQuestionsResponse getKBAQuestionsResponse=new GetKBAQuestionsResponse();
		List<KbaQuestionDTO> kbaQuesDTO = new ArrayList<>();
		KbaQuestionDTO dto = new KbaQuestionDTO();
		dto.setQuizId(1);
		dto.setQuestionId(2);
		dto.setQuestionText("first question");
		kbaQuesDTO.add(dto);
		
		List<KbaAnswerDTO> answerDTO = new ArrayList<>();
		KbaAnswerDTO kbaAnsDTO = new KbaAnswerDTO();
		kbaAnsDTO.setAnswerId(1);
		kbaAnsDTO.setContent("abc");
		kbaAnsDTO.setCorrectAnswer(true);
		answerDTO.add(kbaAnsDTO);
		
		KbaAnswerDTO[] kbaDTO = new KbaAnswerDTO[answerDTO.size()];
		int k=0;
		for(KbaAnswerDTO a:answerDTO){
			kbaDTO[k] = a;
		}
		dto.setAnswerList(kbaDTO);
		
		KbaQuestionDTO[] reasonDto = new KbaQuestionDTO[kbaQuesDTO.size()];
		int j = 0;
		for (KbaQuestionDTO r : kbaQuesDTO) {
			reasonDto[j] = r;
			j++;
		}
		kbaQuestionResponse.setQuestionList(reasonDto);
		kbaQuestionResponse.setTransactionKey("445667");
		try{
			when(oeService.getKBAQuestionList(Matchers.any(KbaQuestionRequest.class))).thenReturn(kbaQuestionResponse);
			when(kbaDao.addKbaDetails(Matchers.any(KbaQuestionResponse.class))).thenReturn(true);
			when(serviceLocationDAO.updateServiceLocation(Matchers.any(UpdateServiceLocationRequest.class))).thenReturn("true");
			getKBAQuestionsResponse = oebo.getKBAQuestions(request);
			Assert.assertEquals(getKBAQuestionsResponse.getStatusCode(), Constants.STATUS_CODE_CONTINUE);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetKBAQuestionsWithOutTokenDLAndQuestionList(){
		GetKBAQuestionsRequest request = getRequestForGetKBAQuestionsRequest();
		
		KbaQuestionResponse kbaQuestionResponse = new KbaQuestionResponse();
		GetKBAQuestionsResponse getKBAQuestionsResponse=new GetKBAQuestionsResponse();
		try{
			when(oeService.getKBAQuestionList(Matchers.any(KbaQuestionRequest.class))).thenReturn(kbaQuestionResponse);
			when(kbaDao.addKbaDetails(Matchers.any(KbaQuestionResponse.class))).thenReturn(true);
			getKBAQuestionsResponse = oebo.getKBAQuestions(request);
			Assert.assertEquals(getKBAQuestionsResponse.getMessageCode(), Constants.POSIDHOLD);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
		public void testGetKBAQuestionsWithException(){
			GetKBAQuestionsRequest request = getRequestForGetKBAQuestionsRequest();
			GetKBAQuestionsResponse getKBAQuestionsResponse=new GetKBAQuestionsResponse();
			try{
				when(oeService.getKBAQuestionList(Matchers.any(KbaQuestionRequest.class))).thenThrow(Exception.class);
				getKBAQuestionsResponse = oebo.getKBAQuestions(request);
				Assert.assertEquals(getKBAQuestionsResponse.getStatusCode(), Constants.STATUS_CODE_CONTINUE);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
	/*	@Test
		public void testGetKBAQuestionsWithOutTokenDLWithQuestionListException(){
			GetKBAQuestionsRequest request = getRequestForGetKBAQuestionsRequest();
			//request.setTrackingId("34444");
			KbaQuestionResponse kbaQuestionResponse = new KbaQuestionResponse();
			GetKBAQuestionsResponse getKBAQuestionsResponse=new GetKBAQuestionsResponse();
			List<KbaQuestionDTO> kbaQuesDTO = new ArrayList<>();
			KbaQuestionDTO dto = new KbaQuestionDTO();
			dto.setQuizId(1);
			dto.setQuestionId(2);
			dto.setQuestionText("first question");
			kbaQuesDTO.add(dto);
			KbaQuestionDTO[] reasonDto = new KbaQuestionDTO[kbaQuesDTO.size()];
			int j = 0;
			for (KbaQuestionDTO r : kbaQuesDTO) {
				reasonDto[j] = r;
				j++;
			}
			kbaQuestionResponse.setQuestionList(reasonDto);
			kbaQuestionResponse.setTransactionKey("445667");
			try{
				when(oeService.getKBAQuestionList(Matchers.any(KbaQuestionRequest.class))).thenReturn(kbaQuestionResponse);
				when(kbaDao.addKbaDetails(Matchers.any(KbaQuestionResponse.class))).thenReturn(true);
				when(serviceLocationDAO.updateServiceLocation(Matchers.any(UpdateServiceLocationRequest.class))).thenThrow(Exception.class);
				getKBAQuestionsResponse = oebo.getKBAQuestions(request);
				Assert.assertEquals(getKBAQuestionsResponse.getStatusCode(), Constants.STATUS_CODE_STOP);
			}catch(Exception e){
				e.printStackTrace();
			}
		} */
		
		@Test
		public void testGetKBAQuestionsWithTokenDLWithOutAnswerList(){
			GetKBAQuestionsRequest request = getRequestForGetKBAQuestionsRequest();
			request.setTokenizedTDL("543546");
			request.setDrivingLicenseState("TX");
			//request.setTrackingId("34444");
			KbaQuestionResponse kbaQuestionResponse = new KbaQuestionResponse();
			GetKBAQuestionsResponse getKBAQuestionsResponse=new GetKBAQuestionsResponse();
			List<KbaQuestionDTO> kbaQuesDTO = new ArrayList<>();
			KbaQuestionDTO dto = new KbaQuestionDTO();
			dto.setQuizId(1);
			dto.setQuestionId(2);
			dto.setQuestionText("first question");
			kbaQuesDTO.add(dto);
			
			KbaQuestionDTO[] reasonDto = new KbaQuestionDTO[kbaQuesDTO.size()];
			int j = 0;
			for (KbaQuestionDTO r : kbaQuesDTO) {
				reasonDto[j] = r;
				j++;
			}
			kbaQuestionResponse.setQuestionList(reasonDto);
			kbaQuestionResponse.setTransactionKey("445667");
			try{
				when(oeService.getKBAQuestionList(Matchers.any(KbaQuestionRequest.class))).thenReturn(kbaQuestionResponse);
				when(kbaDao.addKbaDetails(Matchers.any(KbaQuestionResponse.class))).thenReturn(true);
				when(serviceLocationDAO.updateServiceLocation(Matchers.any(UpdateServiceLocationRequest.class))).thenReturn("true");
				getKBAQuestionsResponse = oebo.getKBAQuestions(request);
				Assert.assertEquals(getKBAQuestionsResponse.getStatusCode(), Constants.STATUS_CODE_CONTINUE);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	private GetKBAQuestionsRequest getRequestForGetKBAQuestionsRequest(){
		GetKBAQuestionsRequest request = new GetKBAQuestionsRequest();
		request.setCompanyCode("01");
		request.setBrandId("01");
		request.setLanguageCode(Constants.EN_US);
		request.setFirstName("abc");
		request.setLastName("xyz");
		request.setMiddleName("A");
		request.setDob("23092019");
		request.setTokenizedSSN("234234");
		//request.setTokenTDL("543546");
		//request.setDrivingLicenseState("TX");
		request.setPhoneNum("4534656566");
		request.setEmail("abc@gmail.com");
		request.setIpAddress("172.19.32.22");
		request.setEsid("3435565656");
		request.setServStreetNum("345");
		request.setServStreetName("street");
		request.setServStreetAptNum("09");
		request.setServCity("H");
		request.setServState("T");
		request.setServZipCode("77063");
		request.setPosidUniqueKey("43");
		
		return request;
	}

	@Test
	public void testPositiveGetESIDDetails() throws SQLException, Exception{
		EsidRequest request = createEsidRequest();
		EsidResponse esidResponse = createEsidResponse();
		when(addressDAO.getESIDDetails(Matchers.any(EsidRequest.class))).thenReturn(esidResponse);
		esidResponse = oebo.getESIDDetails(request);
		Assert.assertEquals(esidResponse.getEsidList().size(), 1);
		
	}
	@Test
	public void testNegativeGetESIDDetails() throws SQLException, Exception{
		EsidRequest request = createEsidRequest();
		EsidResponse esidResponse= new EsidResponse();
		List<ESIDData> esidList = new ArrayList<>();
		esidResponse.setEsidList(esidList);
		when(addressDAO.getESIDDetails(Matchers.any(EsidRequest.class))).thenReturn(esidResponse);
		esidResponse = oebo.getESIDDetails(request);
		Assert.assertEquals(esidResponse.getEsidList().size(), 0);
	}
	
	private EsidRequest createEsidRequest(){
		EsidRequest request = new EsidRequest();
		request.setAffiliateId("12345");
		request.setBrandId("RE");
		request.setChannelType("WEB");
		request.setCompanyCode("0121");
		request.setLanguageCode("EN");
		request.setServCity("MIDLAND");
		request.setServStreet("40010 ANGELINA DR");
		request.setServZipCode("79707");
		return request;
	}
	
	private EsidResponse createEsidResponse(){
		List<ESIDData> esidList=null;
		ESIDData esid = null;
		esidList = new ArrayList<>();
		esid = new ESIDData();
		esid.setEsidNumber("10443720003000440");
		esid.setPremiseType("Resdential");
		esid.setEsidStatus("Active");
		esid.setEsidClass("1");
		esid.setEsidDeposit("410");
		esid.setEsidTDSP("44372");
		esidList.add(esid);
		EsidResponse esidResponse= new EsidResponse();
		esidResponse.setEsidList(esidList);
		return esidResponse;
	}
	
	/*@Test
	public void testCheckFraudulentActivityForDuplicateEnrollment() throws OEException{
		ENROLLMENT_FRAUD_ENUM enrollmentFraudEnum=null;
		oeSignUpDTO.setReqStatusCd(S_VALUE);
	    when(togglzUtil.getFeatureStatusFromTogglzByChannel(Matchers.any(String.class),Matchers.any(String.class))).thenReturn(true);
	    enrollmentFraudEnum=oebo.checkFraudulentActivity(oeSignUpDTO, "",apiCallExecuted);
		Assert.assertEquals(enrollmentFraudEnum.getFraudErrorCode(), MESSAGE_CODE_DUPLICATE_SUBMISSION);
	}
	*/
	@Test
	public void testCheckFraudulentActivityWithPOSIDHOLD() throws OEException{
		ENROLLMENT_FRAUD_ENUM enrollmentFraudEnum=null;
		oeSignUpDTO.setReqStatusCd(I_VALUE);
		oeSignUpDTO.setErrorCdList(POSIDHOLD);
	    when(togglzUtil.getFeatureStatusFromTogglzByChannel(Matchers.any(String.class),Matchers.any(String.class))).thenReturn(false);
	    ServiceLocationResponse serviceLocationResponse = new ServiceLocationResponse();
	    serviceLocationResponse.setPersonResponse(new PersonResponse());
	    enrollmentFraudEnum=oebo.checkFraudulentActivity(oeSignUpDTO, false, false,apiCallExecuted, serviceLocationResponse);
		Assert.assertEquals(enrollmentFraudEnum.getFraudErrorCode(), ERROR_CD_ENROLLMENT_NOT_ALLOWED);
	}
	
	@Test
	public void testCheckFraudulentActivityWithPOSIDHOLDComparePowerToggleOFF() throws OEException{
		ENROLLMENT_FRAUD_ENUM enrollmentFraudEnum=null;
		oeSignUpDTO.setReqStatusCd(I_VALUE);
		oeSignUpDTO.setErrorCdList(POSIDHOLD);
	    when(togglzUtil.getFeatureStatusFromTogglzByChannel(Matchers.any(String.class),Matchers.any(String.class))).thenReturn(false);
	    ServiceLocationResponse serviceLocationResponse = new ServiceLocationResponse();
	    serviceLocationResponse.setPersonResponse(new PersonResponse());
	    enrollmentFraudEnum=oebo.checkFraudulentActivity(oeSignUpDTO, false, false,apiCallExecuted, serviceLocationResponse);
		Assert.assertEquals(enrollmentFraudEnum.getFraudErrorCode(), ERROR_CD_ENROLLMENT_NOT_ALLOWED);
	}
	
	@Test
	public void testCheckFraudulentActivityWithPOSIDHOLDComparePowerToggleON() throws OEException{
		ENROLLMENT_FRAUD_ENUM enrollmentFraudEnum=null;
		oeSignUpDTO.setReqStatusCd(I_VALUE);
		oeSignUpDTO.setErrorCdList(POSIDHOLD);
	    when(togglzUtil.getFeatureStatusFromTogglzByChannel(Matchers.any(String.class),Matchers.any(String.class))).thenReturn(false);
	    ServiceLocationResponse serviceLocationResponse = new ServiceLocationResponse();
	    serviceLocationResponse.setPersonResponse(new PersonResponse());
	    serviceLocationResponse.getPersonResponse().setCredScoreNum("0550");;
	    serviceLocationResponse.getPersonResponse().setCredSourceNum("1");;
	    serviceLocationResponse.getPersonResponse().setCredLevelNum("1");;
	    enrollmentFraudEnum=oebo.checkFraudulentActivity(oeSignUpDTO, true, false,apiCallExecuted, serviceLocationResponse);
	    Assert.assertNull(enrollmentFraudEnum);
	}
	
	/*@Test
	public void testCheckFraudulentActivityWithBPRestrict() throws OEException{
		ENROLLMENT_FRAUD_ENUM enrollmentFraudEnum=null;
		oeSignUpDTO.setReqStatusCd(I_VALUE);
		oeSignUpDTO.setErrorCdList(BP_RESTRICT);
	    when(togglzUtil.getFeatureStatusFromTogglzByChannel(Matchers.any(String.class),Matchers.any(String.class))).thenReturn(true);
	    enrollmentFraudEnum=oebo.checkFraudulentActivity(oeSignUpDTO, "",apiCallExecuted);
		Assert.assertEquals(enrollmentFraudEnum.getFraudSystemNotes(), "RESTRICTED_BP_FRAUD");
	}*/
	
	@Test
	public void testCheckFraudulentActivityWithSWHOLD() throws OEException{
		ENROLLMENT_FRAUD_ENUM enrollmentFraudEnum=null;
		oeSignUpDTO.setReqStatusCd(I_VALUE);
		oeSignUpDTO.setErrorCdList(SWITCHHOLD);
		oeSignUpDTO.setServiceReqTypeCd(SWI);
	    when(togglzUtil.getFeatureStatusFromTogglzByChannel(Matchers.any(String.class),Matchers.any(String.class))).thenReturn(true);
	    ServiceLocationResponse serviceLocationResponse = new ServiceLocationResponse();
	    serviceLocationResponse.setPersonResponse(new PersonResponse());
	    enrollmentFraudEnum=oebo.checkFraudulentActivity(oeSignUpDTO, true, true,apiCallExecuted, serviceLocationResponse);
		Assert.assertEquals(enrollmentFraudEnum.getFraudErrorCode(), ERROR_CD_ENROLLMENT_NOT_ALLOWED);
	}
	
	/*
	@Test
	public void testCheckFraudulentActivityWithCreditFreeze() throws OEException{
		ENROLLMENT_FRAUD_ENUM enrollmentFraudEnum=null;
		oeSignUpDTO.setReqStatusCd(I_VALUE);
		oeSignUpDTO.setErrorCdList(CREDFREEZE);
		when(togglzUtil.getFeatureStatusFromTogglzByChannel(Matchers.any(String.class),Matchers.any(String.class))).thenReturn(true);
	    enrollmentFraudEnum=oebo.checkFraudulentActivity(oeSignUpDTO, "",apiCallExecuted);
		Assert.assertEquals(enrollmentFraudEnum.getFraudSystemNotes(), "CREDIT_FREEZE_FRAUD");
	}
	*/
	@Test
	public void testIsMandatoryCallExecutedForCreditApiCheck(){
		ENROLLMENT_FRAUD_ENUM enrollmentFraudEnum=null;
		String callExecutedFromDB = "identity|available-dates";
		enrollmentFraudEnum = oebo.isMandatoryCallExecuted(callExecutedFromDB);
		Assert.assertEquals(enrollmentFraudEnum.getFraudSystemNotes(), "CREDIT_API_SKIPPED");
	}
	@Test
	public void testIsMandatoryCallExecutedForDateApiCheckTogglzON(){
		ENROLLMENT_FRAUD_ENUM enrollmentFraudEnum=null;
		String callExecutedFromDB = "identity|check-credit|submitUCCData";
	    when(togglzUtil.getFeatureStatusFromTogglz(Matchers.any(String.class))).thenReturn(true);
		enrollmentFraudEnum = oebo.isMandatoryCallExecuted(callExecutedFromDB);
		Assert.assertEquals(enrollmentFraudEnum.getFraudSystemNotes(), "DATES_API_SKIPPED");
	}
	
	@Test
	public void testIsMandatoryCallExecutedForDateApiCheckTogglzOff(){
		ENROLLMENT_FRAUD_ENUM enrollmentFraudEnum=null;
		String callExecutedFromDB = "identity|check-credit|submitUCCData";
	    when(togglzUtil.getFeatureStatusFromTogglz(Matchers.any(String.class))).thenReturn(false);
		enrollmentFraudEnum = oebo.isMandatoryCallExecuted(callExecutedFromDB);
		Assert.assertNull(enrollmentFraudEnum);
	}
	
	@Test
	public void testIsMandatoryCallExecutedNoSkip(){
		ENROLLMENT_FRAUD_ENUM enrollmentFraudEnum=null;
		String callExecutedFromDB = "identity|check-credit|submitUCCData|available-dates";
		enrollmentFraudEnum = oebo.isMandatoryCallExecuted(callExecutedFromDB);
		Assert.assertEquals(enrollmentFraudEnum, null);
	}
	
	@Test
	public void testGetOfferDetailsNoOfferFromCCS() throws ServiceException{
		
		SalesOfferDetailsRequest salesOfferDetailsRequest = new SalesOfferDetailsRequest();
		salesOfferDetailsRequest.setAffiliateId("012345");
		salesOfferDetailsRequest.setBrandId("RE");
		salesOfferDetailsRequest.setCampaignCode("SFFSFDS");
		salesOfferDetailsRequest.setChannelType("AFF");
		salesOfferDetailsRequest.setCompanyCode("0121");
		salesOfferDetailsRequest.setLanguageCode("E");
		salesOfferDetailsRequest.setOfferCode("1939294");
		salesOfferDetailsRequest.setPromoCode("3498093");
		PromoOfferResponse promoOfferResponse = new PromoOfferResponse();
		PromoOfferOutData[] promoOfferOutDataArr =  new PromoOfferOutData[1];
		PromoOfferOutData promoOffer =  new PromoOfferOutData();
		PromoOfferTDSPCharge[] promoTdspCharges = new PromoOfferTDSPCharge[1];
		PromoOfferTDSPCharge promoTdspCharge = new PromoOfferTDSPCharge();
		promoTdspCharge.setStrBundlingGroup("AQ");
		promoTdspCharges[0] = promoTdspCharge;
		promoOffer.setOfferTDSPCharges(promoTdspCharges);
		promoOffer.setStrCampaignCode("SFFSFDS");
		promoOffer.setStrCampaignDescription("iu34u9u4");
		promoOffer.setStrContractTerm("12");
		//promoOffer.setStrEflUrl("");
		promoOffer.setStrIncentiveCode("Incentive1212");
		promoOffer.setStrEFLSmartCode("SC1313");
		promoOffer.setStrOfferCode("1939294");
		promoOffer.setStrPlanName("Pay As You Go");
		promoOffer.setStrPlanType("PREPAY");
		promoOffer.setStrProductCode("1000244");
		promoOffer.setStrCancelFee(new BigDecimal("120"));
		promoOffer.setStrCustClass("");
		promoOffer.setStrCustomerSegment("");
		promoOffer.setStrDwellingType("");
		//promoOffer.setStrEFLDocID("");
		//promoOffer.setStrEFLSmartCode("");
		promoOffer.setStrIncentiveCode("");
		promoOffer.setStrMarketSegment("");
		promoOffer.setStrCustomerSegment("");
		promoOffer.setStrYRAACSmartCode("");
		promoOffer.setStrYRAACDocID("");
		promoOffer.setStrWebOfferRank("1");
		promoOffer.setStrValidToDate("09092021");
		promoOffer.setStrValidFromDate("09092020");
		promoOffer.setStrTOSSmartCode("");
		promoOffer.setStrTOSDocID("");
		promoOffer.setStrPromoCode("");
		promoOffer.setStrProductPriceCode("");
		promoOffer.setStrProductCode("");
		promoOffer.setStrIncentiveDescription("");
		promoOffer.setStrIncentiveValue(new BigDecimal(8.1));
		promoOffer.setStrInvoiceOptions("");
		promoOffer.setStrOfferCellTrackCode("");
		promoOffer.setStrOfferCellTrackCode("");
		promoOffer.setStrOfferCode("10034793");
		promoOffer.setStrOfferCodeTitle("Pay As You Go");
		promoOffer.setStrOfferTeaser("");
		promoOffer.setStrPayOptions("S");
		promoOffer.setStrPenaltyDesciption("");
		promoOffer.setStrPenaltyValue(new BigDecimal(0.0));
		promoOffer.setStrMarketSegment("");
		com.multibrand.domain.PromoOfferOutDataAvgPriceMapEntry[] promoOfferOutDataAvgPriceMapEntry = new com.multibrand.domain.PromoOfferOutDataAvgPriceMapEntry[1];
		com.multibrand.domain.PromoOfferOutDataAvgPriceMapEntry promoOfferOutDataAvgPrice = new com.multibrand.domain.PromoOfferOutDataAvgPriceMapEntry();
		BigDecimal avgPrice = new BigDecimal("13.0");
		PromoOfferAvgPriceData promoOfferAvgPriceData = new PromoOfferAvgPriceData();
		promoOfferAvgPriceData.setAvgPrice(avgPrice);
		promoOfferAvgPriceData.setPriceType("P");
		promoOfferAvgPriceData.setDateEnd("09092021");
		promoOfferAvgPriceData.setDateStart("09092020");
		promoOfferAvgPriceData.setString1("Y");
		promoOfferOutDataAvgPrice.setKey(Constants.PROD_TYPE);
		promoOfferOutDataAvgPrice.setValue(promoOfferAvgPriceData);
		promoOfferOutDataAvgPriceMapEntry[0] = promoOfferOutDataAvgPrice;
		
		promoOffer.setAvgPriceMap(promoOfferOutDataAvgPriceMapEntry);
		promoOfferOutDataArr[0] = promoOffer;
		promoOfferResponse.setOfferOuts(promoOfferOutDataArr);
		when(appConstMessageSource.getMessage("0121" + ".web.url" , null,null)).thenReturn("https://www.reliant.com");
		when(this.offerService.getOfferPricingFromCCS(Matchers.any(OfferPricingRequest.class))).thenReturn(promoOfferResponse);
		
		AffiliateOfferResponse affilaiteResponse = oebo.getOfferDetails(salesOfferDetailsRequest);
		Assert.assertEquals(STATUS_CODE_STOP, affilaiteResponse.getStatusCode());
	
	}
}