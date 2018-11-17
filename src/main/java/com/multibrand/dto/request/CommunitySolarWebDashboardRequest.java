package com.multibrand.dto.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.multibrand.util.Constants;

public class CommunitySolarWebDashboardRequest implements FormEntityRequest, Constants, Serializable {
	

	
	@NotEmpty		
	private String leaseId;
	
	private String accessToken;
	
	
	private Boolean accountInfo;
	
	private Boolean userProductionInfo;
	
	private Boolean facilityInfo;
	private Boolean facilityProductionInfo;
	private Boolean contractInfo;
	
	
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
	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}
	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	/**
	 * @return the accountInfo
	 */
	public Boolean getAccountInfo() {
		return accountInfo;
	}
	/**
	 * @param accountInfo the accountInfo to set
	 */
	public void setAccountInfo(Boolean accountInfo) {
		this.accountInfo = accountInfo;
	}
	/**
	 * @return the userProductionInfo
	 */
	public Boolean getUserProductionInfo() {
		return userProductionInfo;
	}
	/**
	 * @param userProductionInfo the userProductionInfo to set
	 */
	public void setUserProductionInfo(Boolean userProductionInfo) {
		this.userProductionInfo = userProductionInfo;
	}
	/**
	 * @return the facilityInfo
	 */
	public Boolean getFacilityInfo() {
		return facilityInfo;
	}
	/**
	 * @param facilityInfo the facilityInfo to set
	 */
	public void setFacilityInfo(Boolean facilityInfo) {
		this.facilityInfo = facilityInfo;
	}
	/**
	 * @return the facilityProductionInfo
	 */
	public Boolean getFacilityProductionInfo() {
		return facilityProductionInfo;
	}
	/**
	 * @param facilityProductionInfo the facilityProductionInfo to set
	 */
	public void setFacilityProductionInfo(Boolean facilityProductionInfo) {
		this.facilityProductionInfo = facilityProductionInfo;
	}
	/**
	 * @return the contractInfo
	 */
	public Boolean getContractInfo() {
		return contractInfo;
	}
	/**
	 * @param contractInfo the contractInfo to set
	 */
	public void setContractInfo(Boolean contractInfo) {
		this.contractInfo = contractInfo;
	}
}
