package com.multibrand.dto.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.PermitCheckDO;

@XmlRootElement
public class CheckPermitResponse extends GenericResponse {
	
	 private PermitCheckDO[] checkDOs;

	public PermitCheckDO[] getCheckDOs() {
		return checkDOs;
	}

	public void setCheckDOs(PermitCheckDO[] checkDOs) {
		this.checkDOs = checkDOs;
	}
	 
	 

}
