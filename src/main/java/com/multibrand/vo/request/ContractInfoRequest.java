package com.multibrand.vo.request;

import com.multibrand.dto.request.BaseRequest;

public class ContractInfoRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;
	private String accountNumber;
	private String bpNumber;
	private String brandName;
	private String contractId;
	private String esid;
	private String zoneId;
	private String messageId="";
	private String osType="";
	
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	
	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
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
	 * @return the brandName
	 */
	@Override
	public String getBrandId() {
		return brandName;
	}
	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * @return the contractId
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
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
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}
	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	/**
	 * @return the osType
	 */
	public String getOsType() {
		return osType;
	}
	/**
	 * @param osType the osType to set
	 */
	public void setOsType(String osType) {
		this.osType = osType;
	}

	

}
