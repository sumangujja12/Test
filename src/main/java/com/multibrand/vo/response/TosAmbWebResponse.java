package com.multibrand.vo.response;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.domain.AmbOutputTab;


@XmlRootElement
public class TosAmbWebResponse extends GenericResponse {
	private BigDecimal exAmbInitial;
	private AmbOutputTab[] exAmbTabWebOutput;	
	private BigDecimal exDeferredBalance;
	private String exEligible;
	private String errorMessage;
	private String errorCode;
	
	public BigDecimal getExAmbInitial() {
		return exAmbInitial;
	}
	public void setExAmbInitial(BigDecimal exAmbInitial) {
		this.exAmbInitial = exAmbInitial;
	}
	public AmbOutputTab[] getExAmbTabWebOutput() {
		return exAmbTabWebOutput;
	}
	public void setExAmbTabWebOutput(AmbOutputTab[] exAmbTabWebOutput) {
		this.exAmbTabWebOutput = exAmbTabWebOutput;
	}
	public BigDecimal getExDeferredBalance() {
		return exDeferredBalance;
	}
	public void setExDeferredBalance(BigDecimal exDeferredBalance) {
		this.exDeferredBalance = exDeferredBalance;
	}
	public String getExEligible() {
		return exEligible;
	}
	public void setExEligible(String exEligible) {
		this.exEligible = exEligible;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
