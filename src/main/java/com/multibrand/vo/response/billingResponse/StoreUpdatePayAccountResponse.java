package com.multibrand.vo.response.billingResponse;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="StoreUpdatePayAccountResponse")
public class StoreUpdatePayAccountResponse extends GenericResponse{
	
	private boolean successFlag = false;

	public boolean isSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	
	

}
