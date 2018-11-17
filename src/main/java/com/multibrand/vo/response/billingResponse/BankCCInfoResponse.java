package com.multibrand.vo.response.billingResponse;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="BankCCInfoResponse")
public class BankCCInfoResponse extends GenericResponse {
	
	private AutoPayDetails[] autoPayDetailsList;

    private List<BankDetails> bankList = null;

    private List<CrCardDetails> ccList = null;

    private java.lang.String errorCode;

    private java.lang.String errorMessage;

	public AutoPayDetails[] getAutoPayDetailsList() {
		return autoPayDetailsList;
	}

	public void setAutoPayDetailsList(AutoPayDetails[] autoPayDetailsList) {
		this.autoPayDetailsList = autoPayDetailsList;
	}

	public List<BankDetails> getBankList() {
		return bankList;
	}

	public void setBankList(List<BankDetails> bankList) {
		this.bankList = bankList;
	}

	public List<CrCardDetails> getCcList() {
		return ccList;
	}

	public void setCcList(List<CrCardDetails> ccList) {
		this.ccList = ccList;
	}

	public java.lang.String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(java.lang.String errorCode) {
		this.errorCode = errorCode;
	}

	public java.lang.String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(java.lang.String errorMessage) {
		this.errorMessage = errorMessage;
	}
    
    
	
    
    
    
}
