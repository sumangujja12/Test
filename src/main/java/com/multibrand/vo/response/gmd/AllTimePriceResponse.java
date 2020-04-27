package com.multibrand.vo.response.gmd;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement
@XmlSeeAlso({AllTimePriceResponse.class})
public class AllTimePriceResponse extends GenericResponse {

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
