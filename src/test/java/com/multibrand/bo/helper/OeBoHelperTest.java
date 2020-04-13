package com.multibrand.bo.helper;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.multibrand.dto.OESignupDTO;
import com.multibrand.util.Constants;
import com.multibrand.util.LoggerUtil;

@Test(singleThreaded = true)
public class OeBoHelperTest {
		
	@InjectMocks
	OeBoHelper oeBoHelper;

	@Mock
	private LoggerUtil logger;
	
	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(logger.isDebugEnabled()).thenReturn(true);
	}
	
	@Test
	public void testAllowSubmitEnrollmentPositiveForBPMatch(){		
		OESignupDTO oeSignUpDTO = new OESignupDTO();
		oeSignUpDTO.setBpMatchText("");
		Assert.assertTrue(oeBoHelper.allowSubmitEnrollment(oeSignUpDTO, null , 1, true));
	}
	@Test
	public void testAllowSubmitEnrollmentNegativeForBPMatch(){
		OESignupDTO oeSignUpDTO = new OESignupDTO();
		oeSignUpDTO.setBpMatchText(Constants.BPSD);
		oeSignUpDTO.setErrorCdList("BPSD");
		Assert.assertFalse(oeBoHelper.allowSubmitEnrollment(oeSignUpDTO, null , 1, true));
	}
	@Test
	public void testAllowSubmitEnrollmentPositiveForPosid(){		
		OESignupDTO oeSignUpDTO = new OESignupDTO();
		oeSignUpDTO.setBpMatchText("");
		Assert.assertTrue(oeBoHelper.allowSubmitEnrollment(oeSignUpDTO, null , 1, false));
	}

	@Test
	public void testAllowSubmitEnrollmentNegativeForPosid(){
		OESignupDTO oeSignUpDTO = new OESignupDTO();
		oeSignUpDTO.setBpMatchText("");
		Assert.assertFalse(oeBoHelper.allowSubmitEnrollment(oeSignUpDTO, null , 3, false));
	}
}
