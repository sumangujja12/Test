package com.multibrand.dto;

import java.io.Serializable;

import com.multibrand.util.CommonUtil;

public class ContactPreferencesDTO implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -8238845085661258045L;
	private String lowActBalEmail;
	private String lowActBalSMS;
	private String lowActBalPhone;
	private String payReceivedEmail;
	private String payReceivedSMS;
	private String payReceivedPhone;
	private String weeklyBalEmail;
	private String weeklyBalSMS;
	private String weeklyBalPhone;
	private String alertMessage;
	
	public String getWeeklyBalPhone() {
		return weeklyBalPhone;
	}

	public void setWeeklyBalPhone(String weeklyBalPhone) {
		this.weeklyBalPhone = weeklyBalPhone;
	}


	
	public String getLowActBalEmail() {
		return lowActBalEmail;
	}

	public void setLowActBalEmail(String lowActBalEmail) {
		this.lowActBalEmail = lowActBalEmail;
	}

	public String getLowActBalSMS() {
		return lowActBalSMS;
	}

	public void setLowActBalSMS(String lowActBalSMS) {
		this.lowActBalSMS = lowActBalSMS;
	}

	public String getLowActBalPhone() {
		return lowActBalPhone;
	}

	public void setLowActBalPhone(String lowActBalPhone) {
		this.lowActBalPhone = lowActBalPhone;
	}

	public String getPayReceivedEmail() {
		return payReceivedEmail;
	}

	public void setPayReceivedEmail(String payReceivedEmail) {
		this.payReceivedEmail = payReceivedEmail;
	}

	public String getPayReceivedSMS() {
		return payReceivedSMS;
	}

	public void setPayReceivedSMS(String payReceivedSMS) {
		this.payReceivedSMS = payReceivedSMS;
	}

	public String getPayReceivedPhone() {
		return payReceivedPhone;
	}

	public void setPayReceivedPhone(String payReceivedPhone) {
		this.payReceivedPhone = payReceivedPhone;
	}

	public String getWeeklyBalEmail() {
		return weeklyBalEmail;
	}

	public void setWeeklyBalEmail(String weeklyBalEmail) {
		this.weeklyBalEmail = weeklyBalEmail;
	}

	public String getWeeklyBalSMS() {
		return weeklyBalSMS;
	}

	public void setWeeklyBalSMS(String weeklyBalSMS) {
		this.weeklyBalSMS = weeklyBalSMS;
	}

	public String getAlertMessage() {
		return alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}
	
	public void resetPreference() {
		lowActBalEmail = null ;
		lowActBalSMS = null ;
		lowActBalPhone = null ;
		payReceivedEmail = null ;
		payReceivedSMS = null ;
		payReceivedPhone = null ;
		weeklyBalEmail = null ;
		weeklyBalSMS = null ;
		weeklyBalPhone = null ;
	}
	
}
