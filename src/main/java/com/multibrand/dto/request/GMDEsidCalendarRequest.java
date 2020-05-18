package com.multibrand.dto.request;

import java.io.Serializable;

public class GMDEsidCalendarRequest implements FormEntityRequest, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyCode ="";
	private String brandName ="";
	private String languageCode ="";
	private String esiId ="";
	private String transactionType ="";
	private String transactionId ="";
	private String tdspCode ="";
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
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}
	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * @return the languageCode
	 */
	public String getLanguageCode() {
		return languageCode;
	}
	/**
	 * @param languageCode the languageCode to set
	 */
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	/**
	 * @return the esiId
	 */
	public String getEsiId() {
		return esiId;
	}
	/**
	 * @param esiId the esiId to set
	 */
	public void setEsiId(String esiId) {
		this.esiId = esiId;
	}
	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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
	
	
	
}
