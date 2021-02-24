package com.multibrand.vo.response.lucenesearch;

import com.multibrand.util.StreamLuceneConstants;

/** This class will be used in GSON mapping the Search Response 
 * 
 * @author RKiran
 */

public class Address {
	private String line1 = "";

	private String line2 = "";

	private String unitNumber = "";

	private String city = "";

	private String stateAbbreviation = "";

	private String postalCode5 = "";

	private String postalCodePlus4 = "";

	private boolean isDeliverable;

	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine1() {
		return this.line1;
	}
	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}
	public String getUnitNumber() {
		return this.unitNumber;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return this.city;
	}
	public void setStateAbbreviation(String stateAbbreviation) {
		this.stateAbbreviation = stateAbbreviation;
	}
	public String getStateAbbreviation() {
		return this.stateAbbreviation;
	}
	public void setPostalCode5(String postalCode5) {
		this.postalCode5 = postalCode5;
	}
	public String getPostalCode5() {
		return this.postalCode5;
	}
	public void setPostalCodePlus4(String postalCodePlus4) {
		this.postalCodePlus4 = postalCodePlus4;
	}
	public String getPostalCodePlus4() {
		return this.postalCodePlus4;
	}
	public void setIsDeliverable(boolean isDeliverable) {
		this.isDeliverable = isDeliverable;
	}
	public boolean getIsDeliverable() {
		return this.isDeliverable;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((line1 == null) ? 0 : line1.hashCode());
		result = prime * result + ((line2 == null) ? 0 : line2.hashCode());
		result = prime * result + ((postalCode5 == null) ? 0 : postalCode5.hashCode());
		result = prime * result + ((postalCodePlus4 == null) ? 0 : postalCodePlus4.hashCode());
		result = prime * result + ((stateAbbreviation == null) ? 0 : stateAbbreviation.hashCode());
		result = prime * result + ((unitNumber == null) ? 0 : unitNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;

		return (

			getSanitiziedString(this.city).equals(getSanitiziedString(other.getCity())) &&
			getSanitiziedString(this.line1).equals(getSanitiziedString(other.getLine1())) &&
			getSanitiziedString(this.line2).equals(getSanitiziedString(other.getLine2())) &&
			getSanitiziedString(this.postalCode5).equals(getSanitiziedString(other.getPostalCode5())) &&
			getSanitiziedString(this.postalCodePlus4).equals(getSanitiziedString(other.getPostalCodePlus4())) &&
			getSanitiziedString(this.stateAbbreviation).equals(getSanitiziedString(other.getStateAbbreviation())) &&
			getSanitiziedString(this.unitNumber).equals(getSanitiziedString(other.getUnitNumber()))
		);

	}

	public String getSanitiziedString(String inputStr) {
		if (inputStr == null || inputStr.isEmpty()) {
			return StreamLuceneConstants.EMPTY_STR;
		} else {
			inputStr = inputStr.trim().toLowerCase();
		}
		return inputStr;
	}

}