package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement(name="WsEnrollmentResponse")
@Component
public class WsEnrollmentResponse extends GenericResponse
{

	/** instance for wseRequestDate */
	private String wseRequestDate;

	/** instance for wseTerminationDate */
	private String wseTerminationDate;

	/** instance for errorCode */
	private String errorCode;

	/** instance for errorMessage */
	private String errorMessage;

	/** instance for exCode */
	private String sucessCode;

	/**
	 * @return the wseRequestDate
	 */
	public String getWseRequestDate()
	{
		return wseRequestDate;
	}

	/**
	 * @param wseRequestDate
	 *            the wseRequestDate to set
	 */
	public void setWseRequestDate(String wseRequestDate)
	{
		this.wseRequestDate = wseRequestDate;
	}

	/**
	 * @return the wseTerminationDate
	 */
	public String getWseTerminationDate()
	{
		return wseTerminationDate;
	}

	/**
	 * @param wseTerminationDate
	 *            the wseTerminationDate to set
	 */
	public void setWseTerminationDate(String wseTerminationDate)
	{
		this.wseTerminationDate = wseTerminationDate;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode()
	{
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the sucessCode
	 */
	public String getSucessCode()
	{
		return sucessCode;
	}

	/**
	 * @param sucessCode the sucessCode to set
	 */
	public void setSucessCode(String sucessCode)
	{
		this.sucessCode = sucessCode;
	}


}
