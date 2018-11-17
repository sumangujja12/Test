package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({DailyResponseVO.class})
public class DailyUsageResponse extends GenericResponse {

	private List<DailyResponseVO> dailyUsageList;

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
