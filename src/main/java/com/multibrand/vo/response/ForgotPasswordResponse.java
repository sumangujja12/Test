package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement(name="ForgotPasswordResponse")
@Component
public class ForgotPasswordResponse extends GenericResponse{
	
	private Boolean isCallSuccess;

	public Boolean getIsCallSuccess() {
		return isCallSuccess;
	}

	public void setIsCallSuccess(Boolean isCallSuccess) {
		this.isCallSuccess = isCallSuccess;
	}


}
