package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;


@XmlRootElement
@XmlSeeAlso({ WeeklyUsageResponse.class })
public class WeeklyUsageResponseList extends GenericResponse
{
	
	private List<WeeklyUsageResponse> weeklyUsageResponse;

	/**
	 * @return the weeklyUsageResponse
	 */
	public List<WeeklyUsageResponse> getWeeklyUsageResponse() {
		return weeklyUsageResponse;
	}

	/**
	 * @param weeklyUsageResponse the weeklyUsageResponse to set
	 */
	public void setWeeklyUsageResponse(List<WeeklyUsageResponse> weeklyUsageResponse) {
		this.weeklyUsageResponse = weeklyUsageResponse;
	}


}
