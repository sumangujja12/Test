package com.multibrand.vo.response.billingResponse;

import com.multibrand.domain.AmbOutputTab;
import com.multibrand.domain.ProgramStatus;
import com.multibrand.vo.response.GenericResponse;


public class AMBEligibiltyCheckResponseVO extends GenericResponse
{
	private String errMessage;
	private String errCode;
	private ProgramStatus prgStatus;
	private AmbOutputTab[] ambWebTab;
	private Double deffAmt;
	private Double ambAmt;
	private String respStatus;
	/**
	 * @return the errMessage
	 */
	public String getErrMessage()
	{
		return errMessage;
	}
	/**
	 * @param errMessage the errMessage to set
	 */
	public void setErrMessage(String errMessage)
	{
		this.errMessage = errMessage;
	}
	/**
	 * @return the errCode
	 */
	public String getErrCode()
	{
		return errCode;
	}
	/**
	 * @param errCode the errCode to set
	 */
	public void setErrCode(String errCode)
	{
		this.errCode = errCode;
	}
	/**
	 * @return the prgStatus
	 */
	public ProgramStatus getPrgStatus()
	{
		return prgStatus;
	}
	/**
	 * @param prgStatus the prgStatus to set
	 */
	public void setPrgStatus(ProgramStatus prgStatus)
	{
		this.prgStatus = prgStatus;
	}
	/**
	 * @return the ambWebTab
	 */
	public AmbOutputTab[] getAmbWebTab()
	{
		return ambWebTab;
	}
	/**
	 * @param ambWebTab the ambWebTab to set
	 */
	public void setAmbWebTab(AmbOutputTab[] ambWebTab)
	{
		this.ambWebTab = ambWebTab;
	}
	/**
	 * @return the deffAmt
	 */
	public Double getDeffAmt()
	{
		return deffAmt;
	}
	/**
	 * @param deffAmt the deffAmt to set
	 */
	public void setDeffAmt(Double deffAmt)
	{
		this.deffAmt = deffAmt;
	}
	/**
	 * @return the ambAmt
	 */
	public Double getAmbAmt()
	{
		return ambAmt;
	}
	/**
	 * @param ambAmt the ambAmt to set
	 */
	public void setAmbAmt(Double ambAmt)
	{
		this.ambAmt = ambAmt;
	}
	/**
	 * @return the respStatus
	 */
	public String getRespStatus()
	{
		return respStatus;
	}
	/**
	 * @param respStatus the respStatus to set
	 */
	public void setRespStatus(String respStatus)
	{
		this.respStatus = respStatus;
	}
	
	
}
