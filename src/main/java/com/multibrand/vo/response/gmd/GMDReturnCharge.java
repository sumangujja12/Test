package com.multibrand.vo.response.gmd;

import java.math.BigDecimal;

public class GMDReturnCharge {
	
    private String invoiceNumber;
    private String billingDate;
    private BigDecimal returnChrg;
    
	/**
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	/**
	 * @param invoiceNumber the invoiceNumber to set
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	/**
	 * @return the billingDate
	 */
	public String getBillingDate() {
		return billingDate;
	}
	/**
	 * @param billingDate the billingDate to set
	 */
	public void setBillingDate(String billingDate) {
		this.billingDate = billingDate;
	}
	/**
	 * @return the returnChrg
	 */
	public BigDecimal getReturnChrg() {
		return returnChrg;
	}
	/**
	 * @param returnChrg the returnChrg to set
	 */
	public void setReturnChrg(BigDecimal returnChrg) {
		this.returnChrg = returnChrg;
	}
}
