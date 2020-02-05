package com.multibrand.bo;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

/*import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;*/
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
import com.multibrand.domain.KbaQuestionDTO;
import com.multibrand.dao.KbaDAO;
import com.multibrand.dao.ServiceLocationDao;
import com.multibrand.domain.KbaAnswerDTO;
import com.multibrand.domain.KbaErrorDTO;
import com.multibrand.domain.KbaQuestionRequest;
import com.multibrand.domain.KbaQuestionResponse;
import com.multibrand.domain.KbaResponseAssessmentDTO;
import com.multibrand.domain.KbaResponseOutputDTO;
import com.multibrand.domain.KbaResponseReasonDTO;
import com.multibrand.domain.KbaSubmitAnswerRequest;
import com.multibrand.domain.KbaSubmitAnswerResponse;

import com.multibrand.dto.KBASubmitResultsDTO;
import com.multibrand.dto.request.GetKBAQuestionsRequest;
import com.multibrand.dto.request.KbaAnswerRequest;
import com.multibrand.dto.request.UpdateServiceLocationRequest;
import com.multibrand.service.OEService;
import com.multibrand.util.Constants;
import com.multibrand.util.LoggerUtil;

import com.multibrand.vo.request.KBAQuestionAnswerVO;
import com.multibrand.vo.response.GetKBAQuestionsResponse;
import com.multibrand.vo.response.KbaAnswerResponse;
import com.multibrand.web.i18n.WebI18nMessageSource;

@Test(singleThreaded = true)
public class OEBOTest {

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

	@Spy
	ReloadableResourceBundleMessageSource viewResolverMessageSource = new ReloadableResourceBundleMessageSource();

	
	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(logger.isDebugEnabled()).thenReturn(true);
	}

	@BeforeMethod
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
		when(logger.isDebugEnabled()).thenReturn(true);
	}

	// FOR JUNIT

	
	  /*@Before public void init()
	  { MockitoAnnotations.initMocks(this); }*/
	 

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
			response = oebo.submitanswerskba(request);
			Assert.assertEquals(response.getStatusCode(), Constants.STATUS_CODE_CONTINUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
			response = oebo.submitanswerskba(request);
			Assert.assertEquals(response.getStatusCode(), Constants.STATUS_CODE_CONTINUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
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
			response = oebo.submitanswerskba(request);
			Assert.assertEquals(response.getStatusCode(), Constants.STATUS_CODE_CONTINUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
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
			response = oebo.submitanswerskba(request);
			Assert.assertEquals(response.getSsnVerifyDate(), "12/20/2013");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
			response = oebo.submitanswerskba(request);
			Assert.assertEquals(response.getSsnVerifyDate(), "12/20/2013");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
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
			response = oebo.submitanswerskba(request);
			Assert.assertEquals(response.getDrivingLicenceVerifyDate(), "2013-12-20");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
			response = oebo.submitanswerskba(request);
			Assert.assertEquals(response.getStatusCode(), Constants.STATUS_CODE_STOP);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetKBAQuestionsWithTokenDLWithQuestionList(){
		GetKBAQuestionsRequest request = getRequestForGetKBAQuestionsRequest();
		request.setTokenTDL("543546");
		request.setDrivingLicenseState("TX");
		request.setTrackingId("34444");
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
			Assert.assertEquals(getKBAQuestionsResponse.getMessageCode(), Constants.POSID_FAIL);
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
	
		@Test
		public void testGetKBAQuestionsWithOutTokenDLWithQuestionListException(){
			GetKBAQuestionsRequest request = getRequestForGetKBAQuestionsRequest();
			request.setTrackingId("34444");
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
		}
		
		@Test
		public void testGetKBAQuestionsWithTokenDLWithOutAnswerList(){
			GetKBAQuestionsRequest request = getRequestForGetKBAQuestionsRequest();
			request.setTokenTDL("543546");
			request.setDrivingLicenseState("TX");
			request.setTrackingId("34444");
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
		request.setTokenSSN("234234");
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

}