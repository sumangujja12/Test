package com.multibrand.vo.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.dto.request.BaseAffiliateRequest;
import com.multibrand.request.validation.BasicConstraint;

public class TokenRequestVO extends BaseAffiliateRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 747886355739716064L;
	@NotNull
	@NotBlank(groups = BasicConstraint.class)
	private String actionCode;
	@NotNull
	@NotBlank(groups = BasicConstraint.class)
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