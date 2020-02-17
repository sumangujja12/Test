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


	/**
	 * @return String representation of this request.
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
