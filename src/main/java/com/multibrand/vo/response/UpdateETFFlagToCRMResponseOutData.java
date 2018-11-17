package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;



@XmlRootElement(name="UpdateETFFlagToCRMResponseOutData")
public class UpdateETFFlagToCRMResponseOutData {
	
	


	@SerializedName("ActivateETF")
	ActivateETF activateETF;





	public ActivateETF getActivateETF() {
		return activateETF;
	}





	public void setActivateETF(ActivateETF activateETF) {
		this.activateETF = activateETF;
	}





	@Override
	public String toString() {
		return "CheckReliantCustomerStatusOutData [activateETF=" + activateETF +"]";
	}
    
	
}