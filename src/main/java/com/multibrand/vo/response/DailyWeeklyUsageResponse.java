package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DailyWeeklyUsageResponse")
public class DailyWeeklyUsageResponse 
{
	/** Instance to store esiid  */
	private String esiId ="";
	/** Instance to store contractId  */
	private String contractId ="";
	/** Instance to store contractAcctId  */
	private String contractAcctId ="";
	/** Instance to store busPartner  */
	private String busPartner ="";
	/** Instance to store curBillDt  */
	private String curBillDt;
	/** Instance to store totDayUsg  */
	private String totDayUsg; 
	/** Instance to store totDayCst  */
	private String totDayCst;
	/** Instance to store dayTempHigh  */
	private String dayTempHigh;
	/** Instance to store dayTempLow  */
	private String dayTempLow; 
	/** Instance to store totWkUsg  */
	private String totWkUsg;
	/** Instance to store totWkCst  */
	private String totWkCst;
	/** Instance to store wkAveTempHigh  */
	private String wkAveTempHigh;
	/** Instance to store wkAveTempLow  */
	private String wkAveTempLow;
	
	
	/**
	 * @return the esiId
	 */
	public String getEsiId() {
		return esiId;
	}
	/**
	 * @param esiId the esiId to set
	 */
	public void setEsiId(String esiId) {
		this.esiId = esiId;
	}
	/**
	 * @return the contractId
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return the contractAcctId
	 */
	public String getContractAcctId() {
		return contractAcctId;
	}
	/**
	 * @param contractAcctId the contractAcctId to set
	 */
	public void setContractAcctId(String contractAcctId) {
		this.contractAcctId = contractAcctId;
	}
	/**
	 * @return the busPartner
	 */
	public String getBusPartner() {
		return busPartner;
	}
	/**
	 * @param busPartner the busPartner to set
	 */
	public void setBusPartner(String busPartner) {
		this.busPartner = busPartner;
	}
	/**
	 * @return the curBillDt
	 */
	public String getCurBillDt() {
		return curBillDt;
	}
	/**
	 * @param curBillDt the curBillDt to set
	 */
	public void setCurBillDt(String curBillDt) {
		this.curBillDt = curBillDt;
	}
	/**
	 * @return the totDayUsg
	 */
	public String getTotDayUsg() {
		return totDayUsg;
	}
	/**
	 * @param totDayUsg the totDayUsg to set
	 */
	public void setTotDayUsg(String totDayUsg) {
		this.totDayUsg = totDayUsg;
	}
	/**
	 * @return the totDayCst
	 */
	public String getTotDayCst() {
		return totDayCst;
	}
	/**
	 * @param totDayCst the totDayCst to set
	 */
	public void setTotDayCst(String totDayCst) {
		this.totDayCst = totDayCst;
	}
	/**
	 * @return the dayTempHigh
	 */
	public String getDayTempHigh() {
		return dayTempHigh;
	}
	/**
	 * @param dayTempHigh the dayTempHigh to set
	 */
	public void setDayTempHigh(String dayTempHigh) {
		this.dayTempHigh = dayTempHigh;
	}
	/**
	 * @return the dayTempLow
	 */
	public String getDayTempLow() {
		return dayTempLow;
	}
	/**
	 * @param dayTempLow the dayTempLow to set
	 */
	public void setDayTempLow(String dayTempLow) {
		this.dayTempLow = dayTempLow;
	}
	/**
	 * @return the totWkUsg
	 */
	public String getTotWkUsg() {
		return totWkUsg;
	}
	/**
	 * @param totWkUsg the totWkUsg to set
	 */
	public void setTotWkUsg(String totWkUsg) {
		this.totWkUsg = totWkUsg;
	}
	/**
	 * @return the totWkCst
	 */
	public String getTotWkCst() {
		return totWkCst;
	}
	/**
	 * @param totWkCst the totWkCst to set
	 */
	public void setTotWkCst(String totWkCst) {
		this.totWkCst = totWkCst;
	}
	/**
	 * @return the wkAveTempHigh
	 */
	public String getWkAveTempHigh() {
		return wkAveTempHigh;
	}
	/**
	 * @param wkAveTempHigh the wkAveTempHigh to set
	 */
	public void setWkAveTempHigh(String wkAveTempHigh) {
		this.wkAveTempHigh = wkAveTempHigh;
	}
	/**
	 * @return the wkAveTempLow
	 */
	public String getWkAveTempLow() {
		return wkAveTempLow;
	}
	/**
	 * @param wkAveTempLow the wkAveTempLow to set
	 */
	public void setWkAveTempLow(String wkAveTempLow) {
		this.wkAveTempLow = wkAveTempLow;
	}

	
}
