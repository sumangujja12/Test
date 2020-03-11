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
public class SalesEsidCalendarRequest extends SalesBaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank	
	private String trackingId;
	@NotBlank	
	private String guid;
	@NotBlank	
	private String pastServiceMatchedFlag;
	

	public String getTrackingId() {
		return trackingId;
	}


	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}


	public String getGuid() {
		return guid;
	}


	public void setGuid(String guid) {
		this.guid = guid;
	}


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