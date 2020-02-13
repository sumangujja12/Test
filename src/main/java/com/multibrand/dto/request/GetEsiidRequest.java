package com.multibrand.dto.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;


public class GetEsiidRequest extends BaseAffiliateRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8518551731038329899L;
	
	@NotBlank(groups = BasicConstraint.class)
	private String strStreet;
	@NotBlank(groups = BasicConstraint.class)
	private String strZipCode;
	@NotBlank(groups = BasicConstraint.class)
	private String strCity;
	@NotBlank(groups = BasicConstraint.class)
	private String strState;
	@Length(max = 10, groups = SizeConstraint.class)
	private String strAprtNum;
	
	
	public String getStrStreet() {
		return strStreet;
	}
	public void setStrStreet(String strStreet) {
		this.strStreet = strStreet;
	}
	public String getStrZipCode() {
		return strZipCode;
	}
	public void setStrZipCode(String strZipCode) {
		this.strZipCode = strZipCode;
	}
	public String getStrCity() {
		return strCity;
	}
	public void setStrCity(String strCity) {
		this.strCity = strCity;
	}
	public String getStrState() {
		return strState;
	}
	public void setStrState(String strState) {
		this.strState = strState;
	}
	public String getStrAprtNum() {
		return strAprtNum;
	}
	public void setStrAprtNum(String strAprtNum) {
		this.strAprtNum = strAprtNum;
	}

}
