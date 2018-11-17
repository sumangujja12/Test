package com.multibrand.vo.response.profileResponse;

public class Products {
	private String productId;
	private String description;
	private String objectId;
	private String manuPartNo;
	public String getManuPartNo() {
		return manuPartNo;
	}
	public void setManuPartNo(String manuPartNo) {
		this.manuPartNo = manuPartNo;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
}
