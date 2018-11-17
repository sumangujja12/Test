package com.multibrand.dataObjects;

import java.util.Date;

public class Contract {
	private String pointofDeliveryID;
	private String ContractNumber;
	private String ContractAccountNumber;
	private String BusinessPartnerID;
	private String TDSP;
	private Date ContractStartDate;
	private Date ContractEndDate;
	public final String getPointofDeliveryID() {
		return pointofDeliveryID;
	}
	public final void setPointofDeliveryID(String pointofDeliveryID) {
		this.pointofDeliveryID = pointofDeliveryID;
	}
	public final String getContractNumber() {
		return ContractNumber;
	}
	public final void setContractNumber(String contractNumber) {
		ContractNumber = contractNumber;
	}
	public final String getContractAccountNumber() {
		return ContractAccountNumber;
	}
	public final void setContractAccountNumber(String contractAccountNumber) {
		ContractAccountNumber = contractAccountNumber;
	}
	public final String getBusinessPartnerID() {
		return BusinessPartnerID;
	}
	public final void setBusinessPartnerID(String businessPartnerID) {
		BusinessPartnerID = businessPartnerID;
	}
	public final Date getContractStartDate() {
		return ContractStartDate;
	}
	public final void setContractStartDate(Date contractStartDate) {
		ContractStartDate = contractStartDate;
	}
	public final Date getContractEndDate() {
		return ContractEndDate;
	}
	public final void setContractEndDate(Date contractEndDate) {
		ContractEndDate = contractEndDate;
	}
	public final String getTDSP() {
		return TDSP;
	}
	public final void setTDSP(String tDSP) {
		TDSP = tDSP;
	}

}
