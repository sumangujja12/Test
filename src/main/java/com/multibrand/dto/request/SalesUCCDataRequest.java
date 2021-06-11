package com.multibrand.dto.request;

import javax.validation.constraints.Size;

import com.multibrand.request.validation.CurrentOrFutureDate;
import com.multibrand.request.validation.NotEmpty;

public class SalesUCCDataRequest extends SalesOERequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@NotEmpty
	@Size(max = 40)
	String lastName;
	

	@NotEmpty
	@Size(max = 40)
	String firstName;
		
	@Size(max = 20)
	String tokenizedSSN;
	
	@NotEmpty
	@Size(max = 8)
	String depositAmount;
	
	@Size(max = 4)
	String creditScore;
	
	@Size(max = 4)
	String creditSource;
	
	@Size(max = 1)
	String creditBucket;
		
	@Size(max = 2000)
	String creditFactors;	

	@CurrentOrFutureDate
    private String mviDate;

	public String getMviDate() {
		return mviDate;
	}

	public void setMviDate(String mviDate) {
		this.mviDate = mviDate;
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

	
	
}
