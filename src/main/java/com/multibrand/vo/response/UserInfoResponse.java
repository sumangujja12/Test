package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;


@XmlRootElement(name="UserInfoResponse")
@Component
public class UserInfoResponse extends GenericResponse{
	
	    private java.lang.String accountNumber;

	    private java.lang.String userName;
	    
	    private java.lang.String emailID;
	    
	    private java.lang.String userUniqueID;
	    
	   

		public java.lang.String getEmailID() {
			return emailID;
		}

		public void setEmailID(java.lang.String emailID) {
			this.emailID = emailID;
		}

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

		public java.lang.String getUserUniqueID() {
			return userUniqueID;
		}

		public void setUserUniqueID(java.lang.String userUniqueID) {
			this.userUniqueID = userUniqueID;
		}

				
	    

}
