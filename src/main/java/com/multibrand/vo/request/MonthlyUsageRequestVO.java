package com.multibrand.vo.request;

public class MonthlyUsageRequestVO
{
	private String esiid;
	private String contractId;
	private String contractAccountId;
	private String companyCode;
	private String zoneId;
	private String curDate;
	
	public String getEsiid() {
		return esiid;
	}
	public void setEsiid(String esiid) {
		this.esiid = esiid;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getContractAccountId() {
		return contractAccountId;
	}
	public void setContractAccountId(String contractAccountId) {
		this.contractAccountId = contractAccountId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	/**
	 * @return the curDate
	 */
	public String getCurDate()
	{
		return curDate;
	}
	/**
	 * @param curDate the curDate to set
	 */
	public void setCurDate(String curDate)
	{
		this.curDate = curDate;
	}
	
	
	
	
}
	