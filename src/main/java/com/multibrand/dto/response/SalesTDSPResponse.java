package com.multibrand.dto.response;

import java.util.List;

import com.multibrand.vo.response.TDSPDO;

public class SalesTDSPResponse extends SalesBaseResponse{

	private String esidNumber;
	private String esidTDSP;
	private String switchHoldStatus;
	private String meterType;	

	private List<TDSPDO> tdspData;

	public String getEsidNumber() {
		return esidNumber;
	}

	public void setEsidNumber(String esidNumber) {
		this.esidNumber = esidNumber;
	}

	public String getEsidTDSP() {
		return esidTDSP;
	}

	public void setEsidTDSP(String esidTDSP) {
		this.esidTDSP = esidTDSP;
	}

	public String getSwitchHoldStatus() {
		return switchHoldStatus;
	}

	public void setSwitchHoldStatus(String switchHoldStatus) {
		this.switchHoldStatus = switchHoldStatus;
	}

	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public List<TDSPDO> getTdspData() {
		return tdspData;
	}

	public void setTdspData(List<TDSPDO> tdspData) {
		this.tdspData = tdspData;
	}	

}
