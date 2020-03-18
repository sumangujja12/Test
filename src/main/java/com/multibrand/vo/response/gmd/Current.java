package com.multibrand.vo.response.gmd;

import java.math.BigDecimal;

public class Current {
	
    private BigDecimal price;
    private String lastUpdated;
    private String nextUpdate;
    
    
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the lastUpdated
	 */
	public String getLastUpdated() {
		return lastUpdated;
	}
	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	/**
	 * @return the nextUpdate
	 */
	public String getNextUpdate() {
		return nextUpdate;
	}
	/**
	 * @param nextUpdate the nextUpdate to set
	 */
	public void setNextUpdate(String nextUpdate) {
		this.nextUpdate = nextUpdate;
	}
}
