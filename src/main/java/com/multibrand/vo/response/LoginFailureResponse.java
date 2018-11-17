package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;



@XmlRootElement( name="LoginFailureResponse")
@Component
public class LoginFailureResponse extends GenericResponse {

	private String invalidLoginCount;

	/**
	 * @return the invalidLoginCount
	 */
	public String getInvalidLoginCount() {
		return invalidLoginCount;
	}

	/**
	 * @param invalidLoginCount the invalidLoginCount to set
	 */
	public void setInvalidLoginCount(String invalidLoginCount) {
		this.invalidLoginCount = invalidLoginCount;
	}
	
	
}
