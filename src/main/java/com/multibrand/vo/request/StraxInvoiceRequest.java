package com.multibrand.vo.request;

import java.io.Serializable;
import java.util.List;

public class StraxInvoiceRequest implements Serializable {

	private static final long serialVersionUID = 5313732309176983924L;

	private String caNumber;
	private String totalAmount;
	private String straxLeadID;
	private List<InvoiceItemCategory> invoiceItems;

	public String getCaNumber() {
		return caNumber;
	}

	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStraxLeadID() {
		return straxLeadID;
	}

	public void setStraxLeadID(String straxLeadID) {
		this.straxLeadID = straxLeadID;
	}

	public List<InvoiceItemCategory> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(List<InvoiceItemCategory> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

}
