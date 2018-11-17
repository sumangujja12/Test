package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement(name="AvgTempResponse")
@Component
public class AvgTempResponse extends GenericResponse
{
	private String avgTemp = "";

	/**
	 * @return the avgTemp
	 */
	public String getAvgTemp()
	{
		return avgTemp;
	}

	/**
	 * @param avgTemp the avgTemp to set
	 */
	public void setAvgTemp(String avgTemp)
	{
		this.avgTemp = avgTemp;
	}
	
	
}
