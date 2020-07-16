package com.multibrand.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;


import com.multibrand.dto.request.MoveOutRequest;
import com.multibrand.resources.GMDResource;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.gmd.MoveOutResponse;
import com.nrg.cxfstubs.gmdmoveout.ZEISUCREATEMOVEOUTResponse;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/NRGREST-appContext.xml" })
public class GMDServiceTest implements Constants {

	/** Main entry point for server-side Spring MVC test */
	private MockMvc mockMvc;

	@InjectMocks
	GMDResource gmdResource;

	@Autowired
	private WebApplicationContext wac;

	@Mock
	private WebServiceTemplate webServiceTemplateForGMDCreateMoveOut;

	@InjectMocks
	private GMDService gmdService;

	/**
	 * Initializing class members and mocks
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();	
	}

	@Test
	public void testCreateGMDMoveOut() throws Exception {
		ZEISUCREATEMOVEOUTResponse response = new ZEISUCREATEMOVEOUTResponse();
		response.setMOUTDOC("123");

		MoveOutRequest moveOutRequest = new MoveOutRequest();
		moveOutRequest.setContractAccountNumber("000016599493");
		moveOutRequest.setEsiId("10443720001282814");
		moveOutRequest.setMoveOutDate("2020-08-09");
		moveOutRequest.setMoveOutReason("Z1");

		
		when(webServiceTemplateForGMDCreateMoveOut.marshalSendAndReceive(anyObject())).thenReturn(response);
		MoveOutResponse moveOutResponse = gmdService.createMoveOut(moveOutRequest);
		assertEquals("123", moveOutResponse.getMoveOutDocNumber());
	}

}
