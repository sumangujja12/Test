package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({ SmartMeterUsageHistory.class })
public class SmartMeterUsageResponseList extends GenericResponse
{

	private List<SmartMeterUsageHistory> smartMeterUsageHistoryList;

	/**
	 * @return the smartMeterUsageHistoryList
	 */
	public List<SmartMeterUsageHistory> getSmartMeterUsageHistoryList()
	{
		return smartMeterUsageHistoryList;
	}

	/**
	 * @param smartMeterUsageHistoryList the smartMeterUsageHistoryList to set
	 */
	public void setSmartMeterUsageHistoryList(
			List<SmartMeterUsageHistory> smartMeterUsageHistoryList)
	{
		this.smartMeterUsageHistoryList = smartMeterUsageHistoryList;
	}

	
	
	
}
