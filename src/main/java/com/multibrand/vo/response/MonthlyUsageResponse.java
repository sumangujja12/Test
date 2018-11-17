package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MonthlyUsagelResponse")
public class MonthlyUsageResponse 
{
	/** Instance to store esiid  */
	private String esiId;
	/** Instance to store contractId  */
	private String contractId;
	/** Instance to store contractAcctId  */
	private String contractAcctId;
	/** Instance to store busPartner  */
	private String busPartner;
	/** Instance to store yearMonthNo  */
	private String yearMonthNo;
	/** Instance to store totalUsageMonth  */
	private String totalUsageMonth;
	/** Instance to store totalMonthCost  */
	private String totalMonthCost;
	/** Instance to store monthAveTempHigh  */
	private String monthAveTempHigh;
	/** Instance to store monthAveTempLow  */
	private String monthAveTempLow;
	/** Instance to store totalUsageYear  */
	private String totalUsageYear;
	/** Instance to store totalYearCost  */
	private String totalYearCost;
	/** Instance to store yearAveTempHigh  */
	private String yearAveTempHigh;
	/** Instance to store yearAveTempLow  */
	private String yearAveTempLow;
	
	public String getEsiId() {
		return esiId;
	}
	public void setEsiId(String esiId) {
		this.esiId = esiId;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getContractAcctId() {
		return contractAcctId;
	}
	public void setContractAcctId(String contractAcctId) {
		this.contractAcctId = contractAcctId;
	}
	public String getBusPartner() {
		return busPartner;
	}
	public void setBusPartner(String busPartner) {
		this.busPartner = busPartner;
	}
	
	public String getYearMonthNo() {
		return yearMonthNo;
	}
	public void setYearMonthNo(String yearMonthNo) {
		this.yearMonthNo = yearMonthNo;
	}
	public String getTotalUsageMonth() {
		return totalUsageMonth;
	}
	public void setTotalUsageMonth(String totalUsageMonth) {
		this.totalUsageMonth = totalUsageMonth;
	}
	public String getTotalMonthCost() {
		return totalMonthCost;
	}
	public void setTotalMonthCost(String totalMonthCost) {
		this.totalMonthCost = totalMonthCost;
	}
	public String getMonthAveTempHigh() {
		return monthAveTempHigh;
	}
	public void setMonthAveTempHigh(String monthAveTempHigh) {
		this.monthAveTempHigh = monthAveTempHigh;
	}
	public String getMonthAveTempLow() {
		return monthAveTempLow;
	}
	public void setMonthAveTempLow(String monthAveTempLow) {
		this.monthAveTempLow = monthAveTempLow;
	}
	public String getTotalUsageYear() {
		return totalUsageYear;
	}
	public void setTotalUsageYear(String totalUsageYear) {
		this.totalUsageYear = totalUsageYear;
	}
	public String getTotalYearCost() {
		return totalYearCost;
	}
	public void setTotalYearCost(String totalYearCost) {
		this.totalYearCost = totalYearCost;
	}
	public String getYearAveTempHigh() {
		return yearAveTempHigh;
	}
	public void setYearAveTempHigh(String yearAveTempHigh) {
		this.yearAveTempHigh = yearAveTempHigh;
	}
	public String getYearAveTempLow() {
		return yearAveTempLow;
	}
	public void setYearAveTempLow(String yearAveTempLow) {
		this.yearAveTempLow = yearAveTempLow;
	}
	
	
	

}