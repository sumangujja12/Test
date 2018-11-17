package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement(name="AddressValidateResponse")
@Component
public class AddressValidateResponse extends GenericResponse {
	
	private String aptNumber;
	private String city;
	private String country;
	private String state;
	private String streetName;
	private String streetNum;
	private String zipCode;
	private String POBox;
	private String statusValue;
	
	
	public String getStatusValue() {
		return statusValue;
	}
	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}
	@XmlElement(name="strAptNum")
	public String getAptNumber() {
		return aptNumber;
	}
	public void setAptNumber(String aptNumber) {
		this.aptNumber = aptNumber;
	}
	@XmlElement(name="strCity")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@XmlElement(name="strCountry")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@XmlElement(name="strState")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@XmlElement(name="strStreetName")
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	@XmlElement(name="strStreetNum")
	public String getStreetNum() {
		return streetNum;
	}
	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}
	@XmlElement(name="strZip")
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPOBox() {
		return POBox;
	}
	public void setPOBox(String pOBox) {
		POBox = pOBox;
	}
	
	
	
	
}
