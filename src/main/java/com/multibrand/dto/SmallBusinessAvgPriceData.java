package com.multibrand.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SmallBusinessAvgPriceData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String season;

	private String dateStart;

	private String dateEnd;

	private BigDecimal avgPrice;
	private String priceType;
	
	
	
	public SmallBusinessAvgPriceData(String season, String dateStart, String dateEnd, BigDecimal avgPrice,
			String priceType) {
		super();
		this.season = season;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.avgPrice = avgPrice;
		this.priceType = priceType;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public BigDecimal getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	} 
	
	
	

}
