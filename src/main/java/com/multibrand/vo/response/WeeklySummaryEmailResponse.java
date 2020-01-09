package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="WeeklySummaryEmailResponse")
@XmlSeeAlso({WseDO.class,WseResponse.class})
public class WeeklySummaryEmailResponse extends GenericResponse
{
	@SerializedName("contract_account_number")
	@Expose
	private String contractAccountNumber;
	@SerializedName("company_code")
	@Expose
	private String companyCode;
	@SerializedName("wseList")
	@Expose
	private List<WseResponse> wseList = null;
	@SerializedName("error_code")
	@Expose
	private String errorCode;
	@SerializedName("error_message")
	@Expose
	private String errorMessage;
	
	
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
	public List<WseResponse> getWseList() {
		return wseList;
	}
	public void setWseList(List<WseResponse> wseList) {
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
