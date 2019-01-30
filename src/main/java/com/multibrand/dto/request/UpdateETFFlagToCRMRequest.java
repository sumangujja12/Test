/**
 * 
 */
package com.multibrand.dto.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;

import com.multibrand.util.Constants;


public class UpdateETFFlagToCRMRequest implements FormEntityRequest, Constants, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String partner;
	private String account;
	
	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	
	/* (non-Javadoc)
	 * @see com.multibrand.dto.request.BaseAffiliateRequest#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
		//return super.toString() + CommonUtil.doRender(this)
	}	
}
