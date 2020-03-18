package com.multibrand.vo.response.gmd;

import java.util.List;

public class Pricing {

	private Current current;
	private List<PastSeries> pastSeries;
	private List<PredictedSeries> predictedSeries;

	/**
	 * @return the current
	 */
	public Current getCurrent() {
		return current;
	}

	/**
	 * @param current
	 *            the current to set
	 */
	public void setCurrent(Current current) {
		this.current = current;
	}

	/**
	 * @return the pastSeries
	 */
	public List<PastSeries> getPastSeries() {
		return pastSeries;
	}

	/**
	 * @param pastSeries
	 *            the pastSeries to set
	 */
	public void setPastSeries(List<PastSeries> pastSeries) {
		this.pastSeries = pastSeries;
	}

	/**
	 * @return the predictedSeries
	 */
	public List<PredictedSeries> getPredictedSeries() {
		return predictedSeries;
	}

	/**
	 * @param predictedSeries
	 *            the predictedSeries to set
	 */
	public void setPredictedSeries(List<PredictedSeries> predictedSeries) {
		this.predictedSeries = predictedSeries;
	}
}
