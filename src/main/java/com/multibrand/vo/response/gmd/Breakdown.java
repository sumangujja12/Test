package com.multibrand.vo.response.gmd;

import java.math.BigDecimal;
import java.util.List;

public class Breakdown {

	 private String group;
	 private BigDecimal totalCost;
	 private List<Costs> costs;
	 
	 
	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}
	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
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
	 * @return the costs
	 */
	public List<Costs> getCosts() {
		return costs;
	}
	/**
	 * @param costs the costs to set
	 */
	public void setCosts(List<Costs> costs) {
		this.costs = costs;
	}
	    
}
