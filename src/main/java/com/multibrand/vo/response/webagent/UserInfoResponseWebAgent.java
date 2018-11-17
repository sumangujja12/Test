package com.multibrand.vo.response.webagent;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name = "UserInfoResponseWebAgent")
@Component
public class UserInfoResponseWebAgent extends GenericResponse {

	private java.lang.String accountNumber;

	private java.lang.String userName;

	private java.lang.String emailID;

	private String uniqueId;

	public java.lang.String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(java.lang.String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getEmailID() {
		return emailID;
	}

	public void setEmailID(java.lang.String emailID) {
		this.emailID = emailID;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

}
