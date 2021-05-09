package com.multibrand.request.validation;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationDescriptor;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(singleThreaded = true)
public class AgeValidatorTest {
	
	private AgeValidator validator;
	
	@BeforeMethod
	public void setup(){
		AnnotationDescriptor<ValidAge> descriptor = new AnnotationDescriptor<ValidAge>(ValidAge.class);
		ValidAge validAge = AnnotationFactory.create(descriptor);
		this.validator = new AgeValidator();
		this.validator.initialize(validAge);
	}
	
	@Test
	public void testValidAge() {
		assertTrue( validator.isValid("04121956", null));
		
	}
	@Test
	public void testInValidAge() {
		assertFalse(validator.isValid( "08122003", null));
	}
}
