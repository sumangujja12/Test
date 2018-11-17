package com.multibrand.vo.response.historyResponse;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.historyResponse.xi.IntervalResponse;

@XmlRootElement(name="IntervalDataResponse")
public class IntervalDataResponse extends GenericResponse {
	
	@XmlElementRef(name="IntervalData")
	protected IntervalResponse intervalResponse;

	public IntervalResponse getIntervalResponse() {
		return intervalResponse;
	}

	public void setIntervalResponse(IntervalResponse intervalResponse) {
		this.intervalResponse = intervalResponse;
	}
	

}
