package com.multibrand.vo.response.billingResponse;

public class GMEContractAccountDO {
	
	private GMEContractDO[] listOfContracts;
    private java.lang.String strBPNumber;
    private java.lang.String strCANumber;
  
    public GMEContractDO[] getListOfContracts() {
		return listOfContracts;
	}

	public void setListOfContracts(GMEContractDO[] listOfContracts) {
		this.listOfContracts = listOfContracts;
	}

	public java.lang.String getStrBPNumber() {
		return strBPNumber;
	}

	public void setStrBPNumber(java.lang.String strBPNumber) {
		this.strBPNumber = strBPNumber;
	}


	public java.lang.String getStrCANumber() {
		return strCANumber;
	}

	public void setStrCANumber(java.lang.String strCANumber) {
		this.strCANumber = strCANumber;
	}
}
