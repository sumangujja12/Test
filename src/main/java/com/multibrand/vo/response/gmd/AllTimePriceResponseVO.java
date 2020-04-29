package com.multibrand.vo.response.gmd;

import java.util.List;

public class AllTimePriceResponseVO 
{
	private List<AllTimePrice> allTimePriceList;

	/**
	 * @return the allTimePriceList
	 */
	public List<AllTimePrice> getAllTimePriceList() {
		return allTimePriceList;
	}

	/**
	 * @param allTimePriceList the allTimePriceList to set
	 */
	public void setAllTimePriceList(List<AllTimePrice> allTimePriceList) {
		this.allTimePriceList = allTimePriceList;
	}
		
}
