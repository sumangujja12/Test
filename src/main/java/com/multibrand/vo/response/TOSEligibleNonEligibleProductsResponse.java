package com.multibrand.vo.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="TOSEligibleNonEligibleProductsResponse")
public class TOSEligibleNonEligibleProductsResponse  extends GenericResponse implements Serializable {

	private static final long serialVersionUID = 8849945737849845766L;
	
	@SerializedName("d")
	private TOSOtherServicesProductsOutData tosOtherServicesProductsOutData;

	public TOSOtherServicesProductsOutData getTosOtherServicesProductsOutData() {
		return tosOtherServicesProductsOutData;
	}

	public void setTosOtherServicesProductsOutData(TOSOtherServicesProductsOutData tosOtherServicesProductsOutData) {
		this.tosOtherServicesProductsOutData = tosOtherServicesProductsOutData;
	}

	@Override
	public String toString() {
		return "TOSEligibleNonEligibleProductsResponse [tosOtherServicesProductsOutData="
				+ tosOtherServicesProductsOutData + "]";
	}

	
}
