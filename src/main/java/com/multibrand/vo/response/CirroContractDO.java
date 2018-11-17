package com.multibrand.vo.response;

public class CirroContractDO
{

	private String strContractID; 
	private String strESIID; 
	private String strContractLegacyAccount;
	private String strMoveInDate="";
	private String strMoveOutDate="";
	private String StrCANumber;
	private String strBPId ="";
	private String collectiveCA ="";
	
	/**
	 * @return the strContractID
	 */
	public String getStrContractID()
	{
		return strContractID;
	}
	/**
	 * @param strContractID the strContractID to set
	 */
	public void setStrContractID(String strContractID)
	{
		this.strContractID = strContractID;
	}
	/**
	 * @return the strESIID
	 */
	public String getStrESIID()
	{
		return strESIID;
	}
	/**
	 * @param strESIID the strESIID to set
	 */
	public void setStrESIID(String strESIID)
	{
		this.strESIID = strESIID;
	}
	
	/**
	 * @return the strMoveInDate
	 */
	public String getStrMoveInDate()
	{
		return strMoveInDate;
	}
	/**
	 * @param strMoveInDate the strMoveInDate to set
	 */
	public void setStrMoveInDate(String strMoveInDate)
	{
		this.strMoveInDate = strMoveInDate;
	}
	/**
	 * @return the strMoveOutDate
	 */
	public String getStrMoveOutDate()
	{
		return strMoveOutDate;
	}
	/**
	 * @param strMoveOutDate the strMoveOutDate to set
	 */
	public void setStrMoveOutDate(String strMoveOutDate)
	{
		this.strMoveOutDate = strMoveOutDate;
	}
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
	 * @return the strContractLegacyAccount
	 */
	public String getStrContractLegacyAccount()
	{
		return strContractLegacyAccount;
	}
	/**
	 * @param strContractLegacyAccount the strContractLegacyAccount to set
	 */
	public void setStrContractLegacyAccount(String strContractLegacyAccount)
	{
		this.strContractLegacyAccount = strContractLegacyAccount;
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
