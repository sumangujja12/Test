package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;


@XmlRootElement(name="GetContractInfo")
@Component
public class GetContractInfoResponse extends GenericResponse {

	private String strDefaultOfferFlag="";
    public String getStrDefaultOfferFlag() {
		return strDefaultOfferFlag;
	}

	public void setStrDefaultOfferFlag(String strDefaultOfferFlag) {
		this.strDefaultOfferFlag = strDefaultOfferFlag;
	}

	private PendingSwapDO pendingSwapDO;
	
	private OfferDO[] eligibleOffersList;

	public PendingSwapDO getPendingSwapDO() {
		return pendingSwapDO;
	}

	public void setPendingSwapDO(PendingSwapDO pendingSwapDO) {
		this.pendingSwapDO = pendingSwapDO;
	}

	public OfferDO[] getEligibleOffersList() {
		return eligibleOffersList;
	}

	public void setEligibleOffersList(OfferDO[] eligibleOffersList) {
		this.eligibleOffersList = eligibleOffersList;
	}
	
}
