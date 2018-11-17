package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProgramAccountInfoResponse extends GenericResponse {

	private java.lang.String companyCode;

    private java.math.BigDecimal pendingAmount;

	public java.lang.String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(java.lang.String companyCode) {
		this.companyCode = companyCode;
	}

	public java.math.BigDecimal getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(java.math.BigDecimal pendingAmount) {
		this.pendingAmount = pendingAmount;
	}
    
    
}
