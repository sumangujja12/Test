package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TokenizedResponse")
public class TokenizedResponse extends GenericResponse {
	/* author Mayank Mishra */

	private String returnToken="";

	public TokenizedResponse() {
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
