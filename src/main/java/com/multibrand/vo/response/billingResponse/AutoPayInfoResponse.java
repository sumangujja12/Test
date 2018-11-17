package com.multibrand.vo.response.billingResponse;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="AutoPayInfoResponse")
public class AutoPayInfoResponse extends GenericResponse {
	
	private AutoPayDetails[] autoPayDetailsList;
	
	public AutoPayDetails[] getAutoPayDetailsList() {
		return autoPayDetailsList;
	}

	public void setAutoPayDetailsList(AutoPayDetails[] autoPayDetailsList) {
		this.autoPayDetailsList = autoPayDetailsList;
	}

}
