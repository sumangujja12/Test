package com.multibrand.vo.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.dto.request.FormEntityRequest;
import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;

public class DPPSubmitRequest implements FormEntityRequest, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9054119448214804045L;
	@NotBlank(groups = BasicConstraint.class)
	private String companyCode ="";
	private String brandName ="";
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 12, groups = SizeConstraint.class)
	private String contractAccountNumber ="";
	@Length(max = 10, groups = SizeConstraint.class)
	private String contractId ="";
	
	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * @return the contractAccountNumber
	 */
	public String getContractAccountNumber() {
		return contractAccountNumber;
	}

	/**
	 * @param contractAccountNumber the contractAccountNumber to set
	 */
	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}



	/**
	 * @return the contractId
	 */
	public String getContractId() {
		return contractId;
	}

	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	
	/**
	 * @return String representation of this request.
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	

}
