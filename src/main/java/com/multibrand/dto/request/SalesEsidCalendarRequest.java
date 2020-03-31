/**
 * 
 */
package com.multibrand.dto.request;

/**
 * @author Kdeshmu1
 * 
 */
public class SalesEsidCalendarRequest extends SalesOERequest {

	private static final long serialVersionUID = 1L;
	
	private String pastServiceMatchedFlag;
	
	public String getPastServiceMatchedFlag() {
		return pastServiceMatchedFlag;
	}

	public void setPastServiceMatchedFlag(String pastServiceMatchedFlag) {
		this.pastServiceMatchedFlag = pastServiceMatchedFlag;
	}
}