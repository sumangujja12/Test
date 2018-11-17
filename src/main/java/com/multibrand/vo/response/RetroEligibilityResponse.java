package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="RetroEligibilityResponse")
public class RetroEligibilityResponse extends GenericResponse{
	
	private Boolean retroSignupEligible = false;
	private boolean successFlag = false;

	public boolean isSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}

	public Boolean getRetroSignupEligible() {
		return retroSignupEligible;
	}

	public void setRetroSignupEligible(Boolean retroSignupEligible) {
		this.retroSignupEligible = retroSignupEligible;
	}


}
