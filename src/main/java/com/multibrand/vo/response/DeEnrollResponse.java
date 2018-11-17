package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;



@XmlRootElement( name="DeEnrollResponse")
@Component
public class DeEnrollResponse extends GenericResponse {
	
private String successCode;

public String getSuccessCode() {
	return successCode;
}

public void setSuccessCode(String successCode) {
	this.successCode = successCode;
}




}
