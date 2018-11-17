package com.multibrand.vo.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.multibrand.dto.request.FormEntityRequest;
import com.multibrand.util.Constants;

public class UserIdRequest implements FormEntityRequest, Constants,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String companycode;
	private String brandname;
	private String contractaccountnumber;

	public String getCompanycode() {
		return companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public String getContractaccountnumber() {
		return contractaccountnumber;
	}

	public void setContractaccountnumber(String contractaccountnumber) {
		this.contractaccountnumber = contractaccountnumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return String representation of this request.
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
