package com.multibrand.dto.request;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import org.hibernate.validator.internal.util.annotationfactory.AnnotationDescriptor;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.NotEmptyValidator;

public class UCCDataRequestTest {
	
	private NotEmptyValidator notEmptyValidator;
	private UCCDataRequest request;
	
	@BeforeMethod
	public void setup(){
		request = new UCCDataRequest();
		AnnotationDescriptor<NotEmpty> descriptor = new AnnotationDescriptor<NotEmpty>(NotEmpty.class);
		NotEmpty notEmpty = AnnotationFactory.create(descriptor);
		this.notEmptyValidator = new NotEmptyValidator();
		this.notEmptyValidator.initialize(notEmpty);
	}
	
	private void setUCCDataRequestForPositiveTestData(){
		request.setTrackingId("123456789");
		request.setLastName("LastNameTest");
		request.setFirstName("FirstNametest");
		request.setDepositAmount("100");
	}
	
	private void setUCCDataRequestForEmptySpaceTestData(){
		request.setTrackingId("");
		request.setLastName("");
		request.setFirstName("");
		request.setDepositAmount("");
	}
	private void setUCCDataRequestForNullTestData(){
		request.setTrackingId(null);
		request.setLastName(null);
		request.setFirstName(null);
		request.setDepositAmount(null);
	}
	private void setUCCDataRequestForMultipleSpaceTestData(){
		request.setTrackingId("   ");
		request.setLastName("    ");
		request.setFirstName("    ");
		request.setDepositAmount("   ");
	}
	
	@Test
	public void testNotEmptyPositiveTest() {
		setUCCDataRequestForPositiveTestData();
		assertTrue(notEmptyValidator.isValid(request.getTrackingId(), null));
		assertTrue(notEmptyValidator.isValid(request.getLastName(), null));
		assertTrue(notEmptyValidator.isValid(request.getFirstName(), null));
		assertTrue(notEmptyValidator.isValid(request.getDepositAmount(), null));
	}
	
	@Test
	public void testNotEmptyNegativeTestWithEmptyData() {
		setUCCDataRequestForEmptySpaceTestData();
		assertFalse(notEmptyValidator.isValid(request.getTrackingId(), null));
		assertFalse(notEmptyValidator.isValid(request.getLastName(), null));
		assertFalse(notEmptyValidator.isValid(request.getFirstName(), null));
		assertFalse(notEmptyValidator.isValid(request.getDepositAmount(), null));
	}
	
	@Test
	public void testNotEmptyNegativeTestWithNullData() {
		setUCCDataRequestForNullTestData();
		assertFalse(notEmptyValidator.isValid(request.getTrackingId(), null));
		assertFalse(notEmptyValidator.isValid(request.getLastName(), null));
		assertFalse(notEmptyValidator.isValid(request.getFirstName(), null));
		assertFalse(notEmptyValidator.isValid(request.getDepositAmount(), null));
	}
	
	@Test
	public void testNotEmptyNegativeTestWithMultipleSpace() {
		setUCCDataRequestForMultipleSpaceTestData();
		assertFalse(notEmptyValidator.isValid(request.getTrackingId(), null));
		assertFalse(notEmptyValidator.isValid(request.getLastName(), null));
		assertFalse(notEmptyValidator.isValid(request.getFirstName(), null));
		assertFalse(notEmptyValidator.isValid(request.getDepositAmount(), null));
	}

}
