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


	
	//@NotBlank(groups = BasicConstraint.class)
	private String Partner;
	//@NotBlank(groups = BasicConstraint.class)
	private String Account;
	//@NotBlank(groups = BasicConstraint.class)
	private String activate;
	

	
	





	public String getPartner() {
		return Partner;
	}





	public void setPartner(String partner) {
		Partner = partner;
	}





	public String getAccount() {
		return Account;
	}


	public void setAccount(String account) {
		Account = account;
	}


	public String getActivate() {
		return activate;
	}





	public void setActivate(String activate) {
		this.activate = activate;
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
