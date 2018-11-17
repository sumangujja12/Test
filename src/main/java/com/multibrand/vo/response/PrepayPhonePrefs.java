package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PrepayPhonePrefs")
public class PrepayPhonePrefs {

	private String lowBalPhonePref;
	private String payConfPhonePref;
	private String weeklyBalSumPhonePref;
	
	public String getLowBalPhonePref() {
		return lowBalPhonePref;
	}
	public void setLowBalPhonePref(String lowBalPhonePref) {
		this.lowBalPhonePref = lowBalPhonePref;
	}
	public String getPayConfPhonePref() {
		return payConfPhonePref;
	}
	public void setPayConfPhonePref(String payConfPhonePref) {
		this.payConfPhonePref = payConfPhonePref;
	}
	public String getWeeklyBalSumPhonePref() {
		return weeklyBalSumPhonePref;
	}
	public void setWeeklyBalSumPhonePref(String weeklyBalSumPhonePref) {
		this.weeklyBalSumPhonePref = weeklyBalSumPhonePref;
	}
	
	
}
