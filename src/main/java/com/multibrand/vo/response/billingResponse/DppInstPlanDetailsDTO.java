package com.multibrand.vo.response.billingResponse;

public class DppInstPlanDetailsDTO {
	private short itemCount;
	private String amount;
	private String dueDate;
	private String opbel;
	private String dppDes;
	
	/**
	 * @return the itemCount
	 */
	public short getItemCount() {
		return itemCount;
	}
	/**
	 * @param itemCount the itemCount to set
	 */
	public void setItemCount(short itemCount) {
		this.itemCount = itemCount;
	}
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the opbel
	 */
	public String getOpbel() {
		return opbel;
	}
	/**
	 * @param opbel the opbel to set
	 */
	public void setOpbel(String opbel) {
		this.opbel = opbel;
	}
	/**
	 * @return the dppDes
	 */
	public String getDppDes() {
		return dppDes;
	}
	/**
	 * @param dppDes the dppDes to set
	 */
	public void setDppDes(String dppDes) {
		this.dppDes = dppDes;
	}
}
