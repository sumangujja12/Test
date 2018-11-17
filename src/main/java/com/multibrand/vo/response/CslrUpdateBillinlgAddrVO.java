package com.multibrand.vo.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="cslrUpdateBillingAddr")
public class CslrUpdateBillinlgAddrVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4413013530586875352L;

	@SerializedName("LeaseId")
	private String leaseId;

	@SerializedName("Partner")
	private String bpNumber;
	
	@SerializedName("Vkont")
	private String caNumber;

	@SerializedName("Addrnumber")
	private String addrNumber;

	@SerializedName("City1")
	private String city;

	@SerializedName("Country")
	private String country;

	@SerializedName("HouseNum1")
	private String streetNum;

	@SerializedName("HouseNum2")
	private String unitNum;
	
	@SerializedName("NameCo")
	private String nameCo;
	
	@SerializedName("PostCode1")
	private String zipCode;

	@SerializedName("PoBox")
	private String poBox;

	@SerializedName("Region")
	private String state;

	@SerializedName("Street")
	private String streetName;

	@SerializedName("XMsg")
	private String ccsResponseStatusMessage;

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
	 * @return the ccsResponseStatusMessage
	 */
	public String getCcsResponseStatusMessage() {
		return ccsResponseStatusMessage;
	}

	/**
	 * @param ccsResponseStatusMessage the ccsResponseStatusMessage to set
	 */
	public void setCcsResponseStatusMessage(String ccsResponseStatusMessage) {
		this.ccsResponseStatusMessage = ccsResponseStatusMessage;
	}
}
