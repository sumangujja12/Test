package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.multibrand.util.CommonUtil;

@Component
@XmlRootElement(name="CheckPendingMVI")
public class CheckPendingMoveInResponse extends GenericResponse{
	
	private java.lang.String companyCode;

    private java.lang.String contractNumber;
    
    private java.lang.String moveInDate;

    
	public java.lang.String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(java.lang.String companyCode) {
		this.companyCode = companyCode;
	}

	public java.lang.String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(java.lang.String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public java.lang.String getMoveInDate() {
		return moveInDate;
	}

	public void setMoveInDate(java.lang.String moveInDate) {
		
		this.moveInDate = CommonUtil.changeDateFormat(moveInDate);
	}
    
    
    

}
