package com.multibrand.vo.response;

public class GetContactInfoResponse extends GenericResponse {

	private EmailInformation emailInfo;
	private PhoneInformation phoneInfoList[];
	
	public EmailInformation getEmailInfo() {
		return emailInfo;
	}
	public void setEmailInfo(EmailInformation emailInfo) {
		this.emailInfo = emailInfo;
	}
	public PhoneInformation[] getPhoneInfoList() {
		return phoneInfoList;
	}
	public void setPhoneInfoList(PhoneInformation[] phoneInfoList) {
		this.phoneInfoList = phoneInfoList;
	}
	
	
}
