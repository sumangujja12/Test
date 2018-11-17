package com.multibrand.vo.response.historyResponse;

import javax.xml.bind.annotation.XmlElement;
/*
 * Modified by rbansal30 for adding collInvoiceNo field
 */

public class BillPaymentHistory {

	private String invoiceNo;
	private String invoiceDate;
	private String newCharges;
	private String amountDue;
	private String dueDate;
	private String paymentReceived;
	private String previousBalance;
	private String kwhTotal;
	private String billPeriodBegin;
	private String billPeriodEnd;
	private String collInvoiceNo="";
 
	@XmlElement(name="invoiceNumber")
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getNewCharges() {
		return newCharges;
	}
	public void setNewCharges(String newCharges) {
		this.newCharges = newCharges;
	}
	public String getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(String amountDue) {
		this.amountDue = amountDue;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getPaymentReceived() {
		return paymentReceived;
	}
	public void setPaymentReceived(String paymentReceived) {
		this.paymentReceived = paymentReceived;
	}
	public String getPreviousBalance() {
		return previousBalance;
	}
	public void setPreviousBalance(String previousBalance) {
		this.previousBalance = previousBalance;
	}
	public String getKwhTotal() {
		return kwhTotal;
	}
	public void setKwhTotal(String kwhTotal) {
		this.kwhTotal = kwhTotal;
	}
	public String getBillPeriodBegin() {
		return billPeriodBegin;
	}
	public void setBillPeriodBegin(String billPeriodBegin) {
		this.billPeriodBegin = billPeriodBegin;
	}
	public String getBillPeriodEnd() {
		return billPeriodEnd;
	}
	public void setBillPeriodEnd(String billPeriodEnd) {
		this.billPeriodEnd = billPeriodEnd;
	}
	public String getCollInvoiceNo() {
		return collInvoiceNo;
	}
	public void setCollInvoiceNo(String collInvoiceNo) {
		this.collInvoiceNo = collInvoiceNo;
	}	 
}
