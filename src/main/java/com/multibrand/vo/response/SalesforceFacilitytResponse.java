package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="FACILITY")
public class SalesforceFacilitytResponse  {
	

	@SerializedName("FACILITYZIP")
	private String facilityZip;
	
	@SerializedName("FACILITYTOTALPRODUCTIONCAPACITY")
	private String facilityTotalProduction;
	
	@SerializedName("FACILITYTOTALPANELCOUNT")
	private String facilityTotalPanel;	
	

	@SerializedName("FACILITYSTREET")
	private String facilityStreet;	
	
	@SerializedName("FACILITYSTATE")
	private String facilityState;	
	
	
	@SerializedName("FACILITYSTAGE")
	private String facilityStage;
	
	@SerializedName("FACILITYNAME")
	private String facilityName;
	
	@SerializedName("FACILITYCOUNTRY")
	private String facilityCountry;
	
	@SerializedName("FACILITYCITY")
	private String facilityCity;

	/**
	 * @return the facilityZip
	 */
	public String getFacilityZip() {
		return facilityZip;
	}

	/**
	 * @param facilityZip the facilityZip to set
	 */
	public void setFacilityZip(String facilityZip) {
		this.facilityZip = facilityZip;
	}

	/**
	 * @return the facilityTotalProduction
	 */
	public String getFacilityTotalProduction() {
		return facilityTotalProduction;
	}

	/**
	 * @param facilityTotalProduction the facilityTotalProduction to set
	 */
	public void setFacilityTotalProduction(String facilityTotalProduction) {
		this.facilityTotalProduction = facilityTotalProduction;
	}

	/**
	 * @return the facilityTotalPanel
	 */
	public String getFacilityTotalPanel() {
		return facilityTotalPanel;
	}

	/**
	 * @param facilityTotalPanel the facilityTotalPanel to set
	 */
	public void setFacilityTotalPanel(String facilityTotalPanel) {
		this.facilityTotalPanel = facilityTotalPanel;
	}

	/**
	 * @return the facilityStreet
	 */
	public String getFacilityStreet() {
		return facilityStreet;
	}

	/**
	 * @param facilityStreet the facilityStreet to set
	 */
	public void setFacilityStreet(String facilityStreet) {
		this.facilityStreet = facilityStreet;
	}

	/**
	 * @return the facilityState
	 */
	public String getFacilityState() {
		return facilityState;
	}

	/**
	 * @param facilityState the facilityState to set
	 */
	public void setFacilityState(String facilityState) {
		this.facilityState = facilityState;
	}

	/**
	 * @return the facilityStage
	 */
	public String getFacilityStage() {
		return facilityStage;
	}

	/**
	 * @param facilityStage the facilityStage to set
	 */
	public void setFacilityStage(String facilityStage) {
		this.facilityStage = facilityStage;
	}

	/**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return facilityName;
	}

	/**
	 * @param facilityName the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	/**
	 * @return the facilityCountry
	 */
	public String getFacilityCountry() {
		return facilityCountry;
	}

	/**
	 * @param facilityCountry the facilityCountry to set
	 */
	public void setFacilityCountry(String facilityCountry) {
		this.facilityCountry = facilityCountry;
	}

	/**
	 * @return the facilityCity
	 */
	public String getFacilityCity() {
		return facilityCity;
	}

	/**
	 * @param facilityCity the facilityCity to set
	 */
	public void setFacilityCity(String facilityCity) {
		this.facilityCity = facilityCity;
	}		
}
