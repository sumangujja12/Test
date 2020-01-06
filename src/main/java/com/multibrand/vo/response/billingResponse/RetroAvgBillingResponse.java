package com.multibrand.vo.response.billingResponse;

import com.multibrand.vo.response.GenericResponse;

public class RetroAvgBillingResponse extends GenericResponse {
	
	private boolean retroAvgBillEligibilityStatus;

	public boolean isRetroAvgBillEligibilityStatus() {
		return retroAvgBillEligibilityStatus;
	}

	public void setRetroAvgBillEligibilityStatus(boolean retroAvgBillEligibilityStatus) {
		this.retroAvgBillEligibilityStatus = retroAvgBillEligibilityStatus;
	}

}
