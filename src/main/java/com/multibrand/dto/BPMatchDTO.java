package com.multibrand.dto;

import java.io.Serializable;

import com.multibrand.util.CommonUtil;

/**
 * 
 * @author vsood30
 *
 */
public class BPMatchDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2506700814243566292L;
	private String activeCustomerFlag; //activeCustomerFlag
	private String addressMatchFlag; // * Set in code based on logic
	private String pendingBalanceFlag; //balanceFlag
	private String bpActiveContract; // BPActiveCustomerDTO.activeContractNumber
	private String matchedPartnerID; // * Set in code based on logic
	private String addressSearchPerformed; //addressChkPerformed;
	private String bpMatchNoCCSResponse; //* If webservice call fails

	public String getActiveCustomerFlag() {
		return activeCustomerFlag;
	}

	public void setActiveCustomerFlag(String activeCustomerFlag) {
		this.activeCustomerFlag = activeCustomerFlag;
	}

	public String getAddressMatchFlag() {
		return addressMatchFlag;
	}

	public void setAddressMatchFlag(String addressMatchFlag) {
		this.addressMatchFlag = addressMatchFlag;
	}

	public String getPendingBalanceFlag() {
		return pendingBalanceFlag;
	}

	public void setPendingBalanceFlag(String pendingBalanceFlag) {
		this.pendingBalanceFlag = pendingBalanceFlag;
	}

	public String getBpActiveContract() {
		return bpActiveContract;
	}

	public void setBpActiveContract(String bpActiveContract) {
		this.bpActiveContract = bpActiveContract;
	}

	public String getMatchedPartnerID() {
		return matchedPartnerID;
	}

	public void setMatchedPartnerID(String matchedPartnerID) {
		this.matchedPartnerID = matchedPartnerID;
	}

	public String getAddressSearchPerformed() {
		return addressSearchPerformed;
	}

	public void setAddressSearchPerformed(String addressSearchPerformed) {
		this.addressSearchPerformed = addressSearchPerformed;
	}

	public String getBpMatchNoCCSResponse() {
		return bpMatchNoCCSResponse;
	}

	public void setBpMatchNoCCSResponse(String bpMatchNoCCSResponse) {
		this.bpMatchNoCCSResponse = bpMatchNoCCSResponse;
	}

	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}
}
