/**
 * 
 */
package com.multibrand.dto.request;

import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.SizeConstraint;

/**
 * @author Mayank Mishra
 * 
 */
public class EsidCalendarRequest extends BaseAffiliateRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty	
	private String trackingId;
	@NotEmpty
	@Size(max = 3, groups = SizeConstraint.class)
	private String transactionType; //MVI or SWI
	@NotEmpty
	@Size(max = 5, groups = SizeConstraint.class)
	private String tdspCodeCCS;

	@NotEmpty
	private String servStreetNum;

	@NotEmpty
	private String servStreetName;
	
	@Size(max = 10, groups = SizeConstraint.class)
	private String servStreetAptNum;

	@NotEmpty
	@Size(min = 5, max = 10, groups = SizeConstraint.class)
	private String servZipCode;

	@Size(max = 4, groups = SizeConstraint.class)
	private String bpMatchFlag;

	private String requestType = ESID_CALENDAR_REQ_TYPE.FULL.toString();
	
	@Size(max = 32, groups = SizeConstraint.class)
	private String esid;
	
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
	 * @return the tdspCodeCCS
	 */
	public String getTdspCodeCCS() {
		return tdspCodeCCS;
	}

	/**
	 * @param tdspCodeCCS the tdspCodeCCS to set
	 */
	public void setTdspCodeCCS(String tdspCodeCCS) {
		this.tdspCodeCCS = tdspCodeCCS;
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
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEsid() {
		return esid;
	}

	public void setEsid(String esid) {
		this.esid = esid;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
		//return super.toString() + CommonUtil.doRender(this)
	}	
}