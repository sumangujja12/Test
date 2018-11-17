package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement(name="SendMailForPasswordChange")
@Component
public class SendMailForPasswordChangeResponse extends GenericResponse {
	
	private String status = FLAG_FALSE;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
