
package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="GoogleProductSetResponseOutDataResult")
public class GoogleProductSetResponseOutDataResult {

	@SerializedName("PRODUCTSKU")
	private String productSKU;
	@SerializedName("SEQUENCE")
	private String sequence;
	@SerializedName("MSRP")
	private String msrp;
	@SerializedName("PRICE")
	private String price;
	@SerializedName("PRODUCTNAME")
	private String productName;
	@SerializedName("OUTOFSTOCK")
	private String outOfStock;
	
	public String getProductSKU() {
		return productSKU;
	}

	public void setProductSKU(String productSKU) {
		this.productSKU = productSKU;
	}

	public String getSequence() {
		return sequence;
	}
	
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	public String getMsrp() {
		return msrp;
	}

	public void setMsrp(String msrp) {
		this.msrp = msrp;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOutOfStock() {
		return outOfStock;
	}

	public void setOutOfStock(String outOfStock) {
		this.outOfStock = outOfStock;
	}
	
	



}
