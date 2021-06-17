package com.multibrand.resource;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.multibrand.bo.OEBO;
import com.multibrand.bo.TOSBO;
import com.multibrand.dao.AddressDAOIF;
import com.multibrand.dao.ResultObject;
import com.multibrand.domain.CheckPendingMVORequest;
import com.multibrand.domain.CheckPendingMVOResponse;
import com.multibrand.domain.CheckPendingMoveInRequest;
import com.multibrand.domain.CheckPendingMoveInResponse;
import com.multibrand.domain.ContractDataRequest;
import com.multibrand.domain.ContractDataResponse;
import com.multibrand.domain.CreateContactLogRequest;
import com.multibrand.domain.CreateContactLogResponse;
import com.multibrand.domain.EsidProfileResponse;
import com.multibrand.domain.OetdspRequest;
import com.multibrand.domain.OfferOfContractRequest;
import com.multibrand.domain.OfferOfContractResponse;
import com.multibrand.domain.PermitCheckRequest;
import com.multibrand.domain.PermitCheckResponse;
import com.multibrand.domain.ProgramAccountInfoRequest;
import com.multibrand.domain.ProgramAccountInfoResponse;
import com.multibrand.domain.TOSDomain;
import com.multibrand.domain.TdspByESIDResponse;
import com.multibrand.domain.TdspByZipResponse;
import com.multibrand.domain.TosAmbWebRequest;
import com.multibrand.domain.TosAmbWebResponse;
import com.multibrand.domain.TransferServiceRequest;
import com.multibrand.domain.TransferServiceResponse;
import com.multibrand.manager.BaseStoredProcedure;
import com.multibrand.manager.StoredProcedureManager;
import com.multibrand.resources.TOSResource;
import com.multibrand.service.OEService;
import com.multibrand.service.ProfileService;
import com.multibrand.service.TOSService;
import com.multibrand.vo.request.TOSEligibleNonEligibleProductsRequest;
import com.multibrand.vo.request.TOSSubmitEligibleProductsRequest;
import com.multibrand.vo.response.TOSEligibleNonEligibleProductsResponse;
import com.multibrand.vo.response.TOSSubmitEligibleProductsResponse;



public class TOSResourceTest extends AbstractJunitTest{
	
	@Autowired
	@InjectMocks
	TOSResource tosResource;
	
	/*@Autowired
	@InjectMocks
	TOSRequestHandler tOSRequestHandler;*/
	
	
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
	
	@Autowired
	@InjectMocks
	OEBO oeBO;
	
	//@Mock
	//TOSBO tosBO;
	
	/*@Mock
	private TOSService tosService;*/
	
	@Mock
	private TOSService tosService;
	
	@Mock
	private OEService oeService;
	
	@Mock
	private ProfileService profileService;
	
	@Mock
	javax.ws.rs.core.Response resp;
	
	@Mock
	BaseStoredProcedure baseStoredProcedure;
	
	@Mock
	StoredProcedureManager storedProcedure;
	
	@Mock
    //@Qualifier("podJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@InjectMocks
	private AddressDAOIF addressDAO;
	
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
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		
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
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		Mockito.when(tosDomain.checkPendingMVI(Mockito.any(CheckPendingMoveInRequest.class))).thenReturn(chkPndgMovResp);
		
		Mockito.when(tosService.checkingPendingMVI(Mockito.any(CheckPendingMoveInRequest.class), Mockito.any(String.class), Mockito.any(String.class))).thenReturn(chkPndgMovResp);
		resp = tosResource.checkPendingMVI("000070555574", "6010617433", "P1234568", "021");
						
	}
	
	@Test
	public void testGetESIDForAddress() throws Exception {
		
		
		
		//ArgumentCaptor<Map<String, Object>> argCaptor = ArgumentCaptor.forClass(Map.class);
		
		//ArgumentCaptor<Map<String, Integer>> argCaptor1 = ArgumentCaptor.forClass(Map.class);
		
		//ArgumentCaptor<Map<String, ResultObject>> argCaptor2 = ArgumentCaptor.forClass(Map.class);
		Map<String, Object> storedProcResult= new HashMap<String, Object>();
		
		
		Mockito.when(storedProcedure.createStoredProcedure(Mockito.any(JdbcTemplate.class), Mockito.any(String.class), ArgumentMatchers.<Map<String, Object>>any(), ArgumentMatchers.<Map<String, Integer>>any(), ArgumentMatchers.<Map<String, ResultObject>>any(), Mockito.any(String.class))).thenReturn(baseStoredProcedure);
		Mockito.when(baseStoredProcedure.execute()).thenReturn(storedProcResult);
		
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		//Mockito.when(tosDomain.checkPendingMVI(Mockito.any(CheckPendingMoveInRequest.class))).thenReturn(chkPndgMovResp);
		
		//Mockito.when(tosDomain.checkPendingMoveOut(Mockito.any(CheckPendingMVORequest.class))).thenReturn(response);
		//com.multibrand.vo.response.CheckPendingMVOResponse checkPendingMVOResponse = new com.multibrand.vo.response.CheckPendingMVOResponse();
		//Mockito.when(tosService.checkingPendingMVI(Mockito.any(CheckPendingMoveInRequest.class), Mockito.any(String.class), Mockito.any(String.class))).thenReturn(chkPndgMovResp);
		//Mockito.when(tosBO.checkPendingMVO(Mockito.any(CheckPendingMVORequest.class), Mockito.any(String.class), Mockito.any(String.class))).thenReturn(checkPendingMVOResponse);
		resp = tosResource.getESIDForAddress("356", "HOUSTON", "USA", "CALIFORNIA", "BSE", "12", "509103", "021");
						
	}
	
	@Test
	public void testGetOfferOfContract() throws Exception {
		
		OfferOfContractResponse offContratResp = new OfferOfContractResponse();
		offContratResp.setErrCode("400");
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		Mockito.when(tosDomain.getOfferOfContract(Mockito.any(OfferOfContractRequest.class))).thenReturn(offContratResp);
		
		Mockito.when(tosService.getOfferOfContract(Mockito.any(OfferOfContractRequest.class), Mockito.any(String.class))).thenReturn(offContratResp);
		resp = tosResource.getOfferOfContract("000070555574", "021", "EN");
						
	}
	
	@Test
	public void testGetProgramAccountInfo() throws Exception {
		
		ProgramAccountInfoResponse prgAcntInfoResp = new ProgramAccountInfoResponse();
		prgAcntInfoResp.setErrCode("400");
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		//Mockito.when(tosDomain.getProgramAccountInfo(Mockito.any(ProgramAccountInfoRequest.class))).thenReturn(prgAcntInfoResp);
		
		Mockito.when(tosService.getProgramAccountInfo(Mockito.any(ProgramAccountInfoRequest.class), Mockito.any(String.class))).thenReturn(prgAcntInfoResp);
		resp = tosResource.getProgramAccountInfo("6010617433", "000070555574", "0044873760", "021");
						
	}
	@Test
	public void testUpdateContactLog() throws Exception {
		
		CreateContactLogResponse cratContLogResp = new CreateContactLogResponse();
		cratContLogResp.setErrCode("400");
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		//Mockito.when(tosDomain.getProgramAccountInfo(Mockito.any(ProgramAccountInfoRequest.class))).thenReturn(prgAcntInfoResp);
		
		Mockito.when(tosService.updateContactLog(Mockito.any(CreateContactLogRequest.class), Mockito.any(String.class))).thenReturn(cratContLogResp);
		resp = tosResource.updateContactLog("6010617433", "000070555574", "0044873760", "123","Y","Y","D","Text","Y","021");
						
	}
	
	@Test
	public void testGetTdspByZip() throws Exception {
		
		TdspByZipResponse tdspZipResp = new TdspByZipResponse();
		tdspZipResp.setStrErrCode("400");
		tdspZipResp.setStrErrCode("400");
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		//Mockito.when(tosDomain.getProgramAccountInfo(Mockito.any(ProgramAccountInfoRequest.class))).thenReturn(prgAcntInfoResp);
		
		Mockito.when(tosService.ccsGetTDSPFromZip(Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class))).thenReturn(tdspZipResp);
		resp = tosResource.getTdspByZip("509103","021");
						
	}
	
	@Test
	public void testGetTDSPFromESID() throws Exception {
		
		TdspByESIDResponse tdspESIDResp = new TdspByESIDResponse();
		tdspESIDResp.setStrErrCode("400");
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		//Mockito.when(tosDomain.getProgramAccountInfo(Mockito.any(ProgramAccountInfoRequest.class))).thenReturn(prgAcntInfoResp);
		
		Mockito.when(tosService.ccsGetTDSPFromESID(Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class))).thenReturn(tdspESIDResp);
		resp = tosResource.getTDSPFromESID("1008901024900893670114","021");
						
	}
	
	
	@Test
	public void testGetContractData() throws Exception {
		
		ContractDataResponse contDataResp = new ContractDataResponse();
		contDataResp.setErrCode("400");
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		//Mockito.when(tosDomain.getProgramAccountInfo(Mockito.any(ProgramAccountInfoRequest.class))).thenReturn(prgAcntInfoResp);
		
		Mockito.when(tosService.getContractData(Mockito.any(ContractDataRequest.class), Mockito.any(String.class))).thenReturn(contDataResp);
		resp = tosResource.getContractData("6010617433", "1008901024900893670114","021");
	}
						
		 
		 
	@Test
	public void testSaveTransferServiceDetails() throws Exception {
		
		TransferServiceResponse tnfrServcResp = new TransferServiceResponse();
		tnfrServcResp.setErrorCode("400");
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		//Mockito.when(tosDomain.getProgramAccountInfo(Mockito.any(ProgramAccountInfoRequest.class))).thenReturn(prgAcntInfoResp);
		
		Mockito.when(tosService.saveTransferServiceDetails(Mockito.any(TransferServiceRequest.class), Mockito.any(String.class))).thenReturn(tnfrServcResp);
		resp = tosResource.saveTransferServiceDetails("6010617433","000070555574","6","0044873760","Regular", "1008901024900893670114", "103", "USA", "13B","USA", "USA", "103", "USA","509","13B",
				"Nagar","13","USA", "USA","5019103","USA","1205365","12/05/2021", "12/06/2021","Y","Y","ALLINDIA","013","456","12","abc@me.com","Y","Y","Santosh","853621459","12D","IN","021","abc@me.com",
						"EN","Delivery","Program","200","199Plan","Monthly","12","105","url","45","url","EFLID","YRAURL","YRAID","125","Name","45","USA","USA","509103","USA","Y");
						
	}
	
	@Test
	public void testGetTDSPSpecificCalendarDates() throws Exception {
		
		TdspByESIDResponse tdspESIDResp = new TdspByESIDResponse();
		tdspESIDResp.setStrErrCode("400");
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		//Mockito.when(tosDomain.getProgramAccountInfo(Mockito.any(ProgramAccountInfoRequest.class))).thenReturn(prgAcntInfoResp);
		
		Mockito.when(oeService.getTDSPSpecificCalendarDates(Mockito.any(OetdspRequest.class), Mockito.any(String.class))).thenReturn("15/05/2021");
		resp = tosResource.getTDSPSpecificCalendarDates("20/04/2021", "20/06/2021", "TDSP", "1008901024900893670114","021");
						
	}
	
	@Test
	public void testCheckPermitRequirment() throws Exception {
		
		PermitCheckResponse prmtChkResp = new PermitCheckResponse();
		prmtChkResp.setStrErrCode("400");
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		//Mockito.when(tosDomain.getProgramAccountInfo(Mockito.any(ProgramAccountInfoRequest.class))).thenReturn(prgAcntInfoResp);
		
		Mockito.when(oeService.checkPermitRequirment(Mockito.any(PermitCheckRequest.class), Mockito.any(String.class))).thenReturn(prmtChkResp);
		resp = tosResource.checkPermitRequirment("GandhiNagar", "16", "509103","35","WNP","Telanhgana", "509103","INDIA","ALLINDIA","021");
						
	}
	
	@Test
	public void testGetTosAMBDetails() throws Exception {
		
		TosAmbWebResponse tosWebResp = new TosAmbWebResponse();
		tosWebResp.setErrorCode("400");
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		//Mockito.when(tosDomain.getProgramAccountInfo(Mockito.any(ProgramAccountInfoRequest.class))).thenReturn(prgAcntInfoResp);
		
		Mockito.when(tosService.getTosAMBDetails(Mockito.any(TosAmbWebRequest.class), Mockito.any(String.class))).thenReturn(tosWebResp);
		resp = tosResource.getTosAMBDetails("258","1008901024900893670114","000070555574","0044873760","15","30","021");
						
	}
	
	@Test
	public void testGetESIDProfile() throws Exception {
		
		EsidProfileResponse esidProResp = new EsidProfileResponse();
		esidProResp.setErrorCode("400");
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		//Mockito.when(tosDomain.getProgramAccountInfo(Mockito.any(ProgramAccountInfoRequest.class))).thenReturn(prgAcntInfoResp);
		
		Mockito.when(profileService.getESIDProfile(Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class))).thenReturn(esidProResp);
		resp = tosResource.getESIDProfile("1008901024900893670114","021");
						
	}
	
	@Test
	public void testTosEligibleNonEligibleProducts() throws Exception {
		
		TOSEligibleNonEligibleProductsResponse response = new TOSEligibleNonEligibleProductsResponse();
		response.setErrorCode("400");
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		//Mockito.when(tosDomain.getProgramAccountInfo(Mockito.any(ProgramAccountInfoRequest.class))).thenReturn(prgAcntInfoResp);
		
		Mockito.when(tosService.tosEligibleNonEligibleProducts(Mockito.any(TOSEligibleNonEligibleProductsRequest.class))).thenReturn(response);
		
		TOSEligibleNonEligibleProductsRequest tosElgblProj = new TOSEligibleNonEligibleProductsRequest();
		resp = tosResource.tosEligibleNonEligibleProducts(tosElgblProj);
						
	}
	
	@Test
	public void testTosSubmitEligibleProducts() throws Exception {
		
		TOSSubmitEligibleProductsResponse response = new TOSSubmitEligibleProductsResponse();
		response.setErrorCode("400");
		//Mockito.when(tosService.getTOSDomainProxy()).thenReturn(tosDomain);
		//Mockito.when(tosDomain.getProgramAccountInfo(Mockito.any(ProgramAccountInfoRequest.class))).thenReturn(prgAcntInfoResp);
		
		Mockito.when(tosService.tosSubmitEligibleProducts(Mockito.any(TOSSubmitEligibleProductsRequest.class))).thenReturn(response);
		TOSSubmitEligibleProductsRequest request = new TOSSubmitEligibleProductsRequest();
		resp = tosResource.tosSubmitEligibleProducts(request);
						
	}
}
