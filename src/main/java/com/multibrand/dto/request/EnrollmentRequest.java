/**
 * 
 */
package com.multibrand.dto.request;

import org.hibernate.validator.constraints.Length;
import com.multibrand.request.validation.CurrentOrFutureDate;
import com.multibrand.request.validation.FormatConstraint;
import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.request.validation.ValidDateTime;
import com.multibrand.util.CommonUtil;

/**
 * @author jyogapa1
 * 
 */
public class EnrollmentRequest extends BaseAffiliateRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@Length(max = 10, groups = SizeConstraint.class)
	private String trackingId;

	@NotEmpty
	@Length(max = 3, groups = SizeConstraint.class)
	private String transactionType;

	@NotEmpty
	@Length(max = 5, groups = SizeConstraint.class)
	private String tdspCodeCCS;

	@Length(max = 4, groups = SizeConstraint.class)
	private String bpMatchFlag;

	// @NotEmpty
	// @Length(max = 1, groups = SizeConstraint.class)
	private String preferredLanguage;

	@Length(max = 32, groups = SizeConstraint.class)
	private String esid;

	@Length(max = 2, groups = SizeConstraint.class)
	private String switchHoldFlag;

	@Length(max = 1, groups = SizeConstraint.class)
	private String ebillFlag;

	@NotEmpty
	@Length(min= 8, max = 8, groups = SizeConstraint.class)
	private String offerCode;

	@NotEmpty
	@Length(max = 10, groups = SizeConstraint.class)
	private String promoCode;

	@NotEmpty
	@Length(max = 10, groups = SizeConstraint.class)
	private String campaignCode;

	@NotEmpty
	@Length(max = 10, groups = SizeConstraint.class)
	private String productPriceCode;

	@NotEmpty
	@Length(max = 15, groups = SizeConstraint.class)
	private String incentiveCode;

	@Length(max = 2, groups = SizeConstraint.class)
	private String marketSegment;

	@CurrentOrFutureDate
	private String mviDate;

	@NotEmpty
	@Length(max = 10, groups = SizeConstraint.class)
	private String servStreetNum;

	@NotEmpty
	@Length(max = 60, groups = SizeConstraint.class)
	private String servStreetName;

	@Length(max = 10, groups = SizeConstraint.class)
	private String servStreetAptNum;

	@NotEmpty
	@Length(max = 30, groups = SizeConstraint.class)
	private String servCity;

	@NotEmpty
	@Length(min = 2, max = 2, groups = SizeConstraint.class, message = "{err.msg.state.format}")
	private String servState;

	@NotEmpty
	@Length(max = 10, groups = SizeConstraint.class)
	private String servZipCode;

	@Length(max = 10, groups = SizeConstraint.class)
	private String billStreetNum;

	@Length(max = 60, groups = SizeConstraint.class)
	private String billStreetName;

	@Length(max = 10, groups = SizeConstraint.class)
	private String billStreetAptNum;

	@Length(max = 30, groups = SizeConstraint.class)
	private String billCity;

	@Length(min = 2, max = 2, groups = SizeConstraint.class, message = "{err.msg.state.format}")
	private String billState;

	@Length(max = 10, groups = SizeConstraint.class)
	private String billZipCode;

	@Length(max = 25, groups = SizeConstraint.class)
	private String billPOBox;

	@Length(max = 14, groups = SizeConstraint.class)
	private String referralId;

	@ValidDateTime(format = "MMddyyyy", groups = FormatConstraint.class, message = "{err.msg.date.mmddyyyy.format}")
	private String offerDate;

	@ValidDateTime(format = "HH:mm:ss", groups = FormatConstraint.class, message = "{err.msg.date.hhmmss.format}")
	private String offerTime;
	
	@Length(max = 200, groups = SizeConstraint.class)
	private String sapOfferTagline;

	private String securityMethod;
	
	/*	
	 * @return the trackingId
	 */
	public String getTrackingId() {
		return trackingId;
	}

	/**
	 * @param trackingId
	 *            the trackingId to set
	 */
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	/**
	 * @return the tdspCodeCCS
	 */
	public String getTdspCodeCCS() {
		return tdspCodeCCS;
	}

	/**
	 * @param tdspCodeCCS
	 *            the tdspCodeCCS to set
	 */
	public void setTdspCodeCCS(String tdspCodeCCS) {
		this.tdspCodeCCS = tdspCodeCCS;
	}

	/**
	 * @return the bpMatchFlag
	 */
	public String getBpMatchFlag() {
		return bpMatchFlag;
	}

	/**
	 * @param bpMatchFlag
	 *            the bpMatchFlag to set
	 */
	public void setBpMatchFlag(String bpMatchFlag) {
		this.bpMatchFlag = bpMatchFlag;
	}

	/**
	 * @return the preferredLanguage
	 */
	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	/**
	 * @param preferredLanguage
	 *            the preferredLanguage to set
	 */
	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	/**
	 * @return the esid
	 */
	public String getEsid() {
		return esid;
	}

	/**
	 * @param esid
	 *            the esid to set
	 */
	public void setEsid(String esid) {
		this.esid = esid;
	}

	/**
	 * @return the switchHoldFlag
	 */
	public String getSwitchHoldFlag() {
		return switchHoldFlag;
	}

	/**
	 * @param switchHoldFlag
	 *            the switchHoldFlag to set
	 */
	public void setSwitchHoldFlag(String switchHoldFlag) {
		this.switchHoldFlag = switchHoldFlag;
	}

	/**
	 * @return the ebillFlag
	 */
	public String getEbillFlag() {
		return ebillFlag;
	}

	/**
	 * @param ebillFlag
	 *            the ebillFlag to set
	 */
	public void setEbillFlag(String ebillFlag) {
		this.ebillFlag = ebillFlag;
	}

	/**
	 * @return the offerCode
	 */
	public String getOfferCode() {
		return offerCode;
	}

	/**
	 * @param offerCode
	 *            the offerCode to set
	 */
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	/**
	 * @return the promoCode
	 */
	public String getPromoCode() {
		return promoCode;
	}

	/**
	 * @param promoCode
	 *            the promoCode to set
	 */
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	/**
	 * @return the campaignCode
	 */
	public String getCampaignCode() {
		return campaignCode;
	}

	/**
	 * @param campaignCode
	 *            the campaignCode to set
	 */
	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}

	/**
	 * @return the productPriceCode
	 */
	public String getProductPriceCode() {
		return productPriceCode;
	}

	/**
	 * @param productPriceCode
	 *            the productPriceCode to set
	 */
	public void setProductPriceCode(String productPriceCode) {
		this.productPriceCode = productPriceCode;
	}

	/**
	 * @return the incentiveCode
	 */
	public String getIncentiveCode() {
		return incentiveCode;
	}

	/**
	 * @param incentiveCode
	 *            the incentiveCode to set
	 */
	public void setIncentiveCode(String incentiveCode) {
		this.incentiveCode = incentiveCode;
	}

	/**
	 * @return the marketSegment
	 */
	public String getMarketSegment() {
		return marketSegment;
	}

	/**
	 * @param marketSegment
	 *            the marketSegment to set
	 */
	public void setMarketSegment(String marketSegment) {
		this.marketSegment = marketSegment;
	}

	/**
	 * @return the mviDate
	 */
	public String getMviDate() {
		return mviDate;
	}

	/**
	 * @param mviDate
	 *            the mviDate to set
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
	 * @param servStreetNum
	 *            the servStreetNum to set
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
	 * @param servStreetName
	 *            the servStreetName to set
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
	 * @param servStreetAptNum
	 *            the servStreetAptNum to set
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
	 * @param servCity
	 *            the servCity to set
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
	 * @param servState
	 *            the servState to set
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
	 * @param servZipCode
	 *            the servZipCode to set
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
	 * @param billStreetNum
	 *            the billStreetNum to set
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
	 * @param billStreetName
	 *            the billStreetName to set
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
	 * @param billStreetAptNum
	 *            the billStreetAptNum to set
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
	 * @param billCity
	 *            the billCity to set
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
	 * @param billState
	 *            the billState to set
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
	 * @param billZipCode
	 *            the billZipCode to set
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
	 * @param billPOBox
	 *            the billPOBox to set
	 */
	public void setBillPOBox(String billPOBox) {
		this.billPOBox = billPOBox;
	}

	/**
	 * @return the referralId
	 */
	public String getReferralId() {
		return referralId;
	}

	/**
	 * @param referralId
	 *            the referralId to set
	 */
	public void setReferralId(String referralId) {
		this.referralId = referralId;
	}

	/**
	 * @return the offerDate
	 */
	public String getOfferDate() {
		return offerDate;
	}

	/**
	 * @param offerDate
	 *            the offerDate to set
	 */
	public void setOfferDate(String offerDate) {
		this.offerDate = offerDate;
	}

	/**
	 * @return the offerTime
	 */
	public String getOfferTime() {
		return offerTime;
	}

	/**
	 * @param offerTime
	 *            the offerTime to set
	 */
	public void setOfferTime(String offerTime) {
		this.offerTime = offerTime;
	}

	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType
	 *            the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}		

	public String getSapOfferTagline() {
		return sapOfferTagline;
	}

	public void setSapOfferTagline(String sapOfferTagline) {
		this.sapOfferTagline = sapOfferTagline;
	}

	@Override
	public String toString() {
		// return ReflectionToStringBuilder.toString(this);
		return super.toString() + CommonUtil.doRender(this);
	}

	public String getSecurityMethod() {
		return securityMethod;
	}

	public void setSecurityMethod(String securityMethod) {
		this.securityMethod = securityMethod;
	}

}
