package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@XmlRootElement(name="ValidateAccount")
//@Component
//@Scope("prototype")
public class ValidateAccountResponse extends GenericResponse {

	private String firstName="";
	private String lastName="";
	private String checkDigit="";

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCheckDigit() {
		return checkDigit;
	}

	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}
	
	
}

