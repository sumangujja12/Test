package com.multibrand.request.validation;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationDescriptor;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(singleThreaded = true)
public class ChannelTypeValidatorTest {
	
	private ChannelTypeValidator validator;
	
	@BeforeMethod
	public void setup(){
		AnnotationDescriptor<ChannelType> descriptor = new AnnotationDescriptor<ChannelType>(ChannelType.class);
		ChannelType channelType = AnnotationFactory.create(descriptor);
		this.validator = new ChannelTypeValidator();
		this.validator.initialize(channelType);
	}
	
	@Test
	public void testValidChannelType() {
		assertTrue( validator.isValid("", null ));
		assertTrue( validator.isValid(null, null));
		assertTrue( validator.isValid("AA", null));
		assertTrue( validator.isValid("WEB", null));
		assertTrue( validator.isValid("AFF", null));
	}
	@Test
	public void testInValidChannelType() {
		assertFalse(validator.isValid( "ASDF", null));
	}
}
