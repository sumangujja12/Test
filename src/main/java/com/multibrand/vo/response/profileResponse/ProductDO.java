package com.multibrand.vo.response.profileResponse;

public class ProductDO {
	private String accountNumber;
	private String bpNumber;
	private String esid;
	private String contractStartDate;
	private Products[] products;
	
	public String getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBpNumber() {
		return bpNumber;
	}
	public void setBpNumber(String bpNumber) {
		this.bpNumber = bpNumber;
	}
	public String getEsid() {
		return esid;
	}
	public void setEsid(String esid) {
		this.esid = esid;
	}
	public Products[] getProducts() {
		return products;
	}
	public void setProducts(Products[] products) {
		this.products = products;
	}
}
