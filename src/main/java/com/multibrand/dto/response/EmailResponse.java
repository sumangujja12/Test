package com.multibrand.dto.response;


public class EmailResponse{
	
	private String resultcode = "";
	private String resultdescription = "";
	private String errorcode = "";
	private String errordescription= "";
	private String externalid = "";
	
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public String getResultdescription() {
		return resultdescription;
	}
	public void setResultdescription(String resultdescription) {
		this.resultdescription = resultdescription;
	}
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	public String getErrordescription() {
		return errordescription;
	}
	public void setErrordescription(String errordescription) {
		this.errordescription = errordescription;
	}
	public String getExternalid() {
		return externalid;
	}
	public void setExternalid(String externalid) {
		this.externalid = externalid;
	}
}
