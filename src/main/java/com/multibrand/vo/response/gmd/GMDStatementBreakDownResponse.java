package com.multibrand.vo.response.gmd;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="GMDStatementBreakDownResponse")
@Component

public class GMDStatementBreakDownResponse extends GenericResponse{
	
    private double rate;
    private BigDecimal totalUsage;
    private BigDecimal avgPrice;
    private BigDecimal totalCost;
    private List<Breakdown> breakdown;
    private List<GMDReturnCharge> returnCharge;
    
    
	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}
	/**
	 * @return the totalUsage
	 */
	public BigDecimal getTotalUsage() {
		return totalUsage;
	}
	/**
	 * @param totalUsage the totalUsage to set
	 */
	public void setTotalUsage(BigDecimal totalUsage) {
		this.totalUsage = totalUsage;
	}
	/**
	 * @return the totalCost
	 */
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	/**
	 * @return the breakdown
	 */
	public List<Breakdown> getBreakdown() {
		return breakdown;
	}
	/**
	 * @param breakdown the breakdown to set
	 */
	public void setBreakdown(List<Breakdown> breakdown) {
		this.breakdown = breakdown;
	}
	/**
	 * @return the returnCharge
	 */
	public List<GMDReturnCharge> getReturnCharge() {
		return returnCharge;
	}
	/**
	 * @param returnCharge the returnCharge to set
	 */
	public void setReturnCharge(List<GMDReturnCharge> returnCharge) {
		this.returnCharge = returnCharge;
	}
	/**
	 * @return the avgPrice
	 */
	public BigDecimal getAvgPrice() {
		return avgPrice;
	}
	/**
	 * @param avgPrice the avgPrice to set
	 */
	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}
}