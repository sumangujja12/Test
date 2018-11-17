package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;


@XmlRootElement(name="DashboardResponse")
public class SalesforceDashboardResponse extends GenericResponse {
	
	@SerializedName("MESSAGE")
	private String message;
	
	@SerializedName("MESSAGEOBJECT")
	private List<SalesforceMessageObjResponse> messageObject;	 
	
	@SerializedName("LEASEIDMATCH")
	private String leaseIdMatch;
	
	
	@SerializedName("USERACCOUNT")
	List<SalesforceUserAccountResponse> userAccount;

	@SerializedName("USERPRODUCTION")
	SalesforceUserProductionResponse mySolarProduction;
	
	@SerializedName("FACILITYPRODUCTION")
	SalesforceFacilityProductionResponse communitySolarProduction;

	@SerializedName("FACILITY")
	List<SalesforceFacilitytResponse> facility;
	
	
	@SerializedName("CONTRACTDOCUMENTID")
	private String contractDocumentId;
	
	@SerializedName("CompanyCode")
	private String companyCode;

	
	
	/**
	 * @return the communitySolarProduction
	 */
	public SalesforceFacilityProductionResponse getCommunitySolarProduction() {
		return communitySolarProduction;
	}

	/**
	 * @param communitySolarProduction the communitySolarProduction to set
	 */
	public void setCommunitySolarProduction(SalesforceFacilityProductionResponse communitySolarProduction) {
		this.communitySolarProduction = communitySolarProduction;
	}

	/**
	 * @return the userAccount
	 */
	public List<SalesforceUserAccountResponse> getUserAccount() {
		return userAccount;
	}

	/**
	 * @param userAccount the userAccount to set
	 */
	public void setUserAccount(List<SalesforceUserAccountResponse> userAccount) {
		this.userAccount = userAccount;
	}

	

	/**
	 * @return the mySolarProduction
	 */
	public SalesforceUserProductionResponse getMySolarProduction() {
		return mySolarProduction;
	}

	/**
	 * @param mySolarProduction the mySolarProduction to set
	 */
	public void setMySolarProduction(SalesforceUserProductionResponse mySolarProduction) {
		this.mySolarProduction = mySolarProduction;
	}

	/**
	 * @return the facility
	 */
	public List<SalesforceFacilitytResponse> getFacility() {
		return facility;
	}

	/**
	 * @param facility the facility to set
	 */
	public void setFacility(List<SalesforceFacilitytResponse> facility) {
		this.facility = facility;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the leaseIdMatch
	 */
	public String getLeaseIdMatch() {
		return leaseIdMatch;
	}

	/**
	 * @param leaseIdMatch the leaseIdMatch to set
	 */
	public void setLeaseIdMatch(String leaseIdMatch) {
		this.leaseIdMatch = leaseIdMatch;
	}

	/**
	 * @return the contractDocumentId
	 */
	public String getContractDocumentId() {
		return contractDocumentId;
	}

	/**
	 * @param contractDocumentId the contractDocumentId to set
	 */
	public void setContractDocumentId(String contractDocumentId) {
		this.contractDocumentId = contractDocumentId;
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
	 * @return the messageObject
	 */
	public List<SalesforceMessageObjResponse> getMessageObject() {
		return messageObject;
	}

	/**
	 * @param messageObject the messageObject to set
	 */
	public void setMessageObject(List<SalesforceMessageObjResponse> messageObject) {
		this.messageObject = messageObject;
	}	
}
