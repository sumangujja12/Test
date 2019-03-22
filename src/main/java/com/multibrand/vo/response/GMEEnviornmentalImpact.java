package com.multibrand.vo.response;

import org.springframework.stereotype.Component;

@Component("gmeEnvImpact")
public class GMEEnviornmentalImpact extends GenericResponse {

	private double totalPoundOfCO2ForCurrentYr;
	private double toatlTreesPlantedForCurrentYr;
	
	public double getTotalPoundOfCO2ForCurrentYr() {
		return totalPoundOfCO2ForCurrentYr;
	}
	public void setTotalPoundOfCO2ForCurrentYr(double totalPoundOfCO2ForCurrentYr) {
		this.totalPoundOfCO2ForCurrentYr = totalPoundOfCO2ForCurrentYr;
	}
	public double getToatlTreesPlantedForCurrentYr() {
		return toatlTreesPlantedForCurrentYr;
	}
	public void setToatlTreesPlantedForCurrentYr(double toatlTreesPlantedForCurrentYr) {
		this.toatlTreesPlantedForCurrentYr = toatlTreesPlantedForCurrentYr;
	}
	
	

	
}
