package com.multibrand.dto.response;

import java.io.Serializable;

/**
 * @author rpendur1
 *
 */
public class TCSBPDetailsDTO implements Serializable{
	
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 2674198045546600652L;
	private String bpNumber;
	private String caNumber;
	private String companyCode;
	private String leaseId;
	
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
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
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
}