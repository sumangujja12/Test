package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PermitCheckResponse extends GenericResponse {
	
	 private PermitCheckDO[] checkDOs;

	public PermitCheckDO[] getCheckDOs() {
		return checkDOs;
	}

	public void setCheckDOs(PermitCheckDO[] checkDOs) {
		this.checkDOs = checkDOs;
	}
	 
	 

}
