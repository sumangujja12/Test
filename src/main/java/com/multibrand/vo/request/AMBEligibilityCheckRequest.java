package com.multibrand.vo.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.multibrand.dto.request.FormEntityRequest;
import com.multibrand.util.Constants;


public class AMBEligibilityCheckRequest  implements FormEntityRequest, Constants,
Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4384394262659738223L;
	private String bpNumber;
	private String accountNumber;
	private String contractId;
	private String companyCode;
	
	public String getBpNumber() {
		return bpNumber;
	}
	public void setBpNumber(String bpNumber) {
		this.bpNumber = bpNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	/**
	 * @return String representation of this request.
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}	
}
