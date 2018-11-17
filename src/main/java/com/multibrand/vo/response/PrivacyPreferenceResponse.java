package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PrivacyPreferenceResponse")
public class PrivacyPreferenceResponse extends GenericResponse {
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
