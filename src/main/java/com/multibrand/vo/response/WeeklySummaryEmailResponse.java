package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="WeeklySummaryEmailResponse")
@XmlSeeAlso({WeeklySummaryEmailResponseList.class})
public class WeeklySummaryEmailResponse extends GenericResponse
{
	private String contractAccountNumber ="";
	private String companyCode ="";
	private List<WeeklySummaryEmailResponseList> wseList;
    private String errorCode ="";
	private String errorMessage ="";
	
	
	public String getContractAccountNumber() {
		return contractAccountNumber;
	}
	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public List<WeeklySummaryEmailResponseList> getWseList() {
		return wseList;
	}
	public void setWseList(List<WeeklySummaryEmailResponseList> wseList) {
		this.wseList = wseList;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
