package com.multibrand.vo.response;

public class CampEnvironmentDO {
	
	private String productCode="";
	
	private String calcOperand="";
	private String value="";
	
	public String getCalcOperand() {
		return calcOperand;
	}
	public void setCalcOperand(String calcOperand) {
		this.calcOperand = calcOperand;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
}
