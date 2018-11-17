package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TemperatureDataResponse")
public class TemperaturedataResponse extends GenericResponse {
	
	private List<DayTemperatureData> dayTemperatureDataList;
	
	/**
	 * @return the dayTemperatureDataList
	 */
	public List<DayTemperatureData> getDayTemperatureDataList() {
		return dayTemperatureDataList;
	}

	/**
	 * @param dayTemperatureDataList the dayTemperatureDataList to set
	 */
	public void setDayTemperatureDataList(
			List<DayTemperatureData> dayTemperatureDataList) {
		this.dayTemperatureDataList = dayTemperatureDataList;
	}

}
