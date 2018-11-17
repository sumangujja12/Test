package com.multibrand.dto.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

/**
 * @author jyogapa1
 *
 */
@XmlRootElement(name="ValidateAddressResponseDTO")
public class ValidateAddressResponse extends GenericResponse {

	private String streetNum = null;
	private String streetName = null;
	private String aptNum = null;
	private String city = null;
	private String state = null;
	private String zipCode = null;
	private String poBox = null;
	private String country = null;
	
	public ValidateAddressResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the streetNum
	 */
	public String getStreetNum() {
		return streetNum;
	}

	/**
	 * @param streetNum
	 *            the streetNum to set
	 */
	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName
	 *            the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the aptNum
	 */
	public String getAptNum() {
		return aptNum;
	}

	/**
	 * @param aptNum
	 *            the aptNum to set
	 */
	public void setAptNum(String aptNum) {
		this.aptNum = aptNum;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
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
	 * @param poBox
	 *            the poBox to set
	 */
	public void setPoBox(String poBox) {
		this.poBox = poBox;
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
	
	
	

}
