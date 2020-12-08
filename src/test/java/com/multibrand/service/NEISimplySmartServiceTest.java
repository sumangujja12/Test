package com.multibrand.service;


import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
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

	@InjectMocks
	public NEISimplySmartService nEISimplySmartService;
	
	@Spy
	ZEISUNEICREATEBPCA_Service service;
	
	public ZEISUNEICREATEBPCA stub;
	
	
	
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
	
	}

	@Test
	public void testCreateNEIBPCA() {

		NeiBPCARequest neiBPCARequest = new NeiBPCARequest();
		neiBPCARequest.setAcctNumber("1234");
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
		
		ZEIsuNeiCreateBpCaResponse ccsResponse = new ZEIsuNeiCreateBpCaResponse();
		
		  try {
			//  ZEISUNEICREATEBPCA_Service port = new ZEISUNEICREATEBPCA_Service();
			//	ZEISUNEICREATEBPCA stub = port.getZEISUNEICREATEBPCABIND();
		
			  Mockito.when(envMessageReader.getMessage(Constants.CCS_USER_NAME)).thenReturn("WEBCPIC");
			  Mockito.when(envMessageReader.getMessage(Constants.CCS_PSD)).thenReturn("CustAlrt01");
			  Mockito.when(envMessageReader.getMessage(Constants.NEI_CREATE_BPCA_CCS_ENDPOINT_URL)).thenReturn("http://saprpm01.reinternal.com:8080/sap/bc/srt/rfc/sap/z_e_isu_nei_create_bp_ca/900/z_e_isu_nei_create_bp_ca/z_e_isu_nei_create_bp_ca_bind");
			  
				  ((ZEISUNEICREATEBPCA) Mockito.when((ZEISUNEICREATEBPCA)stub)).zeIsuNeiCreateBpCa(
						     Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),
		            		 Matchers.any(String.class),            		 
		            		 Matchers.any(javax.xml.ws.Holder.class),
		            		 Matchers.any(javax.xml.ws.Holder.class),
		            		 Matchers.any(javax.xml.ws.Holder.class)
		            		 );

	             } catch (Exception e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	             }
		
		try {
			ccsResponse = nEISimplySmartService.createNEIBPCA(neiBPCARequest, "Session1");
		} catch (NRGException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//   AssertJUnit.assertEquals

	}

}
