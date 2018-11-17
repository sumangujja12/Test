package com.multibrand.vo.response;

/**
 * 
 * @author dkrishn1
 *
 */
public class BpData {
	private String businessPartner="";
	private String contractAccount="";
	private String vertrag="";
	private String houseNumber="";
	public String getBusinessPartner() {
		return businessPartner;
	}
	public void setBusinessPartner(String businessPartner) {
		this.businessPartner = businessPartner;
	}
	public String getContractAccount() {
		return contractAccount;
	}
	public void setContractAccount(String contractAccount) {
		this.contractAccount = contractAccount;
	}
	public String getVertrag() {
		return vertrag;
	}
	public void setVertrag(String vertrag) {
		this.vertrag = vertrag;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	@Override
	public String toString() {
		return "BpData [businessPartner=" + businessPartner + ", contractAccount=" + contractAccount + ", vertrag="
				+ vertrag + ", houseNumber=" + houseNumber + "]";
	}
	
}
