package com.multibrand.vo.request;

public class AvgTempRequestVO
{

	private String zoneId;
	private String billStartDate;
	private String billEndDate;



	/**
	 * @return the zoneId
	 */
	public String getZoneId()
	{
		return zoneId;
	}

	/**
	 * @param zoneId the zoneId to set
	 */
	public void setZoneId(String zoneId)
	{
		this.zoneId = zoneId;
	}

	/**
	 * @return the billStartDate
	 */
	public String getBillStartDate()
	{
		return billStartDate;
	}

	/**
	 * @param billStartDate
	 *            the billStartDate to set
	 */
	public void setBillStartDate(String billStartDate)
	{
		this.billStartDate = billStartDate;
	}

	/**
	 * @return the billEndDate
	 */
	public String getBillEndDate()
	{
		return billEndDate;
	}

	/**
	 * @param billEndDate
	 *            the billEndDate to set
	 */
	public void setBillEndDate(String billEndDate)
	{
		this.billEndDate = billEndDate;
	}

}
