package com.multibrand.dto.request;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.hibernate.validator.internal.util.annotationfactory.AnnotationDescriptor;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.NotEmptyValidator;

public class EsidCalendarRequestTest {

	
	private NotEmptyValidator notEmptyValidator;
	private EsidCalendarRequest request;
	
	@BeforeMethod
	public void setup(){
		request = new EsidCalendarRequest();
		AnnotationDescriptor<NotEmpty> descriptor = new AnnotationDescriptor<NotEmpty>(NotEmpty.class);
		NotEmpty notEmpty = AnnotationFactory.create(descriptor);
		this.notEmptyValidator = new NotEmptyValidator();
		this.notEmptyValidator.initialize(notEmpty);
	}
	
	private void setEsidCalendarRequestForPositiveTestData(){
		request.setTrackingId("123456789");
		request.setServStreetNum("1120");
		request.setServStreetName("Old Farm Rd");
		request.setServZipCode("77063");
		request.setTransactionType("MVI");
		request.setTdspCodeCCS("D0001");
	}
	
	private void setEsidCalendarRequestForEmptySpaceTestData(){
		request.setTrackingId("");
		request.setServStreetNum("");
		request.setServStreetName("");
		request.setServZipCode("");
		request.setTransactionType("");
		request.setTdspCodeCCS("");
	}
	private void setEsidCalendarRequestForNullTestData(){
		request.setTrackingId(null);
		request.setServStreetNum(null);
		request.setServStreetName(null);
		request.setServZipCode(null);
		request.setTransactionType(null);
		request.setTdspCodeCCS(null);
	}
	private void setEsidCalendarRequestForMultipleSpaceTestData(){
		request.setTrackingId("   ");
		request.setServStreetNum("   ");
		request.setServStreetName("   ");
		request.setServZipCode("   ");
		request.setTransactionType("   ");
		request.setTdspCodeCCS("  ");
	}
	
	@Test
	public void testNotEmptyPositiveTest() {
		setEsidCalendarRequestForPositiveTestData();
		assertTrue(notEmptyValidator.isValid(request.getTrackingId(), null));
		assertTrue(notEmptyValidator.isValid(request.getTransactionType(), null));
		assertTrue(notEmptyValidator.isValid(request.getServStreetNum(), null));
		assertTrue(notEmptyValidator.isValid(request.getServStreetName(), null));;
		assertTrue(notEmptyValidator.isValid(request.getServZipCode(), null));
		assertTrue(notEmptyValidator.isValid(request.getTdspCodeCCS(), null));
	}
	
	@Test
	public void testNotEmptyNegativeTestWithEmptyData() {
		setEsidCalendarRequestForEmptySpaceTestData();
		assertFalse(notEmptyValidator.isValid(request.getTrackingId(), null));
		assertFalse(notEmptyValidator.isValid(request.getTransactionType(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetNum(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetName(), null));;
		assertFalse(notEmptyValidator.isValid(request.getServZipCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getTdspCodeCCS(), null));
	}
	
	@Test
	public void testNotEmptyNegativeTestWithNullData() {
		setEsidCalendarRequestForNullTestData();
		assertFalse(notEmptyValidator.isValid(request.getTrackingId(), null));
		assertFalse(notEmptyValidator.isValid(request.getTransactionType(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetNum(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetName(), null));;
		assertFalse(notEmptyValidator.isValid(request.getServZipCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getTdspCodeCCS(), null));
	}
	
	@Test
	public void testNotEmptyNegativeTestWithMultipleSpace() {
		setEsidCalendarRequestForMultipleSpaceTestData();
		assertFalse(notEmptyValidator.isValid(request.getTrackingId(), null));
		assertFalse(notEmptyValidator.isValid(request.getTransactionType(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetNum(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetName(), null));;
		assertFalse(notEmptyValidator.isValid(request.getServZipCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getTdspCodeCCS(), null));
	}
}
