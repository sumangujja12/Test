package com.multibrand.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.util.Constants;


@XmlRootElement(name = "userProfileUpdateRequest")

public class SFUserProfileUpdateSyncRequest implements FormEntityRequest, Constants, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9113739934701577346L;
	@NotNull
	private String oldEmailAddress;
	@NotNull
	private String newEmailAddress;
	@NotNull
	private String leaseId;
	private String companyCode;
	private String reqApplication;
	 
	/**
	 * @return the oldEmailAddress
	 */
	public String getOldEmailAddress() {
		return oldEmailAddress;
	}
	/**
	 * @param oldEmailAddress the oldEmailAddress to set
	 */
	public void setOldEmailAddress(String oldEmailAddress) {
		this.oldEmailAddress = oldEmailAddress;
	}
	/**
	 * @return the newEmailAddress
	 */
	public String getNewEmailAddress() {
		return newEmailAddress;
	}
	/**
	 * @param newEmailAddress the newEmailAddress to set
	 */
	public void setNewEmailAddress(String newEmailAddress) {
		this.newEmailAddress = newEmailAddress;
	}
	/**
	 * @return the leaseId
	 */
	public String getLeaseId() {
		return leaseId;
	}
	/**
	 * @param leaseId the leaseId to set
	 */
	public void setLeaseId(String leaseId) {
		this.leaseId = leaseId;
	}
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
	 * @return the reqApplication
	 */
	public String getReqApplication() {
		return reqApplication;
	}
	/**
	 * @param reqApplication the reqApplication to set
	 */
	public void setReqApplication(String reqApplication) {
		this.reqApplication = reqApplication;
	}	
}
