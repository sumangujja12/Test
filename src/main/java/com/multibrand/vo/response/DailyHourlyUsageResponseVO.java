package com.multibrand.vo.response;

import java.util.List;

public class DailyHourlyUsageResponseVO 
{
	private List<HourlyUsage> hourlyUsageList;
	private List<DailyResponseVO> dailyUsageList;
	/**
	 * @return the hourlyUsageList
	 */
	public List<HourlyUsage> getHourlyUsageList()
	{
		return hourlyUsageList;
	}
	/**
	 * @param hourlyUsageList the hourlyUsageList to set
	 */
	public void setHourlyUsageList(List<HourlyUsage> hourlyUsageList)
	{
		this.hourlyUsageList = hourlyUsageList;
	}
	/**
	 * @return the dailyUsageList
	 */
	public List<DailyResponseVO> getDailyUsageList()
	{
		return dailyUsageList;
	}
	/**
	 * @param dailyUsageList the dailyUsageList to set
	 */
	public void setDailyUsageList(List<DailyResponseVO> dailyUsageList)
	{
		this.dailyUsageList = dailyUsageList;
	}
	
	
}
