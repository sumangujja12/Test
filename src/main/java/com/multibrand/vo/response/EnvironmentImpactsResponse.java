package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="EnvironmentImpactsResponse")
public class EnvironmentImpactsResponse extends GenericResponse{

	private EnvironmentImpacts[] EnvironmentImpacts;
	
	private String CustomerSince;
	
	
	public String getCustomerSince() {
		return CustomerSince;
	}

	public void setCustomerSince(String customerSince) {
		this.CustomerSince=customerSince;
		
	}

	public EnvironmentImpacts[] getEnvironmentImpacts() {
		return EnvironmentImpacts;
	}

	public void setEnvironmentImpacts(EnvironmentImpacts[] environmentImpacts) {
		EnvironmentImpacts = environmentImpacts;
	}

	
}
