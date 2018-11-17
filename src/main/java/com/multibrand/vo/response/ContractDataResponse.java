package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ContractDataResponse extends GenericResponse{

	 private java.lang.String companyCode;

	 private TosContractData[] contractDataCollection;

	public java.lang.String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(java.lang.String companyCode) {
		this.companyCode = companyCode;
	}

	public TosContractData[] getContractDataCollection() {
		return contractDataCollection;
	}

	public void setContractDataCollection(
			TosContractData[] contractDataCollection) {
		this.contractDataCollection = contractDataCollection;
	}
	 
	 
	
}
