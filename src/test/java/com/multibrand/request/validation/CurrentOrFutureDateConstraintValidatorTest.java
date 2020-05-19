package com.multibrand.request.validation;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.hibernate.validator.internal.util.annotationfactory.AnnotationDescriptor;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.multibrand.request.validation.ActionCode;
import com.multibrand.request.validation.ActionCodeValidator;

@Test(singleThreaded = true)
public class CurrentOrFutureDateConstraintValidatorTest {

	
	private CurrentOrFutureDateConstraintValidator validator;
	LocalDate today = LocalDate.now();
	
	@BeforeMethod
	public void setup(){
		AnnotationDescriptor<CurrentOrFutureDate> descriptor = new AnnotationDescriptor<CurrentOrFutureDate>(CurrentOrFutureDate.class);
		CurrentOrFutureDate currentOrFutureDate = AnnotationFactory.create(descriptor);
		this.validator = new CurrentOrFutureDateConstraintValidator();
		this.validator.initialize(currentOrFutureDate);
	}
	private String getFormattedDateStr(LocalDate localDate){
		return localDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));
	}
	@Test
	public void testValidCurrentDate() {
		String formattedDate = getFormattedDateStr(today);
		assertTrue( validator.isValid(formattedDate, null));
	}
	
	@Test
	public void testValidFutureDate() {
		LocalDate FutreDay1 = today.plusDays(2);
		String FutreDayStr1 = getFormattedDateStr(FutreDay1);
		LocalDate FutreDay2 = today.plusDays(100);
		String FutreDayStr2 = getFormattedDateStr(FutreDay2);
		LocalDate FutreDay3 = today.plusDays(200);
		String FutreDayStr3 = getFormattedDateStr(FutreDay3);
		LocalDate FutreDay4 = today.plusDays(300);
		String FutreDayStr4 = getFormattedDateStr(FutreDay4);
		assertTrue(validator.isValid(FutreDayStr1, null));
		assertTrue(validator.isValid(FutreDayStr2, null));
		assertTrue(validator.isValid(FutreDayStr3, null));
		assertTrue(validator.isValid(FutreDayStr4, null));
	}
	@Test
	public void testInValidCurrentDate() {
		LocalDate yesterday = today.minusDays(1);
		String formattedDate = getFormattedDateStr(yesterday);
		assertFalse(validator.isValid(formattedDate, null));
	}
	@Test
	public void testInValidFutureDate() {
		LocalDate PastDate1 = today.minusDays(1);
		String PastDateStr1 = getFormattedDateStr(PastDate1);
		LocalDate PastDate2 = today.minusDays(100);
		String PastDateStr2 = getFormattedDateStr(PastDate2);
		LocalDate PastDate3 = today.minusDays(200);
		String PastDateStr3 = getFormattedDateStr(PastDate3);
		assertFalse(validator.isValid(PastDateStr1, null));
		assertFalse(validator.isValid(PastDateStr2, null));
		assertFalse(validator.isValid(PastDateStr3, null));
	}
	
	
}
