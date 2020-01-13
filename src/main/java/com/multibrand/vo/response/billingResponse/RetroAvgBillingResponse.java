package com.multibrand.vo.response.billingResponse;

import com.multibrand.vo.response.GenericResponse;

public class RetroAvgBillingResponse extends GenericResponse {
	
	private boolean retroAvgBillEligibilityStatus;
	private Double ambAmount;

	public Double getAmbAmount() {
		return ambAmount;
	}

	public void setAmbAmount(Double ambAmount) {
		this.ambAmount = ambAmount;
	}

	public boolean isRetroAvgBillEligibilityStatus() {
		return retroAvgBillEligibilityStatus;
	}

	public void setRetroAvgBillEligibilityStatus(boolean retroAvgBillEligibilityStatus) {
		this.retroAvgBillEligibilityStatus = retroAvgBillEligibilityStatus;
	}

}
