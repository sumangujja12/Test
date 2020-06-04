package com.multibrand.dto.request;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.util.CommonUtil;

public class SalesCleanupAddressRequest extends SalesBaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Length(max = 10, groups = SizeConstraint.class)
	private String streetNum;
	
	@Length(max = 60, groups = SizeConstraint.class)
	private String streetName;
	
	@Length(max = 10, groups = SizeConstraint.class)
	private String aptNum;
	
	@NotEmpty
	@Length(max = 30, groups = SizeConstraint.class)
	private String city;
	
	@NotEmpty
	@Length(min = 2, max = 2, groups = SizeConstraint.class, message ="{err.msg.state.format}")
	private String state;
	
	@NotEmpty
	@Length(max = 10, groups = SizeConstraint.class)
	private String zipCode;
	
	@Length(max = 25, groups = SizeConstraint.class)
	private String poBox;

	public String getStreetNum() {
		return streetNum;
	}

	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getAptNum() {
		return aptNum;
	}

	public void setAptNum(String aptNum) {
		this.aptNum = aptNum;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPoBox() {
		return poBox;
	}

	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}

	public String getStreetAddress() {
	StringBuffer streetAddress = new StringBuffer();
	if (StringUtils.isNotEmpty(this.streetNum)) {
		streetAddress.append(streetNum);
		if (StringUtils.isNotEmpty(streetName)) {
			streetAddress.append(" ").append(streetName);
		}
	} else if (StringUtils.isNotEmpty(streetName)) {
		streetAddress.append(streetName);
	}
	
	return streetAddress.toString();
	}
	
	@Override
	public String toString() {
		return super.toString() + CommonUtil.doRender(this);
	}
	
}
