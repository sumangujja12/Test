package com.multibrand.resource;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.multibrand.bo.TOSBO;
import com.multibrand.config.WSConfig;
import com.multibrand.dao.AddressDAOIF;
import com.multibrand.dao.ResultObject;
import com.multibrand.domain.CheckPendingMVORequest;
import com.multibrand.domain.CheckPendingMVOResponse;
import com.multibrand.domain.CheckPendingMoveInRequest;
import com.multibrand.domain.CheckPendingMoveInResponse;
import com.multibrand.domain.OfferOfContractRequest;
import com.multibrand.domain.OfferOfContractResponse;
import com.multibrand.domain.TOSDomain;
import com.multibrand.manager.StoredProcedureManager;
import com.multibrand.resources.TOSResource;
import com.multibrand.resources.requestHandlers.TOSRequestHandler;
import com.multibrand.service.TOSService;
//import com.sun.javafx.collections.MappingChange.Map;
import java.util.Map;
import com.multibrand.manager.BaseStoredProcedure;



public class TOSResourceTest extends AbstractJunitTest{
	
	@Autowired
	@InjectMocks
	TOSResource tosResource;
	
	@Autowired
	@InjectMocks
	TOSRequestHandler tOSRequestHandler;
	
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	//@Mock
	//TOSDomainProxy tosDomainProxy;
	
	//@Mock
	//TOSProxy tosProxy;
	
	@Mock
	TOSDomain tosDomain;
	
	@Autowired
	@InjectMocks
	TOSBO tosBO1;
	
	//@Mock
	//TOSBO tosBO;
	
	/*@Mock
	private TOSService tosService;*/
	
	@Mock
	private TOSService tosService;
	
	@Mock
	javax.ws.rs.core.Response resp;
	
	/*@Mock
	BaseStoredProcedure baseStoredProcedure;
	
	@Mock
	StoredProcedureManager storedProcedure;
	
	@Mock
    //@Qualifier("podJdbcTemplate")
    private JdbcTemplate jdbcTemplate;*/
	
	/*@Autowired
	@InjectMocks
	private AddressDAOIF addressDAO;*/
	
	//@Mock
	//private HttpServletRequest httpRequest;
	
		
	/*@Mock
	ContentDaoImpl contentDao;*/
	
	//@Mock
	//AbstractMessageSource sqlMessage;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		// Set up Spring test in web app mode
		//mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		tosResource.setHttpRequest(new MockHttpServletRequest());
	}

	@Test
	public void testCheckPendingMVO() throws Exception {
		
		CheckPendingMVOResponse response = new CheckPendingMVOResponse();
		
		response.setMoveOutDate("01/08/2020");
		response.setPendingMoveOutExists("X");
		response.setForcedMoveOut("Y");
		Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		
		Mockito.when(tosDomain.checkPendingMoveOut(Mockito.any(CheckPendingMVORequest.class))).thenReturn(response);
		com.multibrand.vo.response.CheckPendingMVOResponse checkPendingMVOResponse = new com.multibrand.vo.response.CheckPendingMVOResponse();
		Mockito.when(tosService.checkingPendingMVO(Mockito.any(CheckPendingMVORequest.class), Mockito.any(String.class), Mockito.any(String.class))).thenReturn(response);
		//Mockito.when(tosBO.checkPendingMVO(Mockito.any(CheckPendingMVORequest.class), Mockito.any(String.class), Mockito.any(String.class))).thenReturn(checkPendingMVOResponse);
		resp = tosResource.checkPendingMVO("000070555574", "021");
						
		}
	
	@Test
	public void testCheckPendingMVI() throws Exception {
		
		CheckPendingMoveInResponse chkPndgMovResp = new CheckPendingMoveInResponse();
		chkPndgMovResp.setErrCode("400");
		Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		Mockito.when(tosDomain.checkPendingMVI(Mockito.any(CheckPendingMoveInRequest.class))).thenReturn(chkPndgMovResp);
		
		Mockito.when(tosService.checkingPendingMVI(Mockito.any(CheckPendingMoveInRequest.class), Mockito.any(String.class), Mockito.any(String.class))).thenReturn(chkPndgMovResp);
		resp = tosResource.checkPendingMVI("000070555574", "6010617433", "P1234568", "021");
						
	}
	
	/*@Test
	public void testGetESIDForAddress() throws Exception {
		
		
		
		ArgumentCaptor<Map<String, Object>> argCaptor = ArgumentCaptor.forClass(Map.class);
		
		ArgumentCaptor<Map<String, Integer>> argCaptor1 = ArgumentCaptor.forClass(Map.class);
		
		ArgumentCaptor<Map<String, ResultObject>> argCaptor2 = ArgumentCaptor.forClass(Map.class);
		
		when(storedProcedure.createStoredProcedure(Mockito.any(JdbcTemplate.class), Mockito.any(String.class), ArgumentMatchers.<Map<String, Object>>any(), ArgumentMatchers.<Map<String, Integer>>any(), ArgumentMatchers.<Map<String, ResultObject>>any(), Mockito.any(String.class))).thenReturn(baseStoredProcedure);
		//when(baseStoredProcedure.execute()).thenReturn(storedProcResult);
		
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		//Mockito.when(tosDomain.checkPendingMVI(Mockito.any(CheckPendingMoveInRequest.class))).thenReturn(chkPndgMovResp);
		
		//Mockito.when(tosDomain.checkPendingMoveOut(Mockito.any(CheckPendingMVORequest.class))).thenReturn(response);
		//com.multibrand.vo.response.CheckPendingMVOResponse checkPendingMVOResponse = new com.multibrand.vo.response.CheckPendingMVOResponse();
		//Mockito.when(tosService.checkingPendingMVI(Mockito.any(CheckPendingMoveInRequest.class), Mockito.any(String.class), Mockito.any(String.class))).thenReturn(chkPndgMovResp);
		//Mockito.when(tosBO.checkPendingMVO(Mockito.any(CheckPendingMVORequest.class), Mockito.any(String.class), Mockito.any(String.class))).thenReturn(checkPendingMVOResponse);
		resp = tosResource.getESIDForAddress("356", "HOUSTON", "USA", "CALIFORNIA", "BSE", "12", "509103", "021");
						
	}*/
	
	@Test
	public void testGetOfferOfContract() throws Exception {
		
		OfferOfContractResponse offContratResp = new OfferOfContractResponse();
		offContratResp.setErrCode("400");
		Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		Mockito.when(tosDomain.getOfferOfContract(Mockito.any(OfferOfContractRequest.class))).thenReturn(offContratResp);
		
		Mockito.when(tosService.getOfferOfContract(Mockito.any(OfferOfContractRequest.class), Mockito.any(String.class))).thenReturn(offContratResp);
		resp = tosResource.getOfferOfContract("000070555574", "021", "EN");
						
	}
	
	/*@Test
	public void testTosPreCheckConditions_ForcedMoveOut_Y() throws Exception {
		
		CheckPendingMVOResponse response = new CheckPendingMVOResponse();
		
		response.setMoveOutDate("01/08/2020");
		//response.setPendingMoveOutExists("X");
		response.setForcedMoveOut("Y");
				
		TdspByESIDResponse tdspByESIDResponse = new TdspByESIDResponse();
		
		tdspByESIDResponse.setServiceId("10443720001032191");
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.checkPendingMoveOut(Mockito.anyObject())).thenReturn(response);
		
		when(oeDomainProxy.getOEDomainProxyClient()).thenReturn(oeDomain);
		when(oeDomain.getTDSPSpecificCalendarDates(Mockito.anyObject())).thenReturn("12/08/2020");
		
		when(tosDomain.getTDSPFromESID(Mockito.anyObject())).thenReturn(tdspByESIDResponse);
		
		
		this.mockMvc.perform(post("/tos/tosPreCheckConditions")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("contractAccountNumber", "000070555574")
				.param("contractId", "0071399859")
				.param("businessPartnerNumber", "6005802361")
				.param("newEsiId", "10443720001032191")
				.param("oldEsiId", "10443720001032191")
				.param("newTdsp", "D0002")
				.param("oldTdsp", "D0002")
				.param("permitType", "NO_PERMIT"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testTosPreCheckConditions_PendingMoveOutExists_ForcedMoveOut_BothEmpty() throws Exception {
		
		CheckPendingMVOResponse response = new CheckPendingMVOResponse();
		
		response.setMoveOutDate("01/08/2020");
		//response.setPendingMoveOutExists("X");
		//response.setForcedMoveOut("Y");
		
		ProgramAccountInfoResponse programAccountInfoResponse = new ProgramAccountInfoResponse();
		programAccountInfoResponse.setPendingAmount(new BigDecimal(105));
				
		TdspByESIDResponse tdspByESIDResponse = new TdspByESIDResponse();
		
		tdspByESIDResponse.setServiceId("10443720001032191");
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.checkPendingMoveOut(Mockito.anyObject())).thenReturn(response);
		
		when(oeDomainProxy.getOEDomainProxyClient()).thenReturn(oeDomain);
		when(oeDomain.getTDSPSpecificCalendarDates(Mockito.anyObject())).thenReturn("12/08/2020");
		
		when(tosDomain.getProgramAccountInfo(Mockito.anyObject())).thenReturn(programAccountInfoResponse);
		
		when(tosDomain.getTDSPFromESID(Mockito.anyObject())).thenReturn(tdspByESIDResponse);
		
		
		this.mockMvc.perform(post("/tos/tosPreCheckConditions")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("contractAccountNumber", "000070555574")
				.param("contractId", "0071399859")
				.param("businessPartnerNumber", "6005802361")
				.param("newEsiId", "10443720001032191")
				.param("oldEsiId", "10443720001032191")
				.param("newTdsp", "D0002")
				.param("oldTdsp", "D0002")
				.param("permitType", "NO_PERMIT"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testTosPreCheckConditions_PendingMoveOutExists_ForcedMoveOut_BothEmpty1() throws Exception {
		
		CheckPendingMVOResponse response = new CheckPendingMVOResponse();
		
		response.setMoveOutDate("01/08/2020");
		//response.setPendingMoveOutExists("X");
		//response.setForcedMoveOut("Y");
		
		ProgramAccountInfoResponse programAccountInfoResponse = new ProgramAccountInfoResponse();
		programAccountInfoResponse.setPendingAmount(new BigDecimal(95));
				
		TdspByESIDResponse tdspByESIDResponse = new TdspByESIDResponse();
		
		tdspByESIDResponse.setServiceId("10443720001032191");
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.checkPendingMoveOut(Mockito.anyObject())).thenReturn(response);
		
		when(oeDomainProxy.getOEDomainProxyClient()).thenReturn(oeDomain);
		when(oeDomain.getTDSPSpecificCalendarDates(Mockito.anyObject())).thenReturn("12/08/2020");
		
		when(tosDomain.getProgramAccountInfo(Mockito.anyObject())).thenReturn(programAccountInfoResponse);
		
		TransferServiceResponse transferServiceResponse = new TransferServiceResponse();
		transferServiceResponse.setOutStatus("EXIST");
		
		when(transferServiceDataAccessDomainProxy.getTransferServiceDataAccessDomainProxyClient()).thenReturn(transferServiceDataAccessDomain);
		when(transferServiceDataAccessDomain.getTOSWithinLastTwoDays(Mockito.anyObject())).thenReturn(transferServiceResponse);
		
		when(tosDomain.getTDSPFromESID(Mockito.anyObject())).thenReturn(tdspByESIDResponse);
		
		
		this.mockMvc.perform(post("/tos/tosPreCheckConditions")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("contractAccountNumber", "000070555574")
				.param("contractId", "0071399859")
				.param("businessPartnerNumber", "6005802361")
				.param("newEsiId", "10443720001032191")
				.param("oldEsiId", "10443720001032191")
				.param("newTdsp", "D0002")
				.param("oldTdsp", "D0002")
				.param("permitType", "NO_PERMIT"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testTosPreCheckConditions_PendingMoveOutExists_ForcedMoveOut_BothEmpty2() throws Exception {
		
		CheckPendingMVOResponse response = new CheckPendingMVOResponse();
		
		response.setMoveOutDate("01/08/2020");
		//response.setPendingMoveOutExists("X");
		//response.setForcedMoveOut("Y");
		
		ProgramAccountInfoResponse programAccountInfoResponse = new ProgramAccountInfoResponse();
		programAccountInfoResponse.setPendingAmount(new BigDecimal(95));
				
		TdspByESIDResponse tdspByESIDResponse = new TdspByESIDResponse();
		
		tdspByESIDResponse.setServiceId("10443720001032191");
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.checkPendingMoveOut(Mockito.anyObject())).thenReturn(response);
		
		when(oeDomainProxy.getOEDomainProxyClient()).thenReturn(oeDomain);
		when(oeDomain.getTDSPSpecificCalendarDates(Mockito.anyObject())).thenReturn("12/08/2020");
		
		when(tosDomain.getProgramAccountInfo(Mockito.anyObject())).thenReturn(programAccountInfoResponse);
		
		TransferServiceResponse transferServiceResponse = new TransferServiceResponse();
		transferServiceResponse.setOutStatus("EXISTS");
		
		when(transferServiceDataAccessDomainProxy.getTransferServiceDataAccessDomainProxyClient()).thenReturn(transferServiceDataAccessDomain);
		when(transferServiceDataAccessDomain.getTOSWithinLastTwoDays(Mockito.anyObject())).thenReturn(transferServiceResponse);
		
		//when(tosDomain.getTDSPFromESID(Mockito.anyObject())).thenReturn(tdspByESIDResponse);
		
		
		this.mockMvc.perform(post("/tos/tosPreCheckConditions")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("contractAccountNumber", "000070555574")
				.param("contractId", "0071399859")
				.param("businessPartnerNumber", "6005802361")
				.param("newEsiId", "10443720001032191")
				.param("oldEsiId", "10443720001032191")
				.param("newTdsp", "D0002")
				.param("oldTdsp", "D0002")
				.param("permitType", "NO_PERMIT"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testTOSPreCheckConditions() throws Exception {
		
		CheckPendingMVOResponse response = new CheckPendingMVOResponse();
		
		response.setMoveOutDate("0000-00-00");
		response.setPendingMoveOutExists("X");
		response.setForcedMoveOut("Y");
				
		TdspByESIDResponse tdspByESIDResponse = new TdspByESIDResponse();
		
		tdspByESIDResponse.setServiceId("10443720001032191");
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.checkPendingMoveOut(Mockito.anyObject())).thenReturn(response);
		
		
		when(tosDomain.getTDSPFromESID(Mockito.anyObject())).thenReturn(tdspByESIDResponse);
		
		
		this.mockMvc.perform(post("/tos/tosPreCheckConditions")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("contractAccountNumber", "000074228768")
				.param("contractId", "0071399859")
				.param("businessPartnerNumber", "6005802361")
				.param("oldEsiId", "10443720001032191"))
				.andExpect(status().isOk());
	}	
	
	
	private AddressValidateResponse getAddressValidateResponse() {
		AddressValidateResponse addressValidateResponse = new AddressValidateResponse();
		addressValidateResponse.setCity("KATY");
		addressValidateResponse.setCountry("US");
		addressValidateResponse.setExRetCode("I");
		addressValidateResponse.setMatchStatusFlag("COMPLETE_MATCH");
		addressValidateResponse.setState("TX");
		addressValidateResponse.setStatusValue("NO_POPUP");
		addressValidateResponse.setStreetName("S MASON RD");
		addressValidateResponse.setStreetNum("2005");
		addressValidateResponse.setUs_dpvCmra("N");
		addressValidateResponse.setUs_dpvConfirm("Y");
		addressValidateResponse.setZipCode("77450");
		return addressValidateResponse;
	}
	
	private List<EsiidDetails> getEsiidDetails() {
		List<EsiidDetails> esiidDetailLists = new LinkedList<EsiidDetails>();
		EsiidDetails esiidDetail = new EsiidDetails();
		esiidDetail.setEsiid("1008901023900078400107");
		esiidDetail.setEsiidCount("1");
		esiidDetail.setNonPolrDeposit("330");
		esiidDetail.setPermitClass("1");
		esiidDetail.setTdsp("D0001");
		esiidDetailLists.add(esiidDetail);
		return esiidDetailLists;
	}
	
	private List<EsiidDetails> getBussinessEsiidDetails() {
		List<EsiidDetails> esiidDetailLists = new LinkedList<EsiidDetails>();
		EsiidDetails esiidDetail = new EsiidDetails();
		esiidDetail.setEsiid("1008901023900078400107");
		esiidDetail.setEsiidCount("1");
		esiidDetail.setNonPolrDeposit("330");
		esiidDetail.setPermitClass("2");
		esiidDetail.setTdsp("D0001");
		esiidDetailLists.add(esiidDetail);
		return esiidDetailLists;
	}
	
	private List<EsiidDetails> getNOEsiidDetails() {
		List<EsiidDetails> esiidDetailLists = new LinkedList<EsiidDetails>();
		EsiidDetails esiidDetail = new EsiidDetails();
		esiidDetail.setEsiid("");
		esiidDetail.setEsiidCount("1");
		esiidDetail.setNonPolrDeposit("330");
		esiidDetail.setPermitClass("2");
		esiidDetail.setTdsp("D0001");
		esiidDetailLists.add(esiidDetail);
		return esiidDetailLists;
	}
	
	@Test
	public void testTosAddressVerify_Exception() throws Exception {
		AddressValidateResponse addressValidateResponse = getAddressValidateResponse();
		List<EsiidDetails> esiidDetailLists =getEsiidDetails();
		
		TdspByESIDResponse response = new TdspByESIDResponse();
		response.setCompanyCode("0121");
		response.setServiceId("D0001");
		TdspByZipResponse tdspByZipRequest = new TdspByZipResponse();
		tdspByZipRequest.setCompanyCode("0121");
		tdspByZipRequest.setServiceId("D0001");
		
		PermitCheckResponse permitCheckResponse = new PermitCheckResponse();
		com.multibrand.domain.PermitCheckDO[] checkDOs = new com.multibrand.domain.PermitCheckDO[1];
		com.multibrand.domain.PermitCheckDO permitDO = new com.multibrand.domain.PermitCheckDO();
		permitDO.setStrPermitType1("NO_PERMIT");
		checkDOs[0] = permitDO;
		permitCheckResponse.setCheckDOs(checkDOs);
		
		com.reliant.dataaccess.service.TransferServiceResponse transferServiceResponse = new com.reliant.dataaccess.service.TransferServiceResponse();
		transferServiceResponse.setOutStatus("1234567");
		com.reliant.dataaccess.service.TransferServiceResponse transferServicePow = new com.reliant.dataaccess.service.TransferServiceResponse();
		
		when(addressProxy.getAddressValidationDomainProxyClient()).thenReturn(addressValidationDomain); 
		when(addressValidationDomain.validateAddress(Mockito.anyObject())).thenReturn(addressValidateResponse);	
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.getTDSPFromESID(Mockito.anyObject())).thenReturn(response);
		
		when(oeDomainProxy.getOEDomainProxyClient()).thenReturn(oeDomain);
		when(oeDomain.checkPermitRequirment(Mockito.anyObject())).thenReturn(permitCheckResponse);
		
		when(transferServiceDataAccessProxy.getAddressValidationDomainProxyClient()).thenReturn(transferServiceDataAccessDomain);
		when(transferServiceDataAccessDomain.saveNewServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		
		when(oeDomain.checkPermitRequirment(Mockito.anyObject())).thenReturn(permitCheckResponse);
		
		this.mockMvc.perform(post("/tos/VerifyAddress")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("contractAccountNumber", "000009695620")
				.param("contractId", "0049016119")
				.param("businessPartnerNumber", "0006094527")
				.param("unitNum", "212")
				.param("streetNum", "2005")
				.param("streetName", "S Mason RD")
				.param("city", "Katy")
				.param("state", "tx")
				.param("zipcode", "77450")
				.param("country", "US")
				.param("billingUnitNum", "212")
				.param("billingStreetNum", "2005")
				.param("billingStreetName", "S Mason RD")
				.param("billingCity", "Katy")
				.param("billingState", "Tx")
				.param("billingCountry", "77450")
				.param("country", "US")
				.param("billingAddressSame", "false")
				.param("houseType", "House")
				.param("newConstruction", "false"))
				.andExpect(status().isOk());
	
		
	}
	
	
	@Test
	public void testTosAddressVerify_CONDO() throws Exception {
		AddressValidateResponse addressValidateResponse = getAddressValidateResponse();
		List<EsiidDetails> esiidDetailLists =getBussinessEsiidDetails();
		
		TdspByESIDResponse response = new TdspByESIDResponse();
		response.setCompanyCode("0121");
		response.setServiceId("D0001");
		TdspByZipResponse tdspByZipRequest = new TdspByZipResponse();
		tdspByZipRequest.setCompanyCode("0121");
		tdspByZipRequest.setServiceId("D0001");
		
		PermitCheckResponse permitCheckResponse = new PermitCheckResponse();
		com.multibrand.domain.PermitCheckDO[] checkDOs = new com.multibrand.domain.PermitCheckDO[1];
		com.multibrand.domain.PermitCheckDO permitDO = new com.multibrand.domain.PermitCheckDO();
		permitDO.setStrPermitType1("NO_PERMIT");
		checkDOs[0] = permitDO;
		permitCheckResponse.setCheckDOs(checkDOs);
		
		com.reliant.dataaccess.service.TransferServiceResponse transferServiceResponse = new com.reliant.dataaccess.service.TransferServiceResponse();
		transferServiceResponse.setOutStatus("1234567");
		com.reliant.dataaccess.service.TransferServiceResponse transferServicePow = new com.reliant.dataaccess.service.TransferServiceResponse();
		
		when(addressProxy.getAddressValidationDomainProxyClient()).thenReturn(addressValidationDomain); 
		when(addressValidationDomain.validateAddress(Mockito.anyObject())).thenReturn(addressValidateResponse);	

		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.getTDSPFromESID(Mockito.anyObject())).thenReturn(response);
		
		when(oeDomainProxy.getOEDomainProxyClient()).thenReturn(oeDomain);
		when(oeDomain.checkPermitRequirment(Mockito.anyObject())).thenReturn(permitCheckResponse);
		
		when(transferServiceDataAccessProxy.getAddressValidationDomainProxyClient()).thenReturn(transferServiceDataAccessDomain);
		when(transferServiceDataAccessDomain.saveNewServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);

		
		when(oeDomain.checkPermitRequirment(Mockito.anyObject())).thenReturn(permitCheckResponse);
		
		this.mockMvc.perform(post("/tos/VerifyAddress")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("contractAccountNumber", "000009695620")
				.param("contractId", "0049016119")
				.param("businessPartnerNumber", "0006094527")
				.param("unitNum", "212")
				.param("streetNum", "2005")
				.param("streetName", "S Mason RD")
				.param("city", "Katy")
				.param("state", "tx")
				.param("zipcode", "77450")
				.param("country", "US")
				.param("billingUnitNum", "212")
				.param("billingStreetNum", "2005")
				.param("billingStreetName", "S Mason RD")
				.param("billingCity", "Katy")
				.param("billingState", "Tx")
				.param("billingCountry", "77450")
				.param("country", "US")
				.param("billingAddressSame", "false")
				.param("houseType", "TOWNHOUSE_CONDO")
				.param("newConstruction", "false"))
				.andExpect(status().isOk());
	
		
	}
	
	
	@Test
	public void testTosAddressVerify_NOEsiid() throws Exception {
		AddressValidateResponse addressValidateResponse = getAddressValidateResponse();
		addressValidateResponse.setErrorMessage("NOT VALID");
		addressValidateResponse.setMatchStatusFlag("NO_MATCH");
		
		//addressValidateResponse.setErrorCode("400");
		List<EsiidDetails> esiidDetailLists =getNOEsiidDetails();
		
		TdspByESIDResponse response = new TdspByESIDResponse();
		response.setCompanyCode("0121");
		response.setServiceId("D0001");
		TdspByZipResponse tdspByZipRequest = new TdspByZipResponse();
		tdspByZipRequest.setCompanyCode("0121");
		tdspByZipRequest.setServiceId("");
		
		PermitCheckResponse permitCheckResponse = new PermitCheckResponse();
		com.multibrand.domain.PermitCheckDO[] checkDOs = new com.multibrand.domain.PermitCheckDO[1];
		com.multibrand.domain.PermitCheckDO permitDO = new com.multibrand.domain.PermitCheckDO();
		permitDO.setStrPermitType1("NO_PERMIT");
		checkDOs[0] = permitDO;
		permitCheckResponse.setCheckDOs(checkDOs);
		
		com.reliant.dataaccess.service.TransferServiceResponse transferServiceResponse = new com.reliant.dataaccess.service.TransferServiceResponse();
		transferServiceResponse.setOutStatus("1234567");
		com.reliant.dataaccess.service.TransferServiceResponse transferServicePow = new com.reliant.dataaccess.service.TransferServiceResponse();
		
		when(addressProxy.getAddressValidationDomainProxyClient()).thenReturn(addressValidationDomain); 
		when(addressValidationDomain.validateAddress(Mockito.anyObject())).thenReturn(addressValidateResponse);	
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.getTDSPFromESID(Mockito.anyObject())).thenReturn(response);
		
		when(oeDomainProxy.getOEDomainProxyClient()).thenReturn(oeDomain);
		when(oeDomain.checkPermitRequirment(Mockito.anyObject())).thenReturn(permitCheckResponse);
		
		when(transferServiceDataAccessProxy.getAddressValidationDomainProxyClient()).thenReturn(transferServiceDataAccessDomain);
		when(transferServiceDataAccessDomain.saveNewServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		
		when(oeDomain.checkPermitRequirment(Mockito.anyObject())).thenReturn(permitCheckResponse);
		
		this.mockMvc.perform(post("/tos/VerifyAddress")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("contractAccountNumber", "000009695620")
				.param("contractId", "0049016119")
				.param("businessPartnerNumber", "0006094527")
				.param("unitNum", "212")
				.param("streetNum", "2005")
				.param("streetName", "S Mason RD")
				.param("city", "Katy")
				.param("state", "tx")
				.param("zipcode", "77450")
				.param("country", "US")
				.param("billingUnitNum", "212")
				.param("billingStreetNum", "2005")
				.param("billingStreetName", "S Mason RD")
				.param("billingCity", "Katy")
				.param("billingState", "Tx")
				.param("billingCountry", "77450")
				.param("country", "US")
				.param("billingAddressSame", "false")
				.param("houseType", "TOWNHOUSE_CONDO")
				.param("newConstruction", "false"))
				.andExpect(status().isOk());
	
		
	}
	
	@Test
	public void testTosAddressVerify_NOEsiid_PARTIAL_MATCH() throws Exception {
		AddressValidateResponse addressValidateResponse = getAddressValidateResponse();
		addressValidateResponse.setErrorMessage("NOT VALID");
		addressValidateResponse.setMatchStatusFlag("PARTIAL_MATCH");
		
		//addressValidateResponse.setErrorCode("400");
		List<EsiidDetails> esiidDetailLists =getNOEsiidDetails();
		
		TdspByESIDResponse response = new TdspByESIDResponse();
		response.setCompanyCode("0121");
		response.setServiceId("D0001");
		TdspByZipResponse tdspByZipRequest = new TdspByZipResponse();
		tdspByZipRequest.setCompanyCode("0121");
		tdspByZipRequest.setServiceId("");
		
		PermitCheckResponse permitCheckResponse = new PermitCheckResponse();
		com.multibrand.domain.PermitCheckDO[] checkDOs = new com.multibrand.domain.PermitCheckDO[1];
		com.multibrand.domain.PermitCheckDO permitDO = new com.multibrand.domain.PermitCheckDO();
		permitDO.setStrPermitType1("NO_PERMIT");
		checkDOs[0] = permitDO;
		permitCheckResponse.setCheckDOs(checkDOs);
		
		com.reliant.dataaccess.service.TransferServiceResponse transferServiceResponse = new com.reliant.dataaccess.service.TransferServiceResponse();
		transferServiceResponse.setOutStatus("1234567");
		com.reliant.dataaccess.service.TransferServiceResponse transferServicePow = new com.reliant.dataaccess.service.TransferServiceResponse();
		
		when(addressProxy.getAddressValidationDomainProxyClient()).thenReturn(addressValidationDomain); 
		when(addressValidationDomain.validateAddress(Mockito.anyObject())).thenReturn(addressValidateResponse);	
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.getTDSPFromESID(Mockito.anyObject())).thenReturn(response);
		
		when(oeDomainProxy.getOEDomainProxyClient()).thenReturn(oeDomain);
		when(oeDomain.checkPermitRequirment(Mockito.anyObject())).thenReturn(permitCheckResponse);
		
		when(transferServiceDataAccessProxy.getAddressValidationDomainProxyClient()).thenReturn(transferServiceDataAccessDomain);
		when(transferServiceDataAccessDomain.saveNewServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		
		when(oeDomain.checkPermitRequirment(Mockito.anyObject())).thenReturn(permitCheckResponse);
		
		List<EsiidDetails> esiidDetailsList = new ArrayList<EsiidDetails>();
		EsiidDetails esiidDetails = new EsiidDetails();
		esiidDetails.setPermitClass("1");
		esiidDetails.setTdsp("D0001");
		
		esiidDetailsList.add(esiidDetails);
		
		
		Map<String, Object> storedProcResult = new HashMap<String, Object>();
		storedProcResult.put(TOSConstants.CONST_OUT_ROWSET, esiidDetailsList);
		
		
		when(storedProcedure.createStoredProcedure(Mockito.anyObject(), Mockito.anyString(),Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject(),Mockito.anyString())).thenReturn(baseStoredProcedure);
		when(baseStoredProcedure.execute()).thenReturn(storedProcResult);
		
		
		
		this.mockMvc.perform(post("/tos/VerifyAddress")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("contractAccountNumber", "000009695620")
				.param("contractId", "0049016119")
				.param("businessPartnerNumber", "0006094527")
				.param("unitNum", "212")
				.param("streetNum", "2005")
				.param("streetName", "S Mason RD")
				.param("city", "Katy")
				.param("state", "tx")
				.param("zipcode", "77450")
				.param("country", "US")
				.param("billingUnitNum", "212")
				.param("billingStreetNum", "2005")
				.param("billingStreetName", "S Mason RD")
				.param("billingCity", "Katy")
				.param("billingState", "Tx")
				.param("billingCountry", "77450")
				.param("country", "US")
				.param("billingAddressSame", "false")
				.param("houseType", "TOWNHOUSE_CONDO")
				.param("newConstruction", "false"))
				.andExpect(status().isOk());
	
		
	}
	
	@Test
	public void testTosAddressVerify_NOEsiid_MSG_ADDR_NO_MATCH() throws Exception {
		AddressValidateResponse addressValidateResponse = getAddressValidateResponse();
		addressValidateResponse.setErrorMessage("NOT VALID");
		addressValidateResponse.setMatchStatusFlag("MSG_ADDR_NO_MATCH");
		
		//addressValidateResponse.setErrorCode("400");
		List<EsiidDetails> esiidDetailLists =getNOEsiidDetails();
		
		TdspByESIDResponse response = new TdspByESIDResponse();
		response.setCompanyCode("0121");
		response.setServiceId("D0001");
		TdspByZipResponse tdspByZipRequest = new TdspByZipResponse();
		tdspByZipRequest.setCompanyCode("0121");
		tdspByZipRequest.setServiceId("");
		
		PermitCheckResponse permitCheckResponse = new PermitCheckResponse();
		com.multibrand.domain.PermitCheckDO[] checkDOs = new com.multibrand.domain.PermitCheckDO[1];
		com.multibrand.domain.PermitCheckDO permitDO = new com.multibrand.domain.PermitCheckDO();
		permitDO.setStrPermitType1("NO_PERMIT");
		checkDOs[0] = permitDO;
		permitCheckResponse.setCheckDOs(checkDOs);
		
		com.reliant.dataaccess.service.TransferServiceResponse transferServiceResponse = new com.reliant.dataaccess.service.TransferServiceResponse();
		transferServiceResponse.setOutStatus("1234567");
		com.reliant.dataaccess.service.TransferServiceResponse transferServicePow = new com.reliant.dataaccess.service.TransferServiceResponse();
		
		when(addressProxy.getAddressValidationDomainProxyClient()).thenReturn(addressValidationDomain); 
		when(addressValidationDomain.validateAddress(Mockito.anyObject())).thenReturn(addressValidateResponse);	
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.getTDSPFromESID(Mockito.anyObject())).thenReturn(response);
		
		when(oeDomainProxy.getOEDomainProxyClient()).thenReturn(oeDomain);
		when(oeDomain.checkPermitRequirment(Mockito.anyObject())).thenReturn(permitCheckResponse);
		
		when(transferServiceDataAccessProxy.getAddressValidationDomainProxyClient()).thenReturn(transferServiceDataAccessDomain);
		when(transferServiceDataAccessDomain.saveNewServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		
		when(oeDomain.checkPermitRequirment(Mockito.anyObject())).thenReturn(permitCheckResponse);
		
		this.mockMvc.perform(post("/tos/VerifyAddress")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("contractAccountNumber", "000009695620")
				.param("contractId", "0049016119")
				.param("businessPartnerNumber", "0006094527")
				.param("unitNum", "212")
				.param("streetNum", "2005")
				.param("streetName", "S Mason RD")
				.param("city", "Katy")
				.param("state", "tx")
				.param("zipcode", "77450")
				.param("country", "US")
				.param("billingUnitNum", "212")
				.param("billingStreetNum", "2005")
				.param("billingStreetName", "S Mason RD")
				.param("billingCity", "Katy")
				.param("billingState", "Tx")
				.param("billingCountry", "77450")
				.param("country", "US")
				.param("billingAddressSame", "false")
				.param("houseType", "TOWNHOUSE_CONDO")
				.param("newConstruction", "false"))
				.andExpect(status().isOk());
	
		
	}
	
	
	@Test
	public void testTosAddressVerify_NOEsiid_ErrorCode_NotBlank() throws Exception {
		AddressValidateResponse addressValidateResponse = getAddressValidateResponse();
		addressValidateResponse.setErrorMessage("NOT VALID");
		addressValidateResponse.setErrorCode("400");
		addressValidateResponse.setMatchStatusFlag("MSG_ADDR_NO_MATCH");
		
		//addressValidateResponse.setErrorCode("400");
		List<EsiidDetails> esiidDetailLists =getNOEsiidDetails();
		
		TdspByESIDResponse response = new TdspByESIDResponse();
		response.setCompanyCode("0121");
		response.setServiceId("D0001");
		TdspByZipResponse tdspByZipRequest = new TdspByZipResponse();
		tdspByZipRequest.setCompanyCode("0121");
		tdspByZipRequest.setServiceId("");
		
		PermitCheckResponse permitCheckResponse = new PermitCheckResponse();
		com.multibrand.domain.PermitCheckDO[] checkDOs = new com.multibrand.domain.PermitCheckDO[1];
		com.multibrand.domain.PermitCheckDO permitDO = new com.multibrand.domain.PermitCheckDO();
		permitDO.setStrPermitType1("NO_PERMIT");
		checkDOs[0] = permitDO;
		permitCheckResponse.setCheckDOs(checkDOs);
		
		com.reliant.dataaccess.service.TransferServiceResponse transferServiceResponse = new com.reliant.dataaccess.service.TransferServiceResponse();
		transferServiceResponse.setOutStatus("1234567");
		com.reliant.dataaccess.service.TransferServiceResponse transferServicePow = new com.reliant.dataaccess.service.TransferServiceResponse();
		
		when(addressProxy.getAddressValidationDomainProxyClient()).thenReturn(addressValidationDomain); 
		when(addressValidationDomain.validateAddress(Mockito.anyObject())).thenReturn(addressValidateResponse);	
		when(choiceWbDAOImpl.getEsiidDetails(Mockito.anyObject())).thenReturn(esiidDetailLists);
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.getTDSPFromESID(Mockito.anyObject())).thenReturn(response);
		
		when(oeDomainProxy.getOEDomainProxyClient()).thenReturn(oeDomain);
		when(oeDomain.checkPermitRequirment(Mockito.anyObject())).thenReturn(permitCheckResponse);
		
		when(transferServiceDataAccessProxy.getAddressValidationDomainProxyClient()).thenReturn(transferServiceDataAccessDomain);
		when(transferServiceDataAccessDomain.saveNewServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		
		when(oeDomain.checkPermitRequirment(Mockito.anyObject())).thenReturn(permitCheckResponse);
		
		this.mockMvc.perform(post("/tos/VerifyAddress")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("contractAccountNumber", "000009695620")
				.param("contractId", "0049016119")
				.param("businessPartnerNumber", "0006094527")
				.param("unitNum", "212")
				.param("streetNum", "2005")
				.param("streetName", "S Mason RD")
				.param("city", "Katy")
				.param("state", "tx")
				.param("zipcode", "77450")
				.param("country", "US")
				.param("billingUnitNum", "212")
				.param("billingStreetNum", "2005")
				.param("billingStreetName", "S Mason RD")
				.param("billingCity", "Katy")
				.param("billingState", "Tx")
				.param("billingCountry", "77450")
				.param("country", "US")
				.param("billingAddressSame", "false")
				.param("houseType", "TOWNHOUSE_CONDO")
				.param("newConstruction", "false"))
				.andExpect(status().isOk());
	
		
	}
	
	
	@Test
	public void testDisplayTransferDatesTosPreProcessing() throws Exception  {
		
		EsidProfileResponse esidProfileResponse = new EsidProfileResponse();
		esidProfileResponse.setMeterType("AMSR");
		esidProfileResponse.setSwitchHoldStatus("");
		esidProfileResponse.setESID("10443720001032191");
		esidProfileResponse.setPremiseType("Residential");
		esidProfileResponse.setEsidStatus("Active");
		
		
		CheckPendingMVOResponse checkPendingMVOResponse = new CheckPendingMVOResponse();
		checkPendingMVOResponse.setAmbDeferredBalance(new BigDecimal("0"));
		checkPendingMVOResponse.setForcedMoveOut("");
		checkPendingMVOResponse.setMoveOutDate("2021-03-05");
		checkPendingMVOResponse.setTosAMBenrollment("");
		checkPendingMVOResponse.setPendingMoveOutExists("X");
		
		when(oeDomainProxy.getOEDomainProxyClient()).thenReturn(oeDomain);
		when(oeDomain.getTDSPSpecificCalendarDates(Mockito.anyObject())).thenReturn(
				"03/06/2021|03/07/2021|03/13/2021|03/14/2021|03/20/2021|03/21/2021|03/27/2021|03/28/2021|04/02/2021|04/03/2021|04/04/2021|04/10/2021|04/11/2021|04/17/2021|04/18/2021|04/24/2021|04/25/2021|05/01/2021|05/02/2021");		
		when(profileDomainProxy.getProfileDomainProxyClient()).thenReturn(profileDomain);
		when(profileDomain.getESIDProfile(Mockito.anyObject(), Mockito.anyObject())).thenReturn(esidProfileResponse);
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.checkPendingMoveOut(Mockito.anyObject())).thenReturn(checkPendingMVOResponse);
		
		this.mockMvc.perform(post("/tos/tosDisplayTransferDates")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("contractAccountNumber", "000074228768")
				.param("contractId", "0071399859")
				.param("businessPartnerNumber", "6005802361")
				.param("newEsiId", "10443720001032191")
				.param("oldEsiId", "D0002")
				.param("newTdsp", "D0002")
				.param("permitType", "NO_PERMIT"))
				.andExpect(status().isOk());
		
		
	}
	
	
	@Test
	public void testDisplayTransferDatesTosPreProcessing_EsiidNotfound() throws Exception  {
		
		EsidProfileResponse esidProfileResponse = new EsidProfileResponse();
		esidProfileResponse.setMeterType("AMSR");
		esidProfileResponse.setSwitchHoldStatus("");
		esidProfileResponse.setESID("10443720001032191");
		esidProfileResponse.setPremiseType("Residential");
		esidProfileResponse.setEsidStatus("Active");
		
		
		CheckPendingMVOResponse checkPendingMVOResponse = new CheckPendingMVOResponse();
		checkPendingMVOResponse.setAmbDeferredBalance(new BigDecimal("0"));
		checkPendingMVOResponse.setForcedMoveOut("");
		checkPendingMVOResponse.setMoveOutDate("");
		checkPendingMVOResponse.setTosAMBenrollment("");
		checkPendingMVOResponse.setPendingMoveOutExists("X");
		
		when(oeDomainProxy.getOEDomainProxyClient()).thenReturn(oeDomain);
		when(oeDomain.getTDSPSpecificCalendarDates(Mockito.anyObject())).thenReturn(
				"03/06/2021|03/07/2021|03/13/2021|03/14/2021|03/20/2021|03/21/2021|03/27/2021|03/28/2021|04/02/2021|04/03/2021|04/04/2021|04/10/2021|04/11/2021|04/17/2021|04/18/2021|04/24/2021|04/25/2021|05/01/2021|05/02/2021");		
		when(profileDomainProxy.getProfileDomainProxyClient()).thenReturn(profileDomain);
		when(profileDomain.getESIDProfile(Mockito.anyObject(), Mockito.anyObject())).thenReturn(esidProfileResponse);
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.checkPendingMoveOut(Mockito.anyObject())).thenReturn(checkPendingMVOResponse);
		
		this.mockMvc.perform(post("/tos/tosDisplayTransferDates")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("contractAccountNumber", "000074228768")
				.param("contractId", "0071399859")
				.param("businessPartnerNumber", "6005802361")
				.param("newEsiId", "<ESIDNOTFOUND>")
				.param("oldEsiId", "D0002")
				.param("newTdsp", "D0002")
				.param("permitType", "GENERIC"))
				.andExpect(status().isOk());
		
		
	}
	
	@Test
	public void testDisplayTransferDatesTosPreProcessing_PermitType_GENERIC() throws Exception  {
		
		EsidProfileResponse esidProfileResponse = new EsidProfileResponse();
		esidProfileResponse.setMeterType("AMSR");
		esidProfileResponse.setSwitchHoldStatus("");
		esidProfileResponse.setESID("10443720001032191");
		esidProfileResponse.setPremiseType("Residential");
		esidProfileResponse.setEsidStatus("Active");
		
		
		CheckPendingMVOResponse checkPendingMVOResponse = new CheckPendingMVOResponse();
		checkPendingMVOResponse.setAmbDeferredBalance(new BigDecimal("0"));
		checkPendingMVOResponse.setForcedMoveOut("");
		checkPendingMVOResponse.setMoveOutDate("");
		checkPendingMVOResponse.setTosAMBenrollment("");
		checkPendingMVOResponse.setPendingMoveOutExists("X");
		
		when(oeDomainProxy.getOEDomainProxyClient()).thenReturn(oeDomain);
		when(oeDomain.getTDSPSpecificCalendarDates(Mockito.anyObject())).thenReturn(
				"03/06/2021|03/07/2021|03/13/2021|03/14/2021|03/20/2021|03/21/2021|03/27/2021|03/28/2021|04/02/2021|04/03/2021|04/04/2021|04/10/2021|04/11/2021|04/17/2021|04/18/2021|04/24/2021|04/25/2021|05/01/2021|05/02/2021");		
		when(profileDomainProxy.getProfileDomainProxyClient()).thenReturn(profileDomain);
		when(profileDomain.getESIDProfile(Mockito.anyObject(), Mockito.anyObject())).thenReturn(esidProfileResponse);
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.checkPendingMoveOut(Mockito.anyObject())).thenReturn(checkPendingMVOResponse);
		
		this.mockMvc.perform(post("/tos/tosDisplayTransferDates")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("contractAccountNumber", "000074228768")
				.param("contractId", "0071399859")
				.param("businessPartnerNumber", "6005802361")
				.param("newEsiId", "")
				.param("oldEsiId", "D0002")
				.param("newTdsp", "D0002")
				.param("permitType", "GENERIC"))
				.andExpect(status().isOk());
		
		
	}
	
	
	@Test
	public void testStartTosPreCheck() throws Exception  {
		
		CheckPendingMVOResponse checkPendingMVOResponse = new CheckPendingMVOResponse();
		checkPendingMVOResponse.setAmbDeferredBalance(new BigDecimal("0"));
		checkPendingMVOResponse.setForcedMoveOut("");
		checkPendingMVOResponse.setMoveOutDate("2021-03-05");
		checkPendingMVOResponse.setTosAMBenrollment("");
		checkPendingMVOResponse.setPendingMoveOutExists("X");
		
		TdspByESIDResponse tdspByESIDResponse = new TdspByESIDResponse();
		tdspByESIDResponse.setCompanyCode("0121");
		tdspByESIDResponse.setServiceId("D0002");
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.checkPendingMoveOut(Mockito.anyObject())).thenReturn(checkPendingMVOResponse);
		when(tosDomain.getTDSPFromESID(Mockito.anyObject())).thenReturn(tdspByESIDResponse);
		
		when(oeDomainProxy.getOEDomainProxyClient()).thenReturn(oeDomain);
		when(oeDomain.getTDSPSpecificCalendarDates(Mockito.anyObject())).thenReturn(getCurrentDate());		
		
		this.mockMvc.perform(post("/tos/tosPreCheckConditions")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("contractAccountNumber", "000074228768")
				.param("contractId", "0071399859")
				.param("businessPartnerNumber", "6005802361")
				.param("newEsiId", "10443720001032191")
				.param("oldEsiId", "D0002")
				.param("newTdsp", "D0002")
				.param("permitType", "GENERIC"))
				.andExpect(status().isOk());
		
	}
	
	public static String getCurrentDate() {
	    DateTimeFormatter oldPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz");
	    DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    LocalDateTime datetime = LocalDateTime.parse(new Date().toInstant().toString(), oldPattern);
	    return datetime.format(newPattern);
	}
	
	
	@Test
	public void testSubmit() throws Exception  {
		OfferOfContractResponse offerOfContractResponse = new OfferOfContractResponse();
		offerOfContractResponse.setCampaignCode("19A1AR02V0");
		offerOfContractResponse.setOfferCellTrackingCode("XA03B9");
		offerOfContractResponse.setOfferCode("50856566");
		
	  
		TransferServiceResponse transferServiceResponse = new TransferServiceResponse();
		transferServiceResponse.setOutStatus("OK");
		com.multibrand.domain.TransferServiceResponse transferServiceResponseCCS = new com.multibrand.domain.TransferServiceResponse();
		transferServiceResponseCCS.setIDO_number("1234560967");
		transferServiceResponseCCS.setErrorCode("INVALID_ERROR");
		
		CreateContactLogResponse createContactLogResponse = new CreateContactLogResponse();
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.getOfferOfContract(Mockito.anyObject())).thenReturn(offerOfContractResponse);
		when(tosDomain.saveTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponseCCS);
		when(tosDomain.updateContactLog(Mockito.anyObject())).thenReturn(createContactLogResponse);
		
		when(transferServiceDataAccessProxy.getAddressValidationDomainProxyClient()).thenReturn(transferServiceDataAccessDomain);
		when(transferServiceDataAccessDomain.saveTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		when(transferServiceDataAccessDomain.updateTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		
		this.mockMvc.perform(post("/tos/submitTos")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
		.param("unitNum" ,"1315")
		.param("streetNum" ,"3324")
		.param("streetName" ,"CARLTON ST")
		.param("city" ,"FORT WORTH")
		.param("state" ,"TX")
		.param("zipcode" ,"76133")
		.param("zipcodeComplete" ,"76133-8768")
		.param("poBox" ,"")
		.param("country" ,"US")
		.param("houseType" ,"House")
		.param("billingUnitNum" ,"115")
		.param("billingStreetNum" ,"2005")
		.param("billingStreetName" ,"S Mason RD")
		.param("billingCity" ,"KATY")
		.param("billingState" ,"TX")
		.param("billingZipcode" ,"77450")
		.param("billingZipCodeComplete" ,"")
		.param("billingPoBox" ,"")
		.param("billingCountry" ,"US")
		.param("billingAddressSame" ,"false")
		.param("businessPartnerNumber" ,"6005802361")
		.param("contractAccountNumber" ,"000074228768")
		.param("contractNumber" ,"0071399859")
		.param("dbRequestNumber" ,"1173950")
		.param("mviDate" ,"03/06/2021")
		.param("mvoDate" ,"03/08/2022")
		.param("contactName" ,"Siva Murugan") 
		.param("emailAddress" ,"sivamurugan.marimuthu@nrg.com")
		.param("contactPhone" ,"9737677351")
		.param("permitType" ,"NONE OF THE ABOVE")
		.param("newTdsp" ,"D0002")
		.param("priorityMoveIn" ,"N")
		.param("newEsiid" ,"10443720001032191")
		.param("localeLanguageCode" ,"E"))
		.andExpect(status().isOk());
	}
	
	
	@Test
	public void testSubmit_IDO_number() throws Exception  {
		OfferOfContractResponse offerOfContractResponse = new OfferOfContractResponse();
		offerOfContractResponse.setCampaignCode("19A1AR02V0");
		offerOfContractResponse.setOfferCellTrackingCode("XA03B9");
		offerOfContractResponse.setOfferCode("50856566");
		
	  
		TransferServiceResponse transferServiceResponse = new TransferServiceResponse();
		transferServiceResponse.setOutStatus("OK");
		com.multibrand.domain.TransferServiceResponse transferServiceResponseCCS = new com.multibrand.domain.TransferServiceResponse();
		transferServiceResponseCCS.setIDO_number("1234560967");
		//transferServiceResponseCCS.setErrorCode("INVALID_ERROR");
		
		CreateContactLogResponse createContactLogResponse = new CreateContactLogResponse();
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.getOfferOfContract(Mockito.anyObject())).thenReturn(offerOfContractResponse);
		when(tosDomain.saveTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponseCCS);
		when(tosDomain.updateContactLog(Mockito.anyObject())).thenReturn(createContactLogResponse);
		
		when(transferServiceDataAccessProxy.getAddressValidationDomainProxyClient()).thenReturn(transferServiceDataAccessDomain);
		when(transferServiceDataAccessDomain.saveTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		when(transferServiceDataAccessDomain.updateTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		
		this.mockMvc.perform(post("/tos/submitTos")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
		.param("unitNum" ,"1315")
		.param("streetNum" ,"3324")
		.param("streetName" ,"CARLTON ST")
		.param("city" ,"FORT WORTH")
		.param("state" ,"TX")
		.param("zipcode" ,"76133")
		.param("zipcodeComplete" ,"76133-8768")
		.param("poBox" ,"")
		.param("country" ,"US")
		.param("houseType" ,"House")
		.param("billingUnitNum" ,"115")
		.param("billingStreetNum" ,"2005")
		.param("billingStreetName" ,"S Mason RD")
		.param("billingCity" ,"KATY")
		.param("billingState" ,"TX")
		.param("billingZipcode" ,"77450")
		.param("billingZipCodeComplete" ,"")
		.param("billingPoBox" ,"")
		.param("billingCountry" ,"US")
		.param("billingAddressSame" ,"false")
		.param("businessPartnerNumber" ,"6005802361")
		.param("contractAccountNumber" ,"000074228768")
		.param("contractNumber" ,"0071399859")
		.param("dbRequestNumber" ,"1173950")
		.param("mviDate" ,"03/06/2021")
		.param("mvoDate" ,"03/08/2022")
		.param("contactName" ,"Siva Murugan") 
		.param("emailAddress" ,"sivamurugan.marimuthu@nrg.com")
		.param("contactPhone" ,"9737677351")
		.param("permitType" ,"NONE OF THE ABOVE")
		.param("newTdsp" ,"D0002")
		.param("priorityMoveIn" ,"N")
		.param("newEsiid" ,"10443720001032191")
		.param("localeLanguageCode" ,"E"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testSubmit1() throws Exception  {
		OfferOfContractResponse offerOfContractResponse = new OfferOfContractResponse();
		offerOfContractResponse.setCampaignCode("19A1AR02V0");
		offerOfContractResponse.setOfferCellTrackingCode("XA03B9");
		offerOfContractResponse.setOfferCode("50856566");
		
	  
		TransferServiceResponse transferServiceResponse = new TransferServiceResponse();
		transferServiceResponse.setOutStatus("OK");
		com.multibrand.domain.TransferServiceResponse transferServiceResponseCCS = new com.multibrand.domain.TransferServiceResponse();
		transferServiceResponseCCS.setIDO_number("");
		//transferServiceResponseCCS.setErrorCode("INVALID_ERROR");
		
		CreateContactLogResponse createContactLogResponse = new CreateContactLogResponse();
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.getOfferOfContract(Mockito.anyObject())).thenReturn(offerOfContractResponse);
		when(tosDomain.saveTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponseCCS);
		when(tosDomain.updateContactLog(Mockito.anyObject())).thenReturn(createContactLogResponse);
		
		when(transferServiceDataAccessProxy.getAddressValidationDomainProxyClient()).thenReturn(transferServiceDataAccessDomain);
		when(transferServiceDataAccessDomain.saveTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		when(transferServiceDataAccessDomain.updateTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		
		this.mockMvc.perform(post("/tos/submitTos")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
		.param("unitNum" ,"1315")
		.param("streetNum" ,"3324")
		.param("streetName" ,"CARLTON ST")
		.param("city" ,"FORT WORTH")
		.param("state" ,"TX")
		.param("zipcode" ,"76133")
		.param("zipcodeComplete" ,"76133-8768")
		.param("poBox" ,"")
		.param("country" ,"US")
		.param("houseType" ,"House")
		.param("billingUnitNum" ,"115")
		.param("billingStreetNum" ,"2005")
		.param("billingStreetName" ,"S Mason RD")
		.param("billingCity" ,"KATY")
		.param("billingState" ,"TX")
		.param("billingZipcode" ,"77450")
		.param("billingZipCodeComplete" ,"")
		.param("billingPoBox" ,"")
		.param("billingCountry" ,"US")
		.param("billingAddressSame" ,"false")
		.param("businessPartnerNumber" ,"6005802361")
		.param("contractAccountNumber" ,"000074228768")
		.param("contractNumber" ,"0071399859")
		.param("dbRequestNumber" ,"1173950")
		.param("mviDate" ,"03/06/2021")
		.param("mvoDate" ,"03/08/2022")
		.param("contactName" ,"Siva Murugan") 
		.param("emailAddress" ,"sivamurugan.marimuthu@nrg.com")
		.param("contactPhone" ,"9737677351")
		.param("permitType" ,"NONE OF THE ABOVE")
		.param("newTdsp" ,"D0002")
		.param("priorityMoveIn" ,"N")
		.param("newEsiid" ,"10443720001032191")
		.param("localeLanguageCode" ,"E"))
		.andExpect(status().isOk());
	}
	
	
	@Test
	public void testSubmit_langcode_ES_US() throws Exception  {
		OfferOfContractResponse offerOfContractResponse = new OfferOfContractResponse();
		offerOfContractResponse.setCampaignCode("19A1AR02V0");
		offerOfContractResponse.setOfferCellTrackingCode("XA03B9");
		offerOfContractResponse.setOfferCode("50856566");
	  
		TransferServiceResponse transferServiceResponse = new TransferServiceResponse();
		transferServiceResponse.setOutStatus("OK");
		com.multibrand.domain.TransferServiceResponse transferServiceResponseCCS = new com.multibrand.domain.TransferServiceResponse();
		transferServiceResponseCCS.setIDO_number("1234560967");
		
		CreateContactLogResponse createContactLogResponse = new CreateContactLogResponse();
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.getOfferOfContract(Mockito.anyObject())).thenReturn(offerOfContractResponse);
		when(tosDomain.saveTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponseCCS);
		when(tosDomain.updateContactLog(Mockito.anyObject())).thenReturn(createContactLogResponse);
		
		when(transferServiceDataAccessProxy.getAddressValidationDomainProxyClient()).thenReturn(transferServiceDataAccessDomain);
		when(transferServiceDataAccessDomain.saveTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		when(transferServiceDataAccessDomain.updateTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		
		this.mockMvc.perform(post("/tos/submitTos")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
		.param("unitNum" ,"1315")
		.param("streetNum" ,"3324")
		.param("streetName" ,"CARLTON ST")
		.param("city" ,"FORT WORTH")
		.param("state" ,"TX")
		.param("zipcode" ,"76133")
		.param("zipcodeComplete" ,"76133-8768")
		.param("poBox" ,"")
		.param("country" ,"US")
		.param("houseType" ,"House")
		.param("billingUnitNum" ,"115")
		.param("billingStreetNum" ,"2005")
		.param("billingStreetName" ,"S Mason RD")
		.param("billingCity" ,"KATY")
		.param("billingState" ,"TX")
		.param("billingZipcode" ,"77450")
		.param("billingZipCodeComplete" ,"")
		.param("billingPoBox" ,"")
		.param("billingCountry" ,"US")
		.param("billingAddressSame" ,"false")
		.param("businessPartnerNumber" ,"6005802361")
		.param("contractAccountNumber" ,"000074228768")
		.param("contractNumber" ,"0071399859")
		.param("dbRequestNumber" ,"1173950")
		.param("mviDate" ,"03/06/2021")
		.param("mvoDate" ,"03/08/2022")
		.param("contactName" ,"Siva Murugan") 
		.param("emailAddress" ,"sivamurugan.marimuthu@nrg.com")
		.param("contactPhone" ,"9737677351")
		.param("permitType" ,"NONE OF THE ABOVE")
		.param("newTdsp" ,"D0002")
		.param("priorityMoveIn" ,"N")
		.param("newEsiid" ,"10443720001032191")
		.param("localeLanguageCode" ,"es_US"))
		.andExpect(status().isOk());
	}
	
	
	@Test
	public void testSubmit_ErrorCode_NOTBLANK() throws Exception  {
		OfferOfContractResponse offerOfContractResponse = new OfferOfContractResponse();
		offerOfContractResponse.setCampaignCode("19A1AR02V0");
		offerOfContractResponse.setOfferCellTrackingCode("XA03B9");
		offerOfContractResponse.setOfferCode("50856566");
		offerOfContractResponse.setErrCode("INVALID_ERROR");
	  
		TransferServiceResponse transferServiceResponse = new TransferServiceResponse();
		transferServiceResponse.setOutStatus("OK");
		com.multibrand.domain.TransferServiceResponse transferServiceResponseCCS = new com.multibrand.domain.TransferServiceResponse();
		transferServiceResponseCCS.setIDO_number("1234560967");
		
		CreateContactLogResponse createContactLogResponse = new CreateContactLogResponse();
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.getOfferOfContract(Mockito.anyObject())).thenReturn(offerOfContractResponse);
		when(tosDomain.saveTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponseCCS);
		when(tosDomain.updateContactLog(Mockito.anyObject())).thenReturn(createContactLogResponse);
		
		when(transferServiceDataAccessProxy.getAddressValidationDomainProxyClient()).thenReturn(transferServiceDataAccessDomain);
		when(transferServiceDataAccessDomain.saveTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		when(transferServiceDataAccessDomain.updateTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		
		this.mockMvc.perform(post("/tos/submitTos")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
		.param("unitNum" ,"1315")
		.param("streetNum" ,"3324")
		.param("streetName" ,"CARLTON ST")
		.param("city" ,"FORT WORTH")
		.param("state" ,"TX")
		.param("zipcode" ,"76133")
		.param("zipcodeComplete" ,"76133-8768")
		.param("poBox" ,"")
		.param("country" ,"US")
		.param("houseType" ,"House")
		.param("billingUnitNum" ,"115")
		.param("billingStreetNum" ,"2005")
		.param("billingStreetName" ,"S Mason RD")
		.param("billingCity" ,"KATY")
		.param("billingState" ,"TX")
		.param("billingZipcode" ,"77450")
		.param("billingZipCodeComplete" ,"")
		.param("billingPoBox" ,"")
		.param("billingCountry" ,"US")
		.param("billingAddressSame" ,"false")
		.param("businessPartnerNumber" ,"6005802361")
		.param("contractAccountNumber" ,"000074228768")
		.param("contractNumber" ,"0071399859")
		.param("dbRequestNumber" ,"1173950")
		.param("mviDate" ,"03/06/2021")
		.param("mvoDate" ,"03/08/2022")
		.param("contactName" ,"Siva Murugan") 
		.param("emailAddress" ,"sivamurugan.marimuthu@nrg.com")
		.param("contactPhone" ,"9737677351")
		.param("permitType" ,"NONE OF THE ABOVE")
		.param("newTdsp" ,"D0002")
		.param("priorityMoveIn" ,"N")
		.param("newEsiid" ,"10443720001032191")
		.param("localeLanguageCode" ,"es_US"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testSubmit_OUTSTATUS_NOT_OK() throws Exception  {
		OfferOfContractResponse offerOfContractResponse = new OfferOfContractResponse();
		offerOfContractResponse.setCampaignCode("19A1AR02V0");
		offerOfContractResponse.setOfferCellTrackingCode("XA03B9");
		offerOfContractResponse.setOfferCode("50856566");
		//offerOfContractResponse.setErrCode("INVALID_ERROR");
	  
		TransferServiceResponse transferServiceResponse = new TransferServiceResponse();
		transferServiceResponse.setOutStatus("OKK");
		transferServiceResponse.setApperrorMessage("FAUILURE");
		com.multibrand.domain.TransferServiceResponse transferServiceResponseCCS = new com.multibrand.domain.TransferServiceResponse();
		transferServiceResponseCCS.setIDO_number("1234560967");
		
		CreateContactLogResponse createContactLogResponse = new CreateContactLogResponse();
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.getOfferOfContract(Mockito.anyObject())).thenReturn(offerOfContractResponse);
		when(tosDomain.saveTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponseCCS);
		when(tosDomain.updateContactLog(Mockito.anyObject())).thenReturn(createContactLogResponse);
		
		when(transferServiceDataAccessProxy.getAddressValidationDomainProxyClient()).thenReturn(transferServiceDataAccessDomain);
		when(transferServiceDataAccessDomain.saveTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		when(transferServiceDataAccessDomain.updateTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		
		this.mockMvc.perform(post("/tos/submitTos")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
		.param("unitNum" ,"1315")
		.param("streetNum" ,"3324")
		.param("streetName" ,"CARLTON ST")
		.param("city" ,"FORT WORTH")
		.param("state" ,"TX")
		.param("zipcode" ,"76133")
		.param("zipcodeComplete" ,"76133-8768")
		.param("poBox" ,"")
		.param("country" ,"US")
		.param("houseType" ,"House")
		.param("billingUnitNum" ,"115")
		.param("billingStreetNum" ,"2005")
		.param("billingStreetName" ,"S Mason RD")
		.param("billingCity" ,"KATY")
		.param("billingState" ,"TX")
		.param("billingZipcode" ,"77450")
		.param("billingZipCodeComplete" ,"")
		.param("billingPoBox" ,"")
		.param("billingCountry" ,"US")
		.param("billingAddressSame" ,"false")
		.param("businessPartnerNumber" ,"6005802361")
		.param("contractAccountNumber" ,"000074228768")
		.param("contractNumber" ,"0071399859")
		.param("dbRequestNumber" ,"1173950")
		.param("mviDate" ,"03/06/2021")
		.param("mvoDate" ,"03/08/2022")
		.param("contactName" ,"Siva Murugan") 
		.param("emailAddress" ,"sivamurugan.marimuthu@nrg.com")
		.param("contactPhone" ,"9737677351")
		.param("permitType" ,"NONE OF THE ABOVE")
		.param("newTdsp" ,"D0002")
		.param("priorityMoveIn" ,"N")
		.param("newEsiid" ,"10443720001032191")
		.param("localeLanguageCode" ,"es_US"))
		.andExpect(status().isOk());
	}
	
	
	@Test
	public void testSubmit__UpdateService_OUTSTATUS_NOT_OK() throws Exception  {
		OfferOfContractResponse offerOfContractResponse = new OfferOfContractResponse();
		offerOfContractResponse.setCampaignCode("19A1AR02V0");
		offerOfContractResponse.setOfferCellTrackingCode("XA03B9");
		offerOfContractResponse.setOfferCode("50856566");
		//offerOfContractResponse.setErrCode("INVALID_ERROR");
	  
		TransferServiceResponse transferServiceResponse = new TransferServiceResponse();
		transferServiceResponse.setOutStatus("OK");
		transferServiceResponse.setApperrorMessage("FAUILURE");
		com.multibrand.domain.TransferServiceResponse transferServiceResponseCCS = new com.multibrand.domain.TransferServiceResponse();
		transferServiceResponseCCS.setIDO_number("1234560967");
		
		TransferServiceResponse transferServiceResponseUpdate = new TransferServiceResponse();
		transferServiceResponseUpdate.setOutStatus("OKK");
		
		CreateContactLogResponse createContactLogResponse = new CreateContactLogResponse();
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.getOfferOfContract(Mockito.anyObject())).thenReturn(offerOfContractResponse);
		when(tosDomain.saveTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponseCCS);
		when(tosDomain.updateContactLog(Mockito.anyObject())).thenReturn(createContactLogResponse);
		
		when(transferServiceDataAccessProxy.getAddressValidationDomainProxyClient()).thenReturn(transferServiceDataAccessDomain);
		when(transferServiceDataAccessDomain.saveTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		when(transferServiceDataAccessDomain.updateTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponseUpdate);
		
		this.mockMvc.perform(post("/tos/submitTos")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
		.param("unitNum" ,"1315")
		.param("streetNum" ,"3324")
		.param("streetName" ,"CARLTON ST")
		.param("city" ,"FORT WORTH")
		.param("state" ,"TX")
		.param("zipcode" ,"76133")
		.param("zipcodeComplete" ,"76133-8768")
		.param("poBox" ,"")
		.param("country" ,"US")
		.param("houseType" ,"House")
		.param("billingUnitNum" ,"115")
		.param("billingStreetNum" ,"2005")
		.param("billingStreetName" ,"S Mason RD")
		.param("billingCity" ,"KATY")
		.param("billingState" ,"TX")
		.param("billingZipcode" ,"77450")
		.param("billingZipCodeComplete" ,"")
		.param("billingPoBox" ,"")
		.param("billingCountry" ,"US")
		.param("billingAddressSame" ,"false")
		.param("businessPartnerNumber" ,"6005802361")
		.param("contractAccountNumber" ,"000074228768")
		.param("contractNumber" ,"0071399859")
		.param("dbRequestNumber" ,"1173950")
		.param("mviDate" ,"03/06/2021")
		.param("mvoDate" ,"03/08/2022")
		.param("contactName" ,"Siva Murugan") 
		.param("emailAddress" ,"sivamurugan.marimuthu@nrg.com")
		.param("contactPhone" ,"9737677351")
		.param("permitType" ,"NONE OF THE ABOVE")
		.param("newTdsp" ,"D0002")
		.param("priorityMoveIn" ,"N")
		.param("newEsiid" ,"10443720001032191")
		.param("localeLanguageCode" ,"es_US"))
		.andExpect(status().isOk());
	}
	
	
	public void testSubmit__UpdateService_OUTSTATUS_OK() throws Exception  {
		OfferOfContractResponse offerOfContractResponse = new OfferOfContractResponse();
		offerOfContractResponse.setCampaignCode("19A1AR02V0");
		offerOfContractResponse.setOfferCellTrackingCode("XA03B9");
		offerOfContractResponse.setOfferCode("50856566");
		//offerOfContractResponse.setErrCode("INVALID_ERROR");
	  
		TransferServiceResponse transferServiceResponse = new TransferServiceResponse();
		transferServiceResponse.setOutStatus("OK");
		transferServiceResponse.setApperrorMessage("FAUILURE");
		com.multibrand.domain.TransferServiceResponse transferServiceResponseCCS = new com.multibrand.domain.TransferServiceResponse();
		transferServiceResponseCCS.setIDO_number("1234560967");
		
		TransferServiceResponse transferServiceResponseUpdate = new TransferServiceResponse();
		transferServiceResponseUpdate.setOutStatus("OK");
		
		CreateContactLogResponse createContactLogResponse = new CreateContactLogResponse();
		
		when(tosDomainProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosProxy.getTOSDomainProxyClient()).thenReturn(tosDomain);
		when(tosDomain.getOfferOfContract(Mockito.anyObject())).thenReturn(offerOfContractResponse);
		when(tosDomain.saveTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponseCCS);
		when(tosDomain.updateContactLog(Mockito.anyObject())).thenReturn(createContactLogResponse);
		
		when(transferServiceDataAccessProxy.getAddressValidationDomainProxyClient()).thenReturn(transferServiceDataAccessDomain);
		when(transferServiceDataAccessDomain.saveTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponse);
		when(transferServiceDataAccessDomain.updateTransferServiceDetails(Mockito.anyObject())).thenReturn(transferServiceResponseUpdate);
		
		this.mockMvc.perform(post("/tos/submitTos")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
		.param("unitNum" ,"1315")
		.param("streetNum" ,"3324")
		.param("streetName" ,"CARLTON ST")
		.param("city" ,"FORT WORTH")
		.param("state" ,"TX")
		.param("zipcode" ,"76133")
		.param("zipcodeComplete" ,"76133-8768")
		.param("poBox" ,"")
		.param("country" ,"US")
		.param("houseType" ,"House")
		.param("billingUnitNum" ,"115")
		.param("billingStreetNum" ,"2005")
		.param("billingStreetName" ,"S Mason RD")
		.param("billingCity" ,"KATY")
		.param("billingState" ,"TX")
		.param("billingZipcode" ,"77450")
		.param("billingZipCodeComplete" ,"")
		.param("billingPoBox" ,"")
		.param("billingCountry" ,"US")
		.param("billingAddressSame" ,"false")
		.param("businessPartnerNumber" ,"6005802361")
		.param("contractAccountNumber" ,"000074228768")
		.param("contractNumber" ,"0071399859")
		.param("dbRequestNumber" ,"1173950")
		.param("mviDate" ,"03/06/2021")
		.param("mvoDate" ,"03/08/2022")
		.param("contactName" ,"Siva Murugan") 
		.param("emailAddress" ,"sivamurugan.marimuthu@nrg.com")
		.param("contactPhone" ,"9737677351")
		.param("permitType" ,"NONE OF THE ABOVE")
		.param("newTdsp" ,"D0002")
		.param("priorityMoveIn" ,"N")
		.param("newEsiid" ,"10443720001032191")
		.param("localeLanguageCode" ,"es_US"))
		.andExpect(status().isOk());
	}*/




}
