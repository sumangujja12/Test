package com.multibrand.dto.request;

public class UsageHistoryRequest {
	
	private String esiid;
	private String contractId;
	private String contractAccNumber;
	private String fromDate;
	private String toDate;
	
	public String getEsiid() {
		return esiid;
	}
	public void setEsiid(String esiid) {
		this.esiid = esiid;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getContractAccNumber() {
		return contractAccNumber;
	}
	public void setContractAccNumber(String contractAccNumber) {
		this.contractAccNumber = contractAccNumber;
	}
	
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
}
