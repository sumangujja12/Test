package com.multibrand.vo.response;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement(name = "BalanceAndUsageResponse")
public class SSBalanceAndUsageResponse extends GenericResponse {

	private Short remainingDays;
	private BigDecimal prepayBalance;
	private BigDecimal deferredBalance;
	private BigDecimal avgDailyAmount;

	private DailyEstimatedData dailyEstimatedData[];
	private AccountBalUI accBalUI[];

	public Short getRemainingDays() {
		return remainingDays;
	}

	public void setRemainingDays(Short remainingDays) {
		this.remainingDays = remainingDays;
	}

	public BigDecimal getPrepayBalance() {
		return prepayBalance;
	}

	public void setPrepayBalance(BigDecimal prepayBalance) {
		this.prepayBalance = prepayBalance;
	}

	public BigDecimal getDeferredBalance() {
		return deferredBalance;
	}

	public void setDeferredBalance(BigDecimal deferredBalance) {
		this.deferredBalance = deferredBalance;
	}

	public BigDecimal getAvgDailyAmount() {
		return avgDailyAmount;
	}

	public void setAvgDailyAmount(BigDecimal avgDailyAmount) {
		this.avgDailyAmount = avgDailyAmount;
	}

	public DailyEstimatedData[] getDailyEstimatedData() {
		return dailyEstimatedData;
	}

	public void setDailyEstimatedData(DailyEstimatedData[] dailyEstimatedData) {
		this.dailyEstimatedData = dailyEstimatedData;
	}
	
	@javax.xml.bind.annotation.XmlElement(name="accountActivityData")
	public AccountBalUI[] getAccBalUI() {
		return accBalUI;
	}
	public void setAccBalUI(AccountBalUI[] accBalUI) {
		this.accBalUI = accBalUI;
	}

}
