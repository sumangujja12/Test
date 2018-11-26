/**
 * 
 */
package com.multibrand.dto.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.util.Constants;

/**
 * The Class AffiliateOfferRequest.
 *
 * @author Arumugam
 */
public class AgentDetailsRequest implements FormEntityRequest, Constants, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	
	@NotBlank(groups = BasicConstraint.class)
	private String agentID;
	
	

	public String getAgentID() {
		return agentID;
	}



	public void setAgentID(String agentID) {
		this.agentID = agentID;
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