package com.multibrand.vo.request;


public class LoggingVO {
	
	private String userId = "";
	private String userUniqueId = "";
	private String sessionId = "";
	private Object requestData;
	private Object responseData;
	private long responseTime = 0;
	private String responseStatus = "";
	private String companyCode = "";
	private boolean isMask;
	private String transactionType = "";
	private String endPointURL;
	private String confirmationNumber = "";
	
	public String getConfirmationNumber() {
		return confirmationNumber;
	}
	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}
	public boolean isMask() {
		return isMask;
	}
	public void setMask(boolean isMask) {
		this.isMask = isMask;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserUniqueId() {
		return userUniqueId;
	}
	public void setUserUniqueId(String userUniqueId) {
		this.userUniqueId = userUniqueId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Object getRequestData() {
		return requestData;
	}
	public void setRequestData(Object requestData) {
		this.requestData = requestData;
	}
	public Object getResponseData() {
		return responseData;
	}
	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}
	public long getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public void setEndPointURL(String endPointUrl) {
		
		this.endPointURL = endPointUrl;
		
	}
	public String getEndPointURL() {
		return endPointURL;
	}
	
	

}
