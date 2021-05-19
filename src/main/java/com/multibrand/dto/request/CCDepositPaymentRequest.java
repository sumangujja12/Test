package com.multibrand.dto.request;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;


/**
 * The Class CCDepositPaymentRequest.
 */
public class CCDepositPaymentRequest extends BaseAffiliateRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/** The tracking id. */
	@NotBlank(groups = BasicConstraint.class)
	@Size(max = 10, groups = SizeConstraint.class)
	private String trackingId;
	
	/** The ca number. */
	@NotBlank(groups = BasicConstraint.class)
	@Size(max = 12, groups = SizeConstraint.class)
	private String caNumber;
	
	/** The bpid. */
	@NotBlank(groups = BasicConstraint.class)
	@Size(max = 10, groups = SizeConstraint.class)
	private String bpid;
	
	/** The tokenized cc number. */
	@NotBlank(groups = BasicConstraint.class)
	private String tokenizedCCNumber; 
	
	/** The cvv number. */	
	@Size(max = 3, groups = SizeConstraint.class)
	private String cvvNumber; 
	
	/** The expiration date. */
	@NotBlank(groups = BasicConstraint.class)
	private String expirationDate; 
	
	/** The billing zip. */
	@NotBlank(groups = BasicConstraint.class)
	@Size(max = 10, groups = SizeConstraint.class)
	private String billingZip;  
	
	/** The payment amount. */
	@NotBlank(groups = BasicConstraint.class)
	@Size(max = 11, groups = SizeConstraint.class)
	private String depositAmount;
	
	
	/**
	 * Gets the tracking id.
	 *
	 * @return the tracking id
	 */
	public String getTrackingId() {
		return trackingId;
	}
	
	/**
	 * Sets the tracking id.
	 *
	 * @param trackingId the new tracking id
	 */
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	
	/**
	 * Gets the ca number.
	 *
	 * @return the ca number
	 */
	public String getCaNumber() {
		return caNumber;
	}
	
	/**
	 * Sets the ca number.
	 *
	 * @param caNumber the new ca number
	 */
	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}
	
	/**
	 * Gets the bpid.
	 *
	 * @return the bpid
	 */
	public String getBpid() {
		return bpid;
	}
	
	/**
	 * Sets the bpid.
	 *
	 * @param bpid the new bpid
	 */
	public void setBpid(String bpid) {
		this.bpid = bpid;
	}
	
	/**
	 * Gets the tokenized cc number.
	 *
	 * @return the tokenized cc number
	 */
	public String getTokenizedCCNumber() {
		return tokenizedCCNumber;
	}
	
	/**
	 * Sets the tokenized cc number.
	 *
	 * @param tokenizedCCNumber the new tokenized cc number
	 */
	public void setTokenizedCCNumber(String tokenizedCCNumber) {
		this.tokenizedCCNumber = tokenizedCCNumber;
	}
	
	/**
	 * Gets the cvv number.
	 *
	 * @return the cvv number
	 */
	public String getCvvNumber() {
		return cvvNumber;
	}
	
	/**
	 * Sets the cvv number.
	 *
	 * @param cvvNumber the new cvv number
	 */
	public void setCvvNumber(String cvvNumber) {
		this.cvvNumber = cvvNumber;
	}
	
	/**
	 * Gets the expiration date.
	 *
	 * @return the expiration date
	 */
	public String getExpirationDate() {
		return expirationDate;
	}
	
	/**
	 * Sets the expiration date.
	 *
	 * @param expirationDate the new expiration date
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	/**
	 * Gets the billing zip.
	 *
	 * @return the billing zip
	 */
	public String getBillingZip() {
		return billingZip;
	}
	
	/**
	 * Sets the billing zip.
	 *
	 * @param billingZip the new billing zip
	 */
	public void setBillingZip(String billingZip) {
		this.billingZip = billingZip;
	}

	public String getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}
			
}
