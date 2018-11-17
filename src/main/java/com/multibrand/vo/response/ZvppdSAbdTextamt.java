package com.multibrand.vo.response;

import java.math.BigDecimal;

public class ZvppdSAbdTextamt {

	private BigDecimal amountValue;
	private String currKey;
	private String invNum;
	private String itemText;

	public BigDecimal getAmountValue() {
		return amountValue;
	}

	public void setAmountValue(BigDecimal amountValue) {
		this.amountValue = amountValue;
	}

	public String getCurrKey() {
		return currKey;
	}

	public void setCurrKey(String currKey) {
		this.currKey = currKey;
	}

	public String getInvNum() {
		return invNum;
	}

	public void setInvNum(String invNum) {
		this.invNum = invNum;
	}

	public String getItemText() {
		return itemText;
	}

	public void setItemText(String itemText) {
		this.itemText = itemText;
	}

}
