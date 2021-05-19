package com.multibrand.vo.response;

import org.apache.commons.lang.StringUtils;

public class ValidateThirdPartyReceiptResponse {

	private String successShortMessage;
	private String successLongMessage;
	
	/** The err message. */
	private String errorMessage = StringUtils.EMPTY;
	
	/** The err code. */
	private String errorCode = StringUtils.EMPTY;

	/**
	 * Added requestId for tracking
	 * in application logs
	 */
	
	public String getSuccessShortMessage() {
		return successShortMessage;
	}

	public void setSuccessShortMessage(String successShortMessage) {
		this.successShortMessage = successShortMessage;
	}

	public String getSuccessLongMessage() {
		return successLongMessage;
	}

	public void setSuccessLongMessage(String successLongMessage) {
		this.successLongMessage = successLongMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	
}
