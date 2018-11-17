package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;
import com.multibrand.util.CommonUtil;

@XmlRootElement(name="FacilityPeriodProductions")
public class SalesforceFacilityPeriodProductionResponse  {
	
	
	
	@SerializedName("Net_KwH__c")
	private String production;
	
	@SerializedName("Period_Year__c")
	private String periodYear;
	
	@SerializedName("Period_Month__c")
	private String periodMonth;
	
	@SerializedName("Solar_Radiation_kWh_m_2_day__c")
	private String radiation;	
	
	@SerializedName("Name")
	private String facilityName;
	
	private String month;
	

	/**
	 * @return the production
	 */
	public String getProduction() {
		return  CommonUtil.stringToDecimalFormat(production);
	}

	/**
	 * @param production the production to set
	 */
	public void setProduction(String production) {
		this.production = production;
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
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return facilityName;
	}

	/**
	 * @param facilityName the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	
	/**
	 * @return the month
	 */
	public String getMonth() {
		return CommonUtil.changeDateFormat(periodMonth,"MMMM", "MMM") +" " + periodYear;
	}	
}
