package com.multibrand.vo.request.historyRequest;

public class PlanHistoryRequestVO {

	private String accountNumber;
	private String legacyAccountNumber;
	private String startDate;
	private String endDate;
	private String conversionDate;
	private String languageCode;
	private String companyCode;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getLegacyAccountNumber() {
		return legacyAccountNumber;
	}

	public void setLegacyAccountNumber(String legacyAccountNumber) {
		this.legacyAccountNumber = legacyAccountNumber;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getConversionDate() {
		return conversionDate;
	}

	public void setConversionDate(String conversionDate) {
		this.conversionDate = conversionDate;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

}
