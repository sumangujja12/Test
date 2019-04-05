package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthenticationResponse extends GenericResponse {
	
	private String refreshVal;

	public String getRefreshVal() {
		return refreshVal;
	}

	public void setRefreshVal(String refreshVal) {
		this.refreshVal = refreshVal;
	}

}
