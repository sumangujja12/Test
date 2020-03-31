package com.multibrand.dto.response;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;

@XmlRootElement
public class SalesBaseResponse extends Response implements Constants  {

	private String errorCode = "";
	private String errorDescription = "";
	private String messageCode = "";
	private String messageText = "";
	private String statusCode = STATUS_CODE_CONTINUE;
	private Response.Status httpStatus = Response.Status.OK;
	
	
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
	
	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}
	
	public SalesBaseResponse populateGenericErrorResponse(Exception ex, String messageText) {
		this.errorCode=MESSAGE_CODE_TECHNICAL_ERROR;
		this.errorDescription=ex.getMessage();
		this.messageCode=MESSAGE_CODE_TECHNICAL_ERROR;
		this.messageText=messageText;
		this.statusCode=STATUS_CODE_STOP;
		this.httpStatus=Response.Status.INTERNAL_SERVER_ERROR;
		SalesBaseResponse.status(httpStatus).entity(this).build();
		return this;
	}
	
	public SalesBaseResponse populateInvalidTrackingAndGuidResponse() {
		this.errorCode=MESSAGE_CODE_NO_MATCH_FOUND;
		this.errorDescription="Invalid trackingId / guid";
		this.statusCode=STATUS_CODE_STOP;
		this.httpStatus=Response.Status.BAD_REQUEST;
		SalesBaseResponse.status(httpStatus).entity(this).build();
		return this;
		
	}
	@Override
	public Object getEntity() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public MultivaluedMap<String, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}
}
