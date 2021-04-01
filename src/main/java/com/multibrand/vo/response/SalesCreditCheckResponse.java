package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

import com.multibrand.dto.response.SalesBaseResponse;

@XmlRootElement(name="SalesCreditCheckResponse")
public class SalesCreditCheckResponse extends SalesBaseResponse {
	
	private String creditAgency;
	private String depositAmount;
	private String depositDueText;
	private String depositReasonText = StringUtils.EMPTY;
	private String[] creditFactorsText;
	
	private String depositOptionsText;
	private String activationFee;
	private String bondPrice;
	private String accSecStatus;
	
	public String getCreditAgency() {
		return creditAgency;
	}
	public void setCreditAgency(String creditAgency) {
		this.creditAgency = creditAgency;
	}
	public String getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}
	public String getDepositDueText() {
		return depositDueText;
	}
	public void setDepositDueText(String depositDueText) {
		this.depositDueText = depositDueText;
	}
	public String getDepositReasonText() {
		return depositReasonText;
	}
	public void setDepositReasonText(String depositReasonText) {
		this.depositReasonText = depositReasonText;
	}
	public String[] getCreditFactorsText() {
		return creditFactorsText;
	}
	public void setCreditFactorsText(String[] creditFactorsText) {
		this.creditFactorsText = creditFactorsText;
	}
	public String getDepositOptionsText() {
		return depositOptionsText;
	}
	public void setDepositOptionsText(String depositOptionsText) {
		this.depositOptionsText = depositOptionsText;
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

}
