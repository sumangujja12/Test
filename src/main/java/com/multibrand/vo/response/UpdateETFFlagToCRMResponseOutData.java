package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;


@XmlRootElement(name="UpdateETFFlagToCRMResponseOutData")
public class UpdateETFFlagToCRMResponseOutData {
	
	@SerializedName("MsgType")
	private String msgType;
	
	@SerializedName("MsgNo")
    private String msgNo;
	
	@SerializedName("MsgClass")
	private String msgClass;
	
	@SerializedName("MsgText")
    private String msgText;
	
	@SerializedName("MsgDisplay")
	private String msgDisplay;
	
	
	public String getMsgType() {
		return msgType;
	}


	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}


	public String getMsgNo() {
		return msgNo;
	}

	public void setMsgNo(String msgNo) {
		this.msgNo = msgNo;
	}

	public String getMsgClass() {
		return msgClass;
	}

	public void setMsgClass(String msgClass) {
		this.msgClass = msgClass;
	}


	public String getMsgText() {
		return msgText;
	}




	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}




	public String getMsgDisplay() {
		return msgDisplay;
	}




	public void setMsgDisplay(String msgDisplay) {
		this.msgDisplay = msgDisplay;
	}




	@Override
	public String toString() {
		return "CheckReliantCustomerStatusOutData [msgType=" + msgType + ", msgType="
				+ msgType + "]";
	}
    
	
}