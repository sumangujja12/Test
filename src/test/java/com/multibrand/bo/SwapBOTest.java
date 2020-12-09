package com.multibrand.bo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.rmi.RemoteException;

import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.task.TaskExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.multibrand.domain.SwapResponse;
import com.multibrand.exception.OAMException;
import com.multibrand.helper.EmailHelper;
import com.multibrand.service.AdodeAnalyticService;
import com.multibrand.service.SwapService;
import com.multibrand.service.TOSService;
import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;
import com.multibrand.util.LoggerUtil;
import com.multibrand.vo.request.SubmitSwapRequest;
import com.multibrand.vo.response.SubmitSwapResponse;

public class SwapBOTest implements Constants{

	
	@Mock
	SwapService swapService;
	
	@Spy
	EmailHelper emailHelper;
	@Spy
	TOSService tosService;
	@Mock
	TaskExecutor taskExecutor;
	
	@Mock
	private BaseBO baseBO;
	@Mock
	private LoggerUtil logger;
	
	@InjectMocks
	private SwapBO swapBO;
	
	@Mock
	protected EnvMessageReader envMessageReader;

	@Mock(name ="environmentMessageSource")
	protected ReloadableResourceBundleMessageSource environmentMessageSource;
	
	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(logger.isDebugEnabled()).thenReturn(true);
	}

	@BeforeMethod
	public void initMethod() {
		Mockito.reset(swapService);
		when(logger.isDebugEnabled()).thenReturn(true);
	}
	
	@Test
	public void  submitSwap() {
		
		SubmitSwapRequest request = new SubmitSwapRequest();
		new SubmitSwapRequest();
		request.setAccountNumber("");
		request.setContractId("0032805283");
		request.setBpNumber("");
		request.setEsid("1008901023817453060106");
		request.setLanguageCode("E");
		request.setCampaignCode("06B01R01V1");
		request.setOfferCode("00002709");
		request.setCurrentContractEndDate("12/27/2020");
		request.setCompanyCode("0391");
		request.setBrandName("CE");
		request.setMessageId("e7c3dd0d-9e35-446d-b67f-5cce06dc81fa_Your contract Expires 12/27/2020");
			
		SwapResponse response = new SwapResponse();
		response.setErrorCode("");
		response.setIDOCNumber("IDOC09989229829");
		try {
			when(swapService.submitSwap(Matchers.any(com.multibrand.domain.SwapRequest.class), Mockito.anyString(), Mockito.anyString())).thenReturn(response);
			when(envMessageReader.getMessage("adobe.messageId.message")).thenReturn(
					"Your contract will expire on [date].Log on to find clean electricity plans available for you");
			when(envMessageReader.getMessage("template.url.parameter.reportsuite")).thenReturn(
					"relengmemobile.appstage");
			when(envMessageReader.getMessage(ADOBE_ANALYTIC_TEMPLATE_URL)).thenReturn(
					"https://reliantenergy.sc.omtrdc.net/b/ss/[ReportSuite]/0?");
			when(envMessageReader.getMessage(ADOBE_ANALYTIC_TEMPLATE_URL)).thenReturn(
					"https://reliantenergy.sc.omtrdc.net/b/ss/[ReportSuite]/0?");
			when(envMessageReader.getMessage(TEMPLATE_URL_QUERY_LIST_PARAMETER_ONE)).thenReturn(
					"AQB=1&ndh=1&pageName=myaccount:swap:notification&ch=App%20API&server=api.[BRAND].com&vid=[CANumber]&c.&mycocode=[CompanyCode]&myca=[CANumber]&mymsgid=[MsgId]");
			when(envMessageReader.getMessage(TEMPLATE_URL_QUERY_LIST_PARAMETER_TWO)).thenReturn(
					"&myreaddt=[ActionDate]&mymsgtype=[MessageType]&mymsgcat=[MessageCat]&mymsg=[Message]&mymsgstatus=[MsgStatus]&mylang=[Language]&myos=[OSType]&mycontractid=[ContractId]&mybp=[BPNumber]&msginstance=[msgInstance]&myerrmsg=[ErrorMessage]&.c&AQE=1");
			when(envMessageReader.getMessage(IOT_POST_URL)).thenReturn(
					"http://txaixsexbls05/iotproxy/postServiceRequestURL");

			doNothing().when(taskExecutor).execute(Matchers.any(AdodeAnalyticService.class));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SubmitSwapResponse swapResponse = swapBO.submitSwap(request, null, null);
		assertEquals("",swapResponse.getErrorCode());
		assertEquals("IDOC09989229829",swapResponse.getIDOCNumber());
	}
	
	
	
	//@Test(expectedExceptions=OAMException.class)
	public void  submitSwap_Exception() {
		
		SubmitSwapRequest request = new SubmitSwapRequest();
		new SubmitSwapRequest();
		request.setAccountNumber("");
		request.setContractId("0032805283");
		request.setBpNumber("");
		request.setEsid("1008901023817453060106");
		request.setLanguageCode("E");
		request.setCampaignCode("06B01R01V1");
		request.setOfferCode("00002709");
		request.setCurrentContractEndDate("12/27/2020");
		request.setCompanyCode("0391");
		request.setBrandName("CE");
		request.setMessageId("e7c3dd0d-9e35-446d-b67f-5cce06dc81fa_Your contract Expires 12/27/2020");
			
		SwapResponse response = new SwapResponse();
		response.setErrorCode("");
		response.setIDOCNumber("IDOC09989229829");
		try {
			when(swapService.submitSwap(Matchers.any(com.multibrand.domain.SwapRequest.class), Mockito.anyString(), Mockito.anyString())).thenThrow(new Exception("sample exception"));
			when(envMessageReader.getMessage("adobe.messageId.message")).thenReturn(
					"Your contract will expire on [date].Log on to find clean electricity plans available for you");
			when(envMessageReader.getMessage("template.url.parameter.reportsuite")).thenReturn(
					"relengmemobile.appstage");
			when(envMessageReader.getMessage(ADOBE_ANALYTIC_TEMPLATE_URL)).thenReturn(
					"https://reliantenergy.sc.omtrdc.net/b/ss/[ReportSuite]/0?");
			when(envMessageReader.getMessage(ADOBE_ANALYTIC_TEMPLATE_URL)).thenReturn(
					"https://reliantenergy.sc.omtrdc.net/b/ss/[ReportSuite]/0?");
			when(envMessageReader.getMessage(TEMPLATE_URL_QUERY_LIST_PARAMETER_ONE)).thenReturn(
					"AQB=1&ndh=1&pageName=myaccount:swap:notification&ch=App%20API&server=api.[BRAND].com&vid=[CANumber]&c.&mycocode=[CompanyCode]&myca=[CANumber]&mymsgid=[MsgId]");
			when(envMessageReader.getMessage(TEMPLATE_URL_QUERY_LIST_PARAMETER_TWO)).thenReturn(
					"&myreaddt=[ActionDate]&mymsgtype=[MessageType]&mymsgcat=[MessageCat]&mymsg=[Message]&mymsgstatus=[MsgStatus]&mylang=[Language]&myos=[OSType]&mycontractid=[ContractId]&mybp=[BPNumber]&msginstance=[msgInstance]&myerrmsg=[ErrorMessage]&.c&AQE=1");
			when(envMessageReader.getMessage(IOT_POST_URL)).thenReturn(
					"http://txaixsexbls05/iotproxy/postServiceRequestURL");

			doNothing().when(taskExecutor).execute(Matchers.any(AdodeAnalyticService.class));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SubmitSwapResponse swapResponse = swapBO.submitSwap(request, null, null);
		assertEquals(RESULT_CODE_EXCEPTION_FAILURE,swapResponse.getResultCode());
		assertEquals(RESULT_DESCRIPTION_EXCEPTION,swapResponse.getResultDescription());
	}
	
	
	//@Test(expectedExceptions=OAMException.class)
	public void  submitSwap_RemoteException() {
		
		SubmitSwapRequest request = new SubmitSwapRequest();
		new SubmitSwapRequest();
		request.setAccountNumber("");
		request.setContractId("0032805283");
		request.setBpNumber("");
		request.setEsid("1008901023817453060106");
		request.setLanguageCode("E");
		request.setCampaignCode("06B01R01V1");
		request.setOfferCode("00002709");
		request.setCurrentContractEndDate("12/27/2020");
		request.setCompanyCode("0391");
		request.setBrandName("CE");
		request.setMessageId("e7c3dd0d-9e35-446d-b67f-5cce06dc81fa_Your contract Expires 12/27/2020");
			
		SwapResponse response = new SwapResponse();
		response.setErrorCode("");
		response.setIDOCNumber("IDOC09989229829");
		try {
			when(swapService.submitSwap(Matchers.any(com.multibrand.domain.SwapRequest.class), Mockito.anyString(), Mockito.anyString())).thenThrow(new RemoteException("sample exception"));
			when(envMessageReader.getMessage("adobe.messageId.message")).thenReturn(
					"Your contract will expire on [date].Log on to find clean electricity plans available for you");
			when(envMessageReader.getMessage("template.url.parameter.reportsuite")).thenReturn(
					"relengmemobile.appstage");
			when(envMessageReader.getMessage(ADOBE_ANALYTIC_TEMPLATE_URL)).thenReturn(
					"https://reliantenergy.sc.omtrdc.net/b/ss/[ReportSuite]/0?");
			when(envMessageReader.getMessage(ADOBE_ANALYTIC_TEMPLATE_URL)).thenReturn(
					"https://reliantenergy.sc.omtrdc.net/b/ss/[ReportSuite]/0?");
			when(envMessageReader.getMessage(TEMPLATE_URL_QUERY_LIST_PARAMETER_ONE)).thenReturn(
					"AQB=1&ndh=1&pageName=myaccount:swap:notification&ch=App%20API&server=api.[BRAND].com&vid=[CANumber]&c.&mycocode=[CompanyCode]&myca=[CANumber]&mymsgid=[MsgId]");
			when(envMessageReader.getMessage(TEMPLATE_URL_QUERY_LIST_PARAMETER_TWO)).thenReturn(
					"&myreaddt=[ActionDate]&mymsgtype=[MessageType]&mymsgcat=[MessageCat]&mymsg=[Message]&mymsgstatus=[MsgStatus]&mylang=[Language]&myos=[OSType]&mycontractid=[ContractId]&mybp=[BPNumber]&msginstance=[msgInstance]&myerrmsg=[ErrorMessage]&.c&AQE=1");
			when(envMessageReader.getMessage(IOT_POST_URL)).thenReturn(
					"http://txaixsexbls05/iotproxy/postServiceRequestURL");

			doNothing().when(taskExecutor).execute(Matchers.any(AdodeAnalyticService.class));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SubmitSwapResponse swapResponse = swapBO.submitSwap(request, null, null);
		assertEquals(RESULT_CODE_EXCEPTION_FAILURE,swapResponse.getResultCode());
		assertEquals(RESULT_DESCRIPTION_EXCEPTION,swapResponse.getResultDescription());
	}

}
