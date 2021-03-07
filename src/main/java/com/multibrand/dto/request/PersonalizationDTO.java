/**
 * 
 */
package com.multibrand.dto.request;

import java.io.Serializable;

import com.multibrand.util.Constants;


public class PersonalizationDTO implements FormEntityRequest, Constants, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bpNumber;
	private String caNumber;
	private String coNumber;
	private String customerType;
	
	public String getBpNumber() {
		return bpNumber;
	}
	public void setBpNumber(String bpNumber) {
		this.bpNumber = bpNumber;
	}
	public String getCaNumber() {
		return caNumber;
	}
	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}
	public String getCoNumber() {
		return coNumber;
	}
	public void setCoNumber(String coNumber) {
		this.coNumber = coNumber;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	@Override
	public String toString() {
		return "PersonalizationDTO [bpNumber=" + bpNumber + ", caNumber=" + caNumber + ", coNumber=" + coNumber
				+ ", customerType=" + customerType + "]";
	}
	
}
