package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EsidProfileResponse extends GenericResponse {

	private java.lang.String ESID;

    private java.lang.String esidStatus;

    private java.lang.String meterType;

    private java.lang.String premiseType;

    private java.lang.String recentDisconnectFlag;

    private java.lang.String switchHoldStatus;

	public java.lang.String getESID() {
		return ESID;
	}

	public void setESID(java.lang.String eSID) {
		ESID = eSID;
	}

	public java.lang.String getEsidStatus() {
		return esidStatus;
	}

	public void setEsidStatus(java.lang.String esidStatus) {
		this.esidStatus = esidStatus;
	}

	public java.lang.String getMeterType() {
		return meterType;
	}

	public void setMeterType(java.lang.String meterType) {
		this.meterType = meterType;
	}

	public java.lang.String getPremiseType() {
		return premiseType;
	}

	public void setPremiseType(java.lang.String premiseType) {
		this.premiseType = premiseType;
	}

	public java.lang.String getRecentDisconnectFlag() {
		return recentDisconnectFlag;
	}

	public void setRecentDisconnectFlag(java.lang.String recentDisconnectFlag) {
		this.recentDisconnectFlag = recentDisconnectFlag;
	}

	public java.lang.String getSwitchHoldStatus() {
		return switchHoldStatus;
	}

	public void setSwitchHoldStatus(java.lang.String switchHoldStatus) {
		this.switchHoldStatus = switchHoldStatus;
	}

    
    
}
