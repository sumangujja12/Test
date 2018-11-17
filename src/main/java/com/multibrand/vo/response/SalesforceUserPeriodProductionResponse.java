package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;
import com.multibrand.util.CommonUtil;

@XmlRootElement(name="UserPeriodProductions")
public class SalesforceUserPeriodProductionResponse  {
	
	
	
	@SerializedName("Net_KwH__c")
	private String production;
	
	@SerializedName("Lease_Id__c")
	private String leaseId;	
	
	@SerializedName("Period_Month__c")
	private String periodMonth;	
	
	@SerializedName("Period_Year__c")
	private String periodYear;	
	
	@SerializedName("Solar_Radiation_kWh_m_2_day__c")
	private String radiation;
	
	@SerializedName("Virtual_Net_Metering_Credits__c")
	private String meterCredit;
	
	private String month;
	

	/**
	 * @return the production
	 */
	public String getProduction() {
		return CommonUtil.stringToDecimalFormat(production);
	}

	/**
	 * @param production the production to set
	 */
	public void setProduction(String production) {
		this.production = production;
	}

	/**
	 * @return the leaseId
	 */
	public String getLeaseId() {
		return leaseId;
	}

	/**
	 * @param leaseId the leaseId to set
	 */
	public void setLeaseId(String leaseId) {
		this.leaseId = leaseId;
	}

	/**
	 * @return the periodMonth
	 */
	public String getPeriodMonth() {
		return periodMonth;
	}

	/**
	 * @param periodMonth the periodMonth to set
	 */
	public void setPeriodMonth(String periodMonth) {
		this.periodMonth = periodMonth;
	}

	/**
	 * @return the periodYear
	 */
	public String getPeriodYear() {
		return periodYear;
	}

	/**
	 * @param periodYear the periodYear to set
	 */
	public void setPeriodYear(String periodYear) {
		this.periodYear = periodYear;
	}

	/**
	 * @return the radiation
	 */
	public String getRadiation() {
		return CommonUtil.stringToDecimalFormat(radiation);
	}

	/**
	 * @param radiation the radiation to set
	 */
	public void setRadiation(String radiation) {
		this.radiation = radiation;
	}

	/**
	 * @return the meterCredit
	 */
	public String getMeterCredit() {
		return CommonUtil.stringToDecimalFormat(meterCredit);
	}

	/**
	 * @param meterCredit the meterCredit to set
	 */
	public void setMeterCredit(String meterCredit) {
		this.meterCredit = meterCredit;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return CommonUtil.changeDateFormat(periodMonth,"MMMM", "MMM") +" " + periodYear;
	}

	
}
