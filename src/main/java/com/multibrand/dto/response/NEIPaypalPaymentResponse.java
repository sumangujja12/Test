package com.multibrand.dto.response;

public class NEIPaypalPaymentResponse {

	private String eotbdId;

	private String xcode;

	private String message;

	/**
	 * @return the eotbdId
	 */
	public String getEotbdId() {
		return eotbdId;
	}

	/**
	 * @param eotbdId the eotbdId to set
	 */
	public void setEotbdId(String eotbdId) {
		this.eotbdId = eotbdId;
	}

	/**
	 * @return the xcode
	 */
	public String getXcode() {
		return xcode;
	}

	/**
	 * @param xcode the xcode to set
	 */
	public void setXcode(String xcode) {
		this.xcode = xcode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "NEIPaypalPaymentResponse [eotbdId=" + eotbdId + ", xcode=" + xcode + ", message=" + message + "]";
	}

}
