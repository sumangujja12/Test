package com.multibrand.vo.request;

public class TokenRequestVO {
	private String actionCode;
	private String numToBeTokenized;
	/**
	 * @return the actionCode
	 */
	public String getActionCode() {
		return actionCode;
	}
	/**
	 * @param actionCode the actionCode to set
	 */
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	/**
	 * @return the numToBeTokenized
	 */
	public String getNumToBeTokenized() {
		return numToBeTokenized;
	}
	/**
	 * @param numToBeTokenized the numToBeTokenized to set
	 */
	public void setNumToBeTokenized(String numToBeTokenized) {
		this.numToBeTokenized = numToBeTokenized;
	}
}