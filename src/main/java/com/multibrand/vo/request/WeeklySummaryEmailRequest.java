package com.multibrand.vo.request;

import org.hibernate.validator.constraints.NotEmpty;

import com.multibrand.dto.request.FormEntityRequest;
import com.multibrand.util.Constants;


public class WeeklySummaryEmailRequest implements FormEntityRequest, Constants {
	
	@NotEmpty(message="Contract Account Number is null")		
	private String contractAccountNumber;

	@NotEmpty(message="Company Code is null")	
	private String companyCode;
	
	private int wseReportTotaldays;
	
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
	public int getWseReportTotaldays() {
		return wseReportTotaldays;
	}
	public void setWseReportTotaldays(int wseReportTotaldays) {
		this.wseReportTotaldays = wseReportTotaldays;
	}
	
}
