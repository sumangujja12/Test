package com.multibrand.service;

import static org.junit.Assert.assertTrue;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.multibrand.dto.request.NeiBPCARequest;
import com.multibrand.exception.NRGException;
import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;
import com.nrg.cxfstubs.nei.bpca.ZEISUNEICREATEBPCA;
import com.nrg.cxfstubs.nei.bpca.ZEISUNEICREATEBPCA_Service;
import com.nrg.cxfstubs.nei.bpca.ZEIsuNeiCreateBpCaResponse;

public class NEISimplySmartServiceTest {

	boolean thrown = false;

	@InjectMocks
	public NEISimplySmartService nEISimplySmartService;

	@Spy
	ZEISUNEICREATEBPCA_Service service;

	public ZEISUNEICREATEBPCA stub;

	public NeiBPCARequest neiBPCARequest;

	@Mock
	EnvMessageReader envMessageReader;

	@Mock
	ReloadableResourceBundleMessageSource environmentMessageSource;

	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeMethod
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
		stub = service.getZEISUNEICREATEBPCABIND();

		neiBPCARequest = new NeiBPCARequest();
		neiBPCARequest.setAcctNumber("8759");
		neiBPCARequest.setBillCity("Houston");
		neiBPCARequest.setBillCountry("US");
		neiBPCARequest.setBillDistrict("billDistrict");
		neiBPCARequest.setBillHouseNum1("1945");
		neiBPCARequest.setBillState("TX");
		neiBPCARequest.setBillStreet("Main st ");
		neiBPCARequest.setBillZip("77002");
		neiBPCARequest.setEbill("1");
		neiBPCARequest.setEmailAddr("jkladjkf@gmail.com");
		neiBPCARequest.setFirstName("First Nmae");
		neiBPCARequest.setFullName("FullName");
		neiBPCARequest.setLastName("last4");
		neiBPCARequest.setPlanId("1");
		neiBPCARequest.setPlanName("iPlanName");
		neiBPCARequest.setSrvcCity("Houston");
		neiBPCARequest.setSrvcCountry("US");
		neiBPCARequest.setSrvcDistrict("srvcDistrict");
		neiBPCARequest.setSrvcHouseNum1("3443");
		neiBPCARequest.setSrvcState("TX");
		neiBPCARequest.setSrvcStreet("iSrvcStreet");
		neiBPCARequest.setSrvcZip("77002");
		neiBPCARequest.setUtility("Test");
		neiBPCARequest.setPhoneNumber("746487989");

	}

	@Test
	public void testCreateNEIBPCA() {

		ZEIsuNeiCreateBpCaResponse ccsResponse = new ZEIsuNeiCreateBpCaResponse();

		try {

			Mockito.when(envMessageReader.getMessage(Constants.CCS_USER_NAME)).thenReturn("WEBCPIC");
			Mockito.when(envMessageReader.getMessage(Constants.CCS_PSD)).thenReturn("CustAlrt01");
			Mockito.when(envMessageReader.getMessage(Constants.NEI_CREATE_BPCA_CCS_ENDPOINT_URL)).thenReturn(
					"http://saprpm01.reinternal.com:8080/sap/bc/srt/rfc/sap/z_e_isu_nei_create_bp_ca/900/z_e_isu_nei_create_bp_ca/z_e_isu_nei_create_bp_ca_bind");

			ccsResponse = nEISimplySmartService.createNEIBPCA(neiBPCARequest, "Session1");

		} catch (NRGException e) {
			e.printStackTrace();
		}
		AssertJUnit.assertEquals(ccsResponse.getOAcctNumber(), "");
	}

	@Test
	public void testCreateNEIBPCA_Exception() {
		
		ZEIsuNeiCreateBpCaResponse ccsResponse = new ZEIsuNeiCreateBpCaResponse();
		
		NeiBPCARequest neiBPCARequest1 = new NeiBPCARequest();
		neiBPCARequest1.setAcctNumber("5678");
	

		try {
			Mockito.when(envMessageReader.getMessage(Constants.CCS_USER_NAME)).thenReturn("WEBCPIC");
			Mockito.when(envMessageReader.getMessage(Constants.CCS_PSD)).thenReturn("CustAlrt01");
			Mockito.when(envMessageReader.getMessage(Constants.NEI_CREATE_BPCA_CCS_ENDPOINT_URL)).thenReturn(
					"http://saprpm01.reinternal.com:8080/sap/bc/srt/rfc/sap/z_e_isu_nei_create_bp_ca/900/z_e_isu_nei_create_bp_ca/z_e_isu_nei_create_bp_ca_bind");

			//Mockito.when(stub.zeIsuNeiCreateBpCa(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject());
			
			ccsResponse = nEISimplySmartService.createNEIBPCA(neiBPCARequest, "Session1");

		} catch (Exception e) {
			thrown = true;
			e.printStackTrace();
		}

	}

}
