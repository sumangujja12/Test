package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.dto.response.SalesBaseResponse;

@XmlRootElement(name="SalesTokenResponse")
public class SalesTokenResponse extends SalesBaseResponse {

	private String returnToken="";

	public SalesTokenResponse() {
		super();
	}

	/**
	 * @return the returnToken
	 */
	public String getReturnToken() {
		return returnToken;
	}

	/**
	 * @param returnToken the returnToken to set
	 */
	public void setReturnToken(String returnToken) {
		this.returnToken = returnToken;
	}
}
