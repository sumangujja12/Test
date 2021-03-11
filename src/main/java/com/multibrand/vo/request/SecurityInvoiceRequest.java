package com.multibrand.vo.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


public class SecurityInvoiceRequest implements Serializable {
	
	private static final long serialVersionUID = 5313732309176983924L;
	
	 private String caNumber;
	 private String totalAmount;
	 
	 public SecurityInvoiceRequest()
	 {
		 
	 }

	public SecurityInvoiceRequest(String caNumber, String totalAmount) {
		super();
		this.caNumber = caNumber;
		this.totalAmount = totalAmount;
	}

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

	@Override
	public String toString() {
		return "SecurityInvoiceRequest [caNumber=" + caNumber + ", totalAmount=" + totalAmount + "]";
	}
	 
	 
	 

}
