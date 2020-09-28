/**
 * 
 */
package com.multibrand.dto.request;

import java.io.Serializable;

import com.multibrand.util.Constants;


public class PersonalizationRequest implements FormEntityRequest, Constants, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bp;
	private String ca;
	public String getBp() {
		return bp;
	}
	public void setBp(String bp) {
		this.bp = bp;
	}
	public String getCa() {
		return ca;
	}
	public void setCa(String ca) {
		this.ca = ca;
	}
	@Override
	public String toString() {
		return "PersonalizationRequest [bp=" + bp + ", ca=" + ca + "]";
	}
	
	
}
