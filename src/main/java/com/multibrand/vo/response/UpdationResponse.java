package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="UpdationResponse")
public class UpdationResponse extends GenericResponse {

	private String isUpdateSuccess;

	public String getIsUpdateSuccess() {
		return isUpdateSuccess;
	}

	public void setIsUpdateSuccess(String isUpdateSuccess) {
		this.isUpdateSuccess = isUpdateSuccess;
	}

}
