package com.multibrand.dto.response;

import java.util.List;

public class DailyTemperatureResponse{
	
	private List<Temperature> tempList;
	private boolean dataAvailable = false;
	private String errorMessage;

	public List<Temperature> getTempList() {
		return tempList;
	}

	public void setTempList(List<Temperature> tempList) {
		this.tempList = tempList;
	}

	public boolean isDataAvailable() {
		return dataAvailable;
	}

	public void setDataAvailable(boolean dataAvailable) {
		this.dataAvailable = dataAvailable;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
