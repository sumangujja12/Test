package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement(name="ForgotUserNameResponse")
@Component
public class ForgotUserNameResponse extends GenericResponse{
	
	private java.lang.String userName;

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	

}
