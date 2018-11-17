package com.multibrand.dto;

import java.io.Serializable;

import com.multibrand.util.CommonUtil;

/**
 * 
 * @author vsood30
 *
 */
public class ESIDDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String esidNumber;
	private String meterType;   // //permanent, temporary or unsure
	private String switchHoldStatus;
	private String recentDisconnectFlag;
	private String premiseType;
	private String esidStatus;
	private String esidProfileErrorCode;
	private String esidProfileErrorDescription;
	private Integer esidCount;
	private boolean hasError=false;
	private String esidError;
	private String esidNoteType;
	private String esidMsg;
	private String esidClass;
	private String esidDeposit;
	private String esidTDSP;
	private boolean esidCheckFlag;
	
	public String getEsidNumber() {
		return esidNumber;
	}

	public void setEsidNumber(String esidNumber) {
		this.esidNumber = esidNumber;
	}

	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public String getSwitchHoldStatus() {
		return switchHoldStatus;
	}

	public void setSwitchHoldStatus(String switchHoldStatus) {
		this.switchHoldStatus = switchHoldStatus;
	}

	public String getRecentDisconnectFlag() {
		return recentDisconnectFlag;
	}

	public void setRecentDisconnectFlag(String recentDisconnectFlag) {
		this.recentDisconnectFlag = recentDisconnectFlag;
	}

	public String getPremiseType() {
		return premiseType;
	}

	public void setPremiseType(String premiseType) {
		this.premiseType = premiseType;
	}

	public String getEsidStatus() {
		return esidStatus;
	}

	public void setEsidStatus(String esidStatus) {
		this.esidStatus = esidStatus;
	}

	public Integer getEsidCount() {
		return esidCount;
	}

	public void setEsidCount(Integer esidCount) {
		this.esidCount = esidCount;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	
	public String getEsidProfileErrorCode() {
		return esidProfileErrorCode;
	}

	public void setEsidProfileErrorCode(String esidProfileErrorCode) {
		this.esidProfileErrorCode = esidProfileErrorCode;
	}

	public String getEsidProfileErrorDescription() {
		return esidProfileErrorDescription;
	}

	public void setEsidProfileErrorDescription(String esidProfileErrorDescription) {
		this.esidProfileErrorDescription = esidProfileErrorDescription;
	}

	public String getEsidError() {
		return esidError;
	}

	public void setEsidError(String esidError) {
		this.esidError = esidError;
	}

	public String getEsidNoteType() {
		return esidNoteType;
	}

	public void setEsidNoteType(String esidNoteType) {
		this.esidNoteType = esidNoteType;
	}

	public String getEsidMsg() {
		return esidMsg;
	}

	public void setEsidMsg(String esidMsg) {
		this.esidMsg = esidMsg;
	}

	
	public String getEsidClass() {
		return esidClass;
	}

	public void setEsidClass(String esidClass) {
		this.esidClass = esidClass;
	}

	public String getEsidDeposit() {
		return esidDeposit;
	}

	public void setEsidDeposit(String esidDeposit) {
		this.esidDeposit = esidDeposit;
	}

	public String getEsidTDSP() {
		return esidTDSP;
	}

	public void setEsidTDSP(String esidTDSP) {
		this.esidTDSP = esidTDSP;
	}
	
	public boolean isEsidCheckFlag() {
		return esidCheckFlag;
	}

	public void setEsidCheckFlag(boolean esidCheckFlag) {
		this.esidCheckFlag = esidCheckFlag;
	}

	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}

	
	
	
}
