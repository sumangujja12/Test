package com.multibrand.resource;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;

import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.multibrand.dto.request.NeiBPCARequest;
import com.multibrand.exception.NRGException;
import com.multibrand.resources.NEISimplySmartResource;
import com.multibrand.service.NEISimplySmartService;
import com.nrg.cxfstubs.nei.bpca.Bapiret2;
import com.nrg.cxfstubs.nei.bpca.ZEIsuNeiCreateBpCaResponse;

public class NEISimplySmartResourceTest {

	boolean thrown = false;

	@InjectMocks
	private NEISimplySmartResource neiSimplySmartResource;

	@Mock
	public NEISimplySmartService nEISimplySmartService;

	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeMethod
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
		neiSimplySmartResource.setHttpRequest(new MockHttpServletRequest());
	}

	@Test
	public void testCreateNEIBPCA() {
		NeiBPCARequest neiBPCARequest = new NeiBPCARequest();
		ZEIsuNeiCreateBpCaResponse ccsResponse = new ZEIsuNeiCreateBpCaResponse();
		ccsResponse.setOBusinessPartner("123456");
		ccsResponse.setOAcctNumber("4567898");
		ccsResponse.setExBapiret2(new Bapiret2());
		Response response = null;

		try {
			when(nEISimplySmartService.createNEIBPCA(Matchers.any(NeiBPCARequest.class), Matchers.any(String.class)))
					.thenReturn(ccsResponse);
			response = neiSimplySmartResource.createNEIBPCA(neiBPCARequest);
		} catch (NRGException e) {
			e.printStackTrace();
		}
		AssertJUnit.assertEquals(((ZEIsuNeiCreateBpCaResponse) response.getEntity()).getOAcctNumber(), "4567898");
	}

	@Test
	public void testCreateNEIBPCA_Exception() {
		
		NeiBPCARequest neiBPCARequest1 = new NeiBPCARequest();
		neiBPCARequest1.setAcctNumber("5678");

		try {
			Mockito.when(nEISimplySmartService.createNEIBPCA(Mockito.any(NeiBPCARequest.class), Mockito.anyString()))
					.thenThrow(new Exception());
			neiSimplySmartResource.createNEIBPCA(neiBPCARequest1);
		} catch (Exception ex) {
			thrown = true;
		}
		assertTrue(thrown);

	}

}
