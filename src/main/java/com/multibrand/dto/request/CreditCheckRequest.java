/**
 * 
 */
package com.multibrand.dto.request;

import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.multibrand.request.validation.CurrentOrFutureDate;
import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.SizeConstraint;


/**
 * @author Mayank Mishra
 * 
 */
public class CreditCheckRequest extends BaseAffiliateRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty	
	private String trackingId;

	@NotEmpty
	@Size(max = 40, groups = SizeConstraint.class)
	private String firstName;

	@NotEmpty
	@Size(max = 40, groups = SizeConstraint.class)
	private String lastName;

	@Size(max = 20, groups = SizeConstraint.class)
	private String tokenizedSSN;

	@Size(max = 10, groups = SizeConstraint.class)
	private String matchedBP;

	@NotEmpty
	@Size(max = 3, groups = SizeConstraint.class)
	private String transactionType; //MVI or SWI

	@Size(max = 32, groups = SizeConstraint.class)
	private String esid;

	@NotEmpty
	@Size(max = 8, groups = SizeConstraint.class)
	private String offerCode;

	@CurrentOrFutureDate
	private String mviDate;

	@NotEmpty
	@Size(max = 10, groups = SizeConstraint.class)
	private String servStreetNum;

	@NotEmpty
	@Size(max = 60, groups = SizeConstraint.class)
	private String servStreetName;

	@Size(max = 10, groups = SizeConstraint.class)
	private String servStreetAptNum;

	@NotEmpty
	@Size(max = 30, groups = SizeConstraint.class)
	private String servCity;

	@NotEmpty
	@Size(min = 2, max = 2, groups = SizeConstraint.class)
	private String servState;

	@NotEmpty
	@Size(max = 10, groups = SizeConstraint.class)
	private String servZipCode;

	@Size(max = 10, groups = SizeConstraint.class)
	private String billStreetNum;

	@Size(max = 60, groups = SizeConstraint.class)
	private String billStreetName;

	@Size(max = 10, groups = SizeConstraint.class)
	private String billStreetAptNum;

	@Size(max = 30, groups = SizeConstraint.class)
	private String billCity;

	@Size(min = 2, max = 2, groups = SizeConstraint.class)
	private String billState;

	@Size(max = 10, groups = SizeConstraint.class)
	private String billZipCode;

	@Size(max = 25, groups = SizeConstraint.class)
	private String billPOBox;

	@Size(max = 4, groups = SizeConstraint.class)
	private String bpMatchFlag;
	

	/**
	 * @return the trackingId
	 */
	public String getTrackingId() {
		return trackingId;
	}
	/**
	 * @param trackingId the trackingId to set
	 */
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the tokenizedSSN
	 */
	public String getTokenizedSSN() {
		return tokenizedSSN;
	}
	/**
	 * @param tokenizedSSN the tokenizedSSN to set
	 */
	public void setTokenizedSSN(String tokenizedSSN) {
		this.tokenizedSSN = tokenizedSSN;
	}
	/**
	 * @return the matchedBP
	 */
	public String getMatchedBP() {
		return matchedBP;
	}
	/**
	 * @param matchedBP the matchedBP to set
	 */
	public void setMatchedBP(String matchedBP) {
		this.matchedBP = matchedBP;
	}
	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	/**
	 * @return the esid
	 */
	public String getEsid() {
		return esid;
	}
	/**
	 * @param esid the esid to set
	 */
	public void setEsid(String esid) {
		this.esid = esid;
	}
	/**
	 * @return the offerCode
	 */
	public String getOfferCode() {
		return offerCode;
	}
	/**
	 * @param offerCode the offerCode to set
	 */
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	/**
	 * @return the mviDate
	 */
	public String getMviDate() {
		return mviDate;
	}
	/**
	 * @param mviDate the mviDate to set
	 */
	public void setMviDate(String mviDate) {
		this.mviDate = mviDate;
	}
	/**
	 * @return the servStreetNum
	 */
	public String getServStreetNum() {
		return servStreetNum;
	}
	/**
	 * @param servStreetNum the servStreetNum to set
	 */
	public void setServStreetNum(String servStreetNum) {
		this.servStreetNum = servStreetNum;
	}
	/**
	 * @return the servStreetName
	 */
	public String getServStreetName() {
		return servStreetName;
	}
	/**
	 * @param servStreetName the servStreetName to set
	 */
	public void setServStreetName(String servStreetName) {
		this.servStreetName = servStreetName;
	}
	/**
	 * @return the servStreetAptNum
	 */
	public String getServStreetAptNum() {
		return servStreetAptNum;
	}
	/**
	 * @param servStreetAptNum the servStreetAptNum to set
	 */
	public void setServStreetAptNum(String servStreetAptNum) {
		this.servStreetAptNum = servStreetAptNum;
	}
	/**
	 * @return the servCity
	 */
	public String getServCity() {
		return servCity;
	}
	/**
	 * @param servCity the servCity to set
	 */
	public void setServCity(String servCity) {
		this.servCity = servCity;
	}
	/**
	 * @return the servState
	 */
	public String getServState() {
		return servState;
	}
	/**
	 * @param servState the servState to set
	 */
	public void setServState(String servState) {
		this.servState = servState;
	}
	/**
	 * @return the servZipCode
	 */
	public String getServZipCode() {
		return servZipCode;
	}
	/**
	 * @param servZipCode the servZipCode to set
	 */
	public void setServZipCode(String servZipCode) {
		this.servZipCode = servZipCode;
	}
	/**
	 * @return the billStreetNum
	 */
	public String getBillStreetNum() {
		return billStreetNum;
	}
	/**
	 * @param billStreetNum the billStreetNum to set
	 */
	public void setBillStreetNum(String billStreetNum) {
		this.billStreetNum = billStreetNum;
	}
	/**
	 * @return the billStreetName
	 */
	public String getBillStreetName() {
		return billStreetName;
	}
	/**
	 * @param billStreetName the billStreetName to set
	 */
	public void setBillStreetName(String billStreetName) {
		this.billStreetName = billStreetName;
	}
	/**
	 * @return the billStreetAptNum
	 */
	public String getBillStreetAptNum() {
		return billStreetAptNum;
	}
	/**
	 * @param billStreetAptNum the billStreetAptNum to set
	 */
	public void setBillStreetAptNum(String billStreetAptNum) {
		this.billStreetAptNum = billStreetAptNum;
	}
	/**
	 * @return the billCity
	 */
	public String getBillCity() {
		return billCity;
	}
	/**
	 * @param billCity the billCity to set
	 */
	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}
	/**
	 * @return the billState
	 */
	public String getBillState() {
		return billState;
	}
	/**
	 * @param billState the billState to set
	 */
	public void setBillState(String billState) {
		this.billState = billState;
	}
	/**
	 * @return the billZipCode
	 */
	public String getBillZipCode() {
		return billZipCode;
	}
	/**
	 * @param billZipCode the billZipCode to set
	 */
	public void setBillZipCode(String billZipCode) {
		this.billZipCode = billZipCode;
	}
	/**
	 * @return the billPOBox
	 */
	public String getBillPOBox() {
		return billPOBox;
	}
	/**
	 * @param billPOBox the billPOBox to set
	 */
	public void setBillPOBox(String billPOBox) {
		this.billPOBox = billPOBox;
	}
	/**
	 * @return the bpMatchFlag
	 */
	public String getBpMatchFlag() {
		return bpMatchFlag;
	}
	/**
	 * @param bpMatchFlag the bpMatchFlag to set
	 */
	public void setBpMatchFlag(String bpMatchFlag) {
		this.bpMatchFlag = bpMatchFlag;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
		//return super.toString() + CommonUtil.doRender(this)
	}	
}