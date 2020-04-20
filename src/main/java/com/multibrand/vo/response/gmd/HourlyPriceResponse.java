package com.multibrand.vo.response.gmd;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement
@XmlSeeAlso({HourlyPriceResponse.class})
public class HourlyPriceResponse extends GenericResponse {

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
