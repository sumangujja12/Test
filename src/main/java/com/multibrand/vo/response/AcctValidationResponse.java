package com.multibrand.vo.response;

import java.util.List;

public class AcctValidationResponse extends GenericResponse
{
	
	
	private String caNumber="";
	private String companyCode="";
	private String brandName="";
	private String nameFirst="";
	private String nameLast="";
	private String oraganizationName="";
	private String bpId ="";
	private String dateOfBirth ="";
	private String idNumber ="";
	private String idNumber2 ="";
	List<ContractInfoDO> contractInfo;
	private String superBPNameFirst ="";
	private String superBPNameLast ="";
	private String superBPOraganizationName ="";
	private String superBPId ="";
	private String superBPdateOfBirth ="";
	private String superBPIdNumber ="";
	private String superBPIdNumber2 ="";
	

	/**
	 * @return the contractInfo
	 */
	public List<ContractInfoDO> getContractInfo()
	{
		return contractInfo;
	}
	/**
	 * @param contractInfo the contractInfo to set
	 */
	public void setContractInfo(List<ContractInfoDO> contractInfo)
	{
		this.contractInfo = contractInfo;
	}
	
	/**
	 * @return the caNumber
	 */
	public String getCaNumber()
	{
		return caNumber;
	}
	/**
	 * @param caNumber the caNumber to set
	 */
	public void setCaNumber(String caNumber)
	{
		this.caNumber = caNumber;
	}
	/**
	 * @return the companyCode
	 */
	public String getCompanyCode()
	{
		return companyCode;
	}
	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode)
	{
		this.companyCode = companyCode;
	}
	/**
	 * @return the brandName
	 */
	public String getBrandName()
	{
		return brandName;
	}
	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName)
	{
		this.brandName = brandName;
	}
	/**
	 * @return the nameFirst
	 */
	public String getNameFirst()
	{
		return nameFirst;
	}
	/**
	 * @param nameFirst the nameFirst to set
	 */
	public void setNameFirst(String nameFirst)
	{
		this.nameFirst = nameFirst;
	}
	/**
	 * @return the nameLast
	 */
	public String getNameLast()
	{
		return nameLast;
	}
	/**
	 * @param nameLast the nameLast to set
	 */
	public void setNameLast(String nameLast)
	{
		this.nameLast = nameLast;
	}
	/**
	 * @return the oraganizationName
	 */
	public String getOraganizationName()
	{
		return oraganizationName;
	}
	/**
	 * @param oraganizationName the oraganizationName to set
	 */
	public void setOraganizationName(String oraganizationName)
	{
		this.oraganizationName = oraganizationName;
	}
	/**
	 * @return the bpId
	 */
	public String getBpId()
	{
		return bpId;
	}
	/**
	 * @param bpId the bpId to set
	 */
	public void setBpId(String bpId)
	{
		this.bpId = bpId;
	}
	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth()
	{
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the idNumber
	 */
	public String getIdNumber()
	{
		return idNumber;
	}
	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber)
	{
		this.idNumber = idNumber;
	}
	/**
	 * @return the superBPNameFirst
	 */
	public String getSuperBPNameFirst()
	{
		return superBPNameFirst;
	}
	/**
	 * @param superBPNameFirst the superBPNameFirst to set
	 */
	public void setSuperBPNameFirst(String superBPNameFirst)
	{
		this.superBPNameFirst = superBPNameFirst;
	}
	/**
	 * @return the superBPNameLast
	 */
	public String getSuperBPNameLast()
	{
		return superBPNameLast;
	}
	/**
	 * @param superBPNameLast the superBPNameLast to set
	 */
	public void setSuperBPNameLast(String superBPNameLast)
	{
		this.superBPNameLast = superBPNameLast;
	}
	/**
	 * @return the superBPOraganizationName
	 */
	public String getSuperBPOraganizationName()
	{
		return superBPOraganizationName;
	}
	/**
	 * @param superBPOraganizationName the superBPOraganizationName to set
	 */
	public void setSuperBPOraganizationName(String superBPOraganizationName)
	{
		this.superBPOraganizationName = superBPOraganizationName;
	}
	/**
	 * @return the superBPId
	 */
	public String getSuperBPId()
	{
		return superBPId;
	}
	/**
	 * @param superBPId the superBPId to set
	 */
	public void setSuperBPId(String superBPId)
	{
		this.superBPId = superBPId;
	}
	/**
	 * @return the superBPdateOfBirth
	 */
	public String getSuperBPdateOfBirth()
	{
		return superBPdateOfBirth;
	}
	/**
	 * @param superBPdateOfBirth the superBPdateOfBirth to set
	 */
	public void setSuperBPdateOfBirth(String superBPdateOfBirth)
	{
		this.superBPdateOfBirth = superBPdateOfBirth;
	}
	/**
	 * @return the superBPIdNumber
	 */
	public String getSuperBPIdNumber()
	{
		return superBPIdNumber;
	}
	/**
	 * @param superBPIdNumber the superBPIdNumber to set
	 */
	public void setSuperBPIdNumber(String superBPIdNumber)
	{
		this.superBPIdNumber = superBPIdNumber;
	}
	
	/**
	 * @return the idNumber2
	 */
	public String getIdNumber2()
	{
		return idNumber2;
	}
	/**
	 * @param idNumber2 the idNumber2 to set
	 */
	public void setIdNumber2(String idNumber2)
	{
		this.idNumber2 = idNumber2;
	}
	/**
	 * @return the superBPIdNumber2
	 */
	public String getSuperBPIdNumber2()
	{
		return superBPIdNumber2;
	}
	/**
	 * @param superBPIdNumber2 the superBPIdNumber2 to set
	 */
	public void setSuperBPIdNumber2(String superBPIdNumber2)
	{
		this.superBPIdNumber2 = superBPIdNumber2;
	}
	
	
	
	
}
