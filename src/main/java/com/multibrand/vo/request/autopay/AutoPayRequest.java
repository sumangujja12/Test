package com.multibrand.vo.request.autopay;

import java.io.Serializable;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.multibrand.dto.request.FormEntityRequest;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AutoPayRequest implements FormEntityRequest,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@FormParam("authType")
	private String authType = "";
	
	@FormParam("accountNumber")
	private String accountNumber = "";
	
	@FormParam("bankAccountNumber")
	private String bankAccountNumber = "";
	
	@FormParam("bankRoutingNumber")
	private String bankRoutingNumber = "";
	
	@FormParam("companyCode")
	private String companyCode = "";
	
	@FormParam("accountName")
	private String accountName = "";
	
	@FormParam("accountChkDigit")
	private String accountChkDigit = "";
	
	@FormParam("languageCode")
	private String languageCode = "";
	
	@FormParam("email")
	private String email = "";
	
	@FormParam("emailTypeId")
	private String emailTypeId = "";
	
	@FormParam("brandName")
	private String brandName = "";
	
	@FormParam("bpNumber")
	private String bpNumber = "";
	
	@FormParam("source")
	private String source = "";
	
	@FormParam("caName")
	private String caName = "";
	
	@FormParam("bpid")
	private String bpid = "";
	
	@FormParam("ccNumber")
	private String ccNumber = "";
	
	@FormParam("expirationDate")
	private String expirationDate = "";
	
	@FormParam("billingZip")
	private String billingZip = "";
	
	@FormParam("cvvNumber")
	private String cvvNumber = "";
	


	
	/**
	 * @return the authType
	 */
	public String getAuthType() {
		return authType;
	}
	/**
	 * @param authType the authType to set
	 */
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	/**
	 * @return the bankAccountNumber
	 */
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	/**
	 * @param bankAccountNumber the bankAccountNumber to set
	 */
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	/**
	 * @return the bankRoutingNumber
	 */
	public String getBankRoutingNumber() {
		return bankRoutingNumber;
	}
	/**
	 * @param bankRoutingNumber the bankRoutingNumber to set
	 */
	public void setBankRoutingNumber(String bankRoutingNumber) {
		this.bankRoutingNumber = bankRoutingNumber;
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
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * @return the accountChkDigit
	 */
	public String getAccountChkDigit() {
		return accountChkDigit;
	}
	/**
	 * @param accountChkDigit the accountChkDigit to set
	 */
	public void setAccountChkDigit(String accountChkDigit) {
		this.accountChkDigit = accountChkDigit;
	}
	/**
	 * @return the languageCode
	 */
	public String getLanguageCode() {
		return languageCode;
	}
	/**
	 * @param languageCode the languageCode to set
	 */
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the emailTypeId
	 */
	public String getEmailTypeId() {
		return emailTypeId;
	}
	/**
	 * @param emailTypeId the emailTypeId to set
	 */
	public void setEmailTypeId(String emailTypeId) {
		this.emailTypeId = emailTypeId;
	}
	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}
	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * @return the bpNumber
	 */
	public String getBpNumber() {
		return bpNumber;
	}
	/**
	 * @param bpNumber the bpNumber to set
	 */
	public void setBpNumber(String bpNumber) {
		this.bpNumber = bpNumber;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the caName
	 */
	public String getCaName() {
		return caName;
	}
	/**
	 * @param caName the caName to set
	 */
	public void setCaName(String caName) {
		this.caName = caName;
	}
	/**
	 * @return the bpid
	 */
	public String getBpid() {
		return bpid;
	}
	/**
	 * @param bpid the bpid to set
	 */
	public void setBpid(String bpid) {
		this.bpid = bpid;
	}
	/**
	 * @return the ccNumber
	 */
	public String getCcNumber() {
		return ccNumber;
	}
	/**
	 * @param ccNumber the ccNumber to set
	 */
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	/**
	 * @return the expirationDate
	 */
	public String getExpirationDate() {
		return expirationDate;
	}
	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	/**
	 * @return the billingZip
	 */
	public String getBillingZip() {
		return billingZip;
	}
	/**
	 * @param billingZip the billingZip to set
	 */
	public void setBillingZip(String billingZip) {
		this.billingZip = billingZip;
	}
	/**
	 * @return the cvvNumber
	 */
	public String getCvvNumber() {
		return cvvNumber;
	}
	/**
	 * @param cvvNumber the cvvNumber to set
	 */
	public void setCvvNumber(String cvvNumber) {
		this.cvvNumber = cvvNumber;
	}	
	
	/**
	 * @return String representation of this request.
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}	
}

