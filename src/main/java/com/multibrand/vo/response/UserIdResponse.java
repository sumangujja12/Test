package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserIdResponse {

	private String userid = "";
	private String resultcode ="";
	private String resultdescription="";
	private String errorcode="";
	private String errordescription="";

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

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

	
}
