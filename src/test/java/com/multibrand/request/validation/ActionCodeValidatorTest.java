package com.multibrand.request.validation;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationDescriptor;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActionCodeValidatorTest {
	
	private ActionCodeValidator validator;
	
	@BeforeMethod
	public void setup(){
		AnnotationDescriptor<ActionCode> descriptor = new AnnotationDescriptor<ActionCode>(ActionCode.class);
		ActionCode actionCode = AnnotationFactory.create(descriptor);
		this.validator = new ActionCodeValidator();
		this.validator.initialize(actionCode);
	}
	
	@Test
	public void testValidActionCode() {
		assertTrue( validator.isValid("CE", null));
		assertTrue( validator.isValid("AE", null));
		assertTrue( validator.isValid("PE", null));
		assertTrue( validator.isValid("SE", null));
	}
	@Test
	public void testInValidActionCode() {
		assertFalse(validator.isValid( "ASDF", null));
	}
}
