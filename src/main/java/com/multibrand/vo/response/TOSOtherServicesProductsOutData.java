package com.multibrand.vo.response;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="TOSOtherServicesProductsOutData")
public class TOSOtherServicesProductsOutData {
	
	@SerializedName("results")
	private TOSOtherServicesProducts[] tosOtherServicesProducts;

	public TOSOtherServicesProducts[] getTosOtherServicesProducts() {
		return tosOtherServicesProducts;
	}

	public void setTosOtherServicesProducts(TOSOtherServicesProducts[] tosOtherServicesProducts) {
		this.tosOtherServicesProducts = tosOtherServicesProducts;
	}

	@Override
	public String toString() {
		return "TOSOtherServicesProductsOutData [tosOtherServicesProducts=" + Arrays.toString(tosOtherServicesProducts)
				+ "]";
	}
	
	
	
}
