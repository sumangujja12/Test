package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PrepayEmailPrefs")
public class PrepayEmailPrefs {

	private String lowBalEmailPref;
	private String payConfEmailPref;
	private String weeklyBalSumEmailPref;
	private String dailyBalSumEmailPref;
	
	public String getDailyBalSumEmailPref() {
		return dailyBalSumEmailPref;
	}

	public void setDailyBalSumEmailPref(String dailyBalSumEmailPref) {
		this.dailyBalSumEmailPref = dailyBalSumEmailPref;
	}

	public String getLowBalEmailPref() {
		return lowBalEmailPref;
	}

	public void setLowBalEmailPref(String lowBalEmailPref) {
		this.lowBalEmailPref = lowBalEmailPref;
	}

	public String getPayConfEmailPref() {
		return payConfEmailPref;
	}

	public void setPayConfEmailPref(String payConfEmailPref) {
		this.payConfEmailPref = payConfEmailPref;
	}

	public String getWeeklyBalSumEmailPref() {
		return weeklyBalSumEmailPref;
	}

	public void setWeeklyBalSumEmailPref(String weeklyBalSumEmailPref) {
		this.weeklyBalSumEmailPref = weeklyBalSumEmailPref;
	}

}
