package com.multibrand.dto.request;

import org.hibernate.validator.constraints.NotEmpty;

import com.multibrand.util.Constants;

public class CslrUpdateBillinlgAddrRequest implements FormEntityRequest, Constants {
	
	
	@NotEmpty(message="Lease ID is null")		
	private String leaseId;

	@NotEmpty(message="BP Number is null")	
	private String bpNumber;

	@NotEmpty(message="CA Number is null")	
	private String caNumber;
	
	private String addrNumber;
	private String city;
	private String country;
	private String streetNum;
	private String unitNum;
	private String nameCo;
	private String zipCode;
	private String poBox;
	private String state;
	private String streetName;
	/**
	 * @return the leaseId
	 */
	public String getLeaseId() {
		return leaseId;
	}
	/**
	 * @param leaseId the leaseId to set
	 */
	public void setLeaseId(String leaseId) {
		this.leaseId = leaseId;
	}
	/**
	 * @return the bpNumber
	 */
	public String getBpNumber() {
		return bpNumber;
	}
	/**
	 * @param bpNumber the bpNumber to set
	 */
	public void setBpNumber(String bpNumber) {
		this.bpNumber = bpNumber;
	}
	/**
	 * @return the caNumber
	 */
	public String getCaNumber() {
		return caNumber;
	}
	/**
	 * @param caNumber the caNumber to set
	 */
	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}
	/**
	 * @return the addrNumber
	 */
	public String getAddrNumber() {
		return addrNumber;
	}
	/**
	 * @param addrNumber the addrNumber to set
	 */
	public void setAddrNumber(String addrNumber) {
		this.addrNumber = addrNumber;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the streetNum
	 */
	public String getStreetNum() {
		return streetNum;
	}
	/**
	 * @param streetNum the streetNum to set
	 */
	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}
	/**
	 * @return the unitNum
	 */
	public String getUnitNum() {
		return unitNum;
	}
	/**
	 * @param unitNum the unitNum to set
	 */
	public void setUnitNum(String unitNum) {
		this.unitNum = unitNum;
	}
	/**
	 * @return the nameCo
	 */
	public String getNameCo() {
		return nameCo;
	}
	/**
	 * @param nameCo the nameCo to set
	 */
	public void setNameCo(String nameCo) {
		this.nameCo = nameCo;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the poBox
	 */
	public String getPoBox() {
		return poBox;
	}
	/**
	 * @param poBox the poBox to set
	 */
	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}
	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	
}
