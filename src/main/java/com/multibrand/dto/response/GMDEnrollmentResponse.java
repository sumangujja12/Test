package com.multibrand.dto.response;

import com.multibrand.vo.response.GenericResponse;

/**
 * 
 */


public class GMDEnrollmentResponse extends GenericResponse {

	private String trackingId = "";
	private String idocNumber = "";
	private String contractAccountNumber = "";
	private String caCheckDigit = "";
	private String businessPartnerNumber = "";
	/**
	 * @return the trackingId
	 */
	public String getTrackingId() {
		return trackingId;
	}
	/**
	 * @param trackingId the trackingId to set
	 */
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	/**
	 * @return the idocNumber
	 */
	public String getIdocNumber() {
		return idocNumber;
	}
	/**
	 * @param idocNumber the idocNumber to set
	 */
	public void setIdocNumber(String idocNumber) {
		this.idocNumber = idocNumber;
	}
	/**
	 * @return the contractAccountNumber
	 */
	public String getContractAccountNumber() {
		return contractAccountNumber;
	}
	/**
	 * @param contractAccountNumber the contractAccountNumber to set
	 */
	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}
	/**
	 * @return the caCheckDigit
	 */
	public String getCaCheckDigit() {
		return caCheckDigit;
	}
	/**
	 * @param caCheckDigit the caCheckDigit to set
	 */
	public void setCaCheckDigit(String caCheckDigit) {
		this.caCheckDigit = caCheckDigit;
	}
	/**
	 * @return the businessPartnerNumber
	 */
	public String getBusinessPartnerNumber() {
		return businessPartnerNumber;
	}
	/**
	 * @param businessPartnerNumber the businessPartnerNumber to set
	 */
	public void setBusinessPartnerNumber(String businessPartnerNumber) {
		this.businessPartnerNumber = businessPartnerNumber;
	}

	

}
