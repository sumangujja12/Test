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

}
