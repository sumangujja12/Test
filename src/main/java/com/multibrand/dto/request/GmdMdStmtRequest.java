package com.multibrand.dto.request;


public class GmdMdStmtRequest implements FormEntityRequest {

	private String companyCode;
	private String contractAccountNumber;
	private String esiId;
	private String stmtFromDate;
	private String stmtToDate;
	private String stmtType; // M/D
	
	
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

	public String getStmtType() {
		return stmtType;
	}

	public void setStmtType(String stmtType) {
		this.stmtType = stmtType;
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
