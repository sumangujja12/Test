package com.multibrand.vo.response.billingResponse;


public class GMEContractDO {

	private OfferDO currentPlan;
	
	private AddressDO serviceAddressDO;
	
	private java.lang.String strContractID;
	
	private java.lang.String strContractStartDate;
	
	private java.lang.String strContractEndDate;
	
	private java.lang.String strAvgPrice;
	    
	private java.lang.String strCancelFee;
		
	private java.lang.String strMoveOutDate;
	    
	private java.lang.String eflDocID="";
	    
	private java.lang.String tosDocID="";
	    
	private java.lang.String yraacDocID="";
    
	private java.lang.String strESIID;
    
	private boolean renewalOffers;
    
    private boolean swapOffers;
    
    private boolean PendingSwap;
  
    private java.lang.String swapPendingDate;
  
    public OfferDO getCurrentPlan() {
  		return currentPlan;
  	}

  	public AddressDO getServiceAddressDO() {
  		return serviceAddressDO;
  	}

  	public void setServiceAddressDO(AddressDO serviceAddressDO) {
  		this.serviceAddressDO = serviceAddressDO;
  	}

  	public void setCurrentPlan(OfferDO currentPlan) {
  		this.currentPlan = currentPlan;
  	}

  	public java.lang.String getStrContractID() {
  		return strContractID;
  	}

  	public void setStrContractID(java.lang.String strContractID) {
  		this.strContractID = strContractID;
  	}

  	public java.lang.String getStrContractStartDate() {
  		return strContractStartDate;
  	}

  	public void setStrContractStartDate(java.lang.String strContractStartDate) {
  		this.strContractStartDate = strContractStartDate;
  	}

  	public java.lang.String getStrContractEndDate() {
  		return strContractEndDate;
  	}

  	public void setStrContractEndDate(java.lang.String strContractEndDate) {
  		this.strContractEndDate = strContractEndDate;
  	}

  	public java.lang.String getStrESIID() {
  		return strESIID;
  	}

  	public void setStrESIID(java.lang.String strESIID) {
  		this.strESIID = strESIID;
  	}

  	public java.lang.String getStrAvgPrice() {
  		return strAvgPrice;
  	}

  	public void setStrAvgPrice(java.lang.String strAvgPrice) {
  		this.strAvgPrice = strAvgPrice;
  	}

  	public java.lang.String getStrCancelFee() {
  		return strCancelFee;
  	}

  	public void setStrCancelFee(java.lang.String strCancelFee) {
  		this.strCancelFee = strCancelFee;
  	}

  	public java.lang.String getStrMoveOutDate() {
  		return strMoveOutDate;
  	}

  	public void setStrMoveOutDate(java.lang.String strMoveOutDate) {
  		this.strMoveOutDate = strMoveOutDate;
  	}

  	public java.lang.String getEflDocID() {
  		return eflDocID;
  	}

  	public void setEflDocID(java.lang.String eflDocID) {
  		this.eflDocID = eflDocID;
  	}

  	public java.lang.String getTosDocID() {
  		return tosDocID;
  	}

  	public void setTosDocID(java.lang.String tosDocID) {
  		this.tosDocID = tosDocID;
  	}

  	public java.lang.String getYraacDocID() {
  		return yraacDocID;
  	}

  	public void setYraacDocID(java.lang.String yraacDocID) {
  		this.yraacDocID = yraacDocID;
  	}

  	public boolean isSwapOffers() {
  		return swapOffers;
  	}

  	public void setSwapOffers(boolean swapOffers) {
  		this.swapOffers = swapOffers;
  	}

  	public java.lang.String getSwapPendingDate() {
  		return swapPendingDate;
  	}

  	public void setSwapPendingDate(java.lang.String swapPendingDate) {
  		this.swapPendingDate = swapPendingDate;
  	}

  	public boolean isRenewalOffers() {
  		return renewalOffers;
  	}

  	public void setRenewalOffers(boolean renewalOffers) {
  		this.renewalOffers = renewalOffers;
  	}

  	public boolean isPendingSwap() {
  		return PendingSwap;
  	}

  	public void setPendingSwap(boolean pendingSwap) {
  		PendingSwap = pendingSwap;
  	}

    
}
