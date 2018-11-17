package com.multibrand.vo.response;

public class InvoiceDetails {

	private String invDateFrom;
	private String invDateTo;
	private String invDt;
	private String invNo;

	private InvoiceItemDetails itemsDetails[];

	public String getInvDateFrom() {
		return invDateFrom;
	}

	public void setInvDateFrom(String invDateFrom) {
		this.invDateFrom = invDateFrom;
	}

	public String getInvDateTo() {
		return invDateTo;
	}

	public void setInvDateTo(String invDateTo) {
		this.invDateTo = invDateTo;
	}

	public String getInvDt() {
		return invDt;
	}

	public void setInvDt(String invDt) {
		this.invDt = invDt;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public InvoiceItemDetails[] getItemsDetails() {
		return itemsDetails;
	}

	public void setItemsDetails(InvoiceItemDetails[] itemsDetails) {
		this.itemsDetails = itemsDetails;
	}

}
