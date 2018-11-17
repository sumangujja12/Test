package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({HourlyUsage.class})
public class HourlyUsageResponse extends GenericResponse {

	private List<HourlyUsage> hourlyUsageList;

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

	
	
	
}
