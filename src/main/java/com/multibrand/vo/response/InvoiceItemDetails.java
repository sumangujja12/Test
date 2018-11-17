package com.multibrand.vo.response;

import java.math.BigDecimal;

public class InvoiceItemDetails {

	private String currKey;
	private BigDecimal diffAmountValue;
	private BigDecimal diffUsageValue;
	private BigDecimal invAmountValue;
	private BigDecimal invUsageValue;
	private String itemId;
	private String itemText;
	private String itemType;
	private BigDecimal simAmountValue;
	private BigDecimal simUsageValue;

	private ZvppdSAbdTextamt tdspNonRecurChargeDet[];

	public String getCurrKey() {
		return currKey;
	}

	public void setCurrKey(String currKey) {
		this.currKey = currKey;
	}

	public BigDecimal getDiffAmountValue() {
		return diffAmountValue;
	}

	public void setDiffAmountValue(BigDecimal diffAmountValue) {
		this.diffAmountValue = diffAmountValue;
	}

	public BigDecimal getDiffUsageValue() {
		return diffUsageValue;
	}

	public void setDiffUsageValue(BigDecimal diffUsageValue) {
		this.diffUsageValue = diffUsageValue;
	}

	public BigDecimal getInvAmountValue() {
		return invAmountValue;
	}

	public void setInvAmountValue(BigDecimal invAmountValue) {
		this.invAmountValue = invAmountValue;
	}

	public BigDecimal getInvUsageValue() {
		return invUsageValue;
	}

	public void setInvUsageValue(BigDecimal invUsageValue) {
		this.invUsageValue = invUsageValue;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemText() {
		return itemText;
	}

	public void setItemText(String itemText) {
		this.itemText = itemText;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public BigDecimal getSimAmountValue() {
		return simAmountValue;
	}

	public void setSimAmountValue(BigDecimal simAmountValue) {
		this.simAmountValue = simAmountValue;
	}

	public BigDecimal getSimUsageValue() {
		return simUsageValue;
	}

	public void setSimUsageValue(BigDecimal simUsageValue) {
		this.simUsageValue = simUsageValue;
	}

	public ZvppdSAbdTextamt[] getTdspNonRecurChargeDet() {
		return tdspNonRecurChargeDet;
	}

	public void setTdspNonRecurChargeDet(
			ZvppdSAbdTextamt[] tdspNonRecurChargeDet) {
		this.tdspNonRecurChargeDet = tdspNonRecurChargeDet;
	}

}
