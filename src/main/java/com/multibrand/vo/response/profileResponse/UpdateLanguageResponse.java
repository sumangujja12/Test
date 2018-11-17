package com.multibrand.vo.response.profileResponse;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement
public class UpdateLanguageResponse extends GenericResponse {
	
	  private java.lang.String errorCode;

	    private java.lang.String errorMessage;


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
