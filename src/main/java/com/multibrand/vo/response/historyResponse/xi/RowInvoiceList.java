package com.multibrand.vo.response.historyResponse.xi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author ahanda1
 * Response POJO for XI invoice list call. To be used with JAXB for marshalling and unmarshalling
 * InvoiceListResponse POJO will contain a list of this pojo
 */
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "row", namespace="http://reliant.com/xi/GreenMountain")
@XmlRootElement(name="row", namespace="http://reliant.com/xi/GreenMountain")
public class RowInvoiceList {

	@XmlElement(name = "Invoice_Number")
	protected String invoiceNo;
	
	@XmlElement(name = "Invoice_Date")
	protected String invoiceDate;
	
	@XmlElement(name = "Invoice_Amount")
	protected String invoiceAmt;
	
	@XmlElement(name = "Previous_Balance")
	protected String previousBalance;
	
	@XmlElement(name = "Credits")
	protected String credits;
	
	@XmlElement(name = "Total_Amount_Due")
	protected String totalAmtDue;
	
	@XmlElement(name = "Due_Date")
	protected String dueDate;
	
	@XmlElement(name = "Bill_Start_Date")
	protected String billStartDate;
	
	@XmlElement(name = "Billing_To_Date")
	protected String billingToDate;
	
	@XmlElement(name = "Total_Usage")
	protected String totalUsage;
	
	@XmlElement(name = "Result_Code")
	protected String Result_Code;
	
	@XmlElement(name = "Result_Description")
	protected String Result_Description;

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

	public String getInvoiceAmt() {
		return invoiceAmt;
	}

	public void setInvoiceAmt(String invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
	}

	public String getPreviousBalance() {
		return previousBalance;
	}

	public void setPreviousBalance(String previousBalance) {
		this.previousBalance = previousBalance;
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public String getTotalAmtDue() {
		return totalAmtDue;
	}

	public void setTotalAmtDue(String totalAmtDue) {
		this.totalAmtDue = totalAmtDue;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getBillStartDate() {
		return billStartDate;
	}

	public void setBillStartDate(String billStartDate) {
		this.billStartDate = billStartDate;
	}

	public String getBillingToDate() {
		return billingToDate;
	}

	public void setBillingToDate(String billingToDate) {
		this.billingToDate = billingToDate;
	}

	public String getTotalUsage() {
		return totalUsage;
	}

	public void setTotalUsage(String totalUsage) {
		this.totalUsage = totalUsage;
	}

	public String getResult_Code() {
		return Result_Code;
	}

	public void setResult_Code(String result_Code) {
		Result_Code = result_Code;
	}

	public String getResult_Description() {
		return Result_Description;
	}

	public void setResult_Description(String result_Description) {
		Result_Description = result_Description;
	}
	
	
	
}
