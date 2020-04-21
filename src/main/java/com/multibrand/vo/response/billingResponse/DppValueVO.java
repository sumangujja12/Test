package com.multibrand.vo.response.billingResponse;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class DppValueVO {

	private String dppAmountDue = "";
	private String dppDueDate ="";
	
	/**
	 * @return the dppDueDate
	 */
	public String getDppDueDate() {
		return dppDueDate;
	}
	/**
	 * @param dppDueDate the dppDueDate to set
	 */
	public void setDppDueDate(String dppDueDate) {
		this.dppDueDate = dppDueDate;
	}
	/**
	 * @return the dppAmountDue
	 */
	public String getDppAmountDue() {
		return dppAmountDue;
	}
	/**
	 * @param dppAmountDue the dppAmountDue to set
	 */
	public void setDppAmountDue(String dppAmountDue) {
		this.dppAmountDue = dppAmountDue;
	}
	
	
}
