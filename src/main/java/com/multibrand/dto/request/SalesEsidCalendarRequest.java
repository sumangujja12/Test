/**
 * 
 */
package com.multibrand.dto.request;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Kdeshmu1
 * 
 */
public class SalesEsidCalendarRequest extends SalesOERequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pastServiceMatchedFlag;
	

	


	public String getPastServiceMatchedFlag() {
		return pastServiceMatchedFlag;
	}


	public void setPastServiceMatchedFlag(String pastServiceMatchedFlag) {
		this.pastServiceMatchedFlag = pastServiceMatchedFlag;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
		//return super.toString() + CommonUtil.doRender(this)
	}	
}