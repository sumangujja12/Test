package com.multibrand.dto.request;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.hibernate.validator.internal.util.annotationfactory.AnnotationDescriptor;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.NotEmptyValidator;

public class EnrollmentRequestTest {
	private NotEmptyValidator notEmptyValidator;
	private EnrollmentRequest request;
	
	@BeforeMethod
	public void setup(){
		request = new EnrollmentRequest();
		AnnotationDescriptor<NotEmpty> descriptor = new AnnotationDescriptor<NotEmpty>(NotEmpty.class);
		NotEmpty notEmpty = AnnotationFactory.create(descriptor);
		this.notEmptyValidator = new NotEmptyValidator();
		this.notEmptyValidator.initialize(notEmpty);
	}
	
	private void setEnrollmentRequestDataForPositiveTest(){
		request.setTrackingId("123456789");
		request.setTransactionType("MVI");
		request.setTdspCodeCCS("D0001");
		request.setServStreetNum("1120");
		request.setServStreetName("Old Farm Rd");
		request.setServCity("Houston");
		request.setServState("TX");
		request.setServZipCode("77063");
		request.setOfferCode("12345678");
		request.setPromoCode("zxcv");
		request.setCampaignCode("qwerty");
		request.setIncentiveCode("asdfg");
		request.setProductPriceCode("test");
	} 
	
	private void setEnrollmentRequestDataForEmptySpace(){
		request.setTrackingId("");
		request.setTransactionType("");
		request.setTdspCodeCCS("");
		request.setServStreetNum("");
		request.setServStreetName("");
		request.setServCity("");
		request.setServState("");
		request.setServZipCode("");
		request.setOfferCode("");
		request.setPromoCode("");
		request.setCampaignCode("");
		request.setIncentiveCode("");
		request.setProductPriceCode("");
	} 
	
	private void setEnrollmentRequestDataForNullTest(){

		request.setTrackingId(null);
		request.setTransactionType(null);
		request.setTdspCodeCCS(null);
		request.setServStreetNum(null);
		request.setServStreetName(null);
		request.setServCity(null);
		request.setServState(null);
		request.setServZipCode(null);
		request.setOfferCode(null);
		request.setPromoCode(null);
		request.setCampaignCode(null);
		request.setIncentiveCode(null);
		request.setProductPriceCode(null);
	}
	
	private void setEnrollmentRequestDataForMultipleSpaceTest(){
		request.setTrackingId("   ");
		request.setTransactionType("  ");
		request.setTdspCodeCCS("  ");
		request.setServStreetNum("  ");
		request.setServStreetName("   ");
		request.setServCity("   ");
		request.setServState("   ");
		request.setServZipCode("  ");
		request.setOfferCode("   ");
		request.setPromoCode("   ");
		request.setCampaignCode("   ");
		request.setIncentiveCode("  ");
		request.setProductPriceCode("  ");
	}
	@Test
	public void testNotEmptyPositiveTest() {
		setEnrollmentRequestDataForPositiveTest();

		assertTrue(notEmptyValidator.isValid(request.getTrackingId(), null));
		assertTrue(notEmptyValidator.isValid(request.getTransactionType(), null));
		assertTrue(notEmptyValidator.isValid(request.getTdspCodeCCS(), null));
		assertTrue(notEmptyValidator.isValid(request.getOfferCode(), null));
		assertTrue(notEmptyValidator.isValid(request.getPromoCode(), null));
		assertTrue(notEmptyValidator.isValid(request.getCampaignCode(), null));
		assertTrue(notEmptyValidator.isValid(request.getIncentiveCode(), null));
		assertTrue(notEmptyValidator.isValid(request.getProductPriceCode(), null));
		assertTrue(notEmptyValidator.isValid(request.getServStreetNum(), null));
		assertTrue(notEmptyValidator.isValid(request.getServStreetName(), null));
		assertTrue(notEmptyValidator.isValid(request.getServCity(), null));
		assertTrue(notEmptyValidator.isValid(request.getServState(), null));
		assertTrue(notEmptyValidator.isValid(request.getServZipCode(), null));
	}
	
	@Test
	public void testNotEmptyNegativeTestWithEmptyData() {
		setEnrollmentRequestDataForEmptySpace();
		assertFalse(notEmptyValidator.isValid(request.getTrackingId(), null));
		assertFalse(notEmptyValidator.isValid(request.getTransactionType(), null));
		assertFalse(notEmptyValidator.isValid(request.getTdspCodeCCS(), null));
		assertFalse(notEmptyValidator.isValid(request.getOfferCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getPromoCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getCampaignCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getIncentiveCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getProductPriceCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetNum(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetName(), null));
		assertFalse(notEmptyValidator.isValid(request.getServCity(), null));
		assertFalse(notEmptyValidator.isValid(request.getServState(), null));
		assertFalse(notEmptyValidator.isValid(request.getServZipCode(), null));
	}
	
	@Test
	public void testNotEmptyNegativeTestWithNullData() {
		setEnrollmentRequestDataForNullTest();
		assertFalse(notEmptyValidator.isValid(request.getTrackingId(), null));
		assertFalse(notEmptyValidator.isValid(request.getTransactionType(), null));
		assertFalse(notEmptyValidator.isValid(request.getTdspCodeCCS(), null));
		assertFalse(notEmptyValidator.isValid(request.getOfferCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getPromoCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getCampaignCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getIncentiveCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getProductPriceCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetNum(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetName(), null));
		assertFalse(notEmptyValidator.isValid(request.getServCity(), null));
		assertFalse(notEmptyValidator.isValid(request.getServState(), null));
		assertFalse(notEmptyValidator.isValid(request.getServZipCode(), null));
	}
	
	@Test
	public void testNotEmptyNegativeTestWithMultipleSpace() {
		setEnrollmentRequestDataForMultipleSpaceTest();
		assertFalse(notEmptyValidator.isValid(request.getTrackingId(), null));
		assertFalse(notEmptyValidator.isValid(request.getTransactionType(), null));
		assertFalse(notEmptyValidator.isValid(request.getTdspCodeCCS(), null));
		assertFalse(notEmptyValidator.isValid(request.getOfferCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getPromoCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getCampaignCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getIncentiveCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getProductPriceCode(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetNum(), null));
		assertFalse(notEmptyValidator.isValid(request.getServStreetName(), null));
		assertFalse(notEmptyValidator.isValid(request.getServCity(), null));
		assertFalse(notEmptyValidator.isValid(request.getServState(), null));
		assertFalse(notEmptyValidator.isValid(request.getServZipCode(), null));
	}
}
