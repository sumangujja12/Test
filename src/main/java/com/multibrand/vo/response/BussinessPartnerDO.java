package com.multibrand.vo.response;

import java.util.List;

public class BussinessPartnerDO
{
	
	 private String bpNumber="";
	 private String companyCode ="";
	 private String brandName ="";
	 private String bpLastName="";
	 private String bpFirstName="";
	 private String bpMiddleName = "";
	 private String bpType = "";
	 private String bpOrgName="";
	 private String bpPreferedLang ="";
	 private String marketPreference="";
	 private String emailBounceFlag="";
	 private String emailId ="";
	 private List<BPPhoneDO> phoneDO;
	 private List<BPContractAccountDO> bpContractAccount;
	 
	 
	/**
	 * @return the bpNumber
	 */
	public String getBpNumber()
	{
		return bpNumber;
	}
	/**
	 * @param bpNumber the bpNumber to set
	 */
	public void setBpNumber(String bpNumber)
	{
		this.bpNumber = bpNumber;
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
	 * @return the bpLastName
	 */
	public String getBpLastName()
	{
		return bpLastName;
	}
	/**
	 * @param bpLastName the bpLastName to set
	 */
	public void setBpLastName(String bpLastName)
	{
		this.bpLastName = bpLastName;
	}
	/**
	 * @return the bpFirstName
	 */
	public String getBpFirstName()
	{
		return bpFirstName;
	}
	/**
	 * @param bpFirstName the bpFirstName to set
	 */
	public void setBpFirstName(String bpFirstName)
	{
		this.bpFirstName = bpFirstName;
	}
	/**
	 * @return the bpMiddleName
	 */
	public String getBpMiddleName()
	{
		return bpMiddleName;
	}
	/**
	 * @param bpMiddleName the bpMiddleName to set
	 */
	public void setBpMiddleName(String bpMiddleName)
	{
		this.bpMiddleName = bpMiddleName;
	}
	/**
	 * @return the bpType
	 */
	public String getBpType()
	{
		return bpType;
	}
	/**
	 * @param bpType the bpType to set
	 */
	public void setBpType(String bpType)
	{
		this.bpType = bpType;
	}
	/**
	 * @return the bpOrgName
	 */
	public String getBpOrgName()
	{
		return bpOrgName;
	}
	/**
	 * @param bpOrgName the bpOrgName to set
	 */
	public void setBpOrgName(String bpOrgName)
	{
		this.bpOrgName = bpOrgName;
	}
	/**
	 * @return the bpPreferedLang
	 */
	public String getBpPreferedLang()
	{
		return bpPreferedLang;
	}
	/**
	 * @param bpPreferedLang the bpPreferedLang to set
	 */
	public void setBpPreferedLang(String bpPreferedLang)
	{
		this.bpPreferedLang = bpPreferedLang;
	}
	/**
	 * @return the marketPreference
	 */
	public String getMarketPreference()
	{
		return marketPreference;
	}
	/**
	 * @param marketPreference the marketPreference to set
	 */
	public void setMarketPreference(String marketPreference)
	{
		this.marketPreference = marketPreference;
	}
	/**
	 * @return the emailBounceFlag
	 */
	public String getEmailBounceFlag()
	{
		return emailBounceFlag;
	}
	/**
	 * @param emailBounceFlag the emailBounceFlag to set
	 */
	public void setEmailBounceFlag(String emailBounceFlag)
	{
		this.emailBounceFlag = emailBounceFlag;
	}
	/**
	 * @return the emailId
	 */
	public String getEmailId()
	{
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}
	/**
	 * @return the phoneDO
	 */
	public List<BPPhoneDO> getPhoneDO()
	{
		return phoneDO;
	}
	/**
	 * @param phoneDO the phoneDO to set
	 */
	public void setPhoneDO(List<BPPhoneDO> phoneDO)
	{
		this.phoneDO = phoneDO;
	}
	/**
	 * @return the bpContractAccount
	 */
	public List<BPContractAccountDO> getBpContractAccount()
	{
		return bpContractAccount;
	}
	/**
	 * @param bpContractAccount the bpContractAccount to set
	 */
	public void setBpContractAccount(List<BPContractAccountDO> bpContractAccount)
	{
		this.bpContractAccount = bpContractAccount;
	}
	 
	 
	
	
}
