package com.multibrand.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.SizeConstraint;


public class EsidRequest extends SalesBaseRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8518551731038329899L;
	
	@NotEmpty
	private String servStreet;
	@NotEmpty
	private String servZipCode;
	@NotEmpty
	private String servCity;
	@Size(max = 10, groups = SizeConstraint.class)
	private String servStreetAptNum;
	public String getServStreet() {
		return servStreet;
	}
	public void setServStreet(String servStreet) {
		this.servStreet = servStreet;
	}
	public String getServZipCode() {
		return servZipCode;
	}
	public void setServZipCode(String servZipCode) {
		this.servZipCode = servZipCode;
	}
	public String getServCity() {
		return servCity;
	}
	public void setServCity(String servCity) {
		this.servCity = servCity;
	}
	public String getServStreetAptNum() {
		return servStreetAptNum;
	}
	public void setServStreetAptNum(String servStreetAptNum) {
		this.servStreetAptNum = servStreetAptNum;
	}
	
	public String toString(){
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
