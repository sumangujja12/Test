package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="SalesforceUpdateAccountResponse")
public class SalesforceUpdateAccountResponse extends GenericResponse {
	

	@SerializedName("RegistrationDate")
	private String registrationDate;
	
	@SerializedName("Message")
	private String message;

	@SerializedName("LoginDate")
	private String loginDate;
	
	@SerializedName("LeaseId")
	private String leaseId;
	
	@SerializedName("AccountPhone")
	private String accountPhone;
	
	@SerializedName("AccountEmail")
	private String accountEmail;
	
	@SerializedName("AccountAddress")
	private String accountAddress;

	/**
	 * @return the registrationDate
	 */
	public String getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
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
	 * @return the loginDate
	 */
	public String getLoginDate() {
		return loginDate;
	}

	/**
	 * @param loginDate the loginDate to set
	 */
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
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
	 * @return the accountPhone
	 */
	public String getAccountPhone() {
		return accountPhone;
	}

	/**
	 * @param accountPhone the accountPhone to set
	 */
	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}

	/**
	 * @return the accountEmail
	 */
	public String getAccountEmail() {
		return accountEmail;
	}

	/**
	 * @param accountEmail the accountEmail to set
	 */
	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	/**
	 * @return the accountAddress
	 */
	public String getAccountAddress() {
		return accountAddress;
	}

	/**
	 * @param accountAddress the accountAddress to set
	 */
	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}

	
}
