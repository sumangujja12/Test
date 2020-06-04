package com.multibrand.vo.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 
 * @author vsood30
 *
 */
public class ESIDData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String esidNumber;
	private String premiseType;
	private String esidStatus;
	private String esidClass;
	private String esidDeposit;
	private String esidTDSP;
	private String tdspCodeCCS;
	
	private String meterType;
	private String recentDisconnectFlag;
	private String switchHoldStatus;
	private String blockStatus;
	private String esidStatusBrand;
	
	public String getEsidNumber() {
		return esidNumber;
	}


	public void setEsidNumber(String esidNumber) {
		this.esidNumber = esidNumber;
	}


	public String getPremiseType() {
		return premiseType;
	}


	public void setPremiseType(String premiseType) {
		this.premiseType = premiseType;
	}


	public String getEsidStatus() {
		return esidStatus;
	}


	public void setEsidStatus(String esidStatus) {
		this.esidStatus = esidStatus;
	}


	public String getEsidClass() {
		return esidClass;
	}


	public void setEsidClass(String esidClass) {
		this.esidClass = esidClass;
	}


	public String getEsidDeposit() {
		return esidDeposit;
	}


	public void setEsidDeposit(String esidDeposit) {
		this.esidDeposit = esidDeposit;
	}


	public String getEsidTDSP() {
		return esidTDSP;
	}


	public void setEsidTDSP(String esidTDSP) {
		this.esidTDSP = esidTDSP;
	}


	public String getTdspCodeCCS() {
		return tdspCodeCCS;
	}


	public void setTdspCodeCCS(String tdspCodeCCS) {
		this.tdspCodeCCS = tdspCodeCCS;
	}


	public String getMeterType() {
		return meterType;
	}


	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}


	public String getRecentDisconnectFlag() {
		return recentDisconnectFlag;
	}


	public void setRecentDisconnectFlag(String recentDisconnectFlag) {
		this.recentDisconnectFlag = recentDisconnectFlag;
	}


	public String getSwitchHoldStatus() {
		return switchHoldStatus;
	}


	public void setSwitchHoldStatus(String switchHoldStatus) {
		this.switchHoldStatus = switchHoldStatus;
	}


	public String getBlockStatus() {
		return blockStatus;
	}


	public void setBlockStatus(String blockStatus) {
		this.blockStatus = blockStatus;
	}
	
	


	public String getEsidStatusBrand() {
		return esidStatusBrand;
	}


	public void setEsidStatusBrand(String esidStatusBrand) {
		this.esidStatusBrand = esidStatusBrand;
	}


	/**
	 * @return String representation of this request.
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
