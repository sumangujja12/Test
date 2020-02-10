package com.multibrand.util;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class CommonUtilTest {

	@Test
	public void testValidGetBrandIdFromCompanycodeForTogglz(){
		String brandId = CommonUtil.getBrandIdFromCompanycodeForTogglz("0121", null);		
		assertEquals(brandId, "RE");
		brandId = CommonUtil.getBrandIdFromCompanycodeForTogglz("0271", null);
		assertEquals(brandId, "GM");
		brandId = CommonUtil.getBrandIdFromCompanycodeForTogglz("0391", null);
		assertEquals(brandId, "DP");
	}
	
	@Test
	public void testInvalidGetBrandIdFromCompanycodeForTogglz(){
		String brandId = CommonUtil.getBrandIdFromCompanycodeForTogglz("0122", null);		
		assertEquals(brandId, "");
		
	}
	
	@Test
	public void testValidGetBrandIdFromCompanycodeForCCS(){
		String brandId = CommonUtil.getBrandIdFromCompanycodeForCCS("0121",null);		
		assertEquals(brandId, "RE");
		brandId = CommonUtil.getBrandIdFromCompanycodeForCCS("0271",null);
		assertEquals(brandId, "GR");
		brandId = CommonUtil.getBrandIdFromCompanycodeForCCS("0391",null);
		assertEquals(brandId, "PW");
	}
	
	@Test
	public void testInvalidGetBrandIdFromCompanycodeForCCS(){
		String brandId = CommonUtil.getBrandIdFromCompanycodeForCCS("0122",null);		
		assertEquals(brandId, "");
		
	}
}

