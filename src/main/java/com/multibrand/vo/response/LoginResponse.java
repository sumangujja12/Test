package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;



@XmlRootElement( name="LoginResponse")
@Component
public class LoginResponse extends GenericResponse {
	protected String userID="";
	   protected String userUniqueID="";
	   protected String accountNumber="";
	   
	   
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public final String getUserID() {
		return userID;
	}
	public final void setUserID(String userID) {
		this.userID = userID;
	}
	public final String getUserUniqueID() {
		return userUniqueID;
	}
	public final void setUserUniqueID(String userUniqueID) {
		this.userUniqueID = userUniqueID;
	}

}
