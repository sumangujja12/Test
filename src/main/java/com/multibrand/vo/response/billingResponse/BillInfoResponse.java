package com.multibrand.vo.response.billingResponse;



import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;


import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="BillInfoResponse")
@Component
public class BillInfoResponse extends GenericResponse {

	private java.lang.String unBilledAmount="";

    
	public java.lang.String getUnBilledAmount() {
		return unBilledAmount;
	}

	public void setUnBilledAmount(java.lang.String unBilledAmount) {
		this.unBilledAmount = unBilledAmount;
	}

	    

   
	
	
	
	
}
