package com.multibrand.dto.request;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import com.multibrand.util.Constants;

public class SalesforceDashboardRequest implements Constants, Serializable {
	

	
	@SerializedName("LeaseId")
	private String leaseId;
	
	@SerializedName("GetAccount")	
	private Boolean getAccount;
	
	@SerializedName("GetUserProduction")
	private Boolean getUserProduction;
	
	@SerializedName("GetFacility")
	private Boolean getFacility;
	
	@SerializedName("GetFacilityProduction")
	private Boolean getFacilityProduction;
	
	@SerializedName("GetContract")
	private Boolean getContract;

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
	 * @return the getAccount
	 */
	public Boolean getGetAccount() {
		return getAccount;
	}

	/**
	 * @param getAccount the getAccount to set
	 */
	public void setGetAccount(Boolean getAccount) {
		this.getAccount = getAccount;
	}

	/**
	 * @return the getUserProduction
	 */
	public Boolean getGetUserProduction() {
		return getUserProduction;
	}

	/**
	 * @param getUserProduction the getUserProduction to set
	 */
	public void setGetUserProduction(Boolean getUserProduction) {
		this.getUserProduction = getUserProduction;
	}

	/**
	 * @return the getFacility
	 */
	public Boolean getGetFacility() {
		return getFacility;
	}

	/**
	 * @param getFacility the getFacility to set
	 */
	public void setGetFacility(Boolean getFacility) {
		this.getFacility = getFacility;
	}

	/**
	 * @return the getFacilityProduction
	 */
	public Boolean getGetFacilityProduction() {
		return getFacilityProduction;
	}

	/**
	 * @param getFacilityProduction the getFacilityProduction to set
	 */
	public void setGetFacilityProduction(Boolean getFacilityProduction) {
		this.getFacilityProduction = getFacilityProduction;
	}

	/**
	 * @return the getContract
	 */
	public Boolean getGetContract() {
		return getContract;
	}

	/**
	 * @param getContract the getContract to set
	 */
	public void setGetContract(Boolean getContract) {
		this.getContract = getContract;
	}	
}
