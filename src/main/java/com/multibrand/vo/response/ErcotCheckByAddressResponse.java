package com.multibrand.vo.response;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Start | PBI 52935 | MBAR: Sprint 17 -ERCOT ESID LOOKUP REST IMPL | Jyothi | 9/21/2020
 */
public class ErcotCheckByAddressResponse implements Serializable {
	
	private static final long serialVersionUID = -6174561651074813200L;
	
    private SearchEsiidOutput SearchEsiidOutput;

	public SearchEsiidOutput getSearchEsiidOutput() {
		return SearchEsiidOutput;
	}

	public void setSearchEsiidOutput(SearchEsiidOutput searchEsiidOutput) {
		this.SearchEsiidOutput = searchEsiidOutput;
	}    
    
	/**
	 * @return String representation of this request.
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
