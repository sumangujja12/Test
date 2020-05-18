package com.multibrand.vo.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.dto.request.FormEntityRequest;
import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;

public class PaymentExtensionSubmitRequest implements FormEntityRequest, Serializable {

	private static final long serialVersionUID = -9054119448214804045L;
	
	@NotBlank(groups = BasicConstraint.class)
	private String companyCode ="";
	
	@NotBlank(groups = BasicConstraint.class)
	private String brandName ="";
	
	@NotBlank(groups = BasicConstraint.class)
	private String contractAccountName ="";
	
	@Length(max = 12, groups = SizeConstraint.class)
	private String contractAccountNumber ="";
	
	@NotBlank(groups = BasicConstraint.class)
	private String businessPartnerNumber;
	
	@NotBlank(groups = BasicConstraint.class)
	private String paymentExtDate ="";
	
	@NotBlank(groups = BasicConstraint.class)
	private String checkDigit;
	
	@NotBlank(groups = BasicConstraint.class)
	private String email;
	
	@NotBlank(groups = BasicConstraint.class)
	private String payExtDueAmt ="";
	
	@NotBlank(groups = BasicConstraint.class)
	private String languageCode ="";
	
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
	 * @return the paymentExtDate
	 */
	public String getPaymentExtDate() {
		return paymentExtDate;
	}

	/**
	 * @param paymentExtDate the paymentExtDate to set
	 */
	public void setPaymentExtDate(String paymentExtDate) {
		this.paymentExtDate = paymentExtDate;
	}

	/**
	 * @return the businessPartnerNumber
	 */
	public String getBusinessPartnerNumber() {
		return businessPartnerNumber;
	}

	/**
	 * @param businessPartnerNumber the businessPartnerNumber to set
	 */
	public void setBusinessPartnerNumber(String businessPartnerNumber) {
		this.businessPartnerNumber = businessPartnerNumber;
	}

	/**
	 * @return the checkDigit
	 */
	public String getCheckDigit() {
		return checkDigit;
	}

	/**
	 * @param checkDigit the checkDigit to set
	 */
	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	/**
	 * @return the contractAccountName
	 */
	public String getContractAccountName() {
		return contractAccountName;
	}

	/**
	 * @param contractAccountName the contractAccountName to set
	 */
	public void setContractAccountName(String contractAccountName) {
		this.contractAccountName = contractAccountName;
	}
	
	/**
	 * @return the payExtDueAmt
	 */
	public String getPayExtDueAmt() {
		return payExtDueAmt;
	}

	/**
	 * @param payExtDueAmt the payExtDueAmt to set
	 */
	public void setPayExtDueAmt(String payExtDueAmt) {
		this.payExtDueAmt = payExtDueAmt;
	}

	
	/**
	 * @return the languageCode
	 */
	public String getLanguageCode() {
		return languageCode;
	}

	/**
	 * @param languageCode the languageCode to set
	 */
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	/**
	 * @return String representation of this request.
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
