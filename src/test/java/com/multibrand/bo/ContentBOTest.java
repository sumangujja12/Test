package com.multibrand.bo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.task.TaskExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.multibrand.domain.AddressDO;
import com.multibrand.domain.AllAlertsRequest;
import com.multibrand.domain.AllAlertsResponse;
import com.multibrand.domain.ContractAccountDO;
import com.multibrand.domain.ContractDO;
import com.multibrand.dto.response.OfferDO;
import com.multibrand.exception.OAMException;
import com.multibrand.helper.ContentHelper;
import com.multibrand.service.AdodeAnalyticService;
import com.multibrand.service.ProfileService;
import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;
import com.multibrand.util.LoggerUtil;
import com.multibrand.vo.request.ContractInfoRequest;
import com.multibrand.vo.response.ContractOfferPlanContentResponse;
import com.multibrand.vo.response.GetContractInfoResponse;
import com.multibrand.vo.response.MonthlyUsageResponse;
import com.multibrand.vo.response.MonthlyUsageResponseList;

public class ContentBOTest implements Constants {

	/** Object of ProfileBO class. */
	@Mock
	private ProfileService profileService;

	@Mock
	private ContentHelper contentHelper;

	@Mock
	private HistoryBO historyBO;

	@Mock
	protected EnvMessageReader envMessageReader;

	@Mock
	private TaskExecutor taskExecutor;

	@Mock
	private LoggerUtil logger;

	@InjectMocks
	private ContentBO contentBO;
	@Mock(name = "environmentMessageSource")
	protected ReloadableResourceBundleMessageSource environmentMessageSource;

	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(logger.isDebugEnabled()).thenReturn(true);
	}

	@BeforeMethod
	public void initMethod() {
		when(logger.isDebugEnabled()).thenReturn(true);
		Mockito.reset(profileService);
	}

	@Test
	public void getMultiBrandPlanOffers() {
		ContractInfoRequest request = new ContractInfoRequest();
		request.setAccountNumber("000003040103");
		request.setBpNumber("0002473499");
		request.setBrandId("CE");
		request.setBrandName("CE");
		request.setCompanyCode("0391");
		request.setContractId("0034805112");
		request.setEsid("1008901023816883930105");
		request.setLanguageCode("E");
		request.setMessageId("e7c3dd0d-9e35-446d-b67f-5cce06dc81fa_11272020");	
		request.setOsType("ios");
		request.setZoneId("");
		
		//getContractInfoResponse.setResultCode(resultCode);
		
		try {
			GetContractInfoResponse getContractInfoResponse = getContractInfoResponse();
			when(profileService.getContractInfo(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
					Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
							.thenReturn(getContractInfoResponse);
			com.multibrand.domain.AllAlertsResponse allAlertsResponse = getAllAlertsResponse();
			when(profileService.getContractInfoParallel(Matchers.any(AllAlertsRequest.class), Mockito.anyString()))
					.thenReturn(allAlertsResponse);	
			Set<String> offerList = new HashSet<String>();
			offerList.add("50764703");
			when(contentHelper.getContractOffer(Matchers.any(GetContractInfoResponse.class),
					Matchers.any(com.multibrand.domain.AllAlertsResponse.class),
					Matchers.any(ContractOfferPlanContentResponse.class))).thenReturn(offerList);
			MonthlyUsageResponseList monthilyUsage = getMonthlyUsageList();
			when(historyBO.getMonthlyUsageDetails(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
					Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(monthilyUsage);	
			
					
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
		
		ContractOfferPlanContentResponse response =	contentBO.getMultiBrandPlanOffers(request, null, null);
		assertEquals("0", response.getResultCode());
	}
	
	
	
	
	
	@Test
	public void getMultiBrandPlanOffers_NoOfferCode() {
		ContractInfoRequest request = new ContractInfoRequest();
		request.setAccountNumber("000003040103");
		request.setBpNumber("0002473499");
		request.setBrandId("CE");
		request.setBrandName("CE");
		request.setCompanyCode("0391");
		request.setContractId("0034805112");
		request.setEsid("1008901023816883930105");
		request.setLanguageCode("E");
		request.setMessageId("e7c3dd0d-9e35-446d-b67f-5cce06dc81fa_11272020");	
		request.setOsType("ios");
		request.setZoneId("");
		
		//getContractInfoResponse.setResultCode(resultCode);
		
		try {
			GetContractInfoResponse getContractInfoResponse = getContractInfoResponse();
			when(profileService.getContractInfo(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
					Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
							.thenReturn(getContractInfoResponse);
			com.multibrand.domain.AllAlertsResponse allAlertsResponse = getAllAlertsResponse();
			when(profileService.getContractInfoParallel(Matchers.any(AllAlertsRequest.class), Mockito.anyString()))
					.thenReturn(allAlertsResponse);	
			Set<String> offerList = new HashSet<String>();
			//offerList.add("50764703");
			when(contentHelper.getContractOffer(Matchers.any(GetContractInfoResponse.class),
					Matchers.any(com.multibrand.domain.AllAlertsResponse.class),
					Matchers.any(ContractOfferPlanContentResponse.class))).thenReturn(offerList);
			MonthlyUsageResponseList monthilyUsage = getMonthlyUsageList();
			when(historyBO.getMonthlyUsageDetails(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
					Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(monthilyUsage);	
			
					
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
		
		ContractOfferPlanContentResponse response =	contentBO.getMultiBrandPlanOffers(request, null,null);
		assertEquals("0", response.getResultCode());
	}
	
	
	@Test(expectedExceptions=OAMException.class)
	public void getMultiBrandPlanOffers_RemoteException() {
		ContractInfoRequest request = new ContractInfoRequest();
		request.setAccountNumber("000003040103");
		request.setBpNumber("0002473499");
		request.setBrandId("CE");
		request.setBrandName("CE");
		request.setCompanyCode("0391");
		request.setContractId("0034805112");
		request.setEsid("1008901023816883930105");
		request.setLanguageCode("E");
		request.setMessageId("e7c3dd0d-9e35-446d-b67f-5cce06dc81fa_11272020");	
		request.setOsType("ios");
		request.setZoneId("");
		
		//getContractInfoResponse.setResultCode(resultCode);
		
		try {
			GetContractInfoResponse getContractInfoResponse = getContractInfoResponse();
			when(profileService.getContractInfo(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
					Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),Mockito.anyString()))
							.thenThrow(new RemoteException("Sample Exception"));
			com.multibrand.domain.AllAlertsResponse allAlertsResponse = getAllAlertsResponse();
			when(profileService.getContractInfoParallel(Matchers.any(AllAlertsRequest.class), Mockito.anyString()))
					.thenReturn(allAlertsResponse);	
			Set<String> offerList = new HashSet<String>();
			offerList.add("50764703");
			when(contentHelper.getContractOffer(Matchers.any(GetContractInfoResponse.class),
					Matchers.any(com.multibrand.domain.AllAlertsResponse.class),
					Matchers.any(ContractOfferPlanContentResponse.class))).thenReturn(offerList);
			MonthlyUsageResponseList monthilyUsage = getMonthlyUsageList();
			when(historyBO.getMonthlyUsageDetails(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
					Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(monthilyUsage);	
			
					
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
		
		ContractOfferPlanContentResponse response =	contentBO.getMultiBrandPlanOffers(request, null, null);
		assertEquals(RESULT_CODE_EXCEPTION_FAILURE,response.getResultCode());
		assertEquals(RESULT_DESCRIPTION_EXCEPTION,response.getResultDescription());
	}
	
	
	@Test(expectedExceptions=OAMException.class)
	public void getMultiBrandPlanOffers_Exception() {
		ContractInfoRequest request = new ContractInfoRequest();
		request.setAccountNumber("000003040103");
		request.setBpNumber("0002473499");
		request.setBrandId("CE");
		request.setBrandName("CE");
		request.setCompanyCode("0391");
		request.setContractId("0034805112");
		request.setEsid("1008901023816883930105");
		request.setLanguageCode("E");
		request.setMessageId("e7c3dd0d-9e35-446d-b67f-5cce06dc81fa_11272020");	
		request.setOsType("ios");
		request.setZoneId("");
		
		//getContractInfoResponse.setResultCode(resultCode);
		
		try {
			GetContractInfoResponse getContractInfoResponse = getContractInfoResponse();
			when(profileService.getContractInfo(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
					Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
							.thenThrow(new Exception("Sample Exception"));
			com.multibrand.domain.AllAlertsResponse allAlertsResponse = getAllAlertsResponse();
			when(profileService.getContractInfoParallel(Matchers.any(AllAlertsRequest.class), Mockito.anyString()))
					.thenReturn(allAlertsResponse);	
			Set<String> offerList = new HashSet<String>();
			offerList.add("50764703");
			when(contentHelper.getContractOffer(Matchers.any(GetContractInfoResponse.class),
					Matchers.any(com.multibrand.domain.AllAlertsResponse.class),
					Matchers.any(ContractOfferPlanContentResponse.class))).thenReturn(offerList);
			MonthlyUsageResponseList monthilyUsage = getMonthlyUsageList();
			when(historyBO.getMonthlyUsageDetails(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
					Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(monthilyUsage);	
			
					
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
		
		ContractOfferPlanContentResponse response =	contentBO.getMultiBrandPlanOffers(request, null, null);
		assertEquals(RESULT_CODE_EXCEPTION_FAILURE,response.getResultCode());
		assertEquals(RESULT_DESCRIPTION_EXCEPTION,response.getResultDescription());
	}
	
	
	private GetContractInfoResponse getContractInfoResponse()
			throws JsonParseException, JsonMappingException, IOException {
		String getStringJson = getContent("json/getContractInfo.json");
		GetContractInfoResponse getContractInfoResponse = mapFromJson(getStringJson, GetContractInfoResponse.class);
		return getContractInfoResponse;
	}

	protected String getContent(String fileName) throws IOException {
		InputStream fileStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
		return IOUtils.toString(fileStream);

	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws com.fasterxml.jackson.core.JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
	
	private AllAlertsResponse getAllAlertsResponse() {
		AllAlertsResponse response = new AllAlertsResponse();
		com.multibrand.domain.ContractAccountDO  [] contractAccDoList = new com.multibrand.domain.ContractAccountDO[1];
		com.multibrand.domain.ContractAccountDO contractAcc = new ContractAccountDO();
		contractAcc.setCAName("KARA HOUSER");
		contractAcc.setStrCANumber("000072938063");
		contractAcc.setStrBrandName("CE");
		contractAcc.setStrBPNumber("6021838525");
		contractAcc.setStrCharityId("");
		contractAcc.setStrCompany("0391");
		contractAcc.setStrDisconnectAmt("0.00");
		contractAcc.setStrCustSegment("RS");
		contractAcc.setStrEbillFlag("O");
		contractAcc.setStrCheckDigit("4");
		contractAcc.setStrPaperFlag("X");
		contractAcc.setStrLanguageCode("E");
		
		com.multibrand.domain.AddressDO billingAddressDO  = new AddressDO();
		contractAcc.setBillingAddressDO(billingAddressDO);
		billingAddressDO.setStrAddressID("4637340716");
		billingAddressDO.setStrPOBox("");
		billingAddressDO.setStrStreetNum("9031");
		billingAddressDO.setStrStreetName("6102 BLVD");
		billingAddressDO.setStrCity("DALLAS");
		billingAddressDO.setStrState("TX");
		billingAddressDO.setStrZip("75214");
		billingAddressDO.setStrAddressLine("");
		billingAddressDO.setStrApartNum("3138");
		billingAddressDO.setStrCountry("US");
		com.multibrand.domain.ContractDO [] contractDOList = new com.multibrand.domain.ContractDO[1];
		contractAcc.setListOfContracts(contractDOList);
		
		com.multibrand.domain.ContractDO contractDO = new ContractDO();
		contractDOList [0] = contractDO;
		contractDO.setStrOfferCode("50764703");
		com.multibrand.domain.AddressDO serviceAddressDO  = new AddressDO();
		contractDO.setServiceAddressDO(serviceAddressDO);
		serviceAddressDO.setStrAddressID("4637340716");
		serviceAddressDO.setStrPOBox("");
		serviceAddressDO.setStrStreetNum("9031");
		serviceAddressDO.setStrStreetName("6102 BLVD");
		serviceAddressDO.setStrCity("DALLAS");
		serviceAddressDO.setStrState("TX");
		serviceAddressDO.setStrZip("75214");
		serviceAddressDO.setStrAddressLine("");
		serviceAddressDO.setStrApartNum("3138");
		serviceAddressDO.setStrCountry("US");
		
		
       
		contractDO.setStrContractID("0054093143");
		contractDO.setStrContractStartDate("20190807");
		contractDO.setStrESIID("10443720009849083");
		contractDO.setStrGuardLightFlag("");
		contractDO.setStrMoveInDate("20170630");
		contractDO.setStrMoveOutDate("99991231");
		contractDO.setStrMeterType("AMSR");
		contractDO.setStrMeterNumber("120405598LG");
    
		contractAccDoList [0] = contractAcc;
		response.setContractAccDoList(contractAccDoList);
		
		return response;
	}
	
	
	private MonthlyUsageResponseList getMonthlyUsageList() {
		MonthlyUsageResponseList monthlyUsage = new MonthlyUsageResponseList();
		List<MonthlyUsageResponse> monthlyUsageList = new LinkedList<MonthlyUsageResponse>();
		MonthlyUsageResponse responseVO = new MonthlyUsageResponse();
		responseVO.setBusPartner("6031465907");
		responseVO.setContractAcctId("000073263788");
		responseVO.setContractId("0058650918");
		responseVO.setEsiId("10443720001454624");
		responseVO.setMonthAveTempHigh("60");
		responseVO.setMonthAveTempLow("42");
		responseVO.setTotalUsageMonth("30.033");
		responseVO.setTotalMonthCost("30.113");
		responseVO.setTotalUsageYear("30.113");
		responseVO.setYearMonthNo("0920");
		monthlyUsageList.add(responseVO);

		responseVO = new MonthlyUsageResponse();
		responseVO.setBusPartner("6031465907");
		responseVO.setContractAcctId("000073263788");
		responseVO.setContractId("0058650918");
		responseVO.setEsiId("10443720001454624");
		responseVO.setMonthAveTempHigh("60");
		responseVO.setMonthAveTempLow("42");
		responseVO.setTotalUsageMonth("30.033");
		responseVO.setTotalMonthCost("30.113");
		responseVO.setTotalUsageYear("30.113");
		responseVO.setYearMonthNo("1020");
		monthlyUsageList.add(responseVO);

		responseVO = new MonthlyUsageResponse();
		responseVO.setBusPartner("6031465907");
		responseVO.setContractAcctId("000073263788");
		responseVO.setContractId("0058650918");
		responseVO.setEsiId("10443720001454624");
		responseVO.setMonthAveTempHigh("60");
		responseVO.setMonthAveTempLow("42");
		responseVO.setTotalUsageMonth("30.033");
		responseVO.setTotalMonthCost("30.113");
		responseVO.setTotalUsageYear("30.113");
		responseVO.setYearMonthNo("1120");
		monthlyUsageList.add(responseVO);

		monthlyUsage.setMonthlyUsageResponse(monthlyUsageList);

		return monthlyUsage;
	}
		
}
