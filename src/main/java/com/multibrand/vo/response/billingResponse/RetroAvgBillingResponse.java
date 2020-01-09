package com.multibrand.vo.response.billingResponse;

import com.multibrand.vo.response.GenericResponse;

public class RetroAvgBillingResponse extends GenericResponse {
	
	private boolean retroAvgBillEligibilityStatus;
	private Double retroAvgAmount;

	public Double getRetroAvgAmount() {
		return retroAvgAmount;
	}

	public void setRetroAvgAmount(Double retroAvgAmount) {
		this.retroAvgAmount = retroAvgAmount;
	}

	public boolean isRetroAvgBillEligibilityStatus() {
		return retroAvgBillEligibilityStatus;
	}

	public void setRetroAvgBillEligibilityStatus(boolean retroAvgBillEligibilityStatus) {
		this.retroAvgBillEligibilityStatus = retroAvgBillEligibilityStatus;
	}

}
