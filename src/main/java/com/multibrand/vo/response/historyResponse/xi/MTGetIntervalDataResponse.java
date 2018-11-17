package com.multibrand.vo.response.historyResponse.xi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author ahanda1
 * Main Response POJO for XI daily interval data call. To be used with JAXB for marshalling and unmarshalling
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_IntervalData_Response", namespace="http://reliant.com/xi/GreenMountain")
@XmlRootElement(name="MT_get_IntervalData_Response", namespace="http://reliant.com/xi/GreenMountain")
public class MTGetIntervalDataResponse {

	@XmlElement(name = "Interval_response")
    protected IntervalResponse intervalResponse;

	public IntervalResponse getIntervalResponse() {
		return intervalResponse;
	}

	public void setIntervalResponse(
			IntervalResponse intervalResponse) {
		this.intervalResponse = intervalResponse;
	}
	
}
