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
	private String co;
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
	public String getCo() {
		return co;
	}
	public void setCo(String co) {
		this.co = co;
	}
	@Override
	public String toString() {
		return "PersonalizationRequest [bp=" + bp + ", ca=" + ca + ", co=" + co + "]";
	}
	
}
