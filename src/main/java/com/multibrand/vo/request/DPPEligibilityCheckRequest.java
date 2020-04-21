package com.multibrand.vo.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.dto.request.FormEntityRequest;
import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;

public class DPPEligibilityCheckRequest implements FormEntityRequest, Serializable {

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
	private String noOfInstallments = "";
	private String dppInitialDownPayment ="";
	
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
	 * @return the noOfInstallments
	 */
	public String getNoOfInstallments() {
		return noOfInstallments;
	}

	/**
	 * @param noOfInstallments the noOfInstallments to set
	 */
	public void setNoOfInstallments(String noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}

	/**
	 * @return the dppInitialDownPayment
	 */
	public String getDppInitialDownPayment() {
		return dppInitialDownPayment;
	}

	/**
	 * @param dppInitialDownPayment the dppInitialDownPayment to set
	 */
	public void setDppInitialDownPayment(String dppInitialDownPayment) {
		this.dppInitialDownPayment = dppInitialDownPayment;
	}

	/**
	 * @return String representation of this request.
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	

}
