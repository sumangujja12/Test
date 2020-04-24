package com.multibrand.vo.response.gmd;

import java.util.List;

public class DailyHourlyPriceResponseVO 
{
	private List<HourlyPrice> hourlyPriceList;
	

	/**
	 * @return the hourlyPriceList
	 */
	public List<HourlyPrice> getHourlyPriceList() {
		return hourlyPriceList;
	}

	/**
	 * @param hourlyPriceList the hourlyPriceList to set
	 */
	public void setHourlyPriceList(List<HourlyPrice> hourlyPriceList) {
		this.hourlyPriceList = hourlyPriceList;
	}
	
}
