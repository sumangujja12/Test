package com.multibrand.dto.request;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;

public class BankDepositPaymentRequest extends BaseAffiliateRequest {

	private static final long serialVersionUID = 1L;
	
		
	@NotBlank(groups = BasicConstraint.class)
	@Size(max = 10, groups = SizeConstraint.class)
	private String trackingId;
	
	@NotBlank(groups = BasicConstraint.class)
	@Size(max = 12, groups = SizeConstraint.class)
	private String caNumber;
	
	@NotBlank(groups = BasicConstraint.class)
	@Size(max = 10, groups = SizeConstraint.class)
	private String bpid;
	
	@NotBlank(groups = BasicConstraint.class)
	private String tokenizedBankAccountNumber; 
	
	@NotBlank(groups = BasicConstraint.class)
	@Size(max = 9, groups = SizeConstraint.class)
	private String tokenizedBankRoutingNumber; 
	
	@NotBlank(groups = BasicConstraint.class)
	@Size(max = 11, groups = SizeConstraint.class)
	private String depositAmount;
	
	
	public String getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	public String getCaNumber() {
		return caNumber;
	}
	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}
	public String getBpid() {
		return bpid;
	}
	public void setBpid(String bpid) {
		this.bpid = bpid;
	}
	public String getTokenizedBankAccountNumber() {
		return tokenizedBankAccountNumber;
	}
	public void setTokenizedBankAccountNumber(String tokenizedBankAccountNumber) {
		this.tokenizedBankAccountNumber = tokenizedBankAccountNumber;
	}
	public String getTokenizedBankRoutingNumber() {
		return tokenizedBankRoutingNumber;
	}
	public void setTokenizedBankRoutingNumber(String tokenizedBankRoutingNumber) {
		this.tokenizedBankRoutingNumber = tokenizedBankRoutingNumber;
	}
	public String getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}	
	
}
