package com.multibrand.vo.response;

public class WseEsenseEligibility
{

	private String contractNumber = "";
	private String eligibilty = "";
	private String esid = "";
	private String progName = "";
	private String status = "";
	private String errorCode = "";
	private String errorMessage = "";

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
	 * @return the contractNumber
	 */
	public String getContractNumber()
	{
		return contractNumber;
	}

	/**
	 * @param contractNumber the contractNumber to set
	 */
	public void setContractNumber(String contractNumber)
	{
		this.contractNumber = contractNumber;
	}

	/**
	 * @return the eligibilty
	 */
	public String getEligibilty()
	{
		return eligibilty;
	}

	/**
	 * @param eligibilty
	 *            the eligibilty to set
	 */
	public void setEligibilty(String eligibilty)
	{
		this.eligibilty = eligibilty;
	}

	/**
	 * @return the esid
	 */
	public String getEsid()
	{
		return esid;
	}

	/**
	 * @param esid
	 *            the esid to set
	 */
	public void setEsid(String esid)
	{
		this.esid = esid;
	}

	/**
	 * @return the progName
	 */
	public String getProgName()
	{
		return progName;
	}

	/**
	 * @param progName
	 *            the progName to set
	 */
	public void setProgName(String progName)
	{
		this.progName = progName;
	}

	/**
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

}
