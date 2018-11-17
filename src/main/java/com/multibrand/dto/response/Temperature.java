package com.multibrand.dto.response;

public class Temperature {
	
	private String tempLow;
	private String tempHigh;
	private String day;
	private String yearMonthNum;
	private String actualDay;
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTempLow() {
		return tempLow;
	}
	public void setTempLow(String tempLow) {
		this.tempLow = tempLow;
	}
	public String getTempHigh() {
		return tempHigh;
	}
	public void setTempHigh(String tempHigh) {
		this.tempHigh = tempHigh;
	}
	public String getYearMonthNum() {
		return yearMonthNum;
	}
	public void setYearMonthNum(String yearMonthNum) {
		this.yearMonthNum = yearMonthNum;
	}
	public String getActualDay() {
		return actualDay;
	}
	public void setActualDay(String actualDay) {
		this.actualDay = actualDay;
	}
	
	
}
