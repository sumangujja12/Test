package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="EnvironmentImpactsResponse")
public class EnvironmentImpactsResponse extends GenericResponse{

	private EnvironmentImpacts[] EnvironmentImpacts;

	public EnvironmentImpacts[] getEnvironmentImpacts() {
		return EnvironmentImpacts;
	}

	public void setEnvironmentImpacts(EnvironmentImpacts[] environmentImpacts) {
		EnvironmentImpacts = environmentImpacts;
	}
	
}
