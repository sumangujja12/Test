package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.request.PushNotificationStatus;

@XmlRootElement
public class PushNotificationPrefReadResponse extends GenericResponse{
	
	private String message;
	private String exPushActive;
	private List<PushNotificationStatus> pushStatusTab;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getExPushActive() {
		return exPushActive;
	}
	public void setExPushActive(String exPushActive) {
		this.exPushActive = exPushActive;
	}
	public List<PushNotificationStatus> getPushStatusTab() {
		return pushStatusTab;
	}
	public void setPushStatusTab(List<PushNotificationStatus> pushStatusTab) {
		this.pushStatusTab = pushStatusTab;
	}
	
	

}
