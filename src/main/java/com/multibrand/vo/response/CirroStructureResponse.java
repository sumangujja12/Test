package com.multibrand.vo.response;

import java.util.List;

public class CirroStructureResponse extends GenericResponse
{
    private String errorCode ="";
	private String errorMessage ="";
	private String strFirstName ="";
	private String StrLastName ="";
	private String strOrganization ="";
	private List<CirroContractDO> contractDo;
	private List<CirroContractAccountDO> contractAccountDo;
	private String strSuperBPFirstName ="";
	private String StrSuperBPLastName ="";
	private String strSuperBPOrganization ="";
	/**
	 * @return the errorCode
	 */
	public String getErrorCode()
	{
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
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
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the strFirstName
	 */
	public String getStrFirstName()
	{
		return strFirstName;
	}
	/**
	 * @param strFirstName the strFirstName to set
	 */
	public void setStrFirstName(String strFirstName)
	{
		this.strFirstName = strFirstName;
	}
	/**
	 * @return the strLastName
	 */
	public String getStrLastName()
	{
		return StrLastName;
	}
	/**
	 * @param strLastName the strLastName to set
	 */
	public void setStrLastName(String strLastName)
	{
		StrLastName = strLastName;
	}
	/**
	 * @return the strOrganization
	 */
	public String getStrOrganization()
	{
		return strOrganization;
	}
	/**
	 * @param strOrganization the strOrganization to set
	 */
	public void setStrOrganization(String strOrganization)
	{
		this.strOrganization = strOrganization;
	}
	/**
	 * @return the contractDo
	 */
	public List<CirroContractDO> getContractDo()
	{
		return contractDo;
	}
	/**
	 * @param contractDo the contractDo to set
	 */
	public void setContractDo(List<CirroContractDO> contractDo)
	{
		this.contractDo = contractDo;
	}
	/**
	 * @return the contractAccountDo
	 */
	public List<CirroContractAccountDO> getContractAccountDo()
	{
		return contractAccountDo;
	}
	/**
	 * @param contractAccountDo the contractAccountDo to set
	 */
	public void setContractAccountDo(List<CirroContractAccountDO> contractAccountDo)
	{
		this.contractAccountDo = contractAccountDo;
	}
	/**
	 * @return the strSuperBPFirstName
	 */
	public String getStrSuperBPFirstName()
	{
		return strSuperBPFirstName;
	}
	/**
	 * @param strSuperBPFirstName the strSuperBPFirstName to set
	 */
	public void setStrSuperBPFirstName(String strSuperBPFirstName)
	{
		this.strSuperBPFirstName = strSuperBPFirstName;
	}
	/**
	 * @return the strSuperBPLastName
	 */
	public String getStrSuperBPLastName()
	{
		return StrSuperBPLastName;
	}
	/**
	 * @param strSuperBPLastName the strSuperBPLastName to set
	 */
	public void setStrSuperBPLastName(String strSuperBPLastName)
	{
		StrSuperBPLastName = strSuperBPLastName;
	}
	/**
	 * @return the strSuperBPOrganization
	 */
	public String getStrSuperBPOrganization()
	{
		return strSuperBPOrganization;
	}
	/**
	 * @param strSuperBPOrganization the strSuperBPOrganization to set
	 */
	public void setStrSuperBPOrganization(String strSuperBPOrganization)
	{
		this.strSuperBPOrganization = strSuperBPOrganization;
	}
		
		
		
}
