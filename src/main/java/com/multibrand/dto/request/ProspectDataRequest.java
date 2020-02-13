/**
 * 
 */
package com.multibrand.dto.request;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;

/**
 * 
 * @author 289347
 *
 */
public class ProspectDataRequest extends BaseAffiliateRequest  {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(groups = BasicConstraint.class)
	private String prospectID;
	
	@NotBlank(groups = BasicConstraint.class)
	private String	lastfourdigitSSN;
	
	

	public String getProspectID() {
		return prospectID;
	}



	public void setProspectID(String prospectID) {
		this.prospectID = prospectID;
	}



	public String getLastfourdigitSSN() {
		return lastfourdigitSSN;
	}



	public void setLastfourdigitSSN(String lastfourdigitSSN) {
		this.lastfourdigitSSN = lastfourdigitSSN;
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