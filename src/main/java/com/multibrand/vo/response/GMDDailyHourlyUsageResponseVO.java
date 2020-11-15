package com.multibrand.vo.response;

import java.util.List;

public class GMDDailyHourlyUsageResponseVO 
{
	private List<GMDHourlyUsage> hourlyUsageList;
	private List<GMDDailyResponseVO> dailyUsageList;
	/**
	 * @return the hourlyUsageList
	 */
	public List<GMDHourlyUsage> getHourlyUsageList()
	{
		return hourlyUsageList;
	}
	/**
	 * @param hourlyUsageList the hourlyUsageList to set
	 */
	public void setHourlyUsageList(List<GMDHourlyUsage> hourlyUsageList)
	{
		this.hourlyUsageList = hourlyUsageList;
	}
	/**
	 * @return the dailyUsageList
	 */
	public List<GMDDailyResponseVO> getDailyUsageList()
	{
		return dailyUsageList;
	}
	/**
	 * @param dailyUsageList the dailyUsageList to set
	 */
	public void setDailyUsageList(List<GMDDailyResponseVO> dailyUsageList)
	{
		this.dailyUsageList = dailyUsageList;
	}
	
	
}
