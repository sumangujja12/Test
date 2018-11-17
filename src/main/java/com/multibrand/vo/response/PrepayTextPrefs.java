package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PrepayTextPrefs")
public class PrepayTextPrefs {

	private String lowBalTextPref;
	private String paymentConfTextPref;
	private String weeklyBalSummTextPref;
	private String dailyBalSumTextPref;
	
	public String getDailyBalSumTextPref() {
		return dailyBalSumTextPref;
	}

	public void setDailyBalSumTextPref(String dailyBalSumTextPref) {
		this.dailyBalSumTextPref = dailyBalSumTextPref;
	}

	public String getLowBalTextPref() {
		return lowBalTextPref;
	}

	public void setLowBalTextPref(String lowBalTextPref) {
		this.lowBalTextPref = lowBalTextPref;
	}

	public String getPaymentConfTextPref() {
		return paymentConfTextPref;
	}

	public void setPaymentConfTextPref(String paymentConfTextPref) {
		this.paymentConfTextPref = paymentConfTextPref;
	}

	public String getWeeklyBalSummTextPref() {
		return weeklyBalSummTextPref;
	}

	public void setWeeklyBalSummTextPref(String weeklyBalSummTextPref) {
		this.weeklyBalSummTextPref = weeklyBalSummTextPref;
	}

}
