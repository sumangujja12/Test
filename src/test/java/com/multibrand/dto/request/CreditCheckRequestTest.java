package com.multibrand.dto.request;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.hibernate.validator.internal.util.annotationfactory.AnnotationDescriptor;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.NotEmptyValidator;

@Test(singleThreaded = true)
public class CreditCheckRequestTest {

	
	private NotEmptyValidator notEmptyValidator;
	private CreditCheckRequest request;
	
	@BeforeMethod
	public void setup(){
		request = new CreditCheckRequest();
		AnnotationDescriptor<NotEmpty> descriptor = new AnnotationDescriptor<NotEmpty>(NotEmpty.class);
		NotEmpty notEmpty = AnnotationFactory.create(descriptor);
		this.notEmptyValidator = new NotEmptyValidator();
		this.notEmptyValidator.initialize(notEmpty);
	}
	
	private void setCreditCheckRequestForPositiveTestData(){
		request.setTrackingId("123456789");
		request.setLastName("LastNameTest");
		request.setFirstName("FirstNametest");
		request.setServStreetNum("1120");
		request.setServStreetName("Old Farm Rd");
		request.setServCity("Houston");
		request.setServState("TX");
		request.setServZipCode("77063");
		request.setTransactionType("MVI");
		request.setOfferCode("41898857");
	}
	
	private void setCreditCheckRequestForEmptySpaceTestData(){
		request.setTrackingId("");
		request.setLastName("");
		request.setFirstName("");
		request.setServStreetNum("");
		request.setServStreetName("");
		request.setServCity("");
		request.setServState("");
		request.setServZipCode("");
		request.setTransactionType("");
		request.setOfferCode("");
	}
	private void setCreditCheckRequestForNullTestData(){
		request.setTrackingId(null);
		request.setLastName(null);
		request.setFirstName(null);
		request.setServStreetNum(null);
		request.setServStreetName(null);
		request.setServCity(null);
		request.setServState(null);
		request.setServZipCode(null);
		request.setTransactionType(null);
		request.setOfferCode(null);
	}
	private void setCreditCheckRequestForMultipleSpaceTestData(){
		request.setTrackingId("   ");
		request.setLastName("  ");
		request.setFirstName("   ");
		request.setServStreetNum("   ");
		request.setServStreetName("   ");
		request.setServCity("   ");
		request.setServState("   ");
		request.setServZipCode("   ");
		request.setTransactionType("   ");
		request.setOfferCode("   ");
	}
	
	@Test
	public void testNotEmptyPositiveTest() {
		setCreditCheckRequestForPositiveTestData();
		assertTrue(notEmptyValidator.isValid(request.getLastName(), null));
		assertTrue(notEmptyValidator.isValid(request.getFirstName(), null));
		assertTrue(notEmptyValidator.isValid(request.getTrackingId(), null));
		assertTrue(notEmptyValidator.isValid(request.getTransactionType(), null));
		assertTrue(notEmptyValidator.isValid(request.getOfferCode(), null));
		assertTrue(notEmptyValidator.isValid(request.getServStreetNum(), null));
		assertTrue(notEmptyValidator.isValid(request.getServStreetName(), null));
		assertTrue(notEmptyValidator.isValid(request.getServCity(), null));
		assertTrue(notEmptyValidator.isValid(request.getServState(), null));
		assertTrue(notEmptyValidator.isValid(request.getServZipCode(), null));
	}
	
	@Test
	public void testNotEmptyNegativeTestWithEmptyData() {
		setCreditCheckRequestForEmptySpaceTestData();
		assertFalse(notEmptyValidator.isValid(request.getLastName(), null));
		assertFalse(notEmptyValidator.isValid(request.getFirstName(), null));
		assertFalse(notEmptyValidator.isValid(request.getTrackingId(), null));
		assertFalse(notEmptyValidator.isValid(request.getTransactionType(), null));
		assertFalse(notEmptyValidator.isValid(request.getOfferCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetNum(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetName(), null));
		assertFalse(notEmptyValidator.isValid(request.getServCity(), null));
		assertFalse(notEmptyValidator.isValid(request.getServState(), null));
		assertFalse(notEmptyValidator.isValid(request.getServZipCode(), null));
	}
	
	@Test
	public void testNotEmptyNegativeTestWithNullData() {
		setCreditCheckRequestForNullTestData();
		assertFalse(notEmptyValidator.isValid(request.getLastName(), null));
		assertFalse(notEmptyValidator.isValid(request.getFirstName(), null));
		assertFalse(notEmptyValidator.isValid(request.getTrackingId(), null));
		assertFalse(notEmptyValidator.isValid(request.getTransactionType(), null));
		assertFalse(notEmptyValidator.isValid(request.getOfferCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetNum(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetName(), null));
		assertFalse(notEmptyValidator.isValid(request.getServCity(), null));
		assertFalse(notEmptyValidator.isValid(request.getServState(), null));
		assertFalse(notEmptyValidator.isValid(request.getServZipCode(), null));
	}
	
	@Test
	public void testNotEmptyNegativeTestWithMultipleSpace() {
		setCreditCheckRequestForMultipleSpaceTestData();
		assertFalse(notEmptyValidator.isValid(request.getLastName(), null));
		assertFalse(notEmptyValidator.isValid(request.getFirstName(), null));
		assertFalse(notEmptyValidator.isValid(request.getTrackingId(), null));
		assertFalse(notEmptyValidator.isValid(request.getTransactionType(), null));
		assertFalse(notEmptyValidator.isValid(request.getOfferCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetNum(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetName(), null));
		assertFalse(notEmptyValidator.isValid(request.getServCity(), null));
		assertFalse(notEmptyValidator.isValid(request.getServState(), null));
		assertFalse(notEmptyValidator.isValid(request.getServZipCode(), null));
	}
}
