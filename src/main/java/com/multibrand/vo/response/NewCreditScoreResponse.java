package com.multibrand.vo.response;

import javax.ws.rs.core.Response;

/*import java.util.List;*/

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;


@SuppressWarnings("serial")
@XmlRootElement(name="NewCreditScoreResponse")
public class NewCreditScoreResponse extends GenericResponse implements java.io.Serializable {
	/* author Mayank Mishra */
	private String creditAgency;
	private String depositAmount;
	private String depositDueText;
	private String depositReasonText = StringUtils.EMPTY;
	private String[] creditFactorsText;

	private Response.Status httpStatus;
	
	private String activationFee;
	private String bondPrice;
	private String accSecStatus;
	
	private String depositOptionsText;
	
	/**
	 * @return the depositAmount
	 */
	public String getDepositAmount() {
		return depositAmount;
	}
	/**
	 * @param depositAmount the depositAmount to set
	 */
	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}
	/**
	 * @return the depositDueText
	 */
	public String getDepositDueText() {
		return depositDueText;
	}
	/**
	 * @param depositDueText the depositDueText to set
	 */
	public void setDepositDueText(String depositDueText) {
		this.depositDueText = depositDueText;
	}
	/**
	 * @return the depositReasonText
	 */
	public String getDepositReasonText() {
		return depositReasonText;
	}
	/**
	 * @param depositReasonText the depositReasonText to set
	 */
	public void setDepositReasonText(String depositReasonText) {
		this.depositReasonText = depositReasonText;
	}
	/**
	 * @return the creditFactorsText
	 */
	public String[] getCreditFactorsText() {
		return creditFactorsText;
	}
	/**
	 * @param creditFactorsText the creditFactorsText to set
	 */
	public void setCreditFactorsText(String[] creditFactorsText) {
		this.creditFactorsText = creditFactorsText;
	}
	
	/**
	 * @return the creditAgency
	 */
	public String getCreditAgency() {
		return creditAgency;
	}
	/**
	 * @param creditAgency the creditAgency to set
	 */
	public void setCreditAgency(String creditAgency) {
		this.creditAgency = creditAgency;
	}
	public Response.Status getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(Response.Status httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	public String getActivationFee() {
		return activationFee;
	}
	public void setActivationFee(String activationFee) {
		this.activationFee = activationFee;
	}
	public String getBondPrice() {
		return bondPrice;
	}
	public void setBondPrice(String bondPrice) {
		this.bondPrice = bondPrice;
	}
	public String getAccSecStatus() {
		return accSecStatus;
	}
	public void setAccSecStatus(String accSecStatus) {
		this.accSecStatus = accSecStatus;
	}
	public String getDepositOptionsText() {
		return depositOptionsText;
	}
	public void setDepositOptionsText(String depositOptionsText) {
		this.depositOptionsText = depositOptionsText;
	}
	
	
	
}