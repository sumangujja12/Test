package com.multibrand.vo.response;

import com.multibrand.util.CommonUtil;

public class OfferPriceDO
{
  private static final long serialVersionUID = 1L;
  private String priceType;
  private String priceTypeValue;
  private String startDate;
  private String endDate;
  private String price;
  private String offerPriceCode;
  private String season;
  private String prodTypeString;

  public String getPriceType()
  {
    return this.priceType;
  }
  public void setPriceType(String priceType) {
    this.priceType = priceType;
  }
  public String getPriceTypeValue() {
    return this.priceTypeValue;
  }
  public void setPriceTypeValue(String priceTypeValue) {
    this.priceTypeValue = priceTypeValue;
  }
  public String getStartDate() {
    return CommonUtil.changeDateFormat(this.startDate);
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  public String getEndDate() {
    return CommonUtil.changeDateFormat(this.endDate);
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  public String getPrice() {
    return this.price;
  }
  public void setPrice(String price) {
    this.price = price;
  }
  public String getSeason() {
    return this.season;
  }
  public void setSeason(String season) {
    this.season = season;
  }
	
  public String getOfferPriceCode() {
		return offerPriceCode;
	}
	
  public void setOfferPriceCode(String offerPriceCode) {
		this.offerPriceCode = offerPriceCode;
	}
public String getProdTypeString() {
	return prodTypeString;
}
public void setProdTypeString(String prodTypeString) {
	this.prodTypeString = prodTypeString;
}
  
  
}