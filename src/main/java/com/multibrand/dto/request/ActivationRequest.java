package com.multibrand.dto.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.util.Constants;

public class ActivationRequest implements FormEntityRequest, Constants, Serializable {

	
	
	@NotBlank(groups = BasicConstraint.class)
	private String companyCode;
	
	private String brandId;
	
	@NotBlank(groups = BasicConstraint.class)
	private String activationCode;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 10, groups = SizeConstraint.class)
	private String businessPartner;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 10, groups = SizeConstraint.class)
	private String contactNumber;
	
	private String contract;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 12, groups = SizeConstraint.class)
	private String contractAccount;
	
	@NotBlank(groups = BasicConstraint.class)
	private String language;
	
	private String userId;
	private String userUniqueId;
	
	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

		public String getBusinessPartner() {
		return businessPartner;
	}

	public void setBusinessPartner(String businessPartner) {
		this.businessPartner = businessPartner;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getContractAccount() {
		return contractAccount;
	}

	public void setContractAccount(String contractAccount) {
		this.contractAccount = contractAccount;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserUniqueId() {
		return userUniqueId;
	}

	public void setUserUniqueId(String userUniqueId) {
		this.userUniqueId = userUniqueId;
	}

	
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	
}
