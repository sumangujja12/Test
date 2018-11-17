package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;
import com.multibrand.util.Constants;

@XmlRootElement(name="MESSAGEOBJECT")
public class SalesforceMessageObjResponse implements Constants {
	
	 
	@SerializedName("MessageType")
	private String messageType;
	
	@SerializedName("MessageDesription")
	private String messageDesription;
	
	@SerializedName("MessageCode")
	private String messageCode;

	/**
	 * @return the messageType
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return the messageDesription
	 */
	public String getMessageDesription() {
		return messageDesription;
	}

	/**
	 * @param messageDesription the messageDesription to set
	 */
	public void setMessageDesription(String messageDesription) {
		this.messageDesription = messageDesription;
	}

	/**
	 * @return the messageCode
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * @param messageCode the messageCode to set
	 */
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	
		
}
