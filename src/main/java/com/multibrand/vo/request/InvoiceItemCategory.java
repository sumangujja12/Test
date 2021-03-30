package com.multibrand.vo.request;

import java.io.Serializable;

public class InvoiceItemCategory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4805928745262678566L;
	private String itemCategory;
	private String amount;
	
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	

}
