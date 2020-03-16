package com.multibrand.vo.response.billingResponse;

public class AMBEligibiltyStatusResponse{

	private String avgBillFlag;
	private String avlBillFlag;
	private boolean isRetroAvgBillEligible = false;
	
	public String getAvgBillFlag() {
		return avgBillFlag;
	}
	public void setAvgBillFlag(String avgBillFlag) {
		this.avgBillFlag = avgBillFlag;
	}
	public String getAvlBillFlag() {
		return avlBillFlag;
	}
	public void setAvlBillFlag(String avlBillFlag) {
		this.avlBillFlag = avlBillFlag;
	}
	/**
	 * @return the isRetroAvgBillEligible
	 */
	public boolean isRetroAvgBillEligible() {
		return isRetroAvgBillEligible;
	}
	/**
	 * @param isRetroAvgBillEligible the isRetroAvgBillEligible to set
	 */
	public void setRetroAvgBillEligible(boolean isRetroAvgBillEligible) {
		this.isRetroAvgBillEligible = isRetroAvgBillEligible;
	}
	
}
