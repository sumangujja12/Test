package com.multibrand.vo.request;

import javax.ws.rs.QueryParam;

import org.hibernate.validator.constraints.NotBlank;
import com.multibrand.dto.request.SalesBaseRequest;
import com.multibrand.request.validation.ActionCode;

public class SalesTokenRequest extends SalesBaseRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 747886355739716064L;
	
	@QueryParam(value = "actionCode")
	@NotBlank
	@ActionCode
	private String actionCode;
	
	@QueryParam(value = "numToBeTokenized")
	@NotBlank
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