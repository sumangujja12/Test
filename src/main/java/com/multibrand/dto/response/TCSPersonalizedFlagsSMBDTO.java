package com.multibrand.dto.response;

import java.io.Serializable;

/**
 * @author vanagani
 *
 */
public class TCSPersonalizedFlagsSMBDTO implements Serializable{
	
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -3983986958382147700L;
	private String polrCustomer;
	
	public String getPolrCustomer() {
		return polrCustomer;
	}
	public void setPolrCustomer(String polrCustomer) {
		this.polrCustomer = polrCustomer;
	}

}