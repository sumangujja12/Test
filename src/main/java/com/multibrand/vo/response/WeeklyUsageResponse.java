package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="WeeklyUsageResponse")
public class WeeklyUsageResponse
{

	private String wkStrtDt;
	private String wkEndDt;
	private String wkAvgUsg;
	private String wkAvgusgPct;
	private String wkUsgInd;
	private String wkAvgTemp;
	//private String wkAvgtempPct;
	private String wkAvgTempDiff;
	private String wkTempInd;
	
	
	
	/**
	 * @return the wkStrtDt
	 */
	public String getWkStrtDt() {
		return wkStrtDt;
	}
	/**
	 * @param wkStrtDt the wkStrtDt to set
	 */
	public void setWkStrtDt(String wkStrtDt) {
		this.wkStrtDt = wkStrtDt;
	}
	/**
	 * @return the wkEndDt
	 */
	public String getWkEndDt() {
		return wkEndDt;
	}
	/**
	 * @param wkEndDt the wkEndDt to set
	 */
	public void setWkEndDt(String wkEndDt) {
		this.wkEndDt = wkEndDt;
	}
	/**
	 * @return the wkAvgUsg
	 */
	public String getWkAvgUsg() {
		return wkAvgUsg;
	}
	/**
	 * @param wkAvgUsg the wkAvgUsg to set
	 */
	public void setWkAvgUsg(String wkAvgUsg) {
		this.wkAvgUsg = wkAvgUsg;
	}
	/**
	 * @return the wkAvgusgPct
	 */
	public String getWkAvgusgPct() {
		return wkAvgusgPct;
	}
	/**
	 * @param wkAvgusgPct the wkAvgusgPct to set
	 */
	public void setWkAvgusgPct(String wkAvgusgPct) {
		this.wkAvgusgPct = wkAvgusgPct;
	}
	/**
	 * @return the wkUsgInd
	 */
	public String getWkUsgInd() {
		return wkUsgInd;
	}
	/**
	 * @param wkUsgInd the wkUsgInd to set
	 */
	public void setWkUsgInd(String wkUsgInd) {
		this.wkUsgInd = wkUsgInd;
	}
	/**
	 * @return the wkAvgTemp
	 */
	public String getWkAvgTemp() {
		return wkAvgTemp;
	}
	/**
	 * @param wkAvgTemp the wkAvgTemp to set
	 */
	public void setWkAvgTemp(String wkAvgTemp) {
		this.wkAvgTemp = wkAvgTemp;
	}
	/**
	 * @return the wkTempInd
	 */
	public String getWkTempInd() {
		return wkTempInd;
	}
	/**
	 * @param wkTempInd the wkTempInd to set
	 */
	public void setWkTempInd(String wkTempInd) {
		this.wkTempInd = wkTempInd;
	}
	/**
	 * @return the wkAvgTempDiff
	 */
	public String getWkAvgTempDiff() {
		return wkAvgTempDiff;
	}
	/**
	 * @param wkAvgTempDiff the wkAvgTempDiff to set
	 */
	public void setWkAvgTempDiff(String wkAvgTempDiff) {
		this.wkAvgTempDiff = wkAvgTempDiff;
	}
	
	
}
