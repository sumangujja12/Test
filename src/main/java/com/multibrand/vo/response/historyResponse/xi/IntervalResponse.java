package com.multibrand.vo.response.historyResponse.xi;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Interval_response", namespace="http://reliant.com/xi/GreenMountain")
@XmlRootElement(name="Interval_response", namespace="http://reliant.com/xi/GreenMountain")
public class IntervalResponse {
	
	@XmlElement(name = "row")
    protected List<RowIntervalResponse> row;
	
	@XmlElement(name= "return_code")
	protected String returnCode;
	
	

	public List<RowIntervalResponse> getRow() {
		return row;
	}

	public void setRow(List<RowIntervalResponse> row) {
		this.row = row;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
}
