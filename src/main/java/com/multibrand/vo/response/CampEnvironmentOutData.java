package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CampEnvironmentOutData")
public class CampEnvironmentOutData {

	private java.lang.String calcOperand;

    private java.lang.String ccsProductCd;

    private java.math.BigDecimal value;

	public java.lang.String getCalcOperand() {
		return calcOperand;
	}

	public void setCalcOperand(java.lang.String calcOperand) {
		this.calcOperand = calcOperand;
	}

	public java.lang.String getCcsProductCd() {
		return ccsProductCd;
	}

	public void setCcsProductCd(java.lang.String ccsProductCd) {
		this.ccsProductCd = ccsProductCd;
	}

	public java.math.BigDecimal getValue() {
		return value;
	}

	public void setValue(java.math.BigDecimal value) {
		this.value = value;
	}
    
    
	
	
}
