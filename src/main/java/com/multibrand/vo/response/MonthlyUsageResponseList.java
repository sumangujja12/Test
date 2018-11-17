package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({ MonthlyUsageResponse.class })
public class MonthlyUsageResponseList extends GenericResponse
{

	private List<MonthlyUsageResponse> MonthlyUsageResponse;

	/**
	 * @return the MonthlyUsageResponse
	 */
	public List<MonthlyUsageResponse> getMonthlyUsageResponse()
	{
		return MonthlyUsageResponse;
	}

	/**
	 * @param MonthlyUsageResponse
	 *            the MonthlyUsageResponse to set
	 */
	public void setMonthlyUsageResponse(
			List<MonthlyUsageResponse> MonthlyUsageResponse)
	{
		this.MonthlyUsageResponse = MonthlyUsageResponse;
	}

}
