package com.multibrand.vo.response.historyResponse;

import java.math.BigDecimal;

public class ConsumptionHistoryDO
{

	private String contractId ="";
	private String periodStartDate ="";
	private String periodEndDate ="";
	private BigDecimal consumption;
	/**
	 * @return the contractId
	 */
	public String getContractId()
	{
		return contractId;
	}
	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(String contractId)
	{
		this.contractId = contractId;
	}
	/**
	 * @return the periodStartDate
	 */
	public String getPeriodStartDate()
	{
		return periodStartDate;
	}
	/**
	 * @param periodStartDate the periodStartDate to set
	 */
	public void setPeriodStartDate(String periodStartDate)
	{
		this.periodStartDate = periodStartDate;
	}
	/**
	 * @return the periodEndDate
	 */
	public String getPeriodEndDate()
	{
		return periodEndDate;
	}
	/**
	 * @param periodEndDate the periodEndDate to set
	 */
	public void setPeriodEndDate(String periodEndDate)
	{
		this.periodEndDate = periodEndDate;
	}
	/**
	 * @return the consumption
	 */
	public BigDecimal getConsumption()
	{
		return consumption;
	}
	/**
	 * @param consumption the consumption to set
	 */
	public void setConsumption(BigDecimal consumption)
	{
		this.consumption = consumption;
	}
	
	
}
