package com.multibrand.vo.request.historyRequest.xi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MT_get_IntervalData_Request", namespace="http://reliant.com/xi/GreenMountain", propOrder = {
	    "esid",
	    "startDate",
	    "endDate"
	})
@XmlRootElement(name="MT_get_IntervalData_Request", namespace="http://reliant.com/xi/GreenMountain")
public class IntervalDataRequest {

	@XmlElement(name = "ESIID")
	protected String esid;
	
	@XmlElement(name = "Start_Date")
    protected String startDate;
	
	@XmlElement(name = "End_Date")
	protected String endDate;

	public String getEsid() {
		return esid;
	}

	public void setEsid(String esid) {
		this.esid = esid;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
