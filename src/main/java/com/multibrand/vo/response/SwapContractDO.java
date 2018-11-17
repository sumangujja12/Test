package com.multibrand.vo.response;

public class SwapContractDO {

	
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
