package com.multibrand.vo.response;

import java.io.Serializable;

public class TDSPChargeDO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String perMonthValue;
	private String perKWValue;
	
	public String getPerMonthValue() {
		return perMonthValue;
	}
	public void setPerMonthValue(String perMonthValue) {
		this.perMonthValue = perMonthValue;
	}
	public String getPerKWValue() {
		return perKWValue;
	}
	public void setPerKWValue(String perKWValue) {
		this.perKWValue = perKWValue;
	}	
	
}
