package com.multibrand.dto.response;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.util.Constants;

@XmlRootElement
public class SalesBaseResponse implements Constants  {

	private String errorCode = "";
	private String errorDescription = "";
	private String messageCode = "";
	private String messageText = "";
	private String statusCode = STATUS_CODE_CONTINUE;
	private Response.Status httpStatus;
	
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Response.Status getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(Response.Status httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
}
