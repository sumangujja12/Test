package com.multibrand.vo.response.historyResponse;

public class PaymentDO {
	private String paymentAmount="";
	private String paymentDate="";
	private String channel="";
	private String status="";
	private String paymentId="";
	private String bankNumber="";
	private String routingNumber="";
	private String ccType="";
	private String ccNumber="";
	private String paymentSubmissionDate;
	
	public String getRoutingNumber() {
		return routingNumber;
	}
	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public String getCcType() {
		return ccType;
	}
	public void setCcType(String ccType) {
		this.ccType = ccType;
	}
	public String getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentSubmissionDate() {
		return paymentSubmissionDate;
	}
	public void setPaymentSubmissionDate(String paymentSubmissionDate) {
		this.paymentSubmissionDate = paymentSubmissionDate;
	}
	
}
