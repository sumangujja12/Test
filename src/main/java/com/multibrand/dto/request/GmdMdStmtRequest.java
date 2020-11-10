package com.multibrand.dto.request;

public class GmdMdStmtRequest implements FormEntityRequest {

	private String companyCode;
	private String contractAccountNumber;
	private String esiId;
	private String stmtFromDate;
	private String fromMonth;
	private String fromYear;
	private String stmtType; // M/D
	private String stmtToDate;
	private String toMonth;
	private String toYear;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getContractAccountNumber() {
		return contractAccountNumber;
	}

	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}

	public String getEsiId() {
		return esiId;
	}

	public void setEsiId(String esiId) {
		this.esiId = esiId;
	}

	public String getFromMonth() {
		return fromMonth;
	}

	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getStmtType() {
		return stmtType;
	}

	public void setStmtType(String stmtType) {
		this.stmtType = stmtType;
	}

	public String getToMonth() {
		return toMonth;
	}

	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public String getStmtFromDate() {
		return stmtFromDate;
	}

	public void setStmtFromDate(String stmtFromDate) {
		this.stmtFromDate = stmtFromDate;
	}

	public String getStmtToDate() {
		return stmtToDate;
	}

	public void setStmtToDate(String stmtToDate) {
		this.stmtToDate = stmtToDate;
	}
	
	
	

}
