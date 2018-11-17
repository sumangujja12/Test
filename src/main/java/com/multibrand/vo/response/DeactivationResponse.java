package com.multibrand.vo.response;

public class DeactivationResponse extends GenericResponse {

	private String deliveryStatus;
	private String secureCode;
	private String uniqueKey;
	
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public String getSecureCode() {
		return secureCode;
	}
	public void setSecureCode(String secureCode) {
		this.secureCode = secureCode;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
	
}
