package com.multibrand.vo.response.billingResponse;

import java.io.Serializable;

public class AddressDO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String strAddressLine;	
	private String strStreetNum;
	private String strStreetName;
	private String strCity;
	private String strState;
	private String strZip;
	private String strZipComplete;
	private String strApartNum;
	private String strCountry="";
	private String trilliumMatchStatus=""; //COMPLETE_MATCH, NO_MATCH, PARTIAL_MATCH
	private String trilliumCallStatus=""; 
	private String strAddressID;
	private String strPOBox;
	
	public String getStrAddressID() {
		return strAddressID;
	}
	public void setStrAddressID(String strAddressID) {
		this.strAddressID = strAddressID;
	}
	public String getStrPOBox() {
		return strPOBox;
	}
	public void setStrPOBox(String strPOBox) {
		this.strPOBox = strPOBox;
	}
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
	public String getStrAddressLine() {
		return strAddressLine;
	}
	public void setStrAddressLine(String strAddressLine) {
		this.strAddressLine = strAddressLine;
	}
	public String getStrApartNum() {
		return strApartNum;
	}
	public void setStrApartNum(String strApartNum) {
		this.strApartNum = strApartNum;
	}
	public String getTrilliumMatchStatus() {
		return trilliumMatchStatus;
	}
	public void setTrilliumMatchStatus(String trilliumMatchStatus) {
		this.trilliumMatchStatus = trilliumMatchStatus;
	}
	public String getTrilliumCallStatus() {
		return trilliumCallStatus;
	}
	public void setTrilliumCallStatus(String trilliumCallStatus) {
		this.trilliumCallStatus = trilliumCallStatus;
	}
	public String getStrZipComplete() {
		return strZipComplete;
	}
	public String getStrCountry() {
		return strCountry;
	}
	public void setStrCountry(String strCountry) {
		this.strCountry = strCountry;
	}
	public void setStrZipComplete(String strZipComplete) {
		this.strZipComplete = strZipComplete;
	}
	
	
}
