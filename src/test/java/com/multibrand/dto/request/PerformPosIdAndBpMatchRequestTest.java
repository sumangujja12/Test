package com.multibrand.dto.request;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationDescriptor;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.NotEmptyValidator;

public class PerformPosIdAndBpMatchRequestTest {

	private NotEmptyValidator notEmptyValidator;
	private PerformPosIdAndBpMatchRequest request;
	
	@BeforeMethod
	public void setup(){
		request = new PerformPosIdAndBpMatchRequest();
		AnnotationDescriptor<NotEmpty> descriptor = new AnnotationDescriptor<NotEmpty>(NotEmpty.class);
		NotEmpty notEmpty = AnnotationFactory.create(descriptor);
		this.notEmptyValidator = new NotEmptyValidator();
		this.notEmptyValidator.initialize(notEmpty);
	}
	
	private void setPerformPosIdAndBpMatchRequestDataForPositiveTest(){
		request.setLastName("LastNameTest");
		request.setFirstName("FirstNametest");
		request.setDob("05071990");
		request.setEmail("nrgtest@nrg.com");
		request.setPhoneNum("0123456789");
		request.setServStreetNum("1120");
		request.setServStreetName("Old Farm Rd");
		request.setServCity("Houston");
		request.setServState("TX");
		request.setServZipCode("77063");
		
	} 
	
	private void setPerformPosIdAndBpMatchRequestDataForEmptySpace(){
		request.setLastName("");
		request.setFirstName("");
		request.setDob("");
		request.setEmail("");
		request.setPhoneNum("");
		request.setServStreetNum("");
		request.setServStreetName("");
		request.setServCity("");
		request.setServState("");
		request.setServZipCode("");
	} 
	
	private void setPerformPosIdAndBpMatchRequestDataForNullTest(){
		request.setLastName(null);
		request.setFirstName(null);
		request.setDob(null);
		request.setEmail(null);
		request.setPhoneNum(null);
		request.setServStreetNum(null);
		request.setServStreetName(null);
		request.setServCity(null);
		request.setServState(null);
		request.setServZipCode(null);
	}
	
	private void setPerformPosIdAndBpMatchRequestDataForMultipleSpaceTest(){
		request.setLastName("   ");
		request.setFirstName("   ");
		request.setDob("   ");
		request.setEmail("   ");
		request.setPhoneNum("   ");
		request.setServStreetNum("   ");
		request.setServStreetName("   ");
		request.setServCity("   ");
		request.setServState("   ");
		request.setServZipCode("   ");
	}
	@Test
	public void testNotEmptyPositiveTest() {
		setPerformPosIdAndBpMatchRequestDataForPositiveTest();
		assertTrue(notEmptyValidator.isValid(request.getLastName(), null));
		assertTrue(notEmptyValidator.isValid(request.getFirstName(), null));
		assertTrue(notEmptyValidator.isValid(request.getDob(), null));
		assertTrue(notEmptyValidator.isValid(request.getEmail(), null));
		assertTrue(notEmptyValidator.isValid(request.getPhoneNum(), null));
		assertTrue(notEmptyValidator.isValid(request.getServStreetNum(), null));
		assertTrue(notEmptyValidator.isValid(request.getServStreetName(), null));
		assertTrue(notEmptyValidator.isValid(request.getServCity(), null));
		assertTrue(notEmptyValidator.isValid(request.getServState(), null));
		assertTrue(notEmptyValidator.isValid(request.getServZipCode(), null));
	}
	
	@Test
	public void testNotEmptyNegativeTestWithEmptyData() {
		setPerformPosIdAndBpMatchRequestDataForEmptySpace();
		assertFalse(notEmptyValidator.isValid(request.getLastName(), null));
		assertFalse(notEmptyValidator.isValid(request.getFirstName(), null));
		assertFalse(notEmptyValidator.isValid(request.getDob(), null));
		assertFalse(notEmptyValidator.isValid(request.getEmail(), null));
		assertFalse(notEmptyValidator.isValid(request.getPhoneNum(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetNum(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetName(), null));
		assertFalse(notEmptyValidator.isValid(request.getServCity(), null));
		assertFalse(notEmptyValidator.isValid(request.getServState(), null));
		assertFalse(notEmptyValidator.isValid(request.getServZipCode(), null));
	}
	
	@Test
	public void testNotEmptyNegativeTestWithNullData() {
		setPerformPosIdAndBpMatchRequestDataForNullTest();
		assertFalse(notEmptyValidator.isValid(request.getLastName(), null));
		assertFalse(notEmptyValidator.isValid(request.getFirstName(), null));
		assertFalse(notEmptyValidator.isValid(request.getDob(), null));
		assertFalse(notEmptyValidator.isValid(request.getEmail(), null));
		assertFalse(notEmptyValidator.isValid(request.getPhoneNum(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetNum(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetName(), null));
		assertFalse(notEmptyValidator.isValid(request.getServCity(), null));
		assertFalse(notEmptyValidator.isValid(request.getServState(), null));
		assertFalse(notEmptyValidator.isValid(request.getServZipCode(), null));
	}
	
	@Test
	public void testNotEmptyNegativeTestWithMultipleSpace() {
		setPerformPosIdAndBpMatchRequestDataForMultipleSpaceTest();
		assertFalse(notEmptyValidator.isValid(request.getLastName(), null));
		assertFalse(notEmptyValidator.isValid(request.getFirstName(), null));
		assertFalse(notEmptyValidator.isValid(request.getDob(), null));
		assertFalse(notEmptyValidator.isValid(request.getEmail(), null));
		assertFalse(notEmptyValidator.isValid(request.getPhoneNum(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetNum(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetName(), null));
		assertFalse(notEmptyValidator.isValid(request.getServCity(), null));
		assertFalse(notEmptyValidator.isValid(request.getServState(), null));
		assertFalse(notEmptyValidator.isValid(request.getServZipCode(), null));
	}
	
}
