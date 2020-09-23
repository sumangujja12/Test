package com.multibrand.vo.response;
/**
 * Start | PBI 52935 | MBAR: Sprint 17 -ERCOT ESID LOOKUP REST IMPL | Jyothi | 9/21/2020
 */
public class Address {
 
    public String streetOverflow;
    public int zip; 
    public String state;
    public String street;
    public String city;
    
	public String getStreetOverflow() {
		return streetOverflow;
	}
	public void setStreetOverflow(String streetOverflow) {
		this.streetOverflow = streetOverflow;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
