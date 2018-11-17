package com.multibrand.vo.response.billingResponse;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.billingResponse.PpdContractData;
import com.multibrand.util.CommonUtil;
import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name = "PrepayDocReadResponse")
public class PrepayDocReadResponse extends GenericResponse {

	private String contractAccountNumber;
	private String UUID;
	private Calendar tollTagStartDate;
	private Calendar tollTagEndDate;
	private double thresHoldAmt;
	private String statusCode;
	private String statusDesc;
	private Calendar serviceStartDate;
	private java.util.Calendar serviceEndDate;
	private double reloadAmount;
	private String PPDId;
	private boolean autoPaySetup;
	private boolean disconnected;
	
	private PpdContractData[] contractData;

	public boolean isDisconnected() {
		return disconnected;
	}

	public void setDisconnected(boolean disconnected) {
		this.disconnected = disconnected;
	}

	public String getContractAccountNumber() {
		return contractAccountNumber;
	}

	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}

	@XmlElement(name="statusDescription")
	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	@XmlElement(name="uuid")
	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getTollTagStartDate() {
		return CommonUtil.changeDateFormat(tollTagStartDate);
	}

	public void setTollTagStartDate(Calendar tollTagStartDate) {
		this.tollTagStartDate = tollTagStartDate;
	}

	public String getTollTagEndDate() {
		return CommonUtil.changeDateFormat(tollTagEndDate);
	}

	public void setTollTagEndDate(Calendar tollTagEndDate) {
		this.tollTagEndDate = tollTagEndDate;
	}

	
	@XmlElement(name="threshHoldAmount")
	public double getThresHoldAmt() {
		return thresHoldAmt;
	}

	public void setThresHoldAmt(double thresHoldAmt) {
		this.thresHoldAmt = thresHoldAmt;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getServiceStartDate() {
		
		return CommonUtil.changeDateFormat(serviceStartDate);
	}

	public void setServiceStartDate(Calendar serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public String getServiceEndDate() {
		return CommonUtil.changeDateFormat(serviceEndDate);
	}

	public void setServiceEndDate(java.util.Calendar serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	public double getReloadAmount() {
		return reloadAmount;
	}

	public void setReloadAmount(double reloadAmount) {
		this.reloadAmount = reloadAmount;
	}

	@XmlElement(name="ppdId")
	public String getPPDId() {
		return PPDId;
	}

	public void setPPDId(String pPDId) {
		PPDId = pPDId;
	}

	public boolean isAutoPaySetup() {
		return autoPaySetup;
	}

	public void setAutoPaySetup(boolean autoPaySetup) {
		this.autoPaySetup = autoPaySetup;
	}

	public PpdContractData[] getContractData() {
		return contractData;
	}

	public void setContractData(PpdContractData[] contractData) {
		this.contractData = contractData;
	}

}
