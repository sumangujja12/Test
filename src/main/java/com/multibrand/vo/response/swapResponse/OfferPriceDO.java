package com.multibrand.vo.response.swapResponse;

import com.multibrand.util.CommonUtil;

/**
 * 
 * @author mshukla1
 * 
 */
public class OfferPriceDO {

	private String endDate;
	private String price;
	private String priceType;
	private String priceTypeValue;
	private String startDate;

	public String getEndDate() {
		return CommonUtil.changeDateFormat(endDate);
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getPriceTypeValue() {
		return priceTypeValue;
	}

	public void setPriceTypeValue(String priceTypeValue) {
		this.priceTypeValue = priceTypeValue;
	}

	public String getStartDate() {
		return CommonUtil.changeDateFormat(startDate);
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

}
