package com.multibrand.vo.response.billingResponse;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="PayAccountInfoResponse")
public class PayAccountInfoResponse extends GenericResponse {

	private List<PayAccount> payAccountList;

	public List<PayAccount> getPayAccountList() {
		return payAccountList;
	}

	public void setPayAccountList(List<PayAccount> payAccountList) {
		this.payAccountList = payAccountList;
	}
	
	
	
}
