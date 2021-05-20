package com.multibrand.dto.request;

import javax.validation.constraints.Size;

import com.multibrand.request.validation.NotEmpty;

public class SalesTDSPRequest extends SalesBaseRequest{

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Size(max = 10)
	private String servStreetNum;

	@NotEmpty
	@Size(max = 60)
	private String servStreetName;

	@Size(max = 10)
	private String servStreetAptNum;

	@NotEmpty
	@Size(min = 5, max = 10)
	private String servZipCode;

	@Size(max = 3)
	private String transactionType; // MVI or SWI

	@Size(max = 32)
	private String esid;

	public String getServStreetNum() {
		return servStreetNum;
	}

	public void setServStreetNum(String servStreetNum) {
		this.servStreetNum = servStreetNum;
	}

	public String getServStreetName() {
		return servStreetName;
	}

	public void setServStreetName(String servStreetName) {
		this.servStreetName = servStreetName;
	}

	public String getServStreetAptNum() {
		return servStreetAptNum;
	}

	public void setServStreetAptNum(String servStreetAptNum) {
		this.servStreetAptNum = servStreetAptNum;
	}

	public String getServZipCode() {
		return servZipCode;
	}

	public void setServZipCode(String servZipCode) {
		this.servZipCode = servZipCode;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getEsid() {
		return esid;
	}

	public void setEsid(String esid) {
		this.esid = esid;
	}

	
}
