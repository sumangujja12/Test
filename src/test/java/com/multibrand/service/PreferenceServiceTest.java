package com.multibrand.service;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.multibrand.vo.response.PushNotificationPrefReadResponse;
import com.nrg.cxfstubs.gmd.read.push.pref.TABLEOFZESPUSHALERTSTATUS;
import com.nrg.cxfstubs.gmd.read.push.pref.ZECRMGMDREADPUSHPREFResponse;
import com.nrg.cxfstubs.gmd.read.push.pref.ZESPUSHALERTSTATUS;

@Test(singleThreaded = true)
public class PreferenceServiceTest {

	@InjectMocks
	PreferenceService prefService;

	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_getPushNotificationPrefReadResponse_success() {
		ZESPUSHALERTSTATUS status = new ZESPUSHALERTSTATUS();
		status.setALERTID("PUSH_TMD1");
		status.setPUSHACTIVE("X");

		TABLEOFZESPUSHALERTSTATUS tableOfAlertStatus = new TABLEOFZESPUSHALERTSTATUS();
		tableOfAlertStatus.getItem().add(status);

		ZECRMGMDREADPUSHPREFResponse response = new ZECRMGMDREADPUSHPREFResponse();
		response.setEXPUSHSTATTAB(tableOfAlertStatus);

		PushNotificationPrefReadResponse res = prefService.getPushNotificationPrefReadResponse(response);

	}

}
