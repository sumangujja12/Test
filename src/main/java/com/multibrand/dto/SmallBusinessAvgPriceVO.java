package com.multibrand.dto;

import java.io.Serializable;

public class SmallBusinessAvgPriceVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String key;
	
	private SmallBusinessAvgPriceData value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public SmallBusinessAvgPriceData getValue() {
		return value;
	}
	public void setValue(SmallBusinessAvgPriceData value) {
		this.value = value;
	}

	

}
