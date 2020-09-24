package com.multibrand.vo.response;
/**
 * Start | PBI 52935 | MBAR: Sprint 17 -ERCOT ESID LOOKUP REST IMPL | Jyothi | 9/21/2020
 */
public class Address {
 
    private String StreetOverflow;
    private String Zip; 
    private String State;
    private String Street;
    private String City;
    
	public String getStreetOverflow() {
		return StreetOverflow;
	}
	public void setStreetOverflow(String streetOverflow) {
		StreetOverflow = streetOverflow;
	}
	public String getZip() {
		return Zip;
	}
	public void setZip(String zip) {
		Zip = zip;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}   
	
}
