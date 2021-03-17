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
	private String polrBP;	
	private String polrCA;
	private String polrCO;
	private String customerType;
	
	
	public String getPolrBP() {
		return polrBP;
	}
	public void setPolrBP(String polrBP) {
		this.polrBP = polrBP;
	}
	public String getPolrCA() {
		return polrCA;
	}
	public void setPolrCA(String polrCA) {
		this.polrCA = polrCA;
	}
	public String getPolrCO() {
		return polrCO;
	}
	public void setPolrCO(String polrCO) {
		this.polrCO = polrCO;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	@Override
	public String toString() {
		return "PersonalizationDTO [polrBP=" + polrBP + ", polrCA=" + polrCA + ", polrCO=" + polrCO + ", customerType="
				+ customerType + "]";
	}
	
}
