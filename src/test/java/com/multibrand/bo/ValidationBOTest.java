
package com.multibrand.bo;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;

import com.multibrand.dto.request.PerformPosIdAndBpMatchRequest;
import com.multibrand.request.handlers.ValidationRequestHandler;
import com.multibrand.service.ValidationService;
import com.multibrand.util.TogglzUtil;
import com.multibrand.vo.response.PerformPosIdandBpMatchResponse;
import com.multibrand.web.i18n.WebI18nMessageSource;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.multibrand.util.Constants;
import com.multibrand.util.LoggerUtil;
public class ValidationBOTest {
	
	@Mock
	OEBO oeBO = new OEBO();
	
	@Mock
	TogglzUtil togglzUtil = new TogglzUtil();
	
	@Mock
	ValidationRequestHandler validateRequestHandler;
	
	@Mock
	ValidationService validationService;
	
	@Mock
	WebI18nMessageSource msgSource;
	
	@Mock
	private LoggerUtil logger;
	
	@InjectMocks
	ValidationBO validBO;

	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(logger.isDebugEnabled()).thenReturn(true);
	}
	
	@Test
	public void testValidatePosIdForThirdRetrywithPosidAllowed() throws Exception{
		
		PerformPosIdAndBpMatchRequest performPosIdBpRequest = createPerformPosIdAndBpMatchRequestForThireRetry();
		
		List<Map<String, String>> personIdAndRetryCountResponse = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put(Constants.PERSON_AFFILIATE_RETRY_COUNT, "3");
		personIdAndRetryCountResponse.add(map);
		
		when(oeBO.getPersonIdAndRetryCountByTrackingNo(performPosIdBpRequest.getTrackingId()))
		.thenReturn(personIdAndRetryCountResponse);
		Mockito.when(togglzUtil.getFeatureStatusFromTogglzByChannel(Constants.TOGGLZ_FEATURE_ALLOW_POSID_SUBMISSION,performPosIdBpRequest.getChannelType()))
		.thenReturn(true);
		when(msgSource.getMessage(Constants.POSID_HOLD_MSG_TXT)).thenReturn("TBD");
		
		PerformPosIdandBpMatchResponse result = validBO.validatePosId(performPosIdBpRequest, null);
		
		Assert.assertEquals(result.getStatusCode(), Constants.STATUS_CODE_CONTINUE);
	}
	@Test
	public void testValidatePosIdForThirdRetrywithPosidNotAllowed() throws Exception{
		
		PerformPosIdAndBpMatchRequest performPosIdBpRequest = createPerformPosIdAndBpMatchRequestForThireRetry();
		
		List<Map<String, String>> personIdAndRetryCountResponse = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put(Constants.PERSON_AFFILIATE_RETRY_COUNT, "3");
		personIdAndRetryCountResponse.add(map);
		
		when(oeBO.getPersonIdAndRetryCountByTrackingNo(performPosIdBpRequest.getTrackingId()))
		.thenReturn(personIdAndRetryCountResponse);
		when(togglzUtil.getFeatureStatusFromTogglzByChannel(Constants.TOGGLZ_FEATURE_ALLOW_POSID_SUBMISSION,performPosIdBpRequest.getChannelType()))
		.thenReturn(false);
		when(msgSource.getMessage(Constants.POSID_HOLD_MSG_TXT)).thenReturn("TBD");
		
		PerformPosIdandBpMatchResponse result = validBO.validatePosId(performPosIdBpRequest, null);
		Assert.assertEquals(result.getStatusCode(), Constants.STATUS_CODE_STOP);
	}
	@Test
	public void testValidatePosIdForInvalidTrackingID() throws Exception{
		
		PerformPosIdAndBpMatchRequest performPosIdBpRequest = createPerformPosIdAndBpMatchRequestForThireRetry();
		performPosIdBpRequest.setTrackingId("FDDFG");
		PerformPosIdandBpMatchResponse result = validBO.validatePosId(performPosIdBpRequest, null);
		
		Assert.assertEquals(result.getStatusCode(), Constants.STATUS_CODE_STOP);
	}
	private PerformPosIdAndBpMatchRequest createPerformPosIdAndBpMatchRequestForThireRetry(){
		PerformPosIdAndBpMatchRequest performPosIdBpRequest = new PerformPosIdAndBpMatchRequest();
		
		performPosIdBpRequest.setTokenizedSSN("GDFF-G53156");
		performPosIdBpRequest.setTokenizedTDL("");		
		performPosIdBpRequest.setTrackingId("21231213312");
		performPosIdBpRequest.setPreferredLanguage("E");
		performPosIdBpRequest.setAffiliateId("A44225");
		performPosIdBpRequest.setCompanyCode("0121");
		performPosIdBpRequest.setBrandId("RE");
		return performPosIdBpRequest;
	}

}
