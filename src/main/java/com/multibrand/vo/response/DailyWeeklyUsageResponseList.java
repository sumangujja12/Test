package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({ DailyWeeklyUsageResponseList.class })
public class DailyWeeklyUsageResponseList extends GenericResponse
{

	private List<DailyWeeklyUsageResponse> DailyWeeklyUsageResponse;

	/**
	 * @return the DailyWeeklyUsageResponse
	 */
	public List<DailyWeeklyUsageResponse> getDailyWeeklyUsageResponse()
	{
		return DailyWeeklyUsageResponse;
	}

	/**
	 * @param DailyWeeklyUsageResponse
	 *            the DailyWeeklyUsageResponse to set
	 */
	public void setDailyWeeklyUsageResponse(
			List<DailyWeeklyUsageResponse> DailyWeeklyUsageResponse)
	{
		this.DailyWeeklyUsageResponse = DailyWeeklyUsageResponse;
	}

}