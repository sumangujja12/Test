package com.multibrand.bo;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.multibrand.service.PreferenceService;
import com.multibrand.vo.request.PushNotifiPreferenceRequest;
import com.multibrand.vo.request.PushNotificationStatus;
import com.multibrand.vo.response.PushNotificationPrefReadResponse;
import com.multibrand.vo.response.PushNotificationPrefUpdateResponse;
import com.nrg.cxfstubs.gmd.read.push.pref.TABLEOFZESPUSHALERTSTATUS;
import com.nrg.cxfstubs.gmd.read.push.pref.ZECRMGMDREADPUSHPREFResponse;
import com.nrg.cxfstubs.gmd.read.push.pref.ZESPUSHALERTSTATUS;
import com.nrg.cxfstubs.gmd.upd.push.pref.ZECRMGMDUPDATEPUSHPREFResponse;

@Test(singleThreaded = true)
public class PreferenceBOTest {

	@InjectMocks
	PreferenceBO preferenceBo;

	@Spy
	PreferenceService prefService;

	@Mock
	WebServiceTemplate webServiceTemplateForGmdReadPushPreferences;

	@Mock
	WebServiceTemplate webServiceTemplateForGmdUpdatePushPreferences;

	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
		Mockito.reset(webServiceTemplateForGmdReadPushPreferences);
	}

	@Test
	public void test_readPushNotiPreference_success() {
		PushNotifiPreferenceRequest request = new PushNotifiPreferenceRequest();
		request.setContractAccountNumber("10580093");
		request.setBrandName("GR");
		request.setCompanyCode("0271");

		ZECRMGMDREADPUSHPREFResponse mockResponse = new ZECRMGMDREADPUSHPREFResponse();

		ZESPUSHALERTSTATUS status = new ZESPUSHALERTSTATUS();
		status.setALERTID("PUSH_TMD1");
		status.setPUSHACTIVE("X");

		TABLEOFZESPUSHALERTSTATUS tableStatus = new TABLEOFZESPUSHALERTSTATUS();
		tableStatus.getItem().add(status);

		mockResponse.setEXPUSHSTATTAB(tableStatus);
		when(webServiceTemplateForGmdReadPushPreferences.marshalSendAndReceive(anyObject())).thenReturn(mockResponse);

		PushNotificationPrefReadResponse response = preferenceBo.pushNotificationPreferences(request);
		assertNotNull(response);
	}

	@Test
	public void test_readPushNotiPreference_exception() {
		PushNotifiPreferenceRequest request = new PushNotifiPreferenceRequest();
		request.setContractAccountNumber("10580093");
		request.setBrandName("GR");
		request.setCompanyCode("0271");

		ZECRMGMDREADPUSHPREFResponse mockResponse = null;
		when(webServiceTemplateForGmdReadPushPreferences.marshalSendAndReceive(anyObject())).thenReturn(mockResponse);

		PushNotificationPrefReadResponse response = preferenceBo.pushNotificationPreferences(request);
		Assert.assertEquals(response.getResultDescription(), "Exception Occurred");

	}

	@Test
	public void test_updatePushNotificationPreferences_exception() {
		PushNotifiPreferenceRequest request = new PushNotifiPreferenceRequest();
		request.setContractAccountNumber("10580093");
		request.setBrandName("GR");
		request.setCompanyCode("0271");

		ZECRMGMDUPDATEPUSHPREFResponse mockResponse = new ZECRMGMDUPDATEPUSHPREFResponse();
		mockResponse.setEXMESSAGE("");

		when(webServiceTemplateForGmdUpdatePushPreferences.marshalSendAndReceive(anyObject())).thenReturn(mockResponse);

		PushNotificationPrefUpdateResponse response = preferenceBo.updatePushNotificationPreferences(request);
		Assert.assertEquals(response.getResultDescription(), "Exception Occurred");
	}

	@Test
	public void test_updatePushNotificationPreferences_success() {
		PushNotificationStatus status = new PushNotificationStatus();
		status.setAlertId("PUSH_EXP2");
		status.setStatus("X");

		List<PushNotificationStatus> notifications = new ArrayList<>();
		notifications.add(status);

		PushNotifiPreferenceRequest request = new PushNotifiPreferenceRequest();
		request.setContractAccountNumber("10580093");
		request.setBrandName("GR");
		request.setCompanyCode("0271");
		request.setPushStatTabs(notifications);

		ZECRMGMDUPDATEPUSHPREFResponse mockResponse = new ZECRMGMDUPDATEPUSHPREFResponse();
		mockResponse.setEXMESSAGE("");

		when(webServiceTemplateForGmdUpdatePushPreferences.marshalSendAndReceive(anyObject())).thenReturn(mockResponse);

		PushNotificationPrefUpdateResponse response = preferenceBo.updatePushNotificationPreferences(request);

		assertNotNull(response);
	}
}
