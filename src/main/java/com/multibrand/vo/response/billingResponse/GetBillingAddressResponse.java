package com.multibrand.vo.response.billingResponse;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.multibrand.vo.response.GenericResponse;

/**
 * Response class for GetBillingAddress service
 * @author ahanda1
 *
 */

@XmlRootElement(name="GetBillingAddress")
@Component
public class GetBillingAddressResponse extends GenericResponse{
	
	private String strAddressID;
	private String strStreetNum;
	private String strStreetName;
	private String strCity;
	private String strState;
	private String strZip;
	private String strPOBox;
	private String strCountry;
	private String strApartNum;
	
	
	public String getStrAddressID() {
		return strAddressID;
	}
	public void setStrAddressID(String strAddressID) {
		this.strAddressID = strAddressID;
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
	public String getStrPOBox() {
		return strPOBox;
	}
	public void setStrPOBox(String strPOBox) {
		this.strPOBox = strPOBox;
	}
	public String getStrCountry() {
		return strCountry;
	}
	public void setStrCountry(String strCountry) {
		this.strCountry = strCountry;
	}
	public String getStrApartNum() {
		return strApartNum;
	}
	public void setStrApartNum(String strApartNum) {
		this.strApartNum = strApartNum;
	}
	
	
	

}
