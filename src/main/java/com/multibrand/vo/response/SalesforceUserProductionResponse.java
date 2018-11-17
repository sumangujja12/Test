package com.multibrand.vo.response;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;
import com.multibrand.util.Constants;

@XmlRootElement(name="USERPRODUCTION")
public class SalesforceUserProductionResponse implements Constants {
	
	
	@SerializedName("UserPeriodProductions")
	List<SalesforceUserPeriodProductionResponse> monthlysolardata;
	
	
	@SerializedName("NumberofTreesOffset")
	private BigDecimal numberofTreesOffset;
	
	@SerializedName("NumberofRailcarsOfCoalOffset")
	private BigDecimal numberofRailcarsOfCoalOffset;
	
	@SerializedName("NumberofCarsOffset")
	private BigDecimal numberofCarsOffset;
	
	@SerializedName("LIFETIMEPRODUCION")
	private BigDecimal lifeTimeProduction;
	
	@SerializedName("LIFETIMECREDITS")
	private BigDecimal lifeTimeMeterCredits;
	
	
	
	@SerializedName("CurrentPeriodStartYear")
	private String currentPeriodStartYear;	
	
	@SerializedName("CurrentPeriodStartMonth")
	private String currentPeriodStartMonth;	
	
	@SerializedName("CurrentPeriodProduction")
	private BigDecimal currentPeriodProduction;	
	
	@SerializedName("CurrentPeriodEndYear")
	private String currentPeriodEndYear;	
	
	@SerializedName("CurrentMonthProduction")
	private BigDecimal currentMonthProduction;	
	
	@SerializedName("CurrentMonth")
	private String currentMonth;

	

	/**
	 * @return the monthlysolardata
	 */
	public List<SalesforceUserPeriodProductionResponse> getMonthlysolardata() {
		return monthlysolardata;
	}

	/**
	 * @param monthlysolardata the monthlysolardata to set
	 */
	public void setMonthlysolardata(List<SalesforceUserPeriodProductionResponse> monthlysolardata) {
		this.monthlysolardata = monthlysolardata;
	}

	
	/**
	 * @return the numberofTreesOffset
	 */
	public BigDecimal getNumberofTreesOffset() {
		if (numberofTreesOffset == null) {
			return numberofTreesOffset;
		} else {
			return numberofTreesOffset.setScale(0, RoundingMode.CEILING);
		}
	}

	/**
	 * @param numberofTreesOffset the numberofTreesOffset to set
	 */
	public void setNumberofTreesOffset(BigDecimal numberofTreesOffset) {
		this.numberofTreesOffset = numberofTreesOffset;
	}

	
	/**
	 * @return the numberofRailcarsOfCoalOffset
	 */
	public BigDecimal getNumberofRailcarsOfCoalOffset() {
		if (numberofRailcarsOfCoalOffset == null) {
			return numberofRailcarsOfCoalOffset;
		} else {
			return numberofRailcarsOfCoalOffset.setScale(0, RoundingMode.CEILING);
		}
	}

	/**
	 * @param numberofRailcarsOfCoalOffset the numberofRailcarsOfCoalOffset to set
	 */
	public void setNumberofRailcarsOfCoalOffset(BigDecimal numberofRailcarsOfCoalOffset) {
		this.numberofRailcarsOfCoalOffset = numberofRailcarsOfCoalOffset;
	}

	/**
	 * @return the numberofCarsOffset
	 */
	public BigDecimal getNumberofCarsOffset() {
		if (numberofCarsOffset == null) {
			return numberofCarsOffset;
		} else {
			return numberofCarsOffset.setScale(0, RoundingMode.CEILING);
		}
	}

	/**
	 * @param numberofCarsOffset the numberofCarsOffset to set
	 */
	public void setNumberofCarsOffset(BigDecimal numberofCarsOffset) {
		this.numberofCarsOffset = numberofCarsOffset;
	}

	
	/**
	 * @return the currentPeriodStartYear
	 */
	public String getCurrentPeriodStartYear() {
		return currentPeriodStartYear;
	}

	/**
	 * @param currentPeriodStartYear the currentPeriodStartYear to set
	 */
	public void setCurrentPeriodStartYear(String currentPeriodStartYear) {
		this.currentPeriodStartYear = currentPeriodStartYear;
	}

	/**
	 * @return the currentPeriodStartMonth
	 */
	public String getCurrentPeriodStartMonth() {
		return currentPeriodStartMonth;
	}

	/**
	 * @param currentPeriodStartMonth the currentPeriodStartMonth to set
	 */
	public void setCurrentPeriodStartMonth(String currentPeriodStartMonth) {
		this.currentPeriodStartMonth = currentPeriodStartMonth;
	}

	

	/**
	 * @return the currentPeriodEndYear
	 */
	public String getCurrentPeriodEndYear() {
		return currentPeriodEndYear;
	}

	/**
	 * @param currentPeriodEndYear the currentPeriodEndYear to set
	 */
	public void setCurrentPeriodEndYear(String currentPeriodEndYear) {
		this.currentPeriodEndYear = currentPeriodEndYear;
	}

	/**
	 * @return the currentPeriodProduction
	 */
	public BigDecimal getCurrentPeriodProduction() {
		
		if (currentPeriodProduction == null) {
			return currentPeriodProduction;
		} else {
			return currentPeriodProduction.setScale(0, RoundingMode.CEILING);
		}
	}

	/**
	 * @param currentPeriodProduction the currentPeriodProduction to set
	 */
	public void setCurrentPeriodProduction(BigDecimal currentPeriodProduction) {
		this.currentPeriodProduction = currentPeriodProduction;
	}

	/**
	 * @return the currentMonthProduction
	 */
	public BigDecimal getCurrentMonthProduction() {
		if (currentMonthProduction == null) {
			return currentMonthProduction;
		} else {
			return currentMonthProduction.setScale(0, RoundingMode.CEILING);
		}
	}

	/**
	 * @param currentMonthProduction the currentMonthProduction to set
	 */
	public void setCurrentMonthProduction(BigDecimal currentMonthProduction) {
		this.currentMonthProduction = currentMonthProduction;
	}

	/**
	 * @return the currentMonth
	 */
	public String getCurrentMonth() {
		return currentMonth;
	}

	/**
	 * @param currentMonth the currentMonth to set
	 */
	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}

	/**
	 * @return the lifeTimeProduction
	 */
	public BigDecimal getLifeTimeProduction() {
		if (lifeTimeProduction == null) {
			return lifeTimeProduction;
		} else {
			return lifeTimeProduction.setScale(0, RoundingMode.CEILING);
		}
	}

	/**
	 * @param lifeTimeProduction the lifeTimeProduction to set
	 */
	public void setLifeTimeProduction(BigDecimal lifeTimeProduction) {
		this.lifeTimeProduction = lifeTimeProduction;
	}

	/**
	 * @return the lifeTimeMeterCredits
	 */
	public BigDecimal getLifeTimeMeterCredits() {
		if (lifeTimeMeterCredits == null) {
			return lifeTimeMeterCredits;
		} else { 
			return lifeTimeMeterCredits.setScale(0, RoundingMode.CEILING);
		}
	}

	/**
	 * @param lifeTimeMeterCredits the lifeTimeMeterCredits to set
	 */
	public void setLifeTimeMeterCredits(BigDecimal lifeTimeMeterCredits) {
		this.lifeTimeMeterCredits = lifeTimeMeterCredits;
	}	
}
