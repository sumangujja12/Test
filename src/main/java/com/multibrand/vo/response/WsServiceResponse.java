package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement(name="WsServiceResponse")
@Component
public class WsServiceResponse extends GenericResponse
{
	private String successCode;

	/**
	 * @return the successCode
	 */
	public String getSuccessCode()
	{
		return successCode;
	}

	/**
	 * @param successCode the successCode to set
	 */
	public void setSuccessCode(String successCode)
	{
		this.successCode = successCode;
	}
	
	
}
