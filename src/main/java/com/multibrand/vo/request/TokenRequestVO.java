package com.multibrand.vo.request;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.dto.request.BaseAffiliateRequest;
import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;

public class TokenRequestVO extends BaseAffiliateRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 747886355739716064L;
	@QueryParam(value = "actionCode")
	@NotNull
	@NotBlank(groups = BasicConstraint.class)
	private String actionCode;
	@QueryParam(value = "numToBeTokenized")
	@NotNull
	@NotBlank(groups = BasicConstraint.class)
	private String numToBeTokenized;
	
	// Online Affiliate attributes:
		@QueryParam(value = "affiliateId")
		@NotBlank(groups = BasicConstraint.class)
		@Length(max = 40, groups = SizeConstraint.class)
		private String affiliateId;
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