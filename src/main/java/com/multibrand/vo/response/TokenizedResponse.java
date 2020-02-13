package com.multibrand.vo.response;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TokenizedResponse")
public class TokenizedResponse extends GenericResponse {
	/* author Mayank Mishra */

	private String returnToken="";
	private Response.Status httpStatus;

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

	public Response.Status getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Response.Status httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
}
