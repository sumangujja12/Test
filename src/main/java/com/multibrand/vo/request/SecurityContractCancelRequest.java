package com.multibrand.vo.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

public class SecurityContractCancelRequest implements Serializable {
	private static final long serialVersionUID = 5313732309176983924L;
	
	 private String sTraxLeadId;
	 private String contractId;
	 private String endDate;
	
     

	public SecurityContractCancelRequest(String sTraxLeadId, String contractId, String endDate) {
		super();
		this.sTraxLeadId = sTraxLeadId;
		this.contractId = contractId;
		this.endDate = endDate;
	}

	public SecurityContractCancelRequest() {
	
	}

	public String getsTraxLeadId() {
		return sTraxLeadId;
	}


	public void setsTraxLeadId(String sTraxLeadId) {
		this.sTraxLeadId = sTraxLeadId;
	}


	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "SecurityContractCancelRequest [sTraxLeadId=" + sTraxLeadId + ", contractId=" + contractId + ", endDate="
				+ endDate + "]";
	}

	
	
	

}
