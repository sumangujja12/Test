package com.multibrand.request.validation;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationDescriptor;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RepetitiveDigitsValidatorTest {
	
	private RepetitiveDigitValidator validator;
	
	@BeforeMethod
	public void setup(){
		AnnotationDescriptor<RepetitiveDigitCheck> descriptor = new AnnotationDescriptor<RepetitiveDigitCheck>(RepetitiveDigitCheck.class);
		RepetitiveDigitCheck repetitiveDigitCheck = AnnotationFactory.create(descriptor);
		this.validator = new RepetitiveDigitValidator();
		this.validator.initialize(repetitiveDigitCheck);
	}
	
	@Test
	public void testValidActionCode() {
		assertTrue( validator.isValid("123123123", null));
		
	}
	
	
	@Test
	public void testInValidActionCode() {
		
		assertFalse( validator.isValid("000000000", null));
		assertFalse( validator.isValid("111111111", null));
		assertFalse( validator.isValid("222222222", null));
		assertFalse( validator.isValid("333333333", null));
		assertFalse( validator.isValid("444444444", null));
		assertFalse( validator.isValid("555555555", null));
		assertFalse( validator.isValid("666666666", null));
		assertFalse( validator.isValid("777777777", null));
		assertFalse( validator.isValid("888888888", null));
		assertFalse( validator.isValid("999999999", null));
	}
}
