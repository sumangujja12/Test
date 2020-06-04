package com.multibrand.vo.request;

import javax.ws.rs.QueryParam;

import com.multibrand.dto.request.SalesBaseRequest;
import com.multibrand.request.validation.ActionCode;
import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.RepetitiveDigitCheck;

public class SalesTokenRequest extends SalesBaseRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 747886355739716064L;
	
	@QueryParam(value = "actionCode")
	@NotEmpty
	@ActionCode
	private String actionCode;
	
	@QueryParam(value = "numToBeTokenized")
	@NotEmpty
	@RepetitiveDigitCheck
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