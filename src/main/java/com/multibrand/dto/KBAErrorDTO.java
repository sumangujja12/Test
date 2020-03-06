package com.multibrand.dto;

import java.io.Serializable;


public class KBAErrorDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   private String transactionKey;
	private String errorCode;
	private String errorMsg;
	private String errorDescription;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public String getTransactionKey() {
		return transactionKey;
	}
	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}
	
	
}
