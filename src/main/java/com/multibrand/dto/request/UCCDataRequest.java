package com.multibrand.dto.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;

public class UCCDataRequest extends BaseAffiliateRequest {

	private static final long serialVersionUID = 1L;
	
		
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 10, groups = SizeConstraint.class)
	private String trackingId;
	
	@Length(max = 40, groups = SizeConstraint.class)
	String lastName;
	

	@Length(max = 40, groups = SizeConstraint.class)
	String firstName;
		
	@Length(max = 20, groups = SizeConstraint.class)
	String tokenizedSSN;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 8, groups = SizeConstraint.class)
	String depositAmount;
	
	@Length(max = 4, groups = SizeConstraint.class)
	String creditScore;
	
	@Length(max = 4, groups = SizeConstraint.class)
	String creditSource;
	
	@Length(max = 1, groups = SizeConstraint.class)
	String creditBucket;
		
	@Length(max = 2000, groups = SizeConstraint.class)
	String creditFactors;

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getTokenizedSSN() {
		return tokenizedSSN;
	}

	public void setTokenizedSSN(String tokenizedSSN) {
		this.tokenizedSSN = tokenizedSSN;
	}

	public String getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}

	public String getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}

	public String getCreditSource() {
		return creditSource;
	}

	public void setCreditSource(String creditSource) {
		this.creditSource = creditSource;
	}

	public String getCreditBucket() {
		return creditBucket;
	}

	public void setCreditBucket(String creditBucket) {
		this.creditBucket = creditBucket;
	}

	public String getCreditFactors() {
		return creditFactors;
	}

	public void setCreditFactors(String creditFactors) {
		this.creditFactors = creditFactors;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
	
}
