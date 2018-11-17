package com.multibrand.vo.response;

import com.google.gson.annotations.SerializedName;

public class TOSOtherServicesProducts {
	
	@SerializedName("PRODUCT")
	private String productName;
	@SerializedName("PRODUCTID")
	private String productId;
	@SerializedName("PRICE")
	private String price;
	@SerializedName("ELIGIBLE")
	private String eligibleFlag;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getEligibleFlag() {
		return eligibleFlag;
	}
	public void setEligibleFlag(String eligibleFlag) {
		this.eligibleFlag = eligibleFlag;
	}
	@Override
	public String toString() {
		return "TOSProductsResult [productName=" + productName + ", productId=" + productId + ", price=" + price
				+ ", eligibleFlag=" + eligibleFlag + "]";
	}
	
}
