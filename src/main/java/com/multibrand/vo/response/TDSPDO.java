package com.multibrand.vo.response;

import org.apache.commons.lang.StringUtils;

public class TDSPDO
{
	private String tdspCodeWeb;
	private String tdspCodeCCS;
	private String tdspName;
	private String availableDates = StringUtils.EMPTY;
	private String tdspFee  = StringUtils.EMPTY;
  
	public String getTdspCodeWeb() {
		return tdspCodeWeb;
	}
	public void setTdspCodeWeb(String tdspCodeWeb) {
		this.tdspCodeWeb = tdspCodeWeb;
	}
	public String getTdspCodeCCS() {
		return tdspCodeCCS;
	}
	public void setTdspCodeCCS(String tdspCodeCCS) {
		this.tdspCodeCCS = tdspCodeCCS;
	}
	public String getTdspName() {
		return tdspName;
	}
	public void setTdspName(String tdspName) {
		this.tdspName = tdspName;
	}
	public String getAvailableDates() {
		return availableDates;
	}
	public void setAvailableDates(String availableDates) {
		this.availableDates = availableDates;
	}
	public String getTdspFee() {
		return tdspFee;
	}
	public void setTdspFee(String tdspFee) {
		this.tdspFee = tdspFee;
	}

  
}