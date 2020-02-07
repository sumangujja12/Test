package com.multibrand.util;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class CommonUtilTest {

	@Test
	public void testValidGetBrandIdFromCompanycodeForTogglz(){
		String brandId = CommonUtil.getBrandIdFromCompanycodeForTogglz("0121");		
		assertEquals(brandId, "RE");
		brandId = CommonUtil.getBrandIdFromCompanycodeForTogglz("0271");
		assertEquals(brandId, "GM");
		brandId = CommonUtil.getBrandIdFromCompanycodeForTogglz("0391");
		assertEquals(brandId, "DP");
	}
	
	@Test
	public void testInvalidGetBrandIdFromCompanycodeForTogglz(){
		String brandId = CommonUtil.getBrandIdFromCompanycodeForTogglz("0122");		
		assertEquals(brandId, "");
		
	}
	
	@Test
	public void testValidGetBrandIdFromCompanycodeForCCS(){
		String brandId = CommonUtil.getBrandIdFromCompanycodeForCCS("0121");		
		assertEquals(brandId, "RE");
		brandId = CommonUtil.getBrandIdFromCompanycodeForCCS("0271");
		assertEquals(brandId, "GR");
		brandId = CommonUtil.getBrandIdFromCompanycodeForCCS("0391");
		assertEquals(brandId, "PW");
	}
	
	@Test
	public void testInvalidGetBrandIdFromCompanycodeForCCS(){
		String brandId = CommonUtil.getBrandIdFromCompanycodeForCCS("0122");		
		assertEquals(brandId, "");
		
	}
}

