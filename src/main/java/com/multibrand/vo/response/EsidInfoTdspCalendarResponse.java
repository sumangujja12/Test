package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="EsidInfoTdspCalendarResponse")
public class EsidInfoTdspCalendarResponse extends GenericResponse {
	private String esid;
	private String tdspCode;
	private String availableDates;
	private String switchHoldFlag; //ON or blank
	private String tdspFee;
	private String meterType;
	
	/**
	 * @return the esid
	 */
	public String getEsid() {
		return esid;
	}
	/**
	 * @param esid the esid to set
	 */
	public void setEsid(String esid) {
		this.esid = esid;
	}
	/**
	 * @return the tdspCode
	 */
	public String getTdspCode() {
		return tdspCode;
	}
	/**
	 * @param tdspCode the tdspCode to set
	 */
	public void setTdspCode(String tdspCode) {
		this.tdspCode = tdspCode;
	}
	/**
	 * @return the availableDates
	 */
	public String getAvailableDates() {
		return availableDates;
	}
	/**
	 * @param availableDates the availableDates to set
	 */
	public void setAvailableDates(String availableDates) {
		this.availableDates = availableDates;
	}
	/**
	 * @return the switchHoldFlag
	 */
	public String getSwitchHoldFlag() {
		return switchHoldFlag;
	}
	/**
	 * @param switchHoldFlag the switchHoldFlag to set
	 */
	public void setSwitchHoldFlag(String switchHoldFlag) {
		this.switchHoldFlag = switchHoldFlag;
	}
	/**
	 * @return the tdspFee
	 */
	public String getTdspFee() {
		return tdspFee;
	}
	/**
	 * @param tdspFee the tdspFee to set
	 */
	public void setTdspFee(String tdspFee) {
		this.tdspFee = tdspFee;
	}
	/**
	 * @return the meterType
	 */
	public String getMeterType() {
		return meterType;
	}
	/**
	 * @param meterType the meterType to set
	 */
	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}
}
