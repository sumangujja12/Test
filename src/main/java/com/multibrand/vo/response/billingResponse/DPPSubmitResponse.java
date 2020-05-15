package com.multibrand.vo.response.billingResponse;

import com.multibrand.vo.response.GenericResponse;

public class DPPSubmitResponse extends GenericResponse {

	private boolean dppSubmit = true;

	/**
	 * @return the dppSubmit
	 */
	public boolean isDppSubmit() {
		return dppSubmit;
	}

	/**
	 * @param dppSubmit the dppSubmit to set
	 */
	public void setDppSubmit(boolean dppSubmit) {
		this.dppSubmit = dppSubmit;
	}


	
	
}
