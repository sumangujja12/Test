package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="CheckReliantCustomerStatusOutData")
public class CheckReliantCustomerStatusOutData {
	
	@SerializedName("CustomerStatus")
	private String customerStatus;
	@SerializedName("CustomerFound")
    private String customerFound;
	public String getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	public String getCustomerFound() {
		return customerFound;
	}
	public void setCustomerFound(String customerFound) {
		this.customerFound = customerFound;
	}
	@Override
	public String toString() {
		return "CheckReliantCustomerStatusOutData [customerStatus=" + customerStatus + ", customerFound="
				+ customerFound + "]";
	}
    
	
}
