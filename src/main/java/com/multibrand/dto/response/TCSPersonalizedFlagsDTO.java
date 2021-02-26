package com.multibrand.dto.response;

import java.io.Serializable;

/**
 * @author vanagani
 *
 */
public class TCSPersonalizedFlagsDTO implements Serializable{
	
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ownershipFlag;
	private String securityEligibleFlag;
	private String cashPaymentsFlag;
	private String contractElapsedDays;
	private String reliantAppUserFlag;
	private String securityActive;
	private String polrCustomer;
	
	public String getPolrCustomer() {
		return polrCustomer;
	}
	public void setPolrCustomer(String polrCustomer) {
		this.polrCustomer = polrCustomer;
	}
	public String getOwnershipFlag() {
		return ownershipFlag;
	}
	public void setOwnershipFlag(String ownershipFlag) {
		this.ownershipFlag = ownershipFlag;
	}
	public String getSecurityEligibleFlag() {
		return securityEligibleFlag;
	}
	public void setSecurityEligibleFlag(String securityEligibleFlag) {
		this.securityEligibleFlag = securityEligibleFlag;
	}
	public String getCashPaymentsFlag() {
		return cashPaymentsFlag;
	}
	public void setCashPaymentsFlag(String cashPaymentsFlag) {
		this.cashPaymentsFlag = cashPaymentsFlag;
	}
	public String getContractElapsedDays() {
		return contractElapsedDays;
	}
	public void setContractElapsedDays(String contractElapsedDays) {
		this.contractElapsedDays = contractElapsedDays;
	}
	public String getReliantAppUserFlag() {
		return reliantAppUserFlag;
	}
	public void setReliantAppUserFlag(String reliantAppUserFlag) {
		this.reliantAppUserFlag = reliantAppUserFlag;
	}
	public String getSecurityActive() {
		return securityActive;
	}
	public void setSecurityActive(String securityActive) {
		this.securityActive = securityActive;
	}

}