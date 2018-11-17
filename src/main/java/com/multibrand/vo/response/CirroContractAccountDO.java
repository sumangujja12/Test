package com.multibrand.vo.response;

public class CirroContractAccountDO
{

	private String StrCANumber;
	private String StrConversionDate="";
	private String strBPId ="";
	private String collectiveCA ="";
	/**
	 * @return the strCANumber
	 */
	public String getStrCANumber()
	{
		return StrCANumber;
	}
	/**
	 * @param strCANumber the strCANumber to set
	 */
	public void setStrCANumber(String strCANumber)
	{
		StrCANumber = strCANumber;
	}
	/**
	 * @return the strConversionDate
	 */
	public String getStrConversionDate()
	{
		return StrConversionDate;
	}
	/**
	 * @param strConversionDate the strConversionDate to set
	 */
	public void setStrConversionDate(String strConversionDate)
	{
		StrConversionDate = strConversionDate;
	}
	/**
	 * @return the strBPId
	 */
	public String getStrBPId()
	{
		return strBPId;
	}
	/**
	 * @param strBPId the strBPId to set
	 */
	public void setStrBPId(String strBPId)
	{
		this.strBPId = strBPId;
	}
	/**
	 * @return the collectiveCA
	 */
	public String getCollectiveCA()
	{
		return collectiveCA;
	}
	/**
	 * @param collectiveCA the collectiveCA to set
	 */
	public void setCollectiveCA(String collectiveCA)
	{
		this.collectiveCA = collectiveCA;
	}
	
	
	
}
