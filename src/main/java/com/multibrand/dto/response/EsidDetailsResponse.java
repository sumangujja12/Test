package com.multibrand.dto.response;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.TDSPDO;

/**
 * Represent response structure of ESID details.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.x and Jersey 1.17
 */
public class EsidDetailsResponse extends GenericResponse implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String esidNumber;
	private String esidTDSP;
	private String switchHoldStatus;
	private String meterType;	

	private List<TDSPDO> tdspData;	

	/**
	 * Default constructor.
	 */
	public EsidDetailsResponse() {

	}

	/**
	 * @return the esidNumber
	 */
	public String getEsidNumber() {
		return esidNumber;
	}

	/**
	 * @param esidNumber
	 *            the esidNumber to set
	 */
	public void setEsidNumber(String esidNumber) {
		this.esidNumber = esidNumber;
	}

	/**
	 * @return the esidTDSP
	 */
	public String getEsidTDSP() {
		return esidTDSP;
	}

	/**
	 * @param esidTDSP
	 *            the esidTDSP to set
	 */
	public void setEsidTDSP(String esidTDSP) {
		this.esidTDSP = esidTDSP;
	}

	/**
	 * @return the switchHoldStatus
	 */
	public String getSwitchHoldStatus() {
		return switchHoldStatus;
	}

	/**
	 * @param switchHoldStatus
	 *            the switchHoldStatus to set
	 */
	public void setSwitchHoldStatus(String switchHoldStatus) {
		this.switchHoldStatus = switchHoldStatus;
	}

	/**
	 * @return the meterType
	 */
	public String getMeterType() {
		return meterType;
	}

	/**
	 * @param meterType
	 *            the meterType to set
	 */
	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}
	

	public List<TDSPDO> getTdspData() {
		return tdspData;
	}

	public void setTdspData(List<TDSPDO> tdspData) {
		this.tdspData = tdspData;
	}

	/**
	 * @return String representation of this request.
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
