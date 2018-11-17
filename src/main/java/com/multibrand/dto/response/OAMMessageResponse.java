package com.multibrand.dto.response;

import java.util.List;

import com.multibrand.dto.MessageDTO;

public class OAMMessageResponse {
	
	private boolean dataAvailable = false;
	private List<MessageDTO> messageList;
	private String errorCode, errorMessage;

	public boolean isDataAvailable() {
		return dataAvailable;
	}

	public void setDataAvailable(boolean dataAvailable) {
		this.dataAvailable = dataAvailable;
	}

	public List<MessageDTO> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<MessageDTO> messageList) {
		this.messageList = messageList;
		if(this.messageList.size() >0){
			this.dataAvailable = true;
		}
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
