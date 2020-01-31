package com.multibrand.bo;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.multibrand.dto.KBAErrorDTO;
import com.multibrand.dto.request.KbaAnswerRequest;
import com.multibrand.service.OEService;
import com.multibrand.util.Constants;
import com.multibrand.util.LoggerUtil;
import com.multibrand.vo.response.KbaAnswerResponse;
import com.multibrand.domain.KbaErrorDTO;
import com.multibrand.domain.KbaSubmitAnswerRequest;
import com.multibrand.domain.KbaSubmitAnswerResponse;

import org.junit.Assert;

//@Test(singleThreaded = true)
public class OEBOTest {

	@InjectMocks
	private OEBO oebo;

	@Mock
	OEService oeService;
	
	@Mock
	private LoggerUtil logger;
	
	/*@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(logger.isDebugEnabled()).thenReturn(true);
	}

	@BeforeMethod
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
		when(logger.isDebugEnabled()).thenReturn(true);
		
	}
*/
	// FOR JUNIT

	
	  @Before
	  public void init()
	  { 
	  MockitoAnnotations.initMocks(this);
}
	 

	//@Test
	public void testSubmitanswerskbaWithoutQuestionAnswerList() {
		//without que answer from input
		KbaAnswerRequest request = new KbaAnswerRequest();
		KbaAnswerResponse response = new KbaAnswerResponse();
		KbaSubmitAnswerRequest kbaAnswerRequest = new KbaSubmitAnswerRequest();
		KbaSubmitAnswerResponse kbaSubmitAnswerResponse = new KbaSubmitAnswerResponse();
		kbaSubmitAnswerResponse.setStrErrCode("03");
		request.setTransactionKey("12345678");
		request.setTrackingId("1");
		
		KBAErrorDTO[] kbaerrorDtolist = new KBAErrorDTO[2];
		kbaerrorDtolist[0].setErrorCode("00");
		//kbaSubmitAnswerResponse.setErrorList(kbaerrorDtolist);
		try{
			when(oeService.submitKBAAnswer(kbaAnswerRequest)).thenReturn(kbaSubmitAnswerResponse);
			
		response = oebo.submitanswerskba(request);
		Assert.assertEquals(response.getStatusCode(),Constants.STATUS_CODE_CONTINUE);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
