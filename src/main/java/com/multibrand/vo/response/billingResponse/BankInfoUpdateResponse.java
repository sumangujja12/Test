package com.multibrand.vo.response.billingResponse;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="BankInfoUpdateResponse")
public class BankInfoUpdateResponse extends GenericResponse {

		private java.lang.String onlinePayAccountId="";
	
	 	private java.lang.String errorCode;

	    private java.lang.String errorMessage;
	    
	    public java.lang.String getOnlinePayAccountId() {
			return onlinePayAccountId;
		}

		public void setOnlinePayAccountId(java.lang.String onlinePayAccountId) {
			this.onlinePayAccountId = onlinePayAccountId;
		}

		public java.lang.String getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(java.lang.String errorCode) {
			this.errorCode = errorCode;
		}

		public java.lang.String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(java.lang.String errorMessage) {
			this.errorMessage = errorMessage;
		}

	    
	    
}
