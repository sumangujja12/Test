package com.multibrand.vo.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.dto.request.FormEntityRequest;
import com.multibrand.request.validation.BasicConstraint;

/*
 * Start | PBI 52935 | MBAR: Sprint 17 -ERCOT ESID LOOKUP REST IMPL | Jyothi | 9/21/2019
 */

public class GetAddressOrEsidFromErcotRequest implements FormEntityRequest , Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String esid;
	
	@NotBlank(groups = BasicConstraint.class)
	private String companyName;
	
	private String zip;
	private String state;
	private String street;
	private String streetNum;
	private String unitNum;
	private String city;
	private String StrSearchType;	

	public String getStreetNum() {
		return streetNum;
	}

	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}

	public String getUnitNum() {
		return unitNum;
	}

	public void setUnitNum(String unitNum) {
		this.unitNum = unitNum;
	}

	public String getStrSearchType() {
		return StrSearchType;
	}

	public void setStrSearchType(String strSearchType) {
		StrSearchType = strSearchType;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
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

	public String getEsid() {
		return esid;
	}

	public void setEsid(String esid) {
		this.esid = esid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}