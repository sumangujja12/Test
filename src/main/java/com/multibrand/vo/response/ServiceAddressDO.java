package com.multibrand.vo.response;

import java.io.Serializable;

public class ServiceAddressDO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String strStreetNum;
	private String strStreetName;
	private String strCity;
	private String strState;
	private String strZip;
	private String strZipComplete;
	private String strApartNum;
	public String getStrStreetNum() {
		return strStreetNum;
	}
	public void setStrStreetNum(String strStreetNum) {
		this.strStreetNum = strStreetNum;
	}
	public String getStrStreetName() {
		return strStreetName;
	}
	public void setStrStreetName(String strStreetName) {
		this.strStreetName = strStreetName;
	}
	public String getStrCity() {
		return strCity;
	}
	public void setStrCity(String strCity) {
		this.strCity = strCity;
	}
	public String getStrState() {
		return strState;
	}
	public void setStrState(String strState) {
		this.strState = strState;
	}
	public String getStrZip() {
		return strZip;
	}
	public void setStrZip(String strZip) {
		this.strZip = strZip;
	}
	public String getStrZipComplete() {
		return strZipComplete;
	}
	public void setStrZipComplete(String strZipComplete) {
		this.strZipComplete = strZipComplete;
	}
	public String getStrApartNum() {
		return strApartNum;
	}
	public void setStrApartNum(String strApartNum) {
		this.strApartNum = strApartNum;
	}
	
	
	
}
