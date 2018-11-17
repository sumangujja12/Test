package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="UsageAlertPrefs")
public class UsageAlertPrefs {

	private String myDailyUsageExceeds;
	private String myDailyUsageExceedsPref;
	private String myDayUsageSpikesPref;

	public String getMyDailyUsageExceeds() {
		return myDailyUsageExceeds;
	}

	public void setMyDailyUsageExceeds(String myDailyUsageExceeds) {
		this.myDailyUsageExceeds = myDailyUsageExceeds;
	}

	public String getMyDailyUsageExceedsPref() {
		return myDailyUsageExceedsPref;
	}

	public void setMyDailyUsageExceedsPref(String myDailyUsageExceedsPref) {
		this.myDailyUsageExceedsPref = myDailyUsageExceedsPref;
	}

	public String getMyDayUsageSpikesPref() {
		return myDayUsageSpikesPref;
	}

	public void setMyDayUsageSpikesPref(String myDayUsageSpikesPref) {
		this.myDayUsageSpikesPref = myDayUsageSpikesPref;
	}

}
