package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DailyResponseVO")
public class DailyResponseVO implements UsageInterface
{

	private String esiId;
	private String contractId;
	private String contractAcctId;
	private String busPartner;
	/*private String zoneId;
	private String curDtInd;
	private String curDayInd;
	private String dyHrInd;*/
	private String actualDay;
	private String dayUsg;
	private String dayCst;
	private String dayTempHigh="0";
	private String dayTempLow="0";

	/**
	 * @return the esiId
	 */
	public String getEsiId()
	{
		return esiId;
	}

	/**
	 * @param esiId
	 *            the esiId to set
	 */
	public void setEsiId(String esiId)
	{
		this.esiId = esiId;
	}

	/**
	 * @return the contractId
	 */
	public String getContractId()
	{
		return contractId;
	}

	/**
	 * @param contractId
	 *            the contractId to set
	 */
	public void setContractId(String contractId)
	{
		this.contractId = contractId;
	}

	/**
	 * @return the contractAcctId
	 */
	public String getContractAcctId()
	{
		return contractAcctId;
	}

	/**
	 * @param contractAcctId
	 *            the contractAcctId to set
	 */
	public void setContractAcctId(String contractAcctId)
	{
		this.contractAcctId = contractAcctId;
	}

	/**
	 * @return the busPartner
	 */
	public String getBusPartner()
	{
		return busPartner;
	}

	/**
	 * @param busPartner
	 *            the busPartner to set
	 */
	public void setBusPartner(String busPartner)
	{
		this.busPartner = busPartner;
	}
	
	/**
	 * @return the actualDay
	 */
	public String getActualDay()
	{
		return actualDay;
	}

	/**
	 * @param actualDay
	 *            the actualDay to set
	 */
	public void setActualDay(String actualDay)
	{
		this.actualDay = actualDay;
	}

	/**
	 * @return the dayUsg
	 */
	public String getDayUsg()
	{
		return dayUsg;
	}

	/**
	 * @param dayUsg
	 *            the dayUsg to set
	 */
	public void setDayUsg(String dayUsg)
	{
		this.dayUsg = dayUsg;
	}

	/**
	 * @return the dayCst
	 */
	public String getDayCst()
	{
		return dayCst;
	}

	/**
	 * @param dayCst
	 *            the dayCst to set
	 */
	public void setDayCst(String dayCst)
	{
		this.dayCst = dayCst;
	}

	/**
	 * @return the dayTempHigh
	 */
	public String getDayTempHigh()
	{
		return dayTempHigh;
	}

	/**
	 * @param dayTempHigh
	 *            the dayTempHigh to set
	 */
	public void setDayTempHigh(String dayTempHigh)
	{
		this.dayTempHigh = dayTempHigh;
	}

	/**
	 * @return the dayTempLow
	 */
	public String getDayTempLow()
	{
		return dayTempLow;
	}

	/**
	 * @param dayTempLow
	 *            the dayTempLow to set
	 */
	public void setDayTempLow(String dayTempLow)
	{
		this.dayTempLow = dayTempLow;
	}

}
