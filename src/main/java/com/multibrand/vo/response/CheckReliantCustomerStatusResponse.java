package com.multibrand.vo.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="CheckReliantCustomerStatusResponse")
public class CheckReliantCustomerStatusResponse extends GenericResponse implements Serializable {

	private static final long serialVersionUID = 6852100962768982444L;

	@SerializedName("d")
	CheckReliantCustomerStatusOutData checkReliantCustomerStatusOutData;
	
	
	public CheckReliantCustomerStatusOutData getCheckReliantCustomerStatusOutData() {
		return checkReliantCustomerStatusOutData;
	}


	public void setCheckReliantCustomerStatusOutData(CheckReliantCustomerStatusOutData checkReliantCustomerStatusOutData) {
		this.checkReliantCustomerStatusOutData = checkReliantCustomerStatusOutData;
	}


	@Override
	public String toString() {
		return "CheckReliantCustomerStatusResponse [checkReliantCustomerStatusOutData="
				+ checkReliantCustomerStatusOutData + "]";
	}
	
	
}
