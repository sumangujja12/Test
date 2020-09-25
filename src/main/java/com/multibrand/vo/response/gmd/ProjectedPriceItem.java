package com.multibrand.vo.response.gmd;

import java.math.BigDecimal;

import javax.xml.datatype.XMLGregorianCalendar;

public class ProjectedPriceItem {
	
	private String zone;
	private BigDecimal profValue;
	private String profDate;
	private XMLGregorianCalendar profTime;
	
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	
	public String getProfDate() {
		return profDate;
	}
	public void setProfDate(String profDate) {
		this.profDate = profDate;
	}
	public BigDecimal getProfValue() {
		return profValue;
	}
	public void setProfValue(BigDecimal profValue) {
		this.profValue = profValue;
	}
	public XMLGregorianCalendar getProfTime() {
		return profTime;
	}
	public void setProfTime(XMLGregorianCalendar profTime) {
		this.profTime = profTime;
	}
	

}
